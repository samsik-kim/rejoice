package com.omp.commons.commcode.model;

import java.io.Serializable;
import java.util.StringTokenizer;

import pat.neocore.util.StringPattern;

/**
 * 공통코드 Model <br>
 * 
 * @version 2009.03.12 
 * @author 김애경
 */
// History
// 김애경 [2009.03.12]: 초안작성


public class CommCode
	implements Serializable{

    private String 		grpCd;
    private String 		dtlCd;
    private String 		dtlFullCd;
    private String 		cdNm;
    private String 		addField1;
    private String 		addField2;
    private String 		useYN;
    private String 		description;
    private String 		displayOrder;
    private String[]	tags;

    public String getGrpCd() {
        return grpCd;
    }
    
    public void setGrpCd( String grpCd ) {
        this.grpCd = grpCd;
    }
    
    public String getDtlCd() {
        return dtlCd;
    }
    
    public void setDtlCd( String dtlCd ) {
        this.dtlCd = dtlCd;
    }

    public String getDtlFullCd() {
        return dtlFullCd;
    }
    
    public void setDtlFullCd( String dtlFullCd ) {
        this.dtlFullCd = dtlFullCd;
    }

    public String getCdNm() {
        return cdNm;
    }
    
    public void setCdNm( String cdNm ) {
        this.cdNm = cdNm;
    }
    
    public String getAddField1() {
        return addField1;
    }
    
    public void setAddField1( String addField1 ) {
        this.addField1 = addField1;
    }
    
    public String getAddField2() {
        return addField2;
    }
    
    public void setAddField2( String addField2 ) {
        this.addField2 = addField2;
    }
    
    public String getUseYN() {
        return useYN;
    }
    
    public void setUseYN( String useYN ) {
        this.useYN = useYN;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription( String description ) {
        this.description = description;
    }
    
    public String getDisplayOrder() {
        return displayOrder;
    }
    
    public void setDisplayOrder( String displayOrder ) {
        this.displayOrder = displayOrder;
    }
    
    public void setTags(String tags) {
    	StringTokenizer	stk;
    	
    	if (tags == null) {
    		this.tags	= null;
    		return;
    	}
    	
    	stk	= new StringTokenizer(tags, ",");
    	this.tags	= new String[stk.countTokens()];
    	for (int i=0; i<this.tags.length; i++) {
    		this.tags[i] = stk.nextToken();
    	}
    }
    
    /**
     * 주어진 태그 패턴에 유효한 코드인지 검사 합니다.
     * @param filter 와일드 카드 *나 ?를 사용한 문자열 패턴
     * @return 유효하면 true
     */
    public boolean isAvailable(StringPattern filter) {
    	if (this.tags != null) {
        	for (int i=0; i<this.tags.length; i++) {
        		if (filter.isMatch(this.tags[i])) {
        			return true;
        		}
        	}
    	}
		return false;
    }
    
    /**
     * 주어진 태그 패턴에 유효한 코드인지 검사 합니다.
     * @param filterPattern 와일드 카드 *나 ?를 사용한 문자열 패턴
     * @return 유효하면 true
     */
    public boolean isAvailable(String filterPattern) {
    	return this.isAvailable(new StringPattern(filterPattern));
    }
    
}
