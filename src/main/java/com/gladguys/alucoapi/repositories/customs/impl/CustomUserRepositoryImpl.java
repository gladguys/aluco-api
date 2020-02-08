
package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.entities.dto.UserDTO;
import com.gladguys.alucoapi.repositories.customs.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

	private JdbcTemplate jdbcTemplate;

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

	@Override
	public UserDTO getUserTeacherByEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT te.id as teacherId, au.id as userId, te.name, te.photo_url as photoUrl, au.email as email");
		sql.append(" FROM teacher te ");
		sql.append(" INNER JOIN user_aluco au ON au.id = te.user_aluco_id ");
		sql.append(" WHERE au.email = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{email}, new BeanPropertyRowMapper<>(UserDTO.class));
	}
}