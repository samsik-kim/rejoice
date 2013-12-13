package com.omp.commons.paging;

import javax.servlet.http.HttpServletRequest;

import com.omp.commons.paging.PageNavigator;

public class PageWrapper {

	public String pagingUI = "";

	private String startRow;
	private String endRow;
	private int currentPage;
	private int totalSize;
	private int pageGroupStart;
	private int pageGroupEnd;
	private int firstPage;
	private int lastPage;
	private int prevPage;
	private int nextPage;
	private int prevGroup;
	private int nextGroup;
	private boolean isExsistLeftArrow;
	private boolean isExsistRightArrow;

	/**
	 * @return the prevGroup
	 */
	public int getPrevGroup() {
		return prevGroup;
	}

	/**
	 * @param prevGroup
	 *            the prevGroup to set
	 */
	public void setPrevGroup(int prevGroup) {
		this.prevGroup = prevGroup;
	}

	/**
	 * @return the nextGroup
	 */
	public int getNextGroup() {
		return nextGroup;
	}

	/**
	 * @param nextGroup
	 *            the nextGroup to set
	 */
	public void setNextGroup(int nextGroup) {
		this.nextGroup = nextGroup;
	}

	/**
	 * @return the prevPage
	 */
	public int getPrevPage() {
		return prevPage;
	}

	/**
	 * @param prevPage
	 *            the prevPage to set
	 */
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the pagingUI
	 */
	public String getPagingUI() {
		return pagingUI;
	}

	/**
	 * @param pagingUI
	 *            the pagingUI to set
	 */
	public void setPagingUI(String pagingUI) {
		this.pagingUI = pagingUI;
	}

	/**
	 * @return the firstPage
	 */
	public int getFirstPage() {
		return firstPage;
	}

	/**
	 * @param firstPage
	 *            the firstPage to set
	 */
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	/**
	 * @return the lastPage
	 */
	public int getLastPage() {
		return lastPage;
	}

	/**
	 * @param lastPage
	 *            the lastPage to set
	 */
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return the startRow
	 */
	public String getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow
	 *            the startRow to set
	 */
	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the endRow
	 */
	public String getEndRow() {
		return endRow;
	}

