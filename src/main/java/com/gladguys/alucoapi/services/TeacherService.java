package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Teacher;
import com.gladguys.alucoapi.entities.dto.SignupDTO;
import org.springframework.stereotype.Component;

@Component
public interface TeacherService {

    Teacher getById(Long id);
    Teacher createOrUpdate(SignupDTO sign);

}
