package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.repositories.StudentRepository;
import com.gladguys.alucoapi.services.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

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
	public List<StudentDTO> getAllByTeacher(Long teacherId) {
		return this.studentRepository.getAllByTeacherId(teacherId);
	}

	@Override
	public List<StudentDTO> getAllByClassId(Long classId) {
		return this.studentRepository.getAllByClassId(classId);
	}

	@Override
	public Student update(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.studentRepository.deleteById(id);
		this.studentRepository.deleteStudentFromAllClasses(id);
	}

	@Override
	public boolean existsById(Long id) {
		return this.studentRepository.existsById(id);
	}

}
