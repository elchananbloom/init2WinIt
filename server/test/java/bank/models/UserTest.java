package bank.models;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void emptyTransactionCategoryShouldFailValidation() {
        User user = new User();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }


    @Test
    void emptyFirstNameShouldFailValidation() {
        User user = new User(1, "", "lastName", "address", "9991234567", "email@gmail.com", "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }


    @Test
    void emptyLastNameShouldFailValidation() {
        User user = new User(1, "firstName", "", "address", "9991234567", "email@gmail.com", "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyAddressShouldFailValidation() {
        User user = new User(1, "firstName", "", "address", "9991234567", "email@gmail.com", "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyPhoneNumberShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "", "email@gmail.com", "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyEmailShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "", "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyPassWordHashShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com", "", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyDateHashShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com", "", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nullRoleShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com", "paswordHash", LocalDate.now()
                , null);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }
}