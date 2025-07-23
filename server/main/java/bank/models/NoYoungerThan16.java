package bank.models;

import bank.models.YoungerThan16Validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {YoungerThan16Validator.class})
@Documented
public @interface NoYoungerThan16 {
    String message() default "User younger than 16 are not allowed";

    Class[] groups() default {};

    Class[] payload() default {};
}
