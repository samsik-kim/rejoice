package com.omp.dev.contents.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.omp.commons.model.PagenateInfoModel;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

@SuppressWarnings("serial")
public class ContentsVerify extends CommonModel implements Pagenateable, TotalCountCarriable {

	// TBL_CT_CONTS
	private String rnum;
	private String vmType;				// 플랫폼
	private String[] searchVmType;		// List에서 플랫폼 다중선택시 담을 변수 
	private String agrmntStat;			// 승인여부
	private String[] searchAgrmntStat;	// 승인여부 검색조건 다중선택시 담을 변수
	private String aid;                 // 상품 AID
	private String cid;                 // 상품 CID
	private String ctAssignDt;          // 검증 할당일자
	private String ctEndDt;             // 검증완료일자
	private String ctEndExDt;           // 검증완료예정일자
	private String directPassYn;        // 검증통과 여부
	private String gameDelibGrd;        // 등급
	private String insBy;               // 상품등록자
	private String insDttm; 			// 상품등록일자
	private String pid;                 // 상품PID
	private String prodNm;				// 상품명
	private String prodDescSmmr;		// 상품 요약설명
	private String testEndDt;           // 테스트 종료일자
	private String testMemo;            // 테스트 메모
	private String testRejectCmt;       // Test검증 반려 사유
	private String testerCtStat;        // Tester 진행상태
	private String testerId;            // Tester ID
	private String updBy;               // 수정자
	private String updDttm;             // 수정일자
	private String verifyCommentCd;     // 검증요청사유코드
	private String verifyEtcCmt;		// 검증요청사유 기타내용
	private String verifyPrgrYn;		// 검증진행상태
	private String[] searchVerifyPrgrYn;// 검증진행상태 검색조건 다중선택시 담을 변수
	private String verifyReqVer;        // 검증요청버젼
	private String verifyScnrFile;      // 검증시나리오 파일경로
	private String verifyScnrFileNm;	// 검증시나리오 파일명
	private String filePos;				// 상품이미지 경로
	private String scid;				// 서브컨텐츠 ID
	private String[] scidArray;			// 다중 SCID로 Search가 필요한경우가 있을때 사용용도 변수 
	
	private String ctgrCd;				// 카테고리 코드
	private String sprtPhoneCnt;		// 대상단말 수
	private String ctInsDttm;			// 검증요청일자
	
	private String verifyDetailTab;		// 검증상세페이지 탭 구분
	
	private List<SubContentsVerify> list;
	
	private PagenateInfoModel	page = new PagenateInfoModel();
    private Long	totalCount;
	
    @Override
	public PagenateInfoModel getPage() {
		return this.page;
	}

	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public Long setTotalCount(Long totalCount) {
		return this.totalCount	= totalCount;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String[] getSearchVmType() {
		return searchVmType;
	}

	public void setSearchVmType(String[] searchVmType) {
		this.searchVmType = searchVmType;
	}

	public String getAgrmntStat() {
		return agrmntStat;
	}

	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}

	public String[] getSearchAgrmntStat() {
		return searchAgrmntStat;
	}

	public void setSearchAgrmntStat(String[] searchAgrmntStat) {
		this.searchAgrmntStat = searchAgrmntStat;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCtAssignDt() {
		return ctAssignDt;
	}

	public void setCtAssignDt(String ctAssignDt) {
		this.ctAssignDt = ctAssignDt;
	}

	public String getCtEndDt() {
		return ctEndDt;
	}

	public void setCtEndDt(String ctEndDt) {
		this.ctEndDt = ctEndDt;
	}

	public String getCtEndExDt() {
		return ctEndExDt;
	}

	public void setCtEndExDt(String ctEndExDt) {
		this.ctEndExDt = ctEndExDt;
	}

	public String getDirectPassYn() {
		return directPassYn;
	}

	public void setDirectPassYn(String directPassYn) {
		this.directPassYn = directPassYn;
	}

	public String getGameDelibGrd() {
		return gameDelibGrd;
	}

	public void setGameDelibGrd(String gameDelibGrd) {
		this.gameDelibGrd = gameDelibGrd;
	}

	public String getInsBy() {
		return insBy;
	}

	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}

	public String getInsDttm() {
		return insDttm;
	}

	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getProdDescSmmr() {
		return prodDescSmmr;
	}

	public void setProdDescSmmr(String prodDescSmmr) {
		this.prodDescSmmr = prodDescSmmr;
	}

	public String getTestEndDt() {
		return testEndDt;
	}

	public void setTestEndDt(String testEndDt) {
		this.testEndDt = testEndDt;
	}

	public String getTestMemo() {
		return testMemo;
	}

	public void setTestMemo(String testMemo) {
		this.testMemo = testMemo;
	}

	public String getTestRejectCmt() {
		return testRejectCmt;
	}

	public void setTestRejectCmt(String testRejectCmt) {
		this.testRejectCmt = testRejectCmt;
	}

	public String getTesterCtStat() {
		return testerCtStat;
	}

	public void setTesterCtStat(String testerCtStat) {
		this.testerCtStat = testerCtStat;
	}

	public String getTesterId() {
		return testerId;
	}

	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getVerifyCommentCd() {
		return verifyCommentCd;
	}

	public void setVerifyCommentCd(String verifyCommentCd) {
		this.verifyCommentCd = verifyCommentCd;
	}

	public String getVerifyEtcCmt() {
		return verifyEtcCmt;
	}

	public void setVerifyEtcCmt(String verifyEtcCmt) {
		this.verifyEtcCmt = verifyEtcCmt;
	}

	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}

	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}

	public String[] getSearchVerifyPrgrYn() {
		return searchVerifyPrgrYn;
	}

	public void setSearchVerifyPrgrYn(String[] searchVerifyPrgrYn) {
		this.searchVerifyPrgrYn = searchVerifyPrgrYn;
	}

	public String getVerifyReqVer() {
		return verifyReqVer;
	}

	public void setVerifyReqVer(String verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}

	public String getVerifyScnrFile() {
		return verifyScnrFile;
	}

	public void setVerifyScnrFile(String verifyScnrFile) {
		this.verifyScnrFile = verifyScnrFile;
	}

	public String getVerifyScnrFileNm() {
		return verifyScnrFileNm;
	}

	public void setVerifyScnrFileNm(String verifyScnrFileNm) {
		this.verifyScnrFileNm = verifyScnrFileNm;
	}
	
	public String getFilePos() {
		return filePos;
	}

	public void setFilePos(String filePos) {
		this.filePos = filePos;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String[] getScidArray() {
		return scidArray;
	}

	public void setScidArray(String[] scidArray) {
		this.scidArray = scidArray;
	}

	public String getCtgrCd() {
		return ctgrCd;
	}

	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}

	public String getSprtPhoneCnt() {
		return sprtPhoneCnt;
	}

	public void setSprtPhoneCnt(String sprtPhoneCnt) {
		this.sprtPhoneCnt = sprtPhoneCnt;
	}

	public String getCtInsDttm() {
		return ctInsDttm;
	}

	public void setCtInsDttm(String ctInsDttm) {
		this.ctInsDttm = ctInsDttm;
	}

	public List<SubContentsVerify> getList() {
		return list;
	}

	public void setList(List<SubContentsVerify> list) {
		this.list = list;
	}

	public String getVerifyDetailTab() {
		return verifyDetailTab;
	}

	public void setVerifyDetailTab(String verifyDetailTab) {
		this.verifyDetailTab = verifyDetailTab;
	}

	@Override
    public String toString() { 
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
    } 
}
