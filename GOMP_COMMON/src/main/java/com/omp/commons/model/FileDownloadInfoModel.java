package com.omp.commons.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.omp.commons.util.config.ConfigProperties;

/**
 * 파일 다운로드 정보를 가지는 모델
 * @author pat
 */
@SuppressWarnings("serial")
public class FileDownloadInfoModel
	implements Serializable {

	private File	target;
	private String	contentType;
	private String	saveAs;
	private int		bufferSize;
	private String 	filenameUrlEncodeCharst;
	
	public FileDownloadInfoModel() {
		this.bufferSize	= 1024;
	}

	public String getFilenameUrlEncodeCharst() {
		return filenameUrlEncodeCharst;
	}

	public void setFilenameUrlEncodeCharst(String filenameUrlEncodeCharst) {
		this.filenameUrlEncodeCharst = filenameUrlEncodeCharst;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public void setSaveAs(String saveAs) {
		this.saveAs = saveAs;
	}

	/**
	 * 지정되었으면 지정된 이름이, 지정되지 않았으면 다운로드 대상 파일명이 사용됩니다.
	 * @return
	 */
	public String getSaveAs()
		throws UnsupportedEncodingException {
		String	filename;
		String	charset;
		
		filename	= this.saveAs != null ? this.saveAs : this.target.getName();
		if (this.filenameUrlEncodeCharst != null) {
			filename	= URLEncoder.encode(filename, this.filenameUrlEncodeCharst);
		} else {
			charset		= new ConfigProperties().getString("omp.common.env.charset.file-download-name");
			if (charset != null) {
				filename	= new String(filename.getBytes(), charset);
			}
		}
//		filename	= URLEncoder.encode(filename, charset);
		
		return "filename=\"" + filename + "\"";
	}

	public void setTarget(File target) {
		this.target = target;
	}
	
	public long getLength() {
		return this.target.length();
	}
	
	public InputStream getInputStream()
		throws FileNotFoundException {
		return new FileInputStream(this.target);
	}
}