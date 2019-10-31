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
		sql.append(" SELECT id, grade, student_id as studentId" +
				" FROM grade ");
		sql.append(" WHERE 1=1 ");
		if( examId != null) {
			sql.append(" AND exam_id = " + examId);
		}


		List<GradeDTO> result = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(GradeDTO.class));
		return result;
	}

	@Override
	public List<GradeDTO> getAllGradesByStudent(Long studentId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id, grade, student_id as studentId, exam_id as examId " +
				" FROM grade ");
		sql.append(" WHERE 1=1 ");
		if( studentId != null) {
			sql.append(" AND student_id = " + studentId);
		}


		List<GradeDTO> result = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(GradeDTO.class));
		return result;
	}
}
