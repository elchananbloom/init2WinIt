package bank.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BankStatisticsRepository {

    List<Map<String, BigDecimal>> getTotalTransactionsPerTransactionType();


}
