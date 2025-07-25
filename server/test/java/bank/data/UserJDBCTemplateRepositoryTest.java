package bank.data;

import bank.models.Role;
import bank.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserJDBCTemplateRepositoryTest {


    @Autowired
    UserJDBCTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;


    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findById() {
        User expected = new User(1, "Guy", "One", "123 Street", "1234567890", "test1@example.com",LocalDate.parse("2000-01-01"), "ABCDEF", LocalDate.parse("2025-01-01"), Role.ADMIN);
        User actual = repository.findById(1);

        assertEquals(expected, actual);
    }

    @Test
    void findByEmail() {
        User expected = new User(2, "Person", "Two", "456 Ave", "0987654321", "test2@example.com",LocalDate.parse("2000-02-02"), "ASFGR", LocalDate.parse("2025-02-02"), Role.USER);
        User actual = repository.findByEmail("test2@example.com");


        assertEquals(expected, actual);


    }

    @Test
    void addUser() {
        User user = new User();
            user.setFirstName("Person");
            user.setLastName("Two");
            user.setEmail("newEntrytest@example.com");
            user.setAddress("456 Ave");
            user.setPhoneNumber("0987654321");
            user.setDob(LocalDate.parse("2000-02-02"));
            user.setPasswordHash("ASFGR");
            user.setCreatedAt(LocalDate.now());
            user.setRole(Role.USER);



        User actual = repository.addUser(user);

        user.setUserId(actual.getUserId());

        assertEquals(user, actual);

    }
}