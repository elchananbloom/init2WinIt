package bank.data;

import bank.data.mappers.AccountMapper;
import bank.models.Account;
import bank.models.Loan;
import bank.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AccountJdbcTemplateRepository implements AccountRepository{
    private JdbcTemplate jdbcTemplate;

    public AccountJdbcTemplateRepository(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public List<Account> findAll() {
        final String sql = "select account_id, `type`, balance, account_number, created_at, user_id " +
                "from `account`;";
        return jdbcTemplate.query(sql, new AccountMapper());
    }

    @Override
    public Account add (Account account) {
        final String sql = "insert into `account` (`type`, balance, account_number, user_id) " +
                "values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getAccountType().getValue());
            ps.setBigDecimal(2, account.getBalance());
            ps.setString(3, account.getAccountNumber());
            ps.setInt(4, account.getUserId());
            return ps;

        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        account.setAccountId(keyHolder.getKey().intValue());
        return account;
    }

    @Override
    public List<Account> findByUserId(int userId) {
        final String sql = "select account_id, `type`, balance, account_number, created_at, user_id " +
                " from `account` " +
                "where user_id = ? " +
                "order by `type`;";
        return jdbcTemplate.query(sql, new AccountMapper(), userId);
    }

    @Override
    public Account findById(int accountId) {
        final String sql = "select account_id, `type`, balance, account_number, created_at, user_id " +
                " from `account` " +
                "where account_id = ? " +
                "order by `type`;";
        return jdbcTemplate.query(sql, new AccountMapper(), accountId).stream()
                .findFirst()
                .orElse(null);
    }


    @Override
    public boolean update(Account account) {
        final String sql = "update account set " +
                "balance = ? " +
                "where account_id = ?";

        return jdbcTemplate.update(sql, account.getBalance(), account.getAccountId()) > 0;
    }

}
