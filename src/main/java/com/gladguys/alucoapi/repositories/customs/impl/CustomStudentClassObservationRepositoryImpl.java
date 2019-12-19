package com.gladguys.alucoapi.repositories.customs.impl;

import java.util.List;

import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gladguys.alucoapi.entities.StudentClassObservation;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.repositories.customs.CustomStudentClassObservationRepository;
import com.gladguys.alucoapi.repositories.customs.CustomStudentRepository;

@Repository
public class CustomStudentClassObservationRepositoryImpl implements CustomStudentClassObservationRepository {

	JdbcTemplate jdbcTemplate;

	public CustomStudentClassObservationRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<StudentClassObservationDTO> getStudentObservation(Long idStudent) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sco.id, sco.student_id, sco.class_id, sco.date, sco.observation FROM student_class_observation sco WHERE sco.student_id = ?");

		return jdbcTemplate.query(sql.toString(), new Object[]{idStudent}, new BeanPropertyRowMapper<>(StudentClassObservationDTO.class));
	}
}
