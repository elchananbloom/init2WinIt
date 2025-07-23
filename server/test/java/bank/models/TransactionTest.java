package bank.models;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void emptyTransactionShouldFailValidation() {
        Transaction transaction = new Transaction();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
    }

    @Test
    void negativeAmountTransactionShouldFailValidation() {

        Transaction transaction = new Transaction(1, BigDecimal.valueOf(-200.00), "WITHDRAWAL", LocalDate.parse("2025-01-02"), "emergency car fix", new TransactionCategory(3,"Car"),1,0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
    }

    @Test
    void NoAccountAndNoLoanTransactionShouldFailValidation() {

        Transaction transaction = new Transaction(1, BigDecimal.valueOf(200.00), "WITHDRAWAL", LocalDate.parse("2025-01-02"), "emergency car fix", new TransactionCategory(3,"Car"),0,0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyTransactionTypeShouldFailValidation() {

        Transaction transaction = new Transaction(1, BigDecimal.valueOf(200.00), "", LocalDate.parse("2025-01-02"), "emergency car fix", new TransactionCategory(3,"Car"),1,0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nullTransactionCategoryShouldFailValidation() {

        Transaction transaction = new Transaction(1, BigDecimal.valueOf(200.00), "WITHDRAWAL", LocalDate.parse("2025-01-02"), "emergency car fix", null,1,0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertFalse(violations.isEmpty());
    }

}