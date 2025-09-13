package com.mrm.spring_sec_p05_mysql.Controlller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    public String addUser(@RequestBody UserRequestList request){

    }
}
