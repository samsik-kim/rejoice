package com.omp.admin.community.notice.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import pat.neocore.io.FileUtility;

import com.omp.admin.common.Constants;
import com.omp.admin.common.model.FileSupport;
import com.omp.admin.common.service.FileSupportService;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.community.notice.model.Notice;
import com.omp.admin.community.notice.service.NoticeService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.BaseException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * NOTICE
 * @author soohee
 */
@SuppressWarnings("serial")
public class NoticeAction extends BaseAction {

	private GLogger log = new GLogger(NoticeAction.class);

	private NoticeService noticeService;
	private FileSupportService fileSupportService;

	private String sFileUploadImgLimitSize = "1048576";
	private String sFileUploadEtcLimitSize = "10485760";
	private String sFileUploadImgExt = "gif|jpg|jpeg|png";
	private String sFileUploadEtcExt = "htm|html|zip|rar|alz|doc|ppt|pdf|txt|docx|xls|xlsx|pptx|hwp";

	private Notice notice;
	private List<Notice> noticeList;

	private FileSupport fileSupport;
	private List<FileSupport> fileSupportList;
	private int cntFileSupport = 0;

	private Map<String, Object> result;

	private long srchCnt = 0;
	private String mode = null;

	private String ctgrCd = null;

	private String srchFlag = "";

