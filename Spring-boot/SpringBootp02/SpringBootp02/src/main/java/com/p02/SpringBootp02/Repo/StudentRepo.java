package com.p02.SpringBootp02.Repo;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepo {
    public String getStudentData(){
        return "This is the repo data";
    }
}
