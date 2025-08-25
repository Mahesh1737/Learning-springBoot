package com.mrm.JPA_P02.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(mappedBy="student", cascade = CascadeType.ALL)
    private Laptop laptop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    @JsonIgnoreProperties("student")
    Set<Course> courses = new HashSet<>();

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
