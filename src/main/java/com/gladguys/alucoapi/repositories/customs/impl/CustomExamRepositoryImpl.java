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
		sql.append("  SELECT e.id, e.name, e.exam_date FROM exam e\n" +
				" INNER JOIN class c on c.id = e.class_id\n" +
				" INNER JOIN teacher t on t.id = c.teacher_id ");
		sql.append(" WHERE 1=1 ");
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
		//TODO: query that will get data about the exam PLUS list of students with their grade for this specific exam.
		return null;
	}
}
