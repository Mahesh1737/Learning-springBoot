package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.Show;
import com.mrm.bookMyShow.Model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowSeatRepo extends JpaRepository<ShowSeat, Long>{
    List<ShowSeat> findByShowId(Long showId);

    List<ShowSeat> findByShowIdANDStatus(Long showId, String status);
}
