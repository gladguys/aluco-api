package com.gladguys.alucoapi.entities.dto;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private String photoUrl;
    private Date dateBirth;
    private String phone;
    private Long teacherId;

    public Student toEntity() {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setDateBirth(dateBirth);
        student.setPhone(phone);

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        student.setTeacher(teacher);

        return student;
    }
}
