package com.nmimo.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <pre>
 * requestUrlConnect
 * </pre>
 * @file RequestUrlConnection.java
 * @since 2013. 5. 13.
 * @author Administrator
 */
public class RequestUrlConnection {
	
	/** LOG4J */
	Log logger = LogFactory.getLog(RequestUrlConnection.class);
	
	
	public RequestUrlConnection() {
		super();
	}

	/**
	 * <pre>
	 * PSSO 로그인 연동
	 * </pre>
	 * @param hostUrl - 요청url
	 * @param params  - psso연동 필수 parameter
	 * @throws IOException 
	 */
	public RequestUrlConnection(String hostUrl,String params) throws IOException{
		
		logger.debug("hostUrl -> "+hostUrl);
		logger.debug("params -> "+params);
		
		URL url = null; 
		HttpURLConnection uc = null; 
		PrintWriter out= null; 
		BufferedReader in= null; 
		
		try{
		
			//페이지 연결
			url = new URL(hostUrl); 
			uc = (HttpURLConnection)url.openConnection(); 
			
			uc.setDoOutput(true);	//post방식:true
			uc.setDoInput(true);	//데이터를 첨부하는 경우 true
			uc.setUseCaches(false); 
			
			//데이타 전송
			out = new PrintWriter(uc.getOutputStream()); 
			out.println(params);
			out.flush();
			out.close(); //비운다.
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in  != null)
				in.close();
			if(out  != null)
				out.close();
		}
	}
	
}