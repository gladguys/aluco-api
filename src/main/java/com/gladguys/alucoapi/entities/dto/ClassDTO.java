package com.gladguys.alucoapi.entities.dto;

import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ClassDTO {
	private Long id;
	private String name;
	private String descripton;
	private LocalDate creationDate;
	private Set<StudentDTO> students;
	private Long teacherId;

	public Class toEntity() {
		Class c = new Class();
		c.setId(id);
		c.setName(name);
		c.setDescription(descripton);
		c.setCreationDate(creationDate);

		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		c.setTeacher(teacher);

		return c;
	}
}
