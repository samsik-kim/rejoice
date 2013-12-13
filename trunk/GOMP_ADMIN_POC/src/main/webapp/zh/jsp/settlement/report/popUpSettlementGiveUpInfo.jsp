<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<div id="pop_area05">

	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pop_sl_tit03.gif' />" alt="匯款失敗通知" /></h2>
	<p>產品銷售之結算帳款, 未能完成匯款, 原因如下<br />若要再次請款, 請播打 02-555-5666.</p>
	<p class="pop_txt7" style="overflow-x:hidden;">${report.rmtReason}</p>
	<div class="pop_btn">
		<a href="JavaScript:self.close()"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="確定" /></a>
	</div>

</div>