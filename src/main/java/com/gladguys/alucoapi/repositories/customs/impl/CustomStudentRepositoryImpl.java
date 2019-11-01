package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.repositories.customs.CustomStudentRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

	JdbcTemplate jdbcTemplate;

	public CustomStudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<StudentDTO> getAllByClassId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT s.id, s.name FROM student s ");
		sql.append("INNER JOIN student_class sc ON sc.student_id = s.id ");
		sql.append("WHERE sc.class_id = ?");

		return jdbcTemplate.query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(StudentDTO.class));
	}

}
