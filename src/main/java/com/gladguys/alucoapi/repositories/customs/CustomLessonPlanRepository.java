package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.LessonPlanDTO;
import com.gladguys.alucoapi.entities.filters.LessonPlanFilter;

import java.util.List;

public interface CustomLessonPlanRepository {

	List<LessonPlanDTO> getAllByFilters(LessonPlanFilter filter);
	LessonPlanDTO getById(Long id);
	LessonPlanDTO getLatestEdited(Long id);
	LessonPlanDTO getNextLesson(Long id);
}
