package bank.controllers;

import bank.data.UserRepository;
import bank.models.Role;
import bank.models.TransactionCategory;
import bank.models.User;
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

import java.time.LocalDate;
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
class UserControllerTest {

    @MockBean
    UserRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void findByShouldReturn200() throws Exception {
        User user = new User(1, "Guy", "One", "123 Street", "1234567890", "test1@example.com", "ABCDEF", LocalDate.parse("2025-01-01"), Role.ADMIN);

        var req = get("/api/user/1");
        when(repository.findById(1)).thenReturn(user);
         mvc.perform(req)
                .andExpect(status().isOk());
    }

    @Test
    void addShouldReturn200() throws Exception {
        User user = new User();
        user.setFirstName("firstName2");
        user.setLastName("lastName2");
        user.setAddress("address");
        user.setEmail("email@gmail.com");
        user.setPhoneNumber("9991234567");
        user.setPasswordHash("passwordHash");
        user.setRole(Role.USER);

        User expected = new User();
        expected.setUserId(4);
        expected.setFirstName("firstName2");
        expected.setLastName("lastName2");
        expected.setAddress("address");
        expected.setEmail("email@gmail.com");
        expected.setPhoneNumber("9991234567");
        expected.setPasswordHash("passwordHash");
        expected.setRole(Role.USER);

//        when(repository.addUser(user)).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();

        String userJson = jsonMapper.writeValueAsString(user);

        String expectedJson = jsonMapper.writeValueAsString(expected);


        var req = post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        when(repository.addUser(user)).thenReturn(expected);

        mvc.perform(req)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
        }

    @Test
    void addInvalidUserShouldReturn400() throws Exception {
        User user = new User();
        user.setFirstName("");
        user.setLastName("lastName2");
        user.setAddress("address");
        user.setEmail("email@gmail.com");
        user.setPhoneNumber("9991234567");
        user.setPasswordHash("passwordHash");
        user.setRole(Role.USER);

        User expected = new User();
        expected.setUserId(4);
        expected.setFirstName("firstName2");
        expected.setLastName("lastName2");
        expected.setAddress("address");
        expected.setEmail("email@gmail.com");
        expected.setPhoneNumber("9991234567");
        expected.setPasswordHash("passwordHash");
        expected.setRole(Role.USER);

//        when(repository.addUser(user)).thenReturn(expected);

        ObjectMapper jsonMapper = new ObjectMapper();

        String userJson = jsonMapper.writeValueAsString(user);

        String expectedJson = jsonMapper.writeValueAsString(expected);


        var req = post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        when(repository.addUser(user)).thenReturn(expected);

        mvc.perform(req)
                .andExpect(status().isBadRequest());
    }

    }