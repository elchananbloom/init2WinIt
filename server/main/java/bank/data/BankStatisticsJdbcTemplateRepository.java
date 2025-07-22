package bank.data;

import bank.data.mappers.BankStatistcsMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class BankStatisticsJdbcTemplateRepository implements BankStatisticsRepository{

    private final JdbcTemplate jdbcTemplate;

    public BankStatisticsJdbcTemplateRepository(JdbcTemplate template){
        jdbcTemplate = template;
    }
    @Override
    public List<Map<String,BigDecimal>> getTotalTransactionsPerTransactionType() {

        String sql = "select avg(transaction_amount) `value`, transaction_type label " +
                "                from user_transactions " +
                "                where transaction_year = year(now()) " +
                "                group by transaction_type;";

        List<Map<String,BigDecimal>> res = jdbcTemplate.query(sql,new BankStatistcsMapper());
        return res;
    }


}
