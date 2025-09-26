package com.mrm.Product_Service.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/place")
    public String placeOrder(){
        return "Your order : Laptop is placed successfully";
    }

}
