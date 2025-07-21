package bank.models;

import org.springframework.cglib.core.Local;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class User {

    @NotBlank(message="userId must not be blank")
    private int userId;
    @NotBlank(message="first name must not be blank")
    private String firstName;
    @NotBlank(message="last name must not be blank")
    private String lastName;
    @NotBlank(message="address must not be blank")
    private String address;
    @NotBlank(message="phone must not be blank")
    private String phoneNumber;
    @NotBlank(message="email must not be blank")
    private String email;
    @NotBlank(message="passwordHash must not be blank")
    private String passwordHash;

    private LocalDate createdAt;

    public User(){}

    public User(int userId, String firstName, String lastName, String address, String phoneNumber, String email, String passwordHash, LocalDate createdAt, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Role role;


}
