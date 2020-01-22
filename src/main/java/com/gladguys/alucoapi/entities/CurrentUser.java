package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.UserDTO;
import lombok.Data;
import lombok.NonNull;

public @Data class CurrentUser {
	@NonNull private String token;
	@NonNull private UserDTO user;
}
