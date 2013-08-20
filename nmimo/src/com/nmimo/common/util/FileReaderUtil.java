package com.nmimo.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nmimo.common.info.FileReaderInfo;

/**
 * <pre>
 * 업로드파일 가공처리 유틸 ( 업로드-> 중복제거-> 파일 재생성)
 * </pre>
 * @file FileReaderUtil.java
 * @since 2013. 7. 2.
 * @author Administrator
 */
public class FileReaderUtil {
	
	private final static String TXT_TYPE = "txt";
	private final static String XLS_TYPE = "xls";
	private final static String XLSX_TYPE = "xlsx";
	
	private static Workbook workbook = null;

	private final static String ymdhms = DateUtils.getDate2String(DateUtils.getDate(), "yyyyMMddHHmmss");
	
	public FileReaderUtil(String fileFullPath) throws BiffException, IOException {
		this.workbook = Workbook.getWorkbook(new File(fileFullPath));
	}

	/**
	 * <pre>
	 * 확장자별 Data가져오기
	 * </pre>
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public final static HashMap<String,String> getData(String fileFullPath, String uploadPath) throws IOException, BiffException, RowsExceededException, WriteException{
		
		HashMap<String, String> resultMap =  new HashMap();
		
		String fileExt = CommonUtils.getExt(fileFullPath);
		
		if(TXT_TYPE.equals(fileExt)){
			resultMap = FileReaderUtil.txtDuplChkFile(fileFullPath,uploadPath);
		}else if(XLS_TYPE.equals(fileExt)){
			resultMap = FileReaderUtil.xmlDuplChkFile(fileFullPath,uploadPath);
		}else{
			resultMap = null;
		}
		return resultMap;
	}
	
	
	/**
	 * <pre>
	 * 확장자별 Data가져오기 -> Info담기
	 * </pre>
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public final static Vector<FileReaderInfo> getDataInfo(String fileFullPath, String uploadPath) throws IOException, BiffException, RowsExceededException, WriteException{
		
		Vector<FileReaderInfo> dataRows = new Vector();
		HashMap<String, String> resultMap =  new HashMap();
		
		String fileExt = CommonUtils.getExt(fileFullPath);
		
		if(TXT_TYPE.equals(fileExt)){
			resultMap = FileReaderUtil.txtDuplChkFile(fileFullPath,uploadPath);
			//전송가능폰모델 검사 - 이후(전송가능대상자 + 전송불가능대상자) 파일 생성
			dataRows = FileReaderUtil.getTxtData(resultMap.get("newFileFullPath"));
		}else if(XLS_TYPE.equals(fileExt)){
			resultMap = FileReaderUtil.xmlDuplChkFile(fileFullPath,uploadPath);
			//전송가능폰모델 검사 - 이후(전송가능대상자 + 전송불가능대상자) 파일 생성
			dataRows = FileReaderUtil.getExcelData(resultMap.get("newFileFullPath"));
		}else if(XLSX_TYPE.equals(fileExt)){
			//전송가능폰모델 검사 - 이후(전송가능대상자 + 전송불가능대상자) 파일 생성
			dataRows = FileReaderUtil.getExcelDataAfterVersion(fileFullPath);
		}else{
			dataRows = null;
		}
		return dataRows;
	}
	

	/**
	 * <pre>
	 * TXT형식 Data 추출
	 * </pre>
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 */
	public static Vector<FileReaderInfo> getTxtData(String fileFullPath) throws IOException{
		
		FileReader fr = new FileReader(fileFullPath);
		BufferedReader br = new BufferedReader(fr);
		Vector<FileReaderInfo> txtRows = new Vector();
		String line;
		
		while((line = br.readLine()) != null) {

			FileReaderInfo tmpInfo = new FileReaderInfo();
			String strArr[]  = StringUtils.split(line,"|");

			for(int i = 0 ; i < strArr.length ; i++ ){

				if(i==0){
					tmpInfo.setSendPhoneNum(strArr[i].toString());	
				}else if(i==1){
					tmpInfo.setSendPhoneModel(strArr[i].toString());	
				}else if(i==2){
					tmpInfo.setSendUserInfo1(strArr[i].toString());
				}else if(i==3){
					tmpInfo.setSendUserInfo2(strArr[i].toString());
				}else if(i==4){
					tmpInfo.setSendUserInfo3(strArr[i].toString());
				}else if(i==5){
					tmpInfo.setSendUserInfo4(strArr[i].toString());
				}else if(i==6){
					tmpInfo.setSendUserInfo5(strArr[i].toString());
				}else if(i==7){
					tmpInfo.setSendUserInfo6(strArr[i].toString());
				}else if(i==8){
					tmpInfo.setSendUserInfo7(strArr[i].toString());
				}else if(i==9){
					tmpInfo.setSendUserInfo8(strArr[i].toString());
				}else if(i==10){
					tmpInfo.setSendUserInfo9(strArr[i].toString());
				}else if(i==11){
					tmpInfo.setSendUserInfo10(strArr[i].toString());
				}
			}
			txtRows.add(tmpInfo);
		}

		br.close();
		fr.close();
		
		return txtRows;
	}
	
	
	/**
	 * <pre>
	 * XlS형식 Data 추출
	 * </pre>
	 * @param fileFullPath
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static Vector<FileReaderInfo> getExcelData(String fileFullPath) throws BiffException, IOException{

		int sheetCnt = 0;
		
		FileReaderUtil input = new FileReaderUtil(fileFullPath);
		Sheet sheet = workbook.getSheet(sheetCnt);
		Vector<FileReaderInfo> excelRows = new Vector();
		
		Cell[] row = (Cell[]) null;
		for (int k = 0; k < sheet.getRows(); k++) {
		
			FileReaderInfo tmpInfo = new FileReaderInfo();
			row = sheet.getRow(k);

			for(int i = 0 ; i < row.length ; i++ ){
				
				if(i==0){
					tmpInfo.setSendPhoneNum(row[i].getContents());	
				}else if(i==1){
					tmpInfo.setSendPhoneModel(row[i].getContents());	
				}else if(i==2){
					tmpInfo.setSendUserInfo1(row[i].getContents());	
				}else if(i==3){
					tmpInfo.setSendUserInfo2(row[i].getContents());	
				}else if(i==4){
					tmpInfo.setSendUserInfo3(row[i].getContents());	
				}else if(i==5){
					tmpInfo.setSendUserInfo4(row[i].getContents());	
				}else if(i==6){
					tmpInfo.setSendUserInfo5(row[i].getContents());	
				}else if(i==7){
					tmpInfo.setSendUserInfo6(row[i].getContents());	
				}else if(i==8){
					tmpInfo.setSendUserInfo7(row[i].getContents());	
				}else if(i==9){
					tmpInfo.setSendUserInfo8(row[i].getContents());	
				}else if(i==10){
					tmpInfo.setSendUserInfo9(row[i].getContents());	
				}else if(i==11){
					tmpInfo.setSendUserInfo10(row[i].getContents());	
				}	
			}	
			excelRows.add(tmpInfo);
		}
		return excelRows;
	}
	
	

	/**
	 * <pre>
	 * XlSX형식 Data 추출
	 * </pre>
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 */
	public static Vector<FileReaderInfo> getExcelDataAfterVersion(String fileFullPath) throws IOException{
		
		FileInputStream excelFIS = new FileInputStream(new File(fileFullPath));
		Vector<FileReaderInfo> excelRows = new Vector();
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

			FileReaderInfo tmpInfo = new FileReaderInfo();
			
			XSSFRow oneRow = oneSheet.getRow(rowNumber);
			
			// Skip empty (null) rows.
			if (oneRow == null) {
				excelRows.add(tmpInfo);
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
				
				if(cellNumber==0){
					tmpInfo.setSendPhoneNum(data);	
				}else if(cellNumber==1){
					tmpInfo.setSendPhoneModel(data);
				}else if(cellNumber==2){
					tmpInfo.setSendUserInfo1(data);
				}else if(cellNumber==3){
					tmpInfo.setSendUserInfo2(data);
				}else if(cellNumber==4){
					tmpInfo.setSendUserInfo3(data);
				}else if(cellNumber==5){
					tmpInfo.setSendUserInfo4(data);
				}else if(cellNumber==6){
					tmpInfo.setSendUserInfo5(data);
				}else if(cellNumber==7){
					tmpInfo.setSendUserInfo6(data);
				}else if(cellNumber==8){
					tmpInfo.setSendUserInfo7(data);
				}else if(cellNumber==9){
					tmpInfo.setSendUserInfo8(data);
				}else if(cellNumber==10){
					tmpInfo.setSendUserInfo9(data);
				}else if(cellNumber==11){
					tmpInfo.setSendUserInfo10(data);
				}						
				// End Inner Loop
			}
			excelRows.add(tmpInfo);	
			// End Middle Loop
		}
		// End Outer Loop
		// }
		return excelRows;
	}
	
	
	
	
	/**
	 * <pre>
	 * TXT파일 중복 제거 & 신규파일 생성
	 * </pre>
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String,String> txtDuplChkFile(String fileFullPath, String uploadPath) throws IOException{
		
		HashMap<String,String> map = new HashMap();

		//신규생성되는 파일확장자는 TXT 통일
		//String newFileRealName="newSendList_"+ ymdhms + "."+CommonUtils.getExt(fileFullPath);

		String newFileRealName="newSendList_"+ ymdhms + ".txt";
		uploadPath+= newFileRealName;
		String newFileFullPath = uploadPath;
		
		FileReader fr = new FileReader(fileFullPath);
		BufferedReader br = new BufferedReader(fr);
		BufferedWriter out = new BufferedWriter(new FileWriter(newFileFullPath));
		Vector keyList = new Vector();
		
		int tCnt = 0;
		int sCnt = 0;
		int fCnt = 0;
		
		String line;
		
		//파일 읽어들이기
		while((line = br.readLine()) != null) {
			
			Vector tmpList = new Vector();
			boolean chkType = true;
			String strArr[]  = StringUtils.split(line,"|");
			String chkString = strArr[0].toString();
				
			//첫번째 Line은 중복체크 없이 진행
			if(tCnt==0){
				for(int i=0 ; i < strArr.length ; i++){
					out.append(strArr[i].toString()+"|");
				}
				out.newLine();
				keyList.add(chkString);
				sCnt++;
			}else{
				
				//중복 비교값(PhoneNum) List에서 비교
				for(int i=0 ; i < keyList.size() ; i++){
					
					//List내 중복값 존재시 break
					if(chkString.equals(keyList.get(i))){
						chkType = false;
						fCnt++;
						
						break;
					}
				}
				
				//중복없을 경우 파일쓰기
				if(chkType){
					for(int i=0 ; i < strArr.length ; i++){
						out.append(strArr[i].toString()+"|");
					}
					out.newLine();
					keyList.add(chkString);
					sCnt++;
				}
			}
			tCnt++;
		}
		
		br.close();
		fr.close();
		out.close();
		
		map.put("resultCode", "S");
		map.put("fileFullPath", newFileFullPath);
		map.put("fileRealName", newFileRealName);
		map.put("tCnt", Integer.toString(tCnt));
		map.put("sCnt", Integer.toString(sCnt));
		map.put("fCnt", Integer.toString(fCnt));
		
		return map;
	}
	
	
	
	/**
	 * <pre>
	 * XLS파일 중복 제거 & 신규파일 생성 
	 * </pre>
	 * @param fileFullPath
	 * @return
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static HashMap<String,String> xmlDuplChkFile(String fileFullPath, String uploadPath) throws IOException, BiffException, RowsExceededException, WriteException{
		
		HashMap<String,String> map = new HashMap();
		
		//신규생성되는 파일확장자는 TXT 통일
		//String newFileRealName="newSendList_"+ ymdhms + "."+CommonUtils.getExt(fileFullPath);
		String newFileRealName="newSendList_"+ ymdhms + ".txt";
		
		uploadPath+= newFileRealName;
		String newFileFullPath = uploadPath;
		
		WritableWorkbook newWorkBook = null;
		WritableSheet newSheet = null;
		File newXmlFile = new File(newFileFullPath);
		Label data=null;
		
		newWorkBook = Workbook.createWorkbook(newXmlFile);
		newWorkBook.createSheet("sheet1", 0);
		newSheet = newWorkBook.getSheet(0);
		
		int sheetCnt = 0;
		int tCnt = 0;
		int sCnt = 0;
		int fCnt = 0;
		
		FileReaderUtil input = new FileReaderUtil(fileFullPath);
		Sheet sheet = workbook.getSheet(sheetCnt);
		Vector keyList = new Vector();
		Cell[] row = (Cell[]) null;

		for (int k = 0; k < sheet.getRows(); k++) {
			row = sheet.getRow(k);

			boolean chkType = true;
			String chkString = row[0].getContents();
			Vector tmpList = new Vector();
			
			if(tCnt==0){
				for(int i = 0 ; i < row.length ; i++ ){
					data = new Label(i,k,row[i].getContents());
					newSheet.addCell(data);
				}
				keyList.add(chkString);
				sCnt++;
			}else{
				
				//중복 비교값(PhoneNum) List에서 비교
				for(int i=0 ; i < keyList.size() ; i++){
					
					//List내 중복값 존재시 break
					if(chkString.equals(keyList.get(i))){
						chkType = false;
						fCnt++;
						
						break;
					}
				}
				
				//중복없을 경우 파일쓰기
				if(chkType){
					for(int i = 0 ; i < row.length ; i++ ){
						data = new Label(i,k,row[i].getContents());
						newSheet.addCell(data);
					}
					keyList.add(chkString);
					sCnt++;
				}
			}
			tCnt++;
		}
		newWorkBook.write();
		newWorkBook.close();
		
		map.put("resultCode", "S");
		map.put("fileFullPath", newFileFullPath);
		map.put("fileRealName", newFileRealName);
		map.put("tCnt", Integer.toString(tCnt));
		map.put("sCnt", Integer.toString(sCnt));
		map.put("fCnt", Integer.toString(fCnt));

		return map;
	}
	
	
	
	public static void main(String[] args) throws IOException, BiffException, RowsExceededException, WriteException {
		
		
		Vector<FileReaderInfo> vector = null;
		String uploadPath ="D:/WORK/MIMO/Project/workspace/nmimo/WebContent/upload/";
		String txtfile = "c:/txtTest.txt";
		String xlsfile = "c:/xlsTest.xls";
		String xlsxfile ="c:/xlsxTest.xlsx";
		
		vector = FileReaderUtil.getDataInfo(txtfile,uploadPath);
		System.out.println("txt size() => " + vector.size());
		vector = FileReaderUtil.getDataInfo(xlsfile,uploadPath);
		System.out.println("xls size() => " + vector.size());
		vector = FileReaderUtil.getDataInfo(xlsxfile,uploadPath);
		System.out.println("xlsx size() => " + vector.size());
		
	}
}
