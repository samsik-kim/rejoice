package com.omp.admin.member.membermgr.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

@SuppressWarnings("serial")
public class UserMemberMgr extends CommonModel implements Pagenateable, TotalCountCarriable {
	
	private String rnum;
    private String mbrno;
    private String mdnno;
    private String mbrclscd;
    private String mbrcatcd;
    private String devmbrstatcd;
    private String uptdevmbrstatcd;
    private String mbrstatcd;
    private String mbrstatregdt;
    private String mobilecnt;
    private String mbrstartdt;
    private String mbrenddt;
    private String mbrapprdt;
    private String mbrid;
    private String emailaddr;
    private String realnmauthyn;
    private String startdt;
    private String enddt;
    private String mbrnm;
    private String psregno;
    private String gdid;
    private String mbrgrcd;
    private String payTransDt;
    private String telno;
    private String hpno;
    private String newsyn;
    private String regid;
    private String transinfo;
    private String transno;
    private String oldtransno;
    private String rejreason;
    private String mbrpw;

    private String birthdt;
    private String sex;
    private String zipcd;
    private String homeaddr;
    private String homeaddrdtl;
    private String emailyn;
    private String opnm;
    private String opemailaddr;
    private String optelno;
    private String ophpno;
    private String acctno;
    private String backcd;
    private String acctnm;
    private String compnm;
    private String filepath;
    private String filenm;
    private String filesize;
    private String webSiteUrl;
    private String city;
    private String backnm;
    private String backbranchnm;
    private String backbranchcd;
    private String currencyunit;
    private String backglcd;
    private String backgltype;
    private String nanm;
    private String bizcatcd;
    private String bizcatnm;
    private String acctcertyn;
    private String accdts;
    private String accipaddr;
    private String acctfilenm;
    private String acctfilepath;
    private String idfilenm;
    private String idfilepath;
    private String acctdocnm;
    private String iddocnm;
    private String acctdoccd;
    private String iddoccd;
    private String dochmcd;
    
    private String payStartDt;
    private String payEndDt;

    private String freedevstartdt;
    private String freedevreqdt;
    private String freedevenddt;
    private String freedevwithdrawcd;
    private String freedevwithdrawtxt;
    private String paydevstartdt;
    private String paydevreqdt;
    private String paydevwithdrawcd;
    private String paydevwithdrawtxt;
    private String paydevenddt;

    private String regdts;
    private String uptDt;

    private String modelnm;
    private String scver;

    private String type;
    private String tempEmailAddr;
    
    private PagenateInfoModel	page		= new PagenateInfoModel(10);
    private Long				totalCount;

	@Override
	public PagenateInfoModel getPage() {
		return this.page;
	}

	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getMbrno() {
		return mbrno;
	}

	public void setMbrno(String mbrno) {
		this.mbrno = mbrno;
	}

	public String getMdnno() {
		return mdnno;
	}

	public void setMdnno(String mdnno) {
		this.mdnno = mdnno;
	}

	public String getMbrclscd() {
		return mbrclscd;
	}

	public void setMbrclscd(String mbrclscd) {
		this.mbrclscd = mbrclscd;
	}

	public String getMbrcatcd() {
		return mbrcatcd;
	}

	public void setMbrcatcd(String mbrcatcd) {
		this.mbrcatcd = mbrcatcd;
	}

	public String getDevmbrstatcd() {
		return devmbrstatcd;
	}

	public void setDevmbrstatcd(String devmbrstatcd) {
		this.devmbrstatcd = devmbrstatcd;
	}

	public String getMbrstatcd() {
		return mbrstatcd;
	}

	public void setMbrstatcd(String mbrstatcd) {
		this.mbrstatcd = mbrstatcd;
	}

	public String getMobilecnt() {
		return mobilecnt;
	}

	public void setMobilecnt(String mobilecnt) {
		this.mobilecnt = mobilecnt;
	}

	public String getMbrstartdt() {
		return mbrstartdt;
	}

	public void setMbrstartdt(String mbrstartdt) {
		this.mbrstartdt = mbrstartdt;
	}

