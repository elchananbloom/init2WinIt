package bank.controllers;
import bank.data.AccountRepository;
import bank.models.Account;
import bank.models.AccountType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class AccountControllerTest {

    @MockBean
    AccountRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void findAllShouldReturn200() throws Exception {
        var req = get("/api/account");
        when(repository.findAll()).thenReturn(List.of());
        mvc.perform(req)
                .andExpect(status().isOk());
    }

    @Test
    void addShouldReturn201() throws Exception {
        Account expected = new Account(1, AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);
        Account account = new Account(0,AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);
        when(repository.add(any(Account.class))).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();

        String accountJson = jsonMapper.writeValueAsString(account);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var req = post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountJson);

        mvc.perform(req)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/account")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        Account account = new Account();

        ObjectMapper jsonMapper = new ObjectMapper();

        String accountJson = jsonMapper.writeValueAsString(account);

        var req = post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountJson);

        mvc.perform(req)
                .andExpect(status().isBadRequest());
    }


}