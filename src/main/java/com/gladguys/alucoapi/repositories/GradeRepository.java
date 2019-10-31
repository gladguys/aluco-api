package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.repositories.customs.CustomGradeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>, CustomGradeRepository {

    Set<Grade> getAllByExamIdOrderByStudentId(Long examId);
    Set<Grade> getAllByStudentIdOrderByStudentNameAsc(Long examId);
}
