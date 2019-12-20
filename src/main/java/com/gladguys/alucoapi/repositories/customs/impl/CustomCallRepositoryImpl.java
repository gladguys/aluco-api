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
		sql.append(" SELECT id, student_id as studentId, class_id as classId, status, date ")
				.append(" FROM call c WHERE 1=1 ");

		if (classId != null)
			sql.append(" AND c.class_id = ").append(classId);

		if ( date != null)
			sql.append(" AND c.date = '" +date + "' ");

		return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(CallDTO.class));
	}

	@Override
	public CallDTO getById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.id, c.student_id as studentId, c.class_id as classId, c.date, status ");
		sql.append("FROM call c WHERE c.id = ?");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(CallDTO.class));
	}


}
