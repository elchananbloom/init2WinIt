package bank.domain;

import bank.data.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LoanServiceTest {

    @Autowired
    LoanService service;

    @MockBean
    LoanRepository repository;

    @Test
    void shouldFindAll() {

    }

    @Test
    void shouldFindById() {

    }

    @Test
    void shouldFindByUserId() {

    }

    @Test
    void shouldAdd() {

    }

    @Test
    void shouldNotAddWhenNoFlatInterest() {

    }

    @Test
    void shouldNotAddWhenNoDateDue() {

    }

    @Test
    void shouldNotAddWhenNoStatus() {

    }

    @Test
    void shouldNotAddWhenNoBalance() {

    }

    @Test
    void shouldNotAddWhenNoUserId() {

    }


    @Test
    void shouldUpdate() {

    }

    @Test
    void shouldNotUpdateWhenNoFlatInterest() {

    }

    @Test
    void shouldNotUpdateWhenNoDateDue() {

    }

    @Test
    void shouldNotUpdateWhenNoStatus() {

    }

    @Test
    void shouldNotUpdateWhenNoBalance() {

    }

    @Test
    void shouldNotUpdateWhenNoUserId() {

    }

    @Test
    void shouldDelete() {

    }

    @Test
    void shouldNotDeleteNotFound() {

    }

    @Test
    void shouldNotDeleteNotInProgressStatus() {

    }
}