package com.mrm.spring_sec_p05_mysql.Service;

import com.mrm.spring_sec_p05_mysql.Entity.AppUser;
import com.mrm.spring_sec_p05_mysql.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found!"+username));
        System.out.println("User Found name :"+user.getUsername()+"/ "+user.getPassword());
        return new User( user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
                user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).toList()
        );

    }
}
