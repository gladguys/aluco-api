package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import lombok.Data;

import java.util.List;

@Data
public class GradesWrapper {

	private List<ExamGradeDTO> grades;
}
