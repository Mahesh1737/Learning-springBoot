package com.mrm.bookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookingRequestDto {
    private Long userId;
    protected Long showId;
    private List<Long> seatIds;
    private String paymentMethod;

}
