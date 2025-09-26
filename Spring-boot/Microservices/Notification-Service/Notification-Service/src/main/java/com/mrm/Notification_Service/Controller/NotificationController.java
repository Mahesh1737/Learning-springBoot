package com.mrm.Notification_Service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    ProductApi productApi;


    @GetMapping("/notification")
    public String sendNotification(){
        return "Email send to Mahesh";
    }

    @GetMapping("/notification/product")
    public String getProduct()
    {
        String s1="Hello from notification API ";
        //String s2=restTemplate.getForObject("http://localhost:9091/place",String.class);
        String s2 = productApi.invokeProductApi();
        return s1+" Product API "+s2;
    }

}
