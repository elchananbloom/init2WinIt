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

        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("WITHDRAWAL",new BigDecimal(200).setScale(6));
        expected.put("LOAN",new BigDecimal(120).setScale(6));
        expected.put("DEPOSIT",new BigDecimal(50).setScale(6));

        Map<String, BigDecimal> actual = repository.getTotalTransactionsPerTransactionType();

        assertEquals(3, actual.size());
    }

    @Test
    void getTotalWithdrawsPerQuarter() {
        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("Q1",new BigDecimal(200).setScale(2));
        Map<String, BigDecimal> actual = repository.getTotalWithdrawsPerQuarter();
        assertEquals(expected, actual);
    }

    @Test
    void getTotalDepositsPerQuarter() {
        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("Q1",new BigDecimal(50).setScale(2));
        Map<String, BigDecimal> actual = repository.getTotalDepositsPerQuarter();
        assertEquals(expected, actual);
    }

    @Test
    void getTotalLoansPerQuarter() {
        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("Q1",new BigDecimal(1020).setScale(2));
        Map<String, BigDecimal> actual = repository.getTotalLoansPerQuarter();
        assertEquals(expected, actual);
    }
}