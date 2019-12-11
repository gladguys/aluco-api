package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Teacher;
import com.gladguys.alucoapi.entities.User;
import com.gladguys.alucoapi.entities.dto.SignupDTO;
import com.gladguys.alucoapi.entities.enums.ProfileEnum;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.repositories.TeacherRepository;
import com.gladguys.alucoapi.services.TeacherService;
import com.gladguys.alucoapi.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;

@Service
public class TeacherServiceImpl implements TeacherService {

    private UserService userService;
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(UserService userService, TeacherRepository teacherRepository) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Teacher createOrUpdate(SignupDTO sign) {
        boolean exists = userService.existsByEmail(sign.getEmail());

        if (exists) throw new ApiResponseException("Professor já existe em nossos registros");

        User user = new User();
        user.setCreateDate(LocalDate.now());
        user.setEmail(sign.getEmail());
        user.setPassword(sign.getPassword());
        user.setProfileEnum(ProfileEnum.TEACHER);

        User userCreated = this.userService.createOrUpdate(user);

        Teacher teacher = new Teacher();
        teacher.setCreateDate(LocalDate.now());
        teacher.setUser(userCreated);

        return this.teacherRepository.save(teacher);
    }

}
