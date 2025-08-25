package com.mrm.JPA_P02.Repo;

import com.mrm.JPA_P02.Entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepo extends JpaRepository<Laptop, Integer> {
}
