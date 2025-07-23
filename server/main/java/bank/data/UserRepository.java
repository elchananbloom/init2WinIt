package bank.data;

import java.util.List;
import bank.models.User;

public interface UserRepository {
    User findById(int userId);
    User findByEmail(String userEmail);
    User addUser(User user);
    boolean update(User user);
}
