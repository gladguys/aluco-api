package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.ConfigClass;
import com.gladguys.alucoapi.entities.dto.ConfigClassDTO;
import com.gladguys.alucoapi.repositories.ConfigClassRepository;
import com.gladguys.alucoapi.services.ConfigClassService;
import org.springframework.stereotype.Service;

@Service
public class ConfigClassServiceImpl implements ConfigClassService {

	private ConfigClassRepository configClassRepository;

	public ConfigClassServiceImpl(ConfigClassRepository configClassRepository) {
		this.configClassRepository = configClassRepository;
	}

	@Override
	public ConfigClass saveConfigClass(ConfigClassDTO configClassDTO) {

		return this.configClassRepository.save(configClassDTO.toEntity());
	}

	public ConfigClass getConfigByClassId(Long classId) {
		return this.configClassRepository.getByClassId(classId);
	}

	public void deleteConfigClassByClassId(Long classId) {
		this.configClassRepository.deleteConfigClassByClassId(classId);
	}
}
