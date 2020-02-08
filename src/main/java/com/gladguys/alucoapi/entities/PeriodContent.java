package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import lombok.Data;

import java.util.List;

@Data
public class PeriodContent {

	private List<ExamGradeDTO> examsPeriod;
	private Double average;
}
