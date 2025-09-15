package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.ShowSeat;
import com.mrm.bookMyShow.Model.Theatre;
import com.mrm.bookMyShow.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Boolean existingByEmail(String email);
}
