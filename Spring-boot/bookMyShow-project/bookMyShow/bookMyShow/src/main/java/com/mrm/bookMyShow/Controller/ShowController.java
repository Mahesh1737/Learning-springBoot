package com.mrm.bookMyShow.Controller;

import com.mrm.bookMyShow.DTO.ShowDto;
import com.mrm.bookMyShow.DTO.ShowSeatDto;
import com.mrm.bookMyShow.Service.ShowService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping
    public ResponseEntity<ShowDto> createShow(@RequestBody ShowDto showDto){
        return new ResponseEntity<>(showService.createShow(showDto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ShowDto>> getAllShow(){
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowById(id));
    }


}
