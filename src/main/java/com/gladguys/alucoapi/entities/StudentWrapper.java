package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.StudentDTO;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class StudentWrapper {

	private Set<StudentDTO> studentDTOS;
}
