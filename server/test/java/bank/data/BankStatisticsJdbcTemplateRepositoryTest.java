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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BankStatisticsJdbcTemplateRepositoryTest {


    @Autowired
    BankStatisticsJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    public void setup(){
            knownGoodState.set();
    }


    @Test
    void getTotalTransactionsPerTransactionType() {
        List<Map<String, BigDecimal>> expected = new ArrayList<>();

        Map<String, BigDecimal> cat1 = new HashMap<>();
        cat1.put("WITHDRAWAL",new BigDecimal(200).setScale(6));
        expected.add(cat1);

        Map<String, BigDecimal> cat2 = new HashMap<>();
        cat2.put("LOAN",new BigDecimal(120).setScale(6));
        expected.add(cat2);

        Map<String, BigDecimal> cat3 = new HashMap<>();
        cat3.put("DEPOSIT",new BigDecimal(50).setScale(6));
        expected.add(cat3);


        List<Map<String, BigDecimal>> actual = repository.getTotalTransactionsPerTransactionType();

        assertEquals(3, actual.size());
        assertEquals(expected.get(0),actual.get(0));
    }
}