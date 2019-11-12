package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.exception.notfound.ClassNotFoundException;
import com.gladguys.alucoapi.repositories.ClassRepository;
import com.gladguys.alucoapi.services.ClassService;
import com.gladguys.alucoapi.services.ExamGradeService;
import com.gladguys.alucoapi.services.ExamService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService {

	private ClassRepository classRepository;
	private ExamService examService;
	private ExamGradeService examGradeService;

	public ClassServiceImpl(ClassRepository classRepository, ExamService examService, ExamGradeService examGradeService) {
		this.classRepository = classRepository;
		this.examService = examService;
		this.examGradeService = examGradeService;
	}

	@Override
	public ClassDTO getById(Long id) {
		return this.classRepository.findById(id).orElseThrow(() -> new ClassNotFoundException(id)).toDTO();
	}

	@Override
	public List<ClassDTO> getAllByTeacher(Long teacherId) {

		return this.classRepository.getAllByTeacherId(teacherId);
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
	public void addStudentsIntoClass(Set<StudentDTO> studentDTOS, Long id) {

		Class classToAddStudent = this.classRepository.findById(id).orElseThrow(() -> new ClassNotFoundException(id));

		attachStudentsIntoExams(studentDTOS, classToAddStudent);

		Set<Student> students = new HashSet<>();
		studentDTOS.forEach(dto -> {
			students.add(dto.toEntity());
		});

		if(students.size() > 0) {
			classToAddStudent.addStudents(students);
			this.classRepository.save(classToAddStudent);
		}
	}

	private void attachStudentsIntoExams(Set<StudentDTO> studentDTOS, Class classToAddStudent) {
		Set<Long> exams = this.examService.getAllByClassId(classToAddStudent.getId());

		exams.forEach( ex -> {
			List<ExamGradeDTO> examGradeDTOS = new ArrayList<>();
			studentDTOS.forEach(studentDTO -> examGradeDTOS.add(new ExamGradeDTO(studentDTO.getId(),ex,null)));
			this.examGradeService.saveAllGrades(examGradeDTOS);
		});
	}

	@Override
	@Transactional
	public void deleteStudentFromClass(Long studentId, Long classId) {
		Set<Long> examsId = this.examService.getAllByClassId(classId);

		this.classRepository.deleteStudentFromClass(studentId, classId, examsId);
	}
}
