package com.nmimo.common.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.nmimo.common.ServiceConstants;
import com.nmimo.data.message.info.ContentInfo;
import com.nmimo.data.message.info.ContentSubInfo;
import com.nmimo.data.message.info.MessageInfo;

/**
 * <pre>
 * MMS htm파일생성 Util
 * </pre>
 * @file BodyCreateUtil.java
 * @since 2013. 7. 30.
 * @author Administrator
 */
public class BodyCreateUtil {
	
	private static String ymdms = DateUtils.getDate2String(DateUtils.getDate(), "yyyyMMddHHmmssSS");
	private static String ymd = DateUtils.getDate2String(DateUtils.getDate(), "yyyy/MM/dd");


	/**
	 * <pre>
	 * MMS전용 Body 생성
	 * </pre>
	 * @param params
	 * @param filePath
	 * @param bodyFilePath
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String,String> getBodyHTM(MessageInfo params, String filePath, String bodyFilePath) throws Exception{
		
		HashMap<String,String> map = new HashMap<String, String>();
		ArrayList<ContentSubInfo> cs = new ArrayList<ContentSubInfo>();
		
		String msgTitleNm = StringUtils.nvlStr(params.getMsgTitleNm());									//메세지 제목
		String msgSbst = StringUtils.nvlStr(params.getMsgSbst());										//메세지 내용
		String[] arrMulti = params.getArrMulti();														//첨부파일명 배열
		String separator = File.separator;																//OS별 \ or /
		String createFilelName= ymdms+RandomString.getString(4, RandomString.TYPE_NUMBER)+".htm";		//HTM 파일명(총20자리)
		String htmPathDir = filePath+ymd;																//HTM Path DIR
		String infoPathDir =filePath+ymd+separator+"org"+separator+createFilelName;						//INFO Path DIR
		String createHtmFileFullPath = htmPathDir+separator+createFilelName;							//HTM FullPath
		String createInfoFileFullPath = infoPathDir+separator+createFilelName+".info";					//INFO FullPath
		String multiFileFullPath = infoPathDir+separator;												//업로드된 Img, Mov FullPath
		
		// 디렉토리 만들기
		File fDir = new File(infoPathDir);
		if (!fDir.exists()){
			fDir.mkdirs();
		}
		
		//파일Copy (messageBody -> NAS.SHARE)
		String fromPath=bodyFilePath;
		String toPath=multiFileFullPath;
	
		if(arrMulti != null){
			for(int i=0 ; i < arrMulti.length ; i++){
				String fromFullPath="";
				String toFullPath="";
				
				fromFullPath = fromPath+arrMulti[i];
				toFullPath = toPath+arrMulti[i];
				
		        FileCopy copy = new FileCopy(fromFullPath, toFullPath);
		        copy.start();
			}
		}

		//StoryParts_KTFMMS ID값 생성 (Unique)
		String partsId ="";									
		StringBuffer sb = new StringBuffer();
		sb.append("--").append("StoryParts_KTFMMS::");
		sb.append(RandomString.getString(3, RandomString.TYPE_NUMBER)).append("_");
		sb.append(RandomString.getString(4, RandomString.TYPE_NUMBER + RandomString.TYPE_UPPER_ALPHA)).append("_");
		sb.append(RandomString.getString(8, RandomString.TYPE_NUMBER + RandomString.TYPE_UPPER_ALPHA));
		sb.append("--");
		partsId = sb.toString();	
		
		//Body생성시 필요한 배열
		HashMap<Object,String> arrContentId = new HashMap<Object,String>();
		HashMap<Object,String> arrMineType = new HashMap<Object,String>();
		HashMap<Object,String> arrFileName = new HashMap<Object,String>();
		HashMap<Object,String> arrBase64enc = new HashMap<Object,String>();
		
		String contentId="";
		String contentType="";
		String fileName="";
		String ext="";

		
		//멀티파일 개수만큼 처리 (base64인코딩, Info생성)
		if(arrMulti != null){
			for(int i=0 ; i < arrMulti.length ; i++){
				
				contentId = arrMulti[i];
				fileName = arrMulti[i];
				ext = CommonUtils.getExt(arrMulti[i]);
				contentType = BodyCreateUtil.getMineType(ext);

				arrContentId.put(i, contentId);
				arrMineType.put(i, contentType);
				arrFileName.put(i, fileName);
				arrBase64enc.put(i, BodyCreateUtil.getEncBase64(multiFileFullPath+arrMulti[i]));
				
				//Info생성 
				ContentSubInfo csInfo = new ContentSubInfo(contentId,contentType,fileName);
				cs.add(csInfo);
			}
		}
		
		//Info파일 생성(크로샷연동 파일)
		ContentInfo createInfo = new ContentInfo(msgSbst,cs,createInfoFileFullPath);
		
		//HTM파일쓰기
		BufferedWriter out = new BufferedWriter(new FileWriter(createHtmFileFullPath));	
		out.append(partsId);out.newLine();
		out.append("Content-Type: text/html; charset='euc-kr';");out.newLine();
		out.append("Content-Transfer-Encoding: 8bit");out.newLine();
		out.append("<HTML><HEAD></HEAD>");out.newLine();
		out.append("<BODY bgcolor=white>");out.newLine();
		out.append(msgTitleNm+"<br>");out.newLine();
		
		if(arrMulti != null){
			for(int i=0 ; i < arrMulti.length ; i++ ){
				
				//이미지 or 동영상 구분
				String multiType= BodyCreateUtil.getMultiType(CommonUtils.getExt(arrMulti[i]));	
				
				if("I".equals(multiType)){
					out.append("<IMG SRC='cid:"+arrFileName.get(i)+"'>");out.newLine();	
				}else{
					out.append("<A href='cid:"+arrFileName.get(i)+"'><font color='blue'>동영상보기</font></A>");out.newLine();
				}
			}
		}
		
		out.append("<br>"+msgSbst+"</BODY></HTML>");out.newLine();
		out.append(partsId);out.newLine();

		if(arrMulti != null){
			for(int i=0 ; i < arrMulti.length ; i++ ){
				out.append("Content-Type: "+arrMineType.get(i)+"");out.newLine();
				out.append("Content-Transfer-Encoding: base64");out.newLine();
				out.append("Content-ID: <"+arrContentId.get(i)+">");out.newLine();
				out.append(arrBase64enc.get(i));out.newLine();
				out.append(partsId);out.newLine();
			}
		}
		
		out.close();
		
		map.put("htmFileName", createHtmFileFullPath);
		map.put("infoFileName", createInfoFileFullPath);
		map.put("SUCCESS", "S");
		
		return map;
	}
	
	
	/**
	 * <pre>
	 * 이미지,동영상 Base64인코딩 
	 * </pre>
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String getEncBase64(String filePath) throws IOException{
		
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		   
		// img,mp4 byte처리후 Base64 인코딩
		byte[] by = new byte[(int)file.length()];
		int len = 0;
		
		while( (len=in.read(by)) != -1){
			bout.write(by, 0, len);
		}
		byte[] imgbuf = bout.toByteArray();
		
		String encBase64 = new sun.misc.BASE64Encoder().encode(imgbuf);
		
		bout.close();
		in.close();

		return encBase64;
	}
	
	/**
	 * <pre>
	 * Mine Type 추출
	 * </pre>
	 * @param ext
	 * @return
	 */
	public static String getMineType(String ext){
		
		String type="";
		
		if(ServiceConstants.File.FILE_EXT_GIF.equals(ext)){
			type=ServiceConstants.File.FILE_MIME_TYPE_GIF;
		}else if(ServiceConstants.File.FILE_EXT_JPG.equals(ext)){
			type=ServiceConstants.File.FILE_MIME_TYPE_JPG;
		}else if("mp4".equals(ext)){
			type=ServiceConstants.File.FILE_MIME_TYPE_MP4;
		}else if("3gp".equals(ext)){
			type=ServiceConstants.File.FILE_MIME_TYPE_3GP;
		}else if("k3g".equals(ext)){
			type=ServiceConstants.File.FILE_MIME_TYPE_K3G;
		}else{
			type="FAIL";
		}
		
		return type;
	}
	
	/**
	 * <pre>
	 * Img or Mov 구분값 추출
	 * </pre>
	 * @param ext
	 * @return
	 */
	public static String getMultiType(String ext){
		
		String type="";
		
		if(ServiceConstants.File.FILE_EXT_GIF.equals(ext)){
			type="I";
		}else if(ServiceConstants.File.FILE_EXT_JPG.equals(ext)){
			type="I";
		}else if("mp4".equals(ext)){
			type="M";
		}else if("3gp".equals(ext)){
			type="M";
		}else if("k3g".equals(ext)){
			type="M";
		}
		
		return type;
	}
	
}
