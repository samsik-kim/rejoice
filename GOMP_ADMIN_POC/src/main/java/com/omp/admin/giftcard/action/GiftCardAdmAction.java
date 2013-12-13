package com.omp.admin.giftcard.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoException;
import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.service.AdminMainService;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.giftcard.model.GiftCard;
import com.omp.admin.giftcard.model.GiftCardSub;
import com.omp.admin.giftcard.service.GiftCardAdmService;
import com.omp.admin.giftcard.service.GiftCardAdmServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.config.ConfigProperties;

public class GiftCardAdmAction extends BaseAction {

	private GiftCardAdmService service;

	AdminMainService adminMainService = null;

	private AdMgr adMgr = null;

	// param 
	private GiftCardSub sub = null;

	// GiftCard domain
	private GiftCard giftCard = null;

	private GiftCard reqIssueGiftCard= null;

	// GiftCard list
	private List<GiftCard> giftCardlist = null;

	// giftMdnlist list
	private List<GiftCard> giftMdnlist = null;

	// contents list totalCount
	private long totalCount;

	private File comp_file;

	private String contentDisposition;

	public GiftCard getReqIssueGiftCard() {
		return reqIssueGiftCard;
	}

	public void setReqIssueGiftCard(GiftCard reqIssueGiftCard) {
		this.reqIssueGiftCard = reqIssueGiftCard;
	}

	public List<GiftCard> getGiftMdnlist() {
		return giftMdnlist;
	}

	public void setGiftMdnlist(List<GiftCard> giftMdnlist) {
		this.giftMdnlist = giftMdnlist;
	}

	public List<GiftCard> getGiftCardlist() {
		return giftCardlist;
	}

	public void setGiftCardlist(List<GiftCard> giftCardlist) {
		this.giftCardlist = giftCardlist;
	}

	/**
	 * Constructor
	 */
	public GiftCardAdmAction() {
		this.service = new GiftCardAdmServiceImpl();
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public GiftCardSub getSub() {
		return sub;
	}

	public void setSub(GiftCardSub sub) {
		this.sub = sub;
	}

	public GiftCard getGiftCard() {
		return giftCard;
	}

	public void setGiftCard(GiftCard giftCard) {
		this.giftCard = giftCard;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	private InputStream inputStream;	// InputStream(Excel용)

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getComp_file() {
		return comp_file;
	}

	public void setComp_file(File comp_file) {
		this.comp_file = comp_file;
	}

	public AdMgr getAdMgr() {
		return adMgr;
	}

	public void setAdMgr(AdMgr adMgr) {
		this.adMgr = adMgr;
	}

	/**
	 * 선물 내역 정보 리스트
	 * 
	 * @param hm
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public String getPresentCashList() {
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new GiftCardSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			sub.setIssueType("all");
		}

		//		Calendar calendar = Calendar.getInstance();
		//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//		Date nowDate = calendar.getTime();
		//		String date = dateFormat.format(nowDate);
		//		logger.debug("date=======================>"+date);
		//		String dd="0000012";
		//		int ddin = Integer.parseInt(dd);
		//		logger.debug("ddin=======================>"+ddin);

		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		this.setStep("CallServiceGetPresentCashList");
		giftCardlist = service.getPresentCashList(sub);

		totalCount = ((PagenateList) giftCardlist).getTotalCount();

		return SUCCESS;

	}

	public String getPresentCashListExcel() {
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new GiftCardSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			sub.setIssueType("all");
		}

		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String yyyymmdd = sdf.format(new Date());
		
		this.setStep("CallServiceGetPresentCashListExcel");
		this.setDownloadFile(this.service.getPresentCashListExcel(this.sub), "application/vnd.ms-excel; charset=UTF-8", "管理者Whoopy幣發行紀錄_" + yyyymmdd + ".xls");

		return SUCCESS;

	}

	/**
	 * 난수 Excel 다운로드
	 * @return
	 * @throws Exception
	 */
	public String randomNumberExcelExport(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String yyyymmdd = sdf.format(new Date());
		
		this.setStep("CallServiceGetRandomNumberList");
		this.setDownloadFile(this.service.getRandomNumberList(this.sub), "application/vnd.ms-excel; charset=UTF-8", "下載隨機Excel檔-" + yyyymmdd + ".xls");

		return SUCCESS;
	}


	/**
	 * 선물 내역 발행 요청 등록 초기화면
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String viewRequestIssueCash() {
		this.setStep("SearchConditionSetting");
		if ("all".equals(sub.getIssueType())){
			sub.setIssueType("specific");
		}	
		sub.setResultType(StringUtils.defaultString(sub.getResultType(),"all"));
		return SUCCESS;
	}	


	public String getPresentCashTargetUser() {

		HashMap<String, String> hm = new HashMap<String, String>();
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new GiftCardSub();
			sub.setFirstCheck("Y");
		}

		if (sub.getFirstCheck().equals("N")) {
			if(sub.getSearchType() != null && sub.getSearchText() != null){
				hm.put("searchType", sub.getSearchType());
				hm.put("searchText", sub.getSearchText());
			}
			this.setStep("CallServiceGetPresentCashTarget");
			giftCardlist = service.getPresentCashTarget(hm);
			if(giftCardlist.size() == 0) giftCardlist = null;
		}
		return SUCCESS;
	}

	/**
	 * 난수 리스트 발급 권한 확인
	 * @return
	 * @throws Exception
	 */
	public String authDwldConfirm(){
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new GiftCardSub();
		}

		sub.setResAuthConfirm("error");

		if (adMgr == null) {
			adMgr = new AdMgr();
		}
		this.setStep("SetOper");
		String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());

		adMgr.setMgrId(adminId);
		adMgr.setPswdNo(sub.getPassword());
		
		adminMainService = new AdminMainService();
		
		this.setStep("CallServiceAdminLogin");
		adMgr = adminMainService.adminLogin(adMgr);
		boolean rtnboo = false;
		if (adMgr != null) {
			rtnboo = true;
		}

		if(sub.getPassword() == null) {
			sub.setResAuthConfirm("none");
		}else if(rtnboo) {
			if(sub.getIssueType() == null || sub.getIssueType().equals("all"))
				sub.setResAuthConfirm("success");
			else if(sub.getIssueType().equals("specific") || sub.getIssueType().equals("excelRegi"))
				sub.setResAuthConfirm("present");
		}
		return SUCCESS;
	}


