package bank.controllers;


import bank.domain.UserStatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/statistics/user")
public class UserStatisticsController {

    private UserStatisticsService service;

    public UserStatisticsController(UserStatisticsService userStatisticsService){
        this.service = userStatisticsService;
    }

    @GetMapping("/category_average/{userId}")
    public Map<String, BigDecimal> getAverageAmountByCategory(@PathVariable int userId){
        return service.getAverageAmountByCategory(userId);
    }

    @GetMapping("/account_total/{userId}")
    public BigDecimal getTotalAccountsBalance(@PathVariable int userId){
        return service.getTotalAccountsBalance(userId);
    }


}
