package com.omp.dev.community.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.dev.community.model.Notice;
import com.omp.dev.community.model.WebFaq;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class WebFaqService extends AbstractService{
	
	@SuppressWarnings("unchecked")
	public List<WebFaq> selectFaqList(WebFaq faq) {
		return this.commonDAO.queryForPageList("WebFaq.selectFaqPagingList", faq);
	}

	public WebFaq selectFaq(WebFaq faq) {
		WebFaq retFaq = null;
		WebFaq hitFaq = new WebFaq();
		hitFaq.setCtgrCd(faq.getCtgrCd());
		hitFaq.setFaqId(faq.getFaqId());
		try {
			this.commonDAO.update("WebFaq.updateFaqHit", hitFaq);
			retFaq = (WebFaq) this.commonDAO.queryForObject("WebFaq.selectFaq", faq);
		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retFaq;
	}
	
	public List selectCategoryList(String ctgr) {
		try {
			return this.commonDAO.queryForList("WebFaq.selectCategoryList", ctgr);
		} catch (SQLException e) {
			throw new ServiceException("FAQ 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<WebFaq> selectFileDownloadList(int gid) {
		List<WebFaq> faqFile = null;
		try {
			faqFile = this.commonDAO.queryForList("WebFaq.selectFileDownloadList", gid);
		} catch (SQLException e) {
			throw new ServiceException("File Not Found", e);
		}
		return faqFile;
	}
}
