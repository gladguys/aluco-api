package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Teacher;
import com.gladguys.alucoapi.entities.User;
import com.gladguys.alucoapi.repositories.TeacherRepository;
import com.gladguys.alucoapi.services.TeacherService;
import com.gladguys.alucoapi.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Teacher createOrUpdate(Teacher teacher) {
        User userCreated = this.userService.createOrUpdate(teacher.getUser());;
        teacher.setUser(userCreated);
        return this.teacherRepository.save(teacher);
    }

}
