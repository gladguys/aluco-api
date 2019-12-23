package com.gladguys.alucoapi.entities.dto;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class StudentAbsenceDTO {

	private int quantity;
	private Long studentId;
	private Long classId;

}
