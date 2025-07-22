package bank.data;

import bank.models.Account;
import bank.models.User;

import java.util.List;

public interface AccountRepository {
    Account add(Account account);
    List<Account> findByUserId(int userId);
    List<Account> findAll();
    Account findById(int accountId);
}
