package com.omp.dev.contents.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.xmlpull.v1.XmlPullParserException;

import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.product.service.ContentsHistoryService;
import com.omp.commons.product.service.ContentsHistoryServiceImpl;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.APKInfomation;
import com.omp.commons.util.APKUtil;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;

import com.omp.commons.util.StringUtil;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;
import com.omp.dev.contents.model.ContentSprtPhone;
import com.omp.dev.contents.model.ContentUpdate;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.Provision;
import com.omp.dev.contents.model.SubContents;

public class ContentDevBinaryServiceImpl 
			extends AbstractService		implements ContentDevBinaryService {

	public ContentDevBinaryServiceImpl() {}
	
	public String getSubContentsCount(String cid)  {
		
		try {
			return (String) this.commonDAO.queryForObject("ContentDevBinary.getSubContentsCount", cid);
		} catch (SQLException e) {
			throw new ServiceException("[ContentDevBinary].getSubContentsCount", e);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getSubContentsWithProvisionItem(String cid) {
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			List<SubContents>	sContentsResultList = new ArrayList<SubContents>();
			sContentsResultList = this.commonDAO.queryForList("ContentDevBinary.getSubContentsByCid", cid);

			resultMap.put("subContsList", sContentsResultList);
			
		} catch (Exception e) {
			throw new ServiceException("getSubContentsWithProvisionItem() fail", e);
		}
		
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<Provision> getSubContentsProvisionItem(String cid) {
			
		List<Provision>	resultList = new ArrayList<Provision>();
		
		try {
			
			resultList = this.commonDAO.queryForList("ContentDevBinary.getProvisionItem", cid);			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("getSubContentsProvisionItem() fail.", e);
		}
		
		return resultList;
	}
	
	public String modifyContentDevInfo(Contents content) {	

		this.daoManager.startTransaction();
		
		try {

			// 검증 시나리오 Upload
			if(content.getVerifyScnrUpload() != null) {
			
				StringBuffer basePath = new StringBuffer();
				StringBuffer filePath = new StringBuffer();
				ConfigProperties conf = new ConfigProperties();
				
				Long limitSize = Long.parseLong(conf.getString("omp.dev.product.contents.scenarioFile.uploadLimit"));
				String vmType = null;
				
				basePath.append(conf.getString("omp.common.path.share.product"));
					
				if (log.isDebugEnabled()) {
					log.debug("File BASE PATH ::::: " + basePath.toString());
				}

				if(Constants.CONTENT_PLATFORM_ANDROID.equals(content.getVmType())) {
					vmType = "android";
				} 
				
				filePath.append("/" + content.getCid().substring(0, 2));
				filePath.append("/" + content.getCid().substring(2, 6));
				filePath.append("/" + content.getCid().substring(6, 10));
				filePath.append("/" + vmType + "/verify/");
				
				if (log.isDebugEnabled()) {
					log.debug("File REAL PATH ::::: " + filePath.toString());
				}
				
				boolean isFileAttach = checkFileExt(content.getVerifyScnrUploadFileName(), Constants.FILEEXT_VERIFYSCNR);
			
				if (!isFileAttach) {
					return "fileExtentionError";
					//throw new NoticeException("[ContentDetailInfoService].modifyContentDevInfo : Can't access Other Verify Scnr Upload File");
				}
				String srcFileName = content.getVerifyScnrUploadFileName();	// src File Name
				String scnrFileFullPath = basePath.append(filePath.toString()).toString();
				String scnrFileName 
					= "scenario" + "_" + DateUtil.getGeneralTimeStampString() + getFileNameExtWithDot(srcFileName);
		
				String storedFileResult = upload(content.getVerifyScnrUpload(), limitSize, scnrFileFullPath, scnrFileName);
				content.setVerifyScnrFileNm(content.getVerifyScnrUploadFileName());
				content.setVerifyScnrFile(filePath.toString()+scnrFileName);
	
				if ("fileSizeError".equals(storedFileResult)) return storedFileResult;
			} else {
				
				// 이용 매뉴얼 삭제
				if(Constants.YES.equals(content.getVerifyScnrUploadDelYn())) {
					content.setVerifyScnrFileNm("");
					content.setVerifyScnrFile("");
				}
				
			}
			
			// update Content Dev Info
			this.commonDAO.update("ContentDetailInfo.updateContent", content);
		
			this.daoManager.commitTransaction();


			// History Table Insert
			ContentsHistoryService	contentsHistoryService		= new ContentsHistoryServiceImpl();
			contentsHistoryService.insertMainContentsHis(content.getCid());
			
			return "SUCCESS";
			
		} catch (Exception e) {
			throw new ServiceException("modifyContentDevInfo", e);
		} finally {
			this.daoManager.endTransaction();
		}
		
	}
	
	/* Binary Temp Upload & get Sprt Phone List
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#ajaxReadManifestXML(com.omp.dev.contents.model.Contents, com.omp.dev.contents.model.SubContents, java.util.Properties)
	 */
	public Map<String, Object> ajaxReadManifestXML(String vmType, SubContents subContent, Properties prop) {
		
		log.debug("==== Content ajaxReadManifestXML START ====");
		
		File apkFile;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			apkFile = subContent.getRunFile().getRunUpload();
		
			if (log.isDebugEnabled()) {
				log.debug("File Name : " + subContent.getRunFile().getRunUploadFileName());
				log.debug("File Path : " + apkFile.getPath());
			}  
	
			// Read manafestfile.XML
			resultMap = getAPKManifestXMLInfo(apkFile, subContent.getCid());		
			
			log.debug("File Result  		: " +  (Boolean) resultMap.get("resultCode"));	
			log.debug("File Result Message  : " +  (String) resultMap.get("errorMessage"));	

			if( (Boolean) resultMap.get("resultCode") ) {

				String[] arrayProvisionItem = subContent.getProvisionItem().split(",");
				String[] paramProvisionItem = new String[arrayProvisionItem.length];
				
				for (int i = 0; i < arrayProvisionItem.length; i++) {
				
					if (!"".equals(StringUtil.nvlStr(StringUtil.setTrim(arrayProvisionItem[i])))) {
						
						paramProvisionItem[i] = StringUtil.setTrim(arrayProvisionItem[i]);
					}
				}
				
				// 대상단말 리스트 조회
				List<ContentSprtPhone> sprtPhoneList = getContentSprtPhoneSearchList(vmType, paramProvisionItem, subContent, resultMap);
				
				String  strSubContentsCnt = getSubContentsCount(subContent.getCid());
				//int subContentsCnt =  strSubContentsCnt != null ? Integer.parseInt(strSubContentsCnt) + 1 : 0;
				int subContentsCnt =  strSubContentsCnt != null ? Integer.parseInt(strSubContentsCnt) : 0;
				SubContents sub = tempUploadSubContentsRunFile(vmType, subContent, prop);
				
				resultMap.put("cid", subContent.getCid());													// CID
				resultMap.put("scid", subContent.getScid());												// SCID
				resultMap.put("subContentsCnt", subContentsCnt);											// binary 개수, Form ID
				resultMap.put("sprtPhoneList", sprtPhoneList);												// 대상단말
				resultMap.put("paramProvisionItem", paramProvisionItem);									// 프로비저닝(LCD) 정보
				resultMap.put("lcdSizeChkBox", CacheCommCode.getCommCode(Constants.PHONE_LCD_SIZE));		// LCD SIZE
				resultMap.put("tempRunFileInfo", sub);														// RunFile 정보
				resultMap.put("uploadRunFileResult", sub.getUploadRunFileResult());							// Upload RunFile 결과
			}
		
		} catch (Exception e){
			e.printStackTrace();
			throw new ServiceException("[ContentDevBinaryInfo].ajaxReadManifestXML", e);
		}
		
		log.debug("==== Content ajaxReadManifestXML END   ====");
		
		return resultMap;
	}
	
	/**
	 * Directory RunFile Upload
	 * 
	 * @param content
	 * @param subContent
	 * @param prop
	 */
	private SubContents uploadSubContentsRunFile(String vmType, SubContents subContent, Properties prop) {
		
		log.debug("==== Content uploadSubContentsRunFile START ====");
		
		try{
	
			File srcFile = new File(subContent.getRunFile().getRunFilePos());		// src File
			String srcFileName = subContent.getRunFile().getRunUploadFileName();	// src File Name

			// 업로드가능 확장자 검사
			boolean formatFalg = false;
			
			if(Constants.CONTENT_PLATFORM_ANDROID.equals(vmType)) {
				formatFalg = checkFileExt(srcFileName, Constants.FILEEXT_ANDROID_BIN);
				vmType = "android";
			} 
			
			if(!formatFalg) {
				throw new Exception("Run File Format Error : SCID" + subContent.getScid());
			}
		
			// RunFile 
			StringBuffer basePath = new StringBuffer();
			StringBuffer filePath = new StringBuffer();

			basePath.append(prop.getProperty("omp.common.path.share.product"));
				
			if (log.isDebugEnabled()) {
				log.debug("File BASE PATH ::::: " + basePath.toString());
			}
			
			filePath.append("/" + vmType);
			filePath.append("/" + subContent.getCid().substring(0, 2));
			filePath.append("/" + subContent.getCid().substring(2, 6));
			filePath.append("/" + subContent.getCid().substring(6, 10));
			filePath.append("/binary/");
			
			if (log.isDebugEnabled()) {
				log.debug("File BASE PATH ::::: " + basePath.toString());
				log.debug("File REAL PATH ::::: " + filePath.toString());
			}
		
			String descFileFullPath = basePath.append(filePath.toString()).toString();
			String descFileName 
				= subContent.getScid() + "_" + DateUtil.getGeneralTimeStampString() + getFileNameExtWithDot(srcFileName);
	
			File storedFile = upload(descFileFullPath, descFileName, srcFile);
			subContent.getRunFile().setRunFileNm(srcFileName);
			subContent.getRunFile().setRunFilePos(filePath.toString()+descFileName);
			subContent.getRunFile().setRunFileSize("" + storedFile.length());

		} catch (Exception e) {
			throw new ServiceException("Can't upload Binary CID - [{0}] : ", new Object[] {subContent.getScid()}, e);
		}
		
		log.debug("==== Content uploadSubContentsRunFile END   ====");
		
		return subContent;
	}
	
	/**
	 * Temp Directory RunFile Upload
	 * 
	 * @param content
	 * @param subContent
	 * @param prop
	 */
	private SubContents tempUploadSubContentsRunFile(String vmType, SubContents subContent, Properties prop) {
		
		log.debug("==== Content tempUploadSubContentsRunFile START ====");
		
		try{
		
			// RunFile 
			StringBuffer basePath = new StringBuffer();
			StringBuffer filePath = new StringBuffer();

			basePath.append(prop.getProperty("omp.common.path.temp.base"));
				
			if (log.isDebugEnabled()) {
				log.debug("File BASE PATH(omp.common.path.temp.base) ::::: " + basePath.toString());
			}
			
			filePath.append("/product/" + subContent.getCid().substring(0, 2));
			filePath.append("/" + subContent.getCid().substring(2, 6));
			filePath.append("/" + subContent.getCid().substring(6, 10));
			filePath.append("/binary/");
		
			if (log.isDebugEnabled()) {
				log.debug("File BASE PATH ::::: " + basePath.toString());
				log.debug("File TEMP PATH ::::: " + filePath.toString());
			}
			
			Long limitSize = Long.parseLong(prop.getProperty("omp.dev.product.contents.uploadLimit"));
			File file = subContent.getRunFile().getRunUpload();
			String upName = subContent.getRunFile().getRunUploadFileName();

			// 업로드가능 확장자 검사
			boolean formatFalg = false;
			if(Constants.CONTENT_PLATFORM_ANDROID.equals(vmType)) {
				formatFalg = checkFileExt(upName, Constants.FILEEXT_ANDROID_BIN);
				vmType = "android";
			} 
			
			if(!formatFalg) {
				//throw new ServiceException("Run File Format Error : SCID" + subContent.getScid());
				subContent.setUploadRunFileResult("fileExtentionError");
			}
		
			String fileTempFullPath = basePath.toString() +  filePath.append(DateUtil.getGeneralTimeStampString() +  "/").toString()  ;
			String fileName = subContent.getCid() + "_" + DateUtil.getGeneralTimeStampString() + getFileNameExtWithDot(upName);
	
			String storedFileResult = upload(file, limitSize, fileTempFullPath, fileName);
			subContent.getRunFile().setRunFileNm(upName);
			subContent.getRunFile().setRunFilePos(filePath.toString()+fileName);
			subContent.getRunFile().setRunFileSize("" + file.length());
			subContent.setUploadRunFileResult(storedFileResult);
			
		} catch (Exception e) {
			throw new ServiceException("Can't upload Temp Binary CID - [{0}] : ", new Object[] {subContent.getScid()}, e);
		}
		
		log.debug("==== Content tempUploadSubContentsRunFile END   ====");
		
		return subContent;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ContentSprtPhone> getContentTargetPhoneList(String cid, String scid) {
		
		List<ContentSprtPhone> resultList = new ArrayList<ContentSprtPhone>();
		
		try {
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("cid", cid);
			paramMap.put("scid", scid);
			
			resultList = this.commonDAO.queryForList("ContentDevBinary.getContentTargetPhoneList", paramMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new ServiceException("getContentTargetPhoneList", e);
		}
		return resultList;
	}
	
	/**
	 * @param subContent
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<ContentSprtPhone> getContentSprtPhoneSearchList(String vmType, String[] paramProvisionItem, SubContents subContent,  Map<String, Object> resultMap) throws SQLException {
	
		Map<String, Object> paramMap = new HashMap<String, Object>();

		if ((String) resultMap.get("maxSDKVersion") != null) 
			paramMap.put("maxSDKVersion", (String) resultMap.get("maxSDKVersion"));
		
		paramMap.put("minSDKVersion", (String) resultMap.get("minSDKVersion"));
		paramMap.put("vmType", vmType);
		paramMap.put("paramProvisionItem", paramProvisionItem);
		
		return this.commonDAO.queryForList("ContentDevBinary.getContentSprtPhoneSearchList", paramMap);
	}
	
	
	/* Search Phone
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#getContentSprtPhoneSearchList(com.omp.dev.contents.model.Contents, com.omp.dev.contents.model.SubContents)
	 */
	public List<ContentSprtPhone> getContentSprtPhoneSearchList(Contents content, SubContents subContent, Properties prop) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ContentSprtPhone> resultList = new ArrayList<ContentSprtPhone>();
		
		try {
			File apkFile;
			
			if (subContent.getProvisionItem() != null) {
				
				if (subContent.getScid() != null && !"".equals(subContent.getScid())) {
					apkFile = new File(prop.getProperty("omp.common.path.share.product") + subContent.getRunFile().getRunFilePos());
				}else {
					apkFile = new File(prop.getProperty("omp.common.path.temp.base") + subContent.getRunFile().getRunFilePos());
				}
				
				resultMap = getAPKManifestXMLInfo(apkFile, subContent.getCid());

				String[] arrayProvisionItem = subContent.getProvisionItem().split(",");
				String[] paramProvisionItem = new String[arrayProvisionItem.length];
				
				for (int i = 0; i < arrayProvisionItem.length; i++) {
				
					if (!"".equals(StringUtil.nvlStr(StringUtil.setTrim(arrayProvisionItem[i])))) {
						
						paramProvisionItem[i] = StringUtil.setTrim(arrayProvisionItem[i]);
					}
				}
				
				resultList = getContentSprtPhoneSearchList(content.getVmType(), paramProvisionItem, subContent,  resultMap);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("getContentSprtPhoneSearchList", e);
		}
		
		
		return resultList;
	}

	/**
	 * 첨부파일 업로드가능 확장자 검사 : 첨부파일명, 확장자(gif|zip|jpg)
	 * 
	 * @param fileName
	 * @param fileExt
	 * @return
	 */
	private boolean checkFileExt(String fileName, String fileExt) {
		
		log.debug("#첨부파일 업로드가능 확장자 검사");
		boolean result = false;

		fileName = StringUtil.setTrim(fileName).toUpperCase();
		fileExt = StringUtil.setTrim(fileExt).toUpperCase();
		log.debug("fileName[" + fileName + "]");
		log.debug("fileExt[" + fileExt + "]");
		if(!fileExt.equals("")) {
			StringTokenizer st = new StringTokenizer(fileExt, "|");
			while (st.hasMoreTokens()) {
				if(fileName.endsWith("." + st.nextToken())) { 
					result = true;
					break;
				}
			}
		} else {
			result = true;
		}
		log.debug("#첨부파일 업로드가능 확장자 검사:" + result);
		return result;
	}
	
	private String getFileNameExtWithDot(String fileName) {
		String fileExt = "";
		int idx = fileName.lastIndexOf(".");
		if(idx > 0) {
			fileExt = fileName.substring(fileName.lastIndexOf("."));
		}
		return fileExt;
	}

	/**
	 * RunFile Temp Directory Upload
	 * 
	 * @param upload
	 * @param limitSize
	 * @param physicalPath
	 * @param physicalName
	 * @return
	 * @throws Exception
	 */
	private String upload(File upload, long limitSize, String physicalPath, String physicalName) throws Exception {
		String storedFile = "";
		if(upload != null) {
			log.debug("#첨부파일 업로드 : {0}", new Object[] {upload.getName()});
			if(upload.length() > limitSize) {
				log.debug("#첨부파일  업로드 용량 초과:" + upload.length());
				//throw new ServiceException("업로드 용량 초과");
				return "fileSizeError";
			}
			
			storedFile = physicalPath + physicalName;
			//FileUtils.copyFile(upload, new File(storedFile));
			
			if (log.isDebugEnabled()) {
				log.debug("RunFile Temp File Path : " + physicalPath);
			}
		
			FileUtils.deleteDirectory(new File(physicalPath));
			
			FileUtil.move(upload.getPath(), storedFile, false);
			//FileUploadUtil.uploadFileNaming(upload, physicalPath, physicalName);
		}
		return storedFile;
	}

	/**
	 * RunFile Directory Upload
	 * 
	 * @param descPhysicalPath
	 * @param descPhysicalName
	 * @param srcFile
	 * @return
	 * @throws Exception
	 */
	private File upload(String descPhysicalPath, String descPhysicalName, File srcFile) throws Exception {
		
		String storedFile = descPhysicalPath + descPhysicalName;
		File descFile = new File(storedFile);
		
		if (log.isDebugEnabled()) {
			log.debug("RunFile File Path : " + descPhysicalPath);

			log.debug("SRC PATH ::: " + srcFile.getPath());
			log.debug("DESC PATH ::: " + descPhysicalPath);
			
		}

		//FileUtil.copy(srcFile.getPath(), descPhysicalPath, false );
		FileUtil.move(srcFile.getPath(), storedFile, false);
		
		//FileUploadUtil.uploadFileNaming(srcFile, descPhysicalPath, descPhysicalName);

		return descFile;
	}
	
	/**
	 * androidMainFest.xml Parsing Infomation
	 * 
	 * @param apkFile
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws SQLException 
	 */
	public	Map<String, Object> getAPKManifestXMLInfo(File apkFile, String cid) throws IOException, XmlPullParserException, SQLException  {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		APKInfomation apkInf = APKUtil.getAPKInfomation(apkFile);
		
		if (log.isInfoEnabled()) {
			log.info("=========== cid  : {0} ===========", new Object[] {cid});
			log.info("     versionCode : {0}", new Object[] {apkInf.getVersionCode()});
			log.info("     versionName : {0}", new Object[] {apkInf.getVersionName()});
			log.info("         package : {0}", new Object[] {apkInf.getPackageName()});
			log.info("   minSDKVersion : {0}", new Object[] {apkInf.getMinSDKVersion()});
			log.info("targetSDKVersion : {0}", new Object[] {apkInf.getTargetSDKVersion()});
			log.info("   maxSDKVersion : {0}", new Object[] {apkInf.getMaxSDKVersion()});
			log.info(" usesPermissions : {0}", new Object[] {apkInf.getUsesPermissions()});
			log.info("            uses : {0}", new Object[] {apkInf.getUsesFeatures()});
			log.info("        isSigned : {0}", new Object[] {apkInf.isSigned()});
			
			if (apkInf.isSigned()) { // 사인된 apk일때만
		       log.info("         isDebug : {0}", new Object[] {apkInf.isDebugSign()});
		       log.info("     signKeyDays : {0}", new Object[] {apkInf.getSignKeyValidityDays()});
		       log.info("       signedKey : {0}", new Object[] {apkInf.getSignedKey()});   
			}
			
			log.info("==========================================");
		}

		// minSDKVersion 필수
		if (apkInf.getMinSDKVersion() == null || "".equals(apkInf.getMinSDKVersion()) ) {	
			// Defalt value : 2.1
			log.debug("apkInf.getMinSDKVersion() is NULL!");
			resultMap.put("minSDKVersion", "1");
		}else {
			log.debug("apkInf.getMinSDKVersion() is PASS!");
			resultMap.put("minSDKVersion",  apkInf.getMinSDKVersion());
		}

		resultMap.put("maxSDKVersion", apkInf.getMaxSDKVersion());
		resultMap.put("targetSDKVersion", apkInf.getTargetSDKVersion());
		resultMap.put("pkgNm", apkInf.getPackageName());
		resultMap.put("versionCode", apkInf.getVersionCode());
		resultMap.put("versionName", apkInf.getVersionName());
		resultMap.put("isSigned", apkInf.isSigned());
		
		if (apkInf.isSigned()) { // 사인된 apk일때만
			resultMap.put("isDebug", apkInf.isDebugSign());
			resultMap.put("signKeyDays", apkInf.getSignKeyValidityDays());
		}
		
		
		// androidMainFest.xml validation Check
		androidMainFestVelidate(resultMap, cid);
		
		return resultMap;
	}
	
	private void androidMainFestVelidate(Map<String, Object> resultMap, String cid) throws SQLException {

		boolean supportMaxOsVersion = false;
		boolean supportMinOsVersion = true;
		
		resultMap.put("resultCode", false);
		
		// Android OS Checking(min version : 9 초과, max version : 7 미만)
		List<CommCode> commCodeList = CacheCommCode.getCommCode(Constants.ANDROID_OS);
		List<CommCode> commCodeSupportList  = CacheCommCode.getFilteredList(commCodeList, "support", "");
		
		if (commCodeSupportList == null || commCodeSupportList.size() == 0) {
			commCodeSupportList.addAll(commCodeList);
		}
		
		// Min OS SDK 버전 체크
		if ((String)resultMap.get("minSDKVersion") != null) {

			int supportMaxValueMinVer = 1;
			
			for (int i = 0; i < commCodeSupportList.size(); i ++) {
				if(supportMaxValueMinVer < Integer.parseInt(commCodeSupportList.get(i).getAddField1())) {
					supportMaxValueMinVer = Integer.parseInt(commCodeSupportList.get(i).getAddField1());
				}
			}
			
			if(log.isDebugEnabled()) log.debug(" *** Support Min OS SDK Version MaxValue : {0}", new Object[] {supportMaxValueMinVer});
			
			if(supportMaxValueMinVer  < Integer.parseInt(String.valueOf(resultMap.get("minSDKVersion")))) {
				supportMinOsVersion = false;
			} else {
				supportMinOsVersion = true;
			}
			
		} else {// Min SDK Version null이면 true
			resultMap.put("minSDKVersion", "1");
			supportMinOsVersion = true;
		}
		
		// Max OS SDK 버전 체크
		if ((String)resultMap.get("maxSDKVersion") != null) {
			
			int supportMinValueMaxVer = 9;
			
			for (int i = 0; i < commCodeSupportList.size(); i ++) {
				if(supportMinValueMaxVer > Integer.parseInt(commCodeSupportList.get(i).getAddField1())) {
					supportMinValueMaxVer = Integer.parseInt(commCodeSupportList.get(i).getAddField1());
				}
			}
			
			if(log.isDebugEnabled()) log.debug(" *** Support Max OS SDK Version MinValue : {0}", new Object[] {supportMinValueMaxVer});
			
			if(supportMinValueMaxVer  > Integer.parseInt(String.valueOf(resultMap.get("maxSDKVersion")))) {
				supportMaxOsVersion = false;
			} else {
				supportMaxOsVersion = true;
			}
			
		} else {// Max SDK Version null이면 true
			supportMaxOsVersion = true;
		}

		
		// 버전코드가 null이 아닐 때
		if(resultMap.get("versionCode") != null) {
			
			// Is Signning Check 사이닝 되었을 때
			if ((Boolean) resultMap.get("isSigned")) {
				
				// 유효일 체크 (1000 일 이상 가능)
				if ((Integer)resultMap.get("signKeyDays") < 1000-1) {
					log.debug("APK ::: SigningKeyError");
					resultMap.put("resultCode", false);
					resultMap.put("errorMessage", "SigningKeyError");
				} else {
				
					// Is Debuggingg Check 디버그 모드가 아닐 때
					if (!(Boolean)resultMap.get("isDebug")) {
						//resultMap.put("resultCode", true);
						
						// 이전 버전 코드 불가
						@SuppressWarnings("unchecked")
						List<SubContents> 	oldSubContentList = this.commonDAO.queryForList("ContentDevBinary.getSubContentsByCid", cid);
						
						// 기 등록된 SubContent가 존재할때
						if (oldSubContentList.size() > 0 ) {
							for (int i = 0 ; i < oldSubContentList.size(); i++) {
								
								// 이전 버전 코드값이면 False
								if (Integer.parseInt(oldSubContentList.get(i).getVersionCode()) 
										> Integer.parseInt((String) resultMap.get("versionCode"))) {

									log.debug("APK ::: VersionCodeError");
									resultMap.put("resultCode", false);
									resultMap.put("errorMessage", "VersionCodeError");
									
									break;
									
								} else {	// 이전 버전 코드값보다 같거나 크면 True 
				
									// 지원하는 Min Os Version 일 때
									if(supportMinOsVersion) {
										
										// 지원하는 Max Os Version 일 때
										if(supportMaxOsVersion) {
											
											// MaxSDKVersion 존재 할 경우
											if (resultMap.get("maxSDKVersion") != null) {
												
												// Min SDK Version이 Max SDK Version보다 클 경우
												if (Integer.parseInt((String)resultMap.get("minSDKVersion")) > Integer.parseInt((String)resultMap.get("maxSDKVersion"))) {
													log.debug("APK ::: WrongMaxSDKVersion");
													resultMap.put("resultCode", false);
													resultMap.put("errorMessage", "WrongMaxSDKVersion");
													
												} else {
													// MINSDKVersion이 MAXSDKVersion보다 작거나 같을 경우
													resultMap.put("resultCode", true);
												}
												
											} else {
												// MaxSDKVersion 존재 하지 않을 경우
												resultMap.put("resultCode", true);
											}
											
										} else {	// 지원하지않는 Max Os Version 일 때
											
											log.debug("APK ::: SupportMaxOSError");
											resultMap.put("resultCode", false);
											resultMap.put("errorMessage", "SupportMaxOSError");
										}
										
									} else { // 지원하지않는 Min Os Version 일 때
										log.debug("APK ::: SupportMinOSError");
										resultMap.put("resultCode", false);
										resultMap.put("errorMessage", "SupportMinOSError");
									}
			
								}
							} /////// for End
							
						}else { // 기 등록된 SubContent가 존재하지 않을 때
							
							// 지원하는 Min Os Version 일 때
							if(supportMinOsVersion) {
								
								// 지원하는 Max Os Version 일 때
								if(supportMaxOsVersion) {
									
									// MaxSDKVersion 존재 할 경우
									if (resultMap.get("maxSDKVersion") != null) {
										
										// Min SDK Version이 Max SDK Version보다 클 경우
										if (Integer.parseInt((String)resultMap.get("minSDKVersion")) > Integer.parseInt((String)resultMap.get("maxSDKVersion"))) {
											log.debug("APK ::: WrongMaxSDKVersion");
											resultMap.put("resultCode", false);
											resultMap.put("errorMessage", "WrongMaxSDKVersion");
											
										} else {
											// MINSDKVersion이 MAXSDKVersion보다 작거나 같을 경우
											resultMap.put("resultCode", true);
										}
										
									} else {
										// MaxSDKVersion 존재 하지 않을 경우
										resultMap.put("resultCode", true);
									}
									
								} else {	// 지원하지않는 Max Os Version 일 때
									
									log.debug("APK ::: SupportMaxOSError");
									resultMap.put("resultCode", false);
									resultMap.put("errorMessage", "SupportMaxOSError");
								}
								
							} else { // 지원하지않는 Min Os Version 일 때
								log.debug("APK ::: SupportMinOSError");
								resultMap.put("resultCode", false);
								resultMap.put("errorMessage", "SupportMinOSError");
							}
							
						}
					
					} else {
						// 디버그 모드일 때
						log.debug("APK ::: DebugModeTrueError");
						resultMap.put("resultCode", false);
						resultMap.put("errorMessage", "DebugModeTrueError");
					}	
				}
				
			} else {
				// 사이닝 되지 않았을 때
				log.debug("APK ::: NoneSigningModeFalseError");
				resultMap.put("resultCode", false);
				resultMap.put("errorMessage", "NoneSigningModeFalseError");
			}

		} else {
			// 버전코드가 존재하지 않을 때
			log.debug("APK ::: NoneVersionCode");
			resultMap.put("resultCode", false);
			resultMap.put("errorMessage", "NoneVersionCode");
		}

		// Android OS Setting
		for (int i = 0; i < commCodeList.size(); i++) {
			
			if (log.isDebugEnabled()) {
				log.debug("공통코드에서 조회된 OS 버전 : " + commCodeList.get(i).getAddField1());
				log.debug("MIN SDK 버전 : " + resultMap.get("minSDKVersion"));
				log.debug("MIN SDK 버전 : " + resultMap.get("maxSDKVersion"));
				log.debug("MIN SDK 버전 : " + resultMap.get("targetSDKVersion"));
			}
			
			if(commCodeList.get(i).getAddField1().equals(resultMap.get("minSDKVersion"))) {
				resultMap.put("minSDKVersion", commCodeList.get(i).getCdNm());
			}
			
			if(commCodeList.get(i).getAddField1().equals(resultMap.get("maxSDKVersion"))) {
				resultMap.put("maxSDKVersion", commCodeList.get(i).getCdNm());
			}
			
			if(commCodeList.get(i).getAddField1().equals(resultMap.get("targetSDKVersion"))) {
				resultMap.put("targetSDKVersion", commCodeList.get(i).getCdNm());
			}
		}
		
		if (resultMap.get("targetSDKVersion") != null && ((String)resultMap.get("targetSDKVersion")).indexOf('.') <= -1) {
			resultMap.put("targetSDKVersion", "N/A");
		}

	}
	
	
	/* get Registered Content SprtPhone
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#getRegisteredContentSprtPhone(com.omp.dev.contents.model.SubContents)
	 */
	public int getRegisteredContentSprtPhone(SubContents subContent) {
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
		
			if (subContent.getSprtPhoneModel() != null) {
				
				String[] arraySprtPhoneModel = subContent.getSprtPhoneModel().split(",");
				String[] paramSprtPhoneModel = new String[arraySprtPhoneModel.length];
				
				for (int i = 0; i < arraySprtPhoneModel.length; i++) {
				
					if (!"".equals(StringUtil.nvlStr(StringUtil.setTrim(arraySprtPhoneModel[i])))) {
						
						paramSprtPhoneModel[i] = StringUtil.setTrim(arraySprtPhoneModel[i]);
						
					}
				}

				paramMap.put("cid", subContent.getCid());
				paramMap.put("scid", subContent.getScid());
				paramMap.put("sprtPhoneModel", paramSprtPhoneModel);
				
				int result = (Integer) this.commonDAO.queryForObject("ContentDevBinary.getRegisteredContentSprtPhone", paramMap);
				
				return result;
		
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("ContentDevBinaryService.getRegisteredContentSprtPhone" , e);
		}
	}
	
	
	/* Create SubContents
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#insertSubContent(com.omp.dev.contents.model.Contents, com.omp.dev.contents.model.SubContents, java.util.Properties)
	 */
	public String insertSubContent(Contents content, SubContents subContent, Properties prop) {
	
		this.daoManager.startTransaction();

		try {
		
			String	 resultScid = null;
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			// SCID가 발급 상태인지를 확인 한다.
			String scid = StringUtil.setTrim(subContent.getScid());	

			if (log.isDebugEnabled()) {
				log.debug("SCID 발급 상태 : " + scid);
			}
			
			if ("".equals(scid)){
				// SCID 신규 발급
				subContent.setScid((String) this.commonDAO.queryForObject("ContentDevBinary.getMakeScid"));			
				resultScid = subContent.getScid();
			} 
		   
			// provision check
			//if (getRegisteredContentSprtPhone(subContent) == 0) {  // 기 등록된 단말 없을 때(Provision Pass)
				
			// runFile info
			resultMap = getAPKManifestXMLInfo(new File(prop.getProperty("omp.common.path.temp.base") + subContent.getRunFile().getRunFilePos()), content.getCid());
			subContent.getRunFile().setRunFilePos((prop.getProperty("omp.common.path.temp.base") + subContent.getRunFile().getRunFilePos()));
			
			// runFile Upload
			uploadSubContentsRunFile(content.getVmType(), subContent, prop);
			
			// insert subContent
			newBinarySet(content, subContent, resultMap);				
			
			// insert provision Item
			newProvisionItemSet(subContent);
		
			// insert sprt Phone
			newSprtPhoneSet(content.getCid(), subContent);
			
			// PD_CONTS AGRMNT_STAT => PD005001
			if(Constants.CODE_VERIFY_INIT.equals(content.getVerifyPrgrYn())) {
				content.setAgrmntStat(Constants.AGREEMENT_STATUS_INIT);
				content.setCid(subContent.getCid());
				this.commonDAO.update("ContentDetailInfo.changeContentsAgrmntStat", content);
			}
			
			this.daoManager.commitTransaction();

			
			// History Table Insert
			ContentsHistoryService	contentsHistoryService		= new ContentsHistoryServiceImpl();
			contentsHistoryService.insertSubContsAllHis(resultScid);
			
			return resultScid;

			//} else {
			//	return null;
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ContentDevBinaryService.updateSubContent" , e);
		} finally {
			this.daoManager.endTransaction();
		}
	}
	

	/* Update SubContent
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#updateSubContent(com.omp.dev.contents.model.Contents, com.omp.dev.contents.model.SubContents, java.util.Properties)
	 */
	public String updateSubContent(Contents content, SubContents subContent, Properties prop) {

		this.daoManager.startTransaction();
		
		try {
			
			String	 resultScid = subContent.getScid();
			
			if (subContent.getScid() != null) {
				// provision Delete
				this.commonDAO.delete("ContentDevBinary.deleteProvisionItem", subContent.getScid());
			
				// sprt phone Delete
				this.commonDAO.delete("ContentDevBinary.deleteSprtPhone", subContent.getScid());
	
				// update subContent
				modifyBinarySet(content, subContent);				
			}    

			// insert provision Item
			newProvisionItemSet(subContent);
			
			// insert sprt Phone
			newSprtPhoneSet(content.getCid(), subContent);
			
			// PD_CONTS AGRMNT_STAT => PD005001
			content.setAgrmntStat(Constants.AGREEMENT_STATUS_INIT);
			content.setCid(subContent.getCid());
			this.commonDAO.update("ContentDetailInfo.changeContentsAgrmntStat", content);

			this.daoManager.commitTransaction();

			
			// History Table Insert
			ContentsHistoryService	contentsHistoryService		= new ContentsHistoryServiceImpl();
			contentsHistoryService.insertSubContsAllHis(resultScid);
			
			return resultScid;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ContentDevBinaryService.updateSubContent" , e);
		} finally {
			this.daoManager.endTransaction();
		}
	
	}

	/**
	 * New Binary Create Setting
	 * 
	 * @param content
	 * @param subContent
	 * @param resultMap
	 * @throws SQLException
	 */
	public void newBinarySet(Contents content, SubContents subContent, Map<String, Object> resultMap) throws SQLException {
		
		subContent.setCid(content.getCid());
		subContent.setVmVerMax((String)resultMap.get("maxSDKVersion"));
		subContent.setVmVerMin((String)resultMap.get("minSDKVersion"));
		subContent.setVmVerTarget((String)resultMap.get("targetSDKVersion"));
		subContent.setPkgNm((String)resultMap.get("pkgNm"));
		subContent.setVersionCode((String)resultMap.get("versionCode"));
		subContent.setVersionName((String)resultMap.get("versionName"));
		subContent.setInsBy(content.getDevUserId());
		subContent.setUpdBy(content.getDevUserId());
		
		this.commonDAO.insert("ContentDevBinary.insertSubConts", subContent);	// subContent Insert	
	}
	
	public void modifyBinarySet(Contents content, SubContents subContent) throws SQLException {
		
		subContent.setCid(content.getCid());
		subContent.setInsBy(content.getDevUserId());
		subContent.setUpdBy(content.getDevUserId());
		
		
		this.commonDAO.update("ContentDevBinary.updateSubConts", subContent);	// subContent Insert	
	}
	
	private void newProvisionItemSet(SubContents subContent) {

		String[] arrayProvisionItem = subContent.getProvisionItem().split(",");
		Provision[] provision = null;

		try {
			
			if (arrayProvisionItem.length > 0) {
				provision = new Provision[arrayProvisionItem.length];
				
				for (int i = 0, j = 0; i < arrayProvisionItem.length; i++) {
					if (!"".equals(StringUtil.nvlStr(StringUtil.setTrim(arrayProvisionItem[i])))) {
					
						provision[j] = new Provision();
	
						provision[j].setScid(subContent.getScid());
						provision[j].setItemSeq(j);
						provision[j].setItemType(Constants.PROVISION_TYPE_LCD_SIZE);
						provision[j].setItemCd(StringUtil.setTrim(StringUtil.setTrim(arrayProvisionItem[i])));
						provision[j].setInsBy(subContent.getInsBy());
					
						// provision Insert
						this.commonDAO.insert("ContentDevBinary.insertProvisionItem", provision[j]);
						
						j++;
						
					}
				}
			}
		} catch (SQLException e) {
			throw new ServiceException("Can't newProvisionItemSet CID - [{0}] : ", new Object[] {subContent.getScid()}, e);
		}
	}

	private void newSprtPhoneSet(String cid, SubContents subContent){

		String[] arraySprtPhoneModel = subContent.getSprtPhoneModel().split(",");
		ContentSprtPhone[] sprtPhoneModel = null;
		
		List<ContentSprtPhone> oldSprtPhoneList = new ArrayList<ContentSprtPhone>();
				
		try {
			
			int sprtPhoneCnt = 0;
			boolean isSame = false;
			
			if (arraySprtPhoneModel.length > 0) {
				
				sprtPhoneModel = new ContentSprtPhone[arraySprtPhoneModel.length];
				oldSprtPhoneList = getContentTargetPhoneList(cid, "");			// 기존 등록된 단말 조회
				
				for (int i = 0; i < arraySprtPhoneModel.length; i++) {
					
					if (!"".equals(StringUtil.nvlStr(StringUtil.setTrim(arraySprtPhoneModel[i])))) {	// 단말 모델값이 존재하고
							
						for(int k = 0; k < oldSprtPhoneList.size(); k++) {
							
							if (!(StringUtil.nvlStr(StringUtil.setTrim(arraySprtPhoneModel[i]))).equals((oldSprtPhoneList.get(k)).getPhoneModelCd())){ // 기존 모델과 겹치지 않을 때
								isSame = false;
							} else {
								isSame = true;
								break;
							}
						}
						
						if(!isSame) {
							sprtPhoneModel[sprtPhoneCnt] = new ContentSprtPhone();
							
							sprtPhoneModel[sprtPhoneCnt].setScid(subContent.getScid());
							sprtPhoneModel[sprtPhoneCnt].setSprtPhoneSeq(sprtPhoneCnt);
							sprtPhoneModel[sprtPhoneCnt].setPhoneModelCd(StringUtil.setTrim(arraySprtPhoneModel[i]));
							sprtPhoneModel[sprtPhoneCnt].setInsBy(subContent.getInsBy());
							sprtPhoneModel[sprtPhoneCnt].setUpdBy(subContent.getInsBy());
							
							// sprt phone Insert
							this.commonDAO.insert("ContentDevBinary.insertSprtPhone", sprtPhoneModel[i]);
							
							sprtPhoneCnt++;
							
						}
					}
				}
				
			}

		} catch (SQLException e) {
			throw new ServiceException("Can't newProvisionItemSet CID - [{0}] : ", new Object[] {subContent.getScid()}, e);
		}
	}
	
	public void deleteSubContentsCount(String scid) {
	
		try {
			this.daoManager.startTransaction();
			
			// subContent Delete
			this.commonDAO.delete("ContentDevBinary.deleteSubContentByScid", scid);

			// provision Delete
			this.commonDAO.delete("ContentDevBinary.deleteProvisionItem", scid);

			// sprt phone Delete
			this.commonDAO.delete("ContentDevBinary.deleteSprtPhone", scid);
		
			this.daoManager.commitTransaction();
			
		} catch (SQLException e) {
			throw new ServiceException("deleteSubContentsCount", e);
		} finally {
			this.daoManager.endTransaction();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ContentUpdate> getContentUpdateList(String cid) {
		
		List<ContentUpdate>  resultList = null;
		
		try {
			// subContent Delete
			resultList = this.commonDAO.queryForList("ContentDevBinary.getContentUpdateList", cid);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("getVerifyCommentList", e);
		}
		
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contents> getVerifyCommentList(String cid) {
		
		List<Contents>  resultList = null;
		
		try {
			// subContent Delete
			resultList = this.commonDAO.queryForList("ContentDevBinary.getVerifyCommentList", cid);

		} catch (SQLException e) {
			throw new ServiceException("getVerifyCommentList", e);
		}
		
		return resultList;
	}
	
	/* Update History Insert
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#insertUpdateHistory(com.omp.dev.contents.model.ContentUpdate)
	 */
	public void insertUpdateHistory(ContentUpdate contentUpdate) {
		
		try {
			this.daoManager.startTransaction();
			
			// insert Updater History
			this.commonDAO.insert("ContentDevBinary.insertUpdateHistory", contentUpdate);
		
			this.daoManager.commitTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("insertUpdateHistory", e);
		} finally {
			this.daoManager.endTransaction();
		}
		
	}
	
	/* Update History Update
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#updateUpdateHistory(com.omp.dev.contents.model.ContentUpdate)
	 */
	public void updateUpdateHistory(ContentUpdate contentUpdate) {
		
		try {
			this.daoManager.startTransaction();
			
			// insert Updater History
			this.commonDAO.update("ContentDevBinary.updateUpdateHistory", contentUpdate);
		
			this.daoManager.commitTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("insertUpdateHistory", e);
		} finally {
			this.daoManager.endTransaction();
		}
		
	}
	
	/* Update History delete
	 * @see com.omp.dev.contents.service.ContentDevBinaryService#deleteUpdateHistory(com.omp.dev.contents.model.ContentUpdate)
	 */
	public void deleteUpdateHistory(ContentUpdate contentUpdate) {
		this.daoManager.startTransaction();
		
		try {
		
			// delete Updater History
			this.commonDAO.delete("ContentDevBinary.deleteUpdateHistory", contentUpdate);
		
			this.daoManager.commitTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("insertUpdateHistory", e);
		} finally {
			this.daoManager.endTransaction();
		}
		
	}
	
	public String getOtaDeveloperPhoneList(String mbrNo) {
		
		String result = null;
		
		try {
		
			result = (String) this.commonDAO.queryForObject("ContentDevBinary.getOtaDeveloperPhoneCount", mbrNo);
		
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("insertUpdateHistory", e);
		}
	}
}
