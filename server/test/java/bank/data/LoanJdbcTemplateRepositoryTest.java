package bank.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
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
    void shouldUpdate() {

    }

    @Test
    void shouldNotUpdateNotFound() {

    }

    @Test
    void shouldDelete() {

    }

    @Test
    void shouldNotDeleteNotFound() {

    }
}