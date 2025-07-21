package bank.domain;

import bank.data.LoanTypeRepository;
import bank.models.LoanType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LoanTypeServiceTest {

    @Autowired
    LoanTypeService service;

    @MockBean
    LoanTypeRepository repository;

    @Test
    void shouldFindAll() {
        List<LoanType> expected = List.of(
                new LoanType(1, "Car"),
                new LoanType(2, "Mortgage")
        );
        when(repository.findAll()).thenReturn(expected);
        List<LoanType> actual = service.findAll();
        assertEquals(expected, actual);
    }
}