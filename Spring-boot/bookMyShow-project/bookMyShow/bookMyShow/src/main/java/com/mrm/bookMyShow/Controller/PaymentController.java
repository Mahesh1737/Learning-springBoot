package com.mrm.bookMyShow.Controller;

import com.mrm.bookMyShow.DTO.PaymentDto;
import com.mrm.bookMyShow.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(paymentService.createPayment(paymentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayment(){
        return ResponseEntity.ok(paymentService.getAllPayment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }
}
