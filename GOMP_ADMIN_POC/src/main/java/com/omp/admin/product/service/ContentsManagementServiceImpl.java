/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * bcpark    2011. 3. 1.    Description
 *
 */
package com.omp.admin.product.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.product.model.ConstantsSaleStat;
import com.omp.admin.product.model.Contents;
import com.omp.admin.product.model.ContentsImg;
import com.omp.admin.product.model.ContentsSaleHistory;
import com.omp.admin.product.model.ContentsSub;
import com.omp.admin.product.model.ContentsVerify;
import com.omp.admin.product.model.DpCat;
import com.omp.admin.product.model.SubContents;
import com.omp.commons.Constants;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.model.ContentMailModel;
import com.omp.commons.product.service.ARMManagerService;
import com.omp.commons.product.service.ARMManagerServiceImpl;
import com.omp.commons.product.service.ContentsHistoryService;
import com.omp.commons.product.service.ContentsHistoryServiceImpl;
import com.omp.commons.product.service.DisplayDistributeService;
import com.omp.commons.product.service.DisplayDistributeServiceImpl;
import com.omp.commons.product.service.DownloadDistributeService;
import com.omp.commons.product.service.DownloadDistributeServiceImpl;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CommonUtil;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 상품관리 서비스
 * 
 * @author bcpark
 * @version 0.1
 */
