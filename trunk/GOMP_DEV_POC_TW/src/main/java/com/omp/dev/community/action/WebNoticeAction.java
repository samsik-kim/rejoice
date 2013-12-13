package com.omp.dev.community.action;

import java.util.List;
import com.omp.dev.community.model.Notice;
import com.omp.dev.community.service.WebNoticeService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;
@SuppressWarnings("serial")
public class WebNoticeAction extends BaseAction{
	private WebNoticeService noticeService;
	private Notice notice;
	private List<Notice> noticeList;
	private long srchCnt = 0;
	private List<Notice> noticeSub;
	
	public WebNoticeAction() {
		noticeService = new WebNoticeService();
	}

	/**
	 * 공지사항 리스트
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String selectNoticeList() {
		if (notice == null) {
			notice = new Notice();
		}
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + notice.getPage().getNo() : this.req
				.getParameter("pageNo");
		sPageNo = StringUtil.nvlStr(sPageNo, "1");
		
		notice.setCtgrCd(com.omp.dev.common.Constants.CTGR_NTC_WEB);
		notice.getPage().setRows(15);
		notice.setDelYn("N");
		notice.setOpenYn("Y");
		notice.getPage().setNo(Integer.parseInt(sPageNo));
		
		noticeList = noticeService.selectNoticePagingList(notice);
		srchCnt = ((PagenateList) noticeList).getTotalCount();

		return SUCCESS;
	}
	
	/**
	 * 공지사항 상세 화면
	 * @return
	 */
	public String selectNotice() {

		if (notice == null) {
			notice = new Notice();
		}
		int nPageNo = notice.getPage().getNo();
		notice.setCtgrCd(com.omp.dev.common.Constants.CTGR_NTC_WEB);
		notice = noticeService.selectNotice(notice);
		notice.getPage().setNo(nPageNo);
		if(notice.getGid()!=null){
			noticeSub = noticeService.selectFileDownloadList(Integer.parseInt(notice.getGid()));
		}
		return SUCCESS;
	}

	public WebNoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(WebNoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

	public List<Notice> getNoticeSub() {
		return noticeSub;
	}

	public void setNoticeSub(List<Notice> noticeSub) {
		this.noticeSub = noticeSub;
	}
	
}