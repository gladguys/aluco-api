package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByClassExamId(@Param("classId") Long classId);
}

