package com.gladguys.alucoapi.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "class")
public @Data
class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome deve ser informado.")
    private String name;

    private String description;

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @OneToMany(
            mappedBy = "classCall",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Call> calls = new ArrayList<>();

    @OneToMany(
            mappedBy = "classExam",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Exam> exams = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
