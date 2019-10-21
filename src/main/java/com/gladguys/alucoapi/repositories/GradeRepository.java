package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
