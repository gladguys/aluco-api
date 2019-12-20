package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "student_class_observation")
public class StudentClassObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classStudent;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column(columnDefinition = "text")
    private String observation;

    public StudentClassObservationDTO toDTO() {
        StudentClassObservationDTO dto = new StudentClassObservationDTO();
        dto.setId(id);
        dto.setStudentId(student.getId());
        dto.setClassId(classStudent.getId());
        dto.setDate(date);
        dto.setObservation(observation);

        return dto;
    }
}
