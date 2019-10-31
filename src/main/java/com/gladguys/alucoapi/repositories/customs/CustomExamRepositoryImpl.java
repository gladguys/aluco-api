package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomExamRepositoryImpl implements CustomExamRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomExamRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ExamDTO> getByFilters(ExamFilter examFilter) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id, name, date FROM exam ");
		sql.append(" WHERE 1=1 ");

		if (examFilter.getName() != null)
			sql.append(" AND name LIKE '%" +examFilter.getName() + "%'");

		if (examFilter.getClassId() != null)
			sql.append(" AND class_id = "+examFilter.getClassId());

		if (examFilter.getTeacherId() != null)
			sql.append(" AND teacher_id = "+examFilter.getTeacherId());


		List<ExamDTO> result = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ExamDTO.class));
		return result;
	}

	@Override
	public ExamDTO getExamWithClass(Long id) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, class_id FROM exam WHERE id = " + id);
		return jdbcTemplate.queryForObject(sql.toString(),(rs, rowNum) ->
				new ExamDTO(
						rs.getLong("id"),
						rs.getLong("class_id")
				));
	}
}
