package com.omp.admin.community.qna.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.omp.admin.community.qna.model.QnA;
import com.omp.admin.product.model.ContentsSub;
import com.omp.commons.paging.PageNavigator;
import com.omp.commons.paging.PageResultList;


public interface QnaService 
{
	public void saveCtgrQnA(String highCtgr,String[] ctgrNm,String[] ctgrCd,String[] ctgrDel,String id,String qnaType);
	public void deleteCtgrQnA(String ctgrCd);
	public void deleteSubCtgrQnA(String ctgrCd);
	public void modifyCtgr(QnA qna);
	public List<QnA> searchQnA(QnA qna,String qnaCd);
	public List searchQuestion(QnA qna);
	public List searchAnswer(QnA qna);
	File getExcelSCQnaList(QnA qna,String qnaCd);
	public void insertAnswer(QnA qna);
	public void removeQnA(int queNo);
	public List searchCategoryName(String ctgrCd);
	public List selectCategoryNameAll(String ctgrCd);
	
}
