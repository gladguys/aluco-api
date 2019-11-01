package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.StudentDTO;

import java.util.List;

public interface CustomStudentRepository {

	List<StudentDTO> getAllByClassId(Long id);

}