	public NoticeAction() {
		noticeService = new NoticeService();
		fileSupportService = new FileSupportService();
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

		this.setStep("makeNoticeCriteria");
		result = makeNoticeCriteria(result);

		if ("".equals(StringUtil.nvlStr(ctgrCd))) {
			ctgrCd = Constants.CTGR_NTC_DEV;
		}
		notice.setCtgrCd(ctgrCd);

		srchFlag = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchFlag")))) ? srchFlag : this.req.getParameter("srchFlag");
		if ("".equals(StringUtil.nvlStr(srchFlag))) {
			return SUCCESS;
		}

		String sSearchOpenYn = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchOpenYn")))) ? notice.getSearchOpenYn() : this.req
				.getParameter("searchOpenYn");
		String sStartDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("startDate")))) ? notice.getStartDate() : this.req
				.getParameter("startDate");
		String sEndDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("endDate")))) ? notice.getEndDate() : this.req
				.getParameter("endDate");
		String sSearchType = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchType")))) ? notice.getSearchType() : this.req
				.getParameter("searchType");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? notice.getSearchValue() : this.req
				.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + notice.getPage().getNo() : this.req
				.getParameter("pageNo");
		sPageNo = StringUtil.nvlStr(sPageNo, "1");

		// IE6
		try {
			if (sSearchValue != null) {
				sSearchValue = StringUtil.nvlStr(sSearchValue).replace("@", "%");
				sSearchValue = URLDecoder.decode(sSearchValue, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLDecoder.decode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}

		notice.setSearchOpenYn(sSearchOpenYn);
		notice.setStartDate(sStartDate);
		notice.setEndDate(sEndDate);
		notice.setSearchType(CommonUtil.removeSpecial(sSearchType));
		notice.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		notice.getPage().setNo(Integer.parseInt(sPageNo));

		if ("".equals(StringUtil.nvlStr(notice.getStartDate()))) {
			notice.setStartDate(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), -1), "yyyyMMdd"));
			notice.setEndDate(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), 0), "yyyyMMdd"));
		}

		notice.setDelYn("N");
		notice.setOpenYn(notice.getSearchOpenYn());
		notice.setSearchValue(CommonUtil.removeSpecial(notice.getSearchValue()));

		this.setStep("CallServiceSelectNoticePagingList");
		noticeList = noticeService.selectNoticePagingList(notice);
		srchCnt = ((PagenateList) noticeList).getTotalCount();

		return SUCCESS;
	}

	/**
	 * 공지사항 리스트에서 필요한 검색조건 세팅
	 * @param result
	 * @return
	 */
	private Map<String, Object> makeNoticeCriteria(Map<String, Object> result) {

		if (result == null) {
			result = new HashMap<String, Object>();
		}

		Map<String, String> radioMap = new LinkedHashMap<String, String>();
		radioMap.put("", LocalizeMessage.getLocalizedMessage("전체"));
		radioMap.put(Constants.YES, LocalizeMessage.getLocalizedMessage("노출"));
		radioMap.put(Constants.NO, LocalizeMessage.getLocalizedMessage("숨김"));
		//radioMap.put("", "全部 ");
		//radioMap.put(Constants.YES, "公開 ");
		//radioMap.put(Constants.NO, "隱藏 ");

		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("title", LocalizeMessage.getLocalizedMessage("제목"));
		selectMap.put("dscr", LocalizeMessage.getLocalizedMessage("내용"));
		selectMap.put("both", LocalizeMessage.getLocalizedMessage("제목+내용"));
		//selectMap.put("title", "標題");
		//selectMap.put("dscr", "內容");
		//selectMap.put("both", "標題+內容");

		result.put("radioMap", radioMap);
		result.put("selectMap", selectMap);

		return result;
	}

	/**
	 * 여러개의 공지 상태(메인으로,삭제,노출,숨김) 업데이트
	 * @return
	 */
	public String updateNoticeList() {

		if (notice == null) {
			notice = new Notice();
		}

		if (log.isDebugEnabled())
			log.debug("notice.getSelectedNoticeId() : " + notice.getSelectedNoticeId());

		if (!"".equals(StringUtil.nvlStr(notice.getSelectedNoticeId()))) {

			Notice noticeTemp = new Notice();

			noticeTemp.setSelectedNoticeId(notice.getSelectedNoticeId());

			if ("D".equals(mode)) {
				noticeTemp.setDelYn(Constants.YES);
			} else if ("Y".equals(mode)) {
				noticeTemp.setOpenYn(Constants.YES);
			} else if ("N".equals(mode)) {
				noticeTemp.setOpenYn(Constants.NO);
			} else if ("M".equals(mode)) {
				noticeTemp.setMainOpenYn(Constants.YES);
				noticeTemp.setOpenYn(Constants.YES);
			}

			log.debug("noticeTemp.getDelYn() : " + noticeTemp.getDelYn());
			log.debug("noticeTemp.getOpenYn() : " + noticeTemp.getOpenYn());
			log.debug("noticeTemp.getMainOpenYn() : " + noticeTemp.getMainOpenYn());

			this.setStep("CallServiceUpdateNotice");
			noticeService.updateNotice(noticeTemp, null, "");

			// IE6
			String sSearchValue = notice.getSearchValue();
			try {
				if (sSearchValue != null) {
					sSearchValue = URLEncoder.encode(notice.getSearchValue(), "UTF-8");
					sSearchValue = sSearchValue.replace("%", "@");
				}
			} catch (UnsupportedEncodingException e) {
				log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
				sSearchValue = "";
			}
			notice.setSearchValue(sSearchValue);

		}

		return SUCCESS;
	}

	/**
	 * 등록/수정 화면
	 * @return
	 */
	public String selectNotice() {

		if (notice == null) {
			notice = new Notice();
		}

		if (fileSupport == null) {
			fileSupport = new FileSupport();
		}

		String sSearchOpenYn = notice.getSearchOpenYn();
		String sStartDate = notice.getStartDate();
		String sEndDate = notice.getEndDate();
		String sSearchType = notice.getSearchType();
		String sSearchValue = notice.getSearchValue();
		int nPageNo = notice.getPage().getNo();

		result = new HashMap<String, Object>();

		Map<String, String> radioMap = new HashMap<String, String>();
		radioMap.put(Constants.YES, LocalizeMessage.getLocalizedMessage("노출"));
		radioMap.put(Constants.NO, LocalizeMessage.getLocalizedMessage("숨김"));
		result.put("radioMap", radioMap);

		if (log.isDebugEnabled())
			log.debug("notice.getNoticeId() : " + notice.getNoticeId());

		if (notice.getNoticeId() != 0) { // 수정

			this.setStep("CallServiceSelectNotice");
			notice = noticeService.selectNotice(notice);

		}

		notice.setUploadImgLimit(sFileUploadImgLimitSize);
		notice.setUploadEtcLimit(sFileUploadEtcLimitSize);
		notice.setUploadImgExt(sFileUploadImgExt);
		notice.setUploadEtcExt(sFileUploadEtcExt);

		if (notice.getNoticeId() != 0) { // 수정일 경우

			//첨부파일정보
			try {

				fileSupport.setGid(StringUtil.nvlStr(notice.getGid()));
				if (!"".equals(fileSupport.getGid())) {
					this.setStep("CallServiceSelectFileDownloadList");
					fileSupportList = fileSupportService.selectFileDownloadList(fileSupport);
				}
				if (fileSupportList != null) {
					cntFileSupport = fileSupportList.size();
				}

			} catch (Exception e) {
				throw new BaseException("# Error Notice noticeView()", e);
			}

		}

		notice.setSearchOpenYn(sSearchOpenYn);
		notice.setStartDate(sStartDate);
		notice.setEndDate(sEndDate);
		notice.setSearchType(sSearchType);
		notice.setSearchValue(sSearchValue);
		notice.getPage().setNo(nPageNo);

		return SUCCESS;
	}

	/**
	 * 등록/수정 하기
	 * @return
	 */
	public String processNotice() throws Exception {

		if (notice == null) {
			notice = new Notice();
		}

		// Check Insert or Update
		boolean bIsNew = (notice.getNoticeId() == 0) ? true : false;

		String sFilePath = this.conf.getString("omp.common.path.http-share.common.notice");
		String sFileUrl = this.conf.getString("omp.common.url.http-share.common.notice");
		if (log.isInfoEnabled()) {
			log.info("omp.common.path.http-share.common.notice : {0}", new Object[] { sFilePath });
		}

		notice.setUploadImgLimit(sFileUploadImgLimitSize);
		notice.setUploadEtcLimit(sFileUploadEtcLimitSize);
		notice.setUploadImgExt(sFileUploadImgExt);
		notice.setUploadEtcExt(sFileUploadEtcExt);

		if (bIsNew) { // 신규
			// 신규 등록이면 Seq를 미리 발급 받는다.(첨부파일 Link를 위해서 Seq를 먼저 가져 온다.)
			this.setStep("CallServiceGetNoticeIdSeq");
			long lNoticeIdSeq = noticeService.getNoticeIdSeq();
			log.debug("lNoticeIdSeq : " + lNoticeIdSeq);
			notice.setNoticeId(lNoticeIdSeq);
		}
		if (log.isInfoEnabled())
			log.info("notice.getNoticeId() : {0}", new Object[] { notice.getNoticeId() });

		// Update Upload File
		String sDelAsisFid = "";

		FileSupport tmpFileSupport = null;
		List<FileSupport> tmpFileSupportList = null;

		if (!bIsNew) {
			String sAsisImgFid = StringUtil.nvlStr(notice.getAsisImgFid());
			if ("".equals(StringUtil.nvlStr(notice.getTobeImgFid()))) {
				sDelAsisFid = sAsisImgFid;
			}
			String sAsisEtcFid = StringUtil.nvlStr(notice.getAsisEtcFid());
			if ("".equals(StringUtil.nvlStr(notice.getTobeEtcFid()))) {
				if ("".equals(sDelAsisFid)) {
					sDelAsisFid = sAsisEtcFid;
				} else {
					sDelAsisFid = sDelAsisFid + "," + sAsisEtcFid;
				}
			}
		}

		//첨부파일업로드 처리
		if (notice.getAttFileImg() != null) {

			tmpFileSupportList = new ArrayList<FileSupport>();

			if ("".equals(StringUtil.nvlStr(notice.getGid()))) {
				this.setStep("CallServiceSelectFileUploadGrpSeq");
				Long lGid = fileSupportService.selectFileUploadGrpSeq();
				notice.setGid("" + lGid);

				if (log.isDebugEnabled())
					log.debug("New NOTICE GID : " + notice.getGid());
			}

			if (notice.getAttFileImg().exists()) {

				String sConvFileName = "/" + notice.getCtgrCd() + "/" + DateUtil.getCurrentYear() + "/" + DateUtil.getCurrentMonth() + "/"
						+ notice.getNoticeId() + "_" + DateUtil.getGeneralTimeStampString() + "."
						+ FileUtility.getFileExt(notice.getAttFileImgFileName());

				String uploadFileFullPathName = sFilePath + sConvFileName;

				if (log.isDebugEnabled()) {
					log.debug("uploadFileFullPath::::::: " + uploadFileFullPathName);
					log.debug("fileName :::::::::::::::: " + notice.getAttFileImgFileName());
					log.debug("convFileName :::::::::::: " + sConvFileName);
					log.debug("fileSize :::::::::::::::: " + notice.getAttFileImg().length() + "bytes");
				}

				boolean bResult = checkFileExt(notice.getAttFileImgFileName(), sFileUploadImgExt);
				if (!bResult) {
					throw new ServiceException("업로드 불가능한 확장자");
				}

				if (notice.getAttFileImg().length() > Long.parseLong(notice.getUploadImgLimit())) {
					if (log.isDebugEnabled())
						log.debug("#Img 업로드 용량 초과:" + notice.getAttFileImg().length() + "###");
					throw new ServiceException("파일의 허용 크기를 넘었습니다.");
				}

				FileUtil.move(notice.getAttFileImg(), new File(uploadFileFullPathName), true);

				tmpFileSupport = new FileSupport();

				this.setStep("CallServiceSelectFileUploadSeq");
				Long tempFid = fileSupportService.selectFileUploadSeq();
				if (log.isDebugEnabled())
					log.debug("NOTICE FID : " + tempFid);

				tmpFileSupport.setGid(notice.getGid());
				tmpFileSupport.setFid("" + tempFid);
				tmpFileSupport.setSeq("1");
				tmpFileSupport.setUseYn("Y");
				tmpFileSupport.setFnm(sConvFileName);
				tmpFileSupport.setOfnm(notice.getAttFileImgFileName());
				tmpFileSupport.setFsz(String.valueOf(notice.getAttFileImg().length()));
				tmpFileSupport.setFurl(sFileUrl);
				tmpFileSupport.setIstemp("");
				tmpFileSupport.setFtype(FileUtility.getFileExt(notice.getAttFileImgFileName()).toUpperCase());

				tmpFileSupportList.add(tmpFileSupport);

			}

		}

		if (notice.getAttFileEtc() != null) {

			if (tmpFileSupportList == null)
				tmpFileSupportList = new ArrayList<FileSupport>();

			if ("".equals(StringUtil.nvlStr(notice.getGid()))) {
				this.setStep("CallServiceSelectFileUploadGrpSeq");
				Long lGid = fileSupportService.selectFileUploadGrpSeq();
				notice.setGid("" + lGid);

				if (log.isDebugEnabled())
					log.debug("New NOTICE GID : " + notice.getGid());
			}

			if (notice.getAttFileEtc().exists()) {

				String sConvFileName = "/" + notice.getCtgrCd() + "/" + DateUtil.getCurrentYear() + "/" + DateUtil.getCurrentMonth() + "/"
						+ notice.getNoticeId() + "_" + DateUtil.getGeneralTimeStampString() + "."
						+ FileUtility.getFileExt(notice.getAttFileEtcFileName());

				String uploadFileFullPathName = sFilePath + sConvFileName;

				if (log.isDebugEnabled()) {
					log.debug("uploadFileFullPath::::::: " + uploadFileFullPathName);
					log.debug("fileName :::::::::::::::: " + notice.getAttFileEtcFileName());
					log.debug("convFileName :::::::::::: " + sConvFileName);
					log.debug("fileSize :::::::::::::::: " + notice.getAttFileEtc().length() + "bytes");
				}

				boolean bResult = checkFileExt(notice.getAttFileEtcFileName(), sFileUploadEtcExt);
				if (!bResult) {
					throw new ServiceException("업로드 불가능한 확장자");
				}

				if (notice.getAttFileEtc().length() > Long.parseLong(notice.getUploadEtcLimit())) {
					if (log.isDebugEnabled())
						log.debug("#Etc 업로드 용량 초과:" + notice.getAttFileEtc().length() + "###");
					throw new ServiceException("파일의 허용 크기를 넘었습니다.");
				}

				FileUtil.move(notice.getAttFileEtc(), new File(uploadFileFullPathName), true);

				tmpFileSupport = new FileSupport();

				this.setStep("CallServiceSelectFileUploadSeq");
				Long tempFid = fileSupportService.selectFileUploadSeq();
				if (log.isDebugEnabled())
					log.debug("NOTICE FID : " + tempFid);

				tmpFileSupport.setGid(notice.getGid());
				tmpFileSupport.setFid("" + tempFid);
				tmpFileSupport.setSeq("2");
				tmpFileSupport.setUseYn("Y");
				tmpFileSupport.setFnm(sConvFileName);
				tmpFileSupport.setOfnm(notice.getAttFileEtcFileName());
				tmpFileSupport.setFsz(String.valueOf(notice.getAttFileEtc().length()));
				tmpFileSupport.setFurl(sFileUrl);
				tmpFileSupport.setIstemp("");
				tmpFileSupport.setFtype(FileUtility.getFileExt(notice.getAttFileEtcFileName()).toUpperCase());

				tmpFileSupportList.add(tmpFileSupport);

			}

		}

		if (log.isDebugEnabled())
			log.debug("#notice.getOpenYn() : " + notice.getOpenYn());
		if ("Y".equals(notice.getOpenYn())) {
			notice.setOpenYn(Constants.YES);
		} else {
			notice.setOpenYn(Constants.NO);
		}

		notice.setMainOpenYn(Constants.NO);

		if (!bIsNew) { // 수정

			notice.setUpdBy(CommonUtil.getLoginId(this.req.getSession()));

			this.setStep("CallServiceUpdateNotice");
			noticeService.updateNotice(notice, tmpFileSupportList, sDelAsisFid);

		} else {

			notice.setDelYn(Constants.NO);
			notice.setInsBy(CommonUtil.getLoginId(this.req.getSession()));

			this.setStep("CallServiceInsertNotice");
			noticeService.insertNotice(notice, tmpFileSupportList);

		}

		notice.setOpenYn(null);

		// IE6
		String sSearchValue = notice.getSearchValue();
		try {
			if (sSearchValue != null) {
				sSearchValue = URLEncoder.encode(notice.getSearchValue(), "UTF-8");
				sSearchValue = sSearchValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}
		notice.setSearchValue(sSearchValue);

		if (!bIsNew) { // 수정
			return "success_update";
		}

		return SUCCESS;
	}

	/**
	 * 첨부파일 업로드가능 확장자 검사 : 첨부파일명, 확장자(gif|zip|jpg)
	 * 
	 * @param fileName
	 * @param fileExt
	 * @return
	 */
	private boolean checkFileExt(String fileName, String fileExt) {

		log.debug("#첨부파일 업로드가능 확장자 검사");

		boolean result = false;

		fileName = StringUtil.nvlStr(fileName).trim().toUpperCase();
		fileExt = StringUtil.nvlStr(fileExt).trim().toUpperCase();
		log.debug("fileName[" + fileName + "]");
		log.debug("fileExt[" + fileExt + "]");

		if (!fileExt.equals("")) {
			StringTokenizer st = new StringTokenizer(fileExt, "|");

			while (st.hasMoreTokens()) {
				if (fileName.endsWith("." + st.nextToken())) {
					result = true;
					break;
				}
			}

		} else {
			result = true;
		}

		log.debug("#첨부파일 업로드가능 확장자 검사:" + result + "###");
		return result;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	protected void prepareRequest() throws Exception {
		notice = new Notice();
	}

	public Notice getModel() {
		return notice;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

	public FileSupport getFileSupport() {
		return fileSupport;
	}

	public void setFileSupport(FileSupport fileSupport) {
		this.fileSupport = fileSupport;
	}

	public List<FileSupport> getFileSupportList() {
		return fileSupportList;
	}

	public void setFileSupportList(List<FileSupport> fileSupportList) {
		this.fileSupportList = fileSupportList;
	}

	public int getCntFileSupport() {
		return cntFileSupport;
	}

	public void setCntFileSupport(int cntFileSupport) {
		this.cntFileSupport = cntFileSupport;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCtgrCd() {
		return ctgrCd;
	}

	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}

	public String getSrchFlag() {
		return srchFlag;
	}

	public void setSrchFlag(String srchFlag) {
		this.srchFlag = srchFlag;
	}

}
