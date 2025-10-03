package com.mrm.Payment_Gateway.Service;

import com.mrm.Payment_Gateway.Entity.PaymentOrder;
import com.mrm.Payment_Gateway.Repository.PaymentRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Value("${razorpay.api.key}")
    private String keyId;

    @Value("${razorpay.api.secret}")
    private String keySecret;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private EmailService emailService;


    public String CreateOrder(PaymentOrder orderDetails) throws RazorpayException {
        RazorpayClient client =new RazorpayClient(keyId, keySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",orderDetails.getAmount()*100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_"+ UUID.randomUUID());

        Order razorpayOrder = client.Orders.create(orderRequest);

        System.out.println(razorpayOrder.toString());

        orderDetails.setOrderId(razorpayOrder.get("id"));
        orderDetails.setStatus("Created");
        orderDetails.setCreateAt(LocalDateTime.now());

        paymentRepo.save(orderDetails);
        return razorpayOrder.toString();
    }

    public void updateOrder(String paymentId, String orderId, String status){
        PaymentOrder payment= paymentRepo.findByOrderId(orderId);
        payment.setPaymentId(paymentId);
        payment.setOrderId(orderId);
        payment.setStatus(status);
        paymentRepo.save(payment);

        if("SUCCESS".equalsIgnoreCase(payment.getStatus())){
            emailService.sendEmail(payment.getEmail(), payment.getName(), payment.getCourseName(), payment.getAmount());
        }
    }
}
