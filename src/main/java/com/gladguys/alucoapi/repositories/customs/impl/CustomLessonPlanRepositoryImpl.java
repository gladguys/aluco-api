package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.LessonPlanDTO;
import com.gladguys.alucoapi.entities.filters.LessonPlanFilter;
import com.gladguys.alucoapi.repositories.customs.CustomLessonPlanRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomLessonPlanRepositoryImpl implements CustomLessonPlanRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomLessonPlanRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<LessonPlanDTO> getAllByFilters(LessonPlanFilter filter) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT l.id, l.content, l.lesson_date as lessonDate ");
		sql.append(" FROM lesson_plan l ");
		sql.append(" WHERE l.id IS NOT NULL ");
		if (filter.getClassId() != null)
			sql.append(" AND l.class_id = " + filter.getClassId());
		if (filter.getLessonDate() != null) {
			sql.append(" AND l.lesson_date = '" + filter.getLessonDate() + "' ");
		}

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(LessonPlanDTO.class));
	}

	@Override
	public LessonPlanDTO getById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT l.id, l.content, l.metodology, l.homework, l.classwork, l.lesson_date as lessonDate, l.notes, t.id as teacherId ");
		sql.append(" FROM lesson_plan l");
		sql.append(" INNER JOIN class c ON c.id = l.class_id ");
		sql.append(" INNER JOIN teacher t ON t.id = c.teacher_id ");
		sql.append(" WHERE l.id = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(LessonPlanDTO.class));
	}
}
