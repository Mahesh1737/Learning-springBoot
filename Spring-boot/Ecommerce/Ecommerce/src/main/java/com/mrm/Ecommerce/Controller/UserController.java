package com.mrm.Ecommerce.Controller;

import com.mrm.Ecommerce.Entity.User;
import com.mrm.Ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }


    @GetMapping
    public List<User> getALlUser(){
        return userService.getAllUsers();
    }
}
