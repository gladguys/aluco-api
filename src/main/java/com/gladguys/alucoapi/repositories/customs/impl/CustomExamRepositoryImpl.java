package com.gladguys.alucoapi.repositories.customs.impl;

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

        List<Long> l;
        l = jdbcTemplate.query(sql.toString(), new Object[]{classId}, new BeanPropertyRowMapper<>(Long.class));

        return l.stream().collect(Collectors.toSet());
    }
}
