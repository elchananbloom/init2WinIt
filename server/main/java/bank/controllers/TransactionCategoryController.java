package bank.controllers;

import bank.domain.Result;
import bank.domain.TransactionCategoryService;
import bank.models.TransactionCategory;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/transaction/category")
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class TransactionCategoryController {

    private final TransactionCategoryService service;

    public TransactionCategoryController(TransactionCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionCategory> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid TransactionCategory transactionCategory, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<TransactionCategory> result = service.add(transactionCategory);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{transactionCategoryId}")
    public ResponseEntity<Object> update(@PathVariable int transactionCategoryId, @RequestBody @Valid TransactionCategory transactionCategory, BindingResult bindingResult) {
        if (transactionCategoryId != transactionCategory.getTransactionCategoryId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<TransactionCategory> result = service.update(transactionCategory);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{transactionCategoryId}")
    public ResponseEntity<Object> deleteById(@PathVariable int transactionCategoryId) {
        Result<TransactionCategory> result = service.delete(transactionCategoryId);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
