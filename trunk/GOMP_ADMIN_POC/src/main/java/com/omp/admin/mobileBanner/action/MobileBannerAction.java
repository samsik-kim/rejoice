package com.omp.admin.mobileBanner.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileExistsException;

import net.sf.json.JSONObject;
import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.mobileBanner.model.MobileBanner;
import com.omp.admin.mobileBanner.service.MobileBannerService;
import com.omp.admin.mobileBanner.service.MobileBannerServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.ssc.SyncSignal;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.StringUtil;

public class MobileBannerAction extends BaseAction {
	private static final SimpleDateFormat SS = new SimpleDateFormat("yyyyMMddHHmmssss");
	private static final SimpleDateFormat DD = new SimpleDateFormat("yyyyMMdd");
	private MobileBanner mobileBanner = null;
	private MobileBanner mobileBannerSub = null;
	private MobileBannerService mobileBannerService = null;
	private List<MobileBanner> mobileBannerList = null;
	private long mobileBannerCnt = (long) 0;
	private String delIdx = "";
	private String modIdx = "";
	private String modval = "";
	private String modYn = "";
	private String detailNo = "";
	private File bodyUpload = null;
	private String bodyUploadFileName = "";
	private String tempPath = "";
	private String propNm = "";
	private String aid = "";
	private String orgNm = "";
	private String bnrNo = "";
	private String prodId = "";
	private String chk="";
	private int fileSize = 0;
	private String dateChk="";
	private String searcheck="";
	/**
	 * Constructor
	 */
	public MobileBannerAction() {
		//mobileBanner = new MobileBanner();
		mobileBannerSub = new MobileBanner();
		if (this.mobileBannerService == null) {
			this.mobileBannerService = new MobileBannerServiceImpl();
		}
	}

	/**
	 * Mobile Banner LIST.
	 * 
	 * @param
	 * @return
	 */
	public String mobileBannerList() throws Throwable {
		
		if (mobileBanner == null) {
			mobileBanner = new MobileBanner();
			searcheck="請選擇搜尋日期後點選\"搜尋\"鍵。";
		}else{
			
			if(mobileBannerSub!=null&&chk.equals("Y")){
				mobileBanner = mobileBannerSub;
				chk="";
			}
			if (mobileBanner.getStartDate() == null) {
				Date nowDate = new Date();
				String dateStr = DateUtil.getYYYYMMDD(nowDate);

				mobileBanner.setStartDate(DateUtil.getDayAfterWithOutSlash(dateStr, -7));
				mobileBanner.setEndDate(dateStr);
			}
			String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + mobileBanner.getPage().getNo() : this.req
					.getParameter("pageNo");
			sPageNo = StringUtil.nvlStr(sPageNo, "1");
			mobileBanner.getPage().setNo(Integer.parseInt(sPageNo));
			mobileBannerList = mobileBannerService.getMobileBannerList(mobileBanner);
			mobileBannerCnt = ((PagenateList) mobileBannerList).getTotalCount().longValue();
			if(!(mobileBannerCnt>0)){
				searcheck="無資料。";
			}
		}
		return "success";
	}

	/**
	 * Mobile Banner Delete
	 * 
	 * @param
	 */
	public void removeBanner() {
		this.mobileBannerService.removeBanner(delIdx);
	}

	/**
	 * Mobile Banner Modify
	 * 
	 * @param
	 */
	public void modifyBanner() {
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		String[] tempIdx = this.modIdx.split("#");
		String[] tempVal = this.modval.split("#");
		String[] tempYn = this.modYn.split("#");
		for (int i = 1; i < tempIdx.length; i++) {
			this.mobileBannerService.modifyBanner(Integer.parseInt(tempIdx[i]), tempVal[i], tempYn[i], adMgr.getMgrId());
		}
	}

	/**
	 * Mobile Banner Detail.
	 * 
	 * @param
	 * @return
	 */
	public String mobileBannerDetail() {
		if(mobileBanner!=null){
		if(mobileBannerSub==null){
			mobileBannerSub = new MobileBanner();
		}
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + mobileBanner.getPage().getNo() : this.req
				.getParameter("pageNo");
		mobileBanner.getPage().setNo(Integer.parseInt(sPageNo));
		//mobileBannerSub = mobileBanner;
		mobileBannerSub.setStartDate(mobileBanner.getStartDate());
		mobileBannerSub.setEndDate(mobileBanner.getEndDate());
		mobileBannerSub.setKeyWord(mobileBanner.getKeyWord());
		mobileBannerSub.setOpenYn(mobileBanner.getOpenYn());
		mobileBannerSub.setHorImgSize(mobileBanner.getHorImgSize());
		mobileBannerSub.setBannerType(mobileBanner.getBannerType());
		if (!detailNo.equals("")) {
			mobileBanner = this.mobileBannerService.getBannerDetail(Integer.parseInt(detailNo));
			prodId=mobileBanner.getProdId();
			return "success";
		} else {
			Date nowDate = new Date();
			String dateStr = DateUtil.getYYYYMMDD(nowDate);
			mobileBanner.setHorImgSize("DP005201");
			mobileBanner.setStartDate(DateUtil.getDayAfterWithOutSlash(dateStr, -7));
			mobileBanner.setEndDate(dateStr);
			//mobileBanner.setOpenOrder(this.mobileBannerService.getMaxOpenOrder());
			return "success";
		}
		}
		return "success";
	}

