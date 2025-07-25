package bank.domain;

import bank.data.LoanRepository;
import bank.models.Loan;
import bank.models.LoanStatus;
import bank.models.LoanType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class LoanServiceTest {

    @Autowired
    LoanService service;

    @MockBean
    LoanRepository repository;

    @Test
    void shouldFindAll() {
        List<Loan> expected = List.of(makeLoan());
        when(repository.findAll()).thenReturn(expected);
        List<Loan> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Loan expected = makeLoan();
        expected.setLoanId(1);
        when(repository.findById(1)).thenReturn(expected);
        Loan actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByUserId() {
        List<Loan> expected = List.of(makeLoan());
        when(repository.findByUserId(1)).thenReturn(expected);
        List<Loan> actual = service.findByUserId(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Loan loan = makeLoan();
        Loan expected = makeLoan();
        expected.setLoanId(1);

        when(repository.add(loan)).thenReturn(expected);
        Result<Loan> result = service.add(loan);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenNoFlatInterest() {
        Loan loan = makeLoan();
        loan.setFlatInterest(-1);
        Result<Loan> result = service.add(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenNoDateDue() {
        Loan loan = makeLoan();
        loan.setDateDue(null);
        Result<Loan> result = service.add(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenNoStatus() {
        Loan loan = makeLoan();
        loan.setStatus(null);
        Result<Loan> result = service.add(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenNoBalance() {
        Loan loan = makeLoan();
        loan.setBalance(null);
        Result<Loan> result = service.add(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenNoUserId() {
        Loan loan = makeLoan();
        loan.setUserId(0);
        Result<Loan> result = service.add(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }


    @Test
    void shouldUpdate() {
        Loan loan = makeLoan();
        loan.setLoanId(1);
        when(repository.update(loan)).thenReturn(true);
        Result<Loan> result = service.update(loan);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenNoFlatInterest() {
        Loan loan = makeLoan();
        loan.setLoanId(1);
        loan.setFlatInterest(-1);
        Result<Loan> result = service.update(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenNoDateDue() {
        Loan loan = makeLoan();
        loan.setLoanId(1);
        loan.setDateDue(null);
        Result<Loan> result = service.update(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenNoStatus() {
        Loan loan = makeLoan();
        loan.setLoanId(1);
        loan.setStatus(null);
        Result<Loan> result = service.update(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenNoBalance() {
        Loan loan = makeLoan();
        loan.setLoanId(1);
        loan.setBalance(null);
        Result<Loan> result = service.update(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWhenNoUserId() {
        Loan loan = makeLoan();
        loan.setLoanId(1);
        loan.setUserId(0);
        Result<Loan> result = service.update(loan);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldDelete() {
        when(repository.delete(1)).thenReturn(true);
        Result<Loan> result = service.delete(1);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotDeleteNotFound() {
        when(repository.delete(100)).thenReturn(false);
        Result<Loan> result = service.delete(100);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }

    @Test
    void shouldNotDeleteNotInProgressStatus() {
        Loan loan = makeLoan();
        loan.setLoanId(1);
        loan.setStatus(LoanStatus.APPROVED);

        when(repository.findById(1)).thenReturn(loan);
        Result<Loan> result = service.delete(1);
        assertEquals(ResultType.INVALID, result.getType());
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
                new LoanType(1, "Personal"));
    }
}