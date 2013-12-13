package com.omp.commons.paging;

/**
 * 
 * @author pat
 * @deprecated 페이징 방법 변경
 */
public class PageNavigator
{
    int currentPage;
    int rowsPerPage;
    int startRow;
    int endRow;
    int totalSize;
    int pageTotal=0;// = (totalRecord)/pagingUnit;
    int pageGroupStart;
    int pageGroupEnd;

    public PageNavigator(int i, int j)
    {
        setCurrentPage(i);
        setRowsPerPage(j);
    }

    public int getCurrentPage()
    {
        if ( pageTotal > 0 && currentPage>pageTotal) currentPage=pageTotal;
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        if(currentPage < 1) currentPage = 1;
        this.currentPage = currentPage;
        calcStartEndRow(currentPage);
    }

    public int getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
        calcStartEndRow(currentPage);
    }

    public int getStartRow()
    {
        return startRow;
    }

    public int getEndRow()
    {
        return endRow;
    }

    private void calcStartEndRow(int currentPage)
    {
        startRow = (currentPage - 1) * rowsPerPage + 1;
        endRow = startRow + rowsPerPage - 1;
    }

    public int getTotalSize()
    {
        return totalSize;
    }

    public void setTotalSize(int totalSize)
    {
        this.totalSize = totalSize;
        setPageTotal();
        setPageGroupStart();
        setPageGroupEnd();
    }

    public int getPageTotal()
    {
        return pageTotal;
    }

    public int getPageGroupStart()
    {
        return pageGroupStart;
    }

    public int getPageGroupEnd()
    {
        return pageGroupEnd;
    }

    public void setPageTotal()
    {
        int tempPageTotal=(totalSize)/rowsPerPage;
        if(totalSize > tempPageTotal*rowsPerPage)
            tempPageTotal++;
        this.pageTotal = tempPageTotal;
    }

    public void setPageGroupStart()
    {
        this.pageGroupStart = ((currentPage-1)/10) * 10 + 1;
    }

    public void setPageGroupEnd()
    {
        int tempPageGroupEnd   = getPageGroupStart() + 10;
        if (tempPageGroupEnd> getPageTotal())
            tempPageGroupEnd = getPageTotal() + 1;
        if(tempPageGroupEnd > 1) tempPageGroupEnd=tempPageGroupEnd - 1;
        this.pageGroupEnd = tempPageGroupEnd;
    }

    public void setPageGroupEnd(int totalPage)
    {
        this.pageGroupEnd = totalPage;
    }
}
