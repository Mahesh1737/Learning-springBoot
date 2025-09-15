package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long>{
    List<Booking> findByUserId(Long userId);

    Optional<Booking> findByBookingNumber(String bookingNumber);

    List<Booking> findByShowId(Long showId);
}
