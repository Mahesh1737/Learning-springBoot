package com.mrm.spring_sec_p05_mysql.Repo;

import com.mrm.spring_sec_p05_mysql.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser, String> {
    Optional<AppUser> FindByUsername(String name);
}
