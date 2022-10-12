package week5.assignment;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public static String[][] readSheetData(String excelName)throws IOException {
		//Go to workbook
		XSSFWorkbook workbook=new XSSFWorkbook("serviceNowExcel/"+excelName+".xlsx");
		//go to sheet
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		//get row count
		int rowCount = sheet.getLastRowNum();
		//get column count
		short cellCount = sheet.getRow(0).getLastCellNum();
		//new
		String[][] data=new String[rowCount][cellCount];
		
		//get all row values
		for (int i = 1; i <=rowCount ; i++) {
			XSSFRow row = sheet.getRow(i);
			//get all column values
			for (int j = 0; j <cellCount ; j++) {
				XSSFCell cellvalue = row.getCell(j);
				//get values to print from column and row
				String stringCellValue = cellvalue.getStringCellValue();
				System.out.println(stringCellValue);
				data[i-1][j]=stringCellValue;
			}
		}
		workbook.close();
		return data;
	}
}


