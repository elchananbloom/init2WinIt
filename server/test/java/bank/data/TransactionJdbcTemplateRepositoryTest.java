package bank.data;

import bank.models.Transaction;
import bank.models.TransactionCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TransactionJdbcTemplateRepositoryTest {

    @Autowired
    TransactionJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;


    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findByAccountId() {
        Transaction expected = new Transaction();
        expected.setTransactionId(1);
        expected.setTransactionCategory(new TransactionCategory(3,"Car"));
        expected.setAmount(BigDecimal.valueOf(200.00).setScale(2));
        expected.setType("WITHDRAWAL");
        expected.setAccountId(1);
        expected.setTransactionDate(LocalDate.parse("2025-01-02"));
        expected.setLoanId(0);
        expected.setDescription("emergency car fix");

        Transaction actual = repository.findByAccountId(1).stream().
                findFirst().orElse(null);

        assertEquals(expected, actual);
    }

    @Test
    void findByLoanId() {
        Transaction expected = new Transaction();
        expected.setTransactionId(2);
        expected.setTransactionCategory(new TransactionCategory(1,"Groceries"));
        expected.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        expected.setType("LOAN");
        expected.setAccountId(2);
        expected.setTransactionDate(LocalDate.parse("2025-01-23"));
        expected.setLoanId(1);
        expected.setDescription(null);

        Transaction actual = repository.findByLoanId(1).stream().findFirst().orElse(null);

        assertEquals(expected, actual);




    }

    @Test
    void add() {
        Transaction added = new Transaction();
        added.setTransactionCategory(new TransactionCategory(2,"Check"));
        added.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        added.setType("Deposit");
        added.setAccountId(2);
        added.setTransactionDate(LocalDate.parse("2025-01-23"));
        added.setLoanId(1);
        added.setDescription(null);

        Transaction actual = repository.add(added);

        added.setTransactionId(actual.getTransactionId());

        assertEquals(added, actual);


    }
}