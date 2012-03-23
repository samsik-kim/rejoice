package com.seojeong.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import tframe.web.page.PageInfo;

@SuppressWarnings("serial")
public class PageNavigation extends AbstractHtmlElementTag{

	/** PageInfo*/
	private PageInfo pages;
	/** URL Link*/
	private String link;
	/** 페이지 선택시 서버에서 받을 변수명*/
	private String pageIndexName;

	/** default Image **/
	private String firstImg;
	private String prevImg;
	private String nextImg;
	private String lastImg;

	/** 이미지 페이징 여부*/
	private boolean isImage = false;

	/** 페이징 그룹 이동 사용여부 */
	private boolean isMovePageGroup = true;
	
	/** 페이징 스크립트 여부 */
	private boolean isLinkScript = false;

	/** defalut Text */
	private String firstText = "&lt;&lt;";
	private String prevText = "&lt;";
	private String nextText = "&gt;";
	private String lastText = "&gt;&gt;";
	
	/** Style Sheet ID */
	private String styleClass = "";

	/**
	 * pages를(을) 반환한다.
	 * @return pages PageInfo
	 */
	public PageInfo getPages() {
		return pages;
	}

	/**
	 * <pre>
	 * pages에 값을 셋한다
	 * </pre>
	 * @param pages PageInfo
	 */
	public void setPages(PageInfo pages) {
		this.pages = pages;
	}

	/**
	 * link를(을) 반환한다.
	 * @return link URL Link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * link에 값을 셋한다
	 * @param link URL Link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * pageIndexName를(을) 반환한다.
	 * @return pageIndexName 페이지 선택시 서버에서 받을 변수명
	 */
	public String getPageIndexName() {
		return pageIndexName;
	}
	
	/**
	 * pageIndexName에 값을 셋한다
	 * @param pageIndexName 페이지 선택시 서버에서 받을 변수명
	 */
	public void setPageIndexName(String pageIndexName) {
		this.pageIndexName = pageIndexName;
	}

	/**
	 * firstImg를(을) 반환한다.
	 * @return  firstImg 
	 */
	public String getFirstImg() {
		return firstImg;
	}

