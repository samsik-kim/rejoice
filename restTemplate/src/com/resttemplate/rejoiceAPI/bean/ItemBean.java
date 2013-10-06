package com.resttemplate.rejoiceAPI.bean;

@SuppressWarnings("serial")
public class ItemBean extends ResponseBean{
	
	/**
	 * @comment : ID
	 */
	private String id;
	/**
	 * @comment : NAME
	 */
	private String name;
	/**
	 * @comment : NO
	 */
	private String no;
	/**
	 * @comment : 기타
	 */
	private String etc;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}
	/**
	 * @param etc the etc to set
	 */
	public void setEtc(String etc) {
		this.etc = etc;
	}

}