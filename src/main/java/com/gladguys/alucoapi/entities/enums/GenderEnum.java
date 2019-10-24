package com.gladguys.alucoapi.entities.enums;

public enum GenderEnum {
	MALE(0),
	FEMALE(1);

	Integer id;

	GenderEnum(Integer id) {
		this.id = id;
	}

	public static GenderEnum getGender(Integer id) {
		switch (id) {
			case 0: return MALE;
			case 1: return FEMALE;
			default: return MALE;
		}
	}

	public String getDescription() {
		switch (this) {
			case MALE: return "Male";
			case FEMALE: return "Female";
			default: return "Male";
		}
	}
}
