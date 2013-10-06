package com.resttemplate.rejoiceAPI.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.resttemplate.rejoiceAPI.bean.ItemBean;

@Controller
@RequestMapping(value="/rejoice")
public class RejoiceController {

	Logger logger = Logger.getLogger(getClass());
	
	/**
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/{param}" , method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getList(@PathVariable("") String param){
		
		
		return null;
	}
	
	
	@RequestMapping( value = "/item/new" , method = RequestMethod.POST)
	@ResponseBody
	public ItemBean getItem(@RequestBody ItemBean itemBean){
		ItemBean bean = new ItemBean();
		logger.debug("* NO : " + itemBean.getNo());
		
		bean.setEtc("Res - 기타");
		bean.setNo("Res - 99");
		bean.setName("Res - NM");
		bean.setId("Res - ID");
		return bean;
	}
}
