package bank.data;

import bank.models.LoanType;

import java.util.List;

public interface LoanTypeRepository {

    List<LoanType> findAll();
}
