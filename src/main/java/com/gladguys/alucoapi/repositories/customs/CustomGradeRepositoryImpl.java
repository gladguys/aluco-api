package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.GradeDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomGradeRepositoryImpl implements CustomGradeRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomGradeRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<GradeDTO> getAllGradesByExam(Long examId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT g.id, stu.id, stu.name as studentName, grade, g.student_id as studentId" +
				" FROM grade g ");
		sql.append(" INNER JOIN student stu on stu.id = g.student_id ");
		sql.append(" WHERE 1=1 ");
		if (examId != null) {
			sql.append(" AND exam_id = " + examId);
		}


		List<GradeDTO> result = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(GradeDTO.class));
		return result;
	}

	@Override
	public List<GradeDTO> getAllGradesByStudent(Long studentId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT g.id, g.grade, stu.name as nameStudent, student_id as studentId, exam_id as examId, e.name as examName " +
				" FROM grade g ");
		sql.append(" INNER JOIN student stu on stu.id = student_id ");
		sql.append(" INNER JOIN exam e on e.id = exam_id ");
		sql.append(" WHERE 1=1 ");
		if (studentId != null) {
			sql.append(" AND g.student_id = " + studentId);
		}

		List<GradeDTO> result = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(GradeDTO.class));
		return result;
	}

	@Override
	public List<GradeDTO> getAllGradesByClass(Long classId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT g.id, g.grade, stu.name as studentName, g.weight, student_id as studentId, g.exam_id as examId, e.name as examName FROM grade g ");
		sql.append(" INNER JOIN student stu on stu.id = student_id ");
		sql.append(" INNER JOIN exam e on e.id = g.exam_id ");
		sql.append(" WHERE 1=1 ");
		if (classId != null) {
			sql.append(" AND e.class_id = " + classId);
		}

		return (List<GradeDTO>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(GradeDTO.class));
	}
}
