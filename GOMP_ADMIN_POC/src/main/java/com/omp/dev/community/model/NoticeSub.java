/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 13.    Description
 *
 */
package com.omp.dev.community.model;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

/**
 * Notice Sub Model
 * 
 * @author redaano
 * @version 0.1
 */
public class NoticeSub implements Pagenateable, TotalCountCarriable {
	private PagenateInfoModel    page        = new PagenateInfoModel();
	private String rnum;
	private Long    totalCount;
    private String noticeId = ""; // Notice_ID
    private String ctgrCd = ""; // Category_Code

    private String keyType = ""; // Search condition(really use)
    private String keyWord = ""; // Search Word(really use)

    private String searchType = ""; // Search condition(screen use)
    private String searchWord = ""; // Search Word(screen use)
    private String searchNew = ""; // Search Button Click(screen use)
    private String mainOpenYn = "";
    private String openYn = "";

    @Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}
	
	@Override
    public PagenateInfoModel getPage() {
        return this.page;
    }
	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
    public String getMainOpenYn() {
        return mainOpenYn;
    }


    public void setMainOpenYn(String mainOpenYn) {
        this.mainOpenYn = mainOpenYn;
    }


    public String getOpenYn() {
        return openYn;
    }


    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }


    public String getNoticeId() {
        return noticeId;
    }


    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }


    public String getCtgrCd() {
        return ctgrCd;
    }


    public void setCtgrCd(String ctgrCd) {
        this.ctgrCd = ctgrCd;
    }


    public String getKeyType() {
        return keyType;
    }


    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }


    public String getKeyWord() {
        return keyWord;
    }


    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


    public String getSearchType() {
        return searchType;
    }


    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }


    public String getSearchWord() {
        return searchWord;
    }


    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }


    public String getSearchNew() {
        return searchNew;
    }


    public void setSearchNew(String searchNew) {
        this.searchNew = searchNew;
    }

}