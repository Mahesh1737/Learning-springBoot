package com.mrm.Payment_Gateway.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String name, String course, Double amount){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Payment Successful "+course);

        simpleMailMessage.setText("Hi "+name+", \n\n"+
                "Thank you for enrolling in "+course+". \n\n"+
                "We are looking forward to teach and see you in live class"
        );

        System.out.println("Email send successfully");
        javaMailSender.send(simpleMailMessage);
    }

}
