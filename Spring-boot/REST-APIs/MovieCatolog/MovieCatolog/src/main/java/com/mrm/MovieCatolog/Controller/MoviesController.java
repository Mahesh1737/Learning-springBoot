package com.mrm.MovieCatolog.Controller;

import com.mrm.MovieCatolog.Model.Movies;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    Map<Integer, Movies> moviesData = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Movies>> listAllMovies(){
        return ResponseEntity.ok(new ArrayList<>(moviesData.values()));
    }

    @PostMapping
    public ResponseEntity<Movies> addMovie(@RequestBody Movies movie){
        moviesData.put(movie.getId(), movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movies> searchMovieById(@PathVariable int id){
        Movies movie = moviesData.get(id);
        if(movie==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movies> updateMovieById(@PathVariable int id, @RequestBody Movies movie){
        Movies existing = moviesData.get(id);
        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        moviesData.put(id, existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movies> DeleteMovieById(@PathVariable int id){
        Movies existing = moviesData.get(id);
        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        moviesData.remove(id);
        return ResponseEntity.ok(null);
    }

    public Boolean checkYear(Date movieDate, Date filterDate){
        Calendar releaseY = Calendar.getInstance();
        releaseY.setTime(movieDate);
        Calendar filterY = Calendar.getInstance();
        filterY.setTime(filterDate);

        return releaseY.get(Calendar.YEAR) == filterY.get(Calendar.YEAR);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movies>> searchMovies(@RequestParam(required = false) String genre,
                                               @RequestParam(required = false) @DateTimeFormat(pattern="yyyy") Date releaseYear){
        List<Movies> allMovies = new ArrayList<>(moviesData.values());
        List<Movies> filtered = allMovies.stream()
                .filter(m -> (genre == null || m.getGenre().equalsIgnoreCase(genre)))
                .filter(m -> (releaseYear == null || checkYear( m.releaseYear, releaseYear)))
                .toList();
        return ResponseEntity.ok(filtered);
    }





}
