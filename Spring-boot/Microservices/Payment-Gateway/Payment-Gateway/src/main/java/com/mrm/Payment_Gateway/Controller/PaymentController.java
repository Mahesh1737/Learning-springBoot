package com.mrm.Payment_Gateway.Controller;

import com.mrm.Payment_Gateway.Entity.PaymentOrder;
import com.mrm.Payment_Gateway.Service.PaymentService;
import com.razorpay.RazorpayException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment")
    public ResponseEntity<String> createOrder(@RequestBody PaymentOrder order){
        try{
            String response = paymentService.CreateOrder(order);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order");
        }
    }

    @PostMapping("/update-order")
    public ResponseEntity<String> updateOrder(@RequestParam String paymentId, @RequestParam String orderId, @RequestParam String status){
        paymentService.updateOrder(paymentId, orderId, status);
        System.out.println("Order Place successfully");
        return ResponseEntity.ok("Order Updated successfully");
    }
}
