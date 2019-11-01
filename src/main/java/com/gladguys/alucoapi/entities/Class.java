package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.HashSet;
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

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "create_date")
    private LocalDate creationDate;

    @ManyToMany
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public ClassDTO toDTO() {
        ClassDTO dto = new ClassDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setCreationDate(creationDate);

        return dto;
    }

	public void addStudents(Set<Student> students) {
	    students.forEach(s -> this.students.add(s));
    }
}
