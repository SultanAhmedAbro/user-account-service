package com.user.account.useraccountservice.repository;

import com.user.account.useraccountservice.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    UserAccount findByEmail(String email);
}
