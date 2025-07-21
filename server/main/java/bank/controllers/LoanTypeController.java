package bank.controllers;

import bank.domain.LoanTypeService;
import bank.models.LoanType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/loan/type")
public class LoanTypeController {

    private final LoanTypeService service;

    public LoanTypeController(LoanTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<LoanType> findAll() {
        return service.findAll();
    }

}
