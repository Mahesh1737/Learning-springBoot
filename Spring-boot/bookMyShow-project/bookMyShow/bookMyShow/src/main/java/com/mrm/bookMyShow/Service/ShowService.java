package com.mrm.bookMyShow.Service;

import com.mrm.bookMyShow.DTO.*;
import com.mrm.bookMyShow.Exception.ResourceNotFoundException;
import com.mrm.bookMyShow.Model.Movie;
import com.mrm.bookMyShow.Model.Screen;
import com.mrm.bookMyShow.Model.Show;
import com.mrm.bookMyShow.Model.ShowSeat;
import com.mrm.bookMyShow.Repository.MovieRepo;
import com.mrm.bookMyShow.Repository.ScreenRepo;
import com.mrm.bookMyShow.Repository.ShowRepo;
import com.mrm.bookMyShow.Repository.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ScreenRepo screenRepo;

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private ShowSeatRepo showSeatRepo;

    public ShowDto createShow(ShowDto showDto) {
        Show show = new Show();
        Movie movie = movieRepo.findById(showDto.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Screen screen = screenRepo.findById(showDto.getScreen().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        show.setScreen(screen);
        show.setMovie(movie);
        show.setStartTime(showDto.getStartTime());
        show.setEndTime(showDto.getEndTime());

        Show savedShow = showRepo.save(show);

        List<ShowSeat> availableSeats = showSeatRepo.findByShowIdAndStatus(savedShow.getId(), "AVAILABLE");

        return mapToDto(savedShow, availableSeats);
    }


    public ShowDto getShowById(Long id) {
        Show show = showRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with show Id:" + id));
        List<ShowSeat> availableSeats = showSeatRepo.findByShowIdAndStatus(show.getId(), "AVAILABLE");

        return mapToDto(show, availableSeats);
    }


    public List<ShowDto> getAllShows() {
        List<Show> shows = showRepo.findAll();
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepo.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show, availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowByMovie(Long id) {
        List<Show> shows = showRepo.findByMovieId(id);
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepo.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show, availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowByMovieIdAndCity(Long movieId, String city) {
        List<Show> shows = showRepo.findByMovie_idAndScreen_Theatre_City(movieId, city);
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepo.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show, availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowByStartDateAndEndingDate(LocalDateTime startingDate, LocalDateTime endingDate) {
        List<Show> shows = showRepo.findByStartTimeBetween(startingDate, endingDate);
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepo.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show, availableSeats);
                })
                .collect(Collectors.toList());
    }


    public ShowDto mapToDto(Show show, List<ShowSeat> availableSeats) {
        ShowDto showDto = new ShowDto();
        showDto.setId(show.getId());
        showDto.setStartTime(show.getStartTime());
        showDto.setEndTime(show.getEndTime());

        showDto.setMovie(new MovieDto(
                show.getMovie().getId(),
                show.getMovie().getTitle(),
                show.getMovie().getDescription(),
                show.getMovie().getLanguage(),
                show.getMovie().getGenre(),
                show.getMovie().getDurationMins(),
                show.getMovie().getReleaseDate(),
                show.getMovie().getPosterUrl()
        ));

        TheatreDto theatreDto = new TheatreDto(
                show.getScreen().getTheatre().getId(),
                show.getScreen().getTheatre().getName(),
                show.getScreen().getTheatre().getAddress(),
                show.getScreen().getTheatre().getCity(),
                show.getScreen().getTheatre().getTotalScreen()
        );

        showDto.setScreen(new ScreenDto(
                show.getScreen().getId(),
                show.getScreen().getName(),
                show.getScreen().getTotalSeats(),
                theatreDto
        ));

        List<ShowSeatDto> seatDtos = availableSeats.stream()
                .map(seat -> {
                    ShowSeatDto seatDto = new ShowSeatDto();
                    seatDto.setId(seat.getId());
                    seatDto.setStatus(seat.getStatus());
                    seatDto.setPrice(seat.getAmount());

                    SeatDto baseSeatDto = new SeatDto();
                    baseSeatDto.setId(seat.getSeat().getId());
                    baseSeatDto.setBasePrice(seat.getSeat().getBasePrice());
                    baseSeatDto.setSeatType(seat.getSeat().getSeatType());
                    baseSeatDto.setSeatNumber(seat.getSeat().getSeatNumber());

                    seatDto.setSeat(baseSeatDto);

                    return seatDto;
                })
                .collect(Collectors.toList());
        showDto.setAvailableSeats(seatDtos);

        return showDto;

    }

}
