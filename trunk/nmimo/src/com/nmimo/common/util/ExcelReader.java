package com.nmimo.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReader {
	private Workbook workbook = null;

	public ExcelReader(String file) throws BiffException, IOException {
		this.workbook = Workbook.getWorkbook(new File(file));
	}
	
	//HashMap 반환
	public HashMap[] readHashArray() {
		int sheetCount = 0;
		HashMap[] excelRows = (HashMap[]) null;

		Sheet sheet = this.workbook.getSheet(sheetCount);
		excelRows = new HashMap[sheet.getRows()];
		Cell[] row = (Cell[]) null;

		for (int i = 0; i < excelRows.length; ++i) {
			excelRows[i] = new HashMap();
			row = sheet.getRow(i);
			for (int j = 0; j < row.length; ++j) {
				excelRows[i].put(new Integer(j), row[j].getContents());
			}
		}
		return excelRows;
	}
	
	//Vector로 반환
	public Vector<Vector> readVector(){
		int sheetCount = 0;
		
		Sheet sheet = this.workbook.getSheet(sheetCount);
		Vector<Vector> excelRows = new Vector();
		
		Cell[] row = (Cell[]) null;

		for (int i = 0; i < sheet.getRows(); ++i) {
			Vector excelRow = new Vector();
			row = sheet.getRow(i);
			for (int j = 0; j < row.length; ++j) {
				excelRow.add(row[j].getContents());
			}
			excelRows.add(excelRow);
		}
		return excelRows;
	}

	public String readColum(String columName) {
		Sheet sheet = this.workbook.getSheet(0);

		Cell cell = sheet.getCell(columName);
		String tmp = cell.getContents();
		return tmp;
	}

	public void close() {
		if (this.workbook != null)
			this.workbook.close();
	}

}