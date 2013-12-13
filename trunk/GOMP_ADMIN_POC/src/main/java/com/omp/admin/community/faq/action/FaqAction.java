package com.omp.admin.community.faq.action;

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
import com.omp.admin.community.faq.model.Ctgr;
import com.omp.admin.community.faq.model.Faq;
import com.omp.admin.community.faq.service.FaqService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.BaseException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

@SuppressWarnings("serial")
public class FaqAction extends BaseAction {

	private GLogger log = new GLogger(FaqAction.class);

	private String sFileUploadImgLimitSize = "1048576";
	private String sFileUploadEtcLimitSize = "10485760";
	private String sFileUploadImgExt = "gif|jpg|jpeg|png";
	private String sFileUploadEtcExt = "htm|html|zip|rar|alz|doc|ppt|pdf|txt|docx|xls|xlsx|pptx|hwp";

	private FaqService faqService;
	private FileSupportService fileSupportService;

	private Ctgr ctgr = null;
	private List<Ctgr> ctgrList = null;

	private Faq faq = null;
	private List<Faq> faqList = null;

	private FileSupport fileSupport;
	private List<FileSupport> fileSupportList;
	private int cntFileSupport = 0;

	private Map<String, Object> result;

	private String mode = null;
	private String selectedFaqId = null;

	private String ctgrCd = null;
	private String ctgrNmList = null;
	private String ctgrCdList = null;
	private String ctgrIndexList = null;

	private String srchFlag = "";

	private long srchCnt = 0;

	public FaqAction() {
		faqService = new FaqService();
		fileSupportService = new FileSupportService();
	}

	public String selectCtgrList() {

		if (ctgr == null) {
			ctgr = new Ctgr();
		}

		ctgr.setHighCtgr(ctgrCd);

		this.setStep("CallServiceSelectCtgrList");
		ctgrList = faqService.selectCtgrList(ctgr);

		return SUCCESS;
	}

