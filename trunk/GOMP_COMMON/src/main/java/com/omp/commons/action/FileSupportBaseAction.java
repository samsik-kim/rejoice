/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 31. | Description
 *
 */
package com.omp.commons.action;

import java.io.File;
import java.io.IOException;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;

/**
 * Common File Download Action
 * 
 * @author bcpark
 * @version 0.1
 */
@SuppressWarnings("serial")
public class FileSupportBaseAction extends BaseAction {

	protected String bnsType;
	protected String filePath;
	protected String fileName;

	private static final FileTypeMap MIME_TYPE_MAP = MimetypesFileTypeMap.getDefaultFileTypeMap();

	public String fileDown() {

		String prePath;
		if ("webcontent".equals(this.bnsType)) {
			prePath = ServletActionContext.getServletContext().getRealPath("/dlcontents");
		} else {
			prePath = conf.getString("omp." + bnsType);
			if (prePath == null || ! new File(prePath).exists()) {
				this.res.setStatus(404);
				logger.warn("pre Path Not Found");
				throw new NoticeException("File NOT FOUND");
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("File DOWN prePath : {0}", new Object[] {prePath});
		}
		if (StringUtils.isEmpty(prePath)) {
			this.res.setStatus(404);
			logger.warn("FILE NOT FOUND");
			throw new NoticeException("File NOT FOUND");
		}

		String fullPath = prePath + filePath;

		if (logger.isInfoEnabled()) {
			logger.info("File DOWN fullPath : {0}", new Object[] {fullPath});
		}

		File mFile = new File(fullPath);
		try {
			if (mFile.getCanonicalPath().indexOf(prePath) != 0) {
				this.res.setStatus(404);
				logger.warn("INVALID DOWNLOAD FILE PATH");
				throw new NoticeException("File NOT FOUND");
			}
		} catch (IOException e) { 
			throw new ServiceException("can't get real file path for download", e);
		}
		if (!mFile.canRead()) {
			this.res.setStatus(404);
			logger.warn("FILE NOT FOUND");
			throw new NoticeException("File NOT FOUND");
		}

		String contentType = "application/octet-stream";
		this.setDownloadFile(mFile, contentType, fileName);
		return SUCCESS;
	}

	public String imgView() {
		String prePath;
		if ("webcontent".equals(this.bnsType)) {
			prePath = ServletActionContext.getServletContext().getRealPath("/");
		} else {
			prePath = conf.getString("omp." + bnsType);
			if (prePath == null || ! new File(prePath).exists()) {
				this.res.setStatus(404);
				logger.warn("pre Path Not Found");
				throw new NoticeException("File NOT FOUND");
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("File DOWN prePath : {0}", new Object[] {prePath});
		}

		if (StringUtils.isEmpty(prePath)) {
			this.res.setStatus(404);
			logger.warn("FILE NOT FOUND");
			throw new NoticeException("File NOT FOUND");
		}

		String fullPath = prePath + filePath;

		File mFile = new File(fullPath);
		try {
			if (mFile.getCanonicalPath().indexOf(prePath) != 0) {
				this.res.setStatus(404);
				logger.warn("INVALID DOWNLOAD FILE PATH");
				throw new NoticeException("File NOT FOUND");
			}
		} catch (IOException e) { 
			throw new ServiceException("can't get real file path for download", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("File DOWN fullPath : {0}", new Object[] {fullPath});
		}

		if (!mFile.canRead()) {
			this.res.setStatus(404);
			logger.warn("FILE NOT FOUND");
			throw new NoticeException("File NOT FOUND");
		}

		String contentType = MIME_TYPE_MAP.getContentType(mFile);
		if (contentType == null || !contentType.startsWith("image")) {
			this.res.setStatus(404);
			logger.warn("FILE NOT IMAGE. SEND FILE NOT FOUND MESSAGE");
			throw new NoticeException("File NOT FOUND"); 
		}
		this.setDownloadFile(mFile, contentType);
		return SUCCESS;

	}

	public String fileDown(String fullPath, String name) {

		return SUCCESS;
	}

	public final String getBnsType() {
		return bnsType;
	}

	public final void setBnsType(String bnsType) {
		this.bnsType = bnsType;
	}

	public final String getFilePath() {
		return filePath;
	}

	public final void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public final String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
