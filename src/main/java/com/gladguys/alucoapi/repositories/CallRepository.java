package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {

    @Query("select c from Call c where c.classCall = ?1 and c.date = ?2 ")
    Set<Call> findAllByClassAndDate(Long classId, LocalDate date);

    Set<Call> getAllByStudentId(Long studentId);
}
