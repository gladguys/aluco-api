package com.gladguys.alucoapi.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.StudentClassObservation;
import com.gladguys.alucoapi.repositories.customs.CustomStudentClassObservationRepository;
import com.gladguys.alucoapi.repositories.customs.CustomStudentRepository;

@Repository
public interface StudentClassObservationRepository extends JpaRepository<StudentClassObservation, Long>, CustomStudentClassObservationRepository {
}
