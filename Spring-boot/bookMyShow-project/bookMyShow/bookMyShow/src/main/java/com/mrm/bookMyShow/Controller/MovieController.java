package com.mrm.bookMyShow.Controller;

import com.mrm.bookMyShow.DTO.BookingDto;
import com.mrm.bookMyShow.DTO.BookingRequestDto;
import com.mrm.bookMyShow.DTO.MovieDto;
import com.mrm.bookMyShow.Model.Movie;
import com.mrm.bookMyShow.Service.MovieService;
import jakarta.validation.Valid;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto){
        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }


    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }


}
