package bank.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserStatisticsJdbcRepositoryTest {

    @Autowired
    UserStatisticsJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    public void setUp(){
        knownGoodState.set();
    }

    @Test
    void getAverageAmountByCategory() {
        Map<String, BigDecimal> row = new HashMap<>();
        row.put("Car",new BigDecimal(200).setScale(6));

        Map<String, BigDecimal> actual = repository.getAverageAmountByCategory(1);

        assertEquals(1, actual.size());
        assertEquals(row,actual);

    }

    @Test
    void getTotalAccountsBalance() {

        BigDecimal expected = new BigDecimal(300).setScale(2);

        BigDecimal actual = repository.getTotalAccountsBalance(3);

        assertEquals(expected,actual);
    }


    @Test
    void getAmountSpentByCategoryForAccount() {
        Map<String, BigDecimal> row = new HashMap<>();
        row.put("Car",new BigDecimal(200).setScale(2));

        Map<String, BigDecimal> actual = repository.getAmountSpentByCategoryForAccount(1);

        assertEquals(1, actual.size());
        assertEquals(row,actual);
    }

    @Test
    void getLoanBalanceOverTime() {
        Map<String, BigDecimal> row = new HashMap<>();
        row.put("2025-01-23",new BigDecimal(900.00).setScale(2));

        Map<String, BigDecimal> actual = repository.getLoanBalanceOverTime(1);

        assertEquals(1, actual.size());
        assertEquals(row,actual);

    }
}