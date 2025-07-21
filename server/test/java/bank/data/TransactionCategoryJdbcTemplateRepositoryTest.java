package bank.data;

import bank.models.TransactionCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TransactionCategoryJdbcTemplateRepositoryTest {

    @Autowired
    TransactionCategoryJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<TransactionCategory> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 3);
    }

    @Test
    void shouldAdd() {
        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setTransactionCategoryName("Clothes");

        TransactionCategory actual = repository.add(transactionCategory);
        assertNotNull(actual);
        assertEquals(5, actual.getTransactionCategoryId());
    }

    @Test
    void shouldUpdate() {
        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setTransactionCategoryName("Clothes");
        transactionCategory.setTransactionCategoryId(1);

        assertTrue(repository.update(transactionCategory));

    }

    @Test
    void shouldNotUpdateNotFound() {
        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setTransactionCategoryName("Clothes");
        transactionCategory.setTransactionCategoryId(7);

        assertFalse(repository.update(transactionCategory));

    }

}