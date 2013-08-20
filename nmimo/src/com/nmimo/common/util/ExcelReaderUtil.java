package com.nmimo.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <pre>
 *
 * </pre>
 * @author Rejoice
 *
 */
public class ExcelReaderUtil {

	private static final Logger log = Logger.getLogger(ExcelReaderUtil.class);

	/* Poi Excel 2010 이후 사용 */
	public final static Vector<Vector> simpleExcelReadPoi(File excelFile)
			throws Exception {
		
		FileInputStream excelFIS = new FileInputStream(excelFile);
		Vector<Vector> excelRows = new Vector();
		// Create an Excel Workbook Object using the FileInputStream created
		// above (which contains our file).
		// Use error handling around its creation in case of Input/Output
		// Exception

		XSSFWorkbook excelWB = new XSSFWorkbook(excelFIS);

		// Next, get information out of that Workbook.
		// Start by getting the Spreadsheet (Excel books can have several
		// sheets). Assuming there is just one sheet, it's the zero sheet.

		XSSFSheet topSheet = excelWB.getSheetAt(0);

		// getRow() returns an XSSFRow object, but the numbering
		// system is logical, not physical, and zero based.
		// e.g. use getRow(2) to get the third row.

		XSSFRow thirdRow = topSheet.getRow(2);

		// Get the first two cells in the row
		XSSFCell lastnameCell = thirdRow.getCell(0);
		XSSFCell firstnameCell = thirdRow.getCell(1);

		// Get the string information in the cells
		// String firstName = firstnameCell.getStringCellValue();
		// String lastName = lastnameCell.getStringCellValue();

		// Print out the value of the cells
		// log.debug(firstName + " " + lastName);

		// Traverse the sheets by looping through sheets, rows, and cells.
		// Remember, excelWB is the workbook object obtained earlier.
		// Outer Loop: Loop through each sheet
		// for (int sheetNumber = 0; sheetNumber < excelWB.getNumberOfSheets(); sheetNumber++) {
		XSSFSheet oneSheet = excelWB.getSheetAt(0);

		// Now get the number of rows in the sheet
		int rows = oneSheet.getPhysicalNumberOfRows();
		// Middle Loop: Loop through rows in the sheet
		
		String data = null;
		
		for (int rowNumber = 0; rowNumber < rows; rowNumber++) {
			Vector excelRow = new Vector();
			XSSFRow oneRow = oneSheet.getRow(rowNumber);
			
			// Skip empty (null) rows.
			if (oneRow == null) {
				excelRows.add(excelRow);
				continue;
			}

			// Get the number of cells in the row
			int cells = oneRow.getPhysicalNumberOfCells();

			// Inner Loop: Loop through each cell in the row

			for (int cellNumber = 0; cellNumber < cells; cellNumber++) {
				XSSFCell oneCell = oneRow.getCell(cellNumber);

				// Test the value of the cell.
				// Based on the value type, use the proper
				// method for working with the value.

				// If the cell is blank, the cell object is null, so
				// don't
				// try to use it. It will cause errors.
				// Use continue to skip it and just keep going.
				if (oneCell == null) {
					continue;
				}
				switch (oneCell.getCellType()) {

				case XSSFCell.CELL_TYPE_STRING:
//					log.debug(oneCell.getStringCellValue());
					data = String.valueOf(oneCell.getStringCellValue());
					break;

				case XSSFCell.CELL_TYPE_FORMULA:
//					log.debug(oneCell.getCellFormula());
					data = String.valueOf(oneCell.getCellFormula());
					break;

				case XSSFCell.CELL_TYPE_NUMERIC:
//					log.debug(oneCell.getNumericCellValue());
					data = String.valueOf(oneCell.getNumericCellValue());
					break;

				case XSSFCell.CELL_TYPE_ERROR:
//					log.debug("Error!");
					break;

				}
				excelRow.add(data);
				// End Inner Loop
			}
			excelRows.add(excelRow);
			// End Middle Loop
		}
		// End Outer Loop
		// }
		return excelRows;
	}
	
	public static void main(String[] args) {
		try {
			Vector<Vector> v = ExcelReaderUtil.simpleExcelReadPoi(new File("c:/xlsxTest.xlsx"));
			System.out.println(v.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}