<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!-- Content Area S -->
<h2 class="hide">본문영역</h2>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원가이드 <strong>&gt;</strong> <span>상품검증</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title07.gif' />" alt="상품검증" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt10.gif' />" alt="Taiwan Store에서는 서비스 완결성을 위해 판매자가 등록한 상품에 대해 내부 검증을 수행합니다." /></p>
		<ul class="ucbult08 mar_b30">
			<li>검증의 주요항목은 유해성 검증과 동작 검증으로 구분되며, 자세한 검증 항목은 <a href="#"><span class="txtcolor04">여기</span></a>를 참조하세요.</li>
			<li>상품 검증 단계는 검증대기, 검증중, 검증완료 총 3단계로 구분됩니다.</li>
		</ul>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle03.gif' />" alt="1. 검증요청" /></h5>
		<p class="pad_l07 mar_b22">상품 정보(상세정보, 개발정보)을 등록 하면 [검증요청하기] 버튼이 활성화 됩니다. <br />[검증요청하기] 버튼을 클릭 하면 해당 상품에 대해 검증이 진행됩니다. 검증 진행중인 상품은 정보수정을 할 수 없습니다 </p>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle04.gif' />" alt="2. 검증요청 취소" /></h5>
		<p class="pad_l07 mar_b22">검증요청하기 이후 검증 대기 상태에는 검증요청 취소가 가능합니다. 검증 요청을 취소하면 검증상태가 '검증대기'에서<br /> '검증취소' 상태로 변경이 됩니다. 검증 요청을 취소하면 상세정보 / 개발정보의 내용을 수정할 수 있습니다. </p>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle05.gif' />" alt="3. 검증현황" /></h5>
		<p class="pad_l07 mar_b10">상품의 검증결과는 상품등록/관리 > 상품관리 > 검증현황에서 확인하실 수 있습니다.</p>
		<div class="ucbox">
			<p class="mar_b8"><strong>1) 검증 상태 정의</strong></p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img19.gif' />" alt="" /></p>
			<div class="tstyleE mar_b22">
			<table summary="검증 상태 정의입니다." class="uc">
				<caption>검증 상태 정의</caption>
				<colgroup>
					<col width="16%" />
					<col />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit09.gif' />" alt="검증상태" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit06.gif' />" alt="설명" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="bg01">검증대기</td>
						<td class="left brnone">검증을 위한 대기 단계로 검증 요청 취소를 할 수 있습니다.</td>
					</tr>
					<tr>
						<td class="bg01">검증 중</td>
						<td class="left brnone">검증 요청한 상품에 대한 검증 중 상태입니다.</td>
					</tr>
					<tr>
						<td class="bg01">검증완료</td>
						<td class="left brnone">검증이 완료된 상태로 '승인' 또는 '반려'의 검증결과를 확인할 수 있습니다.</td>
					</tr>
					<tr>
						<td class="bg01">검증취소</td>
						<td class="left brnone">개발자의 검증취소 요청으로 검증 취소된 상태를 의미합니다.</td>
					</tr>
				</tbody>
			</table>
			</div>
			<p class="mar_b8"><strong>2) 검증완료(예정)일</strong></p>
			<p class="mar_b8">검증상태가 검증완료일 경우 검증완료일, 검증 중인 경우 검증완료 예정일이 D-day 형태로 표시됩니다.</p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img20.gif' />" alt="검증중 단계의 검증정보" /></p>
			<p class="mar_b8"><strong>3) 검증결과</strong></p>
			<p class="pad_l10 mar_b8">검증결과는 "승인"과 "반려" 2가지로 분류되며, 반려 시 검증결과 상세 페이지에서 반려에 대한 사유를 확인할 수 있습니다.
				2개 이상의  <br />바이너리 파일 중 한 개라도 반려가 되면, 상품의 검증결과는 반려로 표시됩니다. 검증취소를 한 경우 검증결과는 표시되지 않습니다. </p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img21.gif' />" alt="검증결과 상세내역 화면" /></p>
			<p class="mar_b8"><strong>4) 검증 히스토리</strong></p>
			<p class="mar_b8">검증 히스토리에서는 상품 버전별 검증 히스토리를 확인 할 수 있습니다. <br />
				※ 검증결과를 클릭하면 검증결과 상세내역을 확인할 수 있습니다. </p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img22.gif' />" alt="검증히스토리 화면" /></p>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->