package com.gladguys.alucoapi.entities.dto;

import lombok.Data;

@Data
public class StudentAbsenceDTO {

	private int quantity;
	private Long studentId;
	private Long classId;

}
