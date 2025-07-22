package bank.domain;


import bank.data.AccountRepository;
import bank.models.Account;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll(){
        return repository.findAll();
    }

    public List<Account> findByUserId(int userId){
        return repository.findByUserId(userId);
    }

    public Account findById(int accountId){
        return repository.findById(accountId);
    }

    public Result<Account> add(Account account){
        account = generateAccountNumber(account);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        Result<Account> res = new Result<>();
        if(violations.size() > 0){
            violations.stream().forEach(violation -> {
                res.addMessage(violation.getMessage(), ResultType.INVALID);
            });
            return res;
        }
        else {
            if (account.getAccountId() != 0) {
                res.addMessage("accountId cannot be set for `add` operation", ResultType.INVALID);
                return res;
            }
            Account accountAdded = repository.add(account);
            res.setPayload(accountAdded);
            return res;
        }
    }

    public Account generateAccountNumber(Account account){
        account.setAccountNumber(String.valueOf(account.getUserId()) + String.valueOf(account.getAccountId()) + String.valueOf(account.getCreatedAt().getYear()));
        return account;
    }

}
