package com.gladguys.alucoapi.entities.enums;

public enum StatusEnum {
	PRESENTE(0),
	FALTA(1),
	FALTA_JUSTIFICADA(2),
	FERIADO(3);

	Long id;

	StatusEnum(Long id) {
		this.id = id;
	}

	public static StatusEnum getStatus(Long id) {
		switch (id) {
			case 0: return PRESENTE;
			case 1: return FALTA;
			case 2: return FALTA_JUSTIFICADA;
			case 3: return FERIADO;
			default: return FALTA;
		}
	}

	public String getDescription() {
		switch (this) {
			case PRESENTE: return "Presente";
			case FALTA: return "Falta";
			case FALTA_JUSTIFICADA: return "Falta Justificada";
			case FERIADO: return "Feriado";
			default: return "Falta";
		}
	}
}
