package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@EqualsAndHashCode
@Entity(name = "student_call")
public @Data class StudentCall {

	@Id
	private Integer id;

	private StatusEnum status;
}