public class ContentsManagementServiceImpl extends AbstractService implements ContentsManagementService {

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getDpCatList(java.lang.String)
	 */
	@Override
	public List<DpCat> getDpCatList(String upDpCatNo) {
		List<DpCat> result = null;

		try {
			result = commonDAO.queryForList("Product_Contents.selectDpCatList", upDpCatNo);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsList(com.omp.admin.product.model.ContentsSub)
	 */
	@Override
	public List<Contents> getContentsList(ContentsSub sub) {
		List<Contents> contentsList = null;
		try {
			contentsList = commonDAO.queryForPageList("Product_Contents.selectContentsList", sub);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contentsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getExcelContentsList(com.omp.admin.product.model.ContentsSub)
	 */
	@Override
	public File getExcelContentsList(ContentsSub sub) {

		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		// excelColumnDefineList.add(new ColumnInfoModel("cid", "컨탠츠ID"));
		excelColumnDefineList.add(new ColumnInfoModel("prodNm", "상품명"));
		excelColumnDefineList.add(new ColumnInfoModel("ctgrNm2", "카테고리(상품)"));
		excelColumnDefineList.add(new ColumnInfoModel("aid", "상품AID"));
		excelColumnDefineList.add(new ColumnInfoModel("mbrId", "개발자"));
		excelColumnDefineList.add(new ColumnInfoModel("prodPrc", "가격", ColumnInfoModel.COLUMN_TYPE_INTEGER));
		excelColumnDefineList.add(new ColumnInfoModel("saleStat", "판매상태", ColumnInfoModel.COLUMN_TYPE_CODE));
		excelColumnDefineList.add(new ColumnInfoModel("verifyPrgrYn", "검증상태", ColumnInfoModel.COLUMN_TYPE_CODE));
		excelColumnDefineList.add(new ColumnInfoModel("firstInsDt", "등록일", ColumnInfoModel.COLUMN_TYPE_DATE));

		try {
			return this.commonDAO.queryForExcel("Product_Contents.selectContentsList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsBaseInfo (com.omp.admin.product.model.ContentsSub)
	 */
	@Override
	public Contents getContentsBaseInfo(String cid) {
		Contents contents = null;
		try {
			contents = (Contents) commonDAO.queryForObject("Product_Contents.selectContentsBaseInfo", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contents;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsItemInfo (com.omp.admin.product.model.ContentsSub)
	 */
	@Override
	public Contents getContentsProductInfo(String cid) {
		Contents contents = null;
		try {
			contents = (Contents) commonDAO.queryForObject("Product_Contents.selectContentsProductInfo", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contents;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsTagList (java.lang.String)
	 */
	@Override
	public List<String> getContentsTagList(String cid) {
		List<String> contentsTagList = null;
		try {
			contentsTagList = commonDAO.queryForList("Product_Contents.getSellerTagList", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contentsTagList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsImgMap (java.lang.String)
	 */
	@Override
	public Map<String, ContentsImg> getContentsImgMap(String cid) {
		Map<String, ContentsImg> contentsImgMap = null;
		try {
			contentsImgMap = commonDAO.queryForMap("Product_Contents.getContentsImgMap", cid, "imgGbn");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contentsImgMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService# updateContentsSignOption(java.util.Map)
	 */
	@Override
	public int updateContentsSignOption(Map<String, String> paramMap) {
		// param check
		if (StringUtils.isBlank(paramMap.get("cid"))) {
			throw new InvalidInputException("컨텐츠 정보가 올바르지 않습니다.");
		}

		if (StringUtils.isBlank(paramMap.get("signOption"))) {
			throw new InvalidInputException("컨텐츠 정보가 올바르지 않습니다.");
		}

		if (StringUtils.isBlank(paramMap.get("adminId"))) {
			throw new InvalidInputException("올바른 요청경로가 아닙니다.");
		}

		int updCnt = 0;
		try {
			ConstantsSaleStat contentsSaleStat = (ConstantsSaleStat) commonDAO.queryForObject("Product_Contents.getSaleStat",
					(String) paramMap.get("cid"));

			if (contentsSaleStat == null) {
				throw new NoticeException("상품정보가 올바르지 않습니다.");
			}
			// 검증 중이거나 검증대기이면 수정 금지
			if (Constants.CODE_VERIFY_REQ.equals(contentsSaleStat.getVerifyPrgrYn())) {
				throw new NoticeException("검증대기중 이므로 수정할 수 없습니다.");
			}
			if (Constants.CODE_VERIFY_ING.equals(contentsSaleStat.getVerifyPrgrYn())) {
				throw new NoticeException("검증 중 이므로 수정할 수 없습니다.");
			}

			daoManager.startTransaction();
			updCnt = commonDAO.update("Product_Contents.updateContentsSignOption", paramMap);

			daoManager.commitTransaction();

			// HISTORY
			ContentsHistoryService historyService = new ContentsHistoryServiceImpl();
			historyService.insertContsHis((String) paramMap.get("cid"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}
		return updCnt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#updateContentsRate (java.util.Map)
	 */
	@Override
	public int updateContentsRate(Map<String, String> paramMap) {
		// parameter check
		if (StringUtils.isBlank(paramMap.get("cid")) || paramMap.get("cid").length() != 10) {
			throw new InvalidInputException("컨텐츠 정보가 올바르지 않습니다.");
		}
		int adjRate = 0, adjRateSkt = 0;

		if (StringUtils.isBlank(paramMap.get("adjRate"))) {
			throw new InvalidInputException("개발자 정산율 값이 올바르지 않습니다.");
		} else {
			try {
				adjRate = Integer.parseInt(paramMap.get("adjRate"));
			} catch (NumberFormatException e) {
				throw new InvalidInputException("개발자 정산율 값이 올바르지 않습니다.");
			}
		}
		if (StringUtils.isBlank(paramMap.get("adjRateSkt"))) {
			throw new InvalidInputException("마켓 정산율 값이 올바르지 않습니다.");
		} else {
			try {
				adjRateSkt = Integer.parseInt(paramMap.get("adjRateSkt"));
			} catch (NumberFormatException e) {
				throw new InvalidInputException("마켓 정산율 값이 올바르지 않습니다.");
			}
		}

		if ((adjRate + adjRateSkt) != 100)
			throw new InvalidInputException("정산율 합이 100이 되도록 입력해 주세요.");

		if (StringUtils.isBlank(paramMap.get("adminId"))) {
			throw new InvalidInputException("올바른 요청경로가 아닙니다.");
		}

		int updCnt = 0;
		try {
			daoManager.startTransaction();
			updCnt = commonDAO.update("Product_Contents.updateContentsRate", paramMap);

			// TBL_PROD_SETTL 정산 율 변경 테이블 (primary key : prod_id, startdate) 변경 Logic
			// 1. 최초 상품 등록 시(devPOC) 기본 정산 율 65로 상품(TBL_PD_CONTS)테이블에 등록
			// 2. 최초 DP(전시) 배포 시 정산율관리(TBL_PROD_SETTL) 테이블에 상품(TBL_PD_CONTS)테이블에 등록된 정산 율로 신규 INSERT(오늘 ~ 9999년 12월 31일)
			// 3. 상품의 정산 율 수정 시 (adminPOC) 
			// 3.1 해당 상품의 판매 상태가 [미 승인, 판매대기]인 경우 상품(TBL_PD_CONTS)테이블의 정산 율만 변경
			//   3.2 해당 상품의 판매 상태가 판매 중 이상[판매 중, 판매중지, 판매금지, 해지요청]일 때 정산율관리(TBL_PROD_SETTL)테이블도 같이 변경
			//     3.2.1 정산율관리(TBL_PROD_SETTL)테이블에 해당 PROD_ID로 에 정산 율 Data가 있는지 한번 더 체크
			//       3.2.1.1 기존 정산 율 데이터가 있는 경우 (오늘 날짜로 시작하는 ROW가 있는지 체크)
			//         * 오늘 날짜로 시작하는 정산 율 Data가 있는 경우 
			//             해당 row의 정산율 update
			//         * 오늘 날짜로 시작하는 정산 율 Data가 없는 경우 
			//			   마지막 정산 Data의 EndDate를 어제 날짜로 Mark 처리 후 신규 Insert(오늘 ~ 9999년 12월 31일)
			//       3.2.1.2 기존 정산 율 데이터가 없는 경우
			//         신규 Insert (오늘 ~ 9999년 12월 31일)
			ConstantsSaleStat contentsSaleStat = (ConstantsSaleStat) commonDAO.queryForObject("Product_Contents.getSaleStat",
					(String) paramMap.get("cid"));

			if (contentsSaleStat == null) {
				throw new NoticeException("상품정보가 올바르지 않습니다.");
			}

			// 상품상태가 판매중 이상이면(판매중, 판매중지, 판매금지, 해지요청)
			if (isOverSaleIngStat(contentsSaleStat.getSaleStat())) {

				// // TBL_PROD_SETTL에 해당 CID ROW가 있는지 검사
				String prod_id = (String) commonDAO.queryForObject("Product_Contents.checkProdSettl", paramMap);
				if (StringUtils.isBlank(prod_id)) {
					// TBL_PROD_SETTL에 해당 CID ROW가 없으면 신규 Insert,
					commonDAO.insert("DisplayDeploy.insertProdSettlToday", paramMap);
				} else {
					// TBL_PROD_SETTL에 해당 CID ROW가 존재 하는 경우
					// TBL_PROD_SETTL에 오늘 날짜로 시작하는 데이터가 존재하는지 검사
					prod_id = (String) commonDAO.queryForObject("Product_Contents.checkProdSettlStrtDt", paramMap);

					if (StringUtils.isBlank(prod_id)) {
						// 정산율의 시작일자가 오늘인 데이터가 없으면 End날짜 Mark 처리 후 오늘 날짜로 신규 INSERT(오늘 ~ 9999.12.31)
						commonDAO.update("DisplayDeploy.updateProdSettlEndDt", paramMap);
						commonDAO.insert("DisplayDeploy.insertProdSettlToday", paramMap);
					} else {
						// 정산율의 시작일자가 오늘인 데이터가 있으면 해당 ROW 업데이트
						commonDAO.update("DisplayDeploy.updateProdSettl", paramMap);
					}

				}

			}

			daoManager.commitTransaction();

			// HISTORY
			ContentsHistoryService historyService = new ContentsHistoryServiceImpl();
			historyService.insertContsHis((String) paramMap.get("cid"));

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}
		return updCnt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService# updateContentsProductInfo(java.util.Map)
	 */
	@Override
	public int updateContentsProductInfo(Map paramMap) {
		// parameter validation check
		List<String> tags = null;
		try {
			tags = (List<String>) paramMap.get("tagNm[]");
		} catch (ClassCastException cce) {}

		if (StringUtils.isBlank((String) paramMap.get("cid"))) {
			throw new InvalidInputException("컨텐츠 정보가 올바르지 않습니다.");
		}

		if (StringUtils.isBlank((String) paramMap.get("ctgrCd2"))) {
			throw new InvalidInputException("카테고리가 지정되지 않았습니다.");
		}

		if (StringUtils.isBlank((String) paramMap.get("prodDescSmmr"))) {
			throw new InvalidInputException("상품요약설명을 입력해 주세요.");
		}

		if (StringUtils.isBlank((String) paramMap.get("prodDescDtl"))) {
			throw new InvalidInputException("상품상세설명을 입력해 주세요.");
		}

		if (paramMap.get("promotionUrl") != null && StringUtils.isNotEmpty(((String) paramMap.get("promotionUrl")).trim())) {
			try {
				new URL((String) paramMap.get("promotionUrl"));
			} catch (MalformedURLException e1) {
				throw new InvalidInputException("Promotion URL이 올바르지 않습니다.", e1);
			}
		}

		if (StringUtils.isBlank((String) paramMap.get("adminId"))) {
			throw new InvalidInputException("올바른 요청경로가 아닙니다.");
		}

		// promotionUrl trim
		paramMap.put("promotionUrl", ((String) paramMap.get("promotionUrl")).trim());
		// strip html tag except A 
		paramMap.put("prodDescDtl", CommonUtil.replaceAllTagsExceptA((String) paramMap.get("prodDescDtl"), "").trim());
		paramMap.put("prodDescSmmr", CommonUtil.replaceAllTagsExceptA((String) paramMap.get("prodDescSmmr"), "").trim());

		int updCnt = 0;
		try {

			ConstantsSaleStat contentsSaleStat = (ConstantsSaleStat) commonDAO.queryForObject("Product_Contents.getSaleStat",
					(String) paramMap.get("cid"));

			if (contentsSaleStat == null) {
				throw new NoticeException("상품정보가 올바르지 않습니다.");
			}

			// 검증 중이거나 검증대기이면 수정 금지
			if (Constants.CODE_VERIFY_REQ.equals(contentsSaleStat.getVerifyPrgrYn())) {
				throw new NoticeException("검증대기중 이므로 수정할 수 없습니다.");
			}
			if (Constants.CODE_VERIFY_ING.equals(contentsSaleStat.getVerifyPrgrYn())) {
				throw new NoticeException("검증 중 이므로 수정할 수 없습니다.");
			}
			daoManager.startTransaction();
			// ========= product info ==============
			updCnt = commonDAO.update("Product_Contents.updateContentsProductInfo", paramMap);

			// ========= developer tag ================
			commonDAO.delete("Product_Contents.deleteContentsTag", paramMap.get("cid"));

			Map<String, String> tagMap = new HashMap<String, String>();
			tagMap.put("cid", (String) paramMap.get("cid"));
			tagMap.put("adminId", (String) paramMap.get("adminId"));
			if (tags != null) {
				for (String tag : tags) {
					if (tag != null && StringUtils.isNotEmpty(tag.trim())) {
						tagMap.put("tagNm", tag.trim());
						commonDAO.insert("Product_Contents.insertContentsTag", tagMap);
					}
				}
			}

			// =========== category ================
			commonDAO.delete("Product_Contents.deleteCategory", paramMap.get("cid"));
			commonDAO.insert("Product_Contents.insertCategory", paramMap);

			// 판매상태가 판매중이라면, DD MAIN, DP MAIN 배포 
			if (Constants.CONTENT_SALE_STAT_ING.equals(contentsSaleStat.getSaleStat())) {
				// DP
				DisplayDistributeService dpService = new DisplayDistributeServiceImpl();
				dpService.dpDeployContents((String) paramMap.get("cid"), true);
				// DD
				DownloadDistributeService ddService = new DownloadDistributeServiceImpl();
				ddService.ddDeployContents((String) paramMap.get("cid"), true);
			}

			daoManager.commitTransaction();

			// HISTORY
			ContentsHistoryService historyService = new ContentsHistoryServiceImpl();
			historyService.insertContsHis((String) paramMap.get("cid"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}
		return updCnt;
	}

	// ============================ DevInfo ==================================
	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsDevConts (java.lang.String)
	 */
	@Override
	public Contents getContentsDevConts(String cid) {
		Contents contents = null;
		try {
			contents = (Contents) commonDAO.queryForObject("Product_Contents.selectContentsDevInfo", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contents;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getSubContents (java.lang.String)
	 */
	@Override
	public List<SubContents> getSubContents(String cid) {
		List<SubContents> subContentsList = null;
		try {
			subContentsList = commonDAO.queryForList("Product_Contents.selectSubContents", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return subContentsList;
	}

	// ============================ SaleInfo ==================================
	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getSaleDevConts (java.lang.String)
	 */
	@Override
	public Contents getSaleDevConts(String cid) {
		Contents contents = null;
		try {
			contents = (Contents) commonDAO.queryForObject("Product_Contents.selectSaleConts", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contents;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getSaleSubContents (java.lang.String)
	 */
	@Override
	public List<SubContents> getSaleSubContents(String cid) {
		List<SubContents> subContentsList = null;
		try {
			subContentsList = commonDAO.queryForList("Product_Contents.selectSaleSubContents", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return subContentsList;
	}

	// ============================ VerifyInfo ==================================
	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getSaleDevConts (java.lang.String)
	 */
	@Override
	public Contents getVerifyDevConts(String cid) {
		Contents contents = null;
		try {
			contents = (Contents) commonDAO.queryForObject("Product_Contents.selectVerifyConts", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contents;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getSaleSubContents (java.lang.String)
	 */
	@Override
	public List<SubContents> getVerifySubContents(String cid) {
		List<SubContents> subContentsList = null;
		try {
			subContentsList = commonDAO.queryForList("Product_Contents.selectVerifySubContents", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return subContentsList;
	}

	// ============================ SaleHistory ==================================
	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService# getContentsSaleHistory(java.lang.String)
	 */
	@Override
	public List<ContentsSaleHistory> getContentsSaleHistory(String cid) {
		List<ContentsSaleHistory> contentsSaleHistoryList = null;
		try {
			contentsSaleHistoryList = commonDAO.queryForList("Product_Contents.selectStatHistory", cid);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contentsSaleHistoryList;
	}

	// ============================= verify List ======================================
	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsVerifyList (java.lang.String)
	 */
	@Override
	public List<ContentsVerify> getContentsVerifyList(ContentsSub sub) {
		List<ContentsVerify> contentsVerifyList = null;
		try {
			contentsVerifyList = commonDAO.queryForPageList("Product_Contents.selectContentsVerifyList", sub);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contentsVerifyList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#getContentsVerifyInfo(com.omp.admin.product.model.ContentsSub)
	 */
	@Override
	public ContentsVerify getContentsVerifyInfo(ContentsSub sub) {
		ContentsVerify contentsVerify = null;
		try {
			contentsVerify = (ContentsVerify) commonDAO.queryForObject("Product_Contents.selectContentsVerifyInfo", sub);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contentsVerify;

	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService# getContentsVerifyDetailList(java.lang.String)
	 */
	@Override
	public List<SubContents> getContentsVerifyDetailList(ContentsSub sub) {
		List<SubContents> contentsVerifydetailList = null;
		try {
			contentsVerifydetailList = commonDAO.queryForList("Product_Contents.selectContentsVerifyDetailList", sub);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return contentsVerifydetailList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#updateStopSaleStat(java.util.Map)
	 */
	@Override
	public int updateStopSaleStat(Map<String, String> paramMap) {
		ConfigProperties conf = new ConfigProperties();

		// parameter check
		if (StringUtils.isBlank(paramMap.get("cid")) || paramMap.get("cid").length() != 10) {
			throw new InvalidInputException("컨텐츠 정보가 올바르지 않습니다.");
		}

		if (StringUtils.isBlank(paramMap.get("adminId"))) {
			throw new InvalidInputException("올바른 요청경로가 아닙니다.");
		}

		if (StringUtils.isBlank(paramMap.get("saleStat"))) {
			throw new InvalidInputException("판매상태 변경 코드가 올바르지 않습니다.");
		}

		int updCnt = 0;
		try {
			// 상품 정보 가져오기
			Map<String, String> productInfo = (Map<String, String>) commonDAO.queryForObject("Product_Contents.getProductInfo",
					paramMap.get("cid"));

			// 검증 대기나 검증 중이 아니고, 판매 상태가 판매중이거나 판매중지인지 확인.
			CommCode beforeStatCode = CacheCommCode.getCommCodeByFullCode(productInfo.get("SALESTAT"));
			if (!Constants.CONTENT_SALE_STAT_ING.equals(productInfo.get("SALESTAT"))
					&& !Constants.CONTENT_SALE_STAT_STOP.equals(productInfo.get("SALESTAT"))) {
				throw new NoticeException("판매상태가 {0}상태이므로, 요청 처리가 실패했습니다.", new Object[] { beforeStatCode.getCdNm() });
			}

			CommCode beforeVerifyCode = CacheCommCode.getCommCodeByFullCode(productInfo.get("VERIFYPRGRYN"));
			if (Constants.CODE_VERIFY_REQ.equals(productInfo.get("VERIFYPRGRYN"))
					|| Constants.CODE_VERIFY_ING.equals(productInfo.get("VERIFYPRGRYN"))) {
				throw new NoticeException("검증상태가 {0}이므로, 요청 처리가 실패했습니다.", new Object[] { beforeVerifyCode.getCdNm() });
			}

			daoManager.startTransaction();
			updCnt = commonDAO.update("Product_Contents.changeSaleStat", paramMap);

			// DP
			DisplayDistributeService dpService = new DisplayDistributeServiceImpl();
			dpService.restricSaleContents((String) paramMap.get("cid"));

			// ARM 연동
			ARMManagerService armService = new ARMManagerServiceImpl();
			armService.connectARMReqModifyApplicationStatus(paramMap.get("cid"));

			daoManager.commitTransaction();

			// HISTORY
			ContentsHistoryService historyService = new ContentsHistoryServiceImpl();
			historyService.insertContsHis((String) paramMap.get("cid"));
			historyService.insertSaleStatHis(paramMap.get("cid"), Constants.CONTENT_SALE_STAT_RESTRIC, paramMap.get("adminId"), true);

			try {
				// 메일 보내기
				ContentMailModel contentMailModel = new ContentMailModel();
				SendMailModel mail;
				MailSendAgent msa;

				CommCode afterStatCode = CacheCommCode.getCommCodeByFullCode(Constants.CONTENT_SALE_STAT_RESTRIC);

				contentMailModel.setProdNm(productInfo.get("PRODNM")); // 상품명
				contentMailModel.setDevUserId(productInfo.get("DEVUSERID")); // 개발자 ID
				contentMailModel.setUpdDttm(DateUtil.toDisplayFormat(DateUtil.getSysDate(), "-")); // 업데이트 일자
				contentMailModel.setOldSaleStat(beforeStatCode.getCdNm()); // 이전 판매상태
				contentMailModel.setNewSaleStat(afterStatCode.getCdNm()); // 변경된 판매상태

				//2. SendMailModel Setting
				mail = new SendMailModel();
				mail.setTemplateId("/DEV/PD_005.html");
				mail.setRefAppId("ContentsManagementAction.ajaxUpdateStopSaleStat");
				mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 상품이 운영자에 의해 판매가 금지되었습니다."));
				mail.setRefDataId(paramMap.get("cid"));
				mail.setToAddr(productInfo.get("EMAILADDR"));
				//mail.setToName(mbrId);
				mail.setContentData(contentMailModel);
				mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
				mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
				mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
				msa = CommunicatorFactory.getMailSendAgent();
				//3. Send Mail
				long result = msa.requestMailSend(mail);

				if (log.isInfoEnabled()) {
					log.info("product SALE STOP MAIL SEND");
					log.info("{0}", new Object[] { mail });
					log.info("result={0}", new Object[] { result });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}
		return updCnt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#updateStartSaleStat(java.util.Map)
	 */
	@Override
	public int updateStartSaleStat(Map<String, String> paramMap) {
		ConfigProperties conf = new ConfigProperties();

		// parameter check
		if (StringUtils.isBlank(paramMap.get("cid")) || paramMap.get("cid").length() != 10) {
			throw new InvalidInputException("컨텐츠 정보가 올바르지 않습니다.");
		}

		if (StringUtils.isBlank(paramMap.get("adminId"))) {
			throw new InvalidInputException("올바른 요청경로가 아닙니다.");
		}

		if (StringUtils.isBlank(paramMap.get("saleStat"))) {
			throw new InvalidInputException("판매상태 변경 코드가 올바르지 않습니다.");
		}

		int updCnt = 0;
		try {

			// 상품 정보 가져오기
			Map<String, String> productInfo = (Map<String, String>) commonDAO.queryForObject("Product_Contents.getProductInfo",
					paramMap.get("cid"));

			// 검증 대기나 검증 중이 아니고, 판매 상태가 판매금지나 해지요청인지 확인.
			CommCode beforeStatCode = CacheCommCode.getCommCodeByFullCode(productInfo.get("SALESTAT"));
			if (!Constants.CONTENT_SALE_STAT_RESTRIC.equals(productInfo.get("SALESTAT"))
					&& !Constants.CONTENT_SALE_STAT_UNREGIST.equals(productInfo.get("SALESTAT"))) {
				throw new NoticeException("판매상태가 {0}상태이므로, 요청 처리가 실패했습니다.", new Object[] { beforeStatCode.getCdNm() });
			}

			CommCode beforeVerifyCode = CacheCommCode.getCommCodeByFullCode(productInfo.get("VERIFYPRGRYN"));
			if (Constants.CODE_VERIFY_REQ.equals(productInfo.get("VERIFYPRGRYN"))
					|| Constants.CODE_VERIFY_ING.equals(productInfo.get("VERIFYPRGRYN"))) {
				throw new NoticeException("검증상태가 {0}이므로, 요청 처리가 실패했습니다.", new Object[] { beforeVerifyCode.getCdNm() });
			}

			daoManager.startTransaction();
			updCnt = commonDAO.update("Product_Contents.changeSaleStat", paramMap);

			// 전시배포 (Display 연동) DP FULL 배포
			DisplayDistributeService dpService = new DisplayDistributeServiceImpl();
			dpService.dpDeployContents((String) paramMap.get("cid"), false);

			// Download 연동 DD MAIN 배포
			DownloadDistributeService ddService = new DownloadDistributeServiceImpl();
			ddService.ddDeployContents((String) paramMap.get("cid"), true);

			// ARM 연동
			ARMManagerService armService = new ARMManagerServiceImpl();
			armService.connectARMReqModifyApplicationStatus(paramMap.get("cid"));

			daoManager.commitTransaction();

			// HISTORY
			ContentsHistoryService historyService = new ContentsHistoryServiceImpl();
			historyService.insertContsHis((String) paramMap.get("cid"));
			historyService.insertSaleStatHis(paramMap.get("cid"), Constants.CONTENT_SALE_STAT_ING, paramMap.get("adminId"), true);

			// 메일 보내기
			try {
				ContentMailModel contentMailModel = new ContentMailModel();
				SendMailModel mail;
				MailSendAgent msa;

				CommCode afterStatCode = CacheCommCode.getCommCodeByFullCode(Constants.CONTENT_SALE_STAT_ING);

				contentMailModel.setProdNm(productInfo.get("PRODNM")); // 상품명
				contentMailModel.setDevUserId(productInfo.get("DEVUSERID")); // 개발자 ID
				contentMailModel.setUpdDttm(DateUtil.toDisplayFormat(DateUtil.getSysDate(), "-")); // 업데이트 일자
				contentMailModel.setOldSaleStat(beforeStatCode.getCdNm()); // 이전 판매상태
				contentMailModel.setNewSaleStat(afterStatCode.getCdNm()); // 변경된 판매상태

				//2. SendMailModel Setting
				mail = new SendMailModel();
				mail.setTemplateId("/DEV/PD_004.html");
				mail.setRefAppId("ContentsManagementAction.ajaxUpdateStartSaleStat");
				mail.setSubject(LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 상품상태 변경 안내입니다."));
				mail.setRefDataId(paramMap.get("cid"));
				mail.setToAddr(productInfo.get("EMAILADDR"));
				//mail.setToName(mbrId);
				mail.setContentData(contentMailModel);
				mail.setFromAddr(conf.getString("omp.admin.punish.mail.from.addr"));
				mail.setFromName(conf.getString("omp.admin.punish.mail.from.name"));
				mail.setReturnAddr(conf.getString("omp.admin.punish.mail.return.addr"));
				msa = CommunicatorFactory.getMailSendAgent();
				//3. Send Mail
				long result = msa.requestMailSend(mail);

				if (log.isInfoEnabled()) {
					log.info("product SALE START MAIL SEND");
					log.info("{0}", new Object[] { mail });
					log.info("result={0}", new Object[] { result });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}
		return updCnt;
	}

	/**
	 * 판매상태가 판매중 이상인지 여부(판매중/판매금지/판매중지)
	 * 
	 * @param saleStat
	 * @return
	 */
	private boolean isOverSaleIngStat(String saleStat) {
		if (StringUtils.isBlank(saleStat)) {
			return false;
		}
		if (saleStat.equals(Constants.CONTENT_SALE_STAT_NA) || saleStat.equals(Constants.CONTENT_SALE_STAT_WAIT)) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.product.service.ContentsManagementService#registArm(java.lang.String)
	 */
	@Override
	public boolean registArm(String cid) {
		boolean isSuc = false;
		ConfigProperties conf = new ConfigProperties();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cid", cid);
		paramMap.put("drmYn", "Y");
		paramMap.put("drmSetOpt", Constants.CONTENT_DRM_INFINITE);
		paramMap.put("drmSetVal", conf.getString("omp.dev.arm.infinite.value"));

		try {
			// 상품 정보 가져오기
			Map<String, String> productInfo = (Map<String, String>) commonDAO.queryForObject("Product_Contents.getProductInfo",
					paramMap.get("cid"));

			// 판매상태가 판매금지/판매중지/판매중인지 확인
			CommCode beforeStatCode = CacheCommCode.getCommCodeByFullCode(productInfo.get("SALESTAT"));
			if (!Constants.CONTENT_SALE_STAT_RESTRIC.equals(productInfo.get("SALESTAT"))
					&& !Constants.CONTENT_SALE_STAT_STOP.equals(productInfo.get("SALESTAT"))
					&& !Constants.CONTENT_SALE_STAT_ING.equals(productInfo.get("SALESTAT"))) {
				throw new NoticeException("판매상태가 {0}상태이므로, 요청 처리가 실패했습니다.", new Object[] { beforeStatCode.getCdNm() });
			}

			// 검증상태가 검증대기 또는 검증중이 아닌지 확인
			CommCode beforeVerifyCode = CacheCommCode.getCommCodeByFullCode(productInfo.get("VERIFYPRGRYN"));
			if (Constants.CODE_VERIFY_REQ.equals(productInfo.get("VERIFYPRGRYN"))
					|| Constants.CODE_VERIFY_ING.equals(productInfo.get("VERIFYPRGRYN"))) {
				throw new NoticeException("검증상태가 {0}이므로, 요청 처리가 실패했습니다.", new Object[] { beforeVerifyCode.getCdNm() });
			}

			daoManager.startTransaction();
			int updCnt = commonDAO.update("Product_Contents.updateDpDrmYn", paramMap);
			if (updCnt == 0) {
				throw new NoticeException("DRM 사용여부를 업데이트하는 도중 에러가 발생하였습니다.");
			}
			updCnt = commonDAO.update("Product_Contents.updatePdDrmYn", paramMap);
			if (updCnt == 0) {
				throw new NoticeException("DRM 사용여부를 업데이트하는 도중 에러가 발생하였습니다.");
			}

			ARMManagerService armService = new ARMManagerServiceImpl();
			isSuc = armService.connectARMReqRegisterApplication(cid);
			if (isSuc == false) {
				throw new NoticeException("ARM 연동이 실패했습니다.");
			}

			daoManager.commitTransaction();

			// HISTORY
			ContentsHistoryService historyService = new ContentsHistoryServiceImpl();
			historyService.insertContsHis(cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}
		return isSuc;
	}

}
