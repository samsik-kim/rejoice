package com.resttemplate.sample.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.resttemplate.rejoiceAPI.bean.ItemBean;

@Controller
@RequestMapping(value="/sample")
public class SampleController {

	/**
	 * properties.xml[value]
	 */
	@Value("#{config['api.url']}") String con_url;

	/**
	 * Concat
	 * properties.xml[value] + Customer Value
	 */
	@Value("#{config['api.url'].concat(' abc')}") private String value1;
	
    /**
     * Default Set
     * properties.xml[value] ? Default Value
     */
    @Value("#{config['sample.prop2']?:'test'}") private String value2;
	
	@RequestMapping(value="/{itemId}" , method = RequestMethod.GET)
	@ResponseBody
	public String onlyOneData(@PathVariable("itemId") String itemId){
		itemId +=  "_JA";
		return itemId;
	}
	
	
	@RequestMapping(value="/items.do")
	public String getItems(ModelMap map){
		RestTemplate restTemplate = new RestTemplate();
		ItemBean bean = new ItemBean();
		bean.setEtc("Req-기타");
		bean.setId("Req-ID");
		bean.setName("Req-NM");
		bean.setNo("Req-01");
		
		//Debug
		System.out.println();
		System.out.println(value2);
		System.out.println();
		
		//RestFul - [POST]
		ItemBean itemBean = restTemplate.postForObject(con_url + "/item/new", bean, ItemBean.class);
		
		map.put("data", itemBean);
		return "sample/itemView";
	}
}