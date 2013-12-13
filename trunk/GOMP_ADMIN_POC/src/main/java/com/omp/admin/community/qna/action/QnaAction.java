package com.omp.admin.community.qna.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileExistsException;
import org.json.JSONArray;
import org.json.JSONObject;

import pat.neocore.util.MiscEncoder;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.community.qna.model.QnA;
import com.omp.admin.community.qna.service.QnaService;
import com.omp.admin.community.qna.service.QnaServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;
import com.omp.admin.common.Constants;

public class QnaAction extends BaseAction
{
	private final String	dateFormat = "yyyyMMdd";
	private static final SimpleDateFormat SS = new SimpleDateFormat("yyyyMMddHHmmssss");
	private static final SimpleDateFormat DD = new SimpleDateFormat("yyyyMMdd");
	private GLogger			logger	= new GLogger(this.getClass());
	private QnaService 		qnaService = null;
	private List<QnA> 		questionList = null;
	private List 			ansList = null;
	private QnA 			qna = null;
	private QnA 			qnaSub = null;
	private List		categoryNameList = null;
	private List		secondDepthCategoryList = null;		
	private int    questionCnt = 0;
	private int    ansCnt = 0;
	private int    ctgrCnt = 0;
	private String url = "";
	private String prcstcd = "";
	private String sQna ="SQNA";
	private String dQna ="DQNA";
	private String sQnaCd ="CM000601";
	private String dQnaCd ="CM000602";
	private String delQueNo = "";
	private String mbrId = "";
	private String queNo = "0";
	private String queId = "";
	private String emailAdd = "";
	private String hpModel = "";
	private String hpNum = "";
	private String queTitle = "";
	private String queDscr = "";
	private String prcOpId = "";
	private String qnaTpCd = "";
	private String prcDscr = "";
	private String status = "";
	private String selectFaqCategoryType = "";
	private String keyword = "";
	private String regStartDate = "";
	private String regEndDate = "";
	private String delayCount = "0";	              
	private String currentDate = "";  	                           
	private String prcStatCd;
	private String qnaClsCd2 = "";		
	private String highCtgr = "";
	private String qnaClsCd = "";
	private String qnaClsNm = "";
	private String chk = "";
	private String keytype = "title";	
	private String dateType = "";		
	private String inquiryType = "";
	private String insNm = "";
	private String insTitle = "";
	private String insDscr = "";
	private String insDt = "";
	private String insEmail = "";
	private String insHpM = "";
	private String insHpN = "";
	private String insMbr = "";
	private String searcheck="";
	// Excel Exprt
    private InputStream inputStream;
	private String contentDisposition; 
	private String modCtgr=""; 
	private String modCtgrSub=""; 
	private String modCtgrSub2=""; 
	private File bodyUpload = null;
	private String bodyUploadFileName = "";
	private String bodyUploadContentType = "";
	private int fileSize = 0;

    public QnaAction() {
    	qnaSub = new QnA();
    	if(this.qnaService == null) {
    		this.qnaService = new QnaServiceImpl();
    	}
    }
    
    
    /**
     * Q&A DB Search.
     * @return
     * @throws Exception
     */
	public String searchQnA() throws Exception {
		this.setDate();
		HttpServletRequest request = this.req;
		String[] tempUrl = request.getRequestURL().toString().split("/");
		this.url = tempUrl[tempUrl.length-1];
		
		if(qna == null&&chk.equals("")){
			qna = new QnA();
			searcheck="請選擇搜尋日期後點選\"搜尋\"鍵。";
		}else{
			if(qnaSub!=null&&chk.equals("List")){
				qna = qnaSub;
				chk="N";
			}
			if(this.chk.equals("")){
				qna.setStartDate(regEndDate);
				qna.setEndDate(regEndDate);
			}
	        //if(qna.getQueId().equals("請輸入會員ID")){
	        //	qna.setQueId("");
	    	//}
	        //if(qna.getKeyword().equals("검색어를 입력하세요.")){
	        //	qna.setKeyword("");
	        //}
	        if(qna.getStartDate()==null){
	        	defaultDateSetting();
	        }
	        String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + qna.getPage().getNo() : this.req
					.getParameter("pageNo");
			sPageNo = StringUtil.nvlStr(sPageNo, "1");
			qna.getPage().setNo(Integer.parseInt(sPageNo));
	        qna.getPage().setRows(10);	
	        if(this.url.equals("searchDQnA.omp")||this.url.equals("removeDQnA.omp")||this.url.equals("sendDMail.omp")){
	        	this.questionList = this.qnaService.searchQnA(qna,dQnaCd);
				this.questionCnt = ((PagenateList)questionList).getTotalCount().intValue();
	    	}else{
	        	this.questionList = this.qnaService.searchQnA(qna,sQnaCd);
				this.questionCnt = ((PagenateList)questionList).getTotalCount().intValue();
			}
	        if(!(questionCnt>0)){
				searcheck="無資料。";
			}
		}
		secondDepthCategoryList = this.qnaService.searchCategoryName(qna.getCtgrCd());
		if(this.url.equals("searchDQnA.omp")||this.url.equals("removeDQnA.omp")||this.url.equals("sendDMail.omp")){
        	categoryNameList = this.qnaService.selectCategoryNameAll(dQna);
    	}else{
    		categoryNameList = this.qnaService.selectCategoryNameAll(sQna);
		}
		
        return "success";
	}
	
