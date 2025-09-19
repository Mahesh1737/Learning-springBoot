package com.mrm.bookMyShow.Service;

import com.mrm.bookMyShow.DTO.*;
import com.mrm.bookMyShow.Exception.ResourceNotFoundException;
import com.mrm.bookMyShow.Exception.SeatUnavailableException;
import com.mrm.bookMyShow.Model.*;
import com.mrm.bookMyShow.Repository.BookingRepo;
import com.mrm.bookMyShow.Repository.ShowRepo;
import com.mrm.bookMyShow.Repository.ShowSeatRepo;
import com.mrm.bookMyShow.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private ShowSeatRepo showSeatRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Transactional
    public BookingDto createBooking(BookingRequestDto bookingRequest){
        User user = userRepo.findById(bookingRequest.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        Show show = showRepo.findById(bookingRequest.getShowId())
                .orElseThrow(()-> new ResourceNotFoundException("show no found"));

        List<ShowSeat> selectedSeats = showSeatRepo.findAllById(bookingRequest.getSeatIds());

        for(ShowSeat seat: selectedSeats){
            if(!"AVAILABLE".equals(seat.getStatus())){
                throw new SeatUnavailableException("Seat "+seat.getSeat().getSeatNumber()+"Not available");
            }
            seat.setStatus("LOCKED");
        }
        showSeatRepo.saveAll(selectedSeats);

        Double totalAmount = selectedSeats.stream()
                .mapToDouble(ShowSeat::getAmount)
                .sum();

        //Payment
        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(bookingRequest.getPaymentMethod());
        payment.setStatus("Success");
        payment.setTransactionId(UUID.randomUUID().toString());

        //Booking
        Booking booking = new Booking();
        booking.setBookingTime(LocalDateTime.now());
        booking.setUser(user);
        booking.setShow(show);
        booking.setStatus("CONFIRMED");
        booking.setTotalAmount(totalAmount);
        booking.setPayment(payment);
        booking.setBookingNumber(UUID.randomUUID().toString());

        Booking saveBooking = bookingRepo.save(booking);

        selectedSeats.forEach(seat->{
            seat.setStatus("BOOKED");
            seat.setBooking(saveBooking);
        });

        showSeatRepo.saveAll(selectedSeats);

        return mapToBookingDto(saveBooking, selectedSeats);

    }

    //mapping the bookingrepo to booking dto
    private BookingDto mapToBookingDto(Booking booking, List<ShowSeat>seats){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingNumber(booking.getBookingNumber());
        bookingDto.setBookingTime(booking.getBookingTime());
        bookingDto.setTotalAmount(booking.getTotalAmount());
        bookingDto.setId(booking.getId());
        bookingDto.setStatus(booking.getStatus());

        UserDto userDto = new UserDto();
        userDto.setId(booking.getUser().getId());
        userDto.setName(booking.getUser().getName());
        userDto.setEmail(booking.getUser().getEmail());
        userDto.setPhoneNumber(booking.getUser().getMobileNumber());
        bookingDto.setUser(userDto);

        ShowDto showDto = new ShowDto();
        showDto.setId(booking.getShow().getId());
        showDto.setStartTime(booking.getShow().getStartTime());
        showDto.setEndTime(booking.getShow().getEndTime());


        MovieDto movieDto = new MovieDto();
        movieDto.setId(booking.getShow().getMovie().getId());
        movieDto.setTitle(booking.getShow().getMovie().getTitle());
        movieDto.setDescription(booking.getShow().getMovie().getDescription());
        movieDto.setGenre(booking.getShow().getMovie().getGenre());
        movieDto.setLanguage(booking.getShow().getMovie().getLanguage());
        movieDto.setDurationMins(booking.getShow().getMovie().getDurationMins());
        movieDto.setPosterUrl(booking.getShow().getMovie().getPosterUrl());
        movieDto.setReleaseDate(booking.getShow().getMovie().getReleaseDate());
        showDto.setMovie(movieDto);


        ScreenDto screenDto = new ScreenDto();
        screenDto.setId(booking.getShow().getScreen().getId());
        screenDto.setName(booking.getShow().getScreen().getName());
        screenDto.setTotalSeats(booking.getShow().getScreen().getTotalSeats());

        TheatreDto theatreDto = new TheatreDto();
        theatreDto.setId(booking.getShow().getScreen().getTheatre().getId());
        theatreDto.setName(booking.getShow().getScreen().getTheatre().getName());
        theatreDto.setCity(booking.getShow().getScreen().getTheatre().getCity());
        theatreDto.setAddress(booking.getShow().getScreen().getTheatre().getAddress());
        theatreDto.setTotalScreens(booking.getShow().getScreen().getTheatre().getTotalScreen());



        //this how they are dependent on each other
        screenDto.setTheatre(theatreDto);
        showDto.setScreen(screenDto);
        bookingDto.setShow(showDto);

        List<ShowSeatDto> showSeatDtos = seats.stream()
                .map(seat->{

                    SeatDto seatDto = new SeatDto();
                    seatDto.setSeatNumber(seat.getSeat().getSeatNumber());
                    seatDto.setId(seat.getSeat().getId());
                    seatDto.setSeatType(seat.getSeat().getSeatType());
                    seatDto.setBasePrice(seat.getSeat().getBasePrice());

                    ShowSeatDto showSeatDto = new ShowSeatDto();
                    showSeatDto.setId(seat.getId());
                    showSeatDto.setStatus(seat.getStatus());
                    showSeatDto.setPrice(seat.getAmount());

                    showSeatDto.setSeat(seatDto);
                    return showSeatDto;

                })
                .collect(Collectors.toList());
        bookingDto.setSeats(showSeatDtos);

        if(booking.getPayment()!=null){
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setId(booking.getPayment().getId());
            paymentDto.setPaymentMethod(booking.getPayment().getPaymentMethod());
            paymentDto.setPaymentTime(booking.getPayment().getPaymentTime());
            paymentDto.setStatus(booking.getPayment().getStatus());
            paymentDto.setAmount(booking.getPayment().getAmount());
            paymentDto.setTransactionId(booking.getPayment().getTransactionId());
            bookingDto.setPayment(paymentDto);
        }

        return bookingDto;
    }

    private BookingDto getBookingById(Long id){
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found"));

        List<ShowSeat> seats =  showSeatRepo.findAll()
                .stream()
                .filter(seat->seat.getBooking()!=null && seat.getBooking().getId().equals(booking.getId()))
                .collect(Collectors.toList());

        return mapToBookingDto(booking, seats);
    }

    private BookingDto getBookingByNumber(String bookingNumber){
        Booking booking = bookingRepo.findByBookingNumber(bookingNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found"));

        List<ShowSeat> seats =  showSeatRepo.findAll()
                .stream()
                .filter(seat->seat.getBooking()!=null && seat.getBooking().getBookingNumber().equals(booking.getBookingNumber()))
                .collect(Collectors.toList());

        return mapToBookingDto(booking, seats);
    }

    private BookingDto getBookingByUserId(Long userId){
        List<Booking> booking = bookingRepo.findByUserId(userId);

        List<ShowSeat> seats =  showSeatRepo.findAll()
                .stream()
                .filter(seat->seat.getBooking()!=null && seat.getBooking().getBookingNumber().equals(booking.getBookingNumber()))
                .collect(Collectors.toList());

        return mapToBookingDto(booking, seats);
    }
}
