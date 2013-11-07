package com.omp.admin.common.model;

import java.util.Vector;

/**
 * 이메일 전송 시 사용되는 정보 정의 클래스.
 * @author soohee
 *
 */
public class Email {
	// 보내는이 정보
	private String sender;
	private String sMail;
	// 받는이 정보
	private String reciever;
	private String rEmail;
	// 메일내용
	private String[] params;
	private Vector<String[]> repeatData; // 메일내용 중 반복되는 데이터
	private String title;
	private String[] titleArgs; // 메일제목에 동적 세팅 시 사용
	// 예약전송시
	private String sendDate;
	private String sendTime;
	// 템플릿ID
	private int templeteId;
	
	public Vector<String[]> getRepeatData() {
		return repeatData;
	}
	public void setRepeatData(Vector<String[]> repeatData) {
		this.repeatData = repeatData;
	}
	public String[] getTitleArgs() {
		return titleArgs;
	}
	public void setTitleArgs(String[] titleArgs) {
		this.titleArgs = titleArgs;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSMail() {
		return sMail;
	}
	public void setSMail(String mail) {
		sMail = mail;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getREmail() {
		return rEmail;
	}
	public void setREmail(String email) {
		rEmail = email;
	}
	public String[] getParams() {
		return params;
	}
	public void setParams(String[] params) {
		this.params = params;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getTempleteId() {
		return templeteId;
	}
	public void setTempleteId(int templeteId) {
		this.templeteId = templeteId;
	}
	
	
	
}
