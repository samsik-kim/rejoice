package com.nmimo.common.info;

import org.springframework.web.multipart.MultipartFile;

public class UploadCommandInfo {

	private MultipartFile file1;
	private MultipartFile file2;
	/**
	 * @return the file1
	 */
	public MultipartFile getFile1() {
		return file1;
	}
	/**
	 * @param file1 the file1 to set
	 */
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	/**
	 * @return the file2
	 */
	public MultipartFile getFile2() {
		return file2;
	}
	/**
	 * @param file2 the file2 to set
	 */
	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
}
