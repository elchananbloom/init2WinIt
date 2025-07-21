package bank.domain;


import bank.data.UserRepository;
import bank.models.User;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repo){
        this.repository = repo;
    }

    public User findUserById(int id){
        User user = repository.findById(id);

        return user;
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
                res.addMessage("agentId cannot be set for `add` operation", ResultType.INVALID);
                return res;
            }
            User userAdded = repository.addUser(user);
            res.setPayload(userAdded);
            return res;
        }
    }

}
