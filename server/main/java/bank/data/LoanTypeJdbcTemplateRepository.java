package bank.data;

import bank.data.mappers.LoanTypeMapper;
import bank.models.LoanType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanTypeJdbcTemplateRepository implements LoanTypeRepository{

    private final JdbcTemplate jdbcTemplate;

    public LoanTypeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LoanType> findAll() {
        final String sql = "select loan_type_id, `name` " +
                "from loan_type;";
        return jdbcTemplate.query(sql, new LoanTypeMapper());

    }
}
