package bank.controllers;

import bank.data.TransactionRepository;
import bank.data.UserRepository;
import bank.models.Transaction;
import bank.models.TransactionCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class TransactionControllerTest {

    private ObjectMapper jsonMapper = new ObjectMapper();

    @MockBean
    TransactionRepository repository;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setup() {
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void findByAccountIdshouldReturn200() throws Exception {

        Transaction expected = new Transaction();
        expected.setTransactionId(1);
        expected.setTransactionCategory(new TransactionCategory(3,"Car"));
        expected.setAmount(BigDecimal.valueOf(200.00).setScale(2));
        expected.setType("WITHDRAWAL");
        expected.setAccountId(1);
        expected.setTransactionDate(LocalDate.parse("2025-01-02"));
        expected.setLoanId(0);
        expected.setDescription("emergency car fix");

        when(repository.findByAccountId(1)).thenReturn(List.of(expected));

        var req = get("/api/transaction").param("accountId", "1");
        mvc.perform(req).andExpect(status().isOk());
    }

    @Test
    void findByLoanIdshouldReturn200() throws Exception {

        Transaction expected = new Transaction();
        expected.setTransactionId(2);
        expected.setTransactionCategory(new TransactionCategory(1,"Groceries"));
        expected.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        expected.setType("LOAN");
        expected.setAccountId(2);
        expected.setTransactionDate(LocalDate.parse("2025-01-23"));
        expected.setLoanId(1);
        expected.setDescription(null);

        when(repository.findByLoanId(1)).thenReturn(List.of(expected));

        var req = get("/api/transaction").param("loanId", "1");
        mvc.perform(req).andExpect(status().isOk());
    }

    @Test
    void addTransactionShouldReturn201() throws Exception {

        Transaction added = makeTransaction();

        Transaction expected = makeTransaction();
        expected.setTransactionId(4);




        String transactionJson = jsonMapper.writeValueAsString(added);

        String expectedJson = jsonMapper.writeValueAsString(expected);

        when(repository.add(any(Transaction.class))).thenReturn(expected);

        var req = post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson);


        mvc.perform(req)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));


    }

    private Transaction makeTransaction(){
        Transaction added = new Transaction();
        added.setTransactionCategory(new TransactionCategory(2,"Check"));
        added.setAmount(BigDecimal.valueOf(120.00).setScale(2));
        added.setType("Deposit");
        added.setAccountId(2);
        added.setTransactionDate(LocalDate.of(2025,1,23));
        added.setLoanId(1);
        added.setDescription(null);

        return added;
    }
}