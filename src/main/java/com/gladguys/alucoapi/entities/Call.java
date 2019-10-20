package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.enums.StatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity(name = "call")
public @Data class Call {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private Class classCall;

	private LocalDateTime date;

	private StatusEnum status;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Call )) return false;
		return id != null && id.equals(((Call) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
