<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function(){

		//처리결과 메세지가 존재하면 화면에 출력을 해준다.
		<c:if test="${not empty reportS.processMessage   }">
			alert("<gm:string value='${receiptS.processMessage}'/>");		
		</c:if>
	});
	
	/*
	$(function() {
		
		
		$("#usReceipt").click(function(event){
			event.preventDefault();
			alert('작업중(영수증확인중)');
		});
		
			
	}); */		
	function goPage(no) {
	    $("#no").val(no);
	    document.searchForm.action="settlementReport.omp";
	    document.searchForm.target="_self";
 		document.searchForm.submit();
	}
	
	//송금요청 
 	function goUpdateStat(saleYm){
 		document.searchForm.saleYm.value = saleYm;
		document.searchForm.action="settlementRequest.omp";
 		document.searchForm.target="_self";
 		document.searchForm.submit();
 	}
	
	
 	//외국인 이월 송금요청 
 	function goUpdateForeignStat(){
 		document.searchForm.action="foreignSettlementRequest.omp";
 		document.searchForm.target="_self";
 		document.searchForm.submit();
 	}
	
 	function goDetail(aggtStatCd,saleYm){
 		
 		var targetUrl;
 		if(aggtStatCd=="PD004107"){ //이월처리시
			targetUrl = "<c:url value="/settlement/report/popUpSettlementTransferInfo.omp"/>";
		}else if(aggtStatCd=="PD004123"){ //송금포기시
			targetUrl = "<c:url value="/settlement/report/popUpSettlementGiveUpInfo.omp"/>";
		}
		document.searchForm.saleYm.value = saleYm;
	
		settlement_popup_center('', 380, 320, 0, 0);
		document.searchForm.target = 'popup';
		document.searchForm.action = targetUrl;
		document.searchForm.submit();
		
		
	}
	
