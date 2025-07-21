package bank.controllers;

import bank.domain.Result;
import bank.domain.UserService;
import bank.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
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
    public ResponseEntity<Object> addUser(@RequestBody User user){
        Result<User> result = service.addUser(user);
        if(!result.isSuccess()){
            ResponseEntity<Object> res = ErrorResponse.build(result);
            return res;
        }
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

}
