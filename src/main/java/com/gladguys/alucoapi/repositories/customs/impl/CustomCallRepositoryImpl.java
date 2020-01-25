package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.repositories.customs.CustomCallRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CustomCallRepositoryImpl implements CustomCallRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomCallRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CallDTO> getCallsByClassIdAndDate(Long classId, LocalDate date) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT c.id, c.student_id as studentId, s.name as studentName, c.class_id as classId, c.status, c.date ")
				.append(" FROM call c ")
				.append(" INNER JOIN student s ON s.id = c.student_id ")
				.append(" WHERE 1=1 ");

		if (classId != null)
			sql.append(" AND c.class_id = ").append(classId);

		if ( date != null)
			sql.append(" AND c.date = '" +date + "' ");

		return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(CallDTO.class));
	}

	@Override
	public CallDTO getById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.id, c.student_id as studentId, s.name as studentName, c.class_id as classId, c.date, status ")
				.append(" FROM call c ")
				.append(" INNER JOIN student s ON s.id = c.student_id ")
				.append(" WHERE c.id = ?");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(CallDTO.class));
	}

	@Override
	public List<CallDTO> getAllByStudentIdAndClassId(Long studentId, Long classId) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT c.id, c.student_id as studentId, s.name as studentName, c.class_id as classId, c.status, c.date ")
				.append(" FROM call c ")
				.append(" INNER JOIN student s ON s.id = c.student_id ")
				.append(" WHERE 1=1 ");

		if (classId != null)
			sql.append(" AND c.class_id = ").append(classId);

		if ( studentId != null)
			sql.append(" AND c.student_id = ").append(studentId);

		return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(CallDTO.class));
	}

	public void deleteByClassId(Long classId) {
		this.jdbcTemplate.update(
				"DELETE FROM call WHERE class_id = ? ",
				new Object[]{classId});
	}
}
