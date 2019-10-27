package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.repositories.ClassRepository;
import com.gladguys.alucoapi.services.ClassService;
import com.gladguys.alucoapi.services.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService {

	private ClassRepository classRepository;
	private StudentService studentService;

	public ClassServiceImpl(ClassRepository classRepository, StudentService studentService) {
		this.classRepository = classRepository;
		this.studentService = studentService;
	}

	@Override
	public ClassDTO getById(Long id) throws Exception {
		return this.classRepository.findById(id).orElseThrow(Exception::new).toDTO();
	}

	@Override
	public Set<ClassDTO> getAllByTeacher(Long teacherId) {
		Set<ClassDTO> classesDTO = new HashSet<>();

		Set<Class> classes = this.classRepository.getAllByTeacherId(teacherId);
		classes.forEach(c -> classesDTO.add(c.toDTO()));

		return classesDTO;
	}

	@Override
	public ClassDTO saveOrUpdate(ClassDTO classDTO) {
		Class classSaved = this.classRepository.save(classDTO.toEntity());
		return classSaved.toDTO();
	}

	@Override
	public boolean exists(Long id) {
		return this.classRepository.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.classRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void addStudentsIntoClass(Set<StudentDTO> studentDTOS, Long id) throws Exception {
		Class classToAddStudent = this.classRepository.findById(id).orElseThrow(Exception::new);

		Set<Student> students = new HashSet<>();
		studentDTOS.forEach(dto -> {
			students.add(this.studentService.getById(dto.getId()));
		});

		if(students.size() > 0) {
			classToAddStudent.setStudents(students);
			this.classRepository.save(classToAddStudent);
		}
	}
}
