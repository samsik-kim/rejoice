package com.omp.dev.common;

import com.omp.dev.common.model.Search;

public class ListPaging {

    private String imgPath = "";
    
    public ListPaging(String imgPath) {
        this.imgPath = imgPath;
    }
    
    public String MakePagingTag(Search search) {
        String rtnTag = "";
        int totalCnt  = search.getTotalCnt();  // Data Total Count
        int pageNo    = search.getPageNo  ();  // Current Page No
        int listSize  = search.getListSize();  // List View Size
        int naviSize  = search.getNaviSize();  // NavigationBar Size        
        int totalPage = 0;
        
        if (totalCnt % listSize == 0) totalPage = totalCnt / listSize;
        else                          totalPage = (totalCnt / listSize) + 1;
        
        int startPage = ((pageNo / naviSize) * naviSize) + 1;
        int endPage   = startPage + (naviSize - 1 );
        if (endPage > totalPage) endPage = totalPage;
        
        rtnTag += "<table> \n";
        rtnTag += "    <tr> \n";
        
        if (startPage == 1) {
            rtnTag += "        <td class='btn'><img src='" + imgPath + "/images/common/btn_prev.gif' alt='이전' /></td> \n";
        } else {
            rtnTag += "        <td class=\"btn\"><a href=\"#\" onclick=\"javascript:f_naviSubmit('" + (startPage - 1) + "'\"><img src=\"" + imgPath + "/images/common/btn_prev.gif\" alt=\"이전\" /></a></td> \n";
        }
        
        
        for (int i=startPage; i <= endPage; i++ ) {
            rtnTag += "        <td><a href=\"#\" onclick=\"javascript:f_naviSubmit('" + i + "')\" " + ((pageNo == i) ? " class=\"select\" " : "") + ">" + i + "</a></td> \n";
        }
        
        if (endPage == totalPage) {
            rtnTag += "        <td class=\"btn\"><img src=\"" + imgPath + "/images/common/btn_next.gif\" alt=\"다음\" /></td> \n";
        } else {
            rtnTag += "        <td class=\"btn\"><a href=\"#\" onclick=\"javascript:f_naviSubmit('" + (endPage + 1) + "'\"><img src=\"" + imgPath + "/images/common/btn_next.gif\" alt=\"다음\" /></a></td> \n";
        }
        
        rtnTag += "    </tr> \n";
        rtnTag += "</table> \n";
    
        return rtnTag;
    }
}
