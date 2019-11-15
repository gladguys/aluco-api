package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.dto.LessonPlanDTO;
import com.gladguys.alucoapi.entities.filters.LessonPlanFilter;
import com.gladguys.alucoapi.repositories.LessonPlanRepository;
import com.gladguys.alucoapi.services.LessonPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonPlanServiceImpl implements LessonPlanService {

	private LessonPlanRepository repository;

	public LessonPlanServiceImpl(LessonPlanRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<LessonPlanDTO> getByFilters(LessonPlanFilter filter) {
		return this.repository.getAllByFilters(filter);
	}

	@Override
	public LessonPlanDTO getById(Long id) {
		return this.repository.getById(id);
	}

	@Override
	public LessonPlanDTO save(LessonPlanDTO lessonPlanDTO) {

			return this.repository.save(lessonPlanDTO.toEntity()).toDTO();
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
}
