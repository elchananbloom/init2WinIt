package bank.controllers;

import bank.domain.AccountService;
import bank.domain.Result;
import bank.models.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/account")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService accountService) {
        this.service= accountService;
    }


    @GetMapping
    public List<Account> findAll(){
        return service.findAll();
    }
    @GetMapping(params = "userId")
    public List<Account> findByUserId(@RequestParam int userId){
        return service.findByUserId(userId);
    }

    @GetMapping("/{accountId}")
    public Account findById(@PathVariable int accountId){
        return service.findById(accountId);
    }


    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid Account account, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<Account> result = service.add(account);
        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }


}
