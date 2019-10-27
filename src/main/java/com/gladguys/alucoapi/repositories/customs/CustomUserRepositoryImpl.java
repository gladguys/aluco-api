
package com.gladguys.alucoapi.repositories.customs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

	JdbcTemplate jdbcTemplate;

	public CustomUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Long getTeacherIdByUsername(String username) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT te.id FROM teacher te ");
		sql.append(" INNER JOIN user_aluco au ON au.id = te.user_aluco_id ");
		sql.append(" WHERE au.email = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{username}, Long.class);
	}
}