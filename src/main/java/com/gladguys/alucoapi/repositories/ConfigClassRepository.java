package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.ConfigClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigClassRepository extends JpaRepository<ConfigClass, Long> {

	ConfigClass getByClassId(Long classId);
}