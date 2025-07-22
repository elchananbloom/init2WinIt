package bank.domain;

import bank.data.TransactionRepository;
import bank.data.UserRepository;
import bank.models.Transaction;
import bank.models.TransactionCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TransactionServiceTest {

    @Autowired
    TransactionService service;

    @MockBean
    TransactionRepository repository;

    @Test
    void shouldFindByAccountId() {
        Transaction expected = new Transaction();
        expected.setTransactionId(1);
        expected.setTransactionCategory(new TransactionCategory(3,"Car"));
        expected.setAmount(BigDecimal.valueOf(200.00).setScale(2));
        expected.setType("WITHDRAWAL");
        expected.setAccountId(1);
        expected.setTransactionDate(LocalDate.parse("2025-01-02"));
        expected.setLoanId(0);
        expected.setDescription("emergency car fix");
        when(repository.findByAccountId(1)).thenReturn(List.of(expected));

        List<Transaction> res = service.findByAccountId(1);

        assertEquals(1, res.size());
        assertEquals(expected, res.get(0));


    }

    @Test
    void shouldAddTransaction() {
        Transaction added = new Transaction();
        added.setTransactionCategory(new TransactionCategory(2,"Check"));
        added.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        added.setType("Deposit");
        added.setAccountId(2);
        added.setTransactionDate(LocalDate.parse("2025-01-23"));
        added.setLoanId(1);
        added.setDescription(null);

        Transaction expected = new Transaction();
        expected.setTransactionId(4);
        expected.setTransactionCategory(new TransactionCategory(2,"Check"));
        expected.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        expected.setType("Deposit");
        expected.setAccountId(2);
        expected.setTransactionDate(LocalDate.parse("2025-01-23"));
        expected.setLoanId(1);
        expected.setDescription(null);


        when(repository.add(any(Transaction.class))).thenReturn(expected);

        Result<Transaction> res = service.addTransaction(added);

        assertTrue(res.isSuccess());
        assertEquals(expected, res.getPayload());

    }

    @Test
    void shouldFindByLoanId() {
        Transaction expected = new Transaction();
        expected.setTransactionId(2);
        expected.setTransactionCategory(new TransactionCategory(1,"Groceries"));
        expected.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        expected.setType("LOAN");
        expected.setAccountId(2);
        expected.setTransactionDate(LocalDate.parse("2025-01-23"));
        expected.setLoanId(1);
        expected.setDescription(null);

        when(repository.findByLoanId(1)).thenReturn(List.of(expected));


        List<Transaction> res = service.findByLoanId(1);

        assertEquals(expected, res.get(0));

    }


    @Test
    void shouldNotFindNonExistingAccountId() {
        Transaction expected = new Transaction();
        expected.setTransactionId(1);
        expected.setTransactionCategory(new TransactionCategory(3,"Car"));
        expected.setAmount(BigDecimal.valueOf(200.00).setScale(2));
        expected.setType("WITHDRAWAL");
        expected.setAccountId(1);
        expected.setTransactionDate(LocalDate.parse("2025-01-02"));
        expected.setLoanId(0);
        expected.setDescription("emergency car fix");
        when(repository.findByAccountId(1)).thenReturn(List.of(expected));

        List<Transaction> res = service.findByAccountId(99);

        assertEquals(0, res.size());



    }

    @Test
    void shouldNotAddInvalidTransaction() {
        Transaction added = new Transaction();
        added.setTransactionCategory(new TransactionCategory(2,"Check"));
        added.setAmount(BigDecimal.valueOf(-120.00).setScale(2));
        added.setType("Deposit");
        added.setAccountId(2);
        added.setTransactionDate(LocalDate.parse("2025-01-23"));
        added.setLoanId(1);
        added.setDescription(null);

        Transaction expected = new Transaction();
        expected.setTransactionId(4);
        expected.setTransactionCategory(new TransactionCategory(2,"Check"));
        expected.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        expected.setType("Deposit");
        expected.setAccountId(2);
        expected.setTransactionDate(LocalDate.parse("2025-01-23"));
        expected.setLoanId(1);
        expected.setDescription(null);


        when(repository.add(any(Transaction.class))).thenReturn(expected);

        Result<Transaction> res = service.addTransaction(added);

        assertFalse(res.isSuccess());


    }

    @Test
    void shouldNotFindNonExistingLoanId() {
        Transaction expected = new Transaction();
        expected.setTransactionId(2);
        expected.setTransactionCategory(new TransactionCategory(1,"Groceries"));
        expected.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        expected.setType("LOAN");
        expected.setAccountId(2);
        expected.setTransactionDate(LocalDate.parse("2025-01-23"));
        expected.setLoanId(1);
        expected.setDescription(null);

        when(repository.findByLoanId(1)).thenReturn(List.of(expected));

        List<Transaction> res = service.findByLoanId(99);

        assertEquals(0, res.size());

    }


}