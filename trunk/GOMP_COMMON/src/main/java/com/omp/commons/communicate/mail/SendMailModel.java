package com.omp.commons.communicate.mail;

import java.io.File;
import java.util.Date;

import com.omp.commons.model.DataValueObject;

/**
 * 이메일 발송 요청에서 사용하는 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class SendMailModel
	extends DataValueObject {
	
	protected String 	templateId;			// 탬플릿 아이디
	protected String	refAppId;			// 연관 업무 아이디
	protected String	refDataId;			// 연관 데이터의 아이디
	protected String	toAddr;				// 수신자 주소
	protected String	toName;				// 수신자 명
	protected String	fromAddr;			// 발신자 주소
	protected String	fromName;			// 발신자명 
	protected String	returnAddr;			// 반송 수신자 주소
	protected String	subject;			// 제목
	protected Object	contentData;		// 데이터
	protected File		attachement;		// 첨부 파일
	protected String	attacheFileName;	// 첨부파일명
	protected Date		reserveDate;		// 예약일시
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getRefAppId() {
		return refAppId;
	}
	public void setRefAppId(String refAppId) {
		this.refAppId = refAppId;
	}
	public String getRefDataId() {
		return refDataId;
	}
	public void setRefDataId(String refDataId) {
		this.refDataId = refDataId;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}
	public String getReturnAddr() {
		return returnAddr;
	}
	public void setReturnAddr(String returnAddr) {
		this.returnAddr = returnAddr;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Object getContentData() {
		return contentData;
	}
	public void setContentData(Object contentData) {
		this.contentData = contentData;
	}
	public File getAttachement() {
		return attachement;
	}
	public void setAttachement(File attachement) {
		this.attachement = attachement;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getAttacheFileName() {
		return attacheFileName;
	}
	public void setAttacheFileName(String attacheFileName) {
		this.attacheFileName = attacheFileName;
	}
}
