package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.Booking;
import com.mrm.bookMyShow.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, Long>{
    List<Movie> findByLanguage(String Lang);

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(String genre);
}
