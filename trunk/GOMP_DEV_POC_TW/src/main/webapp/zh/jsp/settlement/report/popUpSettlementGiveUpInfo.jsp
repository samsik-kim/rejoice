<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<div id="pop_area05">

	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pop_sl_tit03.gif' />" alt="송금포기 안내" /></h2>
	<p>상품 판매 정산이 아래와 같은 사유로 포기되었습니다.<br />재송금을 원하시면 02-123-4567로 문의 주십시오.</p>
	<p class="pop_txt7" style="overflow-x:hidden;">${report.rmtReason}</p>
	<div class="pop_btn">
		<a href="JavaScript:self.close()"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="확인" /></a>
	</div>

</div>