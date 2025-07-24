package bank.data;

import bank.data.mappers.UserStatisticsMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserStatisticsJdbcRepository implements UserStatisticsRepository{
    JdbcTemplate jdbcTemplate;

    public UserStatisticsJdbcRepository(JdbcTemplate template){
        this.jdbcTemplate = template;
    }
    @Override
    public Map<String, BigDecimal> getAverageAmountByCategory(int userId) {
        String sql = "select avg(transaction_amount) `value`, transaction_category label " +
                "from user_transactions " +
                "where user_id = ? and transaction_year = year(now()) " +
                "group by transaction_category;";


        List<Map<String, BigDecimal>> res = jdbcTemplate.query(sql,new UserStatisticsMapper(), userId);

        Map<String, BigDecimal> concatResult = new HashMap<>();

        res.stream().forEach(concatResult::putAll);


        return concatResult;
    }

    @Override
    public Map<String, BigDecimal> getAmountSpentByCategoryForAccount(int accountId) {
        String sql = "select sum(transaction_amount) `value`, transaction_category label " +
                "from user_transactions " +
                "where account_id = ? and transaction_year = year(now()) " +
                "group by transaction_category;";


        List<Map<String, BigDecimal>> res = jdbcTemplate.query(sql,new UserStatisticsMapper(), accountId);

        Map<String, BigDecimal> concatResult = new HashMap<>();

        res.stream().forEach(concatResult::putAll);


        return concatResult;
    }

    @Override
    public BigDecimal getTotalAccountsBalance(int userId) {

        String sql = "select sum(acc.balance) `value` " +
                "from `account` acc " +
                "where acc.user_id = ?;";

        return jdbcTemplate.queryForObject(sql , BigDecimal.class, userId);

    }

    @Override
    public Map<String, BigDecimal> getLoanBalanceOverTime(int loanId) {
        String sql = "select loan_balance `value`, date_format(transaction_date, '%Y-%m-%d') label " +
                "from user_transactions " +
                "where loan_id = ? " +
                "order by transaction_date asc;";


        List<Map<String, BigDecimal>> res = jdbcTemplate.query(sql, new UserStatisticsMapper(), loanId);

        Map<String, BigDecimal> concatResult = new HashMap<>();

        res.stream().forEach(concatResult::putAll);

        return concatResult;

    }
}
