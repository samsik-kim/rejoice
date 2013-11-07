package com.omp.admin.giftcard.service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.omp.admin.giftcard.model.GiftCard;
import com.omp.admin.giftcard.model.GiftCardSub;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CipherAES;

public class GiftCardAdmServiceImpl extends AbstractService implements GiftCardAdmService {
	
	@Override
	public List<GiftCard> getPresentCashList(GiftCardSub sub) {
		List<GiftCard> presentCashList = null;
		try {
			this.setStep("GetPresentCashList");
			presentCashList = super.commonDAO.queryForPageList("GiftCard.getPresentCashList", sub);
		} catch (Exception e) {
			throw new ServiceException("운영자 캐쉬발급내역을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return presentCashList;
	}
	
	@Override
	public File getPresentCashListExcel(GiftCardSub sub) {
		
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		excelColumnDefineList.add(new ColumnInfoModel("rnum", "序號"));
		excelColumnDefineList.add(new ColumnInfoModel("issueReqDt", "發行日期"));
		excelColumnDefineList.add(new ColumnInfoModel("cardName", "發行標準"));
		excelColumnDefineList.add(new ColumnInfoModel("issueReqId", "發行人"));
		excelColumnDefineList.add(new ColumnInfoModel("cardCount", "發行對象"));
		excelColumnDefineList.add(new ColumnInfoModel("cardRealAmt", "發行幣額", ColumnInfoModel.COLUMN_TYPE_INTEGER));
		
		try {
			this.setStep("GetPresentCashList");
			return this.commonDAO.queryForExcel("GiftCard.getPresentCashList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("운영자캐쉬발급 excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	@Override
	public File getRandomNumberList(GiftCardSub sub) {
		
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		excelColumnDefineList.add(new ColumnInfoModel("serialNo", LocalizeMessage.getLocalizedMessage("난수번호"), ColumnInfoModel.COLUMN_TYPE_ENCRYRT));
				
		try {
			this.setStep("SelectRandomNumberList");
			return this.commonDAO.queryForExcel("GiftCard.selectRandomNumberList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("난수엑셀 가져오는 동안 에러가 발생 하였습니다.", e);
		}
	}
	
	@Override
	public List<GiftCard> getPresentCashTarget(HashMap hm) {
		List<GiftCard> presentCashTargetList = null;
		try {
			this.setStep("ResultPresentCashTargetUser");
			presentCashTargetList = super.commonDAO.queryForList("GiftCard.resultPresentCashTargetUser", hm);
		} catch (Exception e) {
			throw new ServiceException("선물 캐쉬 대상 유저을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return presentCashTargetList;
	}
	
	@Override
	public String issuePresentCash(HashMap hm) {
		
		String gcGroupSeq = getGroupSequence();
		hm.put("gcGroupSeq", gcGroupSeq);

		List userList = (List)hm.get("userList");

		if(userList.size() != 0){
			//INSERT INTO TBL_US_GIFT_CARD_INFO
			 try {
				this.setStep("InsertCashInfo");
				super.commonDAO.insert("GiftCard.insertCashInfo", hm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(int i = 0; i < userList.size(); i++){
			String mbrId = userList.get(i).toString();
			String cardNumber = getGiftCardNumber("D"); 
			String enCardNumber = null;
			try {
				enCardNumber =  CipherAES.encryption(cardNumber);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("mbrId : " + mbrId);

			hm.put("gcSeq", getGroupSequence());
			hm.put("cardNumber", cardNumber);
			hm.put("mbrId", mbrId);
			hm.put("enCardNumber", enCardNumber);

			//INSERT INTO TBL_US_GIFT_CARD_LIST
			try {
				this.setStep("InsertCashList");
				super.commonDAO.insert("GiftCard.insertCashList", hm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//INSERT INT TBL_OD_POINT_HST
			try {
				this.setStep("InsertPointHistory");
				super.commonDAO.insert("GiftCard.insertPointHistory", hm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return gcGroupSeq;
		
	}

	public String getGroupSequence(){
		String seq="";
		try {
			this.setStep("GetGroupSequence");
			seq = (String) super.commonDAO.queryForObject("GiftCard.getGroupSequence");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seq;
	}
	
	private String getGiftCardNumber(String prefix){
		String cardno = "";
		try {
			this.setStep("GetGiftCardNumber");
			cardno = (String)super.commonDAO.queryForObject("GiftCard.getGiftCardNumber", prefix);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cardno;
	}
	
	@Override
	public Integer getPresentCardInfoListCnt(String groupSeq){
		int cnt =0;
		
		try {
			this.setStep("GetSuccessPresentCashTargetCnt");
			cnt = (Integer)super.commonDAO.queryForObject("GiftCard.getSuccessPresentCashTargetCnt", groupSeq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public  List<GiftCard> getSuccessPresentCashTarget(String groupSeq){
		List<GiftCard> successPresentCashTargetList = null;
		try {
			this.setStep("GetSuccessPresentCashTarget");
			successPresentCashTargetList = super.commonDAO.queryForList("GiftCard.getSuccessPresentCashTarget", groupSeq);
		} catch (Exception e) {
			throw new ServiceException("성공대상 유저을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return successPresentCashTargetList;
	}
	
	@Override
	public  List<GiftCard> getGiftCardInfoList(GiftCardSub sub) {
		List<GiftCard> presentCashList = null;
		try {
			this.setStep("SelectGiftCardInfoList");
			presentCashList = super.commonDAO.queryForPageList("GiftCard.selectGiftCardInfoList", sub);
		} catch (Exception e) {
			throw new ServiceException("기프트카드내역을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return presentCashList;
	}
	
	public GiftCard getGiftCardDetailInfo(GiftCardSub sub) {
		GiftCard giftCard = null;
		try {
			this.setStep("SelectGiftCardInfoDetail");
			giftCard = (GiftCard) super.commonDAO.queryForObject("GiftCard.selectGiftCardInfoDetail", sub);
		} catch (SQLException e) {
			throw new ServiceException("기프트카드등록상세현황을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return giftCard;
	}
	
	public List<GiftCard>  getRegistedGiftCardList(GiftCardSub sub) {
		List<GiftCard> registedGiftCardList = null;
		try {
			this.setStep("SelectRegistedGiftCardList");
			registedGiftCardList = super.commonDAO.queryForPageList("GiftCard.selectRegistedGiftCardList", sub);
		} catch (Exception e) {
			throw new ServiceException("기프트카드등록현황을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return registedGiftCardList;
	}
	
	public void requestIssueGiftCard(HashMap hm){
		
		daoManager.startTransaction();
		
		String groupSeq = getGroupSequence();	// 기프트카드 시퀀스번호
		hm.put("groupSeq", groupSeq);

		try {
			this.setStep("InsertReqIssueGiftCard");
			this.commonDAO.insert("GiftCard.insertReqIssueGiftCard", hm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap resultMap = null;
		try {
			this.setStep("SelectGiftCardInfo");
			resultMap = (HashMap)this.commonDAO.queryForObject("GiftCard.selectGiftCardInfo", groupSeq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String cardAmt = resultMap.get("CARD_AMT").toString();
		String cardType = resultMap.get("CARD_TYPE").toString();
		
		int cardCount = Integer.parseInt(resultMap.get("CARD_COUNT").toString());
					
		String cardAmtIdx = "";
		if ("OR002801".equals(cardAmt)) cardAmtIdx= "2" ;
		else if ("OR002802".equals(cardAmt)) cardAmtIdx= "3" ;
		else if ("OR002803".equals(cardAmt)) cardAmtIdx= "4" ;
		else if ("OR002804".equals(cardAmt)) cardAmtIdx= "5" ;
		else if ("OR002805".equals(cardAmt)) cardAmtIdx= "6" ;
		else if ("OR002806".equals(cardAmt)) cardAmtIdx= "7" ;
					
		String cardPREFIX = "";
		if ("OR002901".equals(cardType)) cardPREFIX= 'A' + cardAmtIdx ;
		else if ("OR002902".equals(cardType)) cardPREFIX= 'B' + cardAmtIdx ;
		else if ("OR002903".equals(cardType)) cardPREFIX= 'C' + cardAmtIdx ;
					
		for(int i=0; i < cardCount; i++) {
			String cardNumber = getGiftCardNumber(cardPREFIX);
			String enCardNumber = null;
			try {
				enCardNumber = CipherAES.encryption(cardNumber);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			hm.put("enCardNumber", enCardNumber);
			//INSERT INTO TBL_US_GIFT_CARD_LIST
			try {
				this.setStep("InsertGiftCardList");
				this.commonDAO.insert("GiftCard.insertGiftCardList", hm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			this.setStep("UpdateGiftCardInfo");
			this.commonDAO.update("GiftCard.updateGiftCardInfo", hm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daoManager.commitTransaction();
		
	}
	
	@Override
	public File getregistStatListExport(GiftCardSub sub) {
		
		// 작성할 엑셀파일의 컬럼 정보
		List<ColumnInfoModel> excelColumnDefineList = new ArrayList<ColumnInfoModel>();
		
		excelColumnDefineList.add(new ColumnInfoModel("rnum", "序號"));
		excelColumnDefineList.add(new ColumnInfoModel("mbrNm", "姓名"));
		excelColumnDefineList.add(new ColumnInfoModel("mbrId", "ID"));
		excelColumnDefineList.add(new ColumnInfoModel("occrDt", "註冊日期"));
		
		try {
			this.setStep("SelectRegistedGiftCardList");
			return this.commonDAO.queryForExcel("GiftCard.selectRegistedGiftCardList", sub, excelColumnDefineList);
		} catch (Exception e) {
			throw new ServiceException("등록된 기프트카드내역 엑셀을 가지고 오는  동안 에러가 발생 하였습니다.", e);
		}
	}
}