	public String getMbrenddt() {
		return mbrenddt;
	}

	public void setMbrenddt(String mbrenddt) {
		this.mbrenddt = mbrenddt;
	}

	public String getMbrapprdt() {
		return mbrapprdt;
	}

	public void setMbrapprdt(String mbrapprdt) {
		this.mbrapprdt = mbrapprdt;
	}

	public String getMbrid() {
		return mbrid;
	}

	public void setMbrid(String mbrid) {
		this.mbrid = mbrid;
	}

	public String getEmailaddr() {
		return emailaddr;
	}

	public void setEmailaddr(String emailaddr) {
		this.emailaddr = emailaddr;
	}

	public String getRealnmauthyn() {
		return realnmauthyn;
	}

	public void setRealnmauthyn(String realnmauthyn) {
		this.realnmauthyn = realnmauthyn;
	}

	public String getStartdt() {
		return startdt;
	}

	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}

	public String getEnddt() {
		return enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	public String getMbrnm() {
		return mbrnm;
	}

	public void setMbrnm(String mbrnm) {
		this.mbrnm = mbrnm;
	}

	public String getBirthdt() {
		return birthdt;
	}

	public void setBirthdt(String birthdt) {
		this.birthdt = birthdt;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getZipcd() {
		return zipcd;
	}

	public void setZipcd(String zipcd) {
		this.zipcd = zipcd;
	}

	public String getHomeaddr() {
		return homeaddr;
	}

	public void setHomeaddr(String homeaddr) {
		this.homeaddr = homeaddr;
	}

	public String getHomeaddrdtl() {
		return homeaddrdtl;
	}

	public void setHomeaddrdtl(String homeaddrdtl) {
		this.homeaddrdtl = homeaddrdtl;
	}

	public String getEmailyn() {
		return emailyn;
	}

	public void setEmailyn(String emailyn) {
		this.emailyn = emailyn;
	}

	public String getOpnm() {
		return opnm;
	}

	public void setOpnm(String opnm) {
		this.opnm = opnm;
	}

	public String getOpemailaddr() {
		return opemailaddr;
	}

	public void setOpemailaddr(String opemailaddr) {
		this.opemailaddr = opemailaddr;
	}

	public String getOptelno() {
		return optelno;
	}

	public void setOptelno(String optelno) {
		this.optelno = optelno;
	}

	public String getOphpno() {
		return ophpno;
	}

	public void setOphpno(String ophpno) {
		this.ophpno = ophpno;
	}

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}

	public String getBackcd() {
		return backcd;
	}

	public void setBackcd(String backcd) {
		this.backcd = backcd;
	}

	public String getAcctnm() {
		return acctnm;
	}

	public void setAcctnm(String acctnm) {
		this.acctnm = acctnm;
	}

	public String getCompnm() {
		return compnm;
	}

	public void setCompnm(String compnm) {
		this.compnm = compnm;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilenm() {
		return filenm;
	}

	public void setFilenm(String filenm) {
		this.filenm = filenm;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFreedevstartdt() {
		return freedevstartdt;
	}

	public void setFreedevstartdt(String freedevstartdt) {
		this.freedevstartdt = freedevstartdt;
	}

	public String getFreedevenddt() {
		return freedevenddt;
	}

	public void setFreedevenddt(String freedevenddt) {
		this.freedevenddt = freedevenddt;
	}

	public String getPaydevstartdt() {
		return paydevstartdt;
	}

	public void setPaydevstartdt(String paydevstartdt) {
		this.paydevstartdt = paydevstartdt;
	}

	public String getPaydevenddt() {
		return paydevenddt;
	}

	public void setPaydevenddt(String paydevenddt) {
		this.paydevenddt = paydevenddt;
	}

	public String getRegdts() {
		return regdts;
	}

	public void setRegdts(String regdts) {
		this.regdts = regdts;
	}

	public String getPsregno() {
		return psregno;
	}

	public void setPsregno(String psregno) {
		this.psregno = psregno;
	}

	public String getGdid() {
		return gdid;
	}

	public void setGdid(String gdid) {
		this.gdid = gdid;
	}

	public String getMbrgrcd() {
		return mbrgrcd;
	}

	public void setMbrgrcd(String mbrgrcd) {
		this.mbrgrcd = mbrgrcd;
	}


	public String getPayStartDt() {
		return payStartDt;
	}

	public void setPayStartDt(String payStartDt) {
		this.payStartDt = payStartDt;
	}

	public String getPayEndDt() {
		return payEndDt;
	}

	public void setPayEndDt(String payEndDt) {
		this.payEndDt = payEndDt;
	}

	public String getPayTransDt() {
		return payTransDt;
	}

	public void setPayTransDt(String payTransDt) {
		this.payTransDt = payTransDt;
	}

	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHpno() {
		return hpno;
	}

	public void setHpno(String hpno) {
		this.hpno = hpno;
	}

	public String getBacknm() {
		return backnm;
	}

	public void setBacknm(String backnm) {
		this.backnm = backnm;
	}

	public String getBackbranchnm() {
		return backbranchnm;
	}

	public void setBackbranchnm(String backbranchnm) {
		this.backbranchnm = backbranchnm;
	}

	public String getBackbranchcd() {
		return backbranchcd;
	}

	public void setBackbranchcd(String backbranchcd) {
		this.backbranchcd = backbranchcd;
	}

	public String getCurrencyunit() {
		return currencyunit;
	}

	public void setCurrencyunit(String currencyunit) {
		this.currencyunit = currencyunit;
	}

	public String getBackglcd() {
		return backglcd;
	}

	public void setBackglcd(String backglcd) {
		this.backglcd = backglcd;
	}

	public String getNanm() {
		return nanm;
	}

	public void setNanm(String nanm) {
		this.nanm = nanm;
	}

	public String getBizcatnm() {
		return bizcatnm;
	}

	public void setBizcatnm(String bizcatnm) {
		this.bizcatnm = bizcatnm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNewsyn() {
		return newsyn;
	}

	public void setNewsyn(String newsyn) {
		this.newsyn = newsyn;
	}

	public String getFreedevreqdt() {
		return freedevreqdt;
	}

	public void setFreedevreqdt(String freedevreqdt) {
		this.freedevreqdt = freedevreqdt;
	}

	public String getFreedevwithdrawcd() {
		return freedevwithdrawcd;
	}

	public void setFreedevwithdrawcd(String freedevwithdrawcd) {
		this.freedevwithdrawcd = freedevwithdrawcd;
	}

	public String getFreedevwithdrawtxt() {
		return freedevwithdrawtxt;
	}

	public void setFreedevwithdrawtxt(String freedevwithdrawtxt) {
		this.freedevwithdrawtxt = freedevwithdrawtxt;
	}

	public String getPaydevreqdt() {
		return paydevreqdt;
	}

	public void setPaydevreqdt(String paydevreqdt) {
		this.paydevreqdt = paydevreqdt;
	}

	public String getPaydevwithdrawcd() {
		return paydevwithdrawcd;
	}

	public void setPaydevwithdrawcd(String paydevwithdrawcd) {
		this.paydevwithdrawcd = paydevwithdrawcd;
	}

	public String getPaydevwithdrawtxt() {
		return paydevwithdrawtxt;
	}

	public void setPaydevwithdrawtxt(String paydevwithdrawtxt) {
		this.paydevwithdrawtxt = paydevwithdrawtxt;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getUptdevmbrstatcd() {
		return uptdevmbrstatcd;
	}

	public void setUptdevmbrstatcd(String uptdevmbrstatcd) {
		this.uptdevmbrstatcd = uptdevmbrstatcd;
	}

	public String getTransinfo() {
		return transinfo;
	}

	public void setTransinfo(String transinfo) {
		this.transinfo = transinfo;
	}

	public String getTransno() {
		return transno;
	}

	public void setTransno(String transno) {
		this.transno = transno;
	}

	public String getMbrstatregdt() {
		return mbrstatregdt;
	}

	public void setMbrstatregdt(String mbrstatregdt) {
		this.mbrstatregdt = mbrstatregdt;
	}

	public String getRejreason() {
		return rejreason;
	}

	public void setRejreason(String rejreason) {
		 String str = "";
		 try{
			 str = rejreason.replaceAll("\n\r", "<BR>");
			 str = rejreason.replaceAll("\n", "<BR>");
		 }catch(Exception ex){
		   // Skip
		 }
		 
		this.rejreason = str;
	}

	public String getAcctcertyn() {
		return acctcertyn;
	}

	public void setAcctcertyn(String acctcertyn) {
		this.acctcertyn = acctcertyn;
	}

	public String getOldtransno() {
		return oldtransno;
	}

	public void setOldtransno(String oldtransno) {
		this.oldtransno = oldtransno;
	}

	public String getAccdts() {
		return accdts;
	}

	public void setAccdts(String accdts) {
		this.accdts = accdts;
	}

	public String getAccipaddr() {
		return accipaddr;
	}

	public void setAccipaddr(String accipaddr) {
		this.accipaddr = accipaddr;
	}

	public String getBizcatcd() {
		return bizcatcd;
	}

	public void setBizcatcd(String bizcatcd) {
		this.bizcatcd = bizcatcd;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getAcctfilenm() {
		return acctfilenm;
	}

	public void setAcctfilenm(String acctfilenm) {
		this.acctfilenm = acctfilenm;
	}

	public String getAcctfilepath() {
		return acctfilepath;
	}

	public void setAcctfilepath(String acctfilepath) {
		this.acctfilepath = acctfilepath;
	}

	public String getIdfilenm() {
		return idfilenm;
	}

	public void setIdfilenm(String idfilenm) {
		this.idfilenm = idfilenm;
	}

	public String getIdfilepath() {
		return idfilepath;
	}

	public void setIdfilepath(String idfilepath) {
		this.idfilepath = idfilepath;
	}

	public String getAcctdocnm() {
		return acctdocnm;
	}

	public void setAcctdocnm(String acctdocnm) {
		this.acctdocnm = acctdocnm;
	}

	public String getIddocnm() {
		return iddocnm;
	}

	public void setIddocnm(String iddocnm) {
		this.iddocnm = iddocnm;
	}

	public String getDochmcd() {
		return dochmcd;
	}

	public void setDochmcd(String dochmcd) {
		this.dochmcd = dochmcd;
	}

	public String getAcctdoccd() {
		return acctdoccd;
	}

	public void setAcctdoccd(String acctdoccd) {
		this.acctdoccd = acctdoccd;
	}

	public String getIddoccd() {
		return iddoccd;
	}

	public void setIddoccd(String iddoccd) {
		this.iddoccd = iddoccd;
	}

	/**
	 * @return the tempEamilAddr
	 */
	public String getTempEmailAddr() {
		return tempEmailAddr;
	}

	/**
	 * @param tempEamilAddr the tempEamilAddr to set
	 */
	public void setTempEmailAddr(String tempEmailAddr) {
		this.tempEmailAddr = tempEmailAddr;
	}

	/**
	 * @return the mbrpw
	 */
	public String getMbrpw() {
		return mbrpw;
	}

	/**
	 * @param mbrpw the mbrpw to set
	 */
	public void setMbrpw(String mbrpw) {
		this.mbrpw = mbrpw;
	}

	/**
	 * @return the modelnm
	 */
	public String getModelnm() {
		return modelnm;
	}

	/**
	 * @param modelnm the modelnm to set
	 */
	public void setModelnm(String modelnm) {
		this.modelnm = modelnm;
	}

	/**
	 * @return the scver
	 */
	public String getScver() {
		return scver;
	}

	/**
	 * @param scver the scver to set
	 */
	public void setScver(String scver) {
		this.scver = scver;
	}

	/**
	 * @return the backgltype
	 */
	public String getBackgltype() {
		return backgltype;
	}

	/**
	 * @param backgltype the backgltype to set
	 */
	public void setBackgltype(String backgltype) {
		this.backgltype = backgltype;
	}

	/**
	 * @return the uptDt
	 */
	public String getUptDt() {
		return uptDt;
	}

	/**
	 * @param uptDt the uptDt to set
	 */
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}
}
