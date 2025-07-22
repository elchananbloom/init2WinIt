package bank.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDate;

@NotNull(message = "Loan cannot be null")
public class Loan {

    private int loanId;

    private LocalDate dateApproved;

    @Min(value = 0, message = "Flat interest is required")
    private double flatInterest;

    @PositiveOrZero(message = "Initial amount is required")
    @NotNull(message = "Initial amount is required")
    private BigDecimal initialAmount;

    @NotNull(message = "Due date is required")
    private LocalDate dateDue;

    @NotNull(message = "Statue is required")
    private LoanStatus status;

    private LocalDate createdAt;

    @PositiveOrZero(message = "Balance is required")
    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @Min(value = 1, message = "User Id is required")
    private int userId;

    private LoanType loanType;

    public Loan() {
    }

    public Loan(int loanId, LocalDate dateApproved, double flatInterest, BigDecimal initialAmount, LocalDate dateDue, LoanStatus status, LocalDate createdAt, BigDecimal balance, int userId, LoanType loanType) {
        this.loanId = loanId;
        this.dateApproved = dateApproved;
        this.flatInterest = flatInterest;
        this.initialAmount = initialAmount;
        this.dateDue = dateDue;
        this.status = status;
        this.createdAt = createdAt;
        this.balance = balance;
        this.userId = userId;
        this.loanType = loanType;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public LocalDate getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(LocalDate dateApproved) {
        this.dateApproved = dateApproved;
    }

    public double getFlatInterest() {
        return flatInterest;
    }

    public void setFlatInterest(double flatInterest) {
        this.flatInterest = flatInterest;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
