package com.omp.commons.util;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.product.model.ProductPhysicalFileCreationInfo;

/**
 * <pre>
 * Product File Upload
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
public class ProductFileUtil {
	
	
	private static GLogger logger		= new GLogger(ProductFileUtil.class);
	
	public static int UPLOAD_JOB_TYPE_BASIC_IMAGE = 0; 
	
	public static int UPLOAD_JOB_TYPE_PRE_IMAGE = 1;
	
	public static int UPLOAD_JOB_TYPE_DESCRIPTION_IMAGE = 2;
	
	public static int UPLOAD_JOB_TYPE_BINARY_APK = 3;
	
	public static int UPLOAD_JOB_TYPE_CERTIFY_GUIDE_MANUAL_DOC = 4;
	
	public static int UPLOAD_JOB_TYPE_CERTIFY_REPORT_SUB_DOC = 5;
	
	public static int UPLOAD_JOB_TYPE_CERTIFY_REPORT_SUB_ETC_DOC = 6;
	
	public static int UPLOAD_JOB_TYPE_BASIC_IMAGE_72 = 7;
	
	public static int UPLOAD_JOB_TYPE_BASIC_IMAGE_130 = 8;
	
	
	private static String[][] PATH_COMBINE_INFO = 
	       {{"", "rep_"}
	       ,{"", "preview_"}
	       ,{"", "desc_"}
	       ,{"binary", ""}
	       ,{"", "scenario_"}
	       ,{"verify", "verify_"}
	       ,{"verify", "addfile_"}
	       ,{"", "72_72_"}
	       ,{"", "130_130_"}
	       };
	
	private static int INDEX_LARTROOT_PATH_NAME = 0;
	
	private static int INDEX_ADD_PRE_FIX = 1;
	
	/**
	 * Get File Exention Name
	 * @param fileName
	 * @return String
	 */
	private static String getFileNameExtWithDot(String fileName) {
		String fileExt = "";
		int idx = fileName.lastIndexOf(".");
		if(idx > 0) {
			fileExt = fileName.substring(fileName.lastIndexOf("."));
		}
		return fileExt;
	}	
	
	/**
	 * Get Physical Product File Path
	 * 
	 * @param cid
	 * @param scid
	 * @return
	 */
	private static String getPhysicalProductFilePath(String cid, String scid){
		
		StringBuffer strBuf = new StringBuffer();
		
		strBuf.append(cid.substring(0, 2));
		strBuf.append(File.separator).append(cid.substring(2, 6));
		strBuf.append(File.separator).append(cid.substring(6, 10));
		
		return strBuf.toString();
		
	}
	

	/**
	 * 
	 * Change Upload File To Physical File 
	 * 
	 * @param param
	 * @return String
	 * @throws ServiceException
	 */
	public static String changePhsicalFilePath(ProductPhysicalFileCreationInfo param) throws ServiceException {
		
		String storedFileExt = "";
		String strFilePath = "";
		String strFileFullPath = "";
		
		StringBuffer strBuff = new StringBuffer();
		
		try{
			
			if(param == null){
				throw new ServiceException("파일 업로드 오류");
			}			
		
			if(param.getUploadedFile() == null){
				throw new ServiceException("파일 업로드 오류");
			}
			
			if(param.getUploadedFile().length() > param.getLimitSize()) {
				throw new ServiceException("파일 업로드 오류");
			}
			
			if(StringUtils.isEmpty(param.getSourceFileName()) || StringUtils.isBlank(param.getSourceFileName())){
				throw new ServiceException("파일 업로드 오류");
			}
			
			Properties properties = param.getConfProps();
			
			storedFileExt = getFileNameExtWithDot(param.getSourceFileName());
			
			switch(param.getUploadJobType()){
			
				case 5 : // with Case 6 same
				case 6 :
					
					strBuff.append(File.separator).append(param.getVmTypeName());
					strBuff.append(File.separator).append(getPhysicalProductFilePath(param.getCid(), param.getScid()));
					strBuff.append(File.separator).append(PATH_COMBINE_INFO[param.getUploadJobType()][INDEX_LARTROOT_PATH_NAME]);
					strBuff.append(File.separator).append(param.getScid());
					strBuff.append("_");
					strBuff.append(PATH_COMBINE_INFO[param.getUploadJobType()][INDEX_ADD_PRE_FIX]);
					strBuff.append(param.getAddPrefixName());
					strBuff.append(DateUtil.getGeneralTimeStampString());	
					strBuff.append(storedFileExt);
					
					strFilePath = strBuff.toString();
					
					
					strFileFullPath = properties.getProperty("omp.common.path.share.product") + File.separator + strFilePath;
					
					if(logger.isDebugEnabled()) logger.debug("uploadedFile.getPath()==========>" + param.getUploadedFile().getPath());
					if(logger.isDebugEnabled()) logger.debug("strFilePath==========>" + strFilePath);
					if(logger.isDebugEnabled()) logger.debug("strFileFullPath==========>" + strFileFullPath);
					
					FileUtil.move(param.getUploadedFile().getPath(), strFileFullPath, true);					

					break;					
				default :
					break;
					
			}
			
			
		}catch(ServiceException se){
			throw se;
		}catch(Exception ex){
			throw new ServiceException("파일 업로드 오류");
		}
		
		return strFilePath;
		
	}	
}
