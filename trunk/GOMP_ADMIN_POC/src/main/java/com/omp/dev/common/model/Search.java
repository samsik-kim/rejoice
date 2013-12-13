/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 3. 30.    Description
 *
 */
package com.omp.dev.common.model;

/**
 * 지식 Base Model
 * 
 * @author redaano
 * @version 0.1
 */
public class Search {
    private String ctgrCd     ;    // 카테고리 코드
    private String ctgrNm     ;    // 카테고리 명
    private String keyValue   ;    // view 페이지 조회 시 사용할 Primary Key 값
    private String searchKey  ;    // 검색 키    
    private String searchValue;    // 검색 값 
    
    private String   ymdGbn      ;    // 일자별 / 월별 구분
    private String   divGbn      ;    // 구분코드
    private String   divGbnNm    ;    // 구분코드명
    private String   fromDt      ;    // From 일자
    private String   toDt        ;    // To   일자
    
    private String   mbrNo      ;    // 로그인 회원 번호
    private String   mbrNm      ;    // 로그인 회원명

    // 페이징 관련.
    private int    pageNo     ;    // 페이지 번호
    private int    totalCnt   ;    // 검색 총 레코드 수
    private int    listSize   ;    // 한 페이지에 보일 리스트 크기 (Default = 10)
    private int    naviSize   ;    // 네비게이션바의 크기 (Default = 10)
    private String pagingTag  ;
    private String imgPath    ;
    
    public String getCtgrCd() {
        return ctgrCd;
    }
    public void setCtgrCd( String ctgrCd ) {
        this.ctgrCd = ctgrCd;
    }
    public String getCtgrNm() {
        return ctgrNm;
    }
    public void setCtgrNm( String ctgrNm ) {
        this.ctgrNm = ctgrNm;
    }
    public String getKeyValue() {
        return keyValue;
    }
    public void setKeyValue( String keyValue ) {
        this.keyValue = keyValue;
    }
    public String getSearchKey() {
        return searchKey;
    }
    public void setSearchKey( String searchKey ) {
        this.searchKey = searchKey;
    }
    public String getSearchValue() {
        return searchValue;
    }
    public void setSearchValue( String searchValue ) {
        this.searchValue = searchValue;
    }
    public int getPageNo() {
        if (pageNo == 0) pageNo = 1;
        return pageNo;
    }
    public void setPageNo( int pageNo ) {
        this.pageNo = pageNo;
    }
    public int getTotalCnt() {
        return totalCnt;
    }
    public void setTotalCnt( int totalCnt ) {
        this.totalCnt = totalCnt;
    }
    public int getListSize() {
        if (listSize == 0) listSize = 10;
        return listSize;
    }
    public void setListSize( int listSize ) {
        this.listSize = listSize;
    }    
    public int getNaviSize() {
        if (naviSize == 0) naviSize = 5;
        return naviSize;
    }
    public void setNaviSize( int naviSize ) {
        this.naviSize = naviSize;
    }
    public String getPagingTag() {
        return pagingTag;
    }
    public void setPagingTag( String pagingTag ) {
        this.pagingTag = pagingTag;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath( String imgPath ) {
        this.imgPath = imgPath;
    }
    public String getYmdGbn() {
        return ymdGbn;
    }
    public void setYmdGbn( String ymdGbn ) {
        this.ymdGbn = ymdGbn;
    }
    public String getDivGbn() {
        return divGbn;
    }
    public void setDivGbn( String divGbn ) {
        this.divGbn = divGbn;
    }
    public String getDivGbnNm() {
        return divGbnNm;
    }
    public void setDivGbnNm( String divGbnNm ) {
        this.divGbnNm = divGbnNm;
    }
    public String getFromDt() {
        return fromDt;
    }
    public void setFromDt( String fromDt ) {
        this.fromDt = fromDt;
    }
    public String getToDt() {
        return toDt;
    }
    public void setToDt( String toDt ) {
        this.toDt = toDt;
    }
    public String getMbrNo() {
        return mbrNo;
    }
    public void setMbrNo( String mbrNo ) {
        this.mbrNo = mbrNo;
    }
    public String getMbrNm() {
        return mbrNm;
    }
    public void setMbrNm( String mbrNm ) {
        this.mbrNm = mbrNm;
    }    
       
}