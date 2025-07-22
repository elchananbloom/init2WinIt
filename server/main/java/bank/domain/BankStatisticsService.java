package bank.domain;

import bank.data.BankStatisticsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;


@Service
public class BankStatisticsService {

    private BankStatisticsRepository repository;

    public BankStatisticsService(BankStatisticsRepository bankStatisticsRepository){
        this.repository = bankStatisticsRepository;
    }

    public List<Map<String, BigDecimal>> getTotalTransactionsPerTransactionType(){
        return repository.getTotalTransactionsPerTransactionType();
    }
}
