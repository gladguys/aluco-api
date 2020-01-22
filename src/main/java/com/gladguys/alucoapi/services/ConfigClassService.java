package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.ConfigClass;
import com.gladguys.alucoapi.entities.dto.ConfigClassDTO;

public interface ConfigClassService {

	ConfigClass saveConfigClass(ConfigClassDTO configClassDTO);
	ConfigClass getConfigByClassId(Long classId);
	void deleteConfigClassByClassId(Long classId);
}
