package com.omp.admin.verify.action;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.verify.model.VerifyLicense;
import com.omp.admin.verify.service.VerifyLicenseService;
import com.omp.admin.verify.service.VerifyLicenseServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.product.service.ARMManagerService;
import com.omp.commons.product.service.ARMManagerServiceImpl;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.StringUtil;

public class VerifyLicenseAction extends BaseAction {
	private VerifyLicense verifyLicense = null;
	private VerifyLicense verifyLicenseSub = null;
	private VerifyLicenseService verifyLicenseService = null;
	private List<VerifyLicense> verifyLicenseList = null;
	private int licenseCnt = 0;
	private String verificationLicense = "";
	private String seqOta = "";
	private String validCd = "";
	private String cdNm = "";
	private InputStream inputStream;
	private String chk="";
	private String searcheck="";
	/**
	 * Constructor
	 */
	public VerifyLicenseAction() {
		verifyLicenseSub = new VerifyLicense();
		if (this.verifyLicenseService == null) {
			this.verifyLicenseService = new VerifyLicenseServiceImpl();
		}
	}

	/**
	 * License List.
	 * 
	 * @param qna
	 * @return
	 */
	public String licenseList() throws Throwable {
		if (verifyLicense == null&&chk.equals("")) {
			verifyLicense = new VerifyLicense();
			searcheck="請選擇搜尋日期後點選\"搜尋\"鍵。";
		}else{
			if(chk.equals("Y")){
				verifyLicense = verifyLicenseSub;
				chk="";
			}
			if (verifyLicense.getStartDate() == null) {
				Date nowDate = new Date();
				String dateStr = DateUtil.getYYYYMMDD(nowDate);

				verifyLicense.setStartDate(DateUtil.getDayAfterWithOutSlash(
						dateStr, -7));
				verifyLicense.setEndDate(dateStr);
			}
			String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + verifyLicense.getPage().getNo() : this.req
					.getParameter("pageNo");
			sPageNo = StringUtil.nvlStr(sPageNo, "1");
			verifyLicense.getPage().setNo(Integer.parseInt(sPageNo));
			this.verifyLicenseList = this.verifyLicenseService
					.licenseList(verifyLicense);
			this.licenseCnt = ((PagenateList) verifyLicenseList).getTotalCount()
					.intValue();
			if(this.licenseCnt>0){
				for(int i=0;i<verifyLicenseList.size();i++){
					String tempInt = verifyLicenseList.get(i).getValidFrdt();
					String tempIssue = verifyLicenseList.get(i).getIssueDttm().substring(0, 4)+verifyLicenseList.get(i).getIssueDttm().substring(5, 7)+verifyLicenseList.get(i).getIssueDttm().substring(8, 10);
					String tempAfer = DateUtil.getDayAfterWithOutSlash(tempIssue,Integer.parseInt((String) tempInt.subSequence(0, 2)));
					verifyLicenseList.get(i).setValidFrdt(tempIssue);
					verifyLicenseList.get(i).setValidTodt(tempAfer);
				}
			}
			if(!(licenseCnt>0)){
				searcheck="無資料。";
			}
		}
		return "success";
	}

