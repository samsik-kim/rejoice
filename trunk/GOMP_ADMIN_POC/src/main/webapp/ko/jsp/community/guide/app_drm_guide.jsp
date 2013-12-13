<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<script type="text/javascript" src="<c:url value="/js/twonly.js"/>"></script>
<script type="text/javascript">

</script>
<!-- Content Area S -->
<h2 class="hide">본문영역</h2>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원가이드 <strong>&gt;</strong> <span>Application DRM</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title09.gif' />" alt="Application DRM" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt12.gif' />" alt="Taiwan Store에서는 판매자가 등록한 Application의 불법복제를 방지하여 판매자의 권익을 보호할 수 있는 솔루션을 제공합니다." /></p>
		
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle09.gif' />" alt="1. ARM 적용 프로세스" /></h5> 
		<div class="ucbox">
			<p class="mar_b25"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img27.gif' />" alt="ARM Library 받기 &rarr; Application ID 발급 &rarr; ARM Library 적용 &rarr; Developer License 받기 &rarr; Validation Tool 받기 &rarr; Self 검증 &rarr; Application 등록" /></p>
			<ol class="ucbult07">
				<li>
                      	1) ARM Library 받기 : ARM 솔루션 적용을 위해 필요한 Library를 다운로드 받으세요. 
                          <a href="javascript:alert('준비중');"><img class="vm" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/download.gif' />" alt="다운로드" /></a>
                      </li>
				<li>2) Application ID 발급</li>
					<ul class="ucbult03">
						<li>신규발급 : 상품 신규 등록 시 발급 받으실 수 있습니다.</li>
						<li>Application ID가 발급된 경우 : 상품등록/관리>상품관리에서 확인 하실 수 있습니다.</li>
					</ul>
				<li>3) ARM Library 적용 : Application 개발소스 내에 ARM Library를 적용하세요.</li>
				<li>4) Developer License 받기</li>
					<ul class="ucbult03">
						<li>검증 툴(Validation Tool)을 통한 ARM 동작 테스트를 위해서는 Application별로 Developer License 발급이 필요합니다.</li>
						<li>Developer License는 상품현황>개발정보 탭에서 발급받으실 수 있습니다.</li>
					</ul>
				<li>
                      	5) Validation Tool 받기 : ARM 동작 테스트를 개발자가 자체적으로 수행할 수 있는 프로그램을 다운로드 받으세요. 
                          <a href="javascript:alert('준비중');"><img class="vm" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/download.gif' />" alt="다운로드" /></a>
                      </li>
				<li>6) Self 검증 : 검증툴(Validation Tool)을 통한 자체 테스트를 진행하세요.</li>
				<li>7) Application 등록 : ARM Self 검증이 완료된 Application을 등록하세요.</li>
			<ol>
		</div>
		
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->