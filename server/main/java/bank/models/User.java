package bank.models;

import org.springframework.cglib.core.Local;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@NotNull(message = "User cannot be null")
@NoYoungerThan16()
public class User {

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

    @NotNull(message = "Date of Birth is required")
    private LocalDate dob;

    @NotNull(message="Role must not be null")
    private Role role;
    private LocalDate createdAt;


    public User(){}

    public User(int userId, String firstName, String lastName, String address, String phoneNumber, String email, LocalDate dob, String passwordHash, LocalDate createdAt, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dob = dob;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.role = role;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(address, user.address) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(dob, user.dob) && role == user.role && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, address, phoneNumber, email, passwordHash, dob, role, createdAt);
    }
}
