package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

//"C:\Users\2492331\Downloads\LoginData.xlsx"
public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName){
        try{
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount-1][columnCount];
            DataFormatter formatter = new DataFormatter();
            for(int i=1 ; i<rowCount ; i++){
                Row row = sheet.getRow(i);
                for (int j = 0 ;j<columnCount ; j++ ){
                    Cell cell = row.getCell(j);
                    data[i-1][j] = formatter.formatCellValue(cell);
                }
            }
            workbook.close();
            fis.close();
            return data;

        }catch (IOException e){
            throw new RuntimeException("File not loaded properly");
        }


    }
}
