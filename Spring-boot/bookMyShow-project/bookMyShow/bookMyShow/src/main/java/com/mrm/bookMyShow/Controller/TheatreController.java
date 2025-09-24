package com.mrm.bookMyShow.Controller;

import com.mrm.bookMyShow.DTO.TheatreDto;
import com.mrm.bookMyShow.Model.Theatre;
import com.mrm.bookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {

    @Autowired
    TheatreService service;

    @PostMapping
    public ResponseEntity<TheatreDto> createTheatre(@RequestBody TheatreDto theatreDto){
        return new ResponseEntity<>(service.createTheatre(theatreDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TheatreDto>> getAllTheatres(){
        return ResponseEntity.ok(service.getAllTheatres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreDto> getMoviesById(@PathVariable Long id){
        return ResponseEntity.ok(service.getTheatreById(id));
    }

}
