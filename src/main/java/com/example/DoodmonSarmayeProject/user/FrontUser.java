package com.example.DoodmonSarmayeProject.user;

import com.example.DoodmonSarmayeProject.validation.FieldMatch;
import com.example.DoodmonSarmayeProject.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "رمز عبور و تکرار آن همخوانی ندارند")
})

public class FrontUser {
    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String userName;

    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String firstName;

    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String lastName;

    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String password;

    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String matchingPassword;

    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String phoneNumber;

    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String nationalCode;

    @ValidEmail
    @NotNull(message = "باید وارد شود")
    @Size(min = 1, message = "نامعتبر است")
    private String email;

    public FrontUser() {

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
}
