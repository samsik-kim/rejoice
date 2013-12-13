package com.omp.dev.test.action;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;

@SuppressWarnings("serial")
public class FileUploadTestAction extends BaseAction {
	@SuppressWarnings("unused")
	private static GLogger logger = new GLogger(FileUploadTestAction.class);
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	private List<String> uploadFileFullPath;

	public String fileUploadNView()
	{
		return SUCCESS;
	}
	
	public String fileUpload() throws Exception
	{
		return SUCCESS;
	}
	
	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileFullPath() {
		return uploadFileFullPath;
	}

	public void setUploadFileFullPath(List<String> uploadFileFullPath) {
		this.uploadFileFullPath = uploadFileFullPath;
	}
}