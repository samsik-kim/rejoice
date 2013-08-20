package com.nmimo.common.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelExportView extends AbstractExcelView {
	/** 파일명 키*/
	public static final String FILE_NAME = "fileName"; // 파일명
	
	/** Data List 키 */
	public static final String DATA_LIST = "list"; // Data List
	
	/** Excel헤더목록 키 */
	public static final String HEADER = "header"; // Excel헤더목록
	
	/** Column 목록 키 */
	public static final String COLUMN = "columnList"; // Column 목록
	
	/** SHEET 명 */
	public static final String SHEET_NAME = "sheet"; // SHEET 명
	
	/** style 정의 */
	private HSSFCellStyle style ;    
	/**
	 * <pre>
	 * EXCEL의 필요한 정보로 하여 EXCEL 파일을 구성하는 Function 이다.
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map data, HSSFWorkbook wookbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = (Map) data.get("excelExportView");
		String fileName = (String) model.get(ExcelExportView.FILE_NAME); // excel
		// export시
		// 파일명
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

		List<Map> list = (List) model.get(ExcelExportView.DATA_LIST);

		String[] header = null;
		String[] columnList = null;
		String sheetName = "";
		if (model.containsKey(ExcelExportView.SHEET_NAME) && model.get(ExcelExportView.SHEET_NAME) != null) {
			sheetName = (String) model.get(ExcelExportView.SHEET_NAME);
		}

		HSSFSheet sheet = createFirstSheet(wookbook, sheetName);
		if (model.containsKey(ExcelExportView.COLUMN)) {
			columnList = (String[]) model.get(ExcelExportView.COLUMN);
		} else {
			Map row = (Map) list.get(0);
			Set keySet = row.keySet();
			Iterator<String> it = keySet.iterator();
			String[] columnTemp = new String[keySet.size()];
			int columnCount = 0;
			while (it.hasNext()) {
				columnTemp[columnCount++] = (String) it.next();
			}
			columnList = columnTemp;
		}

		if (model.containsKey(ExcelExportView.HEADER)) {
			header = (String[]) model.get(ExcelExportView.HEADER);
		} else {
			header = columnList;
		}
		createHeader(sheet, header);
		int rowNum = 1;
		for (Map map : list) {
			createRow(sheet, map, columnList, rowNum++);
		}

	}

	/**
	 * 
	 * <pre>
	 * Excel 파일의 Sheet 를 생성한다
	 * </pre>
	 * 
	 * @param workbook Excel 정보
	 * @return Excel Sheet
	 */
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook, String sheetName) {
		String sheetName1 = "Sheet1";
		
		style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		if (sheetName != null && !sheetName.equals("")) {
			sheetName1 = sheetName;
		}
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName1);
		return sheet;
	}

	/**
	 * 
	 * <pre>
	 * Excel의 Header를 구성한다.
	 * </pre>
	 * 
	 * @param sheet Excel Sheet
	 * @param header Excel Header
	 */
	private void createHeader(HSSFSheet sheet, String[] header) {
		if (header != null && header.length > 0) {
			HSSFRow firstRow = sheet.createRow(0);
			HSSFCell cell = null;
			int cellNum = 0;

			for (String title : header) {
				cell = firstRow.createCell((short) cellNum++);
				cell.setCellValue(new HSSFRichTextString(title));
				cell.setCellStyle(style);
			}
		}

	}

	/**
	 * 
	 * <pre>
	 * Excel Export 시 Row Data로 Cell을 구성한다.
	 * </pre>
	 * 
	 * @param sheet 엑셀Sheet
	 * @param map Row Data
	 * @param rowNum Row 넘버
	 */
	private void createRow(HSSFSheet sheet, Map map, String[] columnList, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		int columnCount = 0;
		HSSFCell cell = null;

		for (String key : columnList) {
			cell = row.createCell((short) columnCount++);
			Object tempValue = map.get(key);
			if (tempValue == null) {
				tempValue = new String("");
			}
			cell.setCellValue(new HSSFRichTextString(tempValue.toString()));
		}

	}

}
