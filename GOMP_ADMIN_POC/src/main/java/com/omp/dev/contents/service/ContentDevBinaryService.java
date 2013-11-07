package com.omp.dev.contents.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.omp.dev.contents.model.ContentSprtPhone;
import com.omp.dev.contents.model.ContentUpdate;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.Provision;
import com.omp.dev.contents.model.SubContents;

public interface ContentDevBinaryService {

	/**
	 * 서브컨텐츠 갯수 조회
	 * 
	 * @param cid
	 * @return
	 */
	public String getSubContentsCount(String cid);
	
	/**
	 * 개발정보 수정
	 * 
	 * @param content
	 * @return
	 */
	public String modifyContentDevInfo(Contents content);
	
	/**
	 * 서브 컨텐츠 리스트 조회
	 * 
	 * @param cid
	 * @return
	 */
	public Map<String, Object> getSubContentsWithProvisionItemByCid(String cid);
	
	/**
	 * 서브 컨텐츠 조회
	 * 
	 * @param cid
	 * @return
	 */
	public SubContents getSubContentsWithProvisionItemByScid(String scid);
	
	/**
	 * 프로비전 Item List 조회
	 * 
	 * @param cid
	 * @return
	 */
	public List<Provision> getSubContentsProvisionItemList(String cid);
	
	/**
	 * 프로비전 Item 조회
	 * 
	 * @param cid
	 * @return
	 */
	public List<Provision> getSubContentProvisionItem(String scid);
	
	/**
	 * 서브컨텐츠 mapping 대상단말 리스트
	 * 
	 * @param cid
	 * @param scid
	 * @return
	 */
	public List<ContentSprtPhone> getContentTargetPhoneList(String cid, String scid);
	
	/**
	 * 대상 단말 조회
	 * 
	 * @param content
	 * @param subContent
	 * @param prop
	 * @return
	 */
	public List<ContentSprtPhone> getContentSprtPhoneSearchList(Contents content, SubContents subContent, Properties prop);
	
	/**
	 * 대상 단말 조회
	 * 
	 * @param vmType
	 * @param paramProvisionItem
	 * @param subContent
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	public List<ContentSprtPhone> getContentSprtPhoneSearchList(String vmType, String[] paramProvisionItem, SubContents subContent,  Map<String, Object> resultMap) throws SQLException;
	
	/**
	 * Binary Temp Upload & get Sprt Phone List
	 * 
	 * @param vmType
	 * @param subContent
	 * @param prop
	 * @return
	 */
	public Map<String, Object> ajaxReadManifestXML(String vmType, SubContents subContent, Properties prop);

	/**
	 * get Registered Content SprtPhone
	 * : 등록된 대상 단말 조회
	 * 
	 * @param subContent
	 * @return
	 */
	public int getRegisteredContentSprtPhone(SubContents subContent);
	
	/**
	 * 서브 컨텐츠 수정
	 * 
	 * @param content
	 * @param subContent
	 * @param prop
	 * @return
	 */
	public String updateSubContent(Contents content, SubContents subContent, Properties prop);
	
	/**
	 * 서브 컨텐츠 등록
	 * 
	 * @param content
	 * @param subContent
	 * @param prop
	 * @return
	 */
	public String insertSubContent(Contents content, SubContents subContent, Properties prop);
	
	/**
	 * 서브 컨텐츠 삭제
	 * 
	 * @param scid
	 */
	public void deleteSubContentsCount(String scid);
	
	/**
	 * 업데이트 이력 리스트 조회
	 * 
	 * @param cid
	 * @return
	 */
	public List<ContentUpdate> getContentUpdateList(String cid);
	
	/**
	 * 검증 이력 리스트 조회
	 * @param cid
	 * @return
	 */
	public List<Contents> getVerifyCommentList(String cid);
	
	/**
	 * 업데이트 이력 등록
	 * 
	 * @param contentUpdate
	 */
	public void insertUpdateHistory(ContentUpdate contentUpdate);
	
	/**
	 * 업데이트 이력 수정
	 * 
	 * @param contentUpdate
	 */
	public void updateUpdateHistory(ContentUpdate contentUpdate);
	
	/**
	 * 업데이트 이력 삭제
	 * 
	 * @param contentUpdate
	 */
	public void deleteUpdateHistory(ContentUpdate contentUpdate);
	
	/**
	 * 테스트 단말 조회
	 * @param mbrNo
	 * @return
	 */
	public String getOtaDeveloperPhoneList(String mbrNo);
}
