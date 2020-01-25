package com.gladguys.alucoapi.component;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

@Component
public class ReportGenerate {

    @Autowired
    private DataSource dataSource;

    public ReportGenerate() {
    }

    public byte[] generate(String reportName, Map<String, Object> parameters) throws JRException, SQLException {
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/" + reportName);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public ResponseEntity exportPDF(byte[] report) {
        return this.exportPDF(report, "report");
    }

    public ResponseEntity exportPDF(byte[] report, String exportName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(exportName + ".pdf", exportName + ".pdf");
        headers.setCacheControl("no-cache, no-store, must-revalidate");

        ResponseEntity<byte[]> response = new ResponseEntity<>(report, headers, HttpStatus.OK);

        return response;
    }
}
