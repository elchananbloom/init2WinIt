package bank.data;

import bank.models.Loan;
import bank.models.LoanStatus;
import bank.models.LoanType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class LoanJdbcTemplateRepositoryTest {

    @Autowired
    LoanJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Loan> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 2);
    }

    @Test
    void shouldFindById() {
        Loan loan = repository.findById(1);
        assertNotNull(loan);
        assertEquals(LoanStatus.APPROVED, loan.getStatus());
    }

    @Test
    void shouldFindByUserId() {
        Loan loan = repository.findById(1);
        assertNotNull(loan);
        assertEquals(LoanStatus.APPROVED, loan.getStatus());
    }

    @Test
    void shouldAdd() {
        Loan loan = makeLoan();
        Loan expected = repository.add(loan);
        loan.setLoanId(4);
        assertEquals(expected, loan);
    }

    @Test
    void shouldUpdate() {
        Loan loan = makeLoan();
        loan.setLoanId(2);
        assertTrue(repository.update(loan));
    }

    @Test
    void shouldNotUpdateNotFound() {
        Loan loan = makeLoan();
        loan.setLoanId(10);
        assertFalse(repository.update(loan));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(3));
    }

    @Test
    void shouldNotDeleteNotFound() {
        assertFalse(repository.delete(10));
    }

    private Loan makeLoan() {
        return new Loan(0,
                LocalDate.now(),
                7.8,
                new BigDecimal("20.00"),
                LocalDate.now(),
                LoanStatus.IN_PROGRESS,
                LocalDate.now(),
                new BigDecimal("20.00"),
                1,
                new LoanType(1,"Personal"));
    }
}