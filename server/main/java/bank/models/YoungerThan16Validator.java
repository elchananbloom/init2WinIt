package bank.models;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class YoungerThan16Validator implements ConstraintValidator<NoYoungerThan16, User> {


    @Override
    public void initialize(NoYoungerThan16 constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if(user == null || user.getDob() == null) {
            return true;
        }

        return !user.getDob().isAfter(LocalDate.now().minusYears(16));
    }
}
