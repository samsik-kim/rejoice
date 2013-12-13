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
package com.omp.admin.phonemapping.service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.phonemapping.model.PhoneMappingContents;
import com.omp.admin.phonemapping.model.PhoneMappingParam;
import com.omp.admin.phonemapping.model.PhoneRemMgr;
import com.omp.admin.phonemapping.model.PhoneRemScid;
import com.omp.admin.phonemapping.model.SearchParam;
import com.omp.admin.product.model.Contents;
import com.omp.commons.Constants;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.product.model.phone.ack2.PhoneSecondAck;
import com.omp.commons.product.model.phone.file.ContentInfo;
import com.omp.commons.product.model.phone.file.PhoneDomain;
import com.omp.commons.product.service.DistributeException;
import com.omp.commons.product.service.DownloadDistributeService;
import com.omp.commons.product.service.DownloadDistributeServiceImpl;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 폰 Remapping 서비스
 * 
 * @author Administrator
 * @version 0.1
 */
public class PhoneMappingServiceImpl extends AbstractService implements PhoneMappingService {
	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#executePhoneSecondAck(com.omp.commons.product.model.phone.ack2.PhoneSecondAck)
	 */
	@Override
	public void executePhoneSecondAck(PhoneSecondAck phoneSecondAck) {
		this.setStep("StartExecutePhoneSecondAck");
		try {
			this.setStep("StartTransaction");
			daoManager.startTransaction();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("txId", phoneSecondAck.header.result_info.getTx_id());

			// 해당 tx_id로 폰 매핑 정보를 TBL_PD_SPRT_PHONE_REM_MGR에서 가져온다.
			this.setStep("GetPhoneMappingInfo");
			PhoneRemMgr phoneRemMgr = (PhoneRemMgr) commonDAO.queryForObject("PhoneMapping.getPhoneRemMgr", paramMap);
			if (phoneRemMgr == null) {
				// DO NOTHING
			} else if (Constants.PHONE_MAPPING_REG.equals(phoneRemMgr.getMappingType())) {
				// 추가 매핑 인 경우
				if ("0".equals(phoneSecondAck.header.result_info.getCode())) {
					// 성공인 경우 
					paramMap.put("mappingStat", Constants.MAPPING_REG_SECOND_ACC_SUC);
					paramMap.put("insBy", "PMS");
					paramMap.put("targetPhoneModelCd", phoneRemMgr.getTargetPhoneModelCd());

					// 해당 tx_id로 폰매핑 상품 정보를 TBL_PD_SPRT_PHONE_REM_SCID에서 가져온다.
					this.setStep("GetScidMapByTxId");
					Map<String, Map<String, String>> scidMap = commonDAO.queryForMap("PhoneMapping.selectPhoneRemScid", paramMap, "SCID");
					int cnt = 0;

					// 가져온 상품정보를 가지고 폰 매핑 정보를 PD/CT/DP 쪽에 반영한다.
					this.setStep("ApplyPhoneMappingAddResult");
					for (String key : scidMap.keySet()) {
						Map<String, String> subMap = scidMap.get(key);
						// DP
						cnt = (Integer) commonDAO.queryForObject("PhoneMapping.selectDpSprtPhone", subMap);
						if (cnt == 0)
							commonDAO.insert("PhoneMapping.insertDpSprtPhone", subMap);
						// PD
						cnt = (Integer) commonDAO.queryForObject("PhoneMapping.selectPdSprtPhone", subMap);
						if (cnt == 0)
							commonDAO.insert("PhoneMapping.insertPdSprtPhone", subMap);
						// CT
						cnt = (Integer) commonDAO.queryForObject("PhoneMapping.selectCtSprtPhone", subMap);
						if (cnt == 0)
							commonDAO.insert("PhoneMapping.insertCtSprtPhone", subMap);
					}
				} else {
					// 실패인 경우 
					paramMap.put("mappingStat", Constants.MAPPING_REG_SECOND_ACC_FAIL);
				}

				// PHONE_REM_MGR
				this.setStep("UpdatePhoneMappingResult");
				commonDAO.update("PhoneMapping.updatePhoneRemMgr", paramMap);
			} else if (Constants.PHONE_MAPPING_DEL.equals(phoneRemMgr.getMappingType())) {
				// 매핑 삭제 인 경우 
				if ("0".equals(phoneSecondAck.header.result_info.getCode())) {
					this.setStep("ApplyPhoneMappingDelResult");
					// 성공인 경우 
					paramMap.put("mappingStat", Constants.MAPPING_DEL_SECOND_ACC_SUC);
					// DP
					commonDAO.delete("PhoneMapping.deleteDpSprtPhone", paramMap);
					// PD
					commonDAO.delete("PhoneMapping.deletePdSprtPhone", paramMap);
					// CT
					commonDAO.delete("PhoneMapping.deleteCtSprtPhone", paramMap);
				} else {
					// 실패인 경우 
					paramMap.put("mappingStat", Constants.MAPPING_DEL_SECOND_ACC_FAIL);
				}
				// PHONE_REM_MGR
				this.setStep("UpdatePhoneMappingResult");
				commonDAO.update("PhoneMapping.updatePhoneRemMgr", paramMap);
			}
			this.setStep("CommitTransaction");
			daoManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			this.setStep("EndTransaction");
			daoManager.endTransaction();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#getContentsExcelByTargetDevice(java.lang.String)
	 */
	@Override
	public File getContentsExcelByTargetDevice(String phone_model_cd) {
		ConfigProperties conf = new ConfigProperties();
		try {
			List<PhoneMappingContents> phoneMappingContentsList = commonDAO.queryForList("PhoneMapping.getContentsByTargetDevice",
					phone_model_cd);

			if (phoneMappingContentsList == null || phoneMappingContentsList.size() == 0) {
				throw new NoticeException("대상단말에 등록된 상품이 없습니다.");
			}
			File tempExcelDir = new File(conf.getString("omp.common.path.temp.excel-download"));
			tempExcelDir.mkdirs();

			File tempExcelFile = new File(conf.getString("omp.common.path.temp.excel-download"), DateUtil.getShortTimeStampString()
					+ ".xls");

			// JXL을 이용하여 Excel 파일 생성
			WritableWorkbook workbook = Workbook.createWorkbook(tempExcelFile);
			WritableSheet sheet = workbook.createSheet(phone_model_cd, 0);

			// ======================= width / height =============================
			// sheet width 조정
			sheet.setColumnView(0, 15);
			sheet.setColumnView(1, 40);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 80);

			int row = 0;
			// sheet height 조정
			sheet.setRowView(row++, 500);
			sheet.setRowView(row++, 400);

			// ======================= 포맷 =============================
			// title 용
			WritableFont titleFont = new WritableFont(WritableFont.TAHOMA, 11, WritableFont.BOLD);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 제목용
			WritableFont headerFont = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD);
			WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
			headerFormat.setAlignment(Alignment.CENTRE);
			headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			// headerFormat.setBackground(Colour.LIGHT_BLUE);

			// 본문용
			WritableFont tahoma10font = new WritableFont(WritableFont.TAHOMA, 10);
			WritableCellFormat tahoma10format = new WritableCellFormat(tahoma10font);
			tahoma10format.setBorder(Border.ALL, BorderLineStyle.THIN);
			tahoma10format.setVerticalAlignment(VerticalAlignment.CENTRE);
			tahoma10format.setWrap(true);
			tahoma10format.setShrinkToFit(true);

			// ======================= 내용 =============================
			// 타이틀
			sheet.mergeCells(0, 0, 4, 0);
			sheet.addCell(new Label(0, 0, phone_model_cd, titleFormat));

			// 제목
			sheet.addCell(new Label(0, 1, "AID", headerFormat));
			sheet.addCell(new Label(1, 1, "PROD NM", headerFormat));
			sheet.addCell(new Label(2, 1, "SCID", headerFormat));
			sheet.addCell(new Label(3, 1, "SALE STAT", headerFormat));
			sheet.addCell(new Label(4, 1, "SUPPORT PHONE", headerFormat));

			// 내용
			for (PhoneMappingContents conts : phoneMappingContentsList) {
				sheet.addCell(new Label(0, row, conts.getAid(), tahoma10format));
				sheet.addCell(new Label(1, row, conts.getProdNm(), tahoma10format));
				sheet.addCell(new Label(2, row, conts.getScid(), tahoma10format));
				sheet.addCell(new Label(3, row, conts.getSaleStatNm(), tahoma10format));
				sheet.addCell(new Label(4, row, conts.getPhoneModelCd(), tahoma10format));
				row++;
			}
			workbook.write();
			workbook.close();

			return tempExcelFile;
		} catch (NoticeException e) {
			throw e;
		} catch (Exception e) {
			throw new NoticeException("요청 처리가 실패했습니다. \n재 시도해 주시기 바랍니다.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#isValidDevice(java.lang.String)
	 */
	@Override
	public Map<String, String> isValidDevice(String searchDevice) {
		Map<String, String> validDeviceMap = null;
		try {
			validDeviceMap = (Map<String, String>) commonDAO.queryForObject("PhoneMapping.selectPhoneInfo", searchDevice);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}

		return validDeviceMap;
	}

	/**
	 * 해당 상품이 검증 승인된 MAX 버전이 존해하는지 검사한다.
	 * 
	 * @param phoneMappingParam
	 * @return
	 */
	private Map<String, String> selectValidProduct(PhoneMappingParam phoneMappingParam) {
		Map<String, String> resultMap = null;
		try {
			resultMap = (Map<String, String>) commonDAO.queryForObject("PhoneMapping.selectContentsInfo", phoneMappingParam);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return resultMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#deviceAddMapping(com.omp.admin.phonemapping.model.PhoneMappingParam)
	 */
	@Override
	public boolean deviceAddMapping(PhoneMappingParam param) throws DistributeException {
		boolean isfisrtAckSuc = true;

		// 해당 상품이 검증 승인된 MAX 버전이 있는지 검사 
		Map<String, String> productCtVerMap = selectValidProduct(param);
		if (productCtVerMap == null || StringUtils.isEmpty(productCtVerMap.get("VERIFYREQVER"))) {
			throw new NoticeException("검증완료된 상품정보가 없습니다. 입력하신 AID를 확인하세요.");
		}
		param.setCid(productCtVerMap.get("CID"));
		param.setVerifyReqVer(productCtVerMap.get("VERIFYREQVER"));

		// 기준 단말이 올바른 단말인지 검사
		Map<String, String> validDeviceMap = isValidDevice(param.getBaseDevice());
		if (validDeviceMap == null || StringUtils.isEmpty(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}이 존재하지 않습니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[기준단말]") });
		}
		if ("Y".equals(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}은 더이상 사용하지 않는 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[기준단말]") });
		}
		if (Constants.PHONE_INFO_NOT_REGIST.equals(validDeviceMap.get("SVC_CD"))) {
			throw new InvalidInputException("{0}은 미등록 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[기준단말]") });
		}

		// 추가 단말이 올바른 단말인지 검사
		validDeviceMap = isValidDevice(param.getAddDevice());
		if (validDeviceMap == null || StringUtils.isEmpty(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}이 존재하지 않습니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[추가단말]") });
		}
		if ("Y".equals(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}은 더이상 사용하지 않는 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[추가단말]") });
		}
		if (Constants.PHONE_INFO_NOT_REGIST.equals(validDeviceMap.get("SVC_CD"))) {
			throw new InvalidInputException("{0}은 미등록 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[추가단말]") });
		}

		try {

			// 기준 단말이 해당 상품에 매핑되어 있는지 검사
			ContentInfo baseContentInfo = (ContentInfo) commonDAO.queryForObject("PhoneMapping.getScidForMapping", param);
			if (baseContentInfo == null) {
				throw new NoticeException("기준단말이 상품에 매핑되지 않았습니다.");
			}

			// 추가 단말이 해당 상품에 이미 매핑되어 있는지 검사
			int isAleadyCnt = (Integer) commonDAO.queryForObject("PhoneMapping.getAlreadyMappingCnt", param);

			if (isAleadyCnt > 0) {
				throw new NoticeException("이미 추가단말에 매핑된 상품입니다.");
			}

			// 기준단말에 매핑된 SCID가 추가단말의 SPEC(VM_VER, LCD SIZE)을 수용가능한지 판단
			ContentInfo searchContentInfo = (ContentInfo) commonDAO.queryForObject("PhoneMapping.searchScidForMapping", param);
			if (searchContentInfo == null) {
				throw new NoticeException("OS 버전, LCD Size 적합하지 않은 단말(추가단말)입니다.");
			}

			List<ContentInfo> contentInfoList = new ArrayList<ContentInfo>();
			contentInfoList.add(searchContentInfo);

			// Download 서버 연동 ==========================
			PhoneDomain phoneDomain = new PhoneDomain();
			phoneDomain.header.request_info.setTx_id(makeTxId(param.getAddDevice()));
			phoneDomain.header.request_info.setModule(param.getAddDevice());
			phoneDomain.header.request_info.setType("C");
			phoneDomain.content_info = contentInfoList;
			DownloadDistributeService ddService = new DownloadDistributeServiceImpl();
			try {
				isfisrtAckSuc = ddService.ddPhoneMapping(phoneDomain);
			} catch (DistributeException de) {
				isfisrtAckSuc = false;
			}

			// 단말요청 정보를 TBL_PD_SPRT_PHONE_REM_MGR에 등록하기 위한 정보 수집
			PhoneRemMgr phoneRemMgr = new PhoneRemMgr();
			phoneRemMgr.setTxId(phoneDomain.header.request_info.getTx_id());
			phoneRemMgr.setMappingType(Constants.PHONE_MAPPING_REG);
			phoneRemMgr.setTargetPhoneModelCd(param.getAddDevice());
			phoneRemMgr.setStandardPhoneModelCd(param.getBaseDevice());
			phoneRemMgr.setInsBy(param.getAdminId());
			phoneRemMgr.setInsIp(param.getInsIp());

			if (isfisrtAckSuc) {
				phoneRemMgr.setMappingStat(Constants.MAPPING_REG_FIRST_ACC_SUC);
			} else {
				phoneRemMgr.setMappingStat(Constants.MAPPING_REG_FIRST_ACC_FAIL);
			}

			daoManager.startTransaction();

			commonDAO.insert("PhoneMapping.insertPhoneRemMgr", phoneRemMgr);

			Map<String, String> scidParamMap = new HashMap<String, String>();
			scidParamMap.put("txId", phoneDomain.header.request_info.getTx_id());
			scidParamMap.put("adminId", param.getAdminId());
			for (ContentInfo contentInfo : contentInfoList) {
				scidParamMap.put("cid", contentInfo.getCid());
				scidParamMap.put("scid", contentInfo.getScid());

				commonDAO.insert("PhoneMapping.insertPhoneRemScid", scidParamMap);
			}

			daoManager.commitTransaction();
		} catch (SQLException e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}

		return isfisrtAckSuc;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#deviceAddMappingExcel(com.omp.admin.phonemapping.model.PhoneMappingParam)
	 */
	@Override
	public Map<String, String> deviceAddMappingExcel(PhoneMappingParam param) throws DistributeException {
		boolean isfisrtAckSuc = true;
		Map<String, String> resultMap = new HashMap<String, String>();

		ConfigProperties conf = new ConfigProperties();
		File excelFile = new File(conf.getString("omp.common.path.temp.base"), param.getTmpExcel());
		if (excelFile == null || !excelFile.isFile()) {
			throw new DistributeException("업로드된 엑셀 파일을 찾을 수 없습니다.");
		}

		// 기준 단말이 올바른 단말인지 검사
		Map<String, String> validDeviceMap = isValidDevice(param.getBaseDevice());
		if (validDeviceMap == null || StringUtils.isEmpty(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}이 존재하지 않습니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[기준단말]") });
		}
		if ("Y".equals(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}은 더이상 사용하지 않는 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[기준단말]") });
		}
		if (Constants.PHONE_INFO_NOT_REGIST.equals(validDeviceMap.get("SVC_CD"))) {
			throw new InvalidInputException("{0}은 미등록 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[기준단말]") });
		}

		// 추가 단말이 올바른 단말인지 검사
		validDeviceMap = isValidDevice(param.getAddDevice());
		if (validDeviceMap == null || StringUtils.isEmpty(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}이 존재하지 않습니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[추가단말]") });
		}
		if ("Y".equals(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}은 더이상 사용하지 않는 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[추가단말]") });
		}
		if (Constants.PHONE_INFO_NOT_REGIST.equals(validDeviceMap.get("SVC_CD"))) {
			throw new InvalidInputException("{0}은 미등록 단말입니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[추가단말]") });
		}

		WorkbookSettings ws = new WorkbookSettings();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(excelFile, ws);
		} catch (Exception e) {
			throw new DistributeException("엑셀파일 파싱에 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}

		List<String> aidList = new ArrayList<String>();
		Sheet sheet = workbook.getSheet(0);
		int totalRow = sheet.getRows();
		int suc = 0;
		int fail = 0;
		boolean isFindAID = false;
		for (int row = 0; row < totalRow; row++) {
			Cell[] cells = sheet.getRow(row);
			String aid = cells[0].getContents();
			if (isFindAID == false) {
				if ("AID".equalsIgnoreCase(aid)) {
					isFindAID = true;
				}
			} else {
				// 값이 비었는지 확인
				if (aid != null && StringUtils.isNotBlank(aid)) {
					// 중복 값인지 확인
					if (aidList.contains(aid)) {
						// 값은 있지만, 중복인 경우 fail count 증가
						fail++;
					} else {
						aidList.add(aid);
					}
				}
			}

			// 엑셀 로우수 Limit 값보다 큰지 체크
			if (aidList.size() > conf.getInt("omp.admin.phonemapping.excel.row.count", 100)) {
				break;
			}
		}

		int total = aidList.size();
		if (total > conf.getInt("omp.admin.phonemapping.excel.row.count", 100)) {
			throw new DistributeException("컨텐츠는 {0}개 이하만 매핑가능합니다.",
					new Object[] { conf.getString("omp.admin.phonemapping.excel.row.count") });
		}

		List<ContentInfo> contentInfoList = new ArrayList<ContentInfo>();

		try {
			for (String aid : aidList) {
				param.setAid(aid);

				// CT 쪽에 검증완료된 버전이 있는지 확인
				Map<String, String> productCtVerMap = selectValidProduct(param);
				if (productCtVerMap == null || StringUtils.isEmpty(productCtVerMap.get("VERIFYREQVER"))) {
					fail++;
					continue;
				}
				param.setCid(productCtVerMap.get("CID"));
				param.setVerifyReqVer(productCtVerMap.get("VERIFYREQVER"));

				// 이미 매핑 되어 있는지 확인
				int isAleadyCnt = (Integer) commonDAO.queryForObject("PhoneMapping.getAlreadyMappingCnt", param);
				if (isAleadyCnt > 0) {
					fail++;
					continue;
				}

				// 기준 단말이 해당 상품에 매핑되어 있는지 검사
				ContentInfo baseContentInfo = (ContentInfo) commonDAO.queryForObject("PhoneMapping.getScidForMapping", param);
				if (baseContentInfo == null) {
					fail++;
					continue;
				}

				// 기준단말에 매핑된 SCID가 추가단말의 SPEC(VM_VER, LCD SIZE)을 수용가능한지 판단
				ContentInfo searchContentInfo = (ContentInfo) commonDAO.queryForObject("PhoneMapping.searchScidForMapping", param);
				if (searchContentInfo == null) {
					fail++;
					continue;
				}

				contentInfoList.add(searchContentInfo);
				suc++;
			}

			if (suc == 0) {
				throw new DistributeException("단말 매핑할 유효한 상품이 없습니다.");
			}

			// Download 서버 연동 ==========================
			PhoneDomain phoneDomain = new PhoneDomain();
			phoneDomain.header.request_info.setTx_id(makeTxId(param.getAddDevice()));
			phoneDomain.header.request_info.setModule(param.getAddDevice());
			phoneDomain.header.request_info.setType("C");
			phoneDomain.content_info = contentInfoList;
			DownloadDistributeService ddService = new DownloadDistributeServiceImpl();
			try {
				isfisrtAckSuc = ddService.ddPhoneMapping(phoneDomain);
			} catch (DistributeException de) {
				isfisrtAckSuc = false;
			}
			// =============================================

			PhoneRemMgr phoneRemMgr = new PhoneRemMgr();
			phoneRemMgr.setTxId(phoneDomain.header.request_info.getTx_id());
			phoneRemMgr.setMappingType(Constants.PHONE_MAPPING_REG);
			phoneRemMgr.setTargetPhoneModelCd(param.getAddDevice());
			phoneRemMgr.setStandardPhoneModelCd(param.getBaseDevice());
			phoneRemMgr.setInsBy(param.getAdminId());
			phoneRemMgr.setInsIp(param.getInsIp());

			if (isfisrtAckSuc) {
				phoneRemMgr.setMappingStat(Constants.MAPPING_REG_FIRST_ACC_SUC);
			} else {
				phoneRemMgr.setMappingStat(Constants.MAPPING_REG_FIRST_ACC_FAIL);
			}

			daoManager.startTransaction();

			commonDAO.insert("PhoneMapping.insertPhoneRemMgr", phoneRemMgr);

			Map<String, String> scidParamMap = new HashMap<String, String>();
			scidParamMap.put("txId", phoneDomain.header.request_info.getTx_id());
			scidParamMap.put("adminId", param.getAdminId());
			for (ContentInfo contentInfo : contentInfoList) {
				scidParamMap.put("cid", contentInfo.getCid());
				scidParamMap.put("scid", contentInfo.getScid());
				commonDAO.insert("PhoneMapping.insertPhoneRemScid", scidParamMap);
			}

			daoManager.commitTransaction();
		} catch (SQLException e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}

		resultMap.put("isfisrtAckSuc", "" + isfisrtAckSuc);
		resultMap.put("total", "" + total);
		resultMap.put("suc", "" + suc);
		resultMap.put("fail", "" + fail);
		return resultMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#deviceDelMapping(com.omp.admin.phonemapping.model.PhoneMappingParam)
	 */
	@Override
	public boolean deviceDelMapping(PhoneMappingParam param) throws DistributeException {
		boolean isfisrtAckSuc = true;

		Map<String, String> productCtVerMap = selectValidProduct(param);
		if (productCtVerMap == null || StringUtils.isEmpty(productCtVerMap.get("VERIFYREQVER"))) {
			throw new NoticeException("검증완료된 상품정보가 없습니다. 입력하신 AID를 확인하세요.");
		}
		param.setCid(productCtVerMap.get("CID"));
		param.setVerifyReqVer(productCtVerMap.get("VERIFYREQVER"));

		// 단말 삭제시는 삭제 여부 및 서비스구분코드를 상관하지 않는다.
		// 해당 단말이 실제 존재하는지만 살펴본다.
		Map<String, String> validDeviceMap = isValidDevice(param.getDelDevice());
		if (validDeviceMap == null || StringUtils.isEmpty(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}이 존재하지 않습니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[삭제단말]") });
		}

		try {
			// 추가 단말이 존재하는지 확인한다.
			int isAleadyCnt = (Integer) commonDAO.queryForObject("PhoneMapping.getAlreadyExistCnt", param);

			if (isAleadyCnt == 0) {
				throw new NoticeException("해당 상품에 매핑되지 않은 단말 입니다.");
			}

			// 쿼리를 재활용 하기 위해 delDevice를 baseDevice로 넣는다.
			param.setBaseDevice(param.getDelDevice());
			List<ContentInfo> contentInfoList = commonDAO.queryForList("PhoneMapping.getScidForMapping", param);

			// Download 서버 연동 ==========================
			PhoneDomain phoneDomain = new PhoneDomain();
			phoneDomain.header.request_info.setTx_id(makeTxId(param.getDelDevice()));
			phoneDomain.header.request_info.setModule(param.getDelDevice());
			phoneDomain.header.request_info.setType("D");

			phoneDomain.content_info = contentInfoList;

			DownloadDistributeService ddService = new DownloadDistributeServiceImpl();

			try {
				isfisrtAckSuc = ddService.ddPhoneMapping(phoneDomain);
			} catch (DistributeException de) {
				isfisrtAckSuc = false;
			}
			// =============================================			

			PhoneRemMgr phoneRemMgr = new PhoneRemMgr();
			phoneRemMgr.setTxId(phoneDomain.header.request_info.getTx_id());
			phoneRemMgr.setMappingType(Constants.PHONE_MAPPING_DEL);
			phoneRemMgr.setTargetPhoneModelCd(param.getDelDevice());
			phoneRemMgr.setStandardPhoneModelCd(null);
			phoneRemMgr.setInsBy(param.getAdminId());
			phoneRemMgr.setInsIp(param.getInsIp());

			if (isfisrtAckSuc) {
				phoneRemMgr.setMappingStat(Constants.MAPPING_DEL_FIRST_ACC_SUC);
			} else {
				phoneRemMgr.setMappingStat(Constants.MAPPING_DEL_FIRST_ACC_FAIL);
			}

			daoManager.startTransaction();

			commonDAO.insert("PhoneMapping.insertPhoneRemMgr", phoneRemMgr);

			Map<String, String> scidParamMap = new HashMap<String, String>();
			scidParamMap.put("txId", phoneDomain.header.request_info.getTx_id());
			scidParamMap.put("adminId", param.getAdminId());
			for (ContentInfo contentInfo : contentInfoList) {
				scidParamMap.put("cid", contentInfo.getCid());
				scidParamMap.put("scid", contentInfo.getScid());

				commonDAO.insert("PhoneMapping.insertPhoneRemScid", scidParamMap);
			}

			daoManager.commitTransaction();
		} catch (SQLException e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}

		return isfisrtAckSuc;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#deviceDelMappingExcel(com.omp.admin.phonemapping.model.PhoneMappingParam)
	 */
	@Override
	public Map<String, String> deviceDelMappingExcel(PhoneMappingParam param) throws DistributeException {
		boolean isfisrtAckSuc = true;
		Map<String, String> resultMap = new HashMap<String, String>();

		ConfigProperties conf = new ConfigProperties();
		File excelFile = new File(conf.getString("omp.common.path.temp.base"), param.getTmpExcel());
		if (excelFile == null || !excelFile.isFile()) {
			throw new DistributeException("업로드된 엑셀 파일을 찾을 수 없습니다.");
		}

		// 단말 삭제시는 삭제 여부 및 서비스구분코드를 상관하지 않는다.
		// 해당 단말이 실제 존재하는지만 살펴본다.
		Map<String, String> validDeviceMap = isValidDevice(param.getDelDevice());
		if (validDeviceMap == null || StringUtils.isEmpty(validDeviceMap.get("DEL_YN"))) {
			throw new InvalidInputException("{0}이 존재하지 않습니다.", new Object[] { LocalizeMessage.getLocalizedMessage("[삭제단말]") });
		}

		WorkbookSettings ws = new WorkbookSettings();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(excelFile, ws);
		} catch (Exception e) {
			throw new DistributeException("엑셀파일 파싱에 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}

		List<String> aidList = new ArrayList<String>();
		Sheet sheet = workbook.getSheet(0);
		int totalRow = sheet.getRows();
		int suc = 0;
		int fail = 0;
		boolean isFindAID = false;
		for (int row = 0; row < totalRow; row++) {
			Cell[] cells = sheet.getRow(row);
			String aid = cells[0].getContents();
			if (isFindAID == false) {
				if ("AID".equalsIgnoreCase(aid)) {
					isFindAID = true;
				}
			} else {
				// 값이 비었는지 확인
				if (aid != null && StringUtils.isNotBlank(aid)) {
					// 중복 값인지 확인
					if (aidList.contains(aid)) {
						// 값은 있지만, 중복인 경우 fail count 증가
						fail++;
					} else {
						aidList.add(aid);
					}
				}
			}

			// 엑셀 로우수 Limit 값보다 큰지 체크
			if (aidList.size() > conf.getInt("omp.admin.phonemapping.excel.row.count", 100)) {
				break;
			}
		}

		int total = aidList.size();
		if (total > conf.getInt("omp.admin.phonemapping.excel.row.count", 100)) {
			throw new DistributeException("컨텐츠는 {0}개 이하만 매핑가능합니다.",
					new Object[] { conf.getString("omp.admin.phonemapping.excel.row.count") });
		}

		List<ContentInfo> contentInfoList = new ArrayList<ContentInfo>();

		try {
			for (String aid : aidList) {
				param.setAid(aid);

				// CT 쪽에 검증완료된 버전이 있는지 확인
				Map<String, String> productCtVerMap = selectValidProduct(param);
				if (productCtVerMap == null || StringUtils.isEmpty(productCtVerMap.get("VERIFYREQVER"))) {
					fail++;
					continue;
				}
				param.setCid(productCtVerMap.get("CID"));
				param.setVerifyReqVer(productCtVerMap.get("VERIFYREQVER"));

				// 추가 단말이 존재하는지 확인한다.
				int isAleadyCnt = (Integer) commonDAO.queryForObject("PhoneMapping.getAlreadyExistCnt", param);

				if (isAleadyCnt == 0) {
					fail++;
					continue;
				}

				// 쿼리를 재활용 하기 위해 delDevice를 baseDevice로 넣는다.
				param.setBaseDevice(param.getDelDevice());
				ContentInfo contentInfo = (ContentInfo) commonDAO.queryForObject("PhoneMapping.getScidForMapping", param);

				if (contentInfo == null) {
					fail++;
					continue;
				}
				contentInfoList.add(contentInfo);
				suc++;
			}

			if (suc == 0) {
				throw new DistributeException("단말 매핑할 유효한 상품이 없습니다.");
			}

			// Download 서버 연동 ==========================
			PhoneDomain phoneDomain = new PhoneDomain();
			phoneDomain.header.request_info.setTx_id(makeTxId(param.getDelDevice()));
			phoneDomain.header.request_info.setModule(param.getDelDevice());
			phoneDomain.header.request_info.setType("D");

			phoneDomain.content_info = contentInfoList;

			DownloadDistributeService ddService = new DownloadDistributeServiceImpl();

			try {
				isfisrtAckSuc = ddService.ddPhoneMapping(phoneDomain);
			} catch (DistributeException de) {
				isfisrtAckSuc = false;
			}
			// =============================================			

			PhoneRemMgr phoneRemMgr = new PhoneRemMgr();
			phoneRemMgr.setTxId(phoneDomain.header.request_info.getTx_id());
			phoneRemMgr.setMappingType(Constants.PHONE_MAPPING_DEL);
			phoneRemMgr.setTargetPhoneModelCd(param.getDelDevice());
			phoneRemMgr.setStandardPhoneModelCd(null);
			phoneRemMgr.setInsBy(param.getAdminId());
			phoneRemMgr.setInsIp(param.getInsIp());

			if (isfisrtAckSuc) {
				phoneRemMgr.setMappingStat(Constants.MAPPING_DEL_FIRST_ACC_SUC);
			} else {
				phoneRemMgr.setMappingStat(Constants.MAPPING_DEL_FIRST_ACC_FAIL);
			}

			daoManager.startTransaction();

			commonDAO.insert("PhoneMapping.insertPhoneRemMgr", phoneRemMgr);

			Map<String, String> scidParamMap = new HashMap<String, String>();
			scidParamMap.put("txId", phoneDomain.header.request_info.getTx_id());
			scidParamMap.put("adminId", param.getAdminId());
			for (ContentInfo contentInfo : contentInfoList) {
				scidParamMap.put("cid", contentInfo.getCid());
				scidParamMap.put("scid", contentInfo.getScid());

				commonDAO.insert("PhoneMapping.insertPhoneRemScid", scidParamMap);
			}

			daoManager.commitTransaction();
		} catch (SQLException e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		} finally {
			daoManager.endTransaction();
		}

		resultMap.put("isfisrtAckSuc", "" + isfisrtAckSuc);
		resultMap.put("total", "" + total);
		resultMap.put("suc", "" + suc);
		resultMap.put("fail", "" + fail);
		return resultMap;
	}

	/**
	 * @param targetDevice
	 * @return
	 * @throws DistributeException
	 */
	private String makeTxId(String targetDevice) throws DistributeException {
		return DateUtil.getShortTimeStampString() + targetDevice;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#selectPhoneRemMgrList(com.omp.admin.phonemapping.model.SearchParam)
	 */
	@Override
	public List<PhoneRemMgr> selectPhoneRemMgrList(SearchParam searchParam) {
		List<PhoneRemMgr> phoneRemMgrList = null;
		try {
			phoneRemMgrList = commonDAO.queryForPageList("PhoneMapping.selectPhoneRemMgrList", searchParam);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return phoneRemMgrList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#getPhoneRemMgr(java.lang.String)
	 */
	@Override
	public PhoneRemMgr getPhoneRemMgr(String txId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("txId", txId);
		PhoneRemMgr phoneRemMgr = null;
		try {
			phoneRemMgr = (PhoneRemMgr) commonDAO.queryForObject("PhoneMapping.getPhoneRemMgr", paramMap);
		} catch (SQLException e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return phoneRemMgr;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#selectPhoneRemScidList(com.omp.admin.phonemapping.model.SearchParam)
	 */
	@Override
	public List<PhoneRemScid> selectPhoneRemScidList(SearchParam searchParam) {
		List<PhoneRemScid> phoneRemScidList = null;
		try {
			phoneRemScidList = commonDAO.queryForPageList("PhoneMapping.selectPhoneRemScidList", searchParam);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		return phoneRemScidList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#getDetailExcel(com.omp.admin.phonemapping.model.SearchParam)
	 */
	@Override
	public File getDetailExcel(SearchParam searchParam) {
		PhoneRemMgr phoneRemMgr = getPhoneRemMgr(searchParam.getTxId());
		String notice = phoneRemMgr.toString();

		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		excelColumnDefineList.add(new ColumnInfoModel("prodNm", "PROD_NM"));
		excelColumnDefineList.add(new ColumnInfoModel("aid", "AID"));
		excelColumnDefineList.add(new ColumnInfoModel("ctgrNm2", "CATEGORY"));
		excelColumnDefineList.add(new ColumnInfoModel("mbrId", "MEMBER_ID"));
		excelColumnDefineList.add(new ColumnInfoModel("saleStatNm", "SALE_STAT"));

		try {
			return this.commonDAO.queryForExcel("PhoneMapping.selectPhoneRemScidList", searchParam, excelColumnDefineList, notice);
		} catch (Exception e) {
			throw new NoticeException("대상단말에 등록된 상품이 없거나, 요청 처리가 실패했습니다. \n재 시도해 주시기 바랍니다.", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.omp.admin.phonemapping.service.PhoneMappingService#getDPContentInfoList()
	 */
	@SuppressWarnings("unchecked")
	public List<Contents> getDPContentInfoList(SearchParam searchParam) {
		List<Contents> resultList = new ArrayList<Contents>();
		
		try {
			resultList = commonDAO.queryForPageList("PhoneMapping.getDPContentInfoList", searchParam);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}
		
		return resultList;
	}

}
