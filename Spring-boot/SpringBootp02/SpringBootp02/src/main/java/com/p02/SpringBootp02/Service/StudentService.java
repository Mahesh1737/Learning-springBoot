package com.p02.SpringBootp02.Service;

import com.p02.SpringBootp02.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepo repo;
    public String getStudentData(){
        return repo.getStudentData();
    }
}
