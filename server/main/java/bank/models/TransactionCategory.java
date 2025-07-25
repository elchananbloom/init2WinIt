package bank.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NotNull(message = "Transaction Category cannot be null")
public class TransactionCategory {

    private int transactionCategoryId;

    @NotBlank(message = "Transaction Category is required")
    private String transactionCategoryName;

    public TransactionCategory() {
    }

    public TransactionCategory(int transactionCategoryId, String transactionCategoryName) {
        this.transactionCategoryId = transactionCategoryId;
        this.transactionCategoryName = transactionCategoryName;
    }

    public int getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public void setTransactionCategoryId(int transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }

    public String getTransactionCategoryName() {
        return transactionCategoryName;
    }

    public void setTransactionCategoryName(String transactionCategoryName) {
        this.transactionCategoryName = transactionCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TransactionCategory)) return false;
        TransactionCategory that = (TransactionCategory) o;
        return transactionCategoryId == that.transactionCategoryId && Objects.equals(transactionCategoryName, that.transactionCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionCategoryId, transactionCategoryName);
    }
}
