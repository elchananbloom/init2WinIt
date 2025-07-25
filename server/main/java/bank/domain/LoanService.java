package bank.domain;

import bank.data.LoanRepository;
import bank.models.Loan;
import bank.models.LoanStatus;
import bank.models.User;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public List<Loan> findAll() {
        return repository.findAll();
    }

    public List<Loan> findByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    public Loan findById(int loanId) {
        return repository.findById(loanId);
    }

    public Result<Loan> add(Loan loan) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        Result<Loan> res = new Result<>();
        if(!violations.isEmpty()){
            violations.forEach(violation -> {
                res.addMessage(violation.getMessage(), ResultType.INVALID);
            });
            return res;
        }

        if (loan.getLoanId() != 0) {
            res.addMessage("loanId cannot be set for `add` operation", ResultType.INVALID);
            return res;
        }

        loan = repository.add(loan);
        res.setPayload(loan);
        return res;
    }

    public Result<Loan> update(Loan loan) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        Result<Loan> res = new Result<>();
        if(!violations.isEmpty()){
            violations.forEach(violation -> {
                res.addMessage(violation.getMessage(), ResultType.INVALID);
            });
            return res;
        }

        if (loan.getLoanId() <= 0) {
            res.addMessage("loanId cannot be set for `update` operation", ResultType.INVALID);
            return res;
        }

        if (!repository.update(loan)) {
            String msg = String.format("loanId: %s, not found", loan.getLoanId());
            res.addMessage(msg, ResultType.NOT_FOUND);
        }
        return res;
    }

    public Result<Loan> delete(int loanId) {
        Result<Loan> result = new Result<>();
        Loan loan = findById(loanId);
        if (loan != null && loan.getStatus() != LoanStatus.IN_PROGRESS) {
            result.addMessage("Cannot delete loan not in 'in progress' mode", ResultType.INVALID);
            return result;
        }
        if (!repository.delete(loanId)) {
            String msg = String.format("loanId: %s, not found", loanId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }
}
