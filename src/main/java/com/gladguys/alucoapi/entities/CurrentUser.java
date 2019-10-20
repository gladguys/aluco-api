package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.NonNull;

public @Data class CurrentUser {
	@NonNull private String token;
	@NonNull private User user;
}
