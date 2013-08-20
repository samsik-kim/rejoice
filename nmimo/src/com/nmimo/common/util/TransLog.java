package com.nmimo.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nmimo.common.util.DateUtils;

public class TransLog {
	/** 전문 전용 Logger */
	private static final TransLog instance = new TransLog();
	/** 전문 출력 시작 타이틀 */
	private static final String header = "::::::::::";
	/** 전문 출력 시작 타이틀 */
	private static final String seperator = "\n";	
	/** 전문 출력 키,값 구분 문자 */
	private static final String match = "=";
	/** 전문 출력 들여 쓰기 문자 */
	@SuppressWarnings("unused")
	private static final String indentation = "    ";	
	/** 전문 출력 종료 타이틀 */
	private static final String footer = "::::::::::";
	/** log4j */
	Log logger = LogFactory.getLog("TRANS_LOG");
	
	/**
	 * <pre>
	 * 로그 문자열을 입력 받아 log4j에 로그를 출력하는 기능을 제공한다.
	 * </pre>
	 * @param service String
	 * @param method String
	 * @param title String
	 * @param logData List<Map>
	 */
	public void write(String service, String method, String title, List<Map<String, String>> logData){
		logger.info("___________________________________________________________________________");
		logger.info("");
		logger.info(DateUtils.getToday("GGG yyyy MMMMM dd EEE  HH:mm:ss"));
		logger.info(makeXML(service, method, title, logData));
	}
	
	/**
	 * <pre>
	 * 로그 문자열을 입력 받아 log4j에 로그를 출력하는 기능을 제공한다.
	 * </pre>
	 * @param service String
	 * @param method String
	 * @param title String
	 * @param logData Map
	 */
	public void write(String service, String method, String title, Map<String, String> logMap){
		List<Map<String, String>> logData = new ArrayList<Map<String, String>>();
		logData.add(logMap);
		write(service, method, title, logData);
	}
	
	/**
	 * <pre>
	 * log4j에 로그를 출력할 문자열 포맷을 만들어 반환하는 기능을 제공한다.
	 * </pre>
	 * @param service String
	 * @param method String
	 * @param title String
	 * @param logData List<Map>
	 * @return String 문자열 포맷
	 */	
	@SuppressWarnings("unused")
	private String makeString(String service, String method, String title, List<Map<String, String>> logData){
		StringBuffer logBuffer = new StringBuffer("\n");
		String key;
		int iLoop = logData == null ? 0 : logData.size();
		int iLoopSymbol = iLoop-1;
		
		logBuffer.append("\t").append(header).append("[").append(service).append("] [").append(method).append("] ").append(title).append(" 시작 ").append(header).append("\n");
		
		for(int iCnt=0; iCnt < iLoop; iCnt++){
			Map<String, String> logMap = logData.get(iCnt);
			Iterator<String> keys = logMap.keySet().iterator();

			while(keys.hasNext()){
				key = keys.next();
				logBuffer.append("\t\t").append(key).append("[").append(iCnt).append("]").append(match).append(logMap.get(key) == null ? "" : logMap.get(key)).append("\n");
			}
			
			if(iCnt < iLoopSymbol){
				logBuffer.append(seperator);
			}
		}

		logBuffer.append("\t").append(footer).append("[").append(service).append("] [").append(method).append("] ").append(title).append(" 끝 ").append(footer).append("\n");
		return logBuffer.toString();
	}
	
	/**
	 * <pre>
	 * log4j에 로그를 출력할 XML 포맷 문자열을 만들어 반환하는 기능을 제공한다.
	 * </pre>
	 * @param service String
	 * @param method String
	 * @param title String
	 * @param logData List<Map>
	 * @return String XML 포맷 문자열
	 */	
	private String makeXML(String service, String method, String title, List<Map<String, String>> logData){
		StringBuffer logBuffer = new StringBuffer("\n");
		String key;
		int iLoop = logData == null ? 0 : logData.size();
		
		logBuffer.append("\t<").append(service).append(" method='").append(method).append("' comment='").append(title).append("'>\n");
	
		for(int iCnt=0; iCnt < iLoop; iCnt++){
			Map<String, String> logMap = logData.get(iCnt);
			Iterator<String> keys = logMap.keySet().iterator();

			logBuffer.append("\t\t<Loop i=").append(iCnt).append(">\n");
			
			while(keys.hasNext()){
				key = keys.next();
//				logBuffer.append("\t\t\t<").append(key).append("><![CDATA[").append(logMap.get(key) == null ? "" : logMap.get(key).toString()).append("]]></").append(key).append(">\n");
				logBuffer.append("\t\t\t<").append(key).append("><").append(logMap.get(key) == null ? "" : logMap.get(key).toString()).append("></").append(key).append(">\n");
			}
			
			logBuffer.append("\t\t</Loop>\n");
		}
		
		logBuffer.append("\t</").append(service).append(">\n");
		return logBuffer.toString();
	}	

	/**
	 * <pre>
	 * symbol 문자열을 iTimes 만큼 반복하여 만든 문자열을 반환하는 기능을 제공한다.
	 * </pre>
	 * @param symbol String
	 * @param iTimes int
	 * @return String 
	 */	
	@SuppressWarnings("unused")
	private String timesStr(String symbol, int iTimes){
		StringBuffer sb = new StringBuffer(symbol.length()*iTimes);
		for(int i=0; i<iTimes; i++){
			sb.append(symbol);
		}
		return sb.toString();
	}
	
	/**
	 * <pre>
	 * 전문 출력 로거 객체를 반환하는 기능을 제공한다.
	 * </pre>	
	 * @return TransLog
	 */
	public static TransLog getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * <pre>
	 * 송신 데이터 전문을 남기기 위해 송신 쿼리를 분석한다.
	 * </pre>
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unused")
	public HashMap<String, String> sendDataAnalysis(String query){
		String queryList[] = query.split("<CRLF>");
		
		HashMap<String, String> listMap = new HashMap<String, String>();
		for(int i=0; i<queryList.length; i++){
			String tmpList [] = queryList[i].split(":");
			listMap.put(tmpList[0], tmpList[1].trim());
		}
		return listMap;
	}
	
}