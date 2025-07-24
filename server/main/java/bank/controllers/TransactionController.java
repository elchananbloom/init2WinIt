package bank.controllers;


import bank.domain.Result;
import bank.domain.TransactionService;
import bank.models.Transaction;
import bank.models.User;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/transaction")
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class TransactionController {

    private TransactionService service;

    public TransactionController(TransactionService transactionService){
        this.service = transactionService;
    }

    @GetMapping(params = "accountId")
    public List<Transaction> findByAccountId(@RequestParam int accountId){
        return service.findByAccountId(accountId);
    }

    @GetMapping(params = "loanId")
    public List<Transaction> findByLoanId(@RequestParam int loanId){
        return service.findByLoanId(loanId);
    }

    @PostMapping()
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<Transaction> result = service.addTransaction(transaction);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        else{
            return ErrorResponse.build(result);
        }
    }


}
