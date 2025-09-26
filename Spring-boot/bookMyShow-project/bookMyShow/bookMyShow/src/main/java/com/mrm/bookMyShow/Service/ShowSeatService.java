package com.mrm.bookMyShow.Service;

import com.mrm.bookMyShow.DTO.ShowSeatDto;
import com.mrm.bookMyShow.Model.Show;
import com.mrm.bookMyShow.Model.ShowSeat;
import com.mrm.bookMyShow.Repository.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowSeatService {

    @Autowired
    private ShowSeatRepo showSeatRepo;

    public List<ShowSeatDto> findByShowIdAndStatus(Long showId, String status){
        List<ShowSeat> availableSeats = showSeatRepo.findByShowIdAndStatus(showId, status);

        return availableSeats.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public ShowSeatDto mapToDto(ShowSeat showSeat){
        ShowSeatDto showSeatDto = new ShowSeatDto();

        showSeatDto.setId(showSeat.getId());
        showSeatDto.setStatus(showSeat.getStatus());
        showSeatDto.setPrice(showSeat.getAmount());

        return showSeatDto;
    }
}
