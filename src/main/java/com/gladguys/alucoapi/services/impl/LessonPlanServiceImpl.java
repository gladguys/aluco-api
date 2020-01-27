package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.dto.LessonPlanDTO;
import com.gladguys.alucoapi.entities.filters.LessonPlanFilter;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.repositories.LessonPlanRepository;
import com.gladguys.alucoapi.services.LessonPlanService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
		if (lessonPlanDTO.getClassId() == null)
			throw new ApiResponseException("id da turma não informado para o plano de aula");
		lessonPlanDTO.setModificationDate(new Date());
		return this.repository.save(lessonPlanDTO.toEntity()).toDTO();
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public LessonPlanDTO getLatestEdited(Long id) {
		try {
		  return this.repository.getLatestEdited(id);
		} catch (EmptyResultDataAccessException e) {
		  return null;
		}
		
	}

	@Override
	public LessonPlanDTO getNextLesson(Long id) {
		try {
		  return this.repository.getNextLesson(id);
		} catch (EmptyResultDataAccessException e) {
		  return null;
		}
	}
}
