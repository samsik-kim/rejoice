package com.nmimo.data.message.info;

import java.io.Serializable;

/**
 * <pre>
 *
 * </pre>
 * @file ContentSubInfo.java
 * @since 2013. 7. 31.
 * @author Rejoice
 */
@SuppressWarnings("serial")
public class ContentSubInfo implements Serializable{

	private String contentId;
	private String contentType;
	private String fileName;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 */
	public ContentSubInfo() {
		super();
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param contentId
	 * @param contentType
	 * @param fileName
	 */
	public ContentSubInfo(String contentId, String contentType, String fileName) {
		super();
		this.contentId = contentId;
		this.contentType = contentType;
		this.fileName = fileName;
	}
	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}