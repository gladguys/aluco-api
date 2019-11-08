package com.gladguys.alucoapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Exam;
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
    private LocalDate creationDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate examDate;

    private int weight;

    private Long classId;

    private Long teacherId;

    public ExamDTO() {}

    public Exam toEntity() {
        Exam exam = new Exam();
        exam.setId(id);
        exam.setName(name);
        exam.setDescription(description);
        exam.setCreationDate(creationDate);
        exam.setExamDate(examDate);
        exam.setWeight(weight);

        Class c = new Class();
        c.setId(classId);
        exam.setClassExam(c);

        return exam;
    }
}
