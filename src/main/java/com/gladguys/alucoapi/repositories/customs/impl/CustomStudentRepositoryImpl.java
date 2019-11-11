package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.repositories.customs.CustomStudentRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

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

	@Override
	public List<StudentDTO> getAllByTeacherId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT s.id, s.name, s.gender FROM student s ");
		sql.append(" WHERE s.teacher_id = ? ");
		sql.append(" ORDER BY (s.name) ");

		return jdbcTemplate.query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(StudentDTO.class));
	}

	@Override
	public void deleteStudentFromAllClasses(Long studentId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(Objects.requireNonNull(this.jdbcTemplate.getDataSource()));
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("studentId", studentId);
		template.update("DELETE FROM grade WHERE student_id = :studentId", parameters);
		this.jdbcTemplate.update("DELETE FROM student_class WHERE student_id = ? ", new Object[]{studentId});
	}
}
