package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.ShowSeat;
import com.mrm.bookMyShow.Model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepo extends JpaRepository<Theatre, Long>{
    List<Theatre> findByCity(String city);

}
