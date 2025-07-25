package bank.domain;


import bank.data.AccountRepository;
import bank.models.Account;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
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
            account = generateAccountNumber(account);
            Account accountAdded = repository.add(account);
            res.setPayload(accountAdded);
            return res;
        }
    }

    public Result<Account> update(Account account) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        Result<Account> res = new Result<>();
        if(!violations.isEmpty()){
            violations.forEach(violation -> {
                res.addMessage(violation.getMessage(), ResultType.INVALID);
            });
            return res;
        }

        if (account.getAccountId() <= 0) {
            res.addMessage("accountId cannot be set for `update` operation", ResultType.INVALID);
            return res;
        }

        if (!repository.update(account)) {
            String msg = String.format("accountId: %s, not found", account.getAccountId());
            res.addMessage(msg, ResultType.NOT_FOUND);
        }
        return res;
    }
    public Account generateAccountNumber(Account account){
        Random rand = new Random();
        account.setAccountNumber(String.valueOf(account.getUserId()) + String.valueOf(LocalDate.now().getYear()) + String.valueOf(rand.nextInt(5000)));
        return account;
    }

}
