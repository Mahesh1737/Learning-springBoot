package com.mrm.bookMyShow.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ScreenDto {
    private Long id;
    private String name;
    private Integer totalSeats;
    private TheatreDto theatre;
    private SeatDto seats;

    public ScreenDto(Long id, String name, Integer totalSeats, TheatreDto theatreDto) {
    }

}
