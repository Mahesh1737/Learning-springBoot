package com.mrm.JPA_P02.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private Set<Student> student = new HashSet<>();
}
