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
				<li><a href="<c:url value='/community/basicInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab01.gif' />" alt="기본정보" /></a></li>
				<li><a href="<c:url value='/community/detailInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab02_on.gif' />" alt="상세정보" /></a></li>
				<li><a href="<c:url value='/community/devInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab03.gif' />" alt="개발정보" /></a></li>
			</ul>
		</div>
		<div class="ucbox">
			<p class="ucbult01 pad_b2"><strong>상세 정보는 서비스에 필요한 각종 Metadata를 등록하는 단계입니다.</strong></p>
			<p class="uctxt02 pad_l07 pad_b5">해당 항목들은 자유롭게 수정이 가능하며, 실시간으로 Shop Client에 적용됩니다.</p>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img09.gif' />" alt="기본정보 입력 화면" /></p>
				<dl class="mar_b22">
					<dt class="dt1">1) 상품명</dt>
					<dd class="dd3">: 기본정보 입력 시 등록한 상품명입니다.</dd>
					<dt class="dt1">2) 판매자 노출명</dt>
					<dd class="dd3">
						: 상품판매 시 노출되는 판매자명을 입력하는 항목으로, 기본값은 회원 가입 시 등록한 판매자명입니다.<br />
						&nbsp; 상품의 소유권자와 판매자가 다른 경우, 활용하실 수 있습니다.
					</dd>
					<dt class="dt1">3) 상품가격</dt>
					<dd class="dd3">
						: 상품가격 입력 시에는 부가가치세가 포함된 금액으로 입력해야 합니다.<br />
						&nbsp; 기본적으로 “0”으로 입력되어 있으며, 수정을 하지 않고 상품을 판매할 경우 해당 상품은 무료상품으로 판매가 됩니다.<br />
						&nbsp; 유료상품을 판매하시기 위해서는 정산정보를 입력하셔야 하며, 정산정보 승인 이후에 유료상품을 판매하실 수 있습니다.
					</dd>
					<dt class="dt1">4) 프로모션 URL</dt>
					<dd class="dd3">: App홍보가 가능한 외부 리소스(예: Youtube 동영상)가 있는 경우 입력하여 제공하실 수 있습니다.</dd>
					<dt class="dt1">5) 상품관리번호</dt>
					<dd class="dd3">
						: 판매자가 별도의 목적(예. 3자 정산 등)을 위해 자제적으로 관리하는 식별코드가 있을 경우 해당 코드를 입력하실 수 있습니다. <br />&nbsp; 해당 정보는 상품별 판매/정산현황 제공 시 함께 제공됩니다.
					</dd>
				</dl>

			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img10.gif' />" alt="분류정보 입력 화면" /></p>
				<dl class="mar_b22">
					<dt class="dt2">6) 분류정보</dt>
					<dd>: 상품의 특성을 고려하여 Category를 선택하실 수 있습니다.</dd>
					<dt class="dt2">7) 이용등급</dt>
					<dd>
						: 이용등급은 전체 이용가, 성인 이용가 2가지로 구분됩니다. <br />
						&nbsp; 등록하시는 상품이 미성년자가 이용 불가능한 상품일 경우 "성인 이용가" 로 선택하시면 됩니다. 
					</dd>
					<dt class="dt2">8) 태그입력</dt>
					<dd>
						: 등록하시는 상품을 가장 잘 설명하는 단어를 최대 5개까지 등록하실 수 있습니다. 등록하신 태그는 사용자가 상품 검색 시 활용됩니다.<br />&nbsp; 태그 등록 시 비속어 및 금기어(특수문자 포함)는 키워드로 등록할 수 없으며, 동일 키워드의 중복 사용은 불가합니다. 
					</dd>
				</dl>

			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img11.gif' />" alt="상품설명 입력 화면" /></p>
				<dl class="mar_b22">
					<dt class="dt3">9) 요약설명</dt>
					<dd class="dd1">: 상품을 간략하게 설명하는 항목으로 상품 검색 시 활용이 됩니다.</dd>
					<dt class="dt3">10) 상세설명&amp;설명이미지</dt>
					<dd class="dd1">
						: 상세설명은 상품에 대한 상세한 정보를 입력하는 항목이며, 설명이미지를 통해 텍스트로 표현할 수 없는 조작방법,<br />
						&nbsp; 상품 관련 이미지를 등록 하실 수 있습니다.
					</dd>
				</dl>

			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img12.gif' />" alt="상품이미지 등록 화면" /></p>
				<dl class="mar_b22">
					<dt class="dt4">11) 대표아이콘</dt>
					<dd>: 212*212 사이즈의 JPG파일을 등록하면 Shop Client에서 사용되는 이미지 사이즈에 맞게 자동 조정됩니다. </dd>
					<dt class="dt4">12) 미리보기</dt>
					<dd>
						: Shop Client의 미리보기 기능에 제공되는 이미지를 등록합니다. <br />
						&nbsp; 상품 내용을 잘 표현할 수 있는 이미지를 등록해주세요.(100KB 이하, JPG파일) 
					</dd>
				</dl>

			<p class="mar_b15"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img13.gif' />" alt="미리보기 버튼 화면" /></p>
			<p class="uctxt02 txt_90">13) 우측의 "미리보기" 버튼을 통해 등록하신 상품정보가 Shop Client에 어떻게 노출되는지 확인하실 수 있습니다.</p>
              </div>
              
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->