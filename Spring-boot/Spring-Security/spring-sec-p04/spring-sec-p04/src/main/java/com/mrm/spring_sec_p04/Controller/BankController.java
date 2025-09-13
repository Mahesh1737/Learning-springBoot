package com.mrm.spring_sec_p04.Controller;

import com.mrm.spring_sec_p04.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    AccountService service;

    @GetMapping("/bal")   //user role can access
    public String getBalance(){
        return service.getbalance();
    }

    @PostMapping("/close")    //Admin role can close
    public String closeAccount(){
        return service.closeAccount();
    }

    @GetMapping("/about")    //Admin role can close
    public String about(){
        return "This is the about page";
    }
}
