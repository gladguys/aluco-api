package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.repositories.customs.CustomExamRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExamRepository extends JpaRepository<Exam, Long>, CustomExamRepository {
}

