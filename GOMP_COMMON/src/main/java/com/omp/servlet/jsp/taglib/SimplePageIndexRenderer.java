package com.omp.servlet.jsp.taglib;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import pat.web.util.pageindex.PageIndexRenderer;

/**
 * 간단한 페이지 인덱스 탬플릿 객체<br/>
 * header, footer, separater 를 제외한 각 Link에는 다음과 같은 심볼을 삽입 할 수 있습니다.<br/>
 * ${link} : 페이지 이동 URL<br/>
 * ${js} : 페이지 이동 스크립트<br/>
 * ${no} : 페이지 번호
 * @author pat
 *
 */
public class SimplePageIndexRenderer
	implements PageIndexRenderer {
	
    private static Pattern	PTN_VALUE_EXPR	= Pattern.compile("\\$\\{([a-zA-Z0-9.-_]+?)\\}");
	
	protected String header;
	protected String footer;
	protected String topLink;
	protected String bottomLink;
	protected String nextLink;
	protected String previousLink;
	protected String incLink;
	protected String decLink;
	protected String currentPageLink;
	protected String otherPageLink;
	protected String separater;
	protected int	scale;
	
	public SimplePageIndexRenderer() {
		this.header				= "";
		this.footer				= "";
		this.topLink			= "<a href='${link}' alt='go TOP'><b>|&lt;</b></a>&nbsp;";
		this.bottomLink			= "&nbsp;<a href='${link}' alt='go Bottom'><b>&gt;|</b></a>";
		this.nextLink			= "&nbsp;<a href='${link}' alt='go Next'><b>&gt;</b></a>&nbsp;";
		this.previousLink		= "&nbsp;<a href='${link}' alt='go Previous'><b>&lt;</b></a>&nbsp;";
		this.currentPageLink	= "&nbsp;<b>${no}</b>&nbsp;";
		this.incLink			= "&nbsp;<a href='${link}' alt='go Page ${no}'>+</a>&nbsp;";
		this.decLink			= "&nbsp;<a href='${link}' alt='go Page ${no}'>-</a>&nbsp;";
		this.otherPageLink		= "&nbsp;<a href='${link}' alt='go Page ${no}'>${no}</a>&nbsp;";
		this.separater			= "&nbsp;|&nbsp;";
		this.scale				= 10;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public void setSeparater(String separater) {
		this.separater = separater;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public void setTopLink(String topLink) {
		this.topLink = topLink;
	}

	public void setBottomLink(String bottomLink) {
		this.bottomLink = bottomLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	public void setPreviousLink(String previousLink) {
		this.previousLink = previousLink;
	}

	public void setCurrentPageLink(String currentPageLink) {
		this.currentPageLink = currentPageLink;
	}

	public void setOtherPageLink(String otherPageLink) {
		this.otherPageLink = otherPageLink;
	}
	
	public void setIncLink(String incLink) {
		this.incLink = incLink;
	}

	public void setDecLink(String decLink) {
		this.decLink = decLink;
	}

	@Override
    public void doWritePageIndex(JspWriter wt, int total, int pageNo, int rowsPerPage, String jsFunctionName)
            throws IOException {
        int fromPageNo;
        int toPageNo;
        int	bottomPageNo;
		
		// 해더 출력
        wt.println();
        wt.print("<!-- Page Index Start (Use Java Script Function ");
        wt.print(jsFunctionName);
        wt.println("() -->");
        wt.print(this.header);
        
        // 탑 페이지 출력
        if (!StringUtils.isEmpty(this.topLink)) {
        	wt.print(this.renderLink(this.topLink, pageNo, 1, jsFunctionName));
        }

        // 이전 페이지 출력
        if (!StringUtils.isEmpty(this.previousLink)) {
            int	prePageNo;
            
	        prePageNo	= pageNo - this.scale;
	        if (prePageNo < 1) {
	        	prePageNo	= 1;
	        }
	        wt.print(this.renderLink(this.previousLink, pageNo, prePageNo, jsFunctionName));
        }
        
        // 한페이지 전 출력
        if (!StringUtils.isEmpty(this.decLink)) {
            int	decPageNo;
            
            decPageNo	= pageNo - 1;
	        if (decPageNo < 1) {
	        	decPageNo	= 1;
	        }
	        wt.print(this.renderLink(this.decLink, pageNo, decPageNo, jsFunctionName));
        }

        // 페이지 인덱스
        fromPageNo	= pageNo - ((pageNo-1) % this.scale);
        toPageNo	= fromPageNo + this.scale - 1;
        if (total == 0) {
        	bottomPageNo   = 1;
        } else {
        	bottomPageNo   = total / rowsPerPage;
            if ((total % rowsPerPage) != 0) {
            	bottomPageNo++;
            }
        }
        if (fromPageNo < 1) {
        	fromPageNo	= 1;
        }
        if (toPageNo > bottomPageNo) {
        	toPageNo = bottomPageNo;
        }
        
        for (int i=fromPageNo; i<=toPageNo; i++) {
        	if (i>fromPageNo) {
        		wt.print(this.separater);
        	}
    		wt.print(this.renderLink(i == pageNo ? this.currentPageLink : this.otherPageLink, pageNo, i, jsFunctionName));
        }
        
        // 한페이지 후 출력
        if (!StringUtils.isEmpty(this.incLink)) {
            int	incPageNo;
            
            incPageNo	= pageNo + 1;
	        if (incPageNo > bottomPageNo) {
	        	incPageNo	= bottomPageNo;
	        }
	        wt.print(this.renderLink(this.incLink, pageNo, incPageNo, jsFunctionName));
        }
        

        // 다음 페이지 출력
        if (!StringUtils.isEmpty(this.nextLink)) {
            int	nextPageNo;
            
	        nextPageNo	= pageNo + this.scale;
	        if (nextPageNo > bottomPageNo) {
	        	nextPageNo	= bottomPageNo;
	        }
	        wt.print(this.renderLink(this.nextLink, pageNo, nextPageNo, jsFunctionName));
        }
        
        // 마지막 페이지 출력
        if (!StringUtils.isEmpty(this.bottomLink)) {
        	wt.print(this.renderLink(this.bottomLink, pageNo, bottomPageNo, jsFunctionName));
        }

        // 푸터
        wt.println(this.footer);
	}

	protected String renderLink(String src, int pageNo, int workPageNo, String funcName) {
		Matcher			mch;
		StringBuffer	sb;
		String			js;
		String			link;
		String			no;

		if (pageNo == workPageNo) {
			js		= "";
			link	= "#";
		} else {
			js		= funcName + "(" + workPageNo + ")";
			link	= "javascript:" + js;
		}
		no		= String.valueOf(workPageNo);
		sb		= new StringBuffer();
		mch		= PTN_VALUE_EXPR.matcher(src);
		while (mch.find()) {
			String	valueId;
			
			valueId	= mch.group(1);
			if ("$".equals(valueId)) {
				mch.appendReplacement(sb, "\\$");
			} else if ("link".equals(valueId)) {
				mch.appendReplacement(sb, link);
			} else if ("js".equals(valueId)) {
				mch.appendReplacement(sb, js);
			} else if ("no".equals(valueId)) {
				mch.appendReplacement(sb, no);
			} else {
				mch.appendReplacement(sb, "\\${" + valueId + "}");
			}
		}
		mch.appendTail(sb);
		
		return sb.toString();
	}
}
