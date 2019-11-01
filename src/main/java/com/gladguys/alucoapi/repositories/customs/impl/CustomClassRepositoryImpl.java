package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.repositories.customs.CustomClassRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomClassRepositoryImpl implements CustomClassRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomClassRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ClassDTO> getAllByTeacherId(Long teacherId) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name from class where teacher_id = ?");

		return this.jdbcTemplate.query(sql.toString(), new Object[]{teacherId}, new BeanPropertyRowMapper<>(ClassDTO.class));
	}
}