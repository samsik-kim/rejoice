package com.resttemplate.rejoiceAPI.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.ResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.resttemplate.common.exception.ResourceNotFoundException;
import com.resttemplate.rejoiceAPI.bean.ItemBean;
import com.resttemplate.rejoiceAPI.bean.Message;
//import org.apache.log4j.Logger;

/**
 * @comment : RestTemplate Server API [rejoiceAPI]
 * @date    : 2013. 10. 6.
 * @author  : Rejoice
 */
@Controller
@RequestMapping(value="/rejoice")
public class RejoiceController {

	/**
	 * @comment : SLF4J 선언 
	 */
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @comment : Data 조회 요청 (GET)
	 * @date    : 2013. 10. 6.
	 * @author  : Rejoice
	 * @param param
	 * @return List<ItemBean>
	 */
	@RequestMapping(value="/{param}" , method = RequestMethod.GET)
	@ResponseBody
	public List<ItemBean> getList(@PathVariable("param") String param){
		
		// 1. parameter 검증
		if(param.isEmpty()){
			logger.debug("param : {}", param);
		}
		
		// 2. Service 요청
		// TODO 추후 Service 작성
		
		// 3. Response Data [Code, Message ...]
		// TODO 임시 Data 셋팅 
		List<ItemBean> list = new ArrayList<ItemBean>();
		ItemBean bean = null;
		for (int i = 0; i < 5; i++) {
			bean = new ItemBean();
			bean.setNo("[" + "] " + i + " - ITEM_NO_" + i);
			bean.setId("[" + "] " + i + "- ITEM_ID_" + i);
			bean.setName("[" + "] " + i + " - ITEM_NAME_" + i);
			bean.setEtc("[" + "] " + i + " - ITEM_ETC_" + i);
			list.add(bean);
		}
		
		return list;
	}
	
	/**
	 * @comment : Data Save 요청 (POST)
	 * @date    : 2013. 10. 6.
	 * @author  : Rejoice
	 * @param itemBean
	 * @return
	 */
	@RequestMapping( value = "/item/new" , method = RequestMethod.POST)
	@ResponseBody
	public ItemBean getItem(@RequestBody ItemBean itemBean){
		
		// 1. 요청 Data
		logger.debug("* NO : {}" , itemBean.getNo());
		logger.debug("* ETC : {}" , itemBean.getEtc());
		
		// 2. Service 요청 (Save)
		// TODO 추후 Service 작성
		
		// 3. Response 를 위한 객체 Setting
		// TODO 임시 셋팅
		ItemBean bean = new ItemBean();
		// Code & Message
		bean.setCode("200");
		bean.setMessage("SUCCESS");
		// Data
		bean.setEtc("Res - 기타");
		bean.setNo("Res - 99");
		bean.setName("Res - NM");
		bean.setId("Res - ID");
		
		return bean;
	}
	
	@RequestMapping( value = "/xml" , method = RequestMethod.GET )
//	@ResponseStatus( value = HttpStatus.FOUND)
	@ResponseWrapper
	public Message sendFruitMessage(){
		Message msg = new Message();
		
//		msg.setName("KinGyeongBok");
//		map.put("msg", msg);
		return msg;
//		return null;
	}
	
	@RequestMapping( value = "/message/{name}" , method = RequestMethod.GET)
	public String getMessage(@PathVariable String name, ModelMap map){
		Message msg = new Message();
		map.addAttribute("model", msg);
		return "list";
	}
	
//	@RequestMapping(value = "" , method = RequestMethod.PUT)
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
	public void handleNotfoundException(ResourceNotFoundException ex, HttpServletRequest request){
		logger.error("handleNotfoundException : ", ex);
	}
	
}