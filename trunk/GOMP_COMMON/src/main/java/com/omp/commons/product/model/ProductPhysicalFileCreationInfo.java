package com.omp.commons.product.model;

import java.io.File;
import java.io.Serializable;
import java.util.Properties;

/**
 * 상품 물리파일 생성 정보 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class ProductPhysicalFileCreationInfo implements Serializable {

	/**
	 * Uploaded File Object
	 */
	private File uploadedFile;
	
	/**
	 * Job Type
	 */
	private int uploadJobType;
	
	/**
	 * File Limit Size
	 */
	private long limitSize;
	
	/**
	 * Source File Name
	 */
	private String sourceFileName;
	
	/**
	 * Properties
	 */
	private Properties confProps;
	
	/**
	 * Add Prefix Name
	 */
	private String addPrefixName;
	
	/**
	 * VM Type Name
	 */
	private String vmTypeName;
	
	/**
	 * Cid
	 */
	private String cid;
	
	/**
	 * SCID
	 */
	private String scid;
	
	
	public ProductPhysicalFileCreationInfo(){
		uploadedFile = null;
		uploadJobType = -1;
		limitSize = -1;
		sourceFileName = "";
		confProps = null;
	}
	

	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public int getUploadJobType() {
		return uploadJobType;
	}

	public void setUploadJobType(int uploadJobType) {
		this.uploadJobType = uploadJobType;
	}

	public long getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(long limitSize) {
		this.limitSize = limitSize;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public Properties getConfProps() {
		return confProps;
	}

	public void setConfProps(Properties confProps) {
		this.confProps = confProps;
	}

	public String getAddPrefixName() {
		return addPrefixName;
	}

	public void setAddPrefixName(String addPrefixName) {
		this.addPrefixName = addPrefixName;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public String getScid() {
		return scid;
	}


	public void setScid(String scid) {
		this.scid = scid;
	}


	public String getVmTypeName() {
		
		return vmTypeName.toLowerCase();
	}


	public void setVmTypeName(String vmTypeName) {
		this.vmTypeName = vmTypeName;
	}
	
	
}
