package com.mrm.spring_sec_p03_csrfdemo.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private Map<String, String> UserData = new HashMap<>();

    @GetMapping("/user")
    public String getUser(){
        return "hello user";
    }

    @PostMapping("/addUser")
    public String Adduser(@RequestBody Map<String, String> data){
        UserData.put(data.get("usrename"), UserData.get("password"));
        return "user saved in memory";
    }

}
