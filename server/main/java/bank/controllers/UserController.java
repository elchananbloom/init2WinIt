package bank.controllers;

import bank.domain.Result;
import bank.domain.UserService;
import bank.models.User;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/user")
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class UserController {

    private UserService service;

    public UserController(UserService userService){
        this.service = userService;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable int userId){
        return service.findUserById(userId);
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody @Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<User> result = service.addUser(user);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        else{
            return ErrorResponse.build(result);
        }


    }

}
