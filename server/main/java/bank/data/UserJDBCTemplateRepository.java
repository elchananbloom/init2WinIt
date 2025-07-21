package bank.data;

import bank.data.mappers.UserMapper;
import bank.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;

@Repository
public class UserJDBCTemplateRepository implements UserRepository{
    private JdbcTemplate jdbcTemplate;

    public UserJDBCTemplateRepository(JdbcTemplate template){
        this.jdbcTemplate = template;
    }

    @Override
    public User findById(int userId) {
        String sql = "SELECT " +
                "user_id, " +
                "first_name, " +
                "last_name, " +
                "address, " +
                "phone_number, " +
                "email, " +
                "password_hash, " +
                "created_at, " +
                "role " +
                "FROM user " +
                "WHERE user_id = ?";

        User res = jdbcTemplate.query(sql, new UserMapper(), userId).stream().findFirst().orElse(null);
        return res;
    }

    @Override
    public User findByEmail(String userEmail) {
        String sql = "SELECT " +
                "user_id, " +
                "first_name, " +
                "last_name, " +
                "address, " +
                "phone_number, " +
                "email, " +
                "password_hash, " +
                "created_at, " +
                "role " +
                "FROM user " +
                "WHERE email = ?;";

        User res = jdbcTemplate.query(sql, new UserMapper(), userEmail).stream().findFirst().orElse(null);
        return res;
    }

    @Override
    public User addUser(User user) {
        String sql = "Insert into user (first_name, last_name, address, phone_number, email, password_hash, created_at, role) values (?,?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPasswordHash());
            ps.setDate(7, Date.valueOf(user.getCreatedAt()));
            ps.setString(8, user.getRole().getValue());
            return ps;
        }, keyHolder);

        if(rowsAffected <= 0){
            return null;
        }
        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }
}
