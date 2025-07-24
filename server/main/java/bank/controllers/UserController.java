package bank.controllers;

import bank.domain.Result;
import bank.domain.UserService;
import bank.models.User;
import bank.security.JwtConverter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/user")
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class UserController {

    private UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtConverter jwtConverter;

    public UserController(UserService userService
            , AuthenticationManager authenticationManager
            , JwtConverter jwtConverter
    ){
        this.service = userService;
        this.authenticationManager = authenticationManager;
        this.jwtConverter = jwtConverter;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable int userId){
        return service.findUserById(userId);
    }

    @GetMapping(params = "email")
    public User findByEmail(@RequestParam String email) {
        return service.findUserByEmail(email);
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

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody @Valid User user, BindingResult bindingResult){
        if (userId != user.getUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Result<User> result = service.updateUser(user);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return ErrorResponse.build(result);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody Map<String, String> credentials) {
        System.out.println(credentials);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credentials.get("email"), credentials.get("password"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String jwtToken = jwtConverter.getTokenFromUser((org.springframework.security.core.userdetails.User) authentication.getPrincipal());

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
