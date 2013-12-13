package com.omp.dev.phonemodel.action;

import java.io.File;
import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.dev.phonemodel.service.PhoneModelDownService;

@SuppressWarnings("serial")
public class PhoneModelDownAction extends BaseAction{
	private String bnsType;
	private String filePath;
	private String fileName;
	private String modelCd;
	private PhoneModelDownService service = null;
	private static final FileTypeMap MIME_TYPE_MAP = MimetypesFileTypeMap.getDefaultFileTypeMap();

	public PhoneModelDownAction(){
		service = new PhoneModelDownService();
	}
	
	public String modelDown() {
		String prePath = conf.getString("omp.common.path.http-share.coreapp");
		
		if (StringUtils.isEmpty(prePath)) {
			throw new NoticeException("File NOT FOUND");
		}
		filePath = service.selectModelDown(modelCd);
		String fullPath = prePath +"/"+ filePath;
		File mFile = new File(fullPath);
		if (!mFile.canRead()) {
			throw new NoticeException("File NOT FOUND");
		}
		
		String contentType = "application/octet-stream";
		this.setDownloadFile(mFile, contentType, filePath);
		return SUCCESS;
	}

	public String fileWebDown() {
		
		String prePath;
		if ("webcontent".equals(this.bnsType)) {
			prePath = ServletActionContext.getServletContext().getRealPath("/dlcontents");
		} else {
			prePath = conf.getString("omp." + bnsType);
			
		}
		if (StringUtils.isEmpty(prePath)) {
			throw new NoticeException("File NOT FOUND");
		}
		String fullPath = prePath + filePath;
		File mFile = new File(fullPath);
		if (!mFile.canRead()) {
			throw new NoticeException("File NOT FOUND");
		}

		String contentType = "application/octet-stream";
		this.setDownloadFile(mFile, contentType, fileName);
		return SUCCESS;
	}
	
	public String imgWebView() {
		String prePath;
		if ("webcontent".equals(this.bnsType)) {
			prePath = ServletActionContext.getServletContext().getRealPath("/");
		} else {
			prePath = conf.getString("omp." + bnsType);
			
		}
		if (StringUtils.isEmpty(prePath)) {
			throw new NoticeException("File NOT FOUND");
		}

		String fullPath = prePath + filePath;

		File mFile = new File(fullPath);
		if (!mFile.canRead()) {
			throw new NoticeException("File NOT FOUND");
		}

		String contentType = MIME_TYPE_MAP.getContentType(mFile);
		if (contentType == null || !contentType.startsWith("image")) {
			throw new NoticeException("File NOT FOUND");
		}
		this.setDownloadFile(mFile, contentType);
		return SUCCESS;

	}
	
	public String fileDown(String fullPath, String name) {
		return SUCCESS;
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

	public String getModelCd() {
		return modelCd;
	}

	public void setModelCd(String modelCd) {
		this.modelCd = modelCd;
	}

	public String getBnsType() {
		return bnsType;
	}

	public void setBnsType(String bnsType) {
		this.bnsType = bnsType;
	}

}
