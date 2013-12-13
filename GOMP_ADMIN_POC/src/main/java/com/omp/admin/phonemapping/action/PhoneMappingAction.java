/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 30. | Description
 *
 */
package com.omp.admin.phonemapping.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.phonemapping.model.PhoneMappingParam;
import com.omp.admin.phonemapping.model.PhoneRemMgr;
import com.omp.admin.phonemapping.model.PhoneRemScid;
import com.omp.admin.phonemapping.model.SearchParam;
import com.omp.admin.phonemapping.service.PhoneMappingService;
import com.omp.admin.phonemapping.service.PhoneMappingServiceImpl;
import com.omp.admin.product.model.Contents;
import com.omp.admin.product.model.ContentsSub;
import com.omp.admin.product.service.ContentsManagementService;
import com.omp.admin.product.service.ContentsManagementServiceImpl;
import com.omp.commons.Constants;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.product.model.phone.ack2.PhoneSecondAck;
import com.omp.commons.product.service.DistributeException;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;

/**
 * 단말 매핑 관리 Action<br/>
 * <br/>
 * <b>메뉴</b>
 * <ul>
 * <li>단말관리 -> 서비스단말관리 -> 단말Mapping 관리</li>
 * <li>단말관리 -> 서비스단말관리 -> 단말Mapping 처리결과</li>
 * <li>단말처리 2차 ACK 수행(downloadPhoneRemappingAck.omp)</li>
 * </ul>
 * 
 * @author bcpark
 * @version 0.1
 */
@SuppressWarnings("serial")
public class PhoneMappingAction extends BaseAction {

	// 배포 전용 Logger (지역화 메세지를 타지 않음)
	final org.apache.log4j.Logger dLogger = org.apache.log4j.Logger.getLogger(PhoneMappingAction.class);

	// 서비스
	private PhoneMappingService phoneMappingService = null;
	
	// 단말Mapping관리 화면에서 사용할 param Object
	private PhoneMappingParam param = null;
	// 파라미터
	private Contents contents = null;
	private ContentsSub sub = null;
	// 단말Mapping처리결과 화면에서 사용할 param Object
	private SearchParam searchParam = null;
	// contents list totalCount
	private long totalCount;
	// 단말Mapping처리결과 List
	private List<PhoneRemMgr> phoneRemMgrList;
	// 단말Mapping처리결과 상세 상단 정보 
	PhoneRemMgr phoneRemMgr;
	// 단말Mapping처리결과 상세 리스트
	private List<PhoneRemScid> phoneRemScidList;
	// 단말Mapping관리 상품정보 리스트
	private List<Contents> contentsList;
	
	private boolean firstSearch;

	/**
	 * 기본 생성자<br/>
	 * 서비스 초기화 수행
	 */
	public PhoneMappingAction() {
		phoneMappingService = new PhoneMappingServiceImpl();
	}

	/**
	 * 단말매핑 2차 ACK 수행<br/>
	 * <ol>
	 * <li>어드민 단말Mapping 요청 --> DL 요청 성공/실패 리턴</li>
	 * <li>DL에서 단말 요청 매핑 수행 후 2차 ACK를 이 페이지를 호출하여 보냄</li>
	 * <li>DL에서 온 2차 ACK가 성공인 경우 단말 요청한 데이터를 가져와 PD/DP/CT에 반영한다.</li>
	 * </ol>
	 * 이때 CT 쪽 업데이트는 해당 CID의 승인상태가 승인인것중 검증요청 버전이 MAX인 것을 찾아 반영한다.<br/>
	 * <b>2차 ACK에 대한 결과는 DL에서 받아가지 않는다.</b><
	 * 
	 * @return
	 */
	public String downloadPhoneRemappingAck() {
		try {
			// request Body에서 DL에서 보내온 XML을 가져온다.
			this.setStep("GetResponseBodyXml");
			String xmlBody = getXMLContents();
			if (dLogger.isInfoEnabled()) {
				dLogger.info("DL PhoneMapping Second ACK XML");
				dLogger.info(xmlBody);
			}
			// XML TO OBJ
			this.setStep("UnmarshalXmlToObj");
			PhoneSecondAck phoneSecondAck = (PhoneSecondAck) xmlStringToObj(PhoneSecondAck.class, xmlBody);
			this.setPrimaryKey("tx_id", phoneSecondAck.header.result_info.getTx_id());
			// 단말매핑 결과 에 따른 PD / CT / DP 에 반영
			this.setStep("CallServiceExecutePhoneSecondAck");
			phoneMappingService.executePhoneSecondAck(phoneSecondAck);
		} catch (DistributeException e) {
			logger.warn("단말 Remapping 2차 ACK 실패", e);

		}
		return SUCCESS;
	}

