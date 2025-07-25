package bank.domain;


import bank.data.UserRepository;
import bank.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo
            , PasswordEncoder encoder
    ){
        this.repository = repo;
        this.encoder = encoder;
    }

    public User findUserById(int id){
        User user = repository.findById(id);

        return user;
    }

    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Result<User> updateUser(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Result<User> res = new Result<>();
        if(violations.size() > 0){
            violations.stream().forEach(violation -> {
                res.addMessage(violation.getMessage(), ResultType.INVALID);
            });
            return res;
        }
        if (user.getUserId() <= 0) {
            res.addMessage("userId cannot be set for `update` operation", ResultType.INVALID);
            return res;
        }

        if (!repository.update(user)) {
            String msg = String.format("userId: %s, not found", user.getUserId());
            res.addMessage(msg, ResultType.NOT_FOUND);
        }
        return res;
    }

    public Result<User> addUser(User user){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Result<User> res = new Result<>();
        if(violations.size() > 0){
            violations.stream().forEach(violation -> {
                res.addMessage(violation.getMessage(), ResultType.INVALID);
            });
            return res;
        }
        else {
            if (user.getUserId() != 0) {
                res.addMessage("userId cannot be set for `add` operation", ResultType.INVALID);
                return res;
            }
            user.setPasswordHash(encoder.encode(user.getPasswordHash()));
            User userAdded = repository.addUser(user);
            res.setPayload(userAdded);
            return res;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email + " not found");
        }
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().getValue()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), authorities);
    }
}
