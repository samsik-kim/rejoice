<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<title>[nMIMO] 페이지 오류 - :: 고객만족, 뛰고 또 뛰겠소 dododo olleh ::</title>
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="No-Cache" />
	<meta http-equiv="content-Type" content="text/html; charset=utf-8" />


	<!--//본 페이지를 사용하는 모든 서비스는 아래 메타태그 부분을 서비스별 정보에 맞게 기입하여야 합니다.-->
	<meta name="Author" content="KT담당자" />
	<meta name="Publisher" content="KT관리부서" />
	<meta name="Other Agent" content="KT부서장" />
	<meta name="Distribution" content="담당엔지니어" />
	<meta name="Author-Date(Date)" content="2012/11/30" />
	<meta name="Description" content="[서비스명] 페이지 오류 - 서비스(페이지) 안내문" />	

	
	<style type="text/css">
		/* default */
		* {font:normal 12px '돋음',dotum,Arial,Verdana,sans-serif;color:#666;letter-spacing:0px;line-height:120%;}
		body {height:100%;margin:0;padding:0;}
		h1, h2, h3, h4, h5, h6, div, p, span, dl, dt, dd, ul, li, table, thead, tbody, tfoot, tr, td, caption, button, form {padding:0;margin:0;}
		frame, iframe {border:none;}
		fieldset {border:0;margin:0px;padding:0px;}
		legend {display:none;}
		img {border:none;vertical-align:top;}
		ol {list-style-position:outside;margin:0;padding:0 0 0 35px;}
		ol * {vertical-align:top;}
		ol li {margin:0;padding:0;}
		ul{list-style:none;}
		table {border-collapse:collapse;border:0;}
		select, input, textarea {vertical-align:middle;}
		br {letter-spacing: 0;}
		a, a:link, a:visited {color:#666;}
		a:hover, a:active {color:#df2428;text-decoration:underline;}
		strong {font-weight:bold;}

		.hideOlleh {text-indent:-9999px;font-size:0;line-height:0;}
		.errorWrap {width:420px;padding:20px 0 0 170px;margin:100px auto;background:url(/resource/images/error/bg_info.gif) no-repeat;}
		.errorText {padding:20px 0 0 0;line-height:130%;}
		.ects {line-height:150%;}
		.errorCopy {padding:20px 0 0 0;}
		.errorCopy span {margin-right:40px;font-size:11px;}
		.errorCopy img {vertical-align:middle;}

		h2.title {padding:20px 0 3px 0;font-weight:bold;}
		.infoList li {padding-bottom:3px;}
		.pt20 {padding-top:20px;}
		.pt20 a {display:inline-block;width:190px;height:21px;padding-top:9px;margin-right:10px;text-align:center;background:url(/resource/images/error/btn2.gif) no-repeat;color:#fff;text-decoration:none;}
		.pt20 a:hover {color:#df2428;}
	</style>
</head>
<body>
<script type="text/javascript" src="http://cfm.olleh.com/adobe/s_code.js"></script>
<script type="text/javascript">
	//본 페이지를 사용하는 모든 서비스는 아래 "서비스명기입"란에 해당 서비스의 명칭을 필수로 입력하여야 합니다.
	s.pageName="^www^errorPage^페이지오류(500)^서비스명기입";
	s.pageType = "errorPage";
	var s_code=s.t();if(s_code)document.write(s_code);
</script>
<!-- ollehWrap -->
	<div class="errorWrap">
		<h3 class="hideOlleh">에러 페이지</h3>
		<p><img src="/resource/images/error/errorTxt02.gif" alt="죄송합니다. 요청하신 페이지를 바르게 표시할 수 없습니다." /></p>
		<h2 class="title">아래와 같은 사유로 해당 페이지에 접속할 수 없습니다.</h2>
		<ul class="infoList">
			<li>① 서비스 수행 중에 장애가 발생한 경우</li>
			<li>② 일시적으로 이용량이 급격히 증가한 경우</li>
			<li>③ 해당 페이지에 대한 접근권한이 부여되지 않은 경우</li>
		</ul>
		<p class="errorText">
			잠시 후 다시 이용하여 주시기 바라며, <br />
			동일 문제가 반복될 경우 고객센터로 문의하여 주시기 바랍니다.
		</p>
		<p class="pt20">
			<a href="${returnUri }">이전 페이지로 이동 ▶</a>
			<a href="/nmimo/main.do">nMIMO 홈으로 이동 ▶</a>
		</p>
<!-- 		<p class="errorText ects"> -->
<!-- 			<strong>[전화로 문의하기]</strong> 집전화는 국번없이 100번, 핸드폰은 지역번호 + 100번 -->
<!-- 			<strong>[올레닷컴에서 문의하기]</strong>  -->
			<!--본 페이지를 사용하는 모든 서비스는 아래 "서비스명기입"란에 해당 서비스의 명칭을 필수로 입력하여야 합니다.-->
<!-- 			<a href="https://my.olleh.com:444/kt12/cs/faq/exMailQuestion.jsp" target="_top" onclick="trackClicks('errorPage500_서비스명기입', '^errorPage^페이지없음(500)^서비스명기입^이메일상담 바로가기');">이메일상담 바로가기</a>,  -->
<!-- 			<a href="https://my.olleh.com:444/kt12/cs/faq/sociallive24.jsp" target="_top" onclick="trackClicks('errorPage500_서비스명기입', '^errorPage^페이지없음(500)^서비스명기입^24시간 소셜상담 바로가기');">24시간 소셜상담 바로가기</a> -->
<!-- 		</p> -->
		<div class="errorCopy">
			<span>Copyright (c) kt corp. All rights reserved. </span>
			<a href="/nmimo/main.do" target="_top"><img src="/resource/images/error/olleh_logo.jpg" alt="nMIMO 메인으로가기" /></a>
		</div>
	</div>
<!-- //ollehWrap -->
</body>
</html>
