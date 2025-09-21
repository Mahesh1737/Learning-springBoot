package com.mrm.bookMyShow.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String Language;
    private String genre;
    private Integer durationMins;
    private String releaseDate;
    private String posterUrl;

}
