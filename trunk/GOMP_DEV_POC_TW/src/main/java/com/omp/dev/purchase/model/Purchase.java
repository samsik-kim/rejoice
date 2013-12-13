package com.omp.dev.purchase.model;

public class Purchase {
	
	//request medel
	private String usPayYn="";				//결제 종류여부  유료회원 연회비 결제 인경우 'Y', 상품결제인경우 'N'
	private String mbrNo="";        		//회원번호
	private String prodId="";       		//상품ID
	private String corpProdNo="";       	//업체상품번호
	private String prodNm = "";         	//상품명
	private String prchsCls="";				//구매경로(웹,S/C)
	private String payCls="";				//결제수단(가입비 결제는 카드만(OR000601))
	private String gdid = "";       		//사용자단말식별키
	private int dcAmt=0;					//할인금액
	private int prcAmt=0;					//상품가격(결제 요청금액 결제 수단 가격 + 사용캐쉬 금액)
	private String prc = "";                //결제가격(신용카드및 폰결제 결제 가격)
	private String cashAmt = "0";   		//캐쉬결제금액
	private String cashAplyYn = "N";   		//캐쉬적용여부
	private String telecom;					//결제이통사
	private String phoneNumber;				//결제핸드폰번호
	private String cardexpy;				//카드 유효기간년도
	private String cardexpm;				//카드 유효기간월
	private String cardNum="";         		//카드번호  
	private String cardInterest="0";      	//카드할부여부(0)
	private String cardQuota="0";   		//카드할부개월수
	private String cardext="";				//카드 CVC
	
	//response medel
	private String rtnCd = "";			//0000:성공 , 0000이외의 값
	private String rtnMsg = "";
	
	//procession id
	private String prchsId = "";        //구매ID
	private String cashId;
	private String pointId;
	private int reserveCashAmt;
	private int reservePointAmt;
	private int totTcashAvailAmt;				
	private int cashAvailAmt;
	private int pointAvailAmt;
	private int availAmt;
	private int amt;
	private String tid=""; 
	private String totprice="";			//결제 수단으로 결제된 금액
    private String mbrId = "";          //회원아이디(req)
    private String hpNo="";				//핸드폰번호(member)
    private String phoneModelCd="";		//핸드폰모델코드(member)
    private String prchsDt="";			//구매일자
    private String prchsTm="";			//구매일자
    private String prchsStat="";		//
    private int prchsAmt=0;
    private String prchsCnclDtm="";
    private String regId="";
    private String regDt="";
    private String updtId="";
    private String updtDt="";
    private String prchsProdNum="";		//1
    private String dpCatNo="";			//전시카테고리
	private String dwnldStat="";	
	private String dwnldExpDt="";
	private String delYn="";
	private String moid="";               
	private String appldate="";           
	private String appltime="";           
	private String applnum="";            
	private String payHpNo="";
	private String cardResultCode;
	private String rtYearterm="";         
	private String occrNo="";             
	private String rtPrfix="";            
	private String rtNo="";               
	private String useYn="";              
	private String rtCbyn=""; 
	private String rtprefix_rtno="";
	private String rt_start_no="";        
	private String rt_end_no="";          
	private int rt_totl_cnt=0;        
	private int rt_use_cnt=0;
	private String mbrNm="";
	private String cashEnableYn = "Y"; 			//캐쉬사용가능여부
	private String prodAmt = "0";      			//상품금액
    private String prodOrginalAmt = "0"; 		//원 상품금액
	private String cashAplyAmt="";				
	private String upCashId;
	private int upAvailAmt;
	private int bfAvailAmt;
	private int afAvailAmt;
	private int availCashAmt;
	private String upPointId;
	private int availPointAmt;
	private int paySeq;
	
	public String getCorpProdNo() {
		return corpProdNo;
	}
	public void setCorpProdNo(String corpProdNo) {
		this.corpProdNo = corpProdNo;
	}
	public String getCardext() {
		return cardext;
	}
	public void setCardext(String cardext) {
		this.cardext = cardext;
	}
	
