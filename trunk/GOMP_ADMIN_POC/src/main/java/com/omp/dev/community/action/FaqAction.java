package com.omp.dev.community.action;

import java.util.List;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.dev.community.model.Ctgr;
import com.omp.dev.community.service.ComService;
import com.omp.dev.community.service.ComServiceImpl;
import com.omp.dev.common.Constants;
import com.omp.dev.community.model.Faq;
import com.omp.dev.community.service.FaqService;
import com.omp.dev.community.service.FaqServiceImpl;

/**
 * FAQ Action
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class FaqAction extends BaseAction {

	private Faq faq = null;
	private List<Faq> list = null;
	private List<Ctgr> ctgrList = null; // Category List
	private FaqService service = null;
	private ComService cs = null;


	public FaqAction() {
		service = new FaqServiceImpl();
		cs = new ComServiceImpl();
	}


	/**
	 * FAQ List Search
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listFaq() {
		// FAQ Category List
		ctgrList = cs.getCategoryList(Constants.CTGR_FAQ_DEV);

		if(faq == null) {
			faq = new Faq();
			if (ctgrList.size() > 0) {
				faq.setCtgrCd(((Ctgr)ctgrList.get(0)).getCtgrCd());
			}
		}
		
		list = service.getFaqList(faq);
		
		return SUCCESS;
	}
	
	/**
	 * Faq Hit Number Increase
	 */
	public void ajaxFaqHitIncrease() {
		if(faq.getFaqId() == null && faq.getCtgrCd() == null) {
			throw new NoticeException("Error");
		}
		
		service.updateFaqHit(faq);
	}
	
	public Faq getFaq() {
		return faq;
	}


	public void setFaq(Faq faq) {
		this.faq = faq;
	}


	public List<Faq> getList() {
		return list;
	}


	public void setList(List<Faq> list) {
		this.list = list;
	}


	public List<Ctgr> getCtgrList() {
		return ctgrList;
	}


	public void setCtgrList(List<Ctgr> ctgrList) {
		this.ctgrList = ctgrList;
	}


	public FaqService getService() {
		return service;
	}


	public void setService(FaqService service) {
		this.service = service;
	}

}