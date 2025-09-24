package com.mrm.bookMyShow.Service;

import com.mrm.bookMyShow.DTO.MovieDto;
import com.mrm.bookMyShow.Exception.ResourceNotFoundException;
import com.mrm.bookMyShow.Model.Movie;
import com.mrm.bookMyShow.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    public MovieDto createMovie(MovieDto movieDto){
        Movie movie =mapToEntity(movieDto);
        Movie saveMovie = movieRepo.save(movie);
        return mapToDto(saveMovie);
    }


    public MovieDto getMovieById(Long id){
        Movie movie= movieRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie not found"));
        return mapToDto(movie);
    }

    public List<MovieDto> getAllMovies(){
        List<Movie> movies = movieRepo.findAll();
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public List<MovieDto> getMoviesByLanguage(String language){
        List<Movie> movies = movieRepo.findByLanguage(language);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMoviesByGenre(String genre){
        List<Movie> movies = movieRepo.findByGenre(genre);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto){
        Movie movie= movieRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie not found"));
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());
        movie.setLanguage(movieDto.getLanguage());
        movie.setTitle(movieDto.getTitle());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setReleaseDate(movieDto.getReleaseDate());

        Movie updatedMovie = movieRepo.save(movie);
        return mapToDto(updatedMovie);
    }


    public void deleteMovie(Long id){
        Movie movie= movieRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie not found"));
        movieRepo.delete(movie);
    }


    public MovieDto mapToDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setGenre(movie.getGenre());
        movieDto.setLanguage(movie.getLanguage());
        movieDto.setDescription(movie.getDescription());
        movieDto.setPosterUrl(movie.getPosterUrl());
        movieDto.setDurationMins(movie.getDurationMins());
        movieDto.setReleaseDate(movie.getReleaseDate());
        return movieDto;
    }

    public Movie mapToEntity(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());
        movie.setLanguage(movieDto.getLanguage());
        movie.setTitle(movieDto.getTitle());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setReleaseDate(movieDto.getReleaseDate());
//        movie.set(movieDto.getReleaseDate());
        return movie;
    }

}
