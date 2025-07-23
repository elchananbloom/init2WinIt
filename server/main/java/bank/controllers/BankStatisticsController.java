package bank.controllers;


import bank.domain.BankStatisticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/statistic/bank")
public class BankStatisticsController {

    BankStatisticsService service;

    public BankStatisticsController(BankStatisticsService bankStatisticsService){
        service = bankStatisticsService;
    }

    @GetMapping("/")
    public Map<String, BigDecimal> getTotalTransactionsPerTransactionType(){
        return service.getTotalTransactionsPerTransactionType();
    }

}
