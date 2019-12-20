package com.gladguys.alucoapi.entities.dto;

import lombok.Data;

@Data
public class StudentAbsenceDTO {

	private int quantity;
	private Long student_id;
	private Long class_id;

}
