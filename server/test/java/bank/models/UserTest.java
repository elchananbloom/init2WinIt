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
        User user = new User(1, "", "lastName", "address", "9991234567", "email@gmail.com",
                LocalDate.parse("2000-02-02"),"passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }


    @Test
    void emptyLastNameShouldFailValidation() {
        User user = new User(1, "firstName", "", "address", "9991234567", "email@gmail.com",
                LocalDate.parse("2000-02-02"), "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyAddressShouldFailValidation() {
        User user = new User(1, "firstName", "", "address", "9991234567", "email@gmail.com",
                LocalDate.parse("2000-02-02"), "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyPhoneNumberShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "", "email@gmail.com",
                LocalDate.parse("2000-02-02"), "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyEmailShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "",
                LocalDate.parse("2000-02-02"), "passwordHash", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyPassWordHashShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com",
                LocalDate.parse("2000-02-02"), "", LocalDate.now()
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyDateShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com",
                null, "1234", null
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void youngerThan16ShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com",
                LocalDate.parse("2020-02-02"), "1234", null
                , Role.USER);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nullRoleShouldFailValidation() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com",
                LocalDate.parse("2000-02-02"), "paswordHash", LocalDate.now()
                , null);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }
}