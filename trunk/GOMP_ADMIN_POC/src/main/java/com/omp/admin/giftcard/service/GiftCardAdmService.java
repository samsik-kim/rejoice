package com.omp.admin.giftcard.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.omp.admin.giftcard.model.GiftCard;
import com.omp.admin.giftcard.model.GiftCardSub;

public interface GiftCardAdmService {
	
	List<GiftCard> getPresentCashList(GiftCardSub sub);

	File getPresentCashListExcel(GiftCardSub sub);
	
	File getRandomNumberList(GiftCardSub sub);
	
	List<GiftCard> getPresentCashTarget(HashMap hm);       
	
	String issuePresentCash(HashMap hm);
	
	Integer getPresentCardInfoListCnt(String groupSeq);
	
	List<GiftCard> getSuccessPresentCashTarget(String groupSeq);
	
	List<GiftCard> getGiftCardInfoList(GiftCardSub sub);
	
	GiftCard getGiftCardDetailInfo(GiftCardSub sub);
	
	List<GiftCard>  getRegistedGiftCardList(GiftCardSub sub);
	
	void requestIssueGiftCard(HashMap hm);
	
	File getregistStatListExport(GiftCardSub sub);
	
}
