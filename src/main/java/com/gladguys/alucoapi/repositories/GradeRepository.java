package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    Set<Grade> getAllByExamIdOrderByStudentId(Long examId);
}
