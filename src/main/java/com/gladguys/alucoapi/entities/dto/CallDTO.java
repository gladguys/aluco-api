package com.gladguys.alucoapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CallDTO {

	private Long id;
	private Long studentId;
	private String studentName;
	private Long classId;
	private StatusEnum status;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private String teacherName;
	private String registrationNumber;
	private String className;

	public Call toEntity() {
		Call call = new Call();
		call.setId(id);
		call.setDate(date);
		call.setStatus(status);

		Student student = new Student();
		student.setId(studentId);
		call.setStudent(student);

		Class classCall = new Class();
		classCall.setId(classId);
		call.setClassCall(classCall);

		return call;
	}
}
