package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.entities.enums.StatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.time.LocalDate;

@Data
@Entity(name = "call")
public class Call {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private Class classCall;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

	private StatusEnum status;

	public CallDTO toDTO() {
		CallDTO callDTO = new CallDTO();
		callDTO.setId(id);
		callDTO.setDate(date);
		callDTO.setStatus(status);
		if (student != null)
			callDTO.setStudentId(student.getId());
		if (classCall != null)
			callDTO.setClassId(classCall.getId());

		return callDTO;
	}
}
