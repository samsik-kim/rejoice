<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<html> 
<head>
<script type="text/javascript">
	$(document).ready( function(){
		
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['ko']);
		// $.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
		// datepicker input right img create
		$.datepicker.setDefaults({showOn:'both', buttonImage:'<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/btn_cal.gif"/>', buttonImageOnly:true});
		
			
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		$("input[name=ctVerify\\.searchVmType]").attr("checked", "checked");
		
		$("#searchBtn").click( function() {
			if(showValidate('frm', 'default','<gm:string value='jsp.content.verify.contentsVerifyList.msg.dateChk01'/>')) {
				$("#frm").attr("action", env.contextPath + "/content/getContentsVerifyList.omp");
				$("#frm").submit();
			}
		});
		
		$("#startDate, #endDate ").keydown(function(event){
			if(event.keyCode == '8') {
				event.preventDefault(); 
			} 
		});
		
		payDateSet($("#searchDay").val());
	});
	
	function payDateSet(payDate) {
		setOrderSearchDateAdminPoC(payDate, document.frm.startDate, document.frm.endDate);
		
		switch(payDate) {
			case "today" :
				$("#searchDay").val("today");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day_on.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif");
				break;
			case "7day" :
				$("#searchDay").val("7day");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day_on.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif");
				break;
			case "1month" :
				$("#searchDay").val("1month");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon_on.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif");
				break;
			case "3month" :
				$("#searchDay").val("3month");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon_on.gif");
				break;
		}
	}
	
	function reasonView(cid, verifyReqVer){ 
		width = 540;
		height = 510;
		action = env.contextPath + "/content/popupSubContentsVerifyDetailView.omp?ctVerify.cid="+cid+"&ctVerify.verifyReqVer="+verifyReqVer;
	
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		  
		window.open(action,"popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",resizable=no, ScrollBars=no, status=yes, menubar=no");
	}
</script>
</head>

<body>
<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 상품등록/관리 &gt; 상품관리 <strong>&gt;</strong> <span>검증현황</span></p>

		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title04.gif' />" alt="검증현황" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<form id="frm" name="frm" action="" method="post">
	<input type="hidden" name="ctVerify.page.no" value="1"/>
	<input type="hidden" id="searchDay" name="searchDay" value="${searchDay }">
	<div id="contents">
          
		<div class="tstyleD mar_b30">
			<table summary="검증현황정보입니다">
				<caption>검증현황정보입니다</caption>

				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt07.gif' />" alt="검증요청일" /></th>
						<td class="tit02">
							<input type="text" id="startDate" name="ctVerify.startDate" class="w109" v:required m:required="<gm:string value='jsp.content.verify.contentsVerifyList.msg.dateChk02'/>" m:scompare="<gm:string value='jsp.content.verify.contentsVerifyList.msg.dateChk03'/>"
								value="<g:text value='${ctVerify.startDate }' format='L####-##-##' />" readonly="readonly" title="시작일 입력" /> &nbsp;~&nbsp;
							<input type="text" id="endDate" name="ctVerify.endDate" class="w109" v:required m:required="<gm:string value='jsp.content.verify.contentsVerifyList.msg.dateChk04'/>"
								v:scompare="ge,@{ctVerify.startDate}" m:scompare="<gm:string value='jsp.content.verify.contentsVerifyList.msg.dateChk05'/>"
								value="<g:text value='${ctVerify.endDate }' format='L####-##-##' />" readonly="readonly" title="종료일 입력" />
							&nbsp;&nbsp; 최근
							<a href="#"><img id="today" name="today" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif' />" alt="1일" onclick="javascript:payDateSet('today');" /></a>
							<a href="#"><img id="7day" name="7day" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif' />" alt="7일" onclick="javascript:payDateSet('7day');" /></a>
							<a href="#"><img id="1month" name="1month" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon_on.gif' />" alt="1개월" onclick="javascript:payDateSet('1month');" /></a>
							<a href="#"><img id="3month" name="3month" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif' />" alt="3개월" onclick="javascript:payDateSet('3month');" /></a>

						</td>
					</tr>
					<tr>
						<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt08.gif' />" alt="플랫폼" /></th>
						<td class="tit02">
							<gc:checkBoxs name="ctVerify.searchVmType" group="PD0056" filter="ph" value="${ctVerify.searchVmType }" split="&nbsp;&nbsp;"/>
						</td>
					</tr>

					<tr>
						<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt09.gif' />" alt="검증상태" /></th>
						<td> <gc:checkBoxs name="ctVerify.searchVerifyPrgrYn" group="PD0053" filter="ph" value="${ctVerify.searchVerifyPrgrYn }" divide="&nbsp;&nbsp;&nbsp;&nbsp;" split="&nbsp;&nbsp;"/> </td>
					</tr>
					<tr>
						<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt10.gif' />" alt="검증결과" /></th>
						<td class="tit02">
							<!-- <gc:checkBoxs name="ctVerify.searchAgrmntStat" group="PD0050" value="${ctVerify.searchAgrmntStat }" divide="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" split="&nbsp;&nbsp;"/> -->
							<c:forEach items="${agrmntCodeList }" var="agrmntCd">
								<c:choose>
									<c:when test="${agrmntCd.dtlFullCd == CONST.AGREEMENT_STATUS_INIT }">
										<input type="checkbox" id="searchAgrmntStat" name="ctVerify.searchAgrmntStat" value="${agrmntCd.dtlFullCd }"
											<c:forEach items="${ctVerify.searchAgrmntStat }" var="agrmnt">
												<c:if test="${agrmntCd.dtlFullCd == agrmnt}"> checked </c:if>
											</c:forEach>
										> (없음) </input>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="searchAgrmntStat" name="ctVerify.searchAgrmntStat" value="${agrmntCd.dtlFullCd }" 
											<c:forEach items="${ctVerify.searchAgrmntStat }" var="agrmnt">
												<c:if test="${agrmntCd.dtlFullCd == agrmnt}"> checked </c:if>
											</c:forEach>
										> ${agrmntCd.cdNm } </input> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt11.gif' />" alt="상품명" /></th>
						<td class="bgnone">
							<input type="text" id="prodNm" name="ctVerify.prodNm" class="w410" value="${ctVerify.prodNm }" title="검색어를 입력해주세요" />
							<img id="searchBtn" name="searchBtn" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_search.gif' />" style="cursor: pointer;" alt="검색" />
						</td>
					</tr>

				</tbody>
			</table>
		</div>
		<p class="pbult09">총 <span class="txtcolor02">${ctVerify.page.totalCount }</span>개의 상품이 존재합니다.</p>
		<div class="btstyleA">
			<table summary="검증현황 상품 리스트">
				<caption>검증현황 상품 리스트</caption>

				<colgroup>
					<col width="6%" />
					<col width="10%" />
					<col />
					<col width="13%" />
					<col width="10%" />
					<col width="8%" />
					<col width="12%" />
				</colgroup>

				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit25.gif' />" alt="번호" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit26.gif' />" alt="플랫폼" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit38.gif' />" alt="상품명" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit27.gif' />" alt="검증요청일" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit28.gif' />" alt="검증상태" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit29.gif' />" alt="검증결과" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit30.gif' />" alt="검증완료(예정)일" /></th>

					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty resultList }">
							<c:forEach items="${resultList }" var="content" varStatus="status">
								<tr>
									<td>${ctVerify.page.totalCount - content.rnum + 1 }</td>
									<td><gc:text code="${content.vmType }" /></td>
									<td class="tit01">
										<span class="imgbox">
											<c:choose>
												<c:when test="${not empty content.filePos }">
													<img src="${CONF['omp.common.url.http-share.product']}${content.filePos}" width="72" height="72" 
														name="contsImg" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/no_images.gif"/>');" />
												</c:when>
												<c:otherwise>
													<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/no_images.gif' />" alt="관련사진" />
												</c:otherwise>
											</c:choose>
										</span>
										<a href="<c:url value='/content/getContentVerifyDetail.omp?ctVerify.cid=${content.cid }&ctVerify.verifyReqVer=${content.verifyReqVer }&ctVerify.verifyDetailTab=verifyState' />">${content.prodNm }</a></td>
									<td><g:text value='${content.insDttm }' format="L####-##-##"/></td>
									<td>
										<c:choose>
											<c:when test="${CONST.CODE_VERIFY_TEST_REJECT eq  content.verifyPrgrYn || CONST.CODE_VERIFY_TEST_END eq content.verifyPrgrYn}">
												<gc:text code="${CONST.CODE_VERIFY_ING }" />
											</c:when>
											<c:otherwise>
												<gc:text code="${content.verifyPrgrYn }" />											
											</c:otherwise> 
										</c:choose>
									</td>
									<td>
										<c:choose>
										<c:when test="${content.agrmntStat eq CONST.AGREEMENT_STATUS_AGREE || content.agrmntStat eq CONST.AGREEMENT_STATUS_REJECT}">
											<span class="txtcolor03">
												<a id="popOpenBtn" href="javascript:reasonView('${content.cid }', '${content.verifyReqVer }');">
													<c:if test="${CONST.AGREEMENT_STATUS_AGREE == content.agrmntStat}">
														<span class="txtcolor03 link01"> <gc:text code="${content.agrmntStat }" /> </span>
													</c:if>
													<c:if test="${CONST.AGREEMENT_STATUS_REJECT == content.agrmntStat }">
														<gc:text code="${content.agrmntStat }" />
													</c:if>
												</a>
											</span>
										</c:when>
										<c:otherwise>-</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:if test="${content.verifyPrgrYn eq CONST.CODE_VERIFY_ING || content.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_END || content.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_REJECT}">
											<c:if test="${0 > content.ctEndExDt}"><div align="center"> D${content.ctEndExDt } 일</div></c:if>
											<c:if test="${0 < content.ctEndExDt}"><div align="center"> D+${content.ctEndExDt } 일</div></c:if>
											<c:if test="${0 == content.ctEndExDt}"><div align="center"> D-${content.ctEndExDt } 일</div></c:if>
											<br/>(<g:text value="${content.ctEndDt }" format="L####-##-##" />)
				                        </c:if>
				                        <c:if test="${content.verifyPrgrYn eq CONST.CODE_VERIFY_END}">
				                        	<g:text value="${content.ctEndDt }" format="L####-##-##" />
				                        </c:if>
				                        <c:if test="${!content.verifyPrgrYn eq CONST.CODE_VERIFY_END || !content.verifyPrgrYn eq CONST.CODE_VERIFY_ING || !content.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_END || !content.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_REJECT}"> - </c:if>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="7"> <gm:string value='jsp.content.verify.contentsVerifyList.msg.list_result'/> </td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			
			<!-- paging -->
			<div align="center">
				<g:pageIndex item="${resultList}" />
			</div>
			<script type="text/javascript">
				function goPage(no) {
				    $("form[name=frm] input[name=ctVerify\\.page\\.no]").val(no);
				    $("#frm").submit();
				}
			</script>
			<!-- //paging -->
			
		</div>
	</div>
	</form>
	<!-- //CONTENT TABLE E-->

</div>
</body>
</html>

