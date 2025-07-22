package bank.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserStatisticsRepository {
    Map<String, BigDecimal> getAverageAmountByCategory(int userId);
    Map<String, BigDecimal> getTotalAccountsBalance(int userId);


}
