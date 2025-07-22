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

    public List<Map<String, BigDecimal>> getAverageAmountByCategory(int userId){
        return repository.getAverageAmountByCategory(userId);
    }

    public Map<String, BigDecimal> getTotalAccountsBalance(int userId){
        return repository.getTotalAccountsBalance(userId);
    }
}
