package com.reservationapp.service;

import com.reservationapp.entity.Passenger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelGeneratorService {
    public byte[] generateExcel(List<Passenger> passengers) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Passenger Data");



            private void createHeaderRow(Sheet sheet) {
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("First Name");
                headerRow.createCell(2).setCellValue("Last Name");
                headerRow.createCell(3).setCellValue("Email");
                headerRow.createCell(4).setCellValue("Mobile");
                headerRow.createCell(5).setCellValue("Bus ID");
                headerRow.createCell(6).setCellValue("Route ID");


//            createHeaderRow(sheet);
            int rowNum = 1;
            for (Passenger passenger : passengers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(passenger.getId());
                row.createCell(1).setCellValue(passenger.getFirstName());
                row.createCell(2).setCellValue(passenger.getLastName());
                row.createCell(3).setCellValue(passenger.getEmail());
                row.createCell(4).setCellValue(passenger.getMobile());
                row.createCell(5).setCellValue(passenger.getBuId());
                row.createCell(6).setCellValue(passenger.getRouteId());
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    }
}
