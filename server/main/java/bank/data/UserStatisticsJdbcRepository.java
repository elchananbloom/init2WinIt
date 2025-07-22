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
    public Map<String, BigDecimal> getTotalAccountsBalance(int userId) {

        String sql = "select sum(acc.balance) `value`, \"Total Account Balance\" label " +
                "from `account` acc " +
                "where acc.user_id = ?;";

        Map<String, BigDecimal> res = (Map<String, BigDecimal>) jdbcTemplate.query(sql,new UserStatisticsMapper(), userId).stream().findFirst().orElse(null);
        return res;
    }
}
