package com.mrm.JPA_P02.Repo;

import com.mrm.JPA_P02.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
