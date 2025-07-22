package bank.data;

import bank.models.Loan;

import java.util.List;

public interface LoanRepository {

    List<Loan> findAll();

    Loan findById(int loanId);

    List<Loan> findByUserId(int userId);

    Loan add(Loan loan);

    boolean update(Loan loan);


}
