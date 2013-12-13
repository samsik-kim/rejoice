/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 5. 24.    Description
 *
 */
package com.omp.dev.community.action;

import java.io.PrintWriter;
import java.util.List;

import org.json.JSONObject;

import com.omp.commons.action.BaseAction;
import com.omp.dev.community.model.Ctgr;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.util.GLogger;
import com.omp.dev.common.Constants;
import com.omp.dev.community.model.Qna;
import com.omp.dev.community.service.QnaService;
import com.omp.dev.community.service.QnaServiceImpl;

@SuppressWarnings("serial")
public class QnaAction extends BaseAction {
	private static GLogger logger = new GLogger(QnaAction.class);

	private Qna qna = null;
	private QnaService service = null;
	private List<Ctgr> ctgrList = null; // Question Type (FAQ Category)
	private List<CommCode> emailList = null;
	private String qna_cd = "DQNA";

	public QnaAction() {
		service = new QnaServiceImpl();
	}


	/**
	 * QnA Main
	 * 
	 * @return
	 */
	public String newQna() throws Exception {
		ctgrList = service.getCategoryList(); // Question Type (FAQ Category)
		
		emailList = CacheCommCode.getCommCode(Constants.CODE_EMAIL);

		return SUCCESS;
	}


	/**
	 * QnA Register
	 * 
	 * @return
	 */
	public void ajaxAddQna() throws Exception {
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;

		try {
			if(qna == null){
				qna = new Qna();
			}

			qna.setQnaTpCd(Constants.CODE_QNA_TP_CD_DEV); // Developer Direct Inquiry
			qna.setPrcStatCd(Constants.CODE_QNA_PRC_STAT_CD_WAIT); // Progress State-Stand By
			qna.setDelYn("N");
			
			JSONObject jsonObject = new JSONObject();

			service.addQna(this.req, qna);
			
			jsonObject.put("result", SUCCESS);

			out = res.getWriter();
			
			out.write(jsonObject.toString());
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}


	public Qna getQna() {
		return qna;
	}


	public void setQna(Qna qna) {
		this.qna = qna;
	}


	public QnaService getService() {
		return service;
	}


	public void setService(QnaService service) {
		this.service = service;
	}


	public List<Ctgr> getCtgrList() {
		return ctgrList;
	}


	public void setCtgrList(List<Ctgr> ctgrList) {
		this.ctgrList = ctgrList;
	}


	public List<CommCode> getEmailList() {
		return emailList;
	}


	public void setEmailList(List<CommCode> emailList) {
		this.emailList = emailList;
	}
	

	public String getQna_cd() {
		return qna_cd;
	}


	public void setQna_cd(String qna_cd) {
		this.qna_cd = qna_cd;
	}

}
