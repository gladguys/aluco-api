package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

	Set<Class> getAllByTeacherId(Long teacherId);
}
