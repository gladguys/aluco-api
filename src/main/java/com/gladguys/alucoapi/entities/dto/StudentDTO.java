package com.gladguys.alucoapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.Teacher;
import com.gladguys.alucoapi.entities.enums.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private String photoUrl;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateBirth;
    private String phone;
    private Long teacherId;
    private String responsibleName;
    private String responsiblePhone;
    private String address;
    private String previousSchool;
    private String observation;
    private GenderEnum gender;

    public Student toEntity() {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setPhotoUrl(photoUrl);
        student.setDateBirth(dateBirth);
        student.setPhone(phone);
        student.setAddress(address);
        student.setResponsibleName(responsibleName);
        student.setResponsiblePhone(responsiblePhone);
        student.setPreviousSchool(previousSchool);
        student.setObservation(observation);
        student.setGender(gender);

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        student.setTeacher(teacher);

        return student;
    }
}
