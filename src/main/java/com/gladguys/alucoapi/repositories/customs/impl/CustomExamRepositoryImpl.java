package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.ClassDTO;
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
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.id, e.name, e.description, e.creation_date, e.exam_date, e.weight, c.name as className, t.id as teacherId FROM exam e " +
				" INNER JOIN class c ON c.id = e.class_id "+
				" INNER JOIN teacher t ON t.id = c.teacher_id "+
				" WHERE e.id = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(ExamDTO.class));
	}
}
