package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.Screen;
import com.mrm.bookMyShow.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepo extends JpaRepository<Show, Long>{
    List<Show> findByMovieId(Long movieId);

    List<Show> findByScreenId(Long screenId);

    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Show> findByMovie_idAndScreen_Theatre_City(Long movieId, String city);
}
