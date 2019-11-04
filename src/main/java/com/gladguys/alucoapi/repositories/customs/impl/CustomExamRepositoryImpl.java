package com.gladguys.alucoapi.repositories.customs.impl;

import com.gladguys.alucoapi.entities.dto.ExamDTO;
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
    public Set<Long> getAllByClassId(Long classId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM exam WHERE class_id = ?");

        List<ExamDTO> examDTOS = jdbcTemplate.query(sql.toString(), new Object[]{classId}, new BeanPropertyRowMapper<>(ExamDTO.class));

        return examDTOS.stream().map(ExamDTO::getId).collect(Collectors.toSet());
    }
}