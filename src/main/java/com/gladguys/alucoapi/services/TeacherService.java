package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Teacher;
import org.springframework.stereotype.Component;

@Component
public interface TeacherService {

    Teacher getById(Long id);
    Teacher createOrUpdate(Teacher teacher);

}
