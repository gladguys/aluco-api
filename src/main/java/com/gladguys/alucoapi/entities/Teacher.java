package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	private LocalDate createDate;

	@OneToOne
	@JoinColumn(name = "user_aluco_id")
	private User user;

}
