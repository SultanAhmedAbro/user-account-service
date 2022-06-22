package com.user.account.useraccountservice.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserAccountRequest {
    @NotBlank(message = "User Name cannot be empty")
    private String userName;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