	/**
     * Q&A Detail.
     * @return
     * @throws Exception
     */
	public String displayDetailQnA() throws Exception{
		if(qnaSub==null){
			qnaSub = new QnA();
		}
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + qna.getPage().getNo() : this.req
				.getParameter("pageNo");
		qna.getPage().setNo(Integer.parseInt(sPageNo));
		qnaSub = qna;
			qna.setQueNo(qna.getQueNo());
			HttpServletRequest request = this.req;
			String[] tempUrl = request.getRequestURL().toString().split("/");
			
			this.url = tempUrl[tempUrl.length-1];
			this.questionList = this.qnaService.searchQuestion(qna);
			secondDepthCategoryList = this.qnaService.searchCategoryName(qna.getCtgrCd());
			if(this.url.equals("displayDQnA.omp")||this.url.equals("sendDMail.omp")){
				categoryNameList = this.qnaService.selectCategoryNameAll(dQna);
				qnaClsCd=questionList.get(0).getQnaClsCd2();
				prcstcd=questionList.get(0).getPrcStatCd();
			}else{
				categoryNameList = this.qnaService.selectCategoryNameAll(sQna);
				if(questionList.get(0).getHighCtgr()!=null){
					qnaClsCd=questionList.get(0).getHighCtgr();
					qnaClsCd2=questionList.get(0).getQnaClsCd2();
					prcstcd=questionList.get(0).getPrcStatCd();
				}else{
					qnaClsCd=questionList.get(0).getQnaClsCd2();
					qnaClsCd2=questionList.get(0).getQnaClsCd2();
					prcstcd=questionList.get(0).getPrcStatCd();
				}
			}
			this.ansList = this.qnaService.searchAnswer(qna);
			this.ansCnt = this.ansList.size();

			return "success";
	}
	/**
	 * Qna File Up.
	 * 
	 * @param
	 * @return
	 * @throws IOException 
	 * @throws FileExistsException 
	 * @throws FileNotFoundException 
	 */
	public void ajaxQnaFileUpload() throws FileNotFoundException, FileExistsException, IOException {
		int resultCode = 1;
		int len =(int)bodyUpload.length();
		JSONObject jsonObject = new JSONObject();
		// Make Json String
		this.res.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		try {
			jsonObject.put("resultCode", resultCode);
			jsonObject.put("size", len);
			//jsonObject.put("orgNm", bodyUploadFileName);
			writer = this.res.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	/**
     * Q&A E-Mail Send.
     * @return
	 * @throws Exception 
     */
	public void sendHtmlMail() throws Exception {
		HttpServletRequest request = this.req;
		String[] tempUrl = request.getRequestURL().toString().split("/");
		this.url = tempUrl[tempUrl.length-1];
		Date nowDate = new Date();
		String tempAns = qna.getAnsDscr();
		String dateStr = DateUtil.getYYYY(nowDate)+"."+DateUtil.getCurrentMonth()+"."+DateUtil.getCurrentDate().substring(6,8);
		qna.setRegDt(dateStr);
		qna.setQueId(insNm);
		qna.setQueTitle(insTitle);
		qna.setQueDscr(MiscEncoder.encode4Html(insDscr));
		String tempDscr = MiscEncoder.encode4Html(qna.getAnsDscr());
		qna.setAnsDscr(tempDscr);
		MailSendAgent msa;
		SendMailModel mail;
		mail = new SendMailModel();
		mail.setRefAppId("QnaAction.sendHtmlMail");
		mail.setRefDataId(qna.getQueNo()+"");
		mail.setToAddr(insEmail);
		//mail.setToAddr("admintest01@dtstore.tw");
		if(this.url.equals("sendMail.omp")){
			mail.setSubject("[Whoopy] "+insNm+"會員！以下為您的諮詢之答覆。");
			if(insMbr.equals("")){
				mail.setTemplateId("/SCI/US_010.html");
				qna.setEmailAddr(insEmail);
				qna.setQnaClsNm(modCtgrSub);
				qna.setQnaClsNm2(modCtgrSub2);
				qna.setAnsRegDt(insDt);
			}else{
				mail.setTemplateId("/SCI/US_009.html");
				qna.setHpModel(insHpM);
				qna.setHpNo(insHpN);
				qna.setQnaClsNm(modCtgrSub);
				qna.setQnaClsNm2(modCtgrSub2);
				qna.setAnsRegDt(insDt);
			}
		}else{
			mail.setSubject("[Whoopy 開發商轉區] 此為會員諮詢回復信函。");
			mail.setTemplateId("/DEV/US_020.html");
		}
		mail.setContentData(qna);
		if(bodyUpload != null) {
			File storePath;
			String tempPath = "/" + DD.format(new Date()) + "/" + SS.format(new Date()) + "_qna" + bodyUploadFileName.substring(bodyUploadFileName.lastIndexOf("."), bodyUploadFileName.length());
			storePath = new File(this.conf.getString("omp.dev.path.share.misc.qna-attachement"), tempPath);
			int len =(int)bodyUpload.length();
			this.logger.debug("==============len=============="+len);
			this.logger.debug("==============bodyUpload=============="+bodyUpload.getPath());
			this.logger.debug("==============storePath=============="+storePath);
			this.logger.debug("==============bodyUploadFileName=============="+bodyUploadFileName);
			this.logger.debug("==============FileName=============="+bodyUploadFileName.substring(bodyUploadFileName.lastIndexOf(".")+1, bodyUploadFileName.length()));
			FileUtil.move(bodyUpload, storePath, true);
			
			qna.setFilePath("/" + DD.format(new Date()) + "/");
			qna.setFileName(SS.format(new Date()) + "_qna" + bodyUploadFileName.substring(bodyUploadFileName.lastIndexOf("."), bodyUploadFileName.length()));
			qna.setFileReal(bodyUploadFileName);
			//sendEMail(qna,storePath);
			mail.setAttachement(storePath);
			mail.setAttacheFileName(bodyUploadFileName);
		}
		try {
			if(!insEmail.equals("")){
				mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
				mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
				mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
				
				msa = CommunicatorFactory.getMailSendAgent();
				msa.requestMailSend(mail);
			}
		} catch (Exception e) {
			throw new ServiceException("메일 전송이 정상적으로 발송되지 않았습니다.", e);
		}
		if(!modCtgr.equals("")){
			QnA modQna = new QnA();
			modQna.setCtgrCd(modCtgr);
			modQna.setQueNo(qna.getQueNo());
			this.logger.debug("바꾸는 카테고리,qneNo===========>>"+modCtgr+","+qna.getQueNo());
			this.qnaService.modifyCtgr(modQna);
		}
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		qna.setAnsId(adMgr.getMgrId());
		qna.setAnsDscr(tempAns);
		this.qnaService.insertAnswer(qna);
	}
	
	/**
     * Q&A E-Mail Upload.
     * @return
	 * @throws Exception 
     */
//	public String eMailAttachFileUpload() throws Exception {
//		String file_full_path = null;
//		ConfigProperties prop	= new ConfigProperties();
//			
//		if (bodyUpload != null) {
//			logger.debug("#bodyUpload#"+bodyUpload);
//			if (bodyUpload.exists()) {
//				logger.debug("#bodyUploadFileName#"+bodyUploadFileName);
//				logger.debug("#prop.getString#"+prop.getString("omp.admin.qna.attachfile"));
//				file_full_path = FileUploadUtil.uploadFile(bodyUpload, prop.getString("omp.admin.qna.attachfile"), bodyUploadFileName);
//				
//			}
//		}
//		return file_full_path;
//	}
	
	/**
	 * Email 전송.
	 * @param sndEmail
	 * @param rcvEmail
	 * @param rcvId
	 * @param header
	 * @return
	 * @throws Exception
	 */
	/*public void sendEMail(QnA qna,File storePath) throws Exception {
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		//AdMgr adMgr = adSession
		this.logger.debug("==========sendEMail Start==========");
		// 테스트 메일 발송
		MailSendAgent msa;
		SendMailModel mail;
    
		mail = new SendMailModel();
		mail.setTemplateId("test.mail");
		mail.setRefAppId("QnaAction.sendEMail");
		mail.setRefDataId(qna.getQueNo()+"");
		mail.setSubject(qna.getAnsTitle());
		mail.setToAddr(qna.getEmailAddr());
		mail.setContentData(qna);
		mail.setAttachement(storePath);
    
		msa = CommunicatorFactory.getMailSendAgent();
		msa.requestMailSend(mail);
		this.logger.debug("==========sendEMail End==========");
    }*/
	
//	/**
//     * Q&A DB 조회 (에디터 포함).
//     * @param qna
//     * @return
//     */
//	public String searchEditorQnA(){
//		this.logger.debug("searchEditorQnA()");
//		
//		try {
//			HttpServletRequest request = this.req;
//    		
//			QnA qna = new QnA();
//			qna.setPrcStatCd(status);
//			qna.setQnaClsCd(selectFaqCategoryType);
//			qna.setQueId(mbrId);
//			qna.setRegDt(regStartDate.replaceAll("-", ""));
//			qna.setPrcCompDt(regEndDate.replaceAll("-", ""));
//			qna.setQueTitle(keyword);
//
//			
//			PageResultList pList = this.qnaService.searchQnA(qna, pageNavi);
//			this.questionList = pList;
//			this.questionCnt = this.questionList.size();
//			pageNavi.setTotalSize(pList.getTotalSize());
//			this.setDate();
//			
//			if(questionList.size() > 0 ) {
//				delayCount = String.valueOf(((QnA)questionList.get(0)).getDelayCnt());
//				this.logger.debug("delayCount = " + delayCount);
//			}
//			return "success";
//		}
//		catch(Exception ex) {
//			this.logger.error("",ex);
//			return "fail";
//		}
//	}
	
	/**
     * Category Register.
     * @return
	 * @throws Exception 
     */
	public String categoryQnA() throws Exception{
		HttpServletRequest request = this.req;
		String[] tempUrl = request.getRequestURL().toString().split("/");
		this.url = tempUrl[tempUrl.length-1];
		qna = new QnA();
		if(this.url.equals("registerDCtgr.omp")||this.url.equals("deleteDCtgrQnA.omp")||this.url.equals("saveDCtgrQnA.omp")){
			categoryNameList = this.qnaService.selectCategoryNameAll(dQna);
			this.ctgrCnt=this.categoryNameList.size();
		}else{
			categoryNameList = this.qnaService.selectCategoryNameAll(sQna);
			secondDepthCategoryList = this.qnaService.searchCategoryName(qna.getCtgrCd());
			this.ctgrCnt=this.categoryNameList.size();
		}
		return "success";
	}
	
	/**
     *  Category Save.
     * @return
	 * @throws Exception 
     */
	public String saveCtgrQnA() throws Exception{
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		String[] tempNm =this.qnaClsNm.split("#");
		String[] tempCd =this.qnaClsCd.split("#");
		String[] tempDel =this.delQueNo.split("#");
		if(this.url.equals("registerDCtgr.omp")||this.url.equals("deleteDCtgrQnA.omp")||this.url.equals("saveDCtgrQnA.omp")){
			this.qnaService.saveCtgrQnA(highCtgr,tempNm, tempCd, tempDel, adMgr.getMgrId(),dQna);
		}else{
			this.qnaService.saveCtgrQnA(highCtgr,tempNm, tempCd, tempDel, adMgr.getMgrId(),sQna);
		}
		return "success";
	}
	
	
	/**
	 * Category Delete.
	 * @return
	 * @throws Exception 
	 */
	public String deleteCtgrQnA() throws Exception{
		HttpServletRequest request = this.req;
		String[] tempUrl = request.getRequestURL().toString().split("/");
		this.url = tempUrl[tempUrl.length-1];
		
		if(this.url.equals("deleteCtgrQnA.omp")){
			this.qnaService.deleteCtgrQnA(qnaClsCd);
			this.qnaService.deleteSubCtgrQnA(qnaClsCd);
		}else{
			this.qnaService.deleteCtgrQnA(qnaClsCd);
		}
		return "success";
	}
	
	
	/**
	 * Q&A DB Delete.
	 * @return
	 * @throws Exception 
	 */
	public String removeQnA() throws Exception{
		StringTokenizer tokenizer = new StringTokenizer(this.delQueNo, ",");
		int queNo = 0;
		while (tokenizer.hasMoreTokens()) {
		    queNo = Integer.parseInt(tokenizer.nextToken());
		    if(queNo > 0) {
		    	this.qnaService.removeQnA(queNo);
		    }
		}
		return "success";
	}
	
	/**
	 * Q&A Excel Down.
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String downloadQnaList() throws FileNotFoundException {
		if (qna == null) {
			throw new NoticeException("조회 수행 후 다운로드 할 수 있습니다.");
		}
		this.setDate();
		HttpServletRequest request = this.req;
		String[] tempUrl = request.getRequestURL().toString().split("/");
		this.url = tempUrl[tempUrl.length-1];
		
		//if(qna.getQueId().equals("請輸入會員ID")){
	    //   	qna.setQueId("");
	    //}
//	    if(qna.getKeyword().equals("검색어를 입력하세요.")){
//	       	qna.setKeyword("");
//	    }
	    
	    if(this.url.equals("downloadDevQnaList.omp")){
			this.setDownloadFile(this.qnaService.getExcelSCQnaList(this.qna,dQnaCd), "application/vnd.ms-excel; charset=UTF-8", "DEV_QNAList-"+regEndDate+".xls");
		}else{
			this.setDownloadFile(this.qnaService.getExcelSCQnaList(this.qna,sQnaCd), "application/vnd.ms-excel; charset=UTF-8", "SC_QNAList-"+regEndDate+".xls");
		}
		
		return "success";
		 
	}
	
//	public String eMailAttachFileUpload() throws Exception {
//		logger.debug("#메일 첨부 파일 업로드");
//		
//		String file_full_path = null;
//		
//		try {
//			AdminConfig prop = new AdminConfig();
//			
//			if (bodyUpload != null) {
//				if (bodyUpload.exists()) {
//					file_full_path = FileUploadUtil.uploadFile(bodyUpload, prop.getString("omp.admin.qna.attachfile"), bodyUploadFileName);
//				}
//			}
//		}
//		catch(Exception e) {
//			logger.error(e);
//		}
//		
//		return file_full_path;
//	}
	
	/**
	 * Category Map List Search (ajax)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getAjaxSecondCategoryList() throws Exception{
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		QnaServiceImpl qnaSvc = new QnaServiceImpl();
		List<QnA> ctgrList =  qnaSvc.searchCategoryName(selectFaqCategoryType);
		List secondCtgrList = new ArrayList();
		
		for(int i=0; i<ctgrList.size(); i++) {
			HashMap map = new HashMap();
			map.put("ctgrCd", ctgrList.get(i).getCtgrCd());
			map.put("ctgrNm", ctgrList.get(i).getCtgrNm());
			secondCtgrList.add(map);
		}
			
		
		JSONArray jsonArray = new JSONArray(secondCtgrList);
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("result", jsonArray);
			jsonObject.put("status", "false");
			out = res.getWriter();
			out.write(jsonObject.toString());
		} catch(Exception ex) {
			logger.error("getAjaxSecondCategoryList error.", ex);
			jsonObject.put("status", "true");
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	public int getQuestionCnt() {
		return questionCnt;
	}
	public void setQuestionCnt(int questionCnt) {
		this.questionCnt = questionCnt;
	}

	public String getDelQueNo() {
		return delQueNo;
	}
	public void setDelQueNo(String delQueNo) {
		this.delQueNo = delQueNo;
	}

	public String getQueId() {
		return queId;
	}
	public void setQueId(String queId) {
		this.queId = queId;
	}
	
	public String getPrcOpId() {
		return prcOpId;
	}
	public void setPrcOpId(String prcOpId) {
		this.prcOpId = prcOpId;
	}
	
	public String getPrcDscr() {
		return prcDscr;
	}
	public void setPrcDscr(String prcDscr) {
		this.prcDscr = prcDscr;
	}

	public QnA getQna() {
		return qna;
	}
	public void setQna(QnA qna) {
		this.qna = qna;
	}
	
	public List getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List questionList) {
		this.questionList = questionList;
	}

	public String getQueNo() {
		return queNo;
	}
	public void setQueNo(String queNo) {
		this.queNo = queNo;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getSelectFaqCategoryType() {
		return selectFaqCategoryType;
	}
	public void setSelectFaqCategoryType(String selectFaqCategoryType) {
		this.selectFaqCategoryType = selectFaqCategoryType;
	}

	public String getRegStartDate() {
		return regStartDate;
	}
	public void setRegStartDate(String regStartDate) {
		this.regStartDate = regStartDate;
	}

	public String getRegEndDate() {
		return regEndDate;
	}
	public void setRegEndDate(String regEndDate) {
		this.regEndDate = regEndDate;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDelayCount() {
		return delayCount;
	}
	public void setDelayCount(String delayCount) {
		this.delayCount = delayCount;
	}
	
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public void setDate() {
		Date date = new Date();  
		SimpleDateFormat fmt = new SimpleDateFormat(this.dateFormat);
		
		if(this.regEndDate == "") {
			this.regEndDate = fmt.format(date).toString();
		}
		if(this.regStartDate == "") {
			long time = date.getTime() - (7L*60L*60L*24L*1000L);  
		    date.setTime(time);  
			this.regStartDate = fmt.format(date).toString();  
		}
	}
	
	private void defaultDateSetting(){
		if(qna.getStartDate() == null) {
			Date nowDate = new Date();
			String dateStr = DateUtil.getYYYYMMDD(nowDate);
			
			qna.setDateType("");
			qna.setEndDate(dateStr);
			qna.setStartDate(DateUtil.getDayAfterWithOutSlash(dateStr, -7));
		}
	}
	
	public String getCurrentDate() {
		if(this.currentDate == null || this.currentDate == "") {
			this.currentDate = (new SimpleDateFormat(this.dateFormat)).format(new Date()).toString();
		}
		return this.currentDate;
	}
	public void setcurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getQnaTpCd() {
		return qnaTpCd;
	}
	public void setQnaTpCd(String qnaTpCd) {
		this.qnaTpCd = qnaTpCd;
	}
	
	public List getCategoryNameList() {
		return categoryNameList;
	}
	public void setCategoryNameList(List categoryNameList) {
		this.categoryNameList = categoryNameList;
	}

	public String getHighCtgr() {
		return highCtgr;
	}
	public void setHighCtgr(String highCtgr) {
		this.highCtgr = highCtgr;
	}
	
	public String getQnaClsCd() {
		return qnaClsCd;
	}
	public void setQnaClsCd(String qnaClsCd) {
		this.qnaClsCd = qnaClsCd;
	}

	public String getKeytype() {
		return keytype;
	}
	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}

	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String getContentDisposition() {
		return contentDisposition;
	}


	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}


	public File getBodyUpload() {
		return bodyUpload;
	}


	public void setBodyUpload(File bodyUpload) {
		this.bodyUpload = bodyUpload;
	}


	public String getBodyUploadFileName() {
		return bodyUploadFileName;
	}


	public void setBodyUploadFileName(String bodyUploadFileName) {
		this.bodyUploadFileName = bodyUploadFileName;
	}


	public String getBodyUploadContentType() {
		return bodyUploadContentType;
	}


	public void setBodyUploadContentType(String bodyUploadContentType) {
		this.bodyUploadContentType = bodyUploadContentType;
	}


	public String getPrcStatCd() {
		return prcStatCd;
	}


	public void setPrcStatCd(String prcStatCd) {
		this.prcStatCd = prcStatCd;
	}


	public List getSecondDepthCategoryList() {
		return secondDepthCategoryList;
	}


	public void setSecondDepthCategoryList(List secondDepthCategoryList) {
		this.secondDepthCategoryList = secondDepthCategoryList;
	}


	public String getQnaClsCd2() {
		return qnaClsCd2;
	}


	public void setQnaClsCd2(String qnaClsCd2) {
		this.qnaClsCd2 = qnaClsCd2;
	}


	public String getInquiryType() {
		return inquiryType;
	}


	public void setInquiryType(String inquiryType) {
		this.inquiryType = inquiryType;
	}
	
	public String getEmailAdd() {
		return emailAdd;
	}


	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}


	public String getHpModel() {
		return hpModel;
	}


	public void setHpModel(String hpModel) {
		this.hpModel = hpModel;
	}


	public String getHpNum() {
		return hpNum;
	}


	public void setHpNum(String hpNum) {
		this.hpNum = hpNum;
	}


	public String getQueTitle() {
		return queTitle;
	}


	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}


	public String getQueDscr() {
		return queDscr;
	}


	public void setQueDscr(String queDscr) {
		this.queDscr = queDscr;
	}
	
	public List getAnsList() {
		return ansList;
	}


	public void setAnsList(List ansList) {
		this.ansList = ansList;
	}


	public int getAnsCnt() {
		return ansCnt;
	}


	public void setAnsCnt(int ansCnt) {
		this.ansCnt = ansCnt;
	}


	public int getCtgrCnt() {
		return ctgrCnt;
	}


	public void setCtgrCnt(int ctgrCnt) {
		this.ctgrCnt = ctgrCnt;
	}


	public String getQnaClsNm() {
		return qnaClsNm;
	}


	public void setQnaClsNm(String qnaClsNm) {
		this.qnaClsNm = qnaClsNm;
	}


	public String getChk() {
		return chk;
	}


	public void setChk(String chk) {
		this.chk = chk;
	}
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getsQnaCd() {
		return sQnaCd;
	}


	public void setsQnaCd(String sQnaCd) {
		this.sQnaCd = sQnaCd;
	}


	public String getdQnaCd() {
		return dQnaCd;
	}


	public void setdQnaCd(String dQnaCd) {
		this.dQnaCd = dQnaCd;
	}


	public QnaService getQnaService() {
		return qnaService;
	}


	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}


	public String getsQna() {
		return sQna;
	}


	public void setsQna(String sQna) {
		this.sQna = sQna;
	}


	public String getdQna() {
		return dQna;
	}


	public void setdQna(String dQna) {
		this.dQna = dQna;
	}


	public String getModCtgr() {
		return modCtgr;
	}


	public void setModCtgr(String modCtgr) {
		this.modCtgr = modCtgr;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}


	public int getFileSize() {
		return fileSize;
	}


	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}


	public QnA getQnaSub() {
		return qnaSub;
	}


	public void setQnaSub(QnA qnaSub) {
		this.qnaSub = qnaSub;
	}


	public String getInsNm() {
		return insNm;
	}


	public void setInsNm(String insNm) {
		this.insNm = insNm;
	}


	public String getInsTitle() {
		return insTitle;
	}


	public void setInsTitle(String insTitle) {
		this.insTitle = insTitle;
	}


	public String getInsDscr() {
		return insDscr;
	}


	public void setInsDscr(String insDscr) {
		this.insDscr = insDscr;
	}


	public String getInsDt() {
		return insDt;
	}


	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}


	public String getInsHpM() {
		return insHpM;
	}


	public void setInsHpM(String insHpM) {
		this.insHpM = insHpM;
	}


	public String getInsHpN() {
		return insHpN;
	}


	public void setInsHpN(String insHpN) {
		this.insHpN = insHpN;
	}


	public String getModCtgrSub() {
		return modCtgrSub;
	}


	public void setModCtgrSub(String modCtgrSub) {
		this.modCtgrSub = modCtgrSub;
	}


	public String getInsEmail() {
		return insEmail;
	}


	public void setInsEmail(String insEmail) {
		this.insEmail = insEmail;
	}


	public String getInsMbr() {
		return insMbr;
	}


	public void setInsMbr(String insMbr) {
		this.insMbr = insMbr;
	}


	public String getModCtgrSub2() {
		return modCtgrSub2;
	}


	public void setModCtgrSub2(String modCtgrSub2) {
		this.modCtgrSub2 = modCtgrSub2;
	}


	public String getPrcstcd() {
		return prcstcd;
	}


	public void setPrcstcd(String prcstcd) {
		this.prcstcd = prcstcd;
	}


	public String getSearcheck() {
		return searcheck;
	}


	public void setSearcheck(String searcheck) {
		this.searcheck = searcheck;
	}
	
}
