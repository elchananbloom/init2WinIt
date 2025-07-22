package bank.data;

import bank.data.mappers.TransactionCategoryMapper;
import bank.models.TransactionCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TransactionCategoryJdbcTemplateRepository implements TransactionCategoryRepository {
    private final JdbcTemplate jdbcTemplate;


    public TransactionCategoryJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TransactionCategory> findAll() {
        final String sql = "select transaction_category_id, `name` " +
                "from transaction_category;";
        return jdbcTemplate.query(sql, new TransactionCategoryMapper());
    }

    @Override
    public TransactionCategory add(TransactionCategory transactionCategory) {
        final String sql = "insert into transaction_category (`name`) " +
                "values (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, transactionCategory.getTransactionCategoryName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        transactionCategory.setTransactionCategoryId(keyHolder.getKey().intValue());
        return transactionCategory;
    }

    @Override
    public boolean update(TransactionCategory transactionCategory) {
        final String sql = "update transaction_category set " +
                "`name` = ? " +
                "where transaction_category_id = ?;";

        return jdbcTemplate.update(sql,
                transactionCategory.getTransactionCategoryName(),
                transactionCategory.getTransactionCategoryId()) > 0;
    }

    @Override
    public boolean delete(int transactionCategoryId) {
        return jdbcTemplate.update("delete from transaction_category where transaction_category_id = ?;", transactionCategoryId) > 0;
    }

    @Override
    public int getUsageCount(int transactionCategoryId) {
        return jdbcTemplate.queryForObject(
                "select count(*) from transaction where transaction_category_id = ?;", Integer.class, transactionCategoryId);
    }

}
