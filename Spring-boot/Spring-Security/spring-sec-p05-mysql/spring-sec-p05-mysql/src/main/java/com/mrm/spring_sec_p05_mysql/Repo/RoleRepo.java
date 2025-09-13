package com.mrm.spring_sec_p05_mysql.Repo;

import com.mrm.spring_sec_p05_mysql.Entity.AppUser;
import com.mrm.spring_sec_p05_mysql.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<AppUser> FindByUserName(String name);
}
