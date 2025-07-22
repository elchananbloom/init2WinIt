package bank.domain;

import bank.data.UserRepository;
import bank.data.UserStatisticsRepository;
import org.junit.jupiter.api.Test;
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
class UserStatisticsServiceTest {


    @Autowired
    UserStatisticsService service;

    @MockBean
    UserStatisticsRepository repository;

    @Test
    void getAverageAmountByCategory() {

        Map<String, BigDecimal> row = new HashMap<>();
        row.put("Car",new BigDecimal(200).setScale(6));



        when(repository.getAverageAmountByCategory(1)).thenReturn(row);

        Map<String,BigDecimal> actual = service.getAverageAmountByCategory(1);

        assertEquals(1, actual.size());
        assertEquals(row, actual);


    }

    @Test
    void getTotalAccountsBalance() {

        BigDecimal expected = new BigDecimal(2000).setScale(2);

        when(repository.getTotalAccountsBalance(1)).thenReturn(expected);

       BigDecimal actual = service.getTotalAccountsBalance(1);



        assertEquals(expected,actual);

    }
}