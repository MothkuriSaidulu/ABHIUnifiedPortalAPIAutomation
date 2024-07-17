package com.abhi.utilities;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData extends TestBase  {

//	public static HashMap<String, String> mapData;
	public static FileInputStream fileInput;
	public static XSSFWorkbook workBook;
	public static XSSFSheet currentSheet;
	public static Iterator<Row> ItRows;
	public static Iterator<Cell> itCell;
	public static Row row;
	public static Cell cell;
	public static String value;
	public static String filepath;

	public static ArrayList<String> getExcellRowData(String SheetName, String testCaseID) throws Exception {

		ArrayList<String> arrayList = new ArrayList<String>();
		
	 	String configPath = ConfigReader.getProperties("ReadExcell");
	 	filepath = rootPath + configPath;
		
		
//		File filePath = new File(".\\src\\test\\resources\\TestData\\MozartTestData.xlsx");
		
		fileInput = new FileInputStream(filepath);
		workBook = new XSSFWorkbook(fileInput);
		int noOfSheets = workBook.getNumberOfSheets();

		try {

			for (int i = 0; i < noOfSheets; i++) {
				if (workBook.getSheetName(i).equalsIgnoreCase(SheetName)) {
					currentSheet = workBook.getSheetAt(i);
					int noOfRows = currentSheet.getPhysicalNumberOfRows();
//					System.err.println(" No Of Rows in a Sheet :  " + noOfRows);
					ItRows = currentSheet.iterator();
					row = ItRows.next();
					int noOfColumns = row.getPhysicalNumberOfCells();
//					System.out.println(" No Of Column in the Row : " + noOfColumns);
					itCell = row.cellIterator();

					/*
					 * Gets the Desired sheetname
					 */

	
					int k = 0;
					int column = 0;
					while (itCell.hasNext()) {
						cell = itCell.next();
						if (cell.getStringCellValue().equalsIgnoreCase("TestCaseID")) { // Header column name
							column = k;
							break;
						}
						k++;
					}


					/*
					 * get the TestcaseID row
					 * 
					 */

					while (ItRows.hasNext()) {
						row = ItRows.next();
						if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseID)) {

							/*
							 * After we got our test case pull the all test data from cells
							 */

							itCell = row.cellIterator();
							while (itCell.hasNext()) {
								cell = itCell.next();
								if (cell.getCellTypeEnum() == CellType.STRING) {
									value = cell.getStringCellValue();
									arrayList.add(value);
								} else {
									value = NumberToTextConverter.toText(cell.getNumericCellValue());
									arrayList.add(value);

								}
							}
						}

					}

				}
				workBook.close();
				fileInput.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;

	}

	public static String getCellData(String sheetName, String TestCaseID, String columName) {
		ArrayList<String> arrayData = null;
		String data = null;

		try {
			arrayData = getExcellRowData(sheetName, TestCaseID);
			ItRows = currentSheet.iterator();
			row = ItRows.next();
			itCell = row.iterator();

			int k = 0;
			int column = 0;

			while (itCell.hasNext()) {
				cell = itCell.next();

				if (cell.getStringCellValue().equalsIgnoreCase(columName)) {
					column = k;
					break;
				}
				k++;
			}
			data = arrayData.get(column); // desired column name

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}


}
