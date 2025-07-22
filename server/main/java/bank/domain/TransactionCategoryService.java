package bank.domain;

import bank.data.TransactionCategoryRepository;
import bank.models.TransactionCategory;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class TransactionCategoryService {

    private final TransactionCategoryRepository repository;

    public TransactionCategoryService(TransactionCategoryRepository repository) {
        this.repository = repository;
    }

    public List<TransactionCategory> findAll() {
        return repository.findAll();
    }

    public Result<TransactionCategory> add(TransactionCategory transactionCategory) {
        Result<TransactionCategory> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<TransactionCategory>> violations = validator.validate(transactionCategory);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<TransactionCategory> violation: violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (transactionCategory.getTransactionCategoryId() != 0) {
            result.addMessage("TransactionCategoryId cannot be set for 'add' operation", ResultType.INVALID);
        }

        transactionCategory = repository.add(transactionCategory);
        result.setPayload(transactionCategory);
        return result;
    }

    public  Result<TransactionCategory> update(TransactionCategory transactionCategory) {
        Result<TransactionCategory> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<TransactionCategory>> violations = validator.validate(transactionCategory);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<TransactionCategory> violation: violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }

        if (transactionCategory.getTransactionCategoryId() <= 0) {
            result.addMessage("TransactionCategoryId cannot be set for 'update' operation", ResultType.INVALID);
        }

        if (!repository.update(transactionCategory)) {
            String msg = String.format("TransactionCategoryId: %s, not found", transactionCategory.getTransactionCategoryId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }
}
