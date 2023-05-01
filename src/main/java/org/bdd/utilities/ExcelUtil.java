package org.bdd.utilities;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public void readExcel() throws IOException {
		File file= new File("C:dummy.xlsx");
		FileInputStream excel = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(excel);
		XSSFSheet sheet= workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell =row.getCell(0);
		String value =cell.getStringCellValue();
		Log.info(value);
		
		workbook.close();
		excel.close();
	}
	
}