	public String requestTargetIssueCash(){
		HashMap hm = new HashMap();

		List<String> list = new java.util.ArrayList<String>();

		List<String> registedMdnList = new java.util.ArrayList<String>();
		
		this.setStep("SearchConditionSetting");
		if(sub.getResultType() == null || sub.getResultType().equals(""))
			sub.setResultType("all");

		if(sub.getResultType().equals("specific")){

			//회원 조회
			hm.put("searchType", "ID");
			String mbrId = giftCard.getMbrId();
			hm.put("searchText", mbrId);
			
			//registedMdnList.add(mbrId);
			//giftCard.setCardCnt("1");

			//TBL_US_MEMBER 조회 
			giftMdnlist = service.getPresentCashTarget(hm);
			
			if(giftMdnlist.size()>0) {
				registedMdnList.add(mbrId);
				giftCard.setCardCnt("1");
			}
			
			hm.put("cardType", "OR002904");  //선물용 대상발급

		}else if((sub.getResultType().equals("excel"))){
			//파일 읽기
			try {
				Workbook workbook = Workbook.getWorkbook(comp_file);
				for(int s = 0; s < workbook.getNumberOfSheets(); s++){
					Sheet sheet = workbook.getSheet(s);

					for(int c = 0; c < sheet.getRows(); c++){
						Cell cell = sheet.getCell(s, c);
						if(cell.getContents() != null && cell.getContents() != "")
							list.add(cell.getContents().trim());
					}
				}

				sub.setCardCnt(String.valueOf(list.size())); //엑셀에 등록된 mbr_id 총 개수

				//회원 조회
				hm.put("searchType", "Excel");
				hm.put("userList", list);

				//TBL_US_MEMBER에 있는 MDN LIST 얻기
				giftMdnlist = service.getPresentCashTarget(hm);
				
				for(int i = 0; i < giftMdnlist.size(); i++){
					giftCard = (GiftCard)giftMdnlist.get(i);
					registedMdnList.add(giftCard.getMbrId());
				}

				hm.put("cardType", "OR002905");  //선물용 엑셀발급

			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		/*#groupSeq#, #userList#, #cardNm#, #cardAmt#, #cardType#, #validity#, #realCardAmt#*/
		if(!sub.getResultType().equals("all")){
			Calendar now = Calendar.getInstance();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String defaultTo = sf.format(now.getTime());

			sub.setEndDate(defaultTo);

			hm.put("userList", registedMdnList);
			hm.put("cardNm", sub.getCardNm());
			hm.put("cardAmt", "OR002899");  //선물 금액 코드
			hm.put("cardCount", registedMdnList.size());
			String adminId = CommonUtil.getLoginId(this.req.getSession());
			hm.put("reqId", adminId);
			hm.put("validity", "6");
			int presentcash = Integer.parseInt(sub.getPresentCashAmt());
			hm.put("realCardAmt", new Integer(presentcash));
			
			logger.debug("registedMdnList.size()==========>" + registedMdnList.size());
			
			giftCard.setGroupSeq(service.issuePresentCash(hm));

			//TBL_US_GIFT_CARD_LIST 테이블에 등록된 수 조회 = SUCCESS 수
			giftCard.setRegCnt(String.valueOf(service.getPresentCardInfoListCnt(giftCard.getGroupSeq())));

			//EXCEL MDN COUNT - SUCCESS MDN COUNT = FAIL COUNT
			giftCard.setRowNum(registedMdnList.size() - Integer.parseInt(giftCard.getRegCnt()));

			List<String> tempFail = new java.util.ArrayList<String>();
			for(int i = 0; i < list.size(); i++){
				String tempMbrId = list.get(i);
				if(!registedMdnList.contains(tempMbrId)){
					tempFail.add(tempMbrId);
				}
			}
			giftCard.setFailedMdnList(tempFail);
		}

		return SUCCESS;
	}

	public String getIssuePresentCashListExcel() {
		int index = 0;					// Excel Sheet Row Index
		int column = 0;					// Excel Sheet Sell Index
		int sheetCnt = 0;				// Excel Sheet Index
		WritableSheet sheet = null;		// Excel Sheet

		try {
			// 파일명을 위한 날짜 
			Calendar now = Calendar.getInstance();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String exprotDate = sf.format(now.getTime());
			exprotDate = exprotDate.replaceAll("-", "");
			logger.info("start1: " + now.getTime().toString());

			List<GiftCard> getSuccessPresentCashTarget = null; 
			
			this.setStep("CallServiceGetSuccessPresentCashTarget");
			getSuccessPresentCashTarget = service.getSuccessPresentCashTarget(giftCard.getGroupSeq());

			//실패한 Mdn 추가
			String failedString = giftCard.getFailedMdnList().get(0);
			failedString = failedString.replaceAll("\\[", "");
			failedString = failedString.replaceAll("\\]", "");
			String[] failedListArray = failedString.split(",");

			GiftCard failedInfo = null;
			for(int i = 0; i < failedListArray.length; i++){
				if(failedListArray[i] != null && !failedListArray[i].equals("")){
					failedInfo = new GiftCard();
					failedInfo.setMbrHpNum("");
					failedInfo.setMbrId(failedListArray[i].trim());
					failedInfo.setMbrCatCd("X");

					getSuccessPresentCashTarget.add(failedInfo);
				}
			}

			logger.info("groupSeq: " + giftCard.getGroupSeq());
			now = Calendar.getInstance();
			logger.info("end1: " + now.getTime().toString());
			// 임시 저장할 경로
			String fullPath = makeFileDirectory();

			String strFileName = /*fullPath +*/ fullPath + File.separator + "issuePresentCashResult_" + exprotDate + ".xls";
			logger.debug("====================================================" + strFileName);

			String strClient = req.getHeader("User-Agent");

			if (strClient.indexOf("MSIE 5.5") != -1 && strClient.indexOf("MSIE 6.0") != -1) {
				res.setHeader("Content-Type", "doesn/matter; charset=euc-kr");
				res.setHeader("Content-Disposition", "filename=" + "issuePresentCashResult_"  +exprotDate + ".xls" + ";");
			} else {
				res.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
				res.setHeader("Content-Disposition", "attachment;filename=" + "issuePresentCashResult_" +exprotDate +  ".xls" + ";");
			}
			res.setHeader("Pragma", "no-cache;");
			res.setHeader("Expires", "-1;");

			WritableWorkbook workbook = Workbook.createWorkbook(new File( strFileName));

			WritableCellFormat border = new WritableCellFormat();
			border.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			border.setWrap(true);
			border.setLocked(true);
			border.setAlignment(jxl.format.Alignment.CENTRE);
			border.setBackground(jxl.format.Colour.WHITE);

			WritableCellFormat borderHeader = new WritableCellFormat();
			borderHeader.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			borderHeader.setWrap(true);
			borderHeader.setLocked(true);
			borderHeader.setAlignment(jxl.format.Alignment.CENTRE);
			borderHeader.setBackground(jxl.format.Colour.GRAY_25);

			for (int i =0; i< getSuccessPresentCashTarget.size(); i++ ){
				// 5만개의 난수 마다 시트 추가
				if((i%50000) == 0) {
					sheet = workbook.createSheet("sheet_" + (sheetCnt + 1), sheetCnt);
					sheet.setColumnView(column++, 30);		// 셀 width

					column = 0;
					sheet.addCell(new Label(column++, 0, "ID", borderHeader));		
					sheet.addCell(new Label(column++, 0, "Result", borderHeader));		
					sheetCnt++;
					index = 0;
				}
				GiftCard presentCashInfo = getSuccessPresentCashTarget.get(i);
				column = 0;
				sheet.addCell(new Label(column++, index + 1, presentCashInfo.getMbrId(), 	border));
				sheet.addCell(new Label(column++, index + 1, presentCashInfo.getMbrCatCd(), 	border));
				index ++;				// Excel Sheet Row Index Next
			}

			workbook.write();
			workbook.close();

			File file = new File(strFileName);
			setContentDisposition("attachment;filename=" + URLEncoder.encode("issuePresentCashResult_" + exprotDate + ".xls", "UTF-8"));
			setInputStream(new FileInputStream(file));

			now = Calendar.getInstance();
			logger.info("end2: " + now.getTime().toString());
			file.delete();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}

		return SUCCESS;
	}

	/**
	 * File Directory Create
	 */
	private String makeFileDirectory() {

		String fullPath = "";

		try{
			ConfigProperties prop	= new ConfigProperties();

			String filePath = prop.getString("omp.admin.path.share.testcase");		// 일시저장 경로.

			File dir = new File(filePath+File.separator);
			if(!dir.exists()){
				dir.mkdirs();
			}

			fullPath = dir.getPath();
		}catch(Exception e){
			logger.debug("ERROR make File Directory : " + e);
		}

		return fullPath;
	}


	/**
	 * 기프트 카드 정보 리스트
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String giftCardInfoList() {
		
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new GiftCardSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			sub.setIssueType("all");
		}

		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		this.setStep("CallServiceGetGiftCardInfoList");
		giftCardlist = service.getGiftCardInfoList(sub);

		totalCount = ((PagenateList) giftCardlist).getTotalCount();

		return SUCCESS;

	}

	/**
	 * 기프트 카드 상세 정보
	 * @return
	 * @throws Exception
	 */
	public String giftCardDetailInfo() {
		this.setStep("CallServiceGetGiftCardDetailInfo");
		giftCard = service.getGiftCardDetailInfo(sub);
		return SUCCESS;
	}

	/**
	 * 등록된 기프트 카드 현황 조회
	 * @return
	 * @throws Exception
	 */
	public String registedGiftCardList(){
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new GiftCardSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}

		sub.setSearchWeek("Y");
		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));
		
