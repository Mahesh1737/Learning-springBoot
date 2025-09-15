package com.mrm.bookMyShow.Repository;

import com.mrm.bookMyShow.Model.Movie;
import com.mrm.bookMyShow.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment, Long>{
    Optional<Payment> findByTransactionId(String transactionId);
}
