package bank.domain;

import bank.data.TransactionRepository;
import bank.models.Transaction;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class TransactionService {

    private TransactionRepository repository;

    public TransactionService(TransactionRepository transactionRepository){
        this.repository = transactionRepository;
    }

    public List<Transaction> findByAccountId(int accountId){
        List<Transaction> res = repository.findByAccountId(accountId);
        return res;
    }

    public List<Transaction> findByLoanId(int loanId){
        List<Transaction> res = repository.findByLoanId(loanId);
        return res;
    }

    public Result<Transaction> addTransaction(Transaction transaction){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        Result<Transaction> res = new Result<>();

        if(violations.size() > 0){
            violations.stream().forEach(violation -> res.addMessage(violation.getMessage(), ResultType.INVALID));
        }

        if(!res.isSuccess()){
            return res;
        }

        else{
            if (transaction.getTransactionId() != 0) {
                res.addMessage("userId cannot be set for `add` operation", ResultType.INVALID);
                return res;
            }
            Transaction transactionAdded = repository.add(transaction);

            res.setPayload(transactionAdded);
            return res;
        }



    }
}
