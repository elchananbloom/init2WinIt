package bank.controllers;
import bank.data.AccountRepository;
import bank.models.Account;
import bank.models.AccountType;
import bank.models.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
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
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class AccountControllerTest {

    @MockBean
    AccountRepository repository;

    @Autowired
    MockMvc mvc;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

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


        String accountJson = jsonMapper.writeValueAsString(account);

        var req = post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountJson);

        mvc.perform(req)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn204() throws Exception {
        Account account = new Account(1,AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);

        when(repository.update(any(Account.class))).thenReturn(true);

        String json = jsonMapper.writeValueAsString(account);

        mvc.perform(put("/api/account/" + account.getAccountId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateShouldReturn400WhenEmpty() throws Exception {
        mvc.perform(put("/api/account/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn400WhenInvalid() throws Exception {
        Account invalid = new Account();
        invalid.setAccountId(1);
        String invalidJson = jsonMapper.writeValueAsString(invalid);

        mvc.perform(put("/api/account/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn409WhenConflict() throws Exception {
        Account account = new Account(0,AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);
        account.setAccountId(2);
        String json = jsonMapper.writeValueAsString(account);

        mvc.perform(put("/api/account/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    void updateShouldReturn404WhenNotFound() throws Exception {
        Account account = new Account(1,AccountType.SAVINGS, new BigDecimal("2000.00"), "123451", LocalDate.of(2025, 1, 1),1);

        when(repository.update(any(Account.class))).thenReturn(false);

        String json = jsonMapper.writeValueAsString(account);

        mvc.perform(put("/api/account/" + account.getAccountId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }



}