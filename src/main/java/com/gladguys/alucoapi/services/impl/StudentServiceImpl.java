package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.repositories.StudentRepository;
import com.gladguys.alucoapi.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student getById(Long id) {
		return this.studentRepository.getOne(id);
	}

	@Override
	public List<Student> findAll() {
		return this.studentRepository.findAll();
	}

	@Override
	public Student save(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public Set<Student> getAllByTeacher(Long teacherId) {
		return this.studentRepository.findAllByTeacherIdOrderByName(teacherId);
	}

	@Override
	public Student update(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public void deleteById(Long id) {
		this.studentRepository.deleteById(id);
	}
}
