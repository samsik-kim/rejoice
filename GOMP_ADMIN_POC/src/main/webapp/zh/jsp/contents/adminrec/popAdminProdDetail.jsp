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
<title>產品詳細資訊</title>
<script type="text/javascript" src="../inc/_pophead.js"></script>
</head>
<body class="popup">
	<div id="popup_area_440">
		<h1>產品詳細資訊</h1>
        <div class="detail">
			<img src="${CONF['omp.common.url.http-share.product']}${detail.filePos}" width="130" height="130" alt="" class="fl mr10" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" />
			<div class="fl">				
				<p><strong>${detail.prodNm}</strong> <br />
					${detail.prodDesc}
				</p>
				<ul>
					<li>- 價格 : NT$ <g:text value="${detail.prodAmt}" format="R###,###" /> | 下載次數 : ${detail.dwldCnt}</li>
					<li>- 等級 : ${detail.prodGrdCd}</li>
					<li>- 容量 : ${detail.fileSize}KB</li>
					<li>- 開發商 : ${detail.mbrId}</li>
					<li>- 上架日期 : <g:text value="${detail.regDt}" format="L####-##-##" /> / 更新日期 : <g:text value="${detail.updDt}" format="L####-##-##" /></li>
					<li>- Plat form : ${detail.platClsCd}</li><!-- 2011-03-16 -->
				</ul>
			</div>
        </div>
        
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:self.close();"><strong>關閉</strong></a>
		</div>
	</div>
    <!-- //contents -->
</body>
</html>
