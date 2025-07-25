package bank.domain;

import bank.data.TransactionRepository;
import bank.models.Account;
import bank.models.Loan;
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
    private final AccountService accountService;
    private final LoanService loanService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, LoanService loanService){
        this.repository = transactionRepository;
        this.accountService = accountService;
        this.loanService = loanService;
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

        if(!violations.isEmpty()){
            for (ConstraintViolation<Transaction> violation: violations) {
                res.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }

        if(!res.isSuccess()){
            return res;
        }

        else{
            if (transaction.getTransactionId() != 0) {
                res.addMessage("userId cannot be set for `add` operation", ResultType.INVALID);
                return res;
            }
            res = makeTransaction(transaction);
            if (!res.isSuccess()) {
                return res;
            }

            Transaction transactionAdded = repository.add(transaction);
            res.setPayload(transactionAdded);
            return res;
        }
    }

    private Result<Transaction> makeTransaction(Transaction transaction) {
        Result<Transaction> result = new Result<>();
        if (transaction.getAccountId() > 0) {
            Account account = accountService.findById(transaction.getAccountId());
            if (transaction.getType().equals("WITHDRAWAL")) {
                account.setBalance(account.getBalance().subtract(transaction.getAmount()));
            } else if (transaction.getType().equals("DEPOSIT")) {
                account.setBalance(account.getBalance().add(transaction.getAmount()));
            }
            Result<Account> accountResult = accountService.update(account);
            if (!accountResult.isSuccess()) {
                for (String message: accountResult.getMessages()) {
                    result.addMessage(message, ResultType.INVALID);
                }
            }
        }
        if (transaction.getLoanId() > 0) {
            Loan loan = loanService.findById(transaction.getLoanId());
            loan.setBalance(loan.getBalance().subtract(transaction.getAmount()));
            Result<Loan> loanResult = loanService.update(loan);
            if (!loanResult.isSuccess()) {
                for (String message: loanResult.getMessages()) {
                    result.addMessage(message, ResultType.INVALID);
                }
            }
        }
        return result;
    }
}
