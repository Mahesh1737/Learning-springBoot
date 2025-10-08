package com.mrm.Ecommerce.Repository;

import com.mrm.Ecommerce.Entity.Product;
import com.mrm.Ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