	public String processCtgr() {

		if (ctgr == null) {
			ctgr = new Ctgr();
		}

		ctgr.setHighCtgr(ctgrCd);
		ctgr.setInsBy(CommonUtil.getLoginId(this.req.getSession()));
		ctgr.setUpdBy(CommonUtil.getLoginId(this.req.getSession()));

		if (log.isDebugEnabled()) {
			log.debug("ctgrNmList : " + ctgrNmList);
			log.debug("ctgrCdList : " + ctgrCdList);
			log.debug("ctgrIndexList : " + ctgrIndexList);
		}
		String[] arrCtgrNm = ctgrNmList.split("#");
		String[] arrCtgrCd = ctgrCdList.split("#");
		String[] arrCtgrIndex = ctgrIndexList.split("#");

		List<Ctgr> processCtgrList = new ArrayList<Ctgr>(); // Use Process
		String[] aAsisCtgrCd = this.req.getParameterValues("asisCtgrCd");

		this.setStep("CheckCondition");
		boolean bCheckExist = false;
		Ctgr tmpCtgr = null;
		for (int j = 0; j < arrCtgrCd.length; j++) {

			tmpCtgr = new Ctgr();

			// INSERT
			if (arrCtgrCd[j].indexOf("ctgrDepth") >= 0) {

				if (log.isDebugEnabled())
					log.debug(arrCtgrCd[j]);

				tmpCtgr.setGubun("INS");
				tmpCtgr.setCtgrCd(arrCtgrCd[j]);
				tmpCtgr.setCtgrNm(arrCtgrNm[j]);
				tmpCtgr.setHighCtgr(ctgr.getHighCtgr());
				tmpCtgr.setCtgrLevelCd("3");
				tmpCtgr.setInsBy(ctgr.getInsBy());
				tmpCtgr.setUpdBy(ctgr.getUpdBy());
				tmpCtgr.setOpenYn("Y");
				tmpCtgr.setDelYn("N");
				tmpCtgr.setDisplayOrder("" + (Integer.parseInt(arrCtgrIndex[j]) + 1));
				processCtgrList.add(tmpCtgr);
				continue;
			}

			if (aAsisCtgrCd != null && aAsisCtgrCd.length > 0) {
				// UPDATE
				for (int i = 0; i < aAsisCtgrCd.length; i++) {
					if (aAsisCtgrCd[i].equals(arrCtgrCd[j])) {
						tmpCtgr.setGubun("UPD");
						tmpCtgr.setCtgrCd(arrCtgrCd[j]);
						tmpCtgr.setCtgrNm(arrCtgrNm[j]);
						tmpCtgr.setUpdBy(ctgr.getUpdBy());
						tmpCtgr.setDisplayOrder("" + (Integer.parseInt(arrCtgrIndex[j]) + 1));
						processCtgrList.add(tmpCtgr);
						break;
					}
				}
			}

		}

		if (aAsisCtgrCd != null && aAsisCtgrCd.length > 0) {
			for (int i = 0; i < aAsisCtgrCd.length; i++) {
				for (int j = 0; j < arrCtgrCd.length; j++) {
					if (aAsisCtgrCd[i].equals(arrCtgrCd[j])) {
						bCheckExist = true;
						break;
					}
				}

				// DELETE
				if (!bCheckExist) {
					tmpCtgr = new Ctgr();
					tmpCtgr.setGubun("DEL");
					tmpCtgr.setCtgrCd(aAsisCtgrCd[i]);
					tmpCtgr.setDelYn("Y");
					tmpCtgr.setUpdBy(ctgr.getUpdBy());
					processCtgrList.add(tmpCtgr);
				}
				bCheckExist = false;
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("processCtgrList : " + processCtgrList.size() + ", " + processCtgrList.toString());
		}

		this.setStep("CallServiceProcessCtgr");
		faqService.processCtgr(processCtgrList);

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String selectFaqList() {

		if (faq == null) {
			faq = new Faq();
		}

		this.setStep("makeFaqCriteria");
		result = makeFaqCriteria(result);

		srchFlag = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchFlag")))) ? srchFlag : this.req.getParameter("srchFlag");
		if ("".equals(StringUtil.nvlStr(srchFlag))) {
			return SUCCESS;
		}

		String sSearchOpenYn = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchOpenYn")))) ? faq.getSearchOpenYn() : this.req
				.getParameter("searchOpenYn");
		String sStartDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("startDate")))) ? faq.getStartDate() : this.req
				.getParameter("startDate");
		String sEndDate = ("".equals(StringUtil.nvlStr(this.req.getParameter("endDate")))) ? faq.getEndDate() : this.req
				.getParameter("endDate");
		String sSearchType = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchType")))) ? faq.getSearchType() : this.req
				.getParameter("searchType");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? faq.getSearchValue() : this.req
				.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + faq.getPage().getNo() : this.req
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

		faq.setSearchOpenYn(sSearchOpenYn);
		faq.setStartDate(sStartDate);
		faq.setEndDate(sEndDate);
		faq.setSearchType(CommonUtil.removeSpecial(sSearchType));
		faq.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		faq.getPage().setNo(Integer.parseInt(sPageNo));

