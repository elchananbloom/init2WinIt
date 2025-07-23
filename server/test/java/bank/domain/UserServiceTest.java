package bank.domain;

import bank.data.UserJDBCTemplateRepository;
import bank.data.UserRepository;
import bank.models.Role;
import bank.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {



    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void findUserById() {
        User user = new User(1, "firstName", "lastName", "address", "9991234567", "email@gmail.com",LocalDate.parse("2000-02-02"), "passwordHash", LocalDate.now()
                , Role.USER);

        when(repository.findById(1)).thenReturn(user);

        User res = service.findUserById(1);

        assertEquals(user, res);
    }

    @Test
    void addUser() {
        User user = new User();
        user.setFirstName("firstName2");
        user.setLastName("lastName2");
        user.setAddress("address");
        user.setEmail("email@gmail.com");
        user.setPhoneNumber("9991234567");
        user.setDob(LocalDate.parse("2000-02-02"));
        user.setPasswordHash("passwordHash");
        user.setCreatedAt(LocalDate.now());
        user.setRole(Role.USER);


        User expected = new User(2, "firstName2", "lastName2", "address", "9991234567", "email@gmail.com",LocalDate.parse("2000-02-02"), "passwordHash", LocalDate.now()
                , Role.USER);

        when(repository.addUser(user)).thenReturn(expected);

        Result<User> res = service.addUser(user);

        assertTrue(res.isSuccess());
        assertEquals(expected,res.getPayload());


    }
}