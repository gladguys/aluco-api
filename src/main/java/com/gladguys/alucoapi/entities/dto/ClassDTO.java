package com.gladguys.alucoapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ClassDTO {
	private Long id;

	private String name;

	private String description;

	private boolean isFromSchool;

	private String schoolName;

	private String shift;

	private String subject;

	private String gradeSchool;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;

	private Set<StudentDTO> students;

	private Set<ExamDTO> examDTOS;

	private Long teacherId;

	private Long classId;

	private String className;

	public Class toEntity() {
		Class c = new Class();
		c.setId(id);
		c.setName(name);
		c.setDescription(description);
		c.setCreationDate(creationDate);
		c.setFromSchool(isFromSchool);
		c.setSchoolName(schoolName);
		c.setGradeSchool(gradeSchool);
		c.setShift(shift);
		c.setSubject(subject);

		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		c.setTeacher(teacher);

		return c;
	}
}
