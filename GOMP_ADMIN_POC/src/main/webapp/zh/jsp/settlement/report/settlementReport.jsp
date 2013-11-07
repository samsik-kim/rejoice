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
			alert("<gm:string value='${reportS.processMessage}'/>");		
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
	
	//정산 금액 송금요청 
 	function goUpdateStat(saleYm){
 		document.searchForm.saleYm.value = saleYm;
		document.searchForm.action="settlementRequest.omp";
 		document.searchForm.target="_self";
 		document.searchForm.submit();
 	}
	
	
 	//조정 금액 송금요청 
 	function goUpdateAdjustMoneyStat(saleYm){
 		document.searchForm.saleYm.value = saleYm;
		document.searchForm.action="adjustMoneyRequest.omp";
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
 		
 		document.searchForm.saleYm.value = saleYm;
 		if(aggtStatCd=="PD004107"){ //이월처리시
			targetUrl = "<c:url value="/settlement/report/popUpSettlementTransferInfo.omp"/>";
			settlement_popup_center('', 400, 285, 0, 0);
		}else if(aggtStatCd=="PD004123"){ //송금포기시
			targetUrl = "<c:url value="/settlement/report/popUpSettlementGiveUpInfo.omp"/>";
			settlement_popup_center('', 400, 303, 0, 0);
		}else{ //조정액일시
			targetUrl = "<c:url value="/settlement/report/popUpSettlementAdjustmentInfo.omp"/>";
			settlement_popup_center('', 400, 285, 0, 0);
		}
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
		<input type="hidden" id="adjYn" name="reportS.adjYn"></input>
		</s:form>
		<!-- Content Area S -->
		<div id="contents_area">	  
			<!-- Title Area S -->
			<div id="ctitle_area"> 
				<p class="history">Home &gt; 產品販售/結算 <strong>&gt;</strong> <span>結算報告</span></p>
				<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_title03.gif' />" alt="結算報告" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->
			<div id="contents">
            
				<ul class="bult07 mar_b5">
					<li>結算報告以上月銷售為準撰寫, 每月10日前後公開. (不包括周末和假日)</li>
					<li>請再次確認您的銀行資料以順利匯款.</li>
				</ul>
				<div class="slbox mar_b8">
					<dl class="dlist07 dlfrt01">
						<dt><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt09.gif' />" alt="帳戶名稱" /></dt>
						<dd>${report.acctNm}</dd>
					</dl>
					<dl class="dlist07">
						<dt><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt10.gif' />" alt="銀行名稱" /></dt>
						<dd><g:text value="${report.backNm}" format="L#####" /></dd>
					</dl>
					<dl class="dlist07">
						<dt><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt11.gif' />" alt="銀行帳戶 " /></dt>
						<dd><g:text value="${report.acctNo}" format="L#####*******************" /></dd>
					</dl>
					<p><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageIntro.omp?forwardAction=CALCULATE"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_account.gif' />" alt="欲變更銀行資料" /></a></p>
				</div>
				<p> &middot; 欲變更銀行資料, 應於每月20日之前變更, 帳款才可匯入新帳戶.</p>
				<div class="tstyleE pad_t20">
					<table summary="結算報告">
						<caption>結算報告</caption>
						<colgroup>
							<col width="8%" />
							<col width="9%" />
							<col width="9%" />
							<col width="9%" />
							<col width="9%" />
							<col width="13%" />
							<col width="10%" />
							<col width="9%" />
							<col width="11%" />
							<col width="9%" />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit11.gif' />" alt="交易月份" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit12.gif' />" alt="銷售總金額(A)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit14.gif' />" alt="未稅總金額(B)=A/1.05" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit15.gif' />" alt="拆帳總金額(C)=Bx拆分比" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit13.gif' />" alt="調帳總金額(D)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit16.gif' />" alt="稅額(E)=(C+D)xTAX Rate" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit17.gif' />" alt="實付金額(F)=(C)+(D)+(E)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit18.gif' />" alt="付款幣別(平均匯率)" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit19.gif' />" alt="匯款金額" /></th>
								<th scope="col" class="th01 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit20.gif' />" alt="狀態" /></th>
								<th scope="col" class="btnone th01 brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit21.gif' />" alt="記錄" /></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
							<c:when test="${totalCount eq 0 }">
							<tr>
								<td colspan="12" class="text_c">無資料。</td>
							</tr>
							</c:when>
							<c:otherwise>
							<c:set var="foreAmt" value="${report.devBuDvAmtSum}"/><!-- 개발자 배분 금액 합계 -->
							<c:set var="rmtReqYN" value="${report.rmtReqYN}"/><!-- 송금신청가능여부 -->
							<c:forEach items="${reportList }" var="salestat">
							<c:if test="${not empty salestat.saleYm}"><!-- 정산판매가 존재할시 -->
							<tr>
								<c:choose>
								<c:when test="${salestat.currencyUnit eq 'US005302' }">
									<c:set var="moneyDisplay" value="US$"/>
								</c:when>
								<c:otherwise>
									<c:set var="moneyDisplay" value="NT$"/>
								</c:otherwise>
								</c:choose>
								<td><span class="tcor"><g:text value="${salestat.saleYm}" format="L####-##" /></span></td>
								<td><g:text value="${salestat.totlPayAmt}" format="###,###,###,###.##" /></td>
								<td><g:text value="${salestat.beforeTaxAmt}" format="###,###,###,###.##" /></td>
								<td><g:text value="${salestat.shareAmt}" format="###,###,###,###.##" /></td>
								<td>&nbsp;</td>
								<td><g:text value="${salestat.txtAmt}" format="###,###,###,###.##" /></td>
								<td><strong><g:text value="${salestat.devBuDvAmtSum}" format="###,###,###,###.##" /></strong></td>
								<td><gc:html code="${salestat.currencyUnit}"/></td>
								<td><span class="txtcolor03"><c:out value="${moneyDisplay}"/> <g:text value="${salestat.devBuDvAmtSum}" format="###,###,###,###.##" /></span></td>
								<td>
									<c:choose>
										<c:when test="${report.mbrClsCd eq 'US000103' }"> <!-- 외국인일시 -->
											<c:choose>
												<c:when test="${foreAmt >= 10 && rmtReqYN eq 'Y' && (salestat.aggtStatCd eq 'PD004102' || salestat.aggtStatCd eq 'PD004107') }"> <!-- 이월 금액 합이 10$이상이고 정산마감상태와 이전 이월금액 데이타에 송금요청 버튼 생성-->
													<a href="JavaScript:goUpdateForeignStat()"> <img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_request.gif' />" alt="請款" /> </a>
												</c:when>
												<c:otherwise>
													<span class="tcor"><gc:html code="${salestat.aggtStatCd}"/></span>
												</c:otherwise>
											</c:choose>
										</c:when> 
										<c:otherwise>  <!-- 국내일시 -->
											<c:choose>
												<c:when test="${rmtReqYN eq 'Y' && salestat.aggtStatCd eq 'PD004102' }">
													<a href="JavaScript:goUpdateStat('${salestat.saleYm}')"> <img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_request.gif' />" alt="請款" /> </a>
												</c:when>
												<c:otherwise>
													<span class="tcor"><gc:html code="${salestat.aggtStatCd}"/></span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
									</span>
								</td>
								<td class="brnone">
										<c:set var="strRmtReasonLength" value="${fn:length(salestat.rmtReason)}"/> <!-- 송금포기메세지 -->
										<c:set var="strRmtReqReasonLength" value="${fn:length(salestat.rmtReqReason)}"/> <!-- 이월처리메세지 -->
										<c:choose>
										<c:when test="${(salestat.aggtStatCd eq 'PD004107') && (strRmtReqReasonLength ne 0)}"><!-- 이월처리상태이면서 메세지가 존재할시 -->
											<a href="JavaScript:goDetail('${salestat.aggtStatCd}','${salestat.saleYm}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_list.gif' />" alt="記錄" /></a>
										</c:when>
										<c:when test="${(salestat.aggtStatCd eq 'PD004123') && (strRmtReasonLength ne 0)}}"><!-- 송금포기상태이면서 메세지가 존재할시 -->
											<a href="JavaScript:goDetail('${salestat.aggtStatCd}','${salestat.saleYm}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_list.gif' />" alt="記錄" /></a>
										</c:when>
										<c:otherwise>
											&nbsp;
										</c:otherwise>
										</c:choose>
								</td>
							</tr>
							</c:if>
							<c:if test="${not empty salestat.adjSaleYm}"><!-- 조정액이 존재할시 -->
							<tr>
								<td><span class="tcor"><g:text value="${salestat.adjSaleYm}" format="L####-##" /></span></td>
								<td></td>
								<td></td>
								<td></td>
								<td><g:text value="${salestat.adjAmt}" format="###,###,###,###.##" /></td>
								<td><g:text value="${salestat.adjTxtAmt}" format="###,###,###,###.##" /></td>
								<td><strong><g:text value="${salestat.devBuDvAmtSum}" format="###,###,###,###.##" /></strong></td>
								<td><gc:html code="${salestat.currencyUnit}"/></td>
								<td><span class="txtcolor03"><c:out value="${moneyDisplay}"/> <g:text value="${salestat.devBuDvAmtSum}" format="###,###,###,###.##" /></span></td>
								<td>
									<c:choose>
										<c:when test="${rmtReqYN eq 'Y' && salestat.adjAggtStatCd eq 'PD004102' }">
											<a href="JavaScript:goUpdateAdjustMoneyStat('${salestat.saleYm}')"> <img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_request.gif' />" alt="請款" /> </a>
										</c:when>
										<c:otherwise>
											<gc:html code="${salestat.adjAggtStatCd}"/>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="brnone">
										<c:set var="strLength" value="${fn:length(salestat.adjReason)}"/>
										<c:choose>
										<c:when test="${(strLength ne 0)}"><!-- 조정사유 메세지가 존재할시 -->
											<a href="JavaScript:goDetail('${salestat.adjAggtStatCd}','${salestat.saleYm}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_list.gif' />" alt="記錄" /></a>
										</c:when>
										<c:otherwise>
											&nbsp;
										</c:otherwise>
										</c:choose>
								</td>
							</tr>
							</c:if>
							</c:forEach>
							</c:otherwise>
							</c:choose>
							
						</tbody>
					</table>
				</div>
				<div class="guideinfo">
					<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt05.gif' />" alt="請確認您的結算報告後, 截止次月30日(以收信日為基準), 依照[請款]款項開立發票或收據, 將其送至以下公司地址." /></p>
					<ul class="bult08">
						<li><strong>公司地址</strong> : 11491台北市內湖區瑞光路192號7樓-3 東陽行動電話會計部拆帳組 &nbsp;<span class="tbar">|</span>&nbsp; <strong>電子信箱</strong> : <span class="link02"><a href="mailto:amart@naver.com">dev_op@whoopy.tw</a></span>
							<div class="slbox2">
								<ol class="txttype15">
									<li><strong>1. 公司戶</strong>&nbsp;&nbsp;: 請開立三聯式發票送至公司地址.</li>
									<li><strong>2. 個人戶</strong>&nbsp;&nbsp;: 請參考我們所提供的 <span class="uline">收據範本(Receipt Template),</span> 將它 <span class="uline">寄送至公司地址.</span>&nbsp; <a href="<c:url value="/fileSupport/fileDown.omp"><c:param name="bnsType" value="webcontent"/><c:param name="filePath" value="/settlement/Receipt_template_ch.doc"/><c:param name="fileName" value="Receipt_template_ch.doc"/></c:url>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_template.gif' />" alt="操作指南" class="vm" /></a></li>
									<li><strong>3. 外國戶</strong>&nbsp;&nbsp;: 請參考我們所提供的 <span class="uline">收據範本(Receipt Template)</span>撰寫後, 將收據掃描檔發送至以下 <span class="uline">電子信箱.</span> &nbsp;<a href="<c:url value="/fileSupport/fileDown.omp"><c:param name="bnsType" value="webcontent"/><c:param name="filePath" value="/settlement/Receipt_template_eng.docx"/><c:param name="fileName" value="Receipt_template_eng.docx"/></c:url>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_template.gif' />" alt="操作指南" class="vm" /></a></li>
								</ol>
							</div>
						</li>
					</ul>
					<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt06.gif' />" alt="若對結算報告有異議, 請與以下業務聯絡人聯系." /></p>
					<ul class="pad_l07">
						<li class="mar_b5"> &nbsp;<strong>聯絡人</strong> : 李昱臻 &nbsp;<span class="tbar">|</span>&nbsp; <strong>聯絡電話</strong> : &nbsp; <span class="font_en">02-5555-5666 </span>&nbsp;<span class="tbar">|</span>&nbsp; <strong>電子信箱</strong> : <span class="link02"><a href="mailto:dev_op@whoopy.tw">dev_op@whoopy.tw</a></span></li>
					</ul> <!-- 0506 수정 -->
					<p class="pad_t10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt07.gif' />" alt=" 結算詳情, 請參照" class="pad_b2 vm" /><span class="link03"><a href="<c:url value='/community/saleCalculateGuide.omp' />">開發支援指南 > 販售結算</a></span></p>
					
				</div>
				
			</div>
			<!-- //CONTENT TABLE E-->
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${reportList}"/>
		    </div>

		</div>
		<!-- //Content Area E -->
