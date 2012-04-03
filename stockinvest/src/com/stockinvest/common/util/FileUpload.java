package com.stockinvest.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import tframe.common.configuration.ConfigurationService;
import tframe.common.util.DateUtils;

/**
 *<pre>
 * 파일 업로드
 * 
 * Created on 2010. 6. 7
 * </pre>
 * 
 * @author 김용준, 블루다임
 */
public class FileUpload {

	Log logger = LogFactory.getLog(getClass());

	ConfigurationService config;
		
	public FileUpload() {
		super();
		config = (ConfigurationService) WCommon.getBean("configurationService");
	}

	/**
	 * <pre>
	 * Upload File Delete
	 * </pre>
	 * 
	 * @param savePath 저장 하위 경로
	 * @param fileName 파일명
	 * @return 성공여부 true, false
	 */
	public boolean uploadDeleteFile(String savePath, String fileName) {

		// 업로드 폴더
		String uploadDir = config.getString("upload.img.dir");

		// 업로드 폴더 경로가 없다면
		if (uploadDir == null || uploadDir.equals("")) {
			return false;
		}
		
		if (savePath == null || savePath.equals("")) {
			return false;
		}
				
		// 업로드 하위 경로
		uploadDir += "/" + savePath + "/";

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
	 * File Upload
	 * </pre>
	 * 
	 * @param request
	 * @param savePath 저장 하위 경로
	 * @return 업로드 파일 정보
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap> upload(HttpServletRequest request, String savePath) {
		return upload(request, savePath, "jpg,gif,png,bmp");
	}

	/**
	 * <pre>
	 * File Upload
	 * </pre>
	 * 
	 * @param request
	 * @param savePath 저장 하위 경로
	 * @param allowExt 허용 확장자 예) jpg,gif,png,bmp
	 * @return 업로드 파일 정보
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap> upload(HttpServletRequest request, String savePath, String allowExt) {
		return upload( request, savePath, allowExt, "");
	}
	
	/**
	 * <pre>
	 * File Upload
	 * </pre>
	 * 
	 * @param request
	 * @param savePath 저장 하위 경로
	 * @param allowExt 허용 확장자 예) jpg,gif,png,bmp
	 * @param fileType 저장 파일의 형식
	 * @return 업로드 파일 정보
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap> upload(HttpServletRequest request, String savePath, String allowExt, String fileType) {

		ArrayList<HashMap> aList = new ArrayList<HashMap>();

		// 업로드 폴더
		String uploadDir = config.getString("uploadTempDir");

		// 업로드 폴더 경로가 없다면
		if (uploadDir == null || uploadDir.equals("")) {
			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "01");
			hMap.put("resultMessage", "업로드 폴더 지정 오류");

			aList.add(hMap);
			return aList;
		}

		// 업로드 하위 경로
		uploadDir += "/" + savePath + "/";

		// 디렉토리 만들기
		File fDir = new File(uploadDir);
		if (!fDir.exists())
			fDir.mkdirs();

		MultipartRequest multipartReq = (MultipartRequest) request;

		Iterator itFile = multipartReq.getFileNames();

		// 확장자 체크 용도
		if (allowExt == null || allowExt.equals("")) {
			allowExt = "jpg,gif,png,bmp";
		}
		allowExt = allowExt.toLowerCase();

		String[] aStr = allowExt.split(",");

		try {
			while (itFile.hasNext()) {
				String file_name = (String) itFile.next();

				MultipartFile multipartFile = multipartReq.getFile(file_name);

				if (multipartFile.isEmpty()) {
					// 비어있는 파일
				} else {

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
						
						File tempFile = File.createTempFile(ymdhms + "_", "." + fileExt, new File(uploadDir));
						multipartFile.transferTo(tempFile);

						HashMap<String, String> hMap = new HashMap<String, String>();

						hMap.put("resultCode", "00");
						hMap.put("savePath", savePath);
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

						hMap.put("resultCode", "02");
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
			}
		} catch (Exception e) {
			e.printStackTrace();

			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "99");
			hMap.put("resultMessage", "업로드 에러");

			aList.add(hMap);
			return aList;
		}

		return aList;
	}
	
	
	/**
	 * <pre>
	 * File Upload Original File Name
     * 한언주 ,2010.06.21  추가
	 * </pre>
	 * 
	 * @param request
	 * @param savePath 저장 하위 경로
	 * @param limitSize 제한크기
	 * @return 업로드 파일 정보
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap> uploadFileOrgnNm(HttpServletRequest request, String savePath) {
		return uploadFileOrgnNm( request, savePath, (2048*1024)); 
	}
	/**
	 * <pre>
	 * File Upload Original File Name
     * 한언주 ,2010.06.21  추가
	 * </pre>
	 * 
	 * @param request
	 * @param savePath 저장 하위 경로
	 * @param limitSize 제한크기
	 * @return 업로드 파일 정보
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap> uploadFileOrgnNm(HttpServletRequest request, String savePath, int limitSize) {

		ArrayList<HashMap> aList = new ArrayList<HashMap>();

		// 업로드 폴더
		String uploadDir = config.getString("uploadTempDir");
		// 업로드 폴더 경로가 없다면
		if (uploadDir == null || uploadDir.equals("")) {
			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "01");
			hMap.put("resultMessage", "업로드 폴더 지정 오류");

			aList.add(hMap);
			return aList;
		}

		// 업로드 하위 경로
		uploadDir += "/" + savePath + "/";

		
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
				if (multipartFile.isEmpty()) {
					// 비어있는 파일
				} else {

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

						File tempFile =  new File(uploadDir+"/"+fileName);
						multipartFile.transferTo(tempFile);

						HashMap<String, String> hMap = new HashMap<String, String>();

						hMap.put("resultCode", "00");
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

						hMap.put("resultCode", "02");
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String, String> hMap = new HashMap<String, String>();

			hMap.put("resultCode", "99");
			hMap.put("resultMessage", "업로드 에러");

			aList.add(hMap);
			return aList;
		}

		return aList;
	}
	

	/**
     * Gets the file name.
     * 한언주 ,2010.06.21  추가
     * 
     * @param fileStorigePath the file storige path
     * @param filePrefix the file prefix
     * @param ctsTokenId the cts token id
     * @param ext the ext
     * 
     * @return the file name
     */
    public String getFileName(String filePath,String fileNm, String ext) {
        File tempFile;
        String fileName = "";
        for(int j = 0; j < 100; j++) {
            if(ext != null && !"".equals(ext)) {
            	if(j==0){
            		tempFile = new File(filePath+"/"+ fileNm + "." + ext);
            	}else{
	                tempFile = new File(filePath+"/"+ fileNm + "_" + String.format("%02d", (j)) + "." + ext);
            	}
                if(!tempFile.exists()) {
                    fileName = tempFile.getName();
                    break;
                }
            }
        }
        return fileName;
    }
}
