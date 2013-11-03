/**
 * 
 */
package com.resttemplate.common.bean;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @comment : 
 * @date    : 2013. 11. 2.
 * @author  : Rejoice
 */
public class FileUploadBean {

	private MultipartFile [] files;
//	private List<MultipartFile> files;
	private List<MultipartFile> getFiles;
//	private Map<String, MultipartFile> mapFiles;
	
	/**
	 * @return the files
	 */
	public MultipartFile[] getFiles() {
		return files;
	}
	/**
	 * @param files the files to set
	 */
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	/**
	 * @return the getFiles
	 */
	public List<MultipartFile> getGetFiles() {
		return getFiles;
	}
	/**
	 * @param getFiles the getFiles to set
	 */
	public void setGetFiles(List<MultipartFile> getFiles) {
		this.getFiles = getFiles;
	}
}