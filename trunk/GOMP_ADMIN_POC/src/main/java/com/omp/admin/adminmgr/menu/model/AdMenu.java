/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *            2009. 4. 29.    Description
 *
 */
package com.omp.admin.adminmgr.menu.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;

/**
 * AdminMenu
 * <p/>
 * AdminMenu Model
 * 
 * @author
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AdMenu extends CommonModel implements Pagenateable {

	private String menuId = null;
	private String upMenuId = null;
	private String menuNm = null;
	private String pageUrl = null;
	private int menuPrior = 0;
	private int menuDepth = 0;
	private String subMenuYn = null;
	private String useYn = null;
	private String regId = null;
	private String regDt = null;
	private String updId = null;
	private String updDt = null;

	private PagenateInfoModel page = new PagenateInfoModel(10);
	private Long totalCount;

	public PagenateInfoModel getPage() {
		return this.page;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public Long setTotalCount(Long totalCount) {
		return this.totalCount = totalCount;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUpMenuId() {
		return upMenuId;
	}

	public void setUpMenuId(String upMenuId) {
		this.upMenuId = upMenuId;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public int getMenuPrior() {
		return menuPrior;
	}

	public void setMenuPrior(int menuPrior) {
		this.menuPrior = menuPrior;
	}

	public int getMenuDepth() {
		return menuDepth;
	}

	public void setMenuDepth(int menuDepth) {
		this.menuDepth = menuDepth;
	}

	public String getSubMenuYn() {
		return subMenuYn;
	}

	public void setSubMenuYn(String subMenuYn) {
		this.subMenuYn = subMenuYn;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getUpdDt() {
		return updDt;
	}

	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString());
		sb.append(" menuId='").append(menuId).append("'");
		sb.append(",upMenuId='").append(upMenuId).append("'");
		sb.append(",menuNm='").append(menuNm).append("'");
		sb.append(",pageUrl='").append(pageUrl).append("'");
		sb.append(",menuPrior=").append(menuPrior);
		sb.append(",menuDepth=").append(menuDepth);
		sb.append(",subMenuYn='").append(subMenuYn).append("'");
		sb.append(",useYn='").append(useYn).append("'");
		sb.append(",regId='").append(regId).append("'");
		sb.append(",regDt='").append(regDt).append("'");
		sb.append(",updId='").append(updId).append("'");
		sb.append(",updDt='").append(updDt).append("'");
		sb.append("}");
		return sb.toString();
	}

}
