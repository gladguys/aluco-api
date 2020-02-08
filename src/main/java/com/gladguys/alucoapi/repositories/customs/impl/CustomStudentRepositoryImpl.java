package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.repositories.customs.CustomStudentRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

	JdbcTemplate jdbcTemplate;

	public CustomStudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<StudentDTO> getAllByClassId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT s.id, s.gender, s.name, s.aee, s.registration_number as registrationNumber, ");
		sql.append(" nc.number as numberCall FROM student s ");
		sql.append(" INNER JOIN student_class sc ON sc.student_id = s.id ");
		sql.append(" LEFT JOIN number_call nc ON nc.student_id = s.id and nc.class_id = ? ");
		sql.append(" WHERE sc.class_id = ? ");
		sql.append(" ORDER BY (s.name) ");

		return jdbcTemplate.query(sql.toString(), new Object[]{id,id}, new BeanPropertyRowMapper<>(StudentDTO.class));
	}

	@Override
	public List<StudentDTO> getAllByTeacherId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT s.id, s.name, s.gender, s.aee FROM student s ");
		sql.append(" WHERE s.teacher_id = ? ");
		sql.append(" ORDER BY (s.name) ");

		return jdbcTemplate.query(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(StudentDTO.class));
	}

	@Override
	public void deleteStudentFromAllClasses(Long studentId) {
		this.jdbcTemplate.update("DELETE FROM exam_grade WHERE student_id = ?", new Object[]{studentId});
		this.jdbcTemplate.update("DELETE FROM student_class WHERE student_id = ? ", new Object[]{studentId});
		this.jdbcTemplate.update("DELETE FROM student_absences student_id = ? ", new Object[]{studentId});
	}

	@Override
	public StudentDTO getById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT s.id, s.name, s.email, s.date_of_birth as dateBirth, s.phone, s.responsible_name as responsibleName, s.responsible_phone as responsiblePhone, " +
				" s.address, s.previous_school as previousSchool, s.observation, s.gender, s.aee, s.registration_number as registrationNumber FROM student s" +
				" WHERE s.id = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(StudentDTO.class));
	}
}
