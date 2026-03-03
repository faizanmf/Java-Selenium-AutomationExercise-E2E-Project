package com.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Map<String, Integer> headerMap;

    public void excelSetup(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        loadHeaders();
        fis.close();
    }

    private void loadHeaders() {
        headerMap = new HashMap<>();
        Row headerRow = sheet.getRow(0);

        if (headerRow == null) {
            throw new RuntimeException("Header row is missing!");
        }

        for (Cell cell : headerRow) {
            headerMap.put(
                    cell.getStringCellValue().trim(),
                    cell.getColumnIndex()
            );
        }
    }

    public String getData(String header, int rowNum) {

        if (!headerMap.containsKey(header)) {
            throw new RuntimeException("Header not found: " + header);
        }

        int colIndex = headerMap.get(header);
        Row row = sheet.getRow(rowNum);

        if (row == null) return "";

        Cell cell = row.getCell(colIndex);

        if (cell == null) return "";

        return getCellValue(cell);
    }

    private String getCellValue(Cell cell) {

        DataFormatter formatter = new DataFormatter();

        switch (cell.getCellType()) {
            case STRING:
            case NUMERIC:
            case BOOLEAN:
            case FORMULA:
                return formatter.formatCellValue(cell).trim();

            case BLANK:
            default:
                return "";
        }
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();  // Correct row count
    }

    public void close() {
        try {
            if (workbook != null)
                workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}