package com.omp.admin.certify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <pre>
 * Cerfify Target Test Case
 * </pre>
 * 
 * 
 * @author shsin
 *
 */
@SuppressWarnings("serial")
public class CerfifyTestCategory implements Serializable{

	/**
	 * Application CID
	 */		
	private String cid;
	
	/**
	 * Certification Requested Version
	 */		
	private String verifyReqVer;
	
	/**
	 * Certification inserted By
	 */
	private String insBy;
	
	/**
	 * Certification inserted date
	 */	
	private String insDttm;
	
	
	/**
	 * Certification Category Seq
	 */
	private String[] ctCtgSeqs;
	
	
	//////////////////////////////////////////////////////////////////////////////////
	
    /**
     * @return the ctCtgSeqList
     */
	public List<Integer> getCtCtgSeqList() {
		List<Integer> ctCtgSeqList = null;
		
		if(ctCtgSeqs != null && ctCtgSeqs.length > 0){
			
			ctCtgSeqList = new ArrayList<Integer>();
			
			for (String ctCtgSeq : ctCtgSeqs) {
				ctCtgSeqList.add(new Integer(ctCtgSeq));
			}
		}

		
		return ctCtgSeqList;
	}	
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}	
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
    /**
     * @return the cid
     */
	public String getCid() {
		return cid;
	}
    /**
     * @param cid the cid to set
     */
	public void setCid(String cid) {
		this.cid = cid;
	}
    /**
     * @return the verifyReqVer
     */
	public String getVerifyReqVer() {
		return verifyReqVer;
	}
    /**
     * @param verifyReqVer the verifyReqVer to set
     */
	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

    /**
     * @return the insBy
     */
	public String getInsBy() {
		return insBy;
	}
    /**
     * @param insBy the insBy to set
     */
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
    /**
     * @return the insDttm
     */
	public String getInsDttm() {
		return insDttm;
	}
    /**
     * @param insDttm the insDttm to set
     */
	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}

	public String[] getCtCtgSeqs() {
		return ctCtgSeqs;
	}

	public void setCtCtgSeqs(String[] ctCtgSeqs) {
		this.ctCtgSeqs = ctCtgSeqs;
	}

}
