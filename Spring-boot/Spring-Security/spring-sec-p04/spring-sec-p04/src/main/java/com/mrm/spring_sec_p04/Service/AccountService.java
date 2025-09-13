package com.mrm.spring_sec_p04.Service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @PreAuthorize("hasRole('USER')")
    public String getbalance(){
        return "This is your Current balance: thulllllluuuuuuu";
    }

    @PreAuthorize("hasRole('Admin')")
    public String closeAccount(){
        return "Your account has been closed";
    }
}
