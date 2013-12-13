/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 8. | Description
 *
 */

package com.omp.admin.product.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.common.Constants;
import com.omp.admin.product.model.Contents;
import com.omp.admin.product.model.ContentsImg;
import com.omp.admin.product.model.ContentsSaleHistory;
import com.omp.admin.product.model.ContentsSub;
import com.omp.admin.product.model.ContentsVerify;
import com.omp.admin.product.model.DpCat;
import com.omp.admin.product.model.SubContents;
import com.omp.admin.product.service.ContentsManagementService;
import com.omp.admin.product.service.ContentsManagementServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.PagenateList;
import com.omp.commons.product.service.DisplayDistributeService;
import com.omp.commons.product.service.DisplayDistributeServiceImpl;
import com.omp.commons.product.service.DownloadDistributeService;
import com.omp.commons.product.service.DownloadDistributeServiceImpl;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;

/**
 * 상품관리> 상품정보<br/>
 * <ul>
 * <li>상품리스트 : listContents</li>
 * <li>상품리스트 엑셀 : downloadContentList</li>
 * <li>상품상세 [판매금지] : ajaxUpdateStopSaleStat</li>
 * <li>상품상세 [판매금지해제] : ajaxUpdateStartSaleStat</li>
 * <li>상품상세 > 기본정보탭 : viewContentsBaseInfo</li>
 * <li>상품상세 > 기본정보탭 [정산율변경] : ajaxUpdateRate</li>
 * <li>상품상세 > 기본정보탭 [검증레벨변경] : ajaxUpdateSignOption</li>
 * <li>상품상세 > 상품정보탭 : viewContentsProductInfo</li>
 * <li>상품상세 > 상품정보탭 [상품정보수정] : ajaxUpdateProductInfo</li>
 * <li>상품상세 > 상품정보탭 [ARM등록] : ajaxRegistArm</li>
 * <li>상품상세 > 개발정보탭 : viewDevConts</li>
 * <li>상품상세 > 판매중개발정보탭 : viewSaleDevConts</li>
 * <li>상품상세 > 검증요청개발정보탭 : viewSignDevConts</li>
 * <li>상품상세 > 상태변경내역탭 : viewSaleStatHisList</li>
 * <li>상품상세 > 검증내역탭 : viewProductVerifyDetail</li>
 * <li>상품상세 > 검증내역 > 검증내역상세 팝업 : popProductVerifyDetail</li>
 * </ul>
 * 
 * @author bcpark
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ContentsManagementAction extends BaseAction {

	// ========================== Domain & Constructor =================================
	// service
	private ContentsManagementService service = null;

	// 파라미터
	private ContentsSub sub = null;

	// 상품 도메인
	private Contents contents = null;

	// 상품 리스트 
	private List<Contents> list = null;

	// 상품 상세정보 이미지 리스트 
	private Map<String, ContentsImg> contentsImgMap = null;

	// 개발자 키워드 리스트
	private List<String> contentsTagList = null;

	// 상품 리스트 totlaCount
	private long totalCount;

	// 카테고리 리스트
	private List<DpCat> dpCatList2; // category level2

	// subContents Info
	private List<SubContents> subContentsList;

	// 상태변경 내역
	private List<ContentsSaleHistory> contentsSaleHistoryList;

	// 검증내역 리스트 및 상세
	private ContentsVerify contentsVerify;
	private List<ContentsVerify> contentsVerifyList;

	// 리스트 초기 검색 구분
	private boolean firstSearch;

	/**
	 * 기본 생성자<br/>
	 */
	public ContentsManagementAction() {
		service = new ContentsManagementServiceImpl();
	}

	/**
	 * 상품관리 > 상품정보 > 상품리스트<br/>
	 * 검색조건에 따른 상품리스트 정보를 가져온다.<br/>
	 * 검색조건이 들어오지 않았을 경우 기본 일주일 조회 조건을 셋팅한다.<br/>
	 * 
	 * @return
	 */
	public String listContents() {
		reqInit();
		// 검색조건 없을 경우
		if (sub == null) {
			sub = new ContentsSub();
			firstSearch = true;
			// 기본 일주일 검색을 셋팅
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		} else {
			firstSearch = false;
		}

		// detail 쪽에서 master No가 넘어온 경우 
		if (StringUtils.isNotBlank(sub.getMasterNo())) {
			try {
				sub.getPage().setNo(Integer.parseInt(sub.getMasterNo()));
			} catch (NumberFormatException nfe) {}
		}

		// category1 default set as "DP01"
		sub.setDpCat1(StringUtils.defaultIfEmpty(sub.getDpCat1(), "DP01"));
		// get category2 list
		dpCatList2 = service.getDpCatList(sub.getDpCat1());

		// search start / end date change db query format
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));

		// 초기 페이지에서는 검색을 하지 않는다.
		if (!firstSearch) {
			list = service.getContentsList(sub);
			totalCount = ((PagenateList) list).getTotalCount();
		}
		return SUCCESS;
	}

	/**
	 * 상품리스트 화면에서 엑셀 다운로드.
	 * 
	 * @return
	 */
	public String downloadContentList() {
		reqInit();
		if (sub == null) {
			sub = new ContentsSub();

			// before 7 days default search
			sub.setSearchWeek("Y");
			sub.setEndDate(DateUtil.getSysDate("yyyy-MM-dd"));
			sub.setStartDate(DateUtil.getAddDay(-7, "yyyy-MM-dd"));
		}
		// category1 default set as "DP01"
		sub.setDpCat1(StringUtils.defaultIfEmpty(sub.getDpCat1(), "DP01"));
		sub.setStartDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getStartDate()).trim()));
		sub.setEndDateDB(DateUtil.toDBFormat(StringUtils.defaultString(sub.getEndDate()).trim()));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String yyyymmdd = sdf.format(new Date());

		// TODO 조회기간 무력화, 페이징 조건 무력화는 commonDao에서 처리되도록 되어 있슴
		this.setDownloadFile(this.service.getExcelContentsList(this.sub), "application/vnd.ms-excel; charset=UTF-8", "產品_" + yyyymmdd
				+ ".xls");
		return SUCCESS;
	}

	/**
	 * 상품 기본정보 탭 정보
	 * 
	 * @return
	 */
	public String viewContentsBaseInfo() {
		if (sub == null || StringUtils.isBlank(sub.getCid())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}

		reqInit();
		contents = service.getContentsBaseInfo(sub.getCid());
		return SUCCESS;
	}

	/**
	 * 상품기본정보에서 검증레벨 변경 액션
	 */
	public void ajaxUpdateSignOption() {
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("성공적으로 처리 되었습니다.");

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cid", this.req.getParameter("cid"));
		paramMap.put("signOption", this.req.getParameter("signOption"));

		try {
			String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			paramMap.put("adminId", adminId);

			int updCnt = service.updateContentsSignOption(paramMap);
			if (updCnt == 0) {
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			resultCode = 1;
		} catch (Exception e) {
			resultCode = 0;
			msg = e.getLocalizedMessage();
		}

		if (logger.isInfoEnabled()) {
			logger.info("product change SingOption");
			logger.info("cid : {0}", new Object[] { paramMap.get("cid") });
			logger.info("signOption : {0}", new Object[] { paramMap.get("signOption") });
			logger.info("result : {0}", new Object[] { ((resultCode == 1) ? "SUCCESS" : "FAIL") });
			logger.info("result msg : {0}", new Object[] { msg });
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
	 * 상품기본정보에서 정산율 변경 액션<br/>
	 * 정산율 변경시에는 TBL_PD_CONTS 뿐만 아니라 정산율 관리 테이블인<br/>
	 * TBL_PROD_SETTL도 같이 업데이트 한다.
	 */
	public void ajaxUpdateRate() {
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("성공적으로 처리 되었습니다.");

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cid", this.req.getParameter("cid"));
		paramMap.put("adjRate", this.req.getParameter("adjRate"));
		paramMap.put("adjRateSkt", this.req.getParameter("adjRateSkt"));

		try {
			String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			paramMap.put("adminId", adminId);

			int updCnt = service.updateContentsRate(paramMap);
			if (updCnt == 0) {
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			resultCode = 1;
		} catch (Exception e) {
			resultCode = 0;
			msg = e.getLocalizedMessage();
		}

		if (logger.isInfoEnabled()) {
			logger.info("product rate change");
			logger.info("cid : {0}", new Object[] { paramMap.get("cid") });
			logger.info("adjRate : {0}", new Object[] { paramMap.get("adjRate") });
			logger.info("adjRateSkt : {0}", new Object[] { paramMap.get("adjRateSkt") });
			logger.info("result : {0}", new Object[] { ((resultCode == 1) ? "SUCCESS" : "FAIL") });
			logger.info("result msg : {0}", new Object[] { msg });
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
	 * DRM 미사용인 상품을 ARM서버와 연동하여 사용으로 등록<br/>
	 * 이때 상품의 판매상태가 판매상태가 "판매금지/판매중지/판매중" 이어야 하고<br/>
	 * 검증상태가 "검증대기/검증중"이 아니어야 한다.<br/>
	 * <ol>
	 * <li>TBL_PD_CONTS DRM_YN SET "Y"</li>
	 * <li>TBL_DP_CONTS DRM_YN SET "Y"</li>
	 * <li>ARM 서버 연동</li>
	 * </ol>
	 */
	public void ajaxRegistArm() {
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("성공적으로 처리 되었습니다.");

		String cid = req.getParameter("cid");

		try {
			if (StringUtils.isBlank(cid)) {
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			boolean isSuc = service.registArm(cid);
			if (isSuc == false) {
				throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.");
			}
			resultCode = 1;
		} catch (Exception e) {
			resultCode = 0;
			msg = e.getLocalizedMessage();
		}

		if (logger.isInfoEnabled()) {
			logger.info("product regist ARM");
			logger.info("cid : {0}", new Object[] { cid });
			logger.info("result : {0}", new Object[] { ((resultCode == 1) ? "SUCCESS" : "FAIL") });
			logger.info("result msg : {0}", new Object[] { msg });
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
	 * 상품상세 > 상품정보탭
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewContentsProductInfo() {
		if (sub == null || StringUtils.isBlank(sub.getCid())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}
		reqInit();
		// contents Product
		contents = service.getContentsProductInfo(sub.getCid());
		// contents Image Map
		contentsImgMap = service.getContentsImgMap(sub.getCid());
		// contents Tag List
		contentsTagList = service.getContentsTagList(sub.getCid());
		// get category2 list
		dpCatList2 = service.getDpCatList("DP01");
		return SUCCESS;
	}

	/**
	 * 상품정보탭에서 상품의 정보를 업데이트 한다. <br/>
	 * 판매상태가 판매중이라면, DD MAIN, DP MAIN 배포를 같이 한다.<br/>
	 * 업데이트 하는 필드는 아래와 같다.
	 * <ul>
	 * <li>카테고리[필수]</li>
	 * <li>promotion url</li>
	 * <li>상품요약설명[필수]</li>
	 * <li>상품상세설명[필수]</li>
	 * <li>개발자등록 키워드</li>
	 * </ul>
	 */
	public void ajaxUpdateProductInfo() {
		// parameter
		String changeData = this.req.getParameter("changeData");
		JSONObject jsonObject = JSONObject.fromObject(changeData);
		Map paramMap = (Map) JSONObject.toBean(jsonObject, HashMap.class);

		// result value
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("성공적으로 처리 되었습니다.");

		try {
			String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			paramMap.put("adminId", adminId);
			service.updateContentsProductInfo(paramMap);
			resultCode = 1;
		} catch (Exception e) {
			resultCode = 0;
			msg = e.getLocalizedMessage();
		}

		if (logger.isInfoEnabled()) {
			logger.info("product update Info");
			logger.info("cid : {0}", new Object[] { paramMap.get("cid") });
			logger.info("category : {0}", new Object[] { paramMap.get("ctgrCd2") });
			logger.info("summery description : {0}", new Object[] { paramMap.get("prodDescSmmr") });
			logger.info("detail description : {0}", new Object[] { paramMap.get("prodDescDtl") });
			logger.info("promotion url : {0}", new Object[] { paramMap.get("promotionUrl") });
			logger.info("adminId : {0}", new Object[] { paramMap.get("adminId") });
			logger.info("result : {0}", new Object[] { ((resultCode == 1) ? "SUCCESS" : "FAIL") });
			logger.info("result msg : {0}", new Object[] { msg });
		}

		// Make Json String
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
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
	 * 상품 판매금지 액션<br/>
	 * 상품의 상태가 판매중/판매중지이고, 상품의 검증상태가 검증대기/검증중이 아니어야 한다.
	 */
	public void ajaxUpdateStopSaleStat() {
		// parameter
		String cid = this.req.getParameter("cid");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cid", cid);

		// result value
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("성공적으로 처리 되었습니다.");

		try {
			String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			paramMap.put("adminId", adminId);
			paramMap.put("saleStat", Constants.CONTENT_SALE_STAT_RESTRIC);
			int updCnt = service.updateStopSaleStat(paramMap);
			resultCode = 1;
		} catch (Exception e) {
			resultCode = 0;
			msg = e.getLocalizedMessage();
		}

		if (logger.isInfoEnabled()) {
			logger.info("product stop sale ");
			logger.info("cid : {0}", new Object[] { paramMap.get("cid") });
			logger.info("adminId : {0}", new Object[] { paramMap.get("adminId") });
			logger.info("result : {0}", new Object[] { ((resultCode == 1) ? "SUCCESS" : "FAIL") });
			logger.info("result msg : {0}", new Object[] { msg });
		}

		// Make Json String
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		try {
			JSONObject jsonObject = new JSONObject();
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
	 * 상품 판매금지 해제 액션<br/>
	 * 상품의 상태가 판매금지/해지요청이고, 상품의 검증상태가 검증대기/검증중이 아니어야 한다.<br/>
	 * DD MAIN 배포 및 DP FULL 배포를 수행한다.
	 */
	public void ajaxUpdateStartSaleStat() {
		// parameter
		String cid = this.req.getParameter("cid");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cid", cid);

		// result value
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("성공적으로 처리 되었습니다.");

		try {
			String adminId = com.omp.admin.common.util.CommonUtil.getLoginId(this.req.getSession());
			paramMap.put("adminId", adminId);
			paramMap.put("saleStat", Constants.CONTENT_SALE_STAT_ING);
			int updCnt = service.updateStartSaleStat(paramMap);
			resultCode = 1;
		} catch (Exception e) {
			resultCode = 0;
			msg = e.getLocalizedMessage();
		}

		if (logger.isInfoEnabled()) {
			logger.info("product start sale ");
			logger.info("cid : {0}", new Object[] { paramMap.get("cid") });
			logger.info("adminId : {0}", new Object[] { paramMap.get("adminId") });
			logger.info("result : {0}", new Object[] { ((resultCode == 1) ? "SUCCESS" : "FAIL") });
			logger.info("result msg : {0}", new Object[] { msg });
		}

		// Make Json String
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
		try {
			JSONObject jsonObject = new JSONObject();
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
	 * 상품상세 > 개발정보탭<br/>
	 * 상품의 정보를 가져온다.(PD)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewDevConts() {
		if (sub == null || StringUtils.isBlank(sub.getCid())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}
		reqInit();
		contents = service.getContentsDevConts(sub.getCid());
		subContentsList = service.getSubContents(sub.getCid());
		return SUCCESS;
	}

	/**
	 * 상품상세 > 판매중개발정보탭<br/>
	 * 판매중인 상품의 정보를 가져온다.(DP)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewSaleDevConts() {
		if (sub == null || StringUtils.isBlank(sub.getCid())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}
		reqInit();
		contents = service.getSaleDevConts(sub.getCid());
		subContentsList = service.getSaleSubContents(sub.getCid());
		return SUCCESS;
	}

	/**
	 * 상품상세 > 검증요청개발정보탭<br/>
	 * 검증요청된 개발정보를 가져온다.(CT)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewSignDevConts() {
		if (sub == null || StringUtils.isBlank(sub.getCid())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}
		reqInit();
		contents = service.getVerifyDevConts(sub.getCid());
		subContentsList = service.getVerifySubContents(sub.getCid());
		return SUCCESS;
	}

	/**
	 * 상품상세 > 상태변경내역 탭<br/>
	 * 상품의 상태 변경내역을 가져온다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewSaleStatHisList() {
		if (sub == null || StringUtils.isBlank(sub.getCid())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}
		reqInit();
		contents = service.getContentsBaseInfo(sub.getCid());
		contentsSaleHistoryList = service.getContentsSaleHistory(sub.getCid());
		return SUCCESS;
	}

	/**
	 * 상품상세 > 검증내역 탭<br/>
	 * 해당 상품의 검증요청한 모든 버전을 보여준다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewProductVerifyDetail() {
		if (sub == null || StringUtils.isBlank(sub.getCid())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}
		reqInit();
		contents = service.getContentsBaseInfo(sub.getCid());
		contentsVerifyList = service.getContentsVerifyList(sub);
		totalCount = ((PagenateList) contentsVerifyList).getTotalCount();
		return SUCCESS;
	}

	/**
	 * 상품상세 > 검증내역 탭 > 검증상세 팝업<br/>
	 * 해당 상품의 각 검증버전의 상세 정보를 보여준다.
	 * 
	 * @return
	 */
	public String popProductVerifyDetail() {
		if (sub == null || StringUtils.isBlank(sub.getCid()) || StringUtils.isBlank(sub.getVerifyReqVer())) {
			throw new NoticeException("컨텐츠 정보가 올바르지 않습니다.");
		}

		contentsVerify = service.getContentsVerifyInfo(sub);
		subContentsList = service.getContentsVerifyDetailList(sub);
		return SUCCESS;
	}

	/**
	 * DD 배포 내부 테스트 용 액션
	 * 
	 * @return
	 */
	public String content() {
		return SUCCESS;
	}

	/**
	 * DD 배포 내부 테스트 용 액션
	 * 
	 * @return
	 */
	public String phone() {
		return SUCCESS;
	}

	// ===================== SET/GET =============================================
	/**
	 * list total count
	 * 
	 * @return long totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * list parameter domain set
	 * 
	 * @param sub ContentsSub
	 */
	public void setSub(ContentsSub sub) {
		this.sub = sub;
	}

	/**
	 * parameter domain get
	 * 
	 * @return ContentsSub domain
	 */
	public ContentsSub getSub() {
		return sub;
	}

	/**
	 * contents list get
	 * 
	 * @return Contents List
	 */
	public List<Contents> getList() {
		return list;
	}

	/**
	 * product Contents Detail View
	 * 
	 * @return
	 */
	public Contents getContents() {
		return contents;
	}

	/**
	 * Product Info Contents Image
	 * 
	 * @return
	 */
	public Map<String, ContentsImg> getContentsImgMap() {
		return contentsImgMap;
	}

	/**
	 * product contents Seller Tag
	 * 
	 * @return
	 */
	public List<String> getContentsTagList() {
		return contentsTagList;
	}

	/**
	 * product category list
	 * 
	 * @return
	 */
	public List<DpCat> getDpCatList2() {
		return dpCatList2;
	}

	/**
	 * SubContents List
	 * 
	 * @return
	 */
	public List<SubContents> getSubContentsList() {
		return subContentsList;
	}

	/**
	 * @return
	 */
	public List<ContentsSaleHistory> getContentsSaleHistoryList() {
		return contentsSaleHistoryList;
	}

	/**
	 * @return
	 */
	public List<ContentsVerify> getContentsVerifyList() {
		return contentsVerifyList;
	}

	public boolean getFirstSearch() {
		return firstSearch;
	}

	/**
	 * @return
	 */
	public ContentsVerify getContentsVerify() {
		return contentsVerify;
	}

	private void reqInit() {
		req.setAttribute("CONTENT_SALE_STAT_NA", Constants.CONTENT_SALE_STAT_NA); //미승인상품 
		req.setAttribute("CONTENT_SALE_STAT_WAIT", Constants.CONTENT_SALE_STAT_WAIT); // 판매대기
		req.setAttribute("CONTENT_SALE_STAT_ING", Constants.CONTENT_SALE_STAT_ING); //판매중
		req.setAttribute("CONTENT_SALE_STAT_STOP", Constants.CONTENT_SALE_STAT_STOP); //판매중지
		req.setAttribute("CONTENT_SALE_STAT_RESTRIC", Constants.CONTENT_SALE_STAT_RESTRIC); //판매금지
		req.setAttribute("CONTENT_SALE_STAT_UNREGIST", Constants.CONTENT_SALE_STAT_UNREGIST); //해제요청

		req.setAttribute("CODE_VERIFY_INIT", Constants.CODE_VERIFY_INIT); // 미검증
		req.setAttribute("CODE_VERIFY_REQ", Constants.CODE_VERIFY_REQ); // 검증대기
		req.setAttribute("CODE_VERIFY_ING", Constants.CODE_VERIFY_ING); // 검증중
		req.setAttribute("CODE_VERIFY_END", Constants.CODE_VERIFY_END); // 검증완료
		req.setAttribute("CODE_VERIFY_CANCEL", Constants.CODE_VERIFY_CANCEL); // 검증취소

		req.setAttribute("AGREEMENT_STATUS_AGREE", Constants.AGREEMENT_STATUS_AGREE); // 승인
	}

	/**
	 * 개발 모드에서 Download 및 DP 쪽 FULL 배포를 위한 임시 Action<br/>
	 * 상품리스트 페이지에서 파라미터 &mode=dev 를 강제로 URL에 넣어주어야 해당 버튼(DD배포/DP배포)이 노출된다.
	 */
	public void ajaxDeplyFullTemporary() {
		// parameter
		String cid = req.getParameter("cid");
		String type = req.getParameter("type");

		JSONObject jsonObject = new JSONObject();

		// result value
		int resultCode = 1;
		String msg = LocalizeMessage.getLocalizedMessage("성공적으로 처리 되었습니다.");

		if (StringUtils.isBlank(cid)) {
			resultCode = 0;
			msg = "cid is empty";
		}

		if (StringUtils.isBlank(type) || !(type.equals("dd") || type.equals("dp"))) {
			resultCode = 0;
			msg = "deploy type must be dd or dp";
		}

		if (resultCode == 1) {
			try {
				if (type.equals("dd")) {
					DownloadDistributeService ddService = new DownloadDistributeServiceImpl();
					ddService.ddDeployContents(cid, false);
				} else if (type.equals("dp")) {
					DisplayDistributeService dpService = new DisplayDistributeServiceImpl();
					dpService.dpDeployContents(cid, false);
				}

				resultCode = 1;
			} catch (Exception e) {
				e.printStackTrace();
				resultCode = 0;
				msg = e.getLocalizedMessage();
			}
		}

		// Make Json String
		this.res.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = null;
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
}
