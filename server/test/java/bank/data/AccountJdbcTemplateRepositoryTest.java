package bank.data;

import bank.models.Account;
import bank.models.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AccountJdbcTemplateRepositoryTest {

    @Autowired
    AccountJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Account> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 3);
    }

    @Test
    void shouldAdd() {
        Account account = new Account(4, AccountType.SAVINGS, new BigDecimal("2000.00"), "123456", LocalDate.of(2025, 1, 1), 1);

        Account actual = repository.add(account);
        assertNotNull(actual);
        assertEquals(4, actual.getAccountId());
    }

    @Test
    void shouldFindByUserId(){
        List<Account> accounts = repository.findByUserId(1);
        assertEquals(AccountType.SAVINGS, accounts.get(1).getAccountType());
        assertEquals(1, accounts.size());
    }

    @Test
    void shouldFindByAccountId(){
        Account expected = repository.findById( 2);
        assertEquals(AccountType.CHECKING, expected.getAccountType());
    }
}