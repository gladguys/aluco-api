package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.repositories.customs.CustomConfigClassRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomConfigClassRepositoryImpl implements CustomConfigClassRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomConfigClassRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void deleteConfigClassByClassId(Long classId) {

		this.jdbcTemplate.update(
				"DELETE FROM config_class WHERE class_id = ? ",
				new Object[]{classId});
	}
}
