package com.resttemplate.sample.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.dozer.util.mapping.Mapper;
import net.sf.dozer.util.mapping.MappingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.resttemplate.rejoiceAPI.bean.ItemBean;
import com.resttemplate.sample.bean.SampleBean;
import com.resttemplate.sample.bean.TestBean;

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
	
	@RequestMapping(value = "/testForm.do")
	public String testForm(ModelMap map){
//		ImgValidator validator = new ImgValidator();
//		validator.validate(info, result);
//		if(result.hasErrors()){
//			
//		}
		return "sample/jqueryForm";
	}
	
	@RequestMapping(value = "/ajaxData.do")
	public String ajaxInnerData(ModelMap map, @ModelAttribute SampleBean bean){
		HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("a", "1");
		hashMap.put("b", "2");
		hashMap.put("c", "3");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		list.add(hashMap);
		hashMap1.put("data", list);
		map.put("url", con_url);
		map.put("mode", System.getProperty("server.mode"));
		map.put("list", hashMap1);
		
		return "sample/innerData";
	}
	
	@RequestMapping(value="/testBean.do")
	public String testGetBean(HttpSession session){
//		TestBean testBean = new TestBean();
//		testBean.setA("23");
//		testBean.setB("33");
//		session.setAttribute("testBean", testBean);
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/restTemplate-servlet.xml");
//		TestBean bean = (TestBean) context.getBean("testBean");
//		bean.getA();
		return "sample/testBean";
	}
	
	public String dozerTest(){
//		MappingUtils.
		
		SampleBean sb = new SampleBean();
		String a=null;
		int b = 0;
		 
		try {
			ItemBean ib = (ItemBean) Mapper.map(sb, ItemBean.class);
		} catch (MappingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping( value = "/sampleJs.do")
	public String sampleJs(){
		return "sample/sampleJs";
	}
	
	@RequestMapping( value = "/js.do")
	public String js(){
		return "sample/js";
	}
	
	@RequestMapping(value="/multiFileuploadForm.do")
	public void multiFileuploadForm(){ } 

	@RequestMapping(value="/file.do")
	public void file(){ }
}