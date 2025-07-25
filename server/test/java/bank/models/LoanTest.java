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

class LoanTest {

    private final Validator validator;

    public LoanTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void emptyLoanShouldFailValidation() {
        Loan loan = new Loan();
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nullInitialAmountShouldFailValidation() {
        Loan loan = new Loan(1, LocalDate.now(), 5.0, null,
                LocalDate.now().plusMonths(6), LoanStatus.APPROVED,
                LocalDate.now(), BigDecimal.ZERO, 1, new LoanType());

        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("initialAmount")));
    }

    @Test
    void negativeBalanceShouldFailValidation() {
        Loan loan = new Loan(1, LocalDate.now(), 5.0, BigDecimal.valueOf(10000),
                LocalDate.now().plusMonths(6), LoanStatus.APPROVED,
                LocalDate.now(), BigDecimal.valueOf(-100), 1, new LoanType());

        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("balance")));
    }

    @Test
    void nullStatusShouldFailValidation() {
        Loan loan = new Loan(1, LocalDate.now(), 5.0, BigDecimal.valueOf(10000),
                LocalDate.now().plusMonths(6), null,
                LocalDate.now(), BigDecimal.valueOf(10000), 1, new LoanType());

        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("status")));
    }

    @Test
    void zeroUserIdShouldFailValidation() {
        Loan loan = new Loan(1, LocalDate.now(), 5.0, BigDecimal.valueOf(10000),
                LocalDate.now().plusMonths(6), LoanStatus.APPROVED,
                LocalDate.now(), BigDecimal.valueOf(10000), 0, new LoanType());

        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("userId")));
    }

    @Test
    void validLoanShouldPassValidation() {
        Loan loan = new Loan(1, LocalDate.now(), 5.0, BigDecimal.valueOf(10000),
                LocalDate.now().plusMonths(6), LoanStatus.APPROVED,
                LocalDate.now(), BigDecimal.valueOf(10000), 1, new LoanType());

        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        assertTrue(violations.isEmpty());
    }
}
