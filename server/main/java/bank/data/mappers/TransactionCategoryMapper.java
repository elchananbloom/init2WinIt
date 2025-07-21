package bank.data.mappers;

import bank.models.TransactionCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionCategoryMapper implements RowMapper<TransactionCategory> {

    @Override
    public TransactionCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setTransactionCategoryId(resultSet.getInt("transaction_category_id"));
        transactionCategory.setTransactionCategoryName(resultSet.getString("name"));
        return transactionCategory;
    }
}
