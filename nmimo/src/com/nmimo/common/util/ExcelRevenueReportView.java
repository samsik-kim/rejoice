package com.nmimo.common.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelRevenueReportView extends AbstractExcelView {

	@SuppressWarnings("deprecation")
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// POINT #1 : 엑셀에 출력할 목록을 가져온다.
		List listApplicants = (List) model.get("excelView");

		// POINT #2 : 엑셀처리를 담당할 POI 객체를 생성한다.
		HSSFSheet sheet = wb.createSheet();

		// POINT #3 : 타이틀 로우를 만들어낸다.
		HSSFRow header = sheet.createRow(0);
		header.createCell((short) 0).setCellValue("Name");
		header.createCell((short) 1).setCellValue("E-mail");
		header.createCell((short) 2).setCellValue("Gender");
		header.createCell((short) 3).setCellValue("Age");
		header.createCell((short) 4).setCellValue("University");
		header.createCell((short) 5).setCellValue("Major");
		header.createCell((short) 6).setCellValue("Reg.Date");

		// POINT #4 : 특별히 날짜 처리를 하고 싶다면 셀 스타일을 적용하게 마련해 둔다.
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

		// POINT #5 : 출력할 목록을 순회하면서 로우를 만들어낸다.
		int rowNum = 1;
		for (Iterator iter = listApplicants.iterator(); iter.hasNext();) {
			// CareersHistoryExtend history = (CareersHistoryExtend)
			// iter.next();
			//
			// HSSFRow row = sheet.createRow(rowNum++);
			// row.createCell((short)0).setCellValue(history.getFullName());
			// row.createCell((short)1).setCellValue(history.getCareersApplicationEmail());
			// row.createCell((short)2).setCellValue(history.getGender());
			// row.createCell((short)3).setCellValue(history.getDateOfBirth());
			// row.createCell((short)4).setCellValue(history.getNameOfUniversity());
			// row.createCell((short)5).setCellValue(history.getMajor());
			//
			// row.createCell((short)6).setCellValue(history.getCareersApplicationWriteday());
			// row.getCell((short)6).setCellStyle(cellStyle);
		}
	}
}
