<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/commonTag.tld" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>상품 상세정보</title>
<script type="text/javascript" src="../inc/_pophead.js"></script>
</head>
<body class="popup">
	<div id="popup_area_440">
		<h1>상품상세정보</h1>
        <div class="detail">
			<img src="${CONF['omp.common.url.http-share.product']}${detail.filePos}" width="130" height="130" alt="" class="fl mr10" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" />
			<div class="fl">				
				<p><strong>${detail.prodNm}</strong> <br />
					${detail.prodDesc}
				</p>
				<ul>
					<li>- 가격 : ${detail.prodAmt}원 | 다운로드수 : ${detail.dwldCnt}</li>
					<li>- 등급 : ${detail.prodGrdCd}</li>
					<li>- 용량 : ${detail.fileSize}KB</li>
					<li>- 판매회원 : ${detail.mbrId}</li>
					<li>- 등록일 : ${detail.regDt2} / 업데이트일 : ${detail.updDt2}</li>
					<li>- Plat form : ${detail.platClsCd}</li><!-- 2011-03-16 -->
				</ul>
			</div>
        </div>
        
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:self.close();"><strong>닫기</strong></a>
		</div>
	</div>
    <!-- //contents -->
</body>
</html>
