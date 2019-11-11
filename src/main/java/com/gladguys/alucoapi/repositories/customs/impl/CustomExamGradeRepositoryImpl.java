package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.repositories.customs.CustomExamGradeRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomExamGradeRepositoryImpl implements CustomExamGradeRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomExamGradeRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ExamGradeDTO> getGradesByExamId(Long id) {

		StringBuilder sql = new StringBuilder();
		sql.append(	" SELECT s.id as studentId, s.name as studentName, eg.grade as grade from exam_grade eg \n" +
					" INNER JOIN student s ON s.id = eg.student_id\n" +
					" where eg.exam_id = ?  ORDER BY s.name \n");

		return jdbcTemplate.query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(ExamGradeDTO.class));
	}
}

