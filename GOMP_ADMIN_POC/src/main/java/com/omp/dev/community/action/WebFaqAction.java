package com.omp.dev.community.action;

import java.util.List;
import com.omp.dev.common.util.CommonUtil;
import com.omp.dev.community.model.WebFaq;
import com.omp.dev.community.service.WebFaqService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.StringUtil;

public class WebFaqAction extends BaseAction{
	private WebFaqService faqService;
	private WebFaq faq = null;
	private List<WebFaq> faqSub = null;
	private List<WebFaq> faqList = null;
	private List categoryList = null;
	private long srchCnt = 0;
	
	public WebFaqAction() {
		faqService = new WebFaqService();
	}
	
	@SuppressWarnings("rawtypes")
	public String selectFaqList() {

		if (faq == null) {
			faq = new WebFaq();
		}
		
		faq.setCtgrCd(com.omp.dev.common.Constants.CTGR_FAQ_WEB);
		String sSearchType = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchType")))) ? faq.getSearchType() : this.req
				.getParameter("searchType");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? faq.getSearchValue() : this.req
				.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + faq.getPage().getNo() : this.req
				.getParameter("pageNo");
		sPageNo = StringUtil.nvlStr(sPageNo, "1");
		faq.setSearchType(CommonUtil.removeSpecial(sSearchType));
		faq.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		faq.getPage().setNo(Integer.parseInt(sPageNo));
		faq.getPage().setRows(15);
		faq.setDelYn("N");
		faq.setOpenYn("Y");

		faqList = faqService.selectFaqList(faq);
		srchCnt = ((PagenateList) faqList).getTotalCount();
		categoryList = faqService.selectCategoryList(com.omp.dev.common.Constants.CTGR_FAQ_WEB);
		return SUCCESS;
	}
	
	public String selectFaq() {

		if (faq == null) {
			faq = new WebFaq();
		}
		String sSearchType = faq.getSearchType();
		String sSearchValue = faq.getSearchValue();
		int nPageNo = faq.getPage().getNo();
		if (faq.getFaqId() != 0) {
			faq.setCtgrCd(com.omp.dev.common.Constants.CTGR_FAQ_WEB);
			faq = faqService.selectFaq(faq);
		}
		
		faq.setSearchType(sSearchType);
		faq.setSearchValue(sSearchValue);
		faq.getPage().setNo(nPageNo);
		if(faq.getGid() != null){
			faqSub = faqService.selectFileDownloadList(Integer.parseInt(faq.getGid()));
		}
		return SUCCESS;
	}

	public WebFaqService getFaqService() {
		return faqService;
	}

	public void setFaqService(WebFaqService faqService) {
		this.faqService = faqService;
	}

	public WebFaq getFaq() {
		return faq;
	}

	public void setFaq(WebFaq faq) {
		this.faq = faq;
	}

	public List<WebFaq> getFaqList() {
		return faqList;
	}

	public void setFaqList(List<WebFaq> faqList) {
		this.faqList = faqList;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

	public List<WebFaq> getFaqSub() {
		return faqSub;
	}

	public void setFaqSub(List<WebFaq> faqSub) {
		this.faqSub = faqSub;
	}

	public List getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

}
