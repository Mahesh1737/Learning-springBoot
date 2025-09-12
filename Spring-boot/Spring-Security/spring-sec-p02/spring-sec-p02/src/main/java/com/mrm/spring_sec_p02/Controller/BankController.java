package com.mrm.spring_sec_p02.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BankController {

        int balance = 3000;
    @GetMapping("/bal")
    public int getbal(){
        return balance;
    }

    @GetMapping("/contact")
    public String ContactUs(){
        return "Contact us at: 8092489987";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "Welcome to about us page";
    }

    @GetMapping("/admin")
    public String adminLogin(){
        return "Welcome admiin";
    }

    @GetMapping("/transfer")
    public String TransferMoney(){
        return "Money tranfer successfully!";
    }

}
