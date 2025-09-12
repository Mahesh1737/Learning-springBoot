package com.mrm.spring_sec_p01.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    int balance=2000;

    @GetMapping("/bal")
    public int getBalance(){
        return balance;
    }

    @PostMapping("/add")
    public int update(@RequestParam String AccNo, @RequestParam int num){
        return balance + num;
    }

    @GetMapping("/contact")
    public String ContactUs(){
        return "you can contact us at : 9809242455";
    }
}
