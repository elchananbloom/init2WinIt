package bank.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BankStatisticsRepository {

    Map<String, BigDecimal> getTotalTransactionsPerTransactionType();

    Map<String, BigDecimal> getTotalWithdrawsPerQuarter();
    Map<String, BigDecimal> getTotalDepositsPerQuarter();
    Map<String, BigDecimal> getTotalLoansPerQuarter();



}
