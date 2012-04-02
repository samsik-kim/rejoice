package com.stockinvest.common.util;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.view.AbstractView;

/**
 * JSon 커스텀 뷰 클래스.
 * 
 */
public class JSonView extends AbstractView{
	/**
	 * <pre>
	 * 생성자.
	 * 컨텐트 타입을 "text/plain; charset=UTF-8"으로 설정한다.
	 * </pre>
	 */
	public JSonView(){
		this.setContentType("text/plain; charset=UTF-8");
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType(this.getContentType());
		
		JSONObject jsonObject = (JSONObject)model.get("jsonObject");
		PrintWriter printWriter = null;
		try{
			printWriter = response.getWriter();
			printWriter.write(jsonObject.toString());
			printWriter.flush();
		}finally{
			if(printWriter != null) { printWriter.close(); }	
		}
	}
}