	/**
	 * firstImg에 값을 셋한다
	 * @param firstImg
	 */
	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}

	/**
	 * prevImg를(을) 반환한다.
	 * @return prevImg
	 */
	public String getPrevImg() {
		return prevImg;
	}

	/**
	 * prevImg에 값을 셋한다
	 * @param prevImg 
	 */
	public void setPrevImg(String prevImg) {
		this.prevImg = prevImg;
	}

	/**
	 * nextImg를(을) 반환한다. 
	 * @return nextImg
	 */
	public String getNextImg() {
		return nextImg;
	}

	/**
	 * nextImg에 값을 셋한다
	 * @param nextImg
	 */
	public void setNextImg(String nextImg) {
		this.nextImg = nextImg;
	}

	/**
	 * lastImg를(을) 반환한다.
	 * @return lastImg
	 */
	public String getLastImg() {
		return lastImg;
	}

	/**
	 * lastImg에 값을 셋한다
	 * @param lastImg
	 */
	public void setLastImg(String lastImg) {
		this.lastImg = lastImg;
	}

	/**
	 * isImage를(을) 반환한다.
	 * @return isImage 이미지 페이징 여부
	 */
	public boolean isImage() {
		return isImage;
	}

	/**
	 * isImage에 값을 셋한다
	 * @param isImage 이미지 페이징 여부
	 */
	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}

	/**
	 * isMovePageGroup를(을) 반환한다.
	 * @return isMovePageGroup 페이징 그룹 이동 사용여부
	 */
	public boolean isMovePageGroup() {
		return isMovePageGroup;
	}

	/**
	 * isMovePageGroup에 값을 셋한다
	 * @param isMovePageGroup 페이징 그룹 이동 사용여부
	 */
	public void setMovePageGroup(boolean isMovePageGroup) {
		this.isMovePageGroup = isMovePageGroup;
	}

	
	/**
	 * isLinkScript 를 반환 한다.
	 * @param link 의 script 여부
	 */	
	public boolean isLinkScript() {
		return isLinkScript;
	}

	/**
	 * isLinkScript에 값을 셋한다
	 * @param link 의 script 여부
	 */	
	public void setLinkScript(boolean isLinkScript) {
		this.isLinkScript = isLinkScript;
	}

	/**
	 * firstText를(을) 반환한다. 
	 * @return firstText
	 */
	public String getFirstText() {
		return firstText;
	}

	/**
	 * firstText에 값을 셋한다
	 * @param firstText
	 */
	public void setFirstText(String firstText) {
		this.firstText = firstText;
	}

	/**
	 * prevText를(을) 반환한다.
	 * @return prevText
	 */
	public String getPrevText() {
		return prevText;
	}

	/**
	 * prevText에 값을 셋한다
	 * @param prevText
	 */
	public void setPrevText(String prevText) {
		this.prevText = prevText;
	}

	/**
	 * nextText를(을) 반환한다.
	 * @return nextText
	 */
	public String getNextText() {
		return nextText;
	}

	/**
	 * nextText에 값을 셋한다
	 * @param nextText
	 */
	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	/**
	 * lastText를(을) 반환한다.
	 * @return lastText
	 */
	public String getLastText() {
		return lastText;
	}

	/**
	 * lastText에 값을 셋한다
	 * @param lastText
	 */
	public void setLastText(String lastText) {
		this.lastText = lastText;
	}

	/**
	 * styleClass를(을) 반환한다.
	 * @return styleClass Style Sheet ID
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * styleClass에 값을 셋한다
	 * @param styleClass  Style Sheet ID
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}


	@SuppressWarnings("unused")
	@Override
	protected int writeTagContent(TagWriter tagWriter) {
		StringBuffer sf = new StringBuffer();
		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
		sf.append("<div class='").append(styleClass).append("'> ");
		
		if (!isImage) {
			if (this.isMovePageGroup) {
				//이전 10페이지가 있을경우
				//sf.append("<span>");
				if (pages.hasPreviousPageUnit()) {
					if(isLinkScript){
						sf.append("<a href='javascript:" + link +"("+pages.getPageOfPreviousPageUnit()+")'>");
					}else{					
						sf.append("<a href='" + link + "?" + pageIndexName + "=" + pages.getPageOfPreviousPageUnit() + "&"
								+ pages.getParamToString() + "'>");
					}
					sf.append(firstText + "</a>");
				} else {
					sf.append(firstText);
				}
				//sf.append("</span>");
			}
			//이전페이지가 있을경우 
			sf.append("<span class='pages'>");
			if (pages.hasPreviousPage()) {
				if(isLinkScript){
					sf.append("<a href='javascript:" + link +"("+pages.getPreviousPage()+")'>");			
				}else{							
					sf.append("<a href='" + link + "?" + pageIndexName + "=" + pages.getPreviousPage() + "&"
							+ pages.getParamToString() + "'>");
				}
				sf.append(prevText + "</a>");
			} else {			
				sf.append(prevText);
				
			}
			//sf.append("</span><span class=\"pre\"></span>");

			//현재 페이지
			if (pages.isEmptyPage()) { //페이지가 존재 하지 않으면
				sf.append("<a class='firstS'>1</a> ");
			} else {
				//현재 페이지가 존재 하면 
				for (int i = pages.getBeginUnitPage(); i <= pages.getEndListPage(); i++) {
					if (i == pages.getCurrentPage()) {
						sf.append(i);
					} else {
						if(isLinkScript){
							sf.append("<a href='javascript:" + link +"("+i+")'>");						
						}else{							
							sf.append("<a href='" + link + "?" + pageIndexName + "=" + i + "&"
									+ pages.getParamToString() + "'>");
						}
						sf.append(i + "</a>");
					}
				}

			}
			//sf.append("<span class=\"next\"></span>");
			//sf.append("<span>");
			if (pages.hasNextPage()) { //다음페이지가 존재하면 
				if(isLinkScript){
					sf.append("<a href='javascript:" + link +"("+pages.getNextPage()+")'>");			
				}else{					
					sf.append("<a href='" + link + "?" + pageIndexName + "=" + pages.getNextPage() + "&"
							+ pages.getParamToString() + "'>");
				}
				sf.append(this.nextText + "</a>");
			} else {
				sf.append(nextText);
			}
			//sf.append("</span>");
			if (this.isMovePageGroup) {
				//sf.append("<span>");
				if (pages.hasNextPageUnit()) {//다음 페이지 그룹이 존재하면 
					if(isLinkScript){
						sf.append("<a href='javascript:" + link +"("+pages.getPageOfNextPageUnit()+")'>");			
					}else{					
					sf.append("<a href='" + link + "?" + pageIndexName + "=" + pages.getPageOfNextPageUnit() + "&"
							+ pages.getParamToString() + "'>");
					}
					sf.append(lastText + "</a>");
				} else {
					sf.append(lastText);
				}
				//sf.append("</span>");
			}

		} else {
			//if (this.isMovePageGroup) {
				//이전 10페이지가 있을경우
				if(pages.getCurrentPage() != 1) {
					if(isLinkScript){
						sf.append("<a class='btn-page-first' href='javascript:" + link +"("+1+")'> ");
					}else{
						sf.append("<a class='btn-page-first' href='" + link + "?" + pageIndexName + "=" + 1 + "&"							
								+ pages.getParamToString() + "'> ");					
					}
					sf.append("<img  alt=\"첫 페이지로 이동\" class=\"hover\" src=\"" + contextPath + firstImg + "\"></a> ");
				} else {
					sf.append("<img alt=\"첫 페이지로 이동\" class=\"hover\" src=\"" + contextPath + firstImg + "\"> ");
				}
					
			//이전페이지가 있을경우 
			if (pages.hasPreviousPage()) {
				int prevPageOdd = pages.getCurrentPage()%10;
				int resultPrevPageNum = 0;
				if(prevPageOdd != 0) {
					resultPrevPageNum = (pages.getCurrentPage() - prevPageOdd);
				} else {
					resultPrevPageNum = pages.getCurrentPage() - 10;
				}
				if(isLinkScript){
					sf.append("<a class='btn-page-prev' href='javascript:" + link +"("+resultPrevPageNum+")'> ");
				}else{				
					sf.append("<a class='btn-page-prev' href='" + link + "?" + pageIndexName + "=" + resultPrevPageNum + "&"
							+ pages.getParamToString() + "'> ");
				}
				sf.append("<img alt=\"이전 페이지로 이동\" class=\"hover\" src=\"" + contextPath + this.prevImg + "\"></a> ");
			} else {
				sf.append("<img alt=\"이전 페이지로 이동\" class=\"hover\" class='prevImg' src=\"" + contextPath + prevImg + "\"> ");
			}
			//현재 페이지
			if (pages.isEmptyPage()) { //페이지가 존재 하지 않으면
				sf.append("<a  class=\"current num\">1</a> ");
			} else {
				//현재 페이지가 존재 하면 
				for (int i = pages.getBeginUnitPage(); i <= pages.getEndListPage(); i++) {
					if (i == pages.getCurrentPage()) {
						sf.append("<strong class=\"current num\">"+i+"</strong>");
					} else {
						sf.append("<a class=\"num\" href='javascript:" + link +"("+i+")'>"+i+"</a>");
					}
				}
			}
			
			if (pages.hasNextPage()) { //다음페이지가 존재하면 
				int nextPageOdd = pages.getPageOfNextPageUnit()%10;
				int resultNextPage = 0;
				if(pages.getTotalPage() > 10) {
					if(nextPageOdd != 0) {
						resultNextPage = (pages.getPageOfNextPageUnit() - nextPageOdd) + 1;
					} else {
						resultNextPage = pages.getPageOfNextPageUnit() - 9;
					}
				} else {
					resultNextPage = pages.getTotalPage();
				}
				
				if(isLinkScript){
					sf.append("<a class='btn-page-next' href='javascript:" + link +"("+resultNextPage+")'> ");
				} else {
					sf.append("<a class='btn-page-next' href='" + link + "?" + pageIndexName + "=" + resultNextPage + "&"
							+ pages.getParamToString() + "'> ");
				}
				sf.append("<img alt=\"다음 페이지로 이동\" class=\"hover\" src=\"" + contextPath + this.nextImg + "\"></a> ");
			} else {
				sf.append("<img alt=\"다음 페이지로 이동\" class=\"hover\" class='next' src=\"" + contextPath + nextImg + "\"> ");
			}
			//sf.append("</span>");
			//sf.append("<span>");
//			if (pages.hasNextPageUnit()) {//다음 페이지 그룹이 존재하면 
				if(pages.getCurrentPage() != pages.getTotalPage()) {
					if(isLinkScript){
						sf.append("<a class='btn-page-last' href='javascript:" + link +"("+pages.getTotalPage()+")'> ");			
					}else{					
						sf.append("<a class='btn-page-last' href='" + link + "?" + pageIndexName + "=" + pages.getTotalPage() + "&"
								+ pages.getParamToString() + "'> ");
					}
					sf.append("<img alt=\"다음 페이지로 이동\" class=\"hover\" src=\"" + contextPath + this.lastImg + "\"></a> ");
				} else {
					sf.append("<img alt=\"다음 페이지로 이동\" class=\"hover\" src=\"" + contextPath + this.lastImg + "\"> ");
				}
			sf.append("</div> ");

		}
		try {
			out.write(sf.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);

		}

		return EVAL_PAGE;
	}
}