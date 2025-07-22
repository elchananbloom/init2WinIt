package bank.domain;

import bank.data.BankStatisticsRepository;
import bank.data.UserStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BankStatisticsServiceTest {

    @Autowired
    BankStatisticsService service;

    @MockBean
    BankStatisticsRepository repository;

    @Test
    void getTotalTransactionsPerTransactionType() {

        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("WITHDRAWAL",new BigDecimal(200).setScale(6));
        expected.put("LOAN",new BigDecimal(120).setScale(6));
        expected.put("DEPOSIT",new BigDecimal(50).setScale(6));

        when(repository.getTotalTransactionsPerTransactionType()).thenReturn(expected);

        Map<String, BigDecimal> actual = service.getTotalTransactionsPerTransactionType();

        assertEquals(3, actual.size());
        assertEquals(expected,actual);
    }
}