package com.mrm.bookMyShow.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TheatreDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private Integer totalScreens;

}
