package com.omp.dev.contents.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.dev.common.Constants;
import com.omp.dev.contents.model.ContentsVerify;
import com.omp.dev.contents.model.SubContentsVerify;
import com.omp.dev.contents.model.verify.ContentVerifyDetail;
import com.omp.dev.contents.model.verify.SubContentVerify;

/**
 * ContentsVerifyService Class : Contents Verify Present Service Class
 * @author Administrator
 *
 */
public class ContentsVerifyServiceImpl 
	extends AbstractService implements ContentsVerifyService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentsVerify> getContentsVerifyList(ContentsVerify ctVerify) throws ServiceException {
		List<ContentsVerify> resultList = null;
		try {
			
			/**
			 * 검증진행상태 조건중에 검증중이 있으면 Test완료, Test검증반려도
			 * 검증중으로 검색이 되어야 하기 때문에 조건값에 Setting 한다.
			 * */
			if (ctVerify.getSearchVerifyPrgrYn() != null) {
				String[] verifyPrgr = null;
				int length = 0;
				
				for (String verifyPrgrYn : ctVerify.getSearchVerifyPrgrYn()) {
					
					if (Constants.CODE_VERIFY_ING.equals(verifyPrgrYn)) {
						
						length = ctVerify.getSearchVerifyPrgrYn().length;
						
						verifyPrgr = new String[length + 2];
						
						for (int idx = 0; idx < length; idx++) {
							verifyPrgr[idx] = ctVerify.getSearchVerifyPrgrYn()[idx];
						}
						
						verifyPrgr[length] = Constants.CODE_VERIFY_TEST_END;
						verifyPrgr[length+1] = Constants.CODE_VERIFY_TEST_REJECT;
						
						ctVerify.setSearchVerifyPrgrYn(verifyPrgr);
						break;
					}
				}
			}
			
			resultList = this.commonDAO.queryForPageList("Verify.getCtVerifyList", ctVerify);
			
		} catch (Exception e) {
			throw new ServiceException(LocalizeMessage.getLocalizedMessage("java.content.verify.ContentsVerifyServiceImpl.ServiceException.msg.getContentsVerifyList"), e);
		}
		return resultList;
	}
	
	@Override
	public ContentVerifyDetail getContentVerifyDetailViewHead(ContentsVerify paramModel) throws ServiceException {
		ContentVerifyDetail result = null;
		try {
			result = (ContentVerifyDetail) this.commonDAO.queryForObject("Verify.getContentVerifyDetailHead", paramModel);
		} catch (Exception e) {
			throw new ServiceException(LocalizeMessage.getLocalizedMessage("java.content.verify.ContentsVerifyServiceImpl.ServiceException.msg.getContentVerifyDetail"), e);
		}
		return result;
	}

	@Override
	public ContentVerifyDetail getSubContentsVerifyDetailState(ContentsVerify paramModel) throws ServiceException {
		ContentVerifyDetail result = null;
			try {
				result = (ContentVerifyDetail) this.commonDAO.queryForObject("Verify.getSubContentsVerifyDetailState", paramModel);
			} catch (SQLException e) {
				throw new ServiceException(LocalizeMessage.getLocalizedMessage("java.content.verify.ContentsVerifyServiceImpl.ServiceException.msg.getContentVerifyDetail"), e);
			}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubContentsVerify> getContentVerifyDetailViewBody(ContentsVerify paramModel) throws ServiceException {
		List<SubContentsVerify> resultList = null;
		try {
			resultList = this.commonDAO.queryForList("Verify.getContentVerifyDetailBody", paramModel);
		} catch (Exception e) {
			throw new ServiceException(LocalizeMessage.getLocalizedMessage("java.content.verify.ContentsVerifyServiceImpl.ServiceException.msg.getContentVerifyDetail"), e);
		}
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SubContentVerify> getContentVerifyDetailAddFile(ContentsVerify paramModel) throws ServiceException {
		List<SubContentVerify> resultList = null;
		try {
			resultList = this.commonDAO.queryForList("Verify.getContentVerifyDetailAddFile", paramModel);
		} catch (Exception e) {
			throw new ServiceException(LocalizeMessage.getLocalizedMessage("java.content.verify.ContentsVerifyServiceImpl.ServiceException.msg.getContentVerifyDetailAddFile"), e);
		}
		return resultList;
	}

	@Override
	public boolean updateContentVerifyCancel(ContentsVerify contentsVerify) throws ServiceException {
		Map<String, String> condition = null;
		boolean returnFlag = false;
		int flagCnt = 0;
		
		/**
		 * 검증취소 로직
		 * 1. TBL_PD_CONTS(상품 Master Table) Update
		 *   => 검증대기 상태에서만 미검증으로 수정
		 * 2. TBL_CT_CONTS(검증 Master Table) Update
		 *   => 검증대기 상테에서만 검증취소로 수정
		 */
		
		try {
			
			daoManager.startTransaction();

			condition = new HashMap<String, String>();
			
			// TBL_PD_CONTS Table Update
			condition.put("cid", contentsVerify.getCid());
			condition.put("insBy", contentsVerify.getInsBy());
			flagCnt = this.commonDAO.update("Verify.updateContentVerifyCancel_TBL_PD_CONTS", condition);
			
			// TBL_CT_CONTS Table Update
			condition.put("verifyPrgrYn", Constants.CODE_VERIFY_CANCEL);
			condition.put("verifyWaitFlag", Constants.CODE_VERIFY_REQ);
			flagCnt = this.commonDAO.update("Verify.updateContentVerifyCancel_TBL_CT_CONTS", condition);
			
			daoManager.commitTransaction();
			
		} catch (Exception e) {
			throw new ServiceException(LocalizeMessage.getLocalizedMessage("java.content.verify.ContentsVerifyServiceImpl.ServiceException.msg.updateContentVerifyCancel"), e);
		} finally {
			daoManager.endTransaction();
		}
		
		if(flagCnt <= 0) { returnFlag = false; }
		else { returnFlag = true; }
		
		return returnFlag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentsVerify> getContentsVerifyHisList(ContentsVerify param) throws ServiceException {
		List<ContentsVerify> resultList = null;
		try {
			resultList = this.commonDAO.queryForPageList("Verify.getSubContentsVerifyHisList", param);
			
			if(resultList != null && resultList.size() > 0){
				for (ContentsVerify info : resultList) {
					
					info.setInsBy(param.getInsBy());
					
					info.setList(this.commonDAO.queryForList("Verify.getSubContents", info));
				}
			}
		} catch (Exception e) {
			throw new ServiceException(LocalizeMessage.getLocalizedMessage("java.content.verify.ContentsVerifyServiceImpl.ServiceException.msg.getContentsVerifyHisList"), e);
		}
		return resultList;
	}	
	
	
}