		if ("".equals(StringUtil.nvlStr(faq.getStartDate()))) {
			faq.setStartDate(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), -1), "yyyyMMdd"));
			faq.setEndDate(DateUtil.getDateToStr(DateUtil.addMonth(new Date(), 0), "yyyyMMdd"));
		}

		faq.setOpenYn(faq.getSearchOpenYn());
		faq.setDelYn("N");

		if ("".equals(StringUtil.nvlStr(ctgrCd))) {
			faq.setHighCtgr(Constants.CTGR_FAQ_SC);
		} else {
			faq.setHighCtgr(ctgrCd);
		}

		this.setStep("CallServiceSelectFaqList");
		faqList = faqService.selectFaqList(faq);
		srchCnt = ((PagenateList) faqList).getTotalCount();

		return SUCCESS;
	}

	private Map<String, Object> makeFaqCriteria(Map<String, Object> result) {

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
		//selectMap.put("", LocalizeMessage.getLocalizedMessage("전체"));
		selectMap.put("category", LocalizeMessage.getLocalizedMessage("카테고리(FAQ)"));
		selectMap.put("dscr", LocalizeMessage.getLocalizedMessage("내용"));
		//selectMap.put("category", "類別");
		//selectMap.put("dscr", "內容");

		result.put("radioMap", radioMap);
		result.put("selectMap", selectMap);

		return result;
	}

	public String selectFaq() {

		if (faq == null) {
			faq = new Faq();
		}

		if (fileSupport == null) {
			fileSupport = new FileSupport();
		}

		if (result == null) {
			result = new HashMap<String, Object>();
		}

		faq.setHighCtgr(ctgrCd);
		faq.setFaqId(faq.getFaqId());
		faq.setSort("");

		String sSearchOpenYn = faq.getSearchOpenYn();
		String sStartDate = faq.getStartDate();
		String sEndDate = faq.getEndDate();
		String sSearchType = faq.getSearchType();
		String sSearchValue = faq.getSearchValue();
		int nPageNo = faq.getPage().getNo();

		Map<String, String> ctgrMap = new LinkedHashMap<String, String>();

		Ctgr tmpCtgr = new Ctgr();
		tmpCtgr.setHighCtgr(ctgrCd);
		List<Ctgr> retCtgrList = faqService.selectCtgrList(tmpCtgr);
		ctgrMap.put("", "CATEGORY");
		//ctgrMap.put("", "類別");
		if (retCtgrList.size() > 0) {
			for (int i = 0; i < retCtgrList.size(); i++) {
				tmpCtgr = (Ctgr) retCtgrList.get(i);
				//log.debug("##### " + i + " - " + tmpCtgr.getCtgrCd() + " : " + tmpCtgr.getCtgrNm());
				ctgrMap.put(StringUtil.nvlStr(tmpCtgr.getCtgrCd()), StringUtil.nvlStr(tmpCtgr.getCtgrNm()));
			}
		}
		result.put("ctgrMap", ctgrMap);

		if (faq.getFaqId() != 0) {
			this.setStep("CallServiceSelectFaq");
			faq = faqService.selectFaq(faq);
		}

		faq.setUploadImgLimit(sFileUploadImgLimitSize);
		faq.setUploadEtcLimit(sFileUploadEtcLimitSize);
		faq.setUploadEtcExt(sFileUploadEtcExt);
		faq.setUploadImgExt(sFileUploadImgExt);

		if (faq.getFaqId() != 0) { // 수정일 경우

			//첨부파일정보
			try {

				fileSupport.setGid(StringUtil.nvlStr(faq.getGid()));
				if (!"".equals(fileSupport.getGid())) {
					this.setStep("CallServiceSelectFileDownloadList");
					fileSupportList = fileSupportService.selectFileDownloadList(fileSupport);
				}
				if (fileSupportList != null) {
					cntFileSupport = fileSupportList.size();
				}

			} catch (Exception e) {
				throw new BaseException("# Error FAQ faqView()", e);
			}

		}

		faq.setSearchOpenYn(sSearchOpenYn);
		faq.setStartDate(sStartDate);
		faq.setEndDate(sEndDate);
		faq.setSearchType(sSearchType);
		faq.setSearchValue(sSearchValue);
		faq.getPage().setNo(nPageNo);

		return SUCCESS;
	}

	public String processFaq() {

		try {

			if (faq == null) {
				faq = new Faq();
			}

			String sFilePath = this.conf.getString("omp.common.path.http-share.common.faq");
			String sFileUrl = this.conf.getString("omp.common.url.http-share.common.faq");

			faq.setUploadImgLimit(sFileUploadImgLimitSize);
			faq.setUploadEtcLimit(sFileUploadEtcLimitSize);
			faq.setUploadEtcExt(sFileUploadEtcExt);
			faq.setUploadImgExt(sFileUploadImgExt);

			// Check Insert or Update
			boolean bIsNew = (faq.getFaqId() == 0) ? true : false;

			// Update Upload File
			String sDelAsisFid = "";

			faq.setHighCtgr(ctgrCd);
			faq.setInsBy(CommonUtil.getLoginId(this.req.getSession()));
			faq.setUpdBy(CommonUtil.getLoginId(this.req.getSession()));

			if ("".equals(StringUtil.nvlStr(faq.getSort()))) {
				faq.setSort("1");
			}

			if (bIsNew) { // 신규
				// 신규 등록이면 Seq를 미리 발급 받는다.(첨부파일 Link를 위해서 Seq를 먼저 가져 온다.)
				this.setStep("CallServiceGetFaqIdSeq");
				long lFaqIdSeq = faqService.getFaqIdSeq();
				log.debug("lFaqIdSeq : " + lFaqIdSeq);
				faq.setFaqId(lFaqIdSeq);
			}
			if (log.isInfoEnabled())
				log.info("faq.getFaqId() : {0}", new Object[] { faq.getFaqId() });

			if (log.isDebugEnabled())
				log.debug(faq.toString());

			FileSupport tmpFileSupport = null;
			List<FileSupport> tmpFileSupportList = null;

			if (!bIsNew) {
				String sAsisImgFid = StringUtil.nvlStr(faq.getAsisImgFid());
				if ("".equals(StringUtil.nvlStr(faq.getTobeImgFid()))) {
					sDelAsisFid = sAsisImgFid;
				}
				String sAsisEtcFid = StringUtil.nvlStr(faq.getAsisEtcFid());
				if ("".equals(StringUtil.nvlStr(faq.getTobeEtcFid()))) {
					if ("".equals(sDelAsisFid)) {
						sDelAsisFid = sAsisEtcFid;
					} else {
						sDelAsisFid = sDelAsisFid + "," + sAsisEtcFid;
					}
				}
			}

			if (faq.getAttFileImg() != null) {

				tmpFileSupportList = new ArrayList<FileSupport>();

				if ("".equals(StringUtil.nvlStr(faq.getGid()))) {
					this.setStep("CallServiceSelectFileUploadGrpSeq");
					Long lGid = fileSupportService.selectFileUploadGrpSeq();
					faq.setGid("" + lGid);

					if (log.isDebugEnabled())
						log.debug("New FAQ GID : " + faq.getGid());
				}

				String sConvFileName = null;

				if (faq.getAttFileImg().exists()) {

					if (bIsNew) {
						sConvFileName = "/" + faq.getCtgrCd() + "_" + DateUtil.getGeneralTimeStampString() + "."
								+ FileUtility.getFileExt(faq.getAttFileImgFileName());
					} else {
						sConvFileName = "/" + faq.getCtgrCd() + "_" + faq.getFaqId() + "."
								+ FileUtility.getFileExt(faq.getAttFileImgFileName());
					}

					String uploadFileFullPathName = sFilePath + sConvFileName;

					if (log.isDebugEnabled()) {
						log.debug("uploadFileFullPath::::::: " + uploadFileFullPathName);
						log.debug("fileName :::::::::::::::: " + faq.getAttFileImgFileName());
						log.debug("convFileName :::::::::::: " + sConvFileName);
						log.debug("fileSize :::::::::::::::: " + faq.getAttFileImg().length() + "bytes");
					}

					boolean bResult = checkFileExt(faq.getAttFileImgFileName(), sFileUploadImgExt);
					if (!bResult) {
						throw new ServiceException("업로드 불가능한 확장자");
					}

					if (log.isDebugEnabled())
						log.debug(faq.getUploadImgLimit());

					if (faq.getAttFileImg().length() > Long.parseLong(faq.getUploadImgLimit())) {
						if (log.isDebugEnabled())
							log.debug("#Img 업로드 용량 초과:" + faq.getAttFileImg().length() + "###");
						throw new ServiceException("파일의 허용 크기를 넘었습니다.");
					}

					FileUtil.move(faq.getAttFileImg(), new File(uploadFileFullPathName), true);

					tmpFileSupport = new FileSupport();

					this.setStep("CallServiceSelectFileUploadSeq");
					Long tempFid = fileSupportService.selectFileUploadSeq();
					if (log.isDebugEnabled())
						log.debug("FAQ FID : " + tempFid);

					tmpFileSupport.setGid(faq.getGid());
					tmpFileSupport.setFid("" + tempFid);
					tmpFileSupport.setSeq("1");
					tmpFileSupport.setUseYn("Y");
					tmpFileSupport.setFnm(sConvFileName);
					tmpFileSupport.setOfnm(faq.getAttFileImgFileName());
					tmpFileSupport.setFsz(String.valueOf(faq.getAttFileImg().length()));
					tmpFileSupport.setFurl(sFileUrl);
					tmpFileSupport.setIstemp("");
					tmpFileSupport.setFtype(FileUtility.getFileExt(faq.getAttFileImgFileName()).toUpperCase());

					tmpFileSupportList.add(tmpFileSupport);

				}

			}

			if (faq.getAttFileEtc() != null) {

				if (tmpFileSupportList == null)
					tmpFileSupportList = new ArrayList<FileSupport>();

				if ("".equals(StringUtil.nvlStr(faq.getGid()))) {
					this.setStep("CallServiceSelectFileUploadSeq");
					Long lGid = fileSupportService.selectFileUploadGrpSeq();
					faq.setGid("" + lGid);

					if (log.isDebugEnabled())
						log.debug("New FAQ GID : " + faq.getGid());
				}

				String sConvFileName = null;

				if (faq.getAttFileEtc().exists()) {

					if (bIsNew) {
						sConvFileName = "/" + faq.getCtgrCd() + "_" + DateUtil.getGeneralTimeStampString() + "."
								+ FileUtility.getFileExt(faq.getAttFileEtcFileName());
					} else {
						sConvFileName = "/" + faq.getCtgrCd() + "_" + faq.getFaqId() + "."
								+ FileUtility.getFileExt(faq.getAttFileEtcFileName());
					}

					String uploadFileFullPathName = sFilePath + sConvFileName;

					if (log.isDebugEnabled()) {
						log.debug("uploadFileFullPath::::::: " + uploadFileFullPathName);
						log.debug("fileName :::::::::::::::: " + faq.getAttFileEtcFileName());
						log.debug("convFileName :::::::::::: " + sConvFileName);
						log.debug("fileSize :::::::::::::::: " + faq.getAttFileEtc().length() + "bytes");
					}

					boolean bResult = checkFileExt(faq.getAttFileEtcFileName(), sFileUploadEtcExt);
					if (!bResult) {
						throw new ServiceException("업로드 불가능한 확장자");
					}

					if (faq.getAttFileEtc().length() > Long.parseLong(faq.getUploadEtcLimit())) {
						if (log.isDebugEnabled())
							log.debug("#Etc 업로드 용량 초과:" + faq.getAttFileEtc().length() + "###");
						throw new ServiceException("파일의 허용 크기를 넘었습니다.");
					}

					FileUtil.move(faq.getAttFileEtc(), new File(uploadFileFullPathName), true);

					tmpFileSupport = new FileSupport();

					this.setStep("CallServiceSelectFileUploadSeq");
					Long tempFid = fileSupportService.selectFileUploadSeq();
					if (log.isDebugEnabled())
						log.debug("FAQ FID : " + tempFid);

					tmpFileSupport.setGid(faq.getGid());
					tmpFileSupport.setFid("" + tempFid);
					tmpFileSupport.setSeq("2");
					tmpFileSupport.setUseYn("Y");
					tmpFileSupport.setFnm(sConvFileName);
					tmpFileSupport.setOfnm(faq.getAttFileEtcFileName());
					tmpFileSupport.setFsz(String.valueOf(faq.getAttFileEtc().length()));
					tmpFileSupport.setFurl(sFileUrl);
					tmpFileSupport.setIstemp("");
					tmpFileSupport.setFtype(FileUtility.getFileExt(faq.getAttFileEtcFileName()).toUpperCase());

					tmpFileSupportList.add(tmpFileSupport);

				}

			}

			if (bIsNew) {
				faq.setDelYn(Constants.NO);
				this.setStep("CallServiceInsertFaq");
				faqService.insertFaq(faq, tmpFileSupportList);
			} else {
				if (log.isDebugEnabled())
					log.debug("sDelAsisFid : " + sDelAsisFid);
				this.setStep("CallServiceUpdateFaq");
				faqService.updateFaq(faq, tmpFileSupportList, sDelAsisFid);
			}

			// IE6
			String sSearchValue = faq.getSearchValue();
			try {
				if (sSearchValue != null) {
					sSearchValue = URLEncoder.encode(faq.getSearchValue(), "UTF-8");
					sSearchValue = sSearchValue.replace("%", "@");
				}
			} catch (UnsupportedEncodingException e) {
				log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
				sSearchValue = "";
			}
			faq.setSearchValue(sSearchValue);

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("FAQ 처리 실패.", e);
		}

		return SUCCESS;
	}

	public String updateFaqList() {

		if (faq == null) {
			faq = new Faq();
		}

		faq.setHighCtgr(ctgrCd);
		faq.setSelectedFaqId(selectedFaqId);
		faq.setUpdBy(CommonUtil.getLoginId(this.req.getSession()));

		if (!"".equals(StringUtil.nvlStr(faq.getSelectedFaqId()))) {

			if ("D".equalsIgnoreCase(mode)) {
				faq.setDelYn(Constants.YES);
				this.setStep("CallServiceUpdateFaqDelYn");
				faqService.updateFaqDelYn(faq);
			} else if ("Y".equalsIgnoreCase(mode)) {
				faq.setOpenYn(Constants.YES);
				this.setStep("CallServiceUpdateFaqOpenYn");
				faqService.updateFaqOpenYn(faq);
			} else if ("N".equalsIgnoreCase(mode)) {
				faq.setOpenYn(Constants.NO);
				this.setStep("CallServiceUpdateFaqOpenYn");
				faqService.updateFaqOpenYn(faq);
			}

		}

		// IE6
		String sSearchValue = faq.getSearchValue();
		try {
			if (sSearchValue != null) {
				sSearchValue = URLEncoder.encode(faq.getSearchValue(), "UTF-8");
				sSearchValue = sSearchValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}
		faq.setSearchValue(sSearchValue);

		return SUCCESS;
	}

	public String updateFaqSortList() {

		if (faq == null) {
			faq = new Faq();
		}

		String[] aSortFaqId = faq.getSortFaqId().trim().split(",");
		String[] aSort = faq.getSort().trim().split(",");

		log.debug("aSortFaqId.length : " + aSortFaqId.length);
		log.debug("aSort.length : " + aSort.length);

		faq.setHighCtgr(ctgrCd);
		faq.setUpdBy(CommonUtil.getLoginId(this.req.getSession()));

		this.setStep("CallServiceProcessCtgr");
		faqService.updateFaqSortList(faq, aSortFaqId, aSort);

		return SUCCESS;
	}

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

	public Ctgr getCtgr() {
		return ctgr;
	}

	public void setCtgr(Ctgr ctgr) {
		this.ctgr = ctgr;
	}

	public List<Ctgr> getCtgrList() {
		return ctgrList;
	}

	public void setCtgrList(List<Ctgr> ctgrList) {
		this.ctgrList = ctgrList;
	}

	public Faq getFaq() {
		return faq;
	}

	public void setFaq(Faq faq) {
		this.faq = faq;
	}

	public List<Faq> getFaqList() {
		return faqList;
	}

	public void setFaqList(List<Faq> faqList) {
		this.faqList = faqList;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSelectedFaqId() {
		return selectedFaqId;
	}

	public void setSelectedFaqId(String selectedFaqId) {
		this.selectedFaqId = selectedFaqId;
	}

	public String getCtgrCd() {
		return ctgrCd;
	}

	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}

	public String getCtgrNmList() {
		return ctgrNmList;
	}

	public void setCtgrNmList(String ctgrNmList) {
		this.ctgrNmList = ctgrNmList;
	}

	public String getCtgrCdList() {
		return ctgrCdList;
	}

	public void setCtgrCdList(String ctgrCdList) {
		this.ctgrCdList = ctgrCdList;
	}

	public String getCtgrIndexList() {
		return ctgrIndexList;
	}

	public void setCtgrIndexList(String ctgrIndexList) {
		this.ctgrIndexList = ctgrIndexList;
	}

	public String getSrchFlag() {
		return srchFlag;
	}

	public void setSrchFlag(String srchFlag) {
		this.srchFlag = srchFlag;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

}