	/**
	 * @return the paySeq
	 */
	public int getPaySeq() {
		return paySeq;
	}
	/**
	 * @param paySeq the paySeq to set
	 */
	public void setPaySeq(int paySeq) {
		this.paySeq = paySeq;
	}
	public int getAvailPointAmt() {
		return availPointAmt;
	}
	public void setAvailPointAmt(int availPointAmt) {
		this.availPointAmt = availPointAmt;
	}
	public String getUpPointId() {
		return upPointId;
	}
	public void setUpPointId(String upPointId) {
		this.upPointId = upPointId;
	}
	public int getAvailCashAmt() {
		return availCashAmt;
	}
	public void setAvailCashAmt(int availCashAmt) {
		this.availCashAmt = availCashAmt;
	}
	public int getBfAvailAmt() {
		return bfAvailAmt;
	}
	public void setBfAvailAmt(int bfAvailAmt) {
		this.bfAvailAmt = bfAvailAmt;
	}
	public int getAfAvailAmt() {
		return afAvailAmt;
	}
	public void setAfAvailAmt(int afAvailAmt) {
		this.afAvailAmt = afAvailAmt;
	}
	public int getUpAvailAmt() {
		return upAvailAmt;
	}
	public void setUpAvailAmt(int upAvailAmt) {
		this.upAvailAmt = upAvailAmt;
	}
	public String getUpCashId() {
		return upCashId;
	}
	public void setUpCashId(String upCashId) {
		this.upCashId = upCashId;
	}
	
