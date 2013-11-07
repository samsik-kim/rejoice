package com.omp.dev.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class PagingTag extends TagSupport {
    private int total;
    private int page = 1;
    private int scale = 10;
    private int blockScale = 10;
    private String paramPrefix;
	private String requestUrl;  

    @Override
    public int doStartTag() throws JspException{
        try {
            int totalPage = 0;
            if(total == scale)          totalPage = 1;
            else if(total % scale == 0) totalPage = total / scale;
            else                        totalPage = (total / scale) + 1; 
            
			int start = ((page/blockScale)*blockScale)+1;  
			if(page%blockScale == 0) start -= blockScale;
			int end = start + (blockScale-1);
			if(end > totalPage)	end = totalPage;
            
            String contextPath = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
            
            if(paramPrefix != null && !"".equals(paramPrefix)){
            	this.paramPrefix = this.paramPrefix+".";
            }else{
            	this.paramPrefix = "";
            }
            
            String link = new StringBuffer(requestUrl).append("&").append(this.paramPrefix).append("scale=").append(String.valueOf(scale)).append("&").append(this.paramPrefix).append("blockScale=").append(String.valueOf(blockScale)).toString();
            
            StringBuffer sb = new StringBuffer();
            sb.append("<table><tr>");
            if(start > blockScale){
                sb.append("<td class=\"btn\"><a href=\"").append(link).append("&").append(this.paramPrefix).append("page=").append(String.valueOf(start-1)).append("\"><img alt=\"이전\" src=\"").append(contextPath).append("/images/common/btn_prev.gif\"/></a></td>").append("\n");
            }else{
                sb.append("<td class=\"btn\"><img alt=\"이전\" src=\"").append(contextPath).append("/images/common/btn_prev.gif\"/></td>").append("\n");              
            }
            for(int i = start; i<=end; i++){                    
                sb.append("<td><a").append((i==page)?" class=\"select\"":"").append(" href=\"").append(link).append("&").append(this.paramPrefix).append("page=").append(String.valueOf(i)).append("\">").append(String.valueOf(i)).append("</a></td>").append("\n");
            }
            if(end < totalPage){
                sb.append("<td class=\"btn\"><a href=\"").append(link).append("&").append(this.paramPrefix).append("page=").append(String.valueOf(end+1)).append("\"><img alt=\"다음\" src=\"").append(contextPath).append("/images/common/btn_next.gif\"/></a></td>").append("\n");
            }else{
                sb.append("<td class=\"btn\"><img alt=\"다음\" src=\"").append(contextPath).append("/images/common/btn_next.gif\"/></td>").append("\n");
            }
            sb.append("</tr></table>");
            pageContext.getOut().print(sb.toString());
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }


    public int getBlockScale() {
        return blockScale;
    }

    public void setBlockScale(int blockScale) {
        this.blockScale = blockScale;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public String getParamPrefix() {
		return paramPrefix;
	}

	public void setParamPrefix(String paramPrefix) {
		this.paramPrefix = paramPrefix;
	}

}