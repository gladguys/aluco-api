package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
