package com.gladguys.alucoapi.entities.enums;

public enum ClassStatus {
	CREATED(0),
	STARTED(1),
	CLOSED(2);

	Integer id;

	ClassStatus(Integer id) {
		this.id = id;
	}

	public static ClassStatus getStatus(Integer id) {
		switch (id) {
			case 0: return CREATED;
			case 1: return STARTED;
			case 2: return CLOSED;
			default: return null;
		}
	}
}
