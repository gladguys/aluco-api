package com.gladguys.alucoapi.exception.internalservererror;

public class ReportInternalServerErrorException extends RuntimeException {

	public ReportInternalServerErrorException(String reportName) {
		super("Erro ao gerar o relatório: " + reportName);
	}
}
