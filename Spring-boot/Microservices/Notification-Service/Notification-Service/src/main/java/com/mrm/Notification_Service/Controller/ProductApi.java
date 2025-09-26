package com.mrm.Notification_Service.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Product-Service")
public interface ProductApi {

    @GetMapping("/place")
    public String invokeProductApi();
}
