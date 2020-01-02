package com.gladguys.alucoapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.StudentClassObservation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentClassObservationDTO {

    private Long id;
    private Long studentId;
    private Long classId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String observation;

    public StudentClassObservation toEntity() {
        StudentClassObservation studentClassObservation = new StudentClassObservation();
        studentClassObservation.setId(id);

        Student student = new Student();
        student.setId(studentId);
        studentClassObservation.setStudent(student);

        Class classStudent = new Class();
        classStudent.setId(classId);
        studentClassObservation.setClassStudent(classStudent);

        studentClassObservation.setDate(date);
        studentClassObservation.setObservation(observation);

        return studentClassObservation;
    }
}
