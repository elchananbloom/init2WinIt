package bank.domain;

import bank.data.UserStatisticsJdbcRepository;
import bank.data.UserStatisticsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class UserStatisticsService {

    private UserStatisticsRepository repository;

    public UserStatisticsService(UserStatisticsRepository userStatisticsRepository){
        this.repository = userStatisticsRepository;
    }

    public Map<String, BigDecimal> getAverageAmountByCategory(int userId){
        return repository.getAverageAmountByCategory(userId);
    }

    public BigDecimal getTotalAccountsBalance(int userId){
        return repository.getTotalAccountsBalance(userId);
    }

    public Map<String, BigDecimal> getAmountSpentByCategoryForAccount(int accountId){
        return repository.getAmountSpentByCategoryForAccount(accountId);
    }

    public Map<String, BigDecimal> getLoanBalanceOverTime(int loanId){
        return repository.getLoanBalanceOverTime(loanId);
    }
}
