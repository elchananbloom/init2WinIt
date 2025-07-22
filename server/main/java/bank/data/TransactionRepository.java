package bank.data;

import bank.models.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findByAccountId(int accountId);
    List<Transaction> findByLoanId(int loanId);
    Transaction add(Transaction transaction);
}
