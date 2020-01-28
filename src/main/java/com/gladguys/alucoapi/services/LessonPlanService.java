package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.dto.LessonPlanDTO;
import com.gladguys.alucoapi.entities.filters.LessonPlanFilter;

import java.util.List;

public interface LessonPlanService {

	List<LessonPlanDTO> getByFilters(LessonPlanFilter filters);
	LessonPlanDTO getById(Long id);
	LessonPlanDTO save(LessonPlanDTO lessonPlanDTO);
	void delete(Long id);
	LessonPlanDTO getLatestEdited(Long id);
	LessonPlanDTO getNextLesson(Long id);

}
