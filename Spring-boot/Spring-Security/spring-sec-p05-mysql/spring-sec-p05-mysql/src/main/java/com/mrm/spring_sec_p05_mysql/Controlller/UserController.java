package com.mrm.spring_sec_p05_mysql.Controlller;

import com.mrm.spring_sec_p05_mysql.Service.UserService;
import com.mrm.spring_sec_p05_mysql.UserRequestList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/addUser")
    public String AddUser(@RequestBody UserRequestList request){
        service.saveUser(request.getUsers());
        return "users added successfully!";
    }

    @GetMapping("/admin")
    public String adminMethod(){
        return "This is a admin method";
    }

    @GetMapping("/user")
    public String userMethod(){
        return "This is a user method";
    }

    @GetMapping("/public")
    public String publicMethod(){
        return "This is a public method";
    }
}
