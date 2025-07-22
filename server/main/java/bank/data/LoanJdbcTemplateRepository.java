package bank.data;

import bank.data.mappers.LoanMapper;
import bank.models.Loan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LoanJdbcTemplateRepository implements LoanRepository{

    private final JdbcTemplate jdbcTemplate;

    public LoanJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Loan> findAll() {
        final String sql = "select loan_id, date_approved, flat_interest, initial_amount, date_due, `status`, created_at, balance, user_id, lt.loan_type_id, lt.`name` " +
                "from loan l " +
                "inner join loan_type lt " +
                "on l.loan_type_id = lt.loan_type_id " +
                "order by created_at desc limit 1000;";
        return jdbcTemplate.query(sql, new LoanMapper());
    }

    @Override
    public Loan findById(int loanId) {
        final String sql = "select loan_id, date_approved, flat_interest, initial_amount, date_due, `status`, created_at, balance, user_id, lt.loan_type_id, lt.`name` " +
                "from loan l " +
                "inner join loan_type lt " +
                "on l.loan_type_id = lt.loan_type_id " +
                "where loan_id = ?;";
        return jdbcTemplate.query(sql, new LoanMapper(), loanId).stream().findFirst().orElse(null);
    }

    @Override
    public List<Loan> findByUserId(int userId) {
        final String sql = "select loan_id, date_approved, flat_interest, initial_amount, date_due, `status`, created_at, balance, user_id, lt.loan_type_id, lt.`name` " +
                "from loan l " +
                "inner join loan_type lt " +
                "on l.loan_type_id = lt.loan_type_id " +
                "where user_id = ? " +
                "order by created_at limit 1000;";
        return jdbcTemplate.query(sql, new LoanMapper(), userId);
    }

    @Override
    public Loan add(Loan loan) {
        final String sql = "insert into loan (date_approved, flat_interest, initial_amount, date_due, `status`, balance, user_id, loan_type_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, loan.getDateApproved().toString());
            ps.setDouble(2, loan.getFlatInterest());
            ps.setBigDecimal(3, loan.getInitialAmount());
            ps.setString(4, loan.getDateDue().toString());
            ps.setString(5, loan.getStatus().getValue());
            ps.setBigDecimal(6, loan.getBalance());
            ps.setInt(7, loan.getUserId());
            ps.setInt(8, loan.getLoanType().getLoanTypeId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        loan.setLoanId(keyHolder.getKey().intValue());
        return loan;
    }

    @Override
    public boolean update(Loan loan) {
        final String sql = "update loan set " +
                "date_approved = ?, " +
                "`status` = ?, " +
                "balance = ? " +
                "where loan_id = ?";

        return jdbcTemplate.update(sql,
                loan.getDateApproved(),
                loan.getStatus().getValue(),
                loan.getBalance(),
                loan.getLoanId()
                ) > 0;
    }

    @Override
    public boolean delete(int loanId) {
        return jdbcTemplate.update("delete from loan where loan_id = ?;", loanId) > 0;
    }
}
