package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Set<Exam> findByClassExamId(@Param("classId") Long classId);
}

