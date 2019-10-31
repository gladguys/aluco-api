package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //@Query("select s from Student s where s.teacher.id = ?1 order by s.name")
    Set<Student> findAllByTeacherIdOrderByName(Long teacherId);

    @Query(value = "SELECT s.* FROM student s " +
           "INNER JOIN student_class sc ON sc.student_id = s.id " +
           "WHERE sc.class_id = :id", nativeQuery = true)
    Set<Student> getAllByClassId(@Param("id") Long classId);
}
