package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "teacher")
@Data
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@Column(name = "photo_url")
	private String photoURL;

	@CreatedDate
	@Column(name = "create_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate createDate;

	@OneToOne
	@JoinColumn(name = "user_aluco_id")
	private User user;

	public void hideUserPassword() {
		this.user.setPassword(null);
	}
}
