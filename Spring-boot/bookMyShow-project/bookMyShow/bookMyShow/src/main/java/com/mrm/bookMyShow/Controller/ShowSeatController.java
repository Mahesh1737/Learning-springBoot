package com.mrm.bookMyShow.Controller;

import com.mrm.bookMyShow.DTO.ShowSeatDto;
import com.mrm.bookMyShow.Model.ShowSeat;
import com.mrm.bookMyShow.Service.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show-seat/")
public class ShowSeatController {

    @Autowired
    ShowSeatService showSeatService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ShowSeatDto>> getShowSeatByIdStatus(@PathVariable Long id, @RequestParam String status){
        return ResponseEntity.ok(showSeatService.findByShowIdAndStatus(id, status));
    }
}
