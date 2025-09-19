package com.mrm.bookMyShow.Controller;

import com.mrm.bookMyShow.DTO.BookingDto;
import com.mrm.bookMyShow.DTO.BookingRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequest){
        return
    }
}
