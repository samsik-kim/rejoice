package com.omp.dev.contents.action;

import java.io.PrintWriter;
import java.util.List;

import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.contents.model.TestPhoneSet;
import com.omp.dev.contents.service.TestPhoneSetService;
import com.omp.dev.contents.service.TestPhoneSetServiceImpl;
import com.omp.dev.user.model.Session;

/**
 * Test Phone Set
 * 
 * @author aylee
 *
 */
@SuppressWarnings("serial")
public class TestPhoneSetAction extends BaseAction {

	private static GLogger logger = new GLogger(TestPhoneSetAction.class);
	
	private     TestPhoneSet 					testPhSet;
	private 	TestPhoneSetService   testPhoneSetService;
	private 	List<TestPhoneSet>				testPhSetList;
	private 	int							testPhSetCnt=0;
	private 	String							otaSeq="";
	private 	String							otaMac="";

	public TestPhoneSetAction() {
		testPhSet					= new TestPhoneSet();
		testPhoneSetService	= new TestPhoneSetServiceImpl();
	}
	
	/**
	 * Test Phone Load
	 * 
	 * @return
	 */
	public String testPhoneLoad() throws Exception{
		if (SessionUtil.existMemberSession(this.req)) {
			
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			testPhSetList=testPhoneSetService.getTestPhoneList(memberInfo.getMbrNo());
			testPhSetCnt = testPhSetList.size();
		}
		return SUCCESS;
	}
	
	/**
	 * Test Phone Delete
	 * 
	 * @return
	 */
	public void ajaxDeletePhone() throws Exception{
		
		this.res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer 		= null;
		JSONObject jsonObject 	= new JSONObject();
		
		try {
			
			if (SessionUtil.existMemberSession(this.req)) {
				Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
				testPhSet.setMbrNo(memberInfo.getMbrNo());
				testPhSet.setTmpSeq(otaSeq);
				testPhoneSetService.delTestPhone(testPhSet);
				
				testPhSetList=testPhoneSetService.getTestPhoneList(memberInfo.getMbrNo());
				testPhSetCnt = testPhSetList.size();
			} 
			
		} catch (Exception e) {
			logger.error("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally {
			
			try {
				
				logger.debug("* testPhSetCnt 	: {0}", new Object[] {testPhSetCnt});
				
				jsonObject.put("testPhSetCnt", testPhSetCnt);
			
				writer = this.res.getWriter();
				writer.write(jsonObject.toString());
				
			} catch (Exception jse) {
				logger.error("Test Phone Delete Fail" , jse);
			}
			
			if(writer != null) { writer.close(); }
		}
	}
	
	/**
	 * Test Phone Insert
	 * 
	 * @return
	 */
	public String testPhoneIns() throws Exception{
		if (SessionUtil.existMemberSession(this.req)) {
			Session memberInfo = (Session) SessionUtil.getMemberSession(this.req);
			
			String[] tempSeq =this.otaSeq.split("#");
			String[] tempMac =this.otaMac.split("#");
			
			testPhoneSetService.insertTestPhone(tempSeq,tempMac,memberInfo.getMbrNo());
		}
		return SUCCESS;
	}
	
	public TestPhoneSet getTestPhSet() {
		return testPhSet;
	}

	public void setTestPhSet(TestPhoneSet testPhSet) {
		this.testPhSet = testPhSet;
	}

	public TestPhoneSetService getTestPhoneSetService() {
		return testPhoneSetService;
	}

	public void setTestPhoneSetService(TestPhoneSetService testPhoneSetService) {
		this.testPhoneSetService = testPhoneSetService;
	}

	public List<TestPhoneSet> getTestPhSetList() {
		return testPhSetList;
	}

	public void setTestPhSetList(List<TestPhoneSet> testPhSetList) {
		this.testPhSetList = testPhSetList;
	}

	public int getTestPhSetCnt() {
		return testPhSetCnt;
	}

	public void setTestPhSetCnt(int testPhSetCnt) {
		this.testPhSetCnt = testPhSetCnt;
	}
	
	public String getOtaSeq() {
		return otaSeq;
	}

	public void setOtaSeq(String otaSeq) {
		this.otaSeq = otaSeq;
	}

	public String getOtaMac() {
		return otaMac;
	}

	public void setOtaMac(String otaMac) {
		this.otaMac = otaMac;
	}

}
