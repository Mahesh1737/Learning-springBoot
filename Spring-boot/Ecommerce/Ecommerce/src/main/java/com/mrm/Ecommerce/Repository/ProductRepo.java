package com.mrm.Ecommerce.Repository;

import com.mrm.Ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
