package com.example.DoodmonSarmayeProject.user;

import com.example.DoodmonSarmayeProject.entities.Role;
import com.example.DoodmonSarmayeProject.validation.FieldMatch;
import com.example.DoodmonSarmayeProject.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "رمز عبور و تکرار آن همخوانی ندارند")
})

public class FrontUser {

    private Long id;

    @NotBlank(message = "باید وارد شود")
    @Size(min = 6, max = 50 ,message = "نامعتبر است")
    private String userName;

    @NotBlank(message = "باید وارد شود")
    @Size(min = 2, max = 50 ,message = "نامعتبر است")
    private String firstName;

    @NotBlank(message = "باید وارد شود")
    @Size(min = 2, max = 50 ,message = "نامعتبر است")
    private String lastName;

    @NotBlank(message = "باید وارد شود")
    @Size(min = 6, message = "نامعتبر است")
    private String password;

    @NotBlank(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String matchingPassword;

    @NotBlank(message = "باید وارد شود")
    @Size(min = 11, max = 11 ,message = "نامعتبر است")
    private String phoneNumber;

    @NotBlank(message = "باید وارد شود")
    @Size(message = "نامعتبر است")
    private String nationalCode;

    @ValidEmail
    @NotBlank(message = "باید وارد شود")
    @Size(message = "نامعتبر است")
    private String email;

    private List<Role> role;

    private boolean enabled;

    public FrontUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
