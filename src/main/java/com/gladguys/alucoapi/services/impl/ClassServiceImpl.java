package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.entities.dto.StudentAbsenceDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.entities.enums.ClassStatus;
import com.gladguys.alucoapi.exception.notfound.ClassNotFoundException;
import com.gladguys.alucoapi.repositories.ClassRepository;
import com.gladguys.alucoapi.services.CallService;
import com.gladguys.alucoapi.services.ClassService;
import com.gladguys.alucoapi.services.ConfigClassService;
import com.gladguys.alucoapi.services.ExamGradeService;
import com.gladguys.alucoapi.services.ExamService;
import com.gladguys.alucoapi.services.StudentClassObservationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {

	private ClassRepository classRepository;
	private ExamService examService;
	private ConfigClassService configClassService;
	private StudentClassObservationService observationService;
	private CallService callService;
	private ExamGradeService examGradeService;

	public ClassServiceImpl(ClassRepository classRepository,
							ExamService examService,
							ConfigClassService configClassService,
							StudentClassObservationService observationService,
							CallService callService, ExamGradeService examGradeService) {
		this.classRepository = classRepository;
		this.examService = examService;
		this.configClassService = configClassService;
		this.observationService = observationService;
		this.callService = callService;
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
		classDTO.setClassStatus(ClassStatus.CREATED);
		Class classSaved = this.classRepository.save(classDTO.toEntity());
		return classSaved.toDTO();
	}

	@Override
	public boolean exists(Long id) {
		return this.classRepository.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.examGradeService.deleteByClassId(id);
		this.configClassService.deleteConfigClassByClassId(id);
		this.callService.deleteAllByClassId(id);
		this.observationService.deleteByClassId(id);
		this.classRepository.deleteClassById(id);
	}

	@Override
	@Transactional
	public void addStudentsIntoClass(Set<StudentDTO> studentDTOS, Long id) {

		Class classToAddStudent = this.classRepository.findById(id).orElseThrow(() -> new ClassNotFoundException(id));
		attachStudentsIntoExams(studentDTOS, classToAddStudent);

		if(studentDTOS.size() > 0) {
			classToAddStudent.addStudents(studentDTOS.stream().map(StudentDTO::toEntity).collect(Collectors.toSet()));
			this.classRepository.save(classToAddStudent);
		}
	}

	private void attachStudentsIntoExams(Set<StudentDTO> studentDTOS, Class classToAddStudent) {

		Set<Long> exams = this.examService.getAllByClassId(classToAddStudent.getId());

		exams.forEach( ex -> {
			this.examGradeService.saveAllGrades(
					studentDTOS.stream().map(dto ->
							new ExamGradeDTO(dto.getId(),ex,null)).collect(Collectors.toList()));
		});
	}

	@Override
	@Transactional
	public void deleteStudentFromClass(Long studentId, Long classId) {
		Set<Long> examsId = this.examService.getAllByClassId(classId);

		this.classRepository.deleteStudentFromClass(studentId, classId, examsId);
	}

	@Override
	public boolean isClassFromTeacher(Long classId, Long teacherId) {
		return this.classRepository.isClassFromTeacher(classId, teacherId);
	}

	@Override
	public List<StudentAbsenceDTO> getAbsences(Long classId, Long studentId) {
		return this.classRepository.getAbsences(classId,studentId);
	}
}
