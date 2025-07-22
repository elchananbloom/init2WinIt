package bank.data.mappers;

import bank.models.Transaction;
import bank.models.TransactionCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(resultSet.getInt("transaction_id"));
        transaction.setAmount(resultSet.getBigDecimal("amount"));
        transaction.setTransactionDate(resultSet.getDate("transaction_date").toLocalDate());
        transaction.setDescription(resultSet.getString("description"));
        transaction.setAccountId(resultSet.getInt("account_id"));
        transaction.setType(resultSet.getString("type"));
        transaction.setLoanId(resultSet.getInt("loan_id"));
        TransactionCategoryMapper transactionCategoryMapper = new TransactionCategoryMapper();
        transaction.setTransactionCategory(transactionCategoryMapper.mapRow(resultSet, i));
        return transaction;
    }
}
