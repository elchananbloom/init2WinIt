package bank.models;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TransactionRequiredLoanOrAccountValidation.class})
@Documented
public @interface TransactionRequiredLoanOrAccount {
    String message() default "Transaction has to have at least accountId or LoanId";

    Class[] groups() default {};

    Class[] payload() default {};
}
