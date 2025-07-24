package bank.domain;

import bank.data.BankStatisticsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;


@Service
public class BankStatisticsService {

    private final BankStatisticsRepository repository;

    public BankStatisticsService(BankStatisticsRepository bankStatisticsRepository){
        this.repository = bankStatisticsRepository;
    }

    public Map<String, BigDecimal> getTotalTransactionsPerTransactionType(){
        return repository.getTotalTransactionsPerTransactionType();
    }

    public Map<String, BigDecimal> getTotalWithdrawsPerQuarter(){
        return repository.getTotalWithdrawsPerQuarter();
    }

    public Map<String, BigDecimal> getTotalDepositsPerQuarter(){
        return repository.getTotalDepositsPerQuarter();
    }

    public Map<String, BigDecimal> getTotalLoansPerQuarter(){
        return repository.getTotalLoansPerQuarter();
    }



}
