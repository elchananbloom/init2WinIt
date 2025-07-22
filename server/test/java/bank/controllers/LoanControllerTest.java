package bank.controllers;

import bank.data.LoanRepository;
import bank.models.Loan;
import bank.models.LoanStatus;
import bank.models.LoanType;
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
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class LoanControllerTest {

    @MockBean
    LoanRepository repository;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setup() {
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    @Test
    void findAllShouldReturn200() throws Exception {
        when(repository.findAll()).thenReturn(List.of(makeLoan()));
        mvc.perform(get("/api/loan"))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdShouldReturn200() throws Exception {
        when(repository.findById(1)).thenReturn(makeLoan());
        mvc.perform(get("/api/loan/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findByUserIdShouldReturn200() throws Exception {
        when(repository.findByUserId(1)).thenReturn(List.of(makeLoan()));
        mvc.perform(get("/api/loan?userId=1"))
                .andExpect(status().isOk());
    }

    @Test
    void addShouldReturn201() throws Exception {
        Loan input = makeLoan();
        input.setLoanId(0);
        Loan expected = makeLoan();

        when(repository.add(any(Loan.class))).thenReturn(expected);

        String inputJson = jsonMapper.writeValueAsString(input);

        mvc.perform(post("/api/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isCreated());
    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        mvc.perform(post("/api/loan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        Loan invalid = new Loan();
        String invalidJson = jsonMapper.writeValueAsString(invalid);

        mvc.perform(post("/api/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn204() throws Exception {
        Loan loan = makeLoan();

        when(repository.update(any(Loan.class))).thenReturn(true);

        String json = jsonMapper.writeValueAsString(loan);

        mvc.perform(put("/api/loan/" + loan.getLoanId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateShouldReturn400WhenEmpty() throws Exception {
        mvc.perform(put("/api/loan/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn400WhenInvalid() throws Exception {
        Loan invalid = new Loan();
        invalid.setLoanId(1);
        String invalidJson = jsonMapper.writeValueAsString(invalid);

        mvc.perform(put("/api/loan/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn409WhenConflict() throws Exception {
        Loan loan = makeLoan();
        loan.setLoanId(2);
        String json = jsonMapper.writeValueAsString(loan);

        mvc.perform(put("/api/loan/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    void updateShouldReturn404WhenNotFound() throws Exception {
        Loan loan = makeLoan();

        when(repository.update(any(Loan.class))).thenReturn(false);

        String json = jsonMapper.writeValueAsString(loan);

        mvc.perform(put("/api/loan/" + loan.getLoanId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteShouldReturn204() throws Exception {

        when(repository.delete(1)).thenReturn(true);

        mvc.perform(delete("/api/loan/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteShouldReturn400WhenInvalid() throws Exception {
        Loan loan = makeLoan();
        loan.setStatus(LoanStatus.APPROVED); 
        when(repository.findById(loan.getLoanId())).thenReturn(loan);
        mvc.perform(delete("/api/loan/" + loan.getLoanId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteShouldReturn404WhenNotFound() throws Exception {
        when(repository.delete(10)).thenReturn(false);

        mvc.perform(delete("/api/loan/1"))
                .andExpect(status().isNotFound());
    }

    private final ObjectMapper jsonMapper = new ObjectMapper();

    private Loan makeLoan() {
        return new Loan(1,
                LocalDate.of(2026,1,1),
                7.8,
                new BigDecimal("20.00"),
                LocalDate.of(2026,1,1),
                LoanStatus.IN_PROGRESS,
                LocalDate.of(2026,1,1),
                new BigDecimal("20.00"),
                1,
                new LoanType(1, "Personal"));
    }


}