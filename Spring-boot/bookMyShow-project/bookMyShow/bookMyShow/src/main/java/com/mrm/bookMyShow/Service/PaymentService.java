package com.mrm.bookMyShow.Service;

import com.mrm.bookMyShow.DTO.MovieDto;
import com.mrm.bookMyShow.DTO.PaymentDto;
import com.mrm.bookMyShow.Exception.ResourceNotFoundException;
import com.mrm.bookMyShow.Model.Payment;
import com.mrm.bookMyShow.Repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public PaymentDto createPayment(PaymentDto paymentDto){
        Payment payment = mapToEntity(paymentDto);
        Payment savedPayment = paymentRepo.save(payment);
        return mapToDto(savedPayment);
    }


    public PaymentDto getPaymentById(Long id){
        Payment payment = paymentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment not found"));
        return mapToDto(payment);
    }

    public List<PaymentDto> getAllPayment(){
        List<Payment> payment = paymentRepo.findAll();
        return payment.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PaymentDto mapToDto(Payment payment){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setTransactionId(payment.getTransactionId());
        paymentDto.setPaymentMethod(payment.getPaymentMethod());
        paymentDto.setPaymentTime(payment.getPaymentTime());
        paymentDto.setId(payment.getId());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setStatus(payment.getStatus());
//        paymentDto.set(payment.getStatus());

        return paymentDto;
    }

    public Payment mapToEntity(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setPaymentTime(paymentDto.getPaymentTime());
        payment.setStatus(paymentDto.getStatus());
        payment.setTransactionId(paymentDto.getTransactionId());
        payment.setAmount(paymentDto.getAmount());

        return payment;
    }
}
