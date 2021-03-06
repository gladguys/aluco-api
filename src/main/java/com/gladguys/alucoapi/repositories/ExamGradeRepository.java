package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.repositories.customs.CustomExamGradeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamGradeRepository extends JpaRepository<ExamGrade, Long>, CustomExamGradeRepository {
}
