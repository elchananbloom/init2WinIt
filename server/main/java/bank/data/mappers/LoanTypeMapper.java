package bank.data.mappers;

import bank.models.LoanType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanTypeMapper implements RowMapper<LoanType> {

    @Override
    public LoanType mapRow(ResultSet resultSet, int i) throws SQLException {
        LoanType loanType = new LoanType();
        loanType.setLoanTypeId(resultSet.getInt("loan_type_id"));
        loanType.setLoanTypeName(resultSet.getString("name"));
        return loanType;
    }
}
