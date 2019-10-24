package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ClassDTO;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "class")
@Data
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome deve ser informado.")
    private String name;

    private String description;

    @Column(name = "create_date")
    private LocalDate creationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public ClassDTO toDTO() {
        ClassDTO dto = new ClassDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescripton(description);

        return dto;
    }
}
