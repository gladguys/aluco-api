package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.repositories.customs.CustomStudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, CustomStudentRepository {

    //@Query("select s from Student s where s.teacher.id = ?1 order by s.name")
    Set<Student> findAllByTeacherIdOrderByName(Long teacherId);

}
