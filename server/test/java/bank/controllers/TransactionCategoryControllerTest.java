package bank.controllers;

import bank.data.TransactionCategoryRepository;
import bank.models.TransactionCategory;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class TransactionCategoryControllerTest {

    @MockBean
    TransactionCategoryRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void findAllShouldReturn200() throws Exception {
        var req = get("/api/transaction/category");
        when(repository.findAll()).thenReturn(List.of());
        mvc.perform(req)
                .andExpect(status().isOk());
    }

    @Test
    void addShouldReturn201() throws Exception {
        TransactionCategory transactionCategory = new TransactionCategory(0, "Culture");
        TransactionCategory expected = new TransactionCategory(1, "Culture");
        when(repository.add(any(TransactionCategory.class))).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();

        String transactionCategoryJson = jsonMapper.writeValueAsString(transactionCategory);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var req = post("/api/transaction/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionCategoryJson);

        mvc.perform(req)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/transaction/category")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        TransactionCategory transactionCategory = new TransactionCategory();

        ObjectMapper jsonMapper = new ObjectMapper();

        String transactionCategoryJson = jsonMapper.writeValueAsString(transactionCategory);

        var req = post("/api/transaction/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionCategoryJson);

        mvc.perform(req)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn204() throws Exception {
        TransactionCategory transactionCategory = new TransactionCategory(1, "Culture");
        when(repository.update(any(TransactionCategory.class))).thenReturn(true);

        ObjectMapper jsonMapper = new ObjectMapper();

        String transactionCategoryJson = jsonMapper.writeValueAsString(transactionCategory);

        var req = put("/api/transaction/category/" + transactionCategory.getTransactionCategoryId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionCategoryJson);

        mvc.perform(req)
                .andExpect(status().isNoContent());
    }

    @Test
    void updateShouldReturn400WhenEmpty() throws Exception {
        var request = put("/api/transaction/category/1")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn400WhenInvalid() throws Exception {
        TransactionCategory transactionCategory = new TransactionCategory(1, null);

        ObjectMapper jsonMapper = new ObjectMapper();

        String transactionCategoryJson = jsonMapper.writeValueAsString(transactionCategory);

        var req = put("/api/transaction/category/" + transactionCategory.getTransactionCategoryId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionCategoryJson);

        mvc.perform(req)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn409WhenConflict() throws Exception {
        TransactionCategory transactionCategory = new TransactionCategory(1, "Culture");

        ObjectMapper jsonMapper = new ObjectMapper();

        String transactionCategoryJson = jsonMapper.writeValueAsString(transactionCategory);

        var req = put("/api/transaction/category/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionCategoryJson);

        mvc.perform(req)
                .andExpect(status().isConflict());
    }

    @Test
    void updateShouldReturn404WhenNotFound() throws Exception {
        TransactionCategory transactionCategory = new TransactionCategory(1, "Culture");
        when(repository.update(any(TransactionCategory.class))).thenReturn(false);

        ObjectMapper jsonMapper = new ObjectMapper();

        String transactionCategoryJson = jsonMapper.writeValueAsString(transactionCategory);

        var req = put("/api/transaction/category/" + transactionCategory.getTransactionCategoryId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionCategoryJson);

        mvc.perform(req)
                .andExpect(status().isNotFound());
    }
}