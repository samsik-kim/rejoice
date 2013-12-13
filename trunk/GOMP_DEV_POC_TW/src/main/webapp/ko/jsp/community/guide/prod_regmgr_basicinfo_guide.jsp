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
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원가이드 <strong>&gt;</strong> <span>상품등록/관리</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title06.gif' />" alt="상품등록/관리" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt09.gif' />" alt="상품등록은 기본정보, 상세정보, 개발정보 총 3단계로 진행됩니다." /></p>
		
		<div class="tab mar_b22">
			<ul>
				<li><a href="<c:url value='/community/basicInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab01_on.gif' />" alt="기본정보" /></a></li>
				<li><a href="<c:url value='/community/detailInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab02.gif' />" alt="상세정보" /></a></li>
				<li><a href="<c:url value='/community/devInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab03.gif' />" alt="개발정보" /></a></li>
			</ul>
		</div>
		<div class="ucbox">
		<p class="ucbult01 pad_b2"><strong>상품에 대한 기본정보(상품명&amp;플랫폼)을 입력 후 Application ID를 발급받는 단계입니다.</strong></p>
		<p class="uctxt02 pad_l07 pad_b7">Application ID는 등록된 상품을 식별하기 위해 자동적으로 발급되는 값이며, 상품의 판매/정산관리 및 ARM등에 사용됩니다.</p>
		<p class="pad_b7"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img07.gif' />" alt="Application ID 발급화면" /></p>
		<p class="ucbult06 mar_b15">Application ID 발급화면</p>
		
		<p class="ucbult01 pad_b2"><strong>상품 상태는 등록 및 검증 완료된 이후 직접 변경 가능합니다.</strong></p>
		<p class="uctxt02 pad_l07 pad_b7">상품 검증이 완료되면 상품의 상태는 판매대기이며 [판매 개시하기]버튼을 클릭해야 판매가 가능합니다. </p>
		<p class="pad_b7"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img08.gif ' />" alt="상품 상태 변경 및 관리 화면" /></p>
		<p class="ucbult06 mar_b22">상품 상태 변경 및 관리 화면</p>
		
		<p class="pad_b7">- 상품 상태 별 안내 및 제공 기능 정의</p>
		<div class="tstyleE">
			<table summary="상태, 설명, 전시여부, 상태변경 버튼" class="uc">
			<caption>상품 상태 별 안내 및 제공 기능 정의</caption>
			<colgroup>
				<col width="16%" />
				<col />
				<col width="9%" />
				<col width="16%" />
			</colgroup>
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit05.gif' />" alt="상태" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit06.gif' />" alt="설명" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit07.gif' />" alt="전시여부" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit08.gif' />" alt="상태변경 버튼" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="bg01">미승인</td>
						<td class="left">검증요청 전 상품 또는 최초 검증이 반려된 상태</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_del.gif' />" alt="삭제하기" /></td>
					</tr>
					<tr>
						<td class="bg01">판매대기</td>
						<td class="left">
							최초 검증 완료 후 상태<br />
							판매개시 버튼 클릭 시 판매 중으로 변경됩니다.
						</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_sell02.gif' />" alt="판매개시" /></td>
					</tr>
					<tr>
						<td class="bg01">판매중</td>
						<td class="left">상품이 Taiwan Store에서 판매되고 있는 상태</td>
						<td>O</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_sell01.gif '/>" alt="판매중지" /></td>
					</tr>
					<tr>
						<td class="bg01">판매중지</td>
						<td class="left">개발자에 의해 판매가 중지된 상태</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_sell02.gif' />" alt="판매개시" /></td>
					</tr>
					<tr>
						<td class="bg01">판매금지</td>
						<td class="left">
							운영자에 의해 판매가 금지된 상태<br />
							(판매금지된 상품은 운영자에게 해지 요청을 할 수 있습니다.)
						</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_cancel.gif' />" alt="해지요청" /></td>
					</tr>
					<tr>
						<td class="bg01">검증진행 중</td>
						<td class="left">상품의 등록, 수정, 변경에 의해 검증을 요청한 상태</td>
						<td>O/X</td>
						<td class="brnone">N/A</td>
					</tr>
					<tr>
						<td class="bg01">해지요청</td>
						<td class="left">판매금지 상태 상품에 대해 운영자에게 해지 요청한 상태</td>
						<td>X</td>
						<td class="brnone">N/A</td>
					</tr>
				</tbody>
			</table>
		</div>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->