package com.omp.admin.member.membermgr.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;


@SuppressWarnings("serial")
public class PurchaseList extends CommonModel implements Pagenateable, TotalCountCarriable {
	private String rnum;
    private String aid;
    private String prodNm;
    private String prchsDt;
    private String prchsTm;
    private String prchsAmt;
    private String prodDesc;
    private String payCls;
    private String prchsCnclDtm;
    private String prchsId;
    private String dwnldStat;
    private String rtCbyn;
    private String mbrId;
    private String mbrNo;
    private String oreRtNo;
    private String devMbrId;
    private String prchsStat;
    private String cancelYn;
    private String applNum;
    private String popupYn;
    private String hpno;
    private String addr;
    
    private PagenateInfoModel page = new PagenateInfoModel(10);
    private Long totalCount;
    
    @Override
	public PagenateInfoModel getPage() {
		return this.page;
	}
	
	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getPrchsDt() {
		return prchsDt;
	}

	public void setPrchsDt(String prchsDt) {
		this.prchsDt = prchsDt;
	}

	/**
	 * @return the prchsTm
	 */
	public String getPrchsTm() {
		return prchsTm;
	}

	/**
	 * @param prchsTm the prchsTm to set
	 */
	public void setPrchsTm(String prchsTm) {
		this.prchsTm = prchsTm;
	}

	public String getPrchsAmt() {
		return prchsAmt;
	}

	public void setPrchsAmt(String prchsAmt) {
		this.prchsAmt = prchsAmt;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getPayCls() {
		return payCls;
	}

	public void setPayCls(String payCls) {
		this.payCls = payCls;
	}

	public String getPrchsCnclDtm() {
		return prchsCnclDtm;
	}

	public void setPrchsCnclDtm(String prchsCnclDtm) {
		this.prchsCnclDtm = prchsCnclDtm;
	}

	public String getPrchsId() {
		return prchsId;
	}

	public void setPrchsId(String prchsId) {
		this.prchsId = prchsId;
	}

	public String getDwnldStat() {
		return dwnldStat;
	}

	public void setDwnldStat(String dwnldStat) {
		this.dwnldStat = dwnldStat;
	}

	public String getRtCbyn() {
		return rtCbyn;
	}

	public void setRtCbyn(String rtCbyn) {
		this.rtCbyn = rtCbyn;
	} 
	
	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getMbrNo() {
		return mbrNo;
	}

	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}

	public String getOreRtNo() {
		return oreRtNo;
	}

	public void setOreRtNo(String oreRtNo) {
		this.oreRtNo = oreRtNo;
	}

	public String getDevMbrId() {
		return devMbrId;
	}

	public void setDevMbrId(String devMbrId) {
		this.devMbrId = devMbrId;
	}

	public String getPrchsStat() {
		return prchsStat;
	}

	public void setPrchsStat(String prchsStat) {
		this.prchsStat = prchsStat;
	}

	/**
	 * @return the cancelYn
	 */
	public String getCancelYn() {
		return cancelYn;
	}

	/**
	 * @param cancelYn the cancelYn to set
	 */
	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}

	/**
	 * @return the applNum
	 */
	public String getApplNum() {
		return applNum;
	}

	/**
	 * @param applNum the applNum to set
	 */
	public void setApplNum(String applNum) {
		this.applNum = applNum;
	}

	/**
	 * @return the popupYn
	 */
	public String getPopupYn() {
		return popupYn;
	}

	/**
	 * @param popupYn the popupYn to set
	 */
	public void setPopupYn(String popupYn) {
		if(popupYn == null) popupYn = "N";
		
		this.popupYn = popupYn;
	}

	/**
	 * @return the hpno
	 */
	public String getHpno() {
		return hpno;
	}

	/**
	 * @param hpno the hpno to set
	 */
	public void setHpno(String hpno) {
		this.hpno = hpno;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Long getTotalCount() {
		return totalCount;
	}
	
	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}

	@Override
    public String toString() { 
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
    }
}
