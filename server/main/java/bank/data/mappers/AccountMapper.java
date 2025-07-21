package bank.data.mappers;

import bank.models.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setAccountId(resultSet.getInt("account_id"));
        account.setBalance(resultSet.getBigDecimal("balance"));
        account.setAccountNumber(resultSet.getString("account_name"));
        account.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
        account.setUserId(resultSet.getInt("user_id"));

        return null;
    }
}
