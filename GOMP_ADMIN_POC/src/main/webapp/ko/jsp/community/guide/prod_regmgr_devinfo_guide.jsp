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
				<li><a href="<c:url value='/community/detailInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab02.gif' />" alt="상세정보" /></a></li>
				<li><a href="<c:url value='/community/devInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab03_on.gif' />" alt="개발정보" /></a></li>
			</ul>
		</div>
		<div class="ucbox">
			<p class="ucbult01 pad_b2"><strong>개발정보는 서비스 제공에 필요한 바이너리 파일과 단말 정보를 등록하는 단계입니다.</strong></p>
			<p class="uctxt02 pad_l07 pad_b5">개발정보를 수정 후 Shop Client에 적용하기 위해서는 반드시 검증단계를 거쳐야 합니다.</p>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img14.gif' />" alt="바이너리 파일 정보 입력 화면" /></p>
			<dl class="mar_b5">
				<dt class="dt5">1) LCD Size </dt>
				<dd class="dd2">: 서비스 가능한 단말의 LCD Size를 선택하실 수 있습니다. LCD Size는 복수 선택이 가능하며, 적용단말 검색 시 사용되는<br />&nbsp; 정보입니다.</dd>
				<dt class="dt5">2) 바이너리 파일 등록</dt>
				<dd class="dd2">
				  : 상품의 바이너리파일(Apk)을 등록합니다. 바이너리 파일은 LCD Size별로 최적화된 파일을 복수개 등록할 수 있으나, <br />
				  &nbsp; 바이너리 파일간 LCD Size는 중복될 수 없습니다.<br />
				  &nbsp; 바이너리 파일을 등록하시면 아래와 같이 화면이 전환되어 바이너리 파일에 대한 정보를 확인하실 수 있습니다.<br />
				  &nbsp; 자동 추출정보(AndroidManifest.xml 활용) : minSdkVersion / targetSdkVersion / maxSdkVersion / versionCode / <br />
				  &nbsp; versionName / Package 
				</dd>
			</dl>
			<p class="ucbult02 cb">주의. 바이너리 파일 등록 전, 아래 내용을 반드시 확인해 주세요. 아래 내용이 충족되지 않은 경우 상품 등록이 불가능합니다.</p>
			<ul class="uctxt02 pad_l15 txt_90 mar_b22">
				<li>&middot; Platform Version &nbsp;&nbsp;&nbsp; : 2.1 이상인지 확인</li>
				<li>&middot; Signing Application : 바이너리 파일은 반드시 private key로 signing이 되어야 하며, 유효기간은 10,000일 이상이어야 합니다.<br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 자세한 내용은 <span class="link02"><a href="http://developer.android.com/guide/topics/manifest/uses-sdk-element.html ">여기</a></span>를 참조하세요.</li>
				<li> &middot; maxSdkVersion &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : Application이 구동 가능한 maximum API Level을 지정하는 값으로, 특별히 지정하지 않는 것을 권장합니다.<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 자세한 내용은 <span class="link02"><a href="http://developer.android.com/guide/topics/manifest/uses-sdk-element.html ">여기</a></span>를 참조하세요. </li>
			</ul>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img15.gif' />" alt="바이너리 파일 정보 입력 화면2" /></p>
			<dl class="mar_b22">
				<dt class="dt6">3) 적용단말 선택</dt>
				<dd class="dd1">: 등록한 바이너리 파일과 선택한 LCD Size를 기준으로 검색된 단말 리스트에서 적용단말을 선택하실 수 있습니다.</dd>
			</dl>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img16.gif' />" alt="바이너리 파일 정보 입력 화면3" /></p>
			<p class="uctxt02 txt_90 mar_b22">&middot;추가/삭제 기능을 통해 복수개의 바이너리 파일을 추가 등록 할 수 있으며, 등록한 바이너리 파일을 삭제할 수도 있습니다.</p>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img17.gif' />" alt="바이너리 파일 정보 입력 화면4" /></p>
			<dl class="mar_b22">
				<dt class="dt7">4) Application DRM</dt>
				<dd class="dd2">
					: 등록하시는 상품의 Application DRM 적용유무를 선택하실 수 있습니다.<br />
					&nbsp; Application DRM 적용을 위한 자세한 정보는 <span class="link02"><a href="<c:url value='/community/appDrmGuide.omp'/>">개발지원가이드>Application DRM</a></span>을 통해 확인하실 수 있습니다.
				</dd>
				<dt class="dt7">5) 이용매뉴얼</dt>
				<dd class="dd2">: 효율적인 상품 검증을 위해 사용되는 정보이며, 상품 내용을 쉽고 직관적으로 기술해 주세요.</dd>
			</dl>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img18.gif' />" alt="미리보기 버튼 화면" /></p>
			<dl class="mar_b22">
				<dt class="dt7">6) 검증 이력 관리</dt>
				<dd class="dd2">: 상품 검증요청 시 등록한 검증 사유를 확인하실 수 있습니다. 해당정보는 효율적인 상품 검증을 위해 활용이 됩니다.</dd>
				<dt class="dt7">7) 업데이트 이력 관리</dt>
				<dd class="dd2">: 상품에 대한 업데이트 이력을 관리하는 기능입니다.</dd>
			</dl>
		</div>
		
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->