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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
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
        List<Map<String, BigDecimal>> expected = new ArrayList<>();
        expected.add(row);

        List<Map<String, BigDecimal>> actual = repository.getAverageAmountByCategory(1);

        assertEquals(1, actual.size());
        assertEquals(expected.get(0),actual.get(0));

    }

    @Test
    void getTotalAccountsBalance() {

        Map<String, BigDecimal> row = new HashMap<>();
        row.put("Total Account Balance",new BigDecimal(2000).setScale(2));

        Map<String, BigDecimal> actual = repository.getTotalAccountsBalance(1);

        assertEquals(row,actual);
    }


}