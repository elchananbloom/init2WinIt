package bank.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {
    @NotNull(message="accountId cannot be null")
    private int accountId;
    @NotBlank(message="account number cannot be null")
    private String accountNumber;
    @NotBlank(message="you must include the type")
    private AccountType accountType;
    @PositiveOrZero(message="You cannot have a negative balance")
    private BigDecimal balance;
    @NotNull(message="created at date cannot be null")
    @Past(message="date must be in the past")
    private LocalDate createdAt;
    private int userId;

    public Account(){};

    public Account(int accountId, AccountType accountType, BigDecimal balance, String accountNumber, LocalDate createdAt, int userId) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
