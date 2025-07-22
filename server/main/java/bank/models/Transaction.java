package bank.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NotNull(message= "Transaction must not be null")
public class Transaction {

    private int transactionId;

    @Min(value=0 ,message="Amount must not be less than 0")
    private BigDecimal amount;

    @NotBlank(message="transaction type must not be blank")
    private String type;

    private LocalDate transactionDate;

    private String description;

    @NotNull(message="transactionCategory must not be null")
    private TransactionCategory transactionCategory;


    private int accountId;

    private int loanId;

    public Transaction(){}

    public Transaction(int transactionId,
                       BigDecimal amount,
                       String type,
                       LocalDate transactionDate,
                       String description,
                       TransactionCategory transactionCategory,
                       int accountId,
                       int loanId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
        this.description = description;
        this.transactionCategory = transactionCategory;
        this.accountId = accountId;
        this.loanId = loanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId && accountId == that.accountId && loanId == that.loanId && Objects.equals(amount, that.amount) && Objects.equals(type, that.type) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(description, that.description) && Objects.equals(transactionCategory, that.transactionCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, type, transactionDate, description, transactionCategory, accountId, loanId);
    }
}
