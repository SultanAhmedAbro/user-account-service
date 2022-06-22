package com.user.account.useraccountservice.service.impl;

import com.user.account.useraccountservice.dto.request.UserAccountRequest;
import com.user.account.useraccountservice.dto.response.UserAccountResponse;
import com.user.account.useraccountservice.entity.UserAccount;
import com.user.account.useraccountservice.repository.UserAccountRepository;
import com.user.account.useraccountservice.service.UserAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;
    @Override
    public List<UserAccountResponse> fetchAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        List<UserAccountResponse> userAccountResponses = new ArrayList<>();
        for(UserAccount userAccount: userAccounts) {
            UserAccountResponse userAccountResponse = new UserAccountResponse();
            BeanUtils.copyProperties(userAccount,userAccountResponse);
            userAccountResponses.add(userAccountResponse);
        }
        return userAccountResponses;
    }

    @Override
    public void saveUser(UserAccountRequest userAccountRequest) {
        UserAccount userAccount = userAccountRepository.findByEmail(userAccountRequest.getEmail());
        if(Objects.isNull(userAccount)) {
            userAccount = new UserAccount();
            BeanUtils.copyProperties(userAccountRequest,userAccount);
            userAccountRepository.save(userAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exist");
        }

    }
}
