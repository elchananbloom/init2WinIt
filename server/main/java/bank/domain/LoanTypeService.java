package bank.domain;

import bank.data.LoanTypeRepository;
import bank.models.LoanType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanTypeService {

    private final LoanTypeRepository repository;

    public LoanTypeService(LoanTypeRepository repository) {
        this.repository = repository;
    }

    public List<LoanType> findAll () {
        return repository.findAll();
    }
}
