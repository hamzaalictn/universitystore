package com.universitystore.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonExcelReader {

    /**
     * This method reads data from an Excel file and returns it as a HashMap.
     *
     * @param testCase     the name of the test case whose data is to be retrieved
     * @param workbookName the name of the Excel workbook that contains the data
     * @param sheetName    the name of the sheet in the workbook that contains the data
     * @return a HashMap containing the data read from the Excel file, with the keys being the column names and the values being the corresponding cell values
     * @throws IOException if an error occurs while reading the Excel file
     */
    public Map<String, String> getDataFromExcel(String testCase, String workbookName, String sheetName) throws IOException {

        HashMap<String, String> excelData = new HashMap<>();

        // Add Test Case Name to Excel Map
        excelData.put("Test Case :", testCase);

        try (FileInputStream inputStream = new FileInputStream("src/test/resources/TestData/" + workbookName + ".xlsx");
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            DataFormatter dataFormatter = new DataFormatter();

            int rowIndexOfTestCase = 0;
            int columnIndexOfTestCase = 0;

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (dataFormatter.formatCellValue(cell).equalsIgnoreCase("TestCase")) {
                        rowIndexOfTestCase = row.getRowNum();
                        columnIndexOfTestCase = cell.getColumnIndex();
                        break;
                    }
                }
            }

            for (int rowIndex = rowIndexOfTestCase + 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row currentRow = sheet.getRow(rowIndex);
                Cell testCaseCell = currentRow.getCell(columnIndexOfTestCase);

                if (testCaseCell != null && testCaseCell.getStringCellValue().equalsIgnoreCase(testCase)) {

                    // Iterate through the cells in the row
                    for (int columnIndex = columnIndexOfTestCase + 1; columnIndex < currentRow.getLastCellNum(); columnIndex++) {

                        Cell currentCell = currentRow.getCell(columnIndex);
                        Cell headerCell = sheet.getRow(rowIndexOfTestCase).getCell(columnIndex);

                        if (currentCell != null && headerCell != null) {
                            String key = dataFormatter.formatCellValue(headerCell);
                            String value = dataFormatter.formatCellValue(currentCell);
                            excelData.put(key, value);
                        }
                    }
                    break;
                }
            }
        }