	/**
	 * @param endRow
	 *            the endRow to set
	 */
	public void setEndRow(String endRow) {
		this.endRow = endRow;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalSize
	 */
	public int getTotalSize() {
		return totalSize;
	}

	/**
	 * @param totalSize
	 *            the totalSize to set
	 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	/**
	 * @return the pageGroupStart
	 */
	public int getPageGroupStart() {
		return pageGroupStart;
	}

	/**
	 * @param pageGroupStart
	 *            the pageGroupStart to set
	 */
	public void setPageGroupStart(int pageGroupStart) {
		this.pageGroupStart = pageGroupStart;
	}

	/**
	 * @return the pageGroupEnd
	 */
	public int getPageGroupEnd() {
		return pageGroupEnd;
	}

	/**
	 * @param pageGroupEnd
	 *            the pageGroupEnd to set
	 */
	public void setPageGroupEnd(int pageGroupEnd) {
		this.pageGroupEnd = pageGroupEnd;
	}

	/**
	 * @return the isExsistLeftArrow
	 */
	public boolean isExsistLeftArrow() {
		return isExsistLeftArrow;
	}

	/**
	 * @param isExsistLeftArrow
	 *            the isExsistLeftArrow to set
	 */
	public void setExsistLeftArrow(boolean isExsistLeftArrow) {
		this.isExsistLeftArrow = isExsistLeftArrow;
	}

	/**
	 * @return the isExsistRightArrow
	 */
	public boolean isExsistRightArrow() {
		return isExsistRightArrow;
	}

	/**
	 * @param isExsistRightArrow
	 *            the isExsistRightArrow to set
	 */
	public void setExsistRightArrow(boolean isExsistRightArrow) {
		this.isExsistRightArrow = isExsistRightArrow;
	}

	/**
	 * 페이징 객체 생성자 req
	 * 
	 * @param totalCnt
	 * @param goPage
	 * @param rowsPerPage
	 * @return
	 * @throws DaoException
	 */
	public PageWrapper(Long totalCnt, String rowsPerPage, int uiNum,
			HttpServletRequest req) {
		String goPage = req.getParameter("goPage");
		String contextRoot = req.getContextPath();
		String targetURL = req.getServletPath().substring(
				req.getServletPath().lastIndexOf('/') + 1);
		String formName = "document.forms[document.forms.length-1]";

		setPage(totalCnt, goPage, rowsPerPage, uiNum, formName, targetURL,
				contextRoot);
	}

	// setPage
	public void setPage(Long totalCnt, String goPage, String rowsPerPage,
			int uiNum, String formName, String targetURL, String contextRoot) {
		if (goPage == null || "".equals(goPage))
			goPage = "1";
		int iPage = Integer.parseInt(goPage); // 이동할 페이지
		int cntPerPage = Integer.parseInt(rowsPerPage);
		PageNavigator navi = new PageNavigator(iPage, cntPerPage); // setCurrentPage,setRowsPerPage
		navi.setTotalSize(totalCnt.intValue()); // 총건수 세팅

		setStartRow("" + navi.getStartRow());
		setEndRow("" + navi.getEndRow());

		// 엑셀 export action은 페이징 없게함
		if (targetURL.startsWith("export")) {
			setStartRow("1");
			setEndRow("" + navi.getTotalSize());
		}

		setTotalSize(navi.getTotalSize());
		setCurrentPage(navi.getCurrentPage());
		setFirstPage(1);
		setLastPage(navi.getPageTotal());
		setPageGroupStart(navi.getPageGroupStart());
		setPageGroupEnd(navi.getPageGroupEnd());

		if (navi.getPageGroupStart() == 1)
			setPrevGroup(1);
		else
			setPrevGroup(navi.getPageGroupStart() - 1); // 이전그룹

		if (navi.getPageGroupEnd() == navi.getPageTotal())
			setNextGroup(navi.getPageTotal());
		else
			setNextGroup(navi.getPageGroupEnd() + 1); // 다음그룹

		if (navi.getCurrentPage() == 1)
			setPrevPage(navi.getCurrentPage());
		else
			setPrevPage(navi.getCurrentPage() - 1); // 이전페이지

		if (navi.getCurrentPage() == navi.getPageTotal())
			setNextPage(navi.getPageTotal());
		else
			setNextPage(navi.getCurrentPage() + 1); // 다음페이지

		if (navi.getPageGroupStart() == 1)
			setExsistLeftArrow(false);
		else
			setExsistLeftArrow(true); // < 표시여부

		if (navi.getPageGroupEnd() == navi.getPageTotal())
			setExsistRightArrow(false);
		else
			setExsistRightArrow(true); // > 표시여부

		pagingUI = makeUI(uiNum, formName, targetURL, iPage, contextRoot);
	}

	// makeUI
	private String makeUI(int uiNum, String formName, String targetURL,
			int iPage, String contextRoot) {
		StringBuffer buf = new StringBuffer();
		int pageNum = getPageGroupStart();
		int pageEnd = getPageGroupEnd();
		// String imgRoot = com.omp.commons.bundle.Message.get("context.root");
		// // 이미지경로
		String imgRoot = contextRoot;

		// 공통
		buf.append("<script>function goPage(num) {" + formName + ".action='"
				+ targetURL + "';" + formName + ".target='_self';" + formName
				+ ".goPage.value=num;" + formName + ".submit();}</script>");
		buf.append("<input type=hidden name=goPage value='" + iPage + "'>\n");

		if (uiNum == 1) {
		}

		else if (uiNum == 2) {
			
			// 개발자 POC 디폴트 페이징 UI
			buf.append("\n\t\t\t\t\t<table>\n<tr>\n");
			buf.append("\t\t\t\t\t<td class='btn'>");
			if (isExsistLeftArrow())
				buf.append("<a href='javascript:goPage(" + getPrevGroup()
						+ ");'>");
			buf.append("<img src='" + imgRoot
					+ "/images/common/btn_prev.gif' alt='이전'/>");
			if (isExsistLeftArrow())
				buf.append("</a>");
			buf.append("</td>\n");
			String selectStr = "class='select'";

			for (int i = pageNum; i <= pageEnd; i++) {
				if (i == getCurrentPage())
					selectStr = "class='select'";
				else
					selectStr = "";
				buf.append("\t\t\t\t\t<td><a href='javascript:goPage(" + i
						+ ");' " + selectStr + " >" + i + "</a></td>\n");
			}

			buf.append("\t\t\t\t\t<td class='btn'>");
			if (isExsistRightArrow())
				buf.append("<a href='javascript:goPage(" + getNextGroup()
						+ ");'>");
			buf.append("<img src='" + imgRoot
					+ "/images/common/btn_next.gif' alt='다음'/>");
			if (isExsistRightArrow())
				buf.append("</a>");
			buf.append("</td>\n");
			buf.append("\t\t\t\t\t</tr></table>\n");
			buf.append("<br style='clear:both;' />");
		
		}

		else if (uiNum == 99) {
			// admin POC 디폴트 페이징 UI
			buf.append("\n\t\t\t\t\t<table>\n<tr>\n");
			String selectStr = "class='choice'";
			buf.append("<td class='pre_02'><a href='javascript:goPage("
					+ getFirstPage() + ");'><img src='" + imgRoot
					+ "/images/pre_02.gif' alt='' /></a></td>  ");
			buf.append("<td class='pre_02'><a href='javascript:goPage("
					+ getPrevGroup() + ");'><img src='" + imgRoot
					+ "/images/pre_01.gif' alt='' /></a></td>  ");
			buf
					.append("<td class='pre_01'><a href='javascript:goPage("
							+ getPrevPage()
							+ ");'>PRE</a></td>                                       ");

			for (int i = pageNum; i <= pageEnd; i++) {
				if (i == getCurrentPage())
					selectStr = "class='choice'";
				else
					selectStr = "";
				buf.append("\t\t\t\t\t<td><a href='javascript:goPage(" + i
						+ ");' " + selectStr + " >" + i + "</a></td>\n");
			}

			buf.append("<td class='next_01'><a href='javascript:goPage("
					+ getNextPage()
					+ ");'>NEXT</a></td>                                     ");
			buf.append("<td class='next_02'><a href='javascript:goPage("
					+ getNextGroup() + ");'><img src='" + imgRoot
					+ "/images/next_01.gif' alt='' /></a></td>");
			buf.append("<td class='next_02'><a href='javascript:goPage("
					+ getLastPage() + ");'><img src='" + imgRoot
					+ "/images/next_02.gif' alt='' /></a></td>");

			buf.append("\t\t\t\t\t</tr></table>\n");

		}

		else {

			// 개발자 POC 디폴트 페이징 UI
			buf.append("\n\t\t\t\t\t<table>\n<tr>\n");
			buf.append("\t\t\t\t\t<td class='btn'>");
			if (isExsistLeftArrow())
				buf.append("<a href='javascript:goPage(" + getPrevGroup()
						+ ");'>");
			buf.append("<img src='" + imgRoot
					+ "/images/common/btn_prev.gif' alt='이전'/>");
			if (isExsistLeftArrow())
				buf.append("</a>");
			buf.append("</td>\n");
			String selectStr = "class='select'";

			for (int i = pageNum; i <= pageEnd; i++) {
				if (i == getCurrentPage())
					selectStr = "class='select'";
				else
					selectStr = "";
				buf.append("\t\t\t\t\t<td><a href='javascript:goPage(" + i
						+ ");' " + selectStr + " >" + i + "</a></td>\n");
			}

			buf.append("\t\t\t\t\t<td class='btn'>");
			if (isExsistRightArrow())
				buf.append("<a href='javascript:goPage(" + getNextGroup()
						+ ");'>");
			buf.append("<img src='" + imgRoot
					+ "/images/common/btn_next.gif' alt='다음'/>");
			if (isExsistRightArrow())
				buf.append("</a>");
			buf.append("</td>\n");
			buf.append("\t\t\t\t\t</tr></table>\n");
		}

		return buf.toString();
	}

	// 테스트용
	public static void main(String[] args) {

	}

}
