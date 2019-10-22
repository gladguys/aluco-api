package com.gladguys.alucoapi.entities.dto;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.Student;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GradeDTO {

    private Long id;
    private BigDecimal grade;
    private Long weight;
    private Long studentId;
    private Long examId;

    public Grade toEntity() {
        Grade grade = new Grade();
        grade.setId(id);
        grade.setGrade(this.grade);
        grade.setWeight(weight);

        //Creating student
        Student student = new Student();
        student.setId(studentId);
        grade.setStudent(student);

        //creating exam
        Exam exam = new Exam();
        exam.setId(examId);
        grade.setExam(exam);

        return grade;
    }
