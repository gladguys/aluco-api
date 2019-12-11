package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.LessonPlan;
import com.gladguys.alucoapi.repositories.customs.CustomLessonPlanRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonPlanRepository extends JpaRepository<LessonPlan, Long>, CustomLessonPlanRepository {
}
