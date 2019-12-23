package com.gladguys.alucoapi.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class ConfigClass {

	@Id
	@GeneratedValue
	private Long id;

	private Long classId;

	private double minimumAverage;

	private int maxQntAbsence;
}
