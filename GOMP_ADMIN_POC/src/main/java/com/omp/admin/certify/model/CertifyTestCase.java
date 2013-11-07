package com.omp.admin.certify.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Certify Test Case
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CertifyTestCase implements Serializable{
	
	/**
	 * Test Case Sequence Number
	 */
	private String testCaseSeq;
	
	/**
	 * VM Type
	 */
	private String vmType;
	
	/**
	 * Certification Sequence Nubmer
	 */
	private String ctCtgSeq;
	
	/**
	 * Title name
	 */
	private String titleNm;
	
	/**
	 * Explain
	 */
	private String explain;
	
	/**
	 * Step File
	 */
	private String stepFile;
	
	/**
	 * Step File Name
	 */
	private String stepFileNm;
	
	/**
	 * Sequence Number
	 */
	private String seqNo;
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}	
	
	//////////////////////////////////////////////////////////////////////////////////	

    /**
     * @return the testCaseSeq
     */	
	public String getTestCaseSeq() {
		return testCaseSeq;
	}
    /**
     * @param testCaseSeq the testCaseSeq to set
     */
	public void setTestCaseSeq(String testCaseSeq) {
		this.testCaseSeq = testCaseSeq;
	}

    /**
     * @return the vmType
     */	
	public String getVmType() {
		return vmType;
	}
    /**
     * @param vmType the vmType to set
     */
	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

    /**
     * @return the ctCtgSeq
     */	
	public String getCtCtgSeq() {
		return ctCtgSeq;
	}
    /**
     * @param ctCtgSeq the ctCtgSeq to set
     */
	public void setCtCtgSeq(String ctCtgSeq) {
		this.ctCtgSeq = ctCtgSeq;
	}

    /**
     * @return the titleNm
     */	
	public String getTitleNm() {
		return titleNm;
	}
    /**
     * @param titleNm the titleNm to set
     */
	public void setTitleNm(String titleNm) {
		this.titleNm = titleNm;
	}
    /**
     * @return the explain
     */		
	public String getExplain() {
		return explain;
	}
    /**
     * @param explain the explain to set
     */	
	public void setExplain(String explain) {
		this.explain = explain;
	}
    /**
     * @return the stepFile
     */		
	public String getStepFile() {
		return stepFile;
	}
    /**
     * @param stepFile the stepFile to set
     */	
	public void setStepFile(String stepFile) {
		this.stepFile = stepFile;
	}
    /**
     * @return the stepFileNm
     */		
	public String getStepFileNm() {
		return stepFileNm;
	}
    /**
     * @param stepFileNm the stepFileNm to set
     */	
	public void setStepFileNm(String stepFileNm) {
		this.stepFileNm = stepFileNm;
	}
    /**
     * @return the seqNo
     */	
	public String getSeqNo() {
		return seqNo;
	}
    /**
     * @param seqNo the seqNo to set
     */	
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	
	
}