		this.setStep("CallServiceGetRegistedGiftCardList");
		giftCardlist = service.getRegistedGiftCardList(sub);

		totalCount = ((PagenateList) giftCardlist).getTotalCount();

		return SUCCESS;
	}

	/**
	 * 기프트 카드 발행 요청 등록 초기화면
	 * @return
	 * @throws Exception
	 */
	public String viewRequestIssueGiftCard() {
		this.setStep("SearchConditionSetting");
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new GiftCardSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}

		if(reqIssueGiftCard == null) {
			reqIssueGiftCard =  new GiftCard();
		}


		Calendar now = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String defaultDate = sf.format(now.getTime());
		
		reqIssueGiftCard.setIssueReqDt(defaultDate);	// 오늘 일자
		reqIssueGiftCard.setCardType("OR002901");		// 증정용
		reqIssueGiftCard.setCardAmt("OR002801");		// 3000P

		return SUCCESS;
	}

	/**
	 * 기프트 카드 발행 요청 등록
	 * @return
	 * @throws Exception
	 */
	public String requestIssueGiftCard(){
		HashMap<String, String> hm = new HashMap<String, String>();
		String validity = null;

		if(reqIssueGiftCard.getCardType().equals("OR002901")) {
			validity = "6";		// 증정용: 유효기간 6개월
		}
		else if(reqIssueGiftCard.getCardType().equals("OR002902")) {
			validity = "9999";		// 판매용: (무제한)
		}

		hm.put("cardNm", reqIssueGiftCard.getCardNm());
		hm.put("cardAmt", reqIssueGiftCard.getCardAmt());
		hm.put("cardType", reqIssueGiftCard.getCardType());
		hm.put("cardCnt", reqIssueGiftCard.getCardCnt());
		hm.put("issueReqDt", reqIssueGiftCard.getIssueReqDt().replaceAll("-", ""));
		String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
		hm.put("issueReqId", adminId);
		hm.put("validity", validity);
		
		this.setStep("CallServiceRequestIssueGiftCard");
		service.requestIssueGiftCard(hm);

		return SUCCESS;
	}

	/**
	 * 기프트카드 등록현황 다운로드
	 * @return
	 * @throws Exception
	 */
	public String registStatExcelExport(){
		try {
			this.setStep("SearchConditionSetting");	
			if (sub == null) {
				if (logger.isInfoEnabled())
					logger.info("init serarch");
				sub = new GiftCardSub();

				// before 7 days default search
				sub.setSearchWeek("Y");
				sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
				sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = sdf.format(new Date());
			 
			this.setStep("CallServiceGetregistStatListExport");
			this.setDownloadFile(this.service.getregistStatListExport(this.sub), "application/vnd.ms-excel; charset=UTF-8", "禮券註冊現狀-" + yyyymmdd + ".xls");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
