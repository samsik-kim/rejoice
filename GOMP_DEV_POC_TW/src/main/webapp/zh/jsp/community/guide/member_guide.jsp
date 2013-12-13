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
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원가이드 <strong>&gt;</strong> <span>회원</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title05.gif' />" alt="회원" /></h3>
	</div>
	<!-- //Title Area E -->

<!-- CONTENT TABLE S-->
<div id="contents">
	<p class="mar_b15"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt08.gif' />" alt="Taiwan Store 개발자센터는 개발자 여러분들이 만든 App을 Twiwan Store에서 자유롭게 등록 및 판매할 수 있도록 거래를 중계하는 플랫폼입니다." /></p>
	<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img01.gif ' />" alt="Taiwan Store 개발자센터 회원께서 1.Content Application 등록 2.검증 요청 3.판매 개시 4.상품 구매 및 정산 및 송금" /></p>
		
	<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle01.gif' />" alt="1. 회원가입" /></h5>
	<p class="pad_l15 pad_b10">Taiwan Store 회원은 개인회원, 회사회원(소규모영업, 일반영업), 외국인회원, 3가지로 회원이 구분됩니다. <br />모든 회원은 Taiwan Store에서 무료 어플을 등록 및 판매할 수 있으며, 정산정보를 입력하시면 유료 어플을 등록 및 판매하실 수 있습니다.</p>
	<div class="ucbox mar_b35">
		<p class="ucbult01 pad_b5"><strong>Taiwan Store 개발자센터 무료 회원가입 Process</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img02.gif' />" alt="회원종류 선택 &rarr; 가입 여부 확인 &rarr; 약관동의 &rarr; 회원정보 입력 &rarr; 이메일 인증 &rarr; 무료 회원가입 완료" /></p>
		<p class="ucbult01 pad_b5"><strong>Taiwan Store 개발자센터 유료 회원가입 Process</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img03.gif' />" alt="무료 회원가입 완료 &rarr; 정산 정보 입력 &rarr; 운영자승인 &rarr; 연회비결제 &rarr; 유료 회원가입 완료" /></p>
		<p class="ucbult02">정산 정보 안내</p>
		<ul class="ucbult03 pad_b7">
			<li>개인회원 : 개인신분사본, 정산 시 사용될 입금계좌정보, 통장사본</li>
			<li>회사회원 : 회사증명사본, 정산 시 사용될 입금계좌 정보, 통장 사본</li>
			<li>외국인회원 : 외국인증명사본, 정산 시 사용될 입금계좌 정보, 통장 사본</li>
		</ul>
		<p class="ucbult02">연회비 안내</p>
		<ul class="ucbult03">
			<li>유료 상품을 등록 및 판매하기 위해서는 연회비를 결제하셔야 상품의 판매가 가능합니다.</li>
			<li>연회비는 평생 1회만 납부하시면 되며, 신용카드로만 결제가 가능합니다. (연회비 결제 후 취소 및 환불은 불가능합니다.)</li>
		</ul>
	</div>

	<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle02.gif' />" alt="2. 회원 전환" /></h5>
	<p class="pad_l15 pad_b10">Taiwan Store 개발자센터 개인회원의 경우 이용하시면서 회사회원으로 회원 전환이 가능합니다.<br />외국인 회원의 경우 회원전환을 하실 수 없습니다.</p>
	<div class="ucbox">
		<p class="uctxt01 pad_b5"><strong>1)회원전환 Case</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img04.gif' /> " alt="무료 일반 회원 &rarr; 무료 회사 회원 또는 유료 회사 회원, 유료 일반 회원 &rarr; 유료 회사 회원" /></p>
		<p class="uctxt01 pad_b7"><strong>2)회원전환 Process</strong></p>
		<p class="ucbult01 pad_b5"><strong>무료 일반 회원 &gt; 무료 회사 회원전환 Process</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img05.gif' />" alt="회원전환 신청 &rarr; 전환 정보 입력 &rarr; 운영자승인 &rarr; 회원전환 완료" /></p>
		<p class="ucbult01 pad_b5"><strong>무료 일반 회원 &gt; 유료 회사 회원 &amp; 유료 일반 회원 &gt; 유료 회사 회원전환 Process</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img06.gif' />" alt="회원전환 신청 &rarr; 전환 정보 &amp; 정산 정보 입력 &rarr; 운영자승인 &rarr; 회원전환 완료" /></p>
		<p class="ucbult02">회원 전환 안내</p>
		<ul class="ucbult03">
			<li>회원전환 시 동일한 아이디와 상품/정산정보를 유지하면서 회사회원으로 전환할 수 있습니다.</li>
			<li>무료 일반 회원의 경우 무료 회사 회원으로 전환 후 무료 상품 등록 및 판매가 가능합니다.</li>
			<li>유료 회원으로 전환을 요청할 경우 정산정보를 입력 후 운영자의 승인을 거쳐야만 유료 상품 등록 및 판매가 가능합니다.</li>
			<li>회원전환 후 다시 이전상태로의 회원 재 전환은 불가능합니다.</li>
		</ul>
	</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->
