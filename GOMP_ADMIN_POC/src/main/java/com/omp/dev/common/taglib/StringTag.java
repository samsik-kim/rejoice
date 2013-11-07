package com.omp.dev.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class StringTag extends TagSupport {
	
		String mbrId;
	  @Override
	    public int doStartTag() throws JspException{
	        try {
	        	if(!"".equals(mbrId )){	        		
	        		if(mbrId.length() > 13 ){	        			
	        			mbrId = mbrId.substring(0, 12)+"...";	        			
	        		}       			        		
	        	}	        	
	        	pageContext.getOut().print(mbrId);	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return SKIP_BODY;
	    }
	/**
	 * @return the mbrId
	 */
	public String getMbrId() {
		return mbrId;
	}
	/**
	 * @param mbrId the mbrId to set
	 */
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	  
}
