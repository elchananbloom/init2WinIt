package bank.controllers;


import bank.domain.BankStatisticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/statistics/bank")
public class BankStatisticsController {

    BankStatisticsService service;

    public BankStatisticsController(BankStatisticsService bankStatisticsService){
        service = bankStatisticsService;
    }

    @GetMapping
    public Map<String, BigDecimal> getTotalTransactionsPerTransactionType(){
        return service.getTotalTransactionsPerTransactionType();
    }

    @GetMapping("/withdrawals")
    public  Map<String, BigDecimal> getTotalWithdrawsPerQuarter(){
        return service.getTotalWithdrawsPerQuarter();
    }

    @GetMapping("/deposits")
    public Map<String, BigDecimal> getTotalDepositsPerQuarter(){
        return service.getTotalDepositsPerQuarter();
    }

    @GetMapping("/loans")
    public Map<String, BigDecimal> getTotalLoansPerQuarter(){
        return service.getTotalLoansPerQuarter();
    }

}
