package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.entities.dto.StudentAbsenceDTO;
import com.gladguys.alucoapi.repositories.customs.CustomClassRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
public class CustomClassRepositoryImpl implements CustomClassRepository {

	private JdbcTemplate jdbcTemplate;

	public CustomClassRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ClassDTO> getAllByTeacherId(Long teacherId) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, class_status as classStatus FROM class WHERE teacher_id = ?");

		return this.jdbcTemplate.query(
				sql.toString(),
				new Object[]{teacherId},
				new BeanPropertyRowMapper<>(ClassDTO.class));
	}

	@Override
	public void deleteClassById(Long classId) {

		this.jdbcTemplate.update(
				"DELETE FROM student_class WHERE class_id = ? ",
				new Object[]{classId});

		this.jdbcTemplate.update(
				"DELETE FROM student_absences WHERE class_id = ? ",
				new Object[]{classId});

		this.jdbcTemplate.update(
				"DELETE FROM class WHERE id = ? ",
				new Object[]{classId});

	}

	@Override
	public int getGreatestNumberCall(Long classId) {

		String sql = "SELECT max(number) FROM number_call WHERE class_id = ? ";
		Integer number = this.jdbcTemplate.queryForObject(sql, new Object[] { classId }, Integer.class);
		if (number == null) return 0;
		return number;
	}

	@Override
	public void deleteStudentFromClass(Long studentId, Long classId, Set<Long> examsId) {
		NamedParameterJdbcTemplate template =
				new NamedParameterJdbcTemplate(Objects.requireNonNull(this.jdbcTemplate.getDataSource()));
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("studentId", studentId);
		if(examsId.size()!= 0) {
			parameters.addValue("examsId", examsId);
			template.update(
					"DELETE FROM exam_grade WHERE student_id = :studentId AND exam_id IN (:examsId) ",
					parameters);
		}

		this.jdbcTemplate.update(
				"DELETE FROM student_class WHERE student_id = ? AND class_id = ?",
				new Object[]{studentId, classId});
	}

	@Override
	public boolean isClassFromTeacher(Long classId, Long teacherId) {
		String sql = "SELECT count(*) FROM class WHERE teacher_id = ? AND id = ?";
		int count = this.jdbcTemplate.queryForObject(sql, new Object[] { teacherId, classId }, Integer.class);
		return count > 0;
	}

	@Override
	public List<StudentAbsenceDTO> getAbsences(Long classId, Long studentId) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT class_id as classId, student_id as studentId, qt_absences, qt_justified_absences ");
		sql.append(" FROM student_absences WHERE class_id = ? ");

		if (studentId != null) {
			sql.append("AND student_id = ").append(studentId);
		}

		return this.jdbcTemplate.query(
				sql.toString(),
				new Object[]{classId},
				new BeanPropertyRowMapper<>(StudentAbsenceDTO.class));

	}

}
