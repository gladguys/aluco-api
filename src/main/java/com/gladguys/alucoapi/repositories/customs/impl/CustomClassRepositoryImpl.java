package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.repositories.customs.CustomClassRepository;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	@Override
	public void deleteStudentFromClass(Long studentId, Long classId, Set<Long> examsId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("studentId", studentId);
		parameters.addValue("examsId", examsId);

		template.update("DELETE FROM grade WHERE student_id = :studentId AND exam_id IN (:examsId) ", parameters);
		this.jdbcTemplate.update("DELETE FROM student_class WHERE student_id = ? AND class_id = ?", new Object[]{studentId, classId});
	}
}
