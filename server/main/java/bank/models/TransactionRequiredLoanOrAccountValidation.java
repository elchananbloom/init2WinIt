package bank.models;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionRequiredLoanOrAccountValidation implements ConstraintValidator<TransactionRequiredLoanOrAccount, Transaction> {
    @Override
    public void initialize(TransactionRequiredLoanOrAccount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintValidatorContext) {
        if (transaction == null) {
            return true;
        }

        return  !(transaction.getAccountId() <= 0 && transaction.getLoanId() <= 0);
    }
}
