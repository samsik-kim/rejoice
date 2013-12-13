<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<div id="pop_area05">

	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pop_sl_tit02.gif' />" alt="이월처리 안내" /></h2>
	<p><span class="txtcolor02">${report.saleYm}</span>월 상품 판매 정산이 아래와 같이 이월되었습니다.</p>
	<p class="pop_txt7" style="overflow-x:hidden;">${report.rmtReason}</p>
	<div class="pop_btn">
		<a href="JavaScript:self.close()"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="확인" /></a>
	</div>

</div>