	/**
	 * Mobile Banner PreView.
	 * 
	 * @param
	 * @return
	 * @throws IOException 
	 * @throws FileExistsException 
	 * @throws FileNotFoundException 
	 */
	public void ajaxPreViewBanner() throws FileNotFoundException, FileExistsException, IOException {
		File storePath;
		int resultCode = 1;
		int len =(int)bodyUpload.length();
		tempPath = "/" + DD.format(new Date()) + "/" + SS.format(new Date()) + "_banner" + bodyUploadFileName.substring(bodyUploadFileName.lastIndexOf("."), bodyUploadFileName.length());
		if(bodyUploadFileName.substring(bodyUploadFileName.lastIndexOf(".")+1, bodyUploadFileName.length()).toLowerCase().equals("png")){
			storePath = new File(this.conf.getString("omp.common.path.temp.base"), tempPath);	
			if(len<10485760){
				FileUtil.move(bodyUpload, storePath, true);
			}
		}else{
			resultCode = 0;
		}
		JSONObject jsonObject = new JSONObject();
		// Make Json String
		this.res.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		try {
			jsonObject.put("resultCode", resultCode);
			jsonObject.put("tempPath", tempPath);

			jsonObject.put("size", len);
			jsonObject.put("orgNm", bodyUploadFileName);

			writer = this.res.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	/**
	 * Mobile Banner Insert.
	 * 
	 * @param
	 * @return
	 */
	public void insertBanner() {
		HttpServletRequest request = this.req;
		AdSession adSession = (AdSession) request.getSession(true).getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		AdMgr adMgr = adSession.getAdMgr();
		if(!tempPath.equals("")){
			File movePath = new File(this.conf.getString("omp.common.path.temp.base"), tempPath);
			File storePath;
			tempPath = "/" + DD.format(new Date()) + "/" + SS.format(new Date()) + "_banner" + orgNm.substring(orgNm.lastIndexOf("."), orgNm.length());
			storePath = new File(this.conf.getString("omp.common.path.http-share.common.banner"), tempPath);
			try {
				FileUtil.move(movePath, storePath, true);
			} catch (IOException e) {
			}
			mobileBanner.setImgNm(SS.format(new Date()) + "_banner" + orgNm.substring(orgNm.lastIndexOf("."), orgNm.length()));
			mobileBanner.setImgOrgNm(orgNm);
			mobileBanner.setImgPos("/" + DD.format(new Date()) + "/");
			mobileBanner.setImgSize(fileSize+"");
		}
		if(mobileBanner.getOpenOrder()>1000000000){
			mobileBanner.setOpenOrder(999999999);
		}
		mobileBanner.setInsId(adMgr.getMgrId());
		mobileBanner.setProdId(prodId);
		mobileBanner.setTitle(propNm);
		if(dateChk.equals("Y")){
			mobileBanner.setBnrStartDt(mobileBanner.getStartDate()+mobileBanner.getStartH());
			mobileBanner.setBnrEndDt(mobileBanner.getEndDate()+mobileBanner.getEndH());
		}else{
			mobileBanner.setBnrStartDt(DD.format(new Date())+"000000");
			mobileBanner.setBnrEndDt("99991231000000");
		}
		if(!bnrNo.equals("")){
			mobileBanner.setBnrNo(bnrNo);
		}
		this.mobileBannerService.insertBanner(mobileBanner);
	}

	public void wasCashClear() throws IOException {
		this.ssc.doCast(new SyncSignal(null, "webcache", "clear"));
		this.logger.info("webcache clear signal casted.");
	}
	
	public MobileBanner getMobileBanner() {
		return mobileBanner;
	}

	public void setMobileBanner(MobileBanner mobileBanner) {
		this.mobileBanner = mobileBanner;
	}

	public MobileBannerService getMobileBannerService() {
		return mobileBannerService;
	}

	public void setMobileBannerService(MobileBannerService mobileBannerService) {
		this.mobileBannerService = mobileBannerService;
	}

	public List<MobileBanner> getMobileBannerList() {
		return mobileBannerList;
	}

	public void setMobileBannerList(List<MobileBanner> mobileBannerList) {
		this.mobileBannerList = mobileBannerList;
	}

	public long getMobileBannerCnt() {
		return mobileBannerCnt;
	}

	public void setMobileBannerCnt(long mobileBannerCnt) {
		this.mobileBannerCnt = mobileBannerCnt;
	}

	public String getDelIdx() {
		return delIdx;
	}

	public void setDelIdx(String delIdx) {
		this.delIdx = delIdx;
	}

	public String getModIdx() {
		return modIdx;
	}

	public void setModIdx(String modIdx) {
		this.modIdx = modIdx;
	}

	public String getModval() {
		return modval;
	}

	public void setModval(String modval) {
		this.modval = modval;
	}

	public String getModYn() {
		return modYn;
	}

	public void setModYn(String modYn) {
		this.modYn = modYn;
	}

	public String getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(String detailNo) {
		this.detailNo = detailNo;
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

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getPropNm() {
		return propNm;
	}

	public void setPropNm(String propNm) {
		this.propNm = propNm;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getBnrNo() {
		return bnrNo;
	}

	public void setBnrNo(String bnrNo) {
		this.bnrNo = bnrNo;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public MobileBanner getMobileBannerSub() {
		return mobileBannerSub;
	}

	public void setMobileBannerSub(MobileBanner mobileBannerSub) {
		this.mobileBannerSub = mobileBannerSub;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getDateChk() {
		return dateChk;
	}

	public void setDateChk(String dateChk) {
		this.dateChk = dateChk;
	}

	public String getSearcheck() {
		return searcheck;
	}

	public void setSearcheck(String searcheck) {
		this.searcheck = searcheck;
	}
	
}
