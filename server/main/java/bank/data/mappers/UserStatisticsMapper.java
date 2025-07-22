package bank.data.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserStatisticsMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Map<String, BigDecimal> stats = new HashMap<>();

        stats.put(resultSet.getString("label"), resultSet.getBigDecimal("value"));

        return stats;
    }
}
