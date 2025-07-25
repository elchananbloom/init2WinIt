package bank.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NotNull(message = "Loan Type cannot be null")
public class LoanType {

    private int loanTypeId;

    @NotBlank(message = "Loan Type name is required")
    private String loanTypeName;

    public LoanType() {
    }

    public LoanType(int loanTypeId, String loanTypeName) {
        this.loanTypeId = loanTypeId;
        this.loanTypeName = loanTypeName;
    }

    public int getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }
}
