package com.gladguys.alucoapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.Grade;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ExamDTO {

    private Long id;

    private String name;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private Long classId;

    private Long teacherId;

    private Set<GradeDTO> gradesDTO;

    public ExamDTO() {}

    public Exam toEntity() {
        Exam exam = new Exam();
        exam.setId(id);
        exam.setName(name);
        exam.setDescription(description);
        exam.setDate(date);

        Class c = new Class();
        c.setId(classId);

        Set<Grade> grades = new HashSet<>();
        if(gradesDTO != null) {
            this.gradesDTO.forEach(g -> grades.add(g.toEntity()));
            exam.setGrades(grades);
        }

        return exam;
    }
}
