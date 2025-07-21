package bank.models;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransactionCategoryTest {
    @Test
    void emptyTransactionCategoryShouldFailValidation() {
        TransactionCategory transactionCategory = new TransactionCategory();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<TransactionCategory>> violations = validator.validate(transactionCategory);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyTransactionCategoryNameShouldFailValidation() {
        TransactionCategory transactionCategory = new TransactionCategory(1, null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<TransactionCategory>> violations = validator.validate(transactionCategory);
        assertFalse(violations.isEmpty());
    }
}