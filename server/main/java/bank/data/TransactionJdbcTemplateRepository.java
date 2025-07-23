package bank.data;

import bank.data.mappers.TransactionMapper;
import bank.models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class TransactionJdbcTemplateRepository implements TransactionRepository{

    private JdbcTemplate jdbcTemplate;

    TransactionJdbcTemplateRepository (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transaction> findByAccountId(int accountId) {
        String sql= "select" +
                " transaction_id, " +
                "amount, " +
                "`type`, " +
                "transaction_date, " +
                "`description`, " +
                "t.transaction_category_id, " +
                "account_id, " +
                "loan_id ," +
                "tc.`name` as `name` "+
                "from `transaction` t inner join transaction_category tc " +
                "on t.transaction_category_id = tc.transaction_category_id " +
                "where account_id = ? " +
                "order by transaction_id desc;";

        return jdbcTemplate.query(sql, new TransactionMapper(), accountId);
    }

    @Override
    public List<Transaction> findByLoanId(int loanId) {
        String sql= "select" +
                " transaction_id, " +
                "amount, " +
                "`type`, " +
                "transaction_date, " +
                "`description`, " +
                "t.transaction_category_id, " +
                "account_id, " +
                "loan_id ," +
                "tc.`name` as `name` "+
                "from `transaction` t inner join transaction_category tc " +
                "on t.transaction_category_id = tc.transaction_category_id " +
                "where loan_id = ?;";
        return jdbcTemplate.query(sql, new TransactionMapper(), loanId);
    }

    @Override
    public Transaction add(Transaction transaction) {
        String sql = "insert into `transaction`(amount, `type`, `description`, transaction_category_id, account_id, loan_id)" +
                " values(?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, transaction.getAmount());
            ps.setString(2, transaction.getType());
            ps.setString(3, transaction.getDescription());
            ps.setInt(4, transaction.getTransactionCategory().getTransactionCategoryId());
            if( transaction.getAccountId() > 0){
                ps.setInt(5, transaction.getAccountId());
            }else{
                ps.setNull(5, Types.INTEGER);
            }

            if( transaction.getLoanId() > 0){
                ps.setInt(6, transaction.getLoanId());
            }else{
                ps.setNull(6, Types.INTEGER);
            }

            return ps;
        }, keyHolder);

        if(rowsAffected == 0){
            return null;
        }
        transaction.setTransactionId(keyHolder.getKey().intValue());
        return transaction;
    }
}
