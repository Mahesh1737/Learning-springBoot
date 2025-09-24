package com.mrm.bookMyShow.Controller;

import com.mrm.bookMyShow.DTO.ShowDto;
import com.mrm.bookMyShow.DTO.ShowSeatDto;
import com.mrm.bookMyShow.Service.ShowService;
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


    @GetMapping("/{showId}/seats")
    public ResponseEntity<?> getSeatsForShow(@PathVariable Long showId) {
        try {
            // Delegate the logic to the service layer
            ShowSeatDto seatMap = showService.getSeatsForShow(showId);
            return ResponseEntity.ok(seatMap); // Return 200 OK with the data
        } catch (ResourceNotFoundException e) {
            // If the service throws an exception (e.g., show not found), return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
