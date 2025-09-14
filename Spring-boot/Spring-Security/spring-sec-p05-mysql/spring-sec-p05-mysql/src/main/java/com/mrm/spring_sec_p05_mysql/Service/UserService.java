package com.mrm.spring_sec_p05_mysql.Service;

import com.mrm.spring_sec_p05_mysql.Entity.AppUser;
import com.mrm.spring_sec_p05_mysql.Repo.RoleRepo;
import com.mrm.spring_sec_p05_mysql.Repo.UserRepo;
import com.mrm.spring_sec_p05_mysql.Entity.Role;
import com.mrm.spring_sec_p05_mysql.userRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;


    public void saveUser(List<userRequest> userRequests){
        for(userRequest req : userRequests){
            AppUser user = new AppUser();
            user.setUsername(req.getUsername());
            user.setPassword(encoder.encode(req.getPassword()));
            user.setEnabled(true);

            Set<Role> roleSet = new HashSet<>();
            for(String rolename: req.getRoles()){
                Role role = roleRepo.findByName(rolename)
                        .orElseGet(()->{
                            Role newRole = new Role();
                            newRole.setName(rolename);
                            return roleRepo.save(newRole);
                        });
                roleSet.add(role);
            }

            user.setRoles(roleSet);
            userRepo.save(user);
        }
    }


}
