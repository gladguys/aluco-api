package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import com.gladguys.alucoapi.repositories.customs.CustomExamRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CustomExamRepositoryImpl implements CustomExamRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomExamRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ExamDTO> getByFilters(ExamFilter examFilter) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT e.id, e.name, e.exam_date, e.weight, e.description, e.rec_exam, e.period_year FROM exam e ");
		sql.append(" INNER JOIN class c on c.id = e.class_id ");
		sql.append(" INNER JOIN teacher t on t.id = c.teacher_id ");
		sql.append(" WHERE e.id IS NOT NULL ");
		if (examFilter.getName() != null)
			sql.append(" AND name LIKE '%" +examFilter.getName() + "%'");

		if (examFilter.getClassId() != null)
			sql.append(" AND e.class_id = "+examFilter.getClassId());

		if (examFilter.getTeacherId() != null)
			sql.append(" AND c.teacher_id = "+examFilter.getTeacherId());


		List<ExamDTO> result = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ExamDTO.class));
		return result;
	}

	@Override
	public Set<Long> getAllByClassId(Long classId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id FROM exam WHERE class_id = ?");

		List<ExamDTO> examDTOS = jdbcTemplate.query(sql.toString(), new Object[]{classId}, new BeanPropertyRowMapper<>(ExamDTO.class));

		return examDTOS.stream().map(ExamDTO::getId).collect(Collectors.toSet());
	}

	@Override
	public ExamDTO getById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.id, e.name, e.description, e.creation_date, e.exam_date, e.rec_exam, e.period_year ");
		sql.append(" e.weight as weight ");
		sql.append(	"c.name as className, t.id as teacherId FROM exam e ");
		sql.append(" INNER JOIN class c ON c.id = e.class_id ");
		sql.append(" INNER JOIN teacher t ON t.id = c.teacher_id ");
		sql.append(" WHERE e.id = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(ExamDTO.class));
	}

	@Override
	public void deleteExamGradeById(Long id) {
		this.jdbcTemplate.update("DELETE FROM exam_grade WHERE exam_id = ? ", new Object[]{id});
		this.jdbcTemplate.update("DELETE FROM exam WHERE id = ? ", new Object[]{id});
	}

}
