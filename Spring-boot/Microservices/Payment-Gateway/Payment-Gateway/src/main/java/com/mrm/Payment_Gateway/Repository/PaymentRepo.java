package com.mrm.Payment_Gateway.Repository;

import com.mrm.Payment_Gateway.Entity.PaymentOrder;
import com.razorpay.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByOrderId(String orderId);
}
