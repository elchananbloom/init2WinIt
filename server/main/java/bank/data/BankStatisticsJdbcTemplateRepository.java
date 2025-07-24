package bank.data;

import bank.data.mappers.BankStatistcsMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankStatisticsJdbcTemplateRepository implements BankStatisticsRepository{

    private final JdbcTemplate jdbcTemplate;

    public BankStatisticsJdbcTemplateRepository(JdbcTemplate template){
        jdbcTemplate = template;
    }
    @Override
    public Map<String,BigDecimal> getTotalTransactionsPerTransactionType() {

        String sql = "select sum(transaction_amount) `value`, " +
                " transaction_type label " +
                "from user_transactions " +
                " where transaction_year = year(now()) and (transaction_type = 'DEPOSIT' or transaction_type = 'WITHDRAWAL') " +
                " group by transaction_type " +
                "        union " +
                "        select avg(initial_amount) `value`, 'LOAN' label " +
                " from loan " +
                " where year(created_at) = year(now()) and `status` = 'APPROVED';";

        List<Map<String,BigDecimal>> res = jdbcTemplate.query(sql,new BankStatistcsMapper());

        Map<String, BigDecimal> concatResult = new HashMap<>();

        res.stream().forEach(concatResult::putAll);
        return concatResult;
    }

    @Override
    public Map<String, BigDecimal> getTotalWithdrawsPerQuarter() {

        String sql = "select sum(amount) `value`, concat('Q',`quarter`) label " +
                "from bank_transactions " +
                "where `type` = 'WITHDRAWAL' " +
                "group by `type`, `quarter`;";

        List<Map<String,BigDecimal>> res = jdbcTemplate.query(sql,new BankStatistcsMapper());

        Map<String, BigDecimal> concatResult = new HashMap<>();

        res.stream().forEach(concatResult::putAll);
        return concatResult;
    }

    @Override
    public Map<String, BigDecimal> getTotalDepositsPerQuarter() {

        String sql = "select sum(amount) `value`, concat('Q',`quarter`) label " +
                "from bank_transactions " +
                "where `type` = 'DEPOSIT' " +
                "group by `type`, `quarter`;";

        List<Map<String,BigDecimal>> res = jdbcTemplate.query(sql,new BankStatistcsMapper());

        Map<String, BigDecimal> concatResult = new HashMap<>();

        res.stream().forEach(concatResult::putAll);
        return concatResult;
    }

    @Override
    public Map<String, BigDecimal> getTotalLoansPerQuarter() {

        String sql = "select sum(initial_amount) `value`, concat('Q',quarter(created_at)) label " +
                "from loan " +
                "where `status` = 'APPROVED' and year(created_at) = year(now()) " +
                "group by label; ";

        List<Map<String,BigDecimal>> res = jdbcTemplate.query(sql,new BankStatistcsMapper());

        Map<String, BigDecimal> concatResult = new HashMap<>();

        res.stream().forEach(concatResult::putAll);
        return concatResult;
    }




}
