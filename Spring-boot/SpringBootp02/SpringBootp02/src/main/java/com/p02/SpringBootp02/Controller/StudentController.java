package com.p02.SpringBootp02.Controller;

import com.p02.SpringBootp02.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentService service;
    @GetMapping("/Welcome")
    public String dataFetchFromService(){
        return service.getStudentData();
    }


}
