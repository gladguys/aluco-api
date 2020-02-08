package com.gladguys.alucoapi.entities.dto;

import com.gladguys.alucoapi.entities.ConfigClass;
import lombok.Data;


@Data
public class ConfigClassDTO {

	private Long id;

	private Long classId;

	private double minimumAverage;

	private int maxQntAbsence;

	public ConfigClass toEntity() {

		ConfigClass configClass = new ConfigClass();
		configClass.setId(id);
		configClass.setMaxQntAbsence(maxQntAbsence);
		configClass.setMinimumAverage(minimumAverage);
		configClass.setClassId(classId);
		return configClass;
	}
}
