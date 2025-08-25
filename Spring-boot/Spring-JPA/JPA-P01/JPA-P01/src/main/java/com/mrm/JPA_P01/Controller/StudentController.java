package com.mrm.JPA_P01.Controller;

import com.mrm.JPA_P01.Entity.Student;
import com.mrm.JPA_P01.Repo.StudentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public List<Student> viewAllStudent(){
        return studentRepo.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }

    @PutMapping
    public Student putStudent(@RequestParam long id, @RequestBody Student student){
        Student s = studentRepo.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        System.out.println(student.getName());
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        return studentRepo.save(s);
    }

    @PatchMapping
    public Student patchStudent(@RequestParam Long id, @RequestParam String name){
        Student s = studentRepo.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
        s.setName(name);
        return s;
    }
}