	public String registerLicense() throws Exception {
		if(verifyLicenseSub==null){
			verifyLicenseSub = new VerifyLicense();
		}
		verifyLicenseSub = verifyLicense;
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true)
				.getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		String dateStr = DateUtil.getCurrentYear().substring(0, 4) + "-"
				+ DateUtil.getCurrentMonth() + "-"
				+ DateUtil.getCurrentDate().substring(6, 8) + " "
				+ DateUtil.getCurrentTime().substring(0, 2) + ":"
				+ DateUtil.getCurrentTime().substring(2, 4) + ":"
				+ DateUtil.getCurrentTime().substring(4, 6);
		verifyLicense.setRegDttm(dateStr);
		verifyLicense.setValidFrdt("AD000201");
		verifyLicense.setInsId(adMgr.getMgrId());
		return "success";
	}

	public void insertLicense() throws Exception {
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		verifyLicense.setInsId(adMgr.getMgrId());
		String temp = verifyLicense.getMacAddr().toUpperCase();
		verifyLicense.setMacAddr(temp);
		cdNm =this.verifyLicenseService.getCodeName(verifyLicense.getValidFrdt());
		ARMManagerService service = new ARMManagerServiceImpl();
		this.verifyLicenseService.insertLicense(verifyLicense);
		verificationLicense = service.connectARMReqManagerVerificationLicense(verifyLicense.getMacAddr(), cdNm);
		HttpServletResponse res = this.res;
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		try {
		jsonObject.put("result", verificationLicense);
		jsonObject.put("status", "true");
		out = res.getWriter();
		out.write(jsonObject.toString());
		} catch(Exception ex) {
			jsonObject.put("status", "false");
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}

	public String downLicense() throws Exception {
		
		StringBuffer filePath = new StringBuffer();
		StringBuffer fileName = new StringBuffer();

		fileName.append("licenseForAdminTW.gomp");
		//fileName.append(DateUtil.getGeneralTimeStampString());
		//fileName.append("_");
		//fileName.append(this.conf.getString("omp.common.arm.licenseFileName","verificationlicense.omp"));

		filePath.append(this.conf.getString("omp.common.path.temp.base"));

		filePath.append("/" + fileName.toString());

		File file = new File(filePath.toString());

		//wt = new BufferedWriter(new FileWriter(file));
		//wt.write(verificationLicense);
		if (file.isDirectory()) {
			FileUtil.write( file, verificationLicense );
		} else {
			file.getParentFile().mkdirs();
			FileUtil.write( file, verificationLicense );
		}
		this.setDownloadFile(file, "binary/octet-stream",fileName.toString());

		return SUCCESS;
	}
	
	public String detailLicense() throws Exception {
		if(verifyLicenseSub==null){
			verifyLicenseSub = new VerifyLicense();
		}
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + verifyLicense.getPage().getNo() : this.req
				.getParameter("pageNo");
		verifyLicense.getPage().setNo(Integer.parseInt(sPageNo));
		verifyLicenseSub = verifyLicense;
		verifyLicense = this.verifyLicenseService.detailLicense(seqOta);
		String tempInt = verifyLicense.getValidFrdt();
		String tempIssue = verifyLicense.getIssueDttm().substring(0, 4)+verifyLicense.getIssueDttm().substring(5, 7)+verifyLicense.getIssueDttm().substring(8, 10);
		String tempAfer = DateUtil.getDayAfterWithOutSlash(tempIssue,Integer.parseInt((String) tempInt.subSequence(0, 2)));
		verifyLicense.setValidFrdt(tempIssue);
		verifyLicense.setValidTodt(tempAfer);
		return "success";
	}

	public VerifyLicense getVerifyLicense() {
		return verifyLicense;
	}

	public void setVerifyLicense(VerifyLicense verifyLicense) {
		this.verifyLicense = verifyLicense;
	}

	public VerifyLicenseService getVerifyLicenseService() {
		return verifyLicenseService;
	}

	public void setVerifyLicenseService(
			VerifyLicenseService verifyLicenseService) {
		this.verifyLicenseService = verifyLicenseService;
	}

	public List<VerifyLicense> getVerifyLicenseList() {
		return verifyLicenseList;
	}

	public void setVerifyLicenseList(List<VerifyLicense> verifyLicenseList) {
		this.verifyLicenseList = verifyLicenseList;
	}

	public int getLicenseCnt() {
		return licenseCnt;
	}

	public void setLicenseCnt(int licenseCnt) {
		this.licenseCnt = licenseCnt;
	}

	public String getVerificationLicense() {
		return verificationLicense;
	}

	public void setVerificationLicense(String verificationLicense) {
		this.verificationLicense = verificationLicense;
	}

	public String getSeqOta() {
		return seqOta;
	}

	public void setSeqOta(String seqOta) {
		this.seqOta = seqOta;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getValidCd() {
		return validCd;
	}

	public void setValidCd(String validCd) {
		this.validCd = validCd;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public VerifyLicense getVerifyLicenseSub() {
		return verifyLicenseSub;
	}

	public void setVerifyLicenseSub(VerifyLicense verifyLicenseSub) {
		this.verifyLicenseSub = verifyLicenseSub;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getSearcheck() {
		return searcheck;
	}

	public void setSearcheck(String searcheck) {
		this.searcheck = searcheck;
	}

}