	public String getTelecom() {
		return telecom;
	}
	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getReservePointAmt() {
		return reservePointAmt;
	}
	public void setReservePointAmt(int reservePointAmt) {
		this.reservePointAmt = reservePointAmt;
	}
	public String getPointId() {
		return pointId;
	}
	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	public int getReserveCashAmt() {
		return reserveCashAmt;
	}
	public void setReserveCashAmt(int reserveCashAmt) {
		this.reserveCashAmt = reserveCashAmt;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public int getTotTcashAvailAmt() {
		return totTcashAvailAmt;
	}
	public void setTotTcashAvailAmt(int totTcashAvailAmt) {
		this.totTcashAvailAmt = totTcashAvailAmt;
	}
	public int getAvailAmt() {
		return availAmt;
	}
	public void setAvailAmt(int availAmt) {
		this.availAmt = availAmt;
	}
	public int getCashAvailAmt() {
		return cashAvailAmt;
	}
	public void setCashAvailAmt(int cashAvailAmt) {
		this.cashAvailAmt = cashAvailAmt;
	}
	public int getPointAvailAmt() {
		return pointAvailAmt;
	}
	public void setPointAvailAmt(int pointAvailAmt) {
		this.pointAvailAmt = pointAvailAmt;
	}
	public String getRtprefix_rtno() {
		return rtprefix_rtno;
	}
	public void setRtprefix_rtno(String rtprefix_rtno) {
		this.rtprefix_rtno = rtprefix_rtno;
	}	
	public String getCardResultCode() {
		return cardResultCode;
	}
	public void setCardResultCode(String cardResultCode) {
		this.cardResultCode = cardResultCode;
	}
	public String getCardexpy() {
		return cardexpy;
	}
	public void setCardexpy(String cardexpy) {
		this.cardexpy = cardexpy;
	}
	public String getCardexpm() {
		return cardexpm;
	}
	public void setCardexpm(String cardexpm) {
		this.cardexpm = cardexpm;
	}
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	public String getUsPayYn() {
		return usPayYn;
	}
	public void setUsPayYn(String usPayYn) {
		this.usPayYn = usPayYn;
	}
	public String getRtnCd() {
		return rtnCd;
	}
	public void setRtnCd(String rtnCd) {
		this.rtnCd = rtnCd;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
	public String getPrchsId() {
		return prchsId;
	}
	public void setPrchsId(String prchsId) {
		this.prchsId = prchsId;
	}
	public String getMbrNo() {
		return mbrNo;
	}
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getGdid() {
		return gdid;
	}
	public void setGdid(String gdid) {
		this.gdid = gdid;
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getPhoneModelCd() {
		return phoneModelCd;
	}
	public void setPhoneModelCd(String phoneModelCd) {
		this.phoneModelCd = phoneModelCd;
	}
	public String getPrchsDt() {
		return prchsDt;
	}
	public void setPrchsDt(String prchsDt) {
		this.prchsDt = prchsDt;
	}
	public String getPrchsTm() {
		return prchsTm;
	}
	public void setPrchsTm(String prchsTm) {
		this.prchsTm = prchsTm;
	}
	public String getPrchsStat() {
		return prchsStat;
	}
	public void setPrchsStat(String prchsStat) {
		this.prchsStat = prchsStat;
	}
	public int getPrchsAmt() {
		return prchsAmt;
	}
	public void setPrchsAmt(int prchsAmt) {
		this.prchsAmt = prchsAmt;
	}
	public String getPrchsCls() {
		return prchsCls;
	}
	public void setPrchsCls(String prchsCls) {
		this.prchsCls = prchsCls;
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
	public int getDcAmt() {
		return dcAmt;
	}
	public void setDcAmt(int dcAmt) {
		this.dcAmt = dcAmt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdtId() {
		return updtId;
	}
	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
	public String getUpdtDt() {
		return updtDt;
	}
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	public String getPrchsProdNum() {
		return prchsProdNum;
	}
	public void setPrchsProdNum(String prchsProdNum) {
		this.prchsProdNum = prchsProdNum;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getDpCatNo() {
		return dpCatNo;
	}
	public void setDpCatNo(String dpCatNo) {
		this.dpCatNo = dpCatNo;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public int getPrcAmt() {
		return prcAmt;
	}
	public void setPrcAmt(int prcAmt) {
		this.prcAmt = prcAmt;
	}
	public String getDwnldStat() {
		return dwnldStat;
	}
	public void setDwnldStat(String dwnldStat) {
		this.dwnldStat = dwnldStat;
	}
	public String getDwnldExpDt() {
		return dwnldExpDt;
	}
	public void setDwnldExpDt(String dwnldExpDt) {
		this.dwnldExpDt = dwnldExpDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getMoid() {
		return moid;
	}
	public void setMoid(String moid) {
		this.moid = moid;
	}
	public String getAppldate() {
		return appldate;
	}
	public void setAppldate(String appldate) {
		this.appldate = appldate;
	}
	public String getAppltime() {
		return appltime;
	}
	public void setAppltime(String appltime) {
		this.appltime = appltime;
	}
	public String getApplnum() {
		return applnum;
	}
	public void setApplnum(String applnum) {
		this.applnum = applnum;
	}
	public String getTotprice() {
		return totprice;
	}
	public void setTotprice(String totprice) {
		this.totprice = totprice;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardInterest() {
		return cardInterest;
	}
	public void setCardInterest(String cardInterest) {
		this.cardInterest = cardInterest;
	}
	public String getCardQuota() {
		return cardQuota;
	}
	public void setCardQuota(String cardQuota) {
		this.cardQuota = cardQuota;
	}
	public String getPayHpNo() {
		return payHpNo;
	}
	public void setPayHpNo(String payHpNo) {
		this.payHpNo = payHpNo;
	}
	public String getRtYearterm() {
		return rtYearterm;
	}
	public void setRtYearterm(String rtYearterm) {
		this.rtYearterm = rtYearterm;
	}
	public String getOccrNo() {
		return occrNo;
	}
	public void setOccrNo(String occrNo) {
		this.occrNo = occrNo;
	}
	public String getRtPrfix() {
		return rtPrfix;
	}
	public void setRtPrfix(String rtPrfix) {
		this.rtPrfix = rtPrfix;
	}
	public String getRtNo() {
		return rtNo;
	}
	public void setRtNo(String rtNo) {
		this.rtNo = rtNo;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRtCbyn() {
		return rtCbyn;
	}
	public void setRtCbyn(String rtCbyn) {
		this.rtCbyn = rtCbyn;
	}
	public String getRt_start_no() {
		return rt_start_no;
	}
	public void setRt_start_no(String rt_start_no) {
		this.rt_start_no = rt_start_no;
	}
	public String getRt_end_no() {
		return rt_end_no;
	}
	public void setRt_end_no(String rt_end_no) {
		this.rt_end_no = rt_end_no;
	}
	public int getRt_totl_cnt() {
		return rt_totl_cnt;
	}
	public void setRt_totl_cnt(int rt_totl_cnt) {
		this.rt_totl_cnt = rt_totl_cnt;
	}
	public int getRt_use_cnt() {
		return rt_use_cnt;
	}
	public void setRt_use_cnt(int rt_use_cnt) {
		this.rt_use_cnt = rt_use_cnt;
	}
	public String getCashAplyYn() {
		return cashAplyYn;
	}
	public void setCashAplyYn(String cashAplyYn) {
		this.cashAplyYn = cashAplyYn;
	}
	public String getCashEnableYn() {
		return cashEnableYn;
	}
	public void setCashEnableYn(String cashEnableYn) {
		this.cashEnableYn = cashEnableYn;
	}
	public String getProdAmt() {
		return prodAmt;
	}
	public void setProdAmt(String prodAmt) {
		this.prodAmt = prodAmt;
	}
	public String getProdOrginalAmt() {
		return prodOrginalAmt;
	}
	public void setProdOrginalAmt(String prodOrginalAmt) {
		this.prodOrginalAmt = prodOrginalAmt;
	}
	public String getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(String cashAmt) {
		this.cashAmt = cashAmt;
	}
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getCashAplyAmt() {
		return cashAplyAmt;
	}
	public void setCashAplyAmt(String cashAplyAmt) {
		this.cashAplyAmt = cashAplyAmt;
	}         
	
}
