package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.Payment;
import com.mrm.bookMyShow.Model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreenRepo extends JpaRepository<Screen, Long>{
    List<Screen> findByTheatreId(Long TheatreId);
}
