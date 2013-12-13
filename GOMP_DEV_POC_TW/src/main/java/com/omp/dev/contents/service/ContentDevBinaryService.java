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

	public String getSubContentsCount(String cid);
	
	public String modifyContentDevInfo(Contents content);
	
	public Map<String, Object> getSubContentsWithProvisionItem(String cid);
	
	public List<Provision> getSubContentsProvisionItem(String cid);
	
	public List<ContentSprtPhone> getContentTargetPhoneList(String cid, String scid);
	
	public List<ContentSprtPhone> getContentSprtPhoneSearchList(Contents content, SubContents subContent, Properties prop);
	
	public List<ContentSprtPhone> getContentSprtPhoneSearchList(String vmType, String[] paramProvisionItem, SubContents subContent,  Map<String, Object> resultMap) throws SQLException;
	
	public Map<String, Object> ajaxReadManifestXML(String vmType, SubContents subContent, Properties prop);

	public int getRegisteredContentSprtPhone(SubContents subContent);
	
	public String updateSubContent(Contents content, SubContents subContent, Properties prop);
	
	public String insertSubContent(Contents content, SubContents subContent, Properties prop);
	
	public void deleteSubContentsCount(String scid);
	
	public List<ContentUpdate> getContentUpdateList(String cid);
	
	public List<Contents> getVerifyCommentList(String cid);
	
	public void insertUpdateHistory(ContentUpdate contentUpdate);
	
	public void updateUpdateHistory(ContentUpdate contentUpdate);
	
	public void deleteUpdateHistory(ContentUpdate contentUpdate);
	
	public String getOtaDeveloperPhoneList(String mbrNo);
}
