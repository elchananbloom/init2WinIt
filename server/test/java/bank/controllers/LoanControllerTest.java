package bank.controllers;

import bank.data.LoanRepository;
import bank.domain.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class LoanControllerTest {

    @MockBean
    LoanRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void findAllShouldReturn200() {

    }

    @Test
    void findByIdShouldReturn200() {

    }

    @Test
    void findByUserIdShouldReturn200() {

    }

    @Test
    void addShouldReturn201() throws Exception {

    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {

    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {

    }

    @Test
    void updateShouldReturn204() throws Exception {

    }

    @Test
    void updateShouldReturn400WhenEmpty() throws Exception {

    }

    @Test
    void updateShouldReturn400WhenInvalid() throws Exception {

    }

    @Test
    void updateShouldReturn409WhenConflict() throws Exception {

    }

    @Test
    void updateShouldReturn404WhenNotFound() throws Exception {

    }

    @Test
    void deleteShouldReturn204() throws Exception {

    }

    @Test
    void deleteShouldReturn400WhenInvalid() throws Exception {

    }

    @Test
    void deleteShouldReturn404WhenNotFound() throws Exception {

    }




}