	/**
	 * 단말관리 -> 서비스단말관리 -> 단말Mapping 처리 결과<br/>
	 * DL 쪽으로 단말 매핑을 보내고 받은 결과에 대한 리스트를 보여준다.
	 * 
	 * @return
	 */
	public String deviceRemappingList() {
		if (searchParam == null) {
			searchParam = new SearchParam();
			firstSearch = true;
			// before 7 days default search
			searchParam.setSearchWeek("Y");
			searchParam.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			searchParam.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		} else {
			firstSearch = false;
		}

		// detail 쪽에서 master No가 넘어온 경우 
		if (StringUtils.isNotBlank(searchParam.getMasterNo())) {
			try {
				searchParam.getPage().setNo(Integer.parseInt(searchParam.getMasterNo()));
			} catch (NumberFormatException nfe) {}
		}

		// search start / end date change db query format
		searchParam.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(searchParam.getStartDate()).trim()));
		searchParam.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(searchParam.getEndDate()).trim()));
		if (!firstSearch) {
			// 리스트를 가져온다.
			phoneRemMgrList = phoneMappingService.selectPhoneRemMgrList(searchParam);

			totalCount = ((PagenateList) phoneRemMgrList).getTotalCount();
		}
		return SUCCESS;
	}

	public String deviceRemappingDetail() {
		if (searchParam == null || StringUtils.isBlank(searchParam.getTxId())) {
			throw new NoticeException("올바르지 않은 요청입니다.");
		}

		phoneRemMgr = phoneMappingService.getPhoneRemMgr(searchParam.getTxId());
		phoneRemScidList = phoneMappingService.selectPhoneRemScidList(searchParam);

		totalCount = ((PagenateList) phoneRemScidList).getTotalCount();
		return SUCCESS;
	}

	public String excelDetailList() {
		if (searchParam == null || StringUtils.isBlank(searchParam.getTxId())) {
			throw new NoticeException("올바르지 않은 요청입니다.");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String yyyymmdd = sdf.format(new Date());

		this.setDownloadFile(this.phoneMappingService.getDetailExcel(searchParam), "application/vnd.ms-excel; charset=UTF-8", "手機與產品介接處理結果"
				+ yyyymmdd + ".xls");
		return SUCCESS;
	}

	/**
	 * 단말관리 -> 서비스단말관리 -> 단말Mapping관리<br/>
	 * 단말매핑 화면으로 이동한다.<br/>
	 * 단말매핑 화면의 기능은 아래와 같다.<br/>
	 * <ul>
	 * <li>단말등록 : A디바이스에 매핑된 상품 X를 B디바이스에 매핑</li>
	 * <li>단말삭제 : 상품X에 매핑된 A 디바이드스를 삭제</li>
	 * <li>단말조회 : A디바이스에 매핑되어 있는 상품 목록조회(엑셀다운)</li>
	 * </ul>
	 * 
	 * @return
	 */
	public String deviceRemapping() {
		return SUCCESS;
	}

	/**
	 * 단말관리 -> 서비스단말관리 -> 단말Mapping관리<br/>
	 * 단말매핑 화면으로 이동한다.<br/>
	 * 상품검색<br/>
	 * 
	 * @return
	 */
	public String getProductInfo() {
		
		// 검색조건 없을 경우
		if (searchParam == null) {
			searchParam = new SearchParam();
			firstSearch = true;
		} else {
			searchParam.getPage().setRows(10);
			firstSearch = false;
		}
		
		// 초기 페이지에서는 검색을 하지 않는다.
		if (!firstSearch) {
			contentsList = phoneMappingService.getDPContentInfoList(searchParam);
			totalCount = ((PagenateList) contentsList).getTotalCount();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 단말관리 -> 서비스단말관리 -> 단말Mapping관리 -> 단말조회(엑셀다운)<br/>
	 * 단말조회 화면에서 대상단말에 매핑되어 있는 상품 목록을 엑셀 다운로드 한다.<br/>
	 * 대상 상품 목록은 CT쪽에서 승인상태가 승인인것중 검증요청 버전이 MAX인 상품을 찾는다.
	 * 
	 * @return
	 */
	public String excelContentList() {
		// 대상단말을 입력하지 않았는지
		if (param == null || StringUtils.isBlank(param.getSearchDevice())) {
			throw new InvalidInputException("{0}을 입력해 주세요.", new Object[] { LocalizeMessage.getLocalizedMessage("대상단말명") });
		}

		// 대상 단말이 존재하는 단말인지
		Map<String, String> validDeviceMap = phoneMappingService.isValidDevice(param.getSearchDevice());
		if (validDeviceMap == null || StringUtils.isEmpty(validDeviceMap.get("DEL_YN"))) {
			throw new NoticeException("{0}이 존재하지 않습니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[대상단말]") });
		}

		// 대상 단말이 삭제된 단말인지
		if ("Y".equals(validDeviceMap.get("DEL_YN"))) {
			throw new NoticeException("{0}은 더이상 사용하지 않는 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[대상단말]") });
		}

		if (Constants.PHONE_INFO_NOT_REGIST.equals(validDeviceMap.get("SVC_CD"))) {
			throw new NoticeException("{0}은 미등록 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[대상단말]") });
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String yyyymmdd = sdf.format(new Date());

		if (logger.isInfoEnabled()) {
			logger.info("Device Mapping Info Excel : {0}", new Object[] { param.getSearchDevice() });
		}

		this.setDownloadFile(this.phoneMappingService.getContentsExcelByTargetDevice(this.param.getSearchDevice()),
				"application/vnd.ms-excel; charset=UTF-8", "搜尋手機[" + param.getSearchDevice() + "]_" + yyyymmdd + ".xls");
		return SUCCESS;
	}

	/**
	 * 단말관리 -> 서비스단말관리 -> 단말Mapping관리 -> 단말등록<br/>
	 * 단말등록 : A디바이스에 매핑된 상품 X를 B디바이스에 매핑<br/>
	 * <OL>
	 * <LI>단말등록 정보를 TBL_PD_SPRT_PHONE_REM_MGR, TBL_PD_SPRT_PHONE_REM_SCID에 저장</LI>
	 * <LI>D/L 쪽에 단말매핑 요청 1차 ACK 보낸 후 1차ACK 결과를 TBL_PD_SPRT_PHONE_REM_MGR에 저장</LI>
	 * <LI>D/L 쪽에서 2차 ACK가 오면(downloadPhoneRemappingAck) 성공여부에 따라 PD/DP/CT 업데이트</LI>
	 * </OL>
	 */
	public void ajaxDeviceAddMapping() {
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("단말 Remapping 요청이 정상적으로 처리 되었습니다.");
		boolean isfisrtAckSuc = true;

		try {
			// AID 하나 매핑인지 Excel(AID 리스트) 매핑인지
			if (param == null || StringUtils.isBlank(param.getType())) {
				throw new NoticeException("올바르지 않은 요청입니다.");
			}

			if (param.getType().equals("aid")) {
				if (StringUtils.isBlank(param.getAid()))
					throw new InvalidInputException("{0}를 입력해 주세요.", new Object[] { "AID" });
			} else {
				if (StringUtils.isBlank(param.getTmpExcel())) {
					throw new InvalidInputException("{0}을 입력해 주세요.", new Object[] { LocalizeMessage.getLocalizedMessage("Excel파일") });
				}
			}

			// 기준 단말이 입력 되었는지
			if (StringUtils.isBlank(param.getBaseDevice())) {
				throw new InvalidInputException("{0}을 입력해 주세요.", new Object[] { LocalizeMessage.getLocalizedMessage("기준단말") });
			}

			// 추가 단말이 입력외었는지
			if (StringUtils.isBlank(param.getAddDevice())) {
				throw new InvalidInputException("{0}을 입력해 주세요.", new Object[] { LocalizeMessage.getLocalizedMessage("추가단말") });
			}

			// 기준단말과 추가단말이 같은 단말인지 
			if (param.getAddDevice().equals(param.getBaseDevice())) {
				throw new InvalidInputException("추가단말과 기준단말이 같은 단말 입니다.");
			}

			String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			param.setAdminId(adminId);
			param.setInsIp(req.getRemoteAddr());
			Map<String, String> resultMap = null;
			if (param.getType().equals("aid")) {
				// 상품 단일 매핑인 경우 
				isfisrtAckSuc = phoneMappingService.deviceAddMapping(param);
			} else {
				// 엑셀 업로드를 통한 상품 리스트 매핑인 경우
				resultMap = phoneMappingService.deviceAddMappingExcel(param);
				isfisrtAckSuc = "true".equals(resultMap.get("isfisrtAckSuc"));
			}

			if (!isfisrtAckSuc) {
				resultCode = 0;
				msg = LocalizeMessage.getLocalizedMessage("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			} else {
				if (param.getType().equals("aidFile")) {
					msg += "\n"
							+ (LocalizeMessage.getLocalizedMessage("요청({0}), 성공({1}), 실패({2})", new Object[] { resultMap.get("total"),
									resultMap.get("suc"), resultMap.get("fail") }));
				}
			}
		} catch (Exception e) {
			resultCode = 0;
			e.printStackTrace();
			msg = e.getLocalizedMessage();
		}

		// Make Json String
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("resultCode", resultCode);
			jsonObject.put("msg", msg);

			writer = this.res.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * 단말관리 -> 서비스단말관리 -> 단말Mapping관리 -> 단말삭제<br/>
	 * 단말삭제 : 상품X에 매핑된 A 디바이드스를 삭제
	 */
	public void ajaxDeviceDelMapping() {
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("단말 Remapping 요청이 정상적으로 처리 되었습니다.");
		boolean isfisrtAckSuc = true;

		try {
			if (param == null || StringUtils.isBlank(param.getType())) {
				throw new NoticeException("올바르지 않은 요청입니다.");
			}

			if (param.getType().equals("aid")) {
				if (StringUtils.isBlank(param.getAid()))
					throw new InvalidInputException("{0}를 입력해 주세요.", new Object[] { "AID" });
			} else {
				if (StringUtils.isBlank(param.getTmpExcel())) {
					throw new InvalidInputException("{0}을 입력해 주세요.", new Object[] { LocalizeMessage.getLocalizedMessage("Excel파일") });
				}
			}

			if (StringUtils.isBlank(param.getDelDevice())) {
				throw new InvalidInputException("{0}을 입력해 주세요.", new Object[] { LocalizeMessage.getLocalizedMessage("삭제단말") });
			}

			String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			param.setAdminId(adminId);
			param.setInsIp(req.getRemoteAddr());
			Map<String, String> resultMap = null;
			if (param.getType().equals("aid")) {
				isfisrtAckSuc = phoneMappingService.deviceDelMapping(param);
			} else {
				resultMap = phoneMappingService.deviceDelMappingExcel(param);
				isfisrtAckSuc = "true".equals(resultMap.get("isfisrtAckSuc"));
			}

			if (!isfisrtAckSuc) {
				resultCode = 0;
				msg = LocalizeMessage.getLocalizedMessage("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			} else {
				if (param.getType().equals("aidFile")) {
					msg += "\n"
							+ (LocalizeMessage.getLocalizedMessage("요청({0}), 성공({1}), 실패({2})", new Object[] { resultMap.get("total"),
									resultMap.get("suc"), resultMap.get("fail") }));
				}
			}
		} catch (Exception e) {
			resultCode = 0;
			e.printStackTrace();
			msg = e.getLocalizedMessage();
		}

		// Make Json String
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("resultCode", resultCode);
			jsonObject.put("msg", msg);

			writer = this.res.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * request Body의 내용을 읽는다.
	 * 
	 * @return request Body 내용
	 * @throws DistributeException
	 */
	private String getXMLContents() throws DistributeException {
		StringBuffer buffer = new StringBuffer();
		String line = "";
		String strXML = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
			while ((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new DistributeException("단말 Remapping 2차 ACK READ BODY ERROR : {0}", new Object[] { "UnsupportedEncodingException" }, e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DistributeException("단말 Remapping 2차 ACK READ BODY ERROR : {0}", new Object[] { "IOException" }, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}

		strXML = buffer.toString();
		return strXML;
	}

	/**
	 * XML String을 JAXB를 이용하여 특정 Object로 unmarshalling 한다.<br/>
	 * 
	 * @param objClass unmarshall로 만들 대상 Object Class
	 * @param xmlString unmarshall 대상이 되는 XMLString
	 * @return unmarshall된 objClass 객체
	 * @throws DistributeException
	 */
	@SuppressWarnings("rawtypes")
	private Object xmlStringToObj(final Class objClass, final String xmlString) throws DistributeException {
		JAXBContext context = null;
		Unmarshaller unmarshaller = null;
		Object obj = null;
		try {
			context = JAXBContext.newInstance(objClass);
			unmarshaller = context.createUnmarshaller();
			obj = unmarshaller.unmarshal(new StringReader(xmlString));

		} catch (JAXBException e) {
			e.printStackTrace();
			throw new DistributeException("단말 Remapping 2차 ACK READ BODY ERROR : {0}", new Object[] { "JAXBException XML Build ERROR" }, e);
		}

		return obj;
	}

	/**
	 * 단말등록 및 단말삭제에 사용될 엑셀 파일을 업로드 한다.<br/>
	 * 단말조회를 통해 내려받은 엑셀파일을 그래도 올려도 상관 없으며<br/>
	 * 왼쪽 첫 컬럼에 AID리스트만 파싱하게 된다.<br/>
	 * 여기서는 해당 엑셀파일을 임시저장소에 저장하고, path만 화면으로 다시 내려준다.
	 */
	public void ajaxExcelUpload() {
		SimpleDateFormat SS = new SimpleDateFormat("yyyyMMddHHmmssss");
		SimpleDateFormat DD = new SimpleDateFormat("yyyyMMdd");

		File storePath;
		String tempPath = "/" + DD.format(new Date()) + "/" + SS.format(new Date()) + ".xls";
		storePath = new File(this.conf.getString("omp.common.path.temp.base"), tempPath);
		int resultCode = 1;
		int len = (int) param.getAidFile().length();
		if (len < 10485760) {
			try {
				FileUtil.move(param.getAidFile(), storePath, true);
			} catch (IOException e) {
				resultCode = 0;
			}
		}
		JSONObject jsonObject = new JSONObject();
		// Make Json String
		this.res.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		try {
			jsonObject.put("resultCode", resultCode);
			jsonObject.put("tempPath", tempPath);
			jsonObject.put("size", len);

			writer = this.res.getWriter();
			writer.write(jsonObject.toString());
		} catch (Exception e) {} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public long getTotalCount() {
		return totalCount;
	}

	public PhoneMappingParam getParam() {
		return param;
	}

	public void setParam(PhoneMappingParam param) {
		this.param = param;
	}

	public void setSearchParam(SearchParam searchParam) {
		this.searchParam = searchParam;
	}

	public SearchParam getSearchParam() {
		return searchParam;
	}

	public List<PhoneRemMgr> getPhoneRemMgrList() {
		return phoneRemMgrList;
	}

	public PhoneRemMgr getPhoneRemMgr() {
		return phoneRemMgr;
	}

	public List<PhoneRemScid> getPhoneRemScidList() {
		return phoneRemScidList;
	}

	public boolean getFirstSearch() {
		return firstSearch;
	}

	public List<Contents> getContentsList() {
		return contentsList;
	}

	public void setContentsList(List<Contents> contentsList) {
		this.contentsList = contentsList;
	}
	public Contents getContents() {
		return contents;
	}
	public void setContents(Contents contents) {
		this.contents = contents;
	}
	public ContentsSub getSub() {
		return sub;
	}
	public void setSub(ContentsSub sub) {
		this.sub = sub;
	}

	
}
