<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
$(document).ready(function(){

	$('#btnOk').click(function(){
		alert("<gm:string value='jsp.member.mypage.msg.com03'/>");
		location.href = "${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp";
	});
	
	$('#cancel').click(function(){
		alert("<gm:string value='jsp.member.mypage.msg.com03'/>");
		location.href = "${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp";
	});
	
	$('#profileMove').click(function(){
		$('#introFrm').attr('action', env.contextPath + "/mypage/mypageProfileView.omp");
		$('#introFrm').submit();
	});
});
</script>
<form name="introFrm" id="introFrm" method="post">
	<input type="hidden" name="isProfile" id="isProfile" value="<g:tagAttb value="${isProfile}"/>"/>
</form>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원정보변경'/></span></p>
		<h3><img alt="회원정보변경" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title01.gif'/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	
		<!-- Tab_menu S -->
		<div class="tab mar_b22">
			<ul>
				<li><a href="#"><img alt="기본정보" id="profileMove" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_tab01.gif'/>" /></a></li>
				<li><a href="#"><img alt="정산정보" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_tab02_on.gif'/>" /></a></li>
			</ul>
			<p class="info"><span>*</span> 가 표시된 부분은 필수입력 항목입니다.</p>
		</div>
		<!-- //Tab_menu E -->
		<h4 class="h41"><img alt="정산정보" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_02.gif'/>"></h4>
		<c:if test="${calculateInfo.dispStat ne 'POP'}">
		<div class="pmbox1 mar_b10">
			<p>정산정보에 대해서 운영자가 <span class="txtcolor10">승인 검토</span> 중입니다. &nbsp;승인 검토 중에는 정산정보 수정이 불가합니다.</p>
		</div>
		</c:if>
		<div class="tstyleA">
			<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
			<table summary="정산정보">
				<caption>정산정보</caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
					<!-- New -->  
					<tr>
						<th scope="row"><span>*</span> 개인신분 사본</th>
						<td><g:html value="${calculateInfo.identityDocNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 예금주</th>
						<td><g:html value="${calculateInfo.acctNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 은행정보</th>
						<td><g:html value="${calculateInfo.bankCd}"/><br /><g:html value="${calculateInfo.bankNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 계좌정보 </th>
						<td><g:html value="${calculateInfo.acctNo}"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> 통장사본</th>
						<td class="bgnone"><g:html value="${calculateInfo.acctDocNm}"/></td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
			<table summary="정산정보 입력">
				<caption>정산정보</caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> 회사증명 사본</th>
						<td><g:html value="${calculateInfo.identityDocNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 회사구분</th>
						<td><gc:html code="${calculateInfo.bizCatCd}"/> </td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 예금주</th>
						<td><g:html value="${calculateInfo.acctNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 은행정보 </th>
						<td><g:html value="${calculateInfo.bankCd}"/><br /> <g:html value="${calculateInfo.bankNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 계좌정보</th>
						<td><g:html value="${calculateInfo.acctNo}"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> 통장사본</th>
						<td class="bgnone"><g:html value="${calculateInfo.acctDocNm}"/></td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
			<table summary="정산정보 입력">
				<caption>정산정보 입력</caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> 외국인증명 사본</th>
						<td><g:html value="${calculateInfo.identityDocNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 예금주</th>
						<td><g:html value="${calculateInfo.acctNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 은행명</th>
						<td><g:html value="${calculateInfo.bankNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 계좌번호</th>
						<td><g:html value="${calculateInfo.acctNo}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 은행코드</th>
						<td>
							<div class="fltl pad_r4">
							<select id="bankcode" class="w188" disabled="disabled">
								<gc:options group="US0057" codeType="full" value="${calculateInfo.bankGlType}"/>
							</select>
							</div>
							<input type="text" id="bankcode1" name="bankcode1" class="w180" value="<g:tagAttb value="${calculateInfo.bankGlCd}"/>" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 화폐선택</th>
						<td><gc:html code="${calculateInfo.currentcyUnit}"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> 통장사본</th>
						<td class="bgnone"><g:html value="${calculateInfo.acctDocNm}"/></td>
					</tr>
				</tbody>
			</table>
			</c:if>
		</div>
		<c:if test="${calculateInfo.dispStat ne 'POP'}">
		<div class="btnarea mar_t30">
			<input id="btnOk" type="image" alt="확인" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>">
			<a href="#" id="cancel"><img alt="취소" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>"></a>
		</div>
		</c:if>
	</div>
	<!-- //CONTENT TABLE E-->

</div>
