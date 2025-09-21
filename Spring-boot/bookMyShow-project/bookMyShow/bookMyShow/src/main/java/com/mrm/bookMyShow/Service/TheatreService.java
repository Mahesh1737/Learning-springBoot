package com.mrm.bookMyShow.Service;

import com.mrm.bookMyShow.DTO.TheatreDto;
import com.mrm.bookMyShow.Exception.ResourceNotFoundException;
import com.mrm.bookMyShow.Model.Theatre;
import com.mrm.bookMyShow.Repository.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepo theatreRepo;


    public TheatreDto createTheatre(TheatreDto theatreDto) {
        Theatre theatre = mapToEntity(theatreDto);
        Theatre savedTheatre = theatreRepo.save(theatre);
        return mapToDto(savedTheatre);
    }

    public TheatreDto mapToDto(Theatre theatre){
        TheatreDto theatreDto = new TheatreDto();
        theatreDto.setId(theatre.getId());
        theatreDto.setName(theatre.getName());
        theatreDto.setCity(theatre.getCity());
        theatreDto.setAddress(theatre.getAddress());
        theatreDto.setTotalScreens(theatre.getTotalScreen());
        return theatreDto;
    }

    public TheatreDto getTheatreById(Long id){
        Theatre theatre = theatreRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theatre not found with id:"+id));
        return mapToDto(theatre);
    }

    public List<TheatreDto> getAllTheatres() {
        List<Theatre> theatres = theatreRepo.findAll();
        return theatres.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TheatreDto> getTheatreByCity(String city) {
        List<Theatre> theatres = theatreRepo.findByCity(city);
        return theatres.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    //update theatre

    //delete theatre


    public Theatre mapToEntity(TheatreDto theatreDto){
        Theatre theatre = new Theatre();
        theatre.setCity(theatreDto.getCity());
        theatre.setName(theatreDto.getName());
        theatre.setAddress(theatreDto.getAddress());
        theatre.setTotalScreen(theatreDto.getTotalScreens());
        return theatre;
    }
}
