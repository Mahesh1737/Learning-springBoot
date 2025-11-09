package com.mrm.Drive_Backend.Repository;

import com.mrm.Drive_Backend.Entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<File, Long> {
}
