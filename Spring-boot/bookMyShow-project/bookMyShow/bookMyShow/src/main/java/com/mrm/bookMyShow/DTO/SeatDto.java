package com.mrm.bookMyShow.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SeatDto {
    private Long id;
    private String seatNumber;
    private String seatType;
    private Double basePrice;
}
