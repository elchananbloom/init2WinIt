package bank.data;

import bank.models.TransactionCategory;

import java.util.List;

public interface TransactionCategoryRepository {

    public List<TransactionCategory> findAll();

    public TransactionCategory add(TransactionCategory transactionCategory);

    public boolean update(TransactionCategory transactionCategory);
}
