package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.StudentDTO;
import lombok.Data;

import java.util.List;

@Data
public class StudentWrapper {

	private List<StudentDTO> studentDTOS;
}
