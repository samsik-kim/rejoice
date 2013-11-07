/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * 이지영            2009. 4. 8.    Description
 *
 */
package com.omp.admin.member.membermgr.model;

import java.util.List;


/**
 * TODO common page model
 * <p/>
 * page model
 *
 * @author  chjin
 * @version 0.1
 */
public class PageModel {
    
    private int rowcnt;
    private int total = 0;          //total count
    private int totalPage = 0;      //total page count
    private int currentPage = 1;    //current page
    private int pageSize = 10;      //page size
    
    private List resultLst;         //단품결과 리스트
    
    /**
     * @return resultLst 값을 반환한다.
     */
    public List getResultLst() {
        return resultLst;
    }

    /**
     * @param resultLst 을 resultLst 에 저장한다.
     */
    public void setResultLst( List resultLst ) {
        this.resultLst = resultLst;
    }

    /**
     * @return totalPage 
     */
    public int getTotalPage() {
        //totalPage
        if( total != 0 && pageSize != 0){
            if( (total/pageSize) * pageSize == total )
                totalPage = total/pageSize;
            else
                totalPage = (total/pageSize) + 1;
        } 
        return totalPage;
    }


    /**
     * @param totalPage 
     */
    public void setTotalPage( int totalPage ) {
        this.totalPage = totalPage;
    }
    
    /**
     * @return currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     */
    public void setCurrentPage( int currentPage ) {
        this.currentPage = currentPage;
    }

    /**
     * @param currentPage
     */
    public void setCurrentPage( String currentPage ) {
        this.currentPage = Integer.parseInt(currentPage);
    }
    
    /**
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize( String pageSize ) {
        if(pageSize.equals("")) this.pageSize = 0;
        else this.pageSize = Integer.parseInt(pageSize);
    }
    
    /**
     * @return rowcnt
     */
    public int getRowcnt() {
        return rowcnt;
    }

    /**
     * @param rowcnt
     */
    public void setRowcnt( int rowcnt ) {
        this.rowcnt = rowcnt;
    }

    /**
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal( int total ) {
        this.total = total;
    }

    
    
}
