package com.user.account.useraccountservice.service;

import com.user.account.useraccountservice.dto.request.UserAccountRequest;
import com.user.account.useraccountservice.dto.response.UserAccountResponse;

import java.util.List;

public interface UserAccountService {

    public List<UserAccountResponse> fetchAllUserAccounts();

    void saveUser(UserAccountRequest userAccountRequest);
}
