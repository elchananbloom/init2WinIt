package bank.domain;

import bank.data.TransactionCategoryRepository;
import bank.models.TransactionCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TransactionCategoryServiceTest {

    @Autowired
    TransactionCategoryService service;

    @MockBean
    TransactionCategoryRepository repository;

    @Test
    void shouldFindAll() {
        List<TransactionCategory> expected = List.of(
                new TransactionCategory(1, "Grocery"),
                new TransactionCategory(2, "Culture")
        );
        when(repository.findAll()).thenReturn(expected);
        List<TransactionCategory> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        TransactionCategory expected = new TransactionCategory(1, "Culture");
        TransactionCategory arg = new TransactionCategory(0, "Culture");

        when(repository.add(arg)).thenReturn(expected);
        Result<TransactionCategory> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());

    }

    @Test
    void shouldNotAddNullName() {
        TransactionCategory transactionCategory = new TransactionCategory(0, null);
        Result<TransactionCategory> result = service.add(transactionCategory);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        TransactionCategory expected = new TransactionCategory(1, "Culture");
        expected.setTransactionCategoryName("Clothes");

        when(repository.update(expected)).thenReturn(true);
        Result<TransactionCategory> result = service.update(expected);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateNullName() {
        TransactionCategory transactionCategory = new TransactionCategory(1, null);
        Result<TransactionCategory> result = service.update(transactionCategory);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateNotFound() {
        TransactionCategory transactionCategory = new TransactionCategory(5, "Culture");
        when(repository.update(transactionCategory)).thenReturn(false);
        Result<TransactionCategory> result = service.update(transactionCategory);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }


}