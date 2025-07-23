package bank.domain;

import bank.data.AccountRepository;
import bank.models.Account;
import bank.models.AccountType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AccountServiceTest {
    @Autowired
    AccountService service;

    @MockBean
    AccountRepository repository;

    @Test
    void shouldFindAll(){
        List<Account> expected = List.of(
                new Account(4, AccountType.SAVINGS, new BigDecimal("2000.00"), "123456", LocalDate.of(2025, 1, 1), 1),
                new Account(5, AccountType.CHECKING, new BigDecimal("1000.00"), "1234321", LocalDate.of(2024, 12, 12), 2)
        );

        when(repository.findAll()).thenReturn(expected);
        List<Account> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByUserId(){
        List<Account> expected = List.of(
                new Account(4, AccountType.SAVINGS, new BigDecimal("2000.00"), "123456", LocalDate.of(2025, 1, 1), 2),
                new Account(5, AccountType.CHECKING, new BigDecimal("1000.00"), "1234321", LocalDate.of(2024, 12, 12), 2)
        );

        when(repository.findByUserId(2)).thenReturn(expected);
        List<Account> actual = service.findByUserId(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById(){
        Account expected = new Account(4, AccountType.SAVINGS, new BigDecimal("2000.00"), "123456", LocalDate.of(2025, 1, 1), 2);
        when(repository.findById(4)).thenReturn(expected);
        Account actual = service.findById(4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Account expected = new Account(1, AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);
        Account arg = new Account(0,AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);

        when(repository.add(arg)).thenReturn(expected);
        Result<Account> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenBalanceNull(){
        Account account = new Account(0, AccountType.SAVINGS, null, "123451", LocalDate.of(2025, 1, 1),1);
        Result<Account> result = service.add(account);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotAddWhenBalanceNegative(){
        Account account = new Account(0, AccountType.SAVINGS, new BigDecimal("-2000.00"), "123451", LocalDate.of(2025, 1, 1),1);
        Result<Account> result = service.add(account);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        Account account = new Account(1, AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);
        when(repository.update(account)).thenReturn(true);
        Result<Account> result = service.update(account);
        assertEquals(ResultType.SUCCESS, result.getType());
    }
    @Test
    void shouldNotUpdateWhenBalanceNull(){
        Account account = new Account(1, AccountType.SAVINGS, null, "123451", LocalDate.of(2025, 1, 1),1);
        Result<Account> result = service.update(account);
        assertEquals(ResultType.INVALID, result.getType());
    }
    @Test
    void shouldNotUpdateWhenBalanceNegative(){
        Account account = new Account(1, AccountType.SAVINGS, new BigDecimal("-2000.00"), "123451", LocalDate.of(2025, 1, 1),1);
        Result<Account> result = service.update(account);
        assertEquals(ResultType.INVALID, result.getType());
    }



}