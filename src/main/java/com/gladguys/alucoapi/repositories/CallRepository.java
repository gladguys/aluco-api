package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.repositories.customs.CustomCallRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Repository
public interface CallRepository extends JpaRepository<Call, Long>, CustomCallRepository {

    Set<Call> getAllByStudentId(Long studentId);
}