//]]>
</script>
    
		<s:form id="searchForm" name="searchForm" theme="simple" >
		<input type="hidden" name=""></input>
		<input type="hidden" id="no" name="reportS.page.no" value="${reportS.page.no }" />
		<input type="hidden" id="aggtStatCd" name="reportS.aggtStatCd"></input>
		<input type="hidden" id="saleYm" name="reportS.saleYm"></input>
		</s:form>
		<!-- Content Area S -->
		<div id="contents_area">	  
			<!-- Title Area S -->
			<div id="ctitle_area"> 
				<p class="history">Home &gt; 판매/정산 현황 <strong>&gt;</strong> <span>정산 리포트</span></p>
				<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_title03.gif' />" alt="정산 리포트" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->
			<div id="contents">
            
				<ul class="bult07 mar_b5">
					<li>정산 리포트는 전월 매출을 기준으로 매월 25일경에 제공됩니다. (공휴일, 주말 제외)</li>
					<li>정확한 송금을 위해 회원님의 계좌정보를 다시 한 번 확인해주세요.</li>
				</ul>
				<div class="slbox mar_b8">
					<dl class="dlist07 dlfrt01">
						<dt>예금주</dt>
						<dd>${report.acctNm}</dd>
					</dl>
					<dl class="dlist07">
						<dt>은행명</dt>
						<dd>${report.backNm}</dd>
					</dl>
					<dl class="dlist07">
						<dt>계좌번호</dt>
						<dd><g:text value="${report.acctNo}" format="L#####*******************" /></dd>
					</dl>
					<p><a href="<c:url value='/mypage/mypageIntro.omp?forwardAction=CALCULATE' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_account.gif' />" alt="계좌번호 수정하기" /></a></p>
				</div>
				<p class="txtr mar_b5">
					<a href="<c:url value="/fileSupport/fileDown.omp"><c:param name="bnsType" value="webcontent"/><c:param name="filePath" value="/settlement/TWDReceipt.jpg"/><c:param name="fileName" value="TWDReceipt.jpg"/></c:url>" id="twReceipt">
				   		<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_receipt_tw.gif' />" alt="Receipt Template (Taiwanese)" /></a>
				   	<a href="<c:url value="/fileSupport/fileDown.omp"><c:param name="bnsType" value="webcontent"/><c:param name="filePath" value="/settlement/TWDReceipt.jpg"/><c:param name="fileName" value="TWDReceipt.jpg"/></c:url>" id="usReceipt">
				   		<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_receipt_eng.gif' />" alt="Receipt Template (English)" /></a>
				</p>
				<div class="tstyleE">
					<table summary="정산 리포트">
						<caption>정산 리포트</caption>
						<colgroup>
							<col width="7%" />
							<col width="9%" />
							<col width="9%" />
							<col width="9%" />
							<col width="9%" />
							<col width="9%" />
							<col width="12%" />
							<col width="12%" />
							<col width="9%" />
							<col width="10%" />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit11.gif' />" alt="판매월" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit12.gif' />" alt="판매총액" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit13.gif' />" alt="조정금액" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit14.gif' />" alt="세전금액" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit15.gif' />" alt="배분총액(A)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit16.gif' />" alt="세금(B)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit17.gif' />" alt="실정산액(C=A+B)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit18.gif' />" alt="지불화폐(평균환율)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit19.gif' />" alt="송금액" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit20.gif' />" alt="상태" /></th>
								<th scope="col" class="btnone th01 brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit21.gif' />" alt="내역" /></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
							<c:when test="${totalCount eq 0 }">
							<tr>
								<td colspan="12" class="text_c">검색결과가 없습니다.</td>
							</tr>
							</c:when>
							<c:otherwise>
							<c:set var="foreAmt" value="${report.devBuAdjAmtSum}"/>
							<c:forEach items="${reportList }" var="salestat">
							<tr>
								<td><span class="tcor"><g:text value="${salestat.saleYm}" format="L####-##" /></span></td>
								<td><strong><g:text value="${salestat.totlPayAmt}" format="R###,###,###,###" /></strong></td>
								<td><g:text value="${salestat.adjAmt}" format="R###,###,###,###" /></td>
								<td><g:text value="${salestat.beforeTaxAmt}" format="R###,###,###,###" /></td>
								<td><g:text value="${salestat.devBuDvAmtSum}" format="R###,###,###,###" /></td>
								<td><g:text value="${salestat.txtAmt}" format="R###,###,###,###" /></td>
								<td><g:text value="${salestat.devBuAdjAmtSum}" format="R###,###,###,###" /></td>
								<td><gc:html code="${salestat.currencyUnit}"/></td>
								<td><g:text value="${salestat.devBuAdjAmtSum}" format="R###,###,###,###" /></td>
								<td>
									<c:choose>
										<c:when test="${report.mbrClsCd eq 'US000103' }"> <!-- 외국인일시 -->
											<c:choose>
												<c:when test="${foreAmt > 10 && (salestat.aggtStatCd eq 'PD004102' || salestat.aggtStatCd eq 'PD004107') }"> <!-- 이월 금액 합이 10$이상이고 정산마감상태와 이전 이월금액 데이타에 송금요청 버튼 생성-->
													<a href="JavaScript:goUpdateForeignStat()"> <img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_request.gif' />" alt="송금요청" /> </a>
												</c:when>
												<c:otherwise>
													<gc:html code="${salestat.aggtStatCd}"/>
												</c:otherwise>
											</c:choose>
										</c:when> 
										<c:otherwise>  <!-- 국내일시 -->
											<c:choose>
												<c:when test="${salestat.aggtStatCd eq 'PD004102' }">
													<a href="JavaScript:goUpdateStat('${salestat.saleYm}')"> <img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_request.gif' />" alt="송금요청" /> </a>
												</c:when>
												<c:otherwise>
													<gc:html code="${salestat.aggtStatCd}"/>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="brnone">
										<c:set var="strLength" value="${fn:length(salestat.rmtReason)}"/>
										<c:choose>
										<c:when test="${(salestat.aggtStatCd eq 'PD004107') && (strLength ne 0)}"><!-- 이월처리상태이면서 메세지가 존재할시 -->
											<a href="JavaScript:goDetail('${salestat.aggtStatCd}','${salestat.saleYm}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_list.gif' />" alt="내역" /></a>
										</c:when>
										<c:when test="${(salestat.aggtStatCd eq 'PD004123') && (strLength ne 0)}}"><!-- 송금포기상태이면서 메세지가 존재할시 -->
											<a href="JavaScript:goDetail('${salestat.aggtStatCd}','${salestat.saleYm}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_list.gif' />" alt="내역" /></a>
										</c:when>
										<c:otherwise>
											&nbsp;
										</c:otherwise>
										</c:choose>
								</td>
							</tr>
							</c:forEach>
							</c:otherwise>
							</c:choose>
							
						</tbody>
					</table>
				</div>
				<p class="txtr txt_90 pad_t2">화폐단위 : NT$</p>
				<div class="guideinfo mar_mt10">
					<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt05.gif' />" alt="정산 리포트를 확인하시고, 익월 5일(수취일 기준)전까지 송금신청 항목별로 Receipt/Invoice를 작성하여 아래 주소로 보내주시기 바랍니다." /></p>
					<ul class="bult08 txt_90">
						<li>주소 : 서울특별시 강남구 신사동123-45 &nbsp;<span class="tbar">|</span>&nbsp; 이메일 : <span class="link02"><a href="mailto:amart@naver.com">amart@naver.com</a></span>
							<ol class="txttype15">
								<li><strong>1) 회사회원</strong>&nbsp;&nbsp;&nbsp; : 회사 내 <span class="uline">자체 양식</span>을 활용하여 우편으로 발송해 주십시오.</li>
								<li><strong>2) 개인회원</strong>&nbsp;&nbsp;&nbsp; : 별도로 제공되는 <span class="uline">Receipt Template</span>를 활용하여 우편으로 발송해 주십시오.</li>
								<li><strong>3) 외국인회원</strong> : 별도로 제공되는 <span class="uline">Receipt Template</span>를 활용하여 작성 후 Scan 하여 File 을 이메일로 발송해 주십시오.<br /></li>
							</ol>
						</li>
					</ul>
					<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt06.gif' />" alt="정산 리포트에 문제가 있는 경우 아래 연락처로 문의해 주세십시오." /></p>
					<ul class="bult08 txt_90">
						<li class="mar_b5">담당자 : 라이트 정 &nbsp;<span class="tbar">|</span>&nbsp; 연락처 : &nbsp;<span class="tbar">|</span>&nbsp; 02-123-4567 &nbsp;<span class="tbar">|</span>&nbsp; 이메일 : <span class="link02"><a href="mailto:rightbrain@co.kr">rightbrain@co.kr</a></span></li>
					</ul>
					<p class="pad_t2"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt07.gif' />" alt="정산 관련된 상세 내용은" class="vm" /><span class="link03"><a href="<c:url value='/community/saleCalculateGuide.omp' />">개발지원 가이드 > 판매정산</a></span><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt08.gif' />" alt="을 참조하기 바랍니다." class="vm" /></p>
				</div>
				
			</div>
			<!-- //CONTENT TABLE E-->
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${reportList}"/>
		    </div>

		</div>
		<!-- //Content Area E -->
