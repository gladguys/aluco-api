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
		sql.append(	" SELECT s.id as studentId, s.name as studentName, eg.grade as grade from exam_grade eg ");
		sql.append(	" INNER JOIN student s ON s.id = eg.student_id");
		sql.append("  WHERE eg.exam_id = ?  ORDER BY s.name ");

		return jdbcTemplate.query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(ExamGradeDTO.class));
	}

	public List<ExamGradeDTO> getGradesBoard(Long classId) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT s.id as studentId, s.name as studentName, e.id as examId, e.weight as weight, ");
		sql.append(" e.name as examName, e.exam_date as examDate, e.rec_exam as recExam, e.period_year as periodYear, ");
		sql.append(" eg.grade as grade FROM exam_grade eg ");
		sql.append(" INNER JOIN student s ON  s.id = eg.student_id ");
		sql.append(" INNER JOIN exam e ON e.id = eg.exam_id\n");
		sql.append(" WHERE e.class_id = ? ");

		return jdbcTemplate.query(sql.toString(), new Object[]{classId}, new BeanPropertyRowMapper<>(ExamGradeDTO.class));
	}

	@Override
	public void deleteByClassId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM exam_grade eg ");
		sql.append("WHERE eg.exam_id IN (SELECT id FROM exam e WHERE e.class_id = ?); ");
		sql.append(" DELETE FROM exam e WHERE e.class_id = ").append(id);

		this.jdbcTemplate.update(sql.toString(), id);
	}

	@Override
	public List<ExamGradeDTO> getGradesByStudentId(Long classId, Long studentId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT s.id as studentId, s.name as studentName, e.id as examId, e.weight as weight, ");
		sql.append(" e.name as examName, e.exam_date as examDate, e.rec_exam as recExam, e.period_year as periodYear,");
		sql.append(" eg.grade as grade FROM exam_grade eg ");
		sql.append(" INNER JOIN student s ON  s.id = eg.student_id ");
		sql.append(" INNER JOIN exam e ON e.id = eg.exam_id\n");
		sql.append(" WHERE s.id = ? ");
		if (classId != null) {
			sql.append(" AND e.class_id  = ").append(classId);
		}

		return jdbcTemplate.query(sql.toString(),
				new Object[]{studentId},
				new BeanPropertyRowMapper<>(ExamGradeDTO.class));
	}
}
