package com.mrm.spring_sec_p05_mysql.Service;

import com.mrm.spring_sec_p05_mysql.Entity.AppUser;
import com.mrm.spring_sec_p05_mysql.Role;
import com.mrm.spring_sec_p05_mysql.UserRequestList;
import com.mrm.spring_sec_p05_mysql.userRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    AppUser appUser;

    @Autowired
    Role role;

    @Autowired
    userRequest userRequest;

    @Autowired
    UserRequestList userRequestList;

    public String saveUser(List<Role>)


}
