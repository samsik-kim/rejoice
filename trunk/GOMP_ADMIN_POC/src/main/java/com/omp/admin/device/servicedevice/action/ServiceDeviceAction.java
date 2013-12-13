package com.omp.admin.device.servicedevice.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pat.neocore.io.FileUtility;

import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.device.servicedevice.model.PhoneInfo;
import com.omp.admin.device.servicedevice.service.ServiceDeviceService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * Service Device Action
 */
@SuppressWarnings("serial")
public class ServiceDeviceAction extends BaseAction {

	private GLogger log = new GLogger(ServiceDeviceAction.class);

	private ServiceDeviceService serviceDeviceService;

	private PhoneInfo phoneInfo = null;
	private List<PhoneInfo> phoneInfoList = null;

	private Map<String, Object> result;

	private String selectedPhoneModelCd = null;

	private String srchFlag = "";

	private long srchCnt = 0;

	public ServiceDeviceAction() {
		serviceDeviceService = new ServiceDeviceService();
	}

	@SuppressWarnings("rawtypes")
	public String selectPhoneInfoList() {

		if (phoneInfo == null) {
			phoneInfo = new PhoneInfo();
		}

		result = makePhoneInfoCriteria(result);

		srchFlag = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchFlag")))) ? srchFlag : this.req.getParameter("srchFlag");
		if ("".equals(StringUtil.nvlStr(srchFlag))) {
			return SUCCESS;
		}

		String sSearchLcdSize = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchLcdSize")))) ? phoneInfo.getSearchLcdSize()
				: this.req.getParameter("searchLcdSize");
		String sSearchSvcCd = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchSvcCd")))) ? phoneInfo.getSearchSvcCd() : this.req
				.getParameter("searchSvcCd");
		String sSearchVmVer = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchVmVer")))) ? phoneInfo.getSearchVmVer() : this.req
				.getParameter("searchVmVer");
		String sSearchType = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchType")))) ? phoneInfo.getSearchType() : this.req
				.getParameter("searchType");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? phoneInfo.getSearchValue() : this.req
				.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + phoneInfo.getPage().getNo() : this.req
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

		phoneInfo.setSearchLcdSize(sSearchLcdSize);
		phoneInfo.setSearchSvcCd(sSearchSvcCd);
		phoneInfo.setSearchVmVer(sSearchVmVer);
		phoneInfo.setSearchType(CommonUtil.removeSpecial(sSearchType));
		phoneInfo.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		phoneInfo.getPage().setNo(Integer.parseInt(sPageNo));

		phoneInfoList = serviceDeviceService.selectPhoneInfoPagingList(phoneInfo);
		srchCnt = ((PagenateList) phoneInfoList).getTotalCount();

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String selectPopPhoneInfoList() {

		if (phoneInfo == null) {
			phoneInfo = new PhoneInfo();
		}

		result = makePhoneInfoCriteria(result);

		srchFlag = ("".equals(StringUtil.nvlStr(this.req.getParameter("srchFlag")))) ? srchFlag : this.req.getParameter("srchFlag");
		if ("".equals(StringUtil.nvlStr(srchFlag))) {
			return SUCCESS;
		}

		String sSearchLcdSize = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchLcdSize")))) ? phoneInfo.getSearchLcdSize()
				: this.req.getParameter("searchLcdSize");
		String sSearchSvcCd = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchSvcCd")))) ? phoneInfo.getSearchSvcCd() : this.req
				.getParameter("searchSvcCd");
		String sSearchVmVer = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchVmVer")))) ? phoneInfo.getSearchVmVer() : this.req
				.getParameter("searchVmVer");
		String sSearchType = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchType")))) ? phoneInfo.getSearchType() : this.req
				.getParameter("searchType");
		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? phoneInfo.getSearchValue() : this.req
				.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + phoneInfo.getPage().getNo() : this.req
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

		phoneInfo.setSearchLcdSize(sSearchLcdSize);
		phoneInfo.setSearchSvcCd(sSearchSvcCd);
		phoneInfo.setSearchVmVer(sSearchVmVer);
		phoneInfo.setSearchType(CommonUtil.removeSpecial(sSearchType));
		phoneInfo.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		phoneInfo.getPage().setNo(Integer.parseInt(sPageNo));

		phoneInfoList = serviceDeviceService.selectPopPhoneInfoPagingList(phoneInfo);
		srchCnt = ((PagenateList) phoneInfoList).getTotalCount();

		return SUCCESS;
	}
	
	private Map<String, Object> makePhoneInfoCriteria(Map<String, Object> result) {

		if (result == null) {
			result = new HashMap<String, Object>();
		}

		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		selectMap.put("byMgmtPhoneModelNm", LocalizeMessage.getLocalizedMessage("모델명"));
		selectMap.put("byModelNm", LocalizeMessage.getLocalizedMessage("단말명칭"));
		selectMap.put("byGdcd", "GDCD");
		//selectMap.put("byMgmtPhoneModelNm", "型號");
		//selectMap.put("byModelNm", "手機名稱");
		//selectMap.put("byGdcd", "GDCD");

		result.put("selectMap", selectMap);

		return result;
	}

	public String selectPhoneInfo() {

		if (phoneInfo == null) {
			phoneInfo = new PhoneInfo();
		}

		String sSearchLcdSize = phoneInfo.getSearchLcdSize();
		String sSearchSvcCd = phoneInfo.getSearchSvcCd();
		String sSearchVmVer = phoneInfo.getSearchVmVer();
		String sSearchType = phoneInfo.getSearchType();
		String sSearchValue = phoneInfo.getSearchValue();
		int nPageNo = phoneInfo.getPage().getNo();

		if ("".equals(StringUtil.nvlStr(phoneInfo.getPhoneModelCd()))) {

			phoneInfo.setVmType(Constants.CONTENT_PLATFORM_ANDROID);

		} else {

			if (log.isDebugEnabled())
				log.debug("phoneInfo.getPhoneModelCd() : " + phoneInfo.getPhoneModelCd());

			phoneInfo = serviceDeviceService.selectPhoneInfo(phoneInfo);

			if (!"".equals(phoneInfo.getNetworkCd())) {
				phoneInfo.setArrNetworkCd(phoneInfo.getNetworkCd().split(";"));
			}

		}

		phoneInfo.setSearchLcdSize(sSearchLcdSize);
		phoneInfo.setSearchSvcCd(sSearchSvcCd);
		phoneInfo.setSearchVmVer(sSearchVmVer);
		phoneInfo.setSearchType(sSearchType);
		phoneInfo.setSearchValue(sSearchValue);
		phoneInfo.getPage().setNo(nPageNo);

		return SUCCESS;
	}

	public String processPhoneInfo() {

		String sFilePath = "";
		String sFileUrl = "";

		try {

			if (phoneInfo == null) {
				phoneInfo = new PhoneInfo();
			}

			// Check Duplication
			if ("".equals(phoneInfo.getPhoneModelCd())) {
				int retCnt = serviceDeviceService.selectPhoneInfoCnt(phoneInfo);
				if (retCnt > 0) {
					if (log.isInfoEnabled())
						log.info("Device Duplication - {0}, {1}, {2}", new Object[] { phoneInfo.getVmType(), phoneInfo.getVmVer(),
								phoneInfo.getGdcd() });
					return NONE;
				}
			}

			if ("".equals(phoneInfo.getDelYn())) {
				phoneInfo.setDelYn("N");
			}
			phoneInfo.setNetworkCd(phoneInfo.getSelectedNetworkCd());
			phoneInfo.setRegId(CommonUtil.getLoginId(this.req.getSession()));
			phoneInfo.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

			if (log.isDebugEnabled())
				log.debug("phoneInfo : " + phoneInfo.toString());

			sFilePath = this.conf.getString("omp.common.path.http-share.phone");
			sFileUrl = this.conf.getString("omp.common.url.http-share.phone");
			if (log.isInfoEnabled()) {
				log.info("omp.common.path.http-share.phone : {0}", new Object[] { sFilePath });
			}

			if (phoneInfo.getListImg() != null) {

				String sConvFileName = null;

				if (phoneInfo.getListImg().exists()) {

					sConvFileName = "/" + phoneInfo.getMgmtPhoneModelNm() + "_" + phoneInfo.getVmVer() + "_LIST" + "."
							+ FileUtility.getFileExt(phoneInfo.getListImgFileName());

					String uploadFileFullPathName = sFilePath + sConvFileName;

					if (log.isDebugEnabled()) {
						log.debug("ListImg uploadFileFullPath::::::: " + uploadFileFullPathName);
						log.debug("ListImg fileName :::::::::::::::: " + phoneInfo.getListImgFileName());
						log.debug("ListImg convFileName :::::::::::: " + sConvFileName);
						log.debug("ListImg fileSize :::::::::::::::: " + phoneInfo.getListImg().length() + "bytes");
					}

					FileUtil.move(phoneInfo.getListImg(), new File(uploadFileFullPathName), true);

				}

				phoneInfo.setListImgPos(sConvFileName);

			} else {
				sFileUrl = "";
				if (log.isDebugEnabled()) {
					log.debug("listImg uploadPath : " + StringUtil.nvlStr(sFileUrl));
				}
			}

			if (phoneInfo.getDtlImg() != null) {

				String sConvFileName = null;

				if (phoneInfo.getDtlImg().exists()) {

					sConvFileName = "/" + phoneInfo.getMgmtPhoneModelNm() + "_" + phoneInfo.getVmVer() + "_DTL" + "."
							+ FileUtility.getFileExt(phoneInfo.getDtlImgFileName());

					String uploadFileFullPathName = sFilePath + sConvFileName;

					if (log.isDebugEnabled()) {
						log.debug("DtlImg uploadFileFullPath::::::: " + uploadFileFullPathName);
						log.debug("DtlImg fileName :::::::::::::::: " + phoneInfo.getDtlImgFileName());
						log.debug("DtlImg convFileName :::::::::::: " + sConvFileName);
						log.debug("DtlImg fileSize :::::::::::::::: " + phoneInfo.getDtlImg().length() + "bytes");
					}

					FileUtil.move(phoneInfo.getDtlImg(), new File(uploadFileFullPathName), true);

				}

				phoneInfo.setDtlImgPos(sConvFileName);

			} else {
				sFileUrl = "";
				if (log.isDebugEnabled()) {
					log.debug("dtlImg uploadPath : " + StringUtil.nvlStr(sFileUrl));
				}
			}

			if ("".equals(phoneInfo.getPhoneModelCd())) {
				serviceDeviceService.insertPhoneInfo(phoneInfo);
			} else {
				serviceDeviceService.updatePhoneInfo(phoneInfo);
			}

			// IE6
			String sSearchValue = phoneInfo.getSearchValue();
			try {
				if (sSearchValue != null) {
					sSearchValue = URLEncoder.encode(phoneInfo.getSearchValue(), "UTF-8");
					sSearchValue = sSearchValue.replace("%", "@");
				}
			} catch (UnsupportedEncodingException e) {
				log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
				sSearchValue = "";
			}
			phoneInfo.setSearchValue(sSearchValue);

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("단말기 처리 실패.", e);
		}

		return SUCCESS;
	}

	public String updatePhoneInfoDelYn() {

		if (phoneInfo == null) {
			phoneInfo = new PhoneInfo();
		}

		phoneInfo.setPhoneModelCd(selectedPhoneModelCd);
		phoneInfo.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		serviceDeviceService.updatePhoneInfoDelYn(phoneInfo);

		// IE6
		String sSearchValue = phoneInfo.getSearchValue();
		try {
			if (sSearchValue != null) {
				sSearchValue = URLEncoder.encode(phoneInfo.getSearchValue(), "UTF-8");
				sSearchValue = sSearchValue.replace("%", "@");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("ERROR URLEncoder.encode() - {0}" + new Object[] { sSearchValue });
			sSearchValue = "";
		}
		phoneInfo.setSearchValue(sSearchValue);

		return SUCCESS;
	}

	public PhoneInfo getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(PhoneInfo phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public List<PhoneInfo> getPhoneInfoList() {
		return phoneInfoList;
	}

	public void setPhoneInfoList(List<PhoneInfo> phoneInfoList) {
		this.phoneInfoList = phoneInfoList;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getSelectedPhoneModelCd() {
		return selectedPhoneModelCd;
	}

	public void setSelectedPhoneModelCd(String selectedPhoneModelCd) {
		this.selectedPhoneModelCd = selectedPhoneModelCd;
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
