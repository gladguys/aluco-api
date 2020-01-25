package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.ConfigClass;
import com.gladguys.alucoapi.repositories.customs.CustomConfigClassRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigClassRepository extends JpaRepository<ConfigClass, Long>, CustomConfigClassRepository {

	ConfigClass getByClassId(Long classId);

	void deleteByClassId(Long classId);
}