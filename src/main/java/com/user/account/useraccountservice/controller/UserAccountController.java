package com.user.account.useraccountservice.controller;

import com.user.account.useraccountservice.dto.request.UserAccountRequest;
import com.user.account.useraccountservice.dto.response.UserAccountResponse;
import com.user.account.useraccountservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sultan Ahmed
 * This controller will be responsible to take requests in JSON format and manipulate and read User Accounts
 */
@RestController
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    /**
     * This Api will return all user accounts
     * @return ResponseEntity<List<UserAccountResponse>>
     */
    @GetMapping("/user-accounts")
    public ResponseEntity<List<UserAccountResponse>> fetchUserAccounts() {
        return new ResponseEntity<>(userAccountService.fetchAllUserAccounts(), HttpStatus.OK);
    }

    /**
     * This Api will create user account
     * @param userAccountRequest
     * @return
     */
    @PostMapping("/user-account")
    public ResponseEntity saveUser(@RequestBody @Valid UserAccountRequest userAccountRequest) {
        userAccountService.saveUser(userAccountRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
