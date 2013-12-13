package com.omp.admin.community.qna.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.omp.admin.community.qna.model.QnA;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.service.AbstractService;

public class QnaServiceImpl extends AbstractService implements QnaService
{
	private static final List<ColumnInfoModel> COLINFO_SC_QNA_LIST	= new ArrayList<ColumnInfoModel>();
	static {
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("rnum", "序號"));
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("queId", "諮詢者"));
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("highCtgrNm", "大類別"));
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("qnaClsNm2", "小類別"));
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("queTitle", "標題"));
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("regDt", "答覆中"));
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("prcCompDt", "答覆完畢"));
		COLINFO_SC_QNA_LIST.add(new ColumnInfoModel("prcStatNm", "狀態"));
	}
	
	public File getExcelSCQnaList(QnA qna,String qnaCd) {
		try {
			List<ColumnInfoModel> COLINFO_DEV_QNA_LIST	= new ArrayList<ColumnInfoModel>();
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("rnum", "序號"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("queId", "諮詢者"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("highCtgrNm", "諮詢者"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("queTitle", "標題"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("regDt", "答覆中"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("prcCompDt", "答覆完畢"));
			COLINFO_DEV_QNA_LIST.add(new ColumnInfoModel("prcStatNm", "狀態"));
			qna.setQnaCd(qnaCd);
			if(qnaCd.equals("CM000602")){
				return this.commonDAO.queryForExcel("qna.selectQnA", qna, COLINFO_DEV_QNA_LIST);
			}else{
				return this.commonDAO.queryForExcel("qna.selectQnA", qna, COLINFO_SC_QNA_LIST);
			}
		} catch (Exception e) {
			throw new ServiceException("excel파일 작성중 에러가 발생 하였습니다.", e);
		}
	}
	
	/**
	 * Q&A Answer Save.
	 * 
	 */
	public void insertAnswer(QnA qna) {
		try{
			daoManager.startTransaction();
			this.commonDAO.insert("qna.insertAnswer", qna);
			this.commonDAO.update("qna.prcStatCdQnA", qna.getQueNo());
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
	
	/**
	 * Q&A Search.
	 * @return List
	 */
	public List<QnA> searchQnA(QnA qna,String qnaCd) {
		List<QnA> qnaList = null;
		try{
			qna.setQnaCd(qnaCd);
			qnaList = this.commonDAO.queryForPageList("qna.selectQnA", qna);
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("searchQnA Error.", e);
		}
		return qnaList;
	}
	
    
    /**
     * Q&A Delete.
	 */
    public void removeQnA(int queNo) {
		try{
			daoManager.startTransaction();
			this.commonDAO.update("qna.deleteQnA", queNo);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
    }
    
    
    /**
     * Question Search.
	 * @return List
	 */
	public List<QnA> searchQuestion(QnA qna) {
		List<QnA> qnaList = null;
		try{
			qnaList=this.commonDAO.queryForList("qna.selectQuestion", qna);
			return qnaList;
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("searchQuestion Error.", e);
		}
	}
	
	
	/**
     * Answer Search.
	 * @return List
	 */
	public List searchAnswer(QnA qna) {
		try{
			return this.commonDAO.queryForList("qna.selectAnswers", qna);
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("searchAnswer Error.", e);
		}
	}

	/**
	 *  Category Save.
	 */
	public void saveCtgrQnA(String highCtgr,String[] ctgrNm,String[] ctgrCd,String[] ctgrDel,String id,String qnaType){
		QnA qna = new QnA();
		int max = 0;
		if(ctgrNm.length>ctgrCd.length){
			max = ctgrCd.length;
		}else{
			max = ctgrNm.length;
		}
		try{
			daoManager.startTransaction();
			if(ctgrDel.length!=0){
				for(int i=1;i<ctgrDel.length;i++){
					//deleteCtgrQnA(ctgrDel[i]);
					this.commonDAO.delete("qna.deleteCtgr", ctgrDel[i]);
					//this.commonDAO.delete("qna.deleteSubCtgr", ctgrDel[i]);
				}
			}
			if(ctgrNm.length!=0||ctgrCd.length!=0){
				for(int i=1;i<max;i++){
					qna.setCtgrNm(ctgrNm[i]);
					qna.setCtgrCd(ctgrCd[i]);
					qna.setDisplayOd(i+"");
					qna.setQueId(id);
					qna.setQnaClsCd(qnaType);
					if(highCtgr.equals("")){
						qna.setHighCtgr("");
						qna.setCtgrLevel("1");
					}else{
						qna.setHighCtgr(highCtgr);
						qna.setCtgrLevel("2");
					}
					this.commonDAO.insert("qna.saveMrgCtgr", qna);
				}
			}
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
	
	/**
	 * Category Delete.
	 */
	public void deleteCtgrQnA(String ctgrCd){
		try{
			this.commonDAO.delete("qna.deleteCtgr", ctgrCd);
			this.commonDAO.delete("qna.deleteSubCtgr", ctgrCd);
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("deleteCtgrQnA Error.", e);
		}
	}
	
	public void deleteSubCtgrQnA(String ctgrCd){
		try{
			daoManager.startTransaction();
			this.commonDAO.delete("qna.deleteSubCtgr", ctgrCd);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
	
	public void modifyCtgr(QnA qna){
		try{
			daoManager.startTransaction();
			this.commonDAO.update("qna.modifyCtgr", qna);
			daoManager.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.", e);
		}finally{
			daoManager.endTransaction();
		}
	}
	
	/**
	 * Category Search.
	 * @return List
	 */
	public List searchCategoryName(String ctgrCd) {
		try{
			return this.commonDAO.queryForList("qna.selectCategoryName", ctgrCd);
				
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("searchCategoryName Error.", e);
		}
	}
	
	/**
	 * Category All Search.
	 * @return List
	 */
	public List selectCategoryNameAll(String ctgrCd) {
		try{
			return this.commonDAO.queryForList("qna.selectCategoryNameAll", ctgrCd);
				
		}catch(Exception e){
			e.printStackTrace();
            throw new ServiceException("selectCategoryNameAll Error.", e);
		}
	}
}
