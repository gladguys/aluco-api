package com.gladguys.alucoapi.entities.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "student_absences")
public class StudentAbsenceDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int qtAbsences;
	private int qtJustifiedAbsences;
	private Long studentId;
	private Long classId;

}
