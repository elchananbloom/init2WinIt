package bank.data.mappers;

import bank.models.Role;
import bank.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setAddress(resultSet.getString("address"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setEmail(resultSet.getString("email"));
        user.setPasswordHash(resultSet.getString("password_hash"));
        user.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
        user.setRole(Role.findByValue(resultSet.getString("role")));

        return user;
    }
}
