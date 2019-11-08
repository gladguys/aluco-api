package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.repositories.customs.CustomClassRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long>, CustomClassRepository {}
