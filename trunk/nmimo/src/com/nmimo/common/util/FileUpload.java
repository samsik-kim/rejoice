package com.nmimo.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
 * <pre>
 *
 * </pre>
 * @file FileUpload.java
 * @since 2013. 7. 23.
 * @author Administrator
 */
public class FileUpload {

	Log logger = LogFactory.getLog(getClass());

	public FileUpload() {
		super();
	}
	
	/**
	 * <pre>
	 * Upload File Delete
	 * </pre>
	 * @param savePath 저장 하위 경로
	 * @param fileName 파일명
	 * @return 성공여부 true, false
	 */
	public boolean uploadDeleteFile(String uploadDir, String savePath, String fileName, String pathKey) {

		// 업로드 폴더 경로가 없다면
		if (uploadDir == null || uploadDir.equals("")) {
			return false;
		}
		
		if (savePath == null || savePath.equals("")) {
			return false;
		}
				
		// 업로드 하위 경로
		uploadDir += File.separator + savePath + File.separator;

		try {
			File f = new File(uploadDir + fileName);
			if (f.isFile() && f.exists()) {
				f.delete();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * <pre>
	 * Upload File Delete
	 * </pre>
	 * @param savePath 저장 하위 경로
	 * @param fileName 파일명
	 * @return 성공여부 true, false
	 */
	public boolean uploadDeleteFile2(String uploadDir, String savePath, String fileName, String pathKey) {

		// 업로드 폴더 경로가 없다면
		if (uploadDir == null || uploadDir.equals("")) {
			return false;
		}
		
		if (savePath == null || savePath.equals("")) {
			return false;
		}
				
		// 업로드 하위 경로
		uploadDir += File.separator + savePath + File.separator;

		try {
			File f = new File(uploadDir + fileName);
			if (f.isFile() && f.exists()) {
				f.delete();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * <pre>
	 * 파일 upload
	 * </pre>
	 * @param request
	 * @param tmpPath
	 * @param newPath
	 * @return
	 */
	public ArrayList<HashMap> upload(HttpServletRequest request, String tmpPath, String newPath, String type) {
		
		//업로드경로 형식 ( pathKey +yyyy/mm )
		String yyyymm = DateUtils.getToday("yyyy/MM");
		
		//D = doc Default
		//M = img, mov
		 if("M".equals(type)){
			return uploadImg(request, yyyymm, "jpg,gif,mp4", tmpPath);
		}else{
			return upload(request, yyyymm, "xls,txt", "", tmpPath, newPath);
		}
	}


	/**
	 * <pre>
	 * 파일 upload
	 * </pre>
	 * @param request
	 * @param savePath
	 * @param allowExt
	 * @param fileType
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public ArrayList<HashMap> upload(HttpServletRequest request, String savePath, String allowExt, String fileType, String oldPath, String newPath) {

		ArrayList<HashMap> aList = new ArrayList<HashMap>();
		// 업로드 폴더
		String oldPathDir = oldPath;
		String newPathDir = newPath;
		
		System.err.println("oldPathDir >>> "+oldPathDir);
		System.err.println("newPathDir >>> "+newPathDir);
		
		
		// 업로드 폴더 경로가 없다면
		if (oldPathDir == null || oldPathDir.equals("") || newPathDir == null || newPathDir.equals("")) {
			HashMap<String, String> hMap = new HashMap<String, String>();

			System.err.println("폴더생성 오류 >>> ");
			
			
			hMap.put("resultCode", "F");
			hMap.put("resultMessage", "업로드 폴더 지정 오류");

			aList.add(hMap);
			return aList;
		}

		// 업로드 하위 경로
		if(!"".equals(savePath)){
			oldPathDir += savePath + File.separator;	
			newPathDir += savePath + File.separator;
		}
		
		// 디렉토리 만들기(old , new 동시생성)
		File fDir1 = new File(oldPathDir);
		File fDir2 = new File(newPathDir);
		if (!fDir1.exists()){
			fDir1.mkdirs();
		}
		if (!fDir2.exists()){
			fDir2.mkdirs();
		}
		
		MultipartRequest multipartReq = null;
		if(request instanceof MultipartRequest) {
			multipartReq = (MultipartRequest) request;
		} else {
			return aList;
		}
		

		Iterator itFile = multipartReq.getFileNames();

		// 확장자 체크 용도
		if (allowExt == null || allowExt.equals("")) {
			allowExt = "xls,txt";
		}
		allowExt = allowExt.toLowerCase();

		String[] aStr = allowExt.split(",");

		try {
			while (itFile.hasNext()) {
				String file_name = (String) itFile.next();

				MultipartFile multipartFile = multipartReq.getFile(file_name);
				
				if (!multipartFile.isEmpty()) {
					// 원본명
					String originalFilename = multipartFile.getOriginalFilename();
					// input 태그 이름
					String inputName = multipartFile.getName();
					// 파일용량
					long lFileSize = multipartFile.getSize();
					
					// 파일 확장자 ( "." 제외 )
					String fileExt = "";
					if (originalFilename.indexOf(".") >= 0) {
						fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
						fileExt = fileExt.toLowerCase(); //소문자로 변경
					}
					
					// 확장자 체크
					boolean bExtCheck = false;
					for (int i = 0; i < aStr.length; i++) {
						if (aStr[i].equals(fileExt)) {
							bExtCheck = true;
							break;
						}
					}
					
					if (bExtCheck) {
						
						String ymdhms = DateUtils.getDate2String(DateUtils.getDate(), "yyyyMMddHHmmss");
						
						//파일 형식이 "" 아니면 
						if(!"".equals(fileType)){
							ymdhms = fileType + "_" + ymdhms;
						}
						
						
						System.err.println("ymdhms >>> "+ymdhms);
						System.err.println("fileExt >>> "+fileExt);
						System.err.println("oldPathDir >>> "+oldPathDir);
						
						File tempFile = File.createTempFile(ymdhms + "_", "." + fileExt, new File(oldPathDir));
						multipartFile.transferTo(tempFile);
						
						HashMap<String, String> hMap = new HashMap<String, String>();
						
						hMap.put("resultCode", "S");
						hMap.put("oldPathDir", oldPathDir);
						hMap.put("newPathDir", newPathDir);
						hMap.put("resultMessage", "SUCCESS");
						// input 태그 이름
						hMap.put("inputName", inputName);
						// 저장된 파일명
						hMap.put("fileRealName", tempFile.getName());
						// 원본파일명
						hMap.put("fileOrgName", originalFilename);
						// 파일용량
						hMap.put("fileSize", "" + lFileSize);
						//확장자
						hMap.put("fileExt", fileExt);
						//파일full path
						hMap.put("fileFullPath", oldPathDir+tempFile.getName());
						aList.add(hMap);
						
					} else {
						
						HashMap<String, String> hMap = new HashMap<String, String>();
						
						hMap.put("resultCode", "F");
						hMap.put("resultMessage", "허용하는 확장자 아님");
						// input 태그 이름
						hMap.put("inputName", inputName);
						// 원본파일명
						hMap.put("fileOrgName", originalFilename);
						// 파일용량
						hMap.put("fileSize", "" + lFileSize);
						aList.add(hMap);
						
					}
				}
//				else {
					// 비어있는 파일
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "F");
			hMap.put("resultMessage", "업로드 에러");

			aList.add(hMap);
			return aList;
		}

		return aList;
	}
	
	
	/**
	 * <pre>
	 * 파일 upload ( IMG / MOV )
	 * </pre>
	 * @param request
	 * @param savePath
	 * @param allowExt
	 * @param tmpPath
	 * @return
	 */
	public ArrayList<HashMap> uploadImg(HttpServletRequest request, String savePath, String allowExt, String tmpPath) {
		
		ArrayList<HashMap> aList = new ArrayList<HashMap>();
		
		// 업로드 폴더
		String pathDir = tmpPath;
		
		// 업로드 폴더 경로가 없다면
		if (pathDir == null || pathDir.equals("") ) {
			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "F");
			hMap.put("resultMessage", "업로드 폴더 지정 오류");

			aList.add(hMap);
			return aList;
		}

		// 업로드 하위 경로
//		if(!"".equals(savePath)){
//			pathDir += savePath + File.separator;	
//		}
		
		// 디렉토리 만들기
		File fDir1 = new File(pathDir);
		if (!fDir1.exists()){
			fDir1.mkdirs();
		}
		
		MultipartRequest multipartReq = null;
		if(request instanceof MultipartRequest) {
			multipartReq = (MultipartRequest) request;
		} else {
			return aList;
		}

		Iterator itFile = multipartReq.getFileNames();

		// 확장자 체크 용도
		if (allowExt == null || allowExt.equals("")) {
			allowExt = "jpg,gif,mp4";
		}
		allowExt = allowExt.toLowerCase();

		String[] aStr = allowExt.split(",");

		try {
			while (itFile.hasNext()) {
				String file_name = (String) itFile.next();

				MultipartFile multipartFile = multipartReq.getFile(file_name);
				
				if (!multipartFile.isEmpty()) {
					String originalFilename = multipartFile.getOriginalFilename();	// 원본명
					String inputName = multipartFile.getName();	// input 태그 이름
					long lFileSize = multipartFile.getSize();	// 파일용량
					
					// 파일 확장자 ( "." 제외 )
					String fileExt = "";
					if (originalFilename.indexOf(".") >= 0) {
						fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
						fileExt = fileExt.toLowerCase(); //소문자로 변경
					}
					
					// 확장자 체크
					boolean bExtCheck = false;
					for (int i = 0; i < aStr.length; i++) {
						if (aStr[i].equals(fileExt)) {
							bExtCheck = true;
							break;
						}
					}
					
					if (bExtCheck) {
						
						String ymdhms = DateUtils.getDate2String(DateUtils.getDate(), "yyyyMMddmmss");
						File tempFile = File.createTempFile(ymdhms + "_", "." + fileExt, new File(pathDir));
						multipartFile.transferTo(tempFile);
						HashMap<String, String> hMap = new HashMap<String, String>();
						
						hMap.put("resultCode", "S");
						hMap.put("pathDir", pathDir);
						hMap.put("resultMessage", "SUCCESS");
						hMap.put("inputName", inputName);	// input 태그 이름
						hMap.put("fileRealName", tempFile.getName());	// 저장된 파일명
						hMap.put("fileOrgName", originalFilename);	// 원본파일명
						hMap.put("fileSize", "" + lFileSize);	// 파일용량
						hMap.put("fileExt", fileExt);	//확장자
						hMap.put("fileFullPath", pathDir+tempFile.getName());	//파일full path
						aList.add(hMap);
						
					} else {
						
						HashMap<String, String> hMap = new HashMap<String, String>();
						hMap.put("resultCode", "F");
						hMap.put("resultMessage", "허용하는 확장자 아님");
						hMap.put("inputName", inputName);	// input 태그 이름
						hMap.put("fileOrgName", originalFilename);	// 원본파일명
						hMap.put("fileSize", "" + lFileSize);	// 파일용량
						aList.add(hMap);
						
					}
				}
//				else {
					// 비어있는 파일
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "F");
			hMap.put("resultMessage", "업로드 에러");

			aList.add(hMap);
			return aList;
		}

		return aList;
	}
	
	
	/**
	 * <pre>
	 * File Upload Original File Name
	 * </pre>
	 * @param request
	 * @param savePath 저장 하위 경로
	 * @param limitSize 제한크기
	 * @return 업로드 파일 정보
	 */
	public ArrayList<HashMap> uploadFileOrgnNm(HttpServletRequest request, String uploadDir, String savePath) {
		return uploadFileOrgnNm(request, uploadDir, savePath, 2048*1024); 
	}
	
	/**
	 * <pre>
	 * File Upload Original File Name
	 * </pre>
	 * @param request
	 * @param savePath 저장 하위 경로
	 * @param limitSize 제한크기
	 * @return 업로드 파일 정보
	 */
	public ArrayList<HashMap> uploadFileOrgnNm(HttpServletRequest request, String uploadDir, String savePath, int limitSize) {

		ArrayList<HashMap> aList = new ArrayList<HashMap>();

		// 업로드 폴더 경로가 없다면
		if (uploadDir == null || uploadDir.equals("")) {
			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "F");
			hMap.put("resultMessage", "업로드 폴더 지정 오류");

			aList.add(hMap);
			return aList;
		}

		// 업로드 하위 경로
		uploadDir += File.separator + savePath + File.separator;

		
		// 디렉토리 만들기
		File fDir = new File(uploadDir);
		if (!fDir.exists())
			fDir.mkdirs();

		MultipartRequest multipartReq = (MultipartRequest) request;

		Iterator itFile = multipartReq.getFileNames();

		try {
			while (itFile.hasNext()) {
				String file_name = (String) itFile.next();
				MultipartFile multipartFile = multipartReq.getFile(file_name);
				if (!multipartFile.isEmpty()) {
					// input 태그 이름
					String inputName = multipartFile.getName();
				
				
					// 원본명
					String originalFilename = multipartFile.getOriginalFilename();
					// 파일용량
					long lFileSize = multipartFile.getSize();

					// 파일 확장자 ( "." 제외 )
					String fileExt = "";
					if (originalFilename.indexOf(".") >= 0) {
						fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
						fileExt = fileExt.toLowerCase(); //소문자로 변경
					}
					// 파일명 (".확장자" 제외)
					String fileNm = originalFilename;
					if (originalFilename.indexOf(".") >= 0) {
						fileNm = originalFilename.substring(0,originalFilename.lastIndexOf("."));
					}

					// 파일용량 체크
					boolean bExtCheck = false;
					if(lFileSize<=limitSize){
						bExtCheck = true;
					}
					if (bExtCheck) {

						String fileName = getFileName(uploadDir,fileNm,fileExt);

						File tempFile =  new File(uploadDir+File.separator+fileName);
						multipartFile.transferTo(tempFile);

						HashMap<String, String> hMap = new HashMap<String, String>();

						hMap.put("resultCode", "S");
						hMap.put("resultMessage", "SUCCESS");
						// input 태그 이름
						hMap.put("inputName", inputName);
						// 저장된 파일명
						hMap.put("fileRealName", tempFile.getName());
						// 원본파일명
						hMap.put("fileOrgName", originalFilename);
						// 파일용량
						hMap.put("fileSize", "" + lFileSize);

						aList.add(hMap);
				
					} else {

						HashMap<String, String> hMap = new HashMap<String, String>();

						hMap.put("resultCode", "F");
						hMap.put("resultMessage", "2048kb이상 등록하실수없습니다.");
						// input 태그 이름
						hMap.put("inputName", inputName);
						// 원본파일명
						hMap.put("fileOrgName", originalFilename);
						// 파일용량
						hMap.put("fileSize", "" + lFileSize);
						aList.add(hMap);

					}
				}
				//else{
				// 비어있는 파일
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "F");
			hMap.put("resultMessage", "업로드 에러");

			aList.add(hMap);
			return aList;
		}

		return aList;
	}
	

	/**
	 * <pre>
	 * Gets the file name.
	 * </pre>
	 * @param fileStorigePath the file storige path
	 * @param filePrefix the file prefix
	 * @param ctsTokenId the cts token id
	 * @param ext the ext
	 * @return the file name
	 */
	public String getFileName(String filePath,String fileNm, String ext) {
		File tempFile;
		String fileName = "";
		for(int j = 0; j < 100; j++) {
			if(ext != null && !"".equals(ext)) {
				if(j==0){
					tempFile = new File(filePath+File.separator+ fileNm + "." + ext);
				}else{
					tempFile = new File(filePath+File.separator+ fileNm + "_" + String.format("%02d", j) + "." + ext);
				}
				if(!tempFile.exists()) {
					fileName = tempFile.getName();
					break;
				}
			}
		}
		return fileName;
	}
	
	public static void main(String[] args) throws IOException {
		
		File dir1 = new File(".");
		File dir2 = new File("..");
		
		System.out.println("Current Dir => "+ dir1.getCanonicalPath());
		System.out.println("Parent Dir => "+ dir2.getCanonicalPath());
		
		String path = FileUpload.class.getCanonicalName().toString()+"\temp";
		System.out.println(dir1.getAbsolutePath()); 
		System.out.println("Path => "+path);
		
//		File file = new File("./");
//		for(String fileName : file.list()){
//			System.err.println(fileName);
//		}
		
		System.err.println(new File("").getCanonicalPath()+"\\upload\\temp");
		System.err.println(new File("").getCanonicalPath());
		
	}
}
