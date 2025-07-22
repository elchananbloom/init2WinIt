package bank.data.mappers;

import bank.models.Loan;
import bank.models.LoanStatus;
import bank.models.TransactionCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanMapper implements RowMapper<Loan> {

    @Override
    public Loan mapRow(ResultSet resultSet, int i) throws SQLException {
        Loan loan = new Loan();
        loan.setLoanId(resultSet.getInt("loan_id"));
        if (resultSet.getDate("date_approved") != null) {
            loan.setDateApproved(resultSet.getDate("date_approved").toLocalDate());
        }
        loan.setFlatInterest(resultSet.getDouble("flat_interest"));
        loan.setInitialAmount(resultSet.getBigDecimal("initial_amount"));
        if (resultSet.getDate("date_due") != null) {
            loan.setDateDue(resultSet.getDate("date_due").toLocalDate());
        }
        loan.setStatus(LoanStatus.findByValue(resultSet.getString("status")));
        if (resultSet.getDate("created_at") != null) {
            loan.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
        }
        loan.setBalance(resultSet.getBigDecimal("balance"));
        loan.setUserId(resultSet.getInt("user_id"));

        LoanTypeMapper loanTypeMapper = new LoanTypeMapper();
        loan.setLoanType(loanTypeMapper.mapRow(resultSet, i));

        return loan;
    }
}
