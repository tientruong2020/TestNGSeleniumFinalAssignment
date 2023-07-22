package org.example.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    public static Object[][] getDataFromExcel() throws IOException {
        String[][] arrayData = null;
        FileInputStream fileIS = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\resources\\test_data\\fail_test_data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileIS);

        // Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        int totalRow = sheet.getPhysicalNumberOfRows();
        int totalCol = 4;
        arrayData = new String[totalRow - 1][totalCol - 1];

        for (int row = 1; row < totalRow; ++row) {
            for (int col = 1; col < totalCol; ++col) {
                if (sheet.getRow(row).getCell(col).getCellType() == CellType.NUMERIC) {
                    arrayData[row - 1][col - 1] = Long.toString(Math.round(sheet.getRow(row).getCell(col).getNumericCellValue()));
                } else {
                    arrayData[row - 1][col - 1] = sheet.getRow(row).getCell(col).getStringCellValue();
                }
            }
        }
        return (arrayData);

    }
}
