package bank.controllers;

import bank.domain.LoanService;
import bank.domain.Result;
import bank.models.Loan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/loan")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public List<Loan> findAll() {
        return service.findAll();
    }

    @GetMapping("/{loanId}")
    public Loan findById(@PathVariable int loanId) {
        return service.findById(loanId);
    }

    @GetMapping(params = "userId")
    public List<Loan> findByUserId(@RequestParam int userId) {
        return service.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid Loan loan, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<Loan> result = service.add(loan);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<Object> update(@PathVariable int loanId, @RequestBody @Valid Loan loan, BindingResult bindingResult) {
        if (loanId != loan.getLoanId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<Loan> result = service.update(loan);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Object> delete(@PathVariable int loanId) {
        Result<Loan> result = service.delete(loanId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
}
