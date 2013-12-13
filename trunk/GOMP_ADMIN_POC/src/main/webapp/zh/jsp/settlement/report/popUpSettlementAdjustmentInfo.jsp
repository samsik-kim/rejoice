<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<div id="pop_area05">

	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pop_sl_tit04.gif' />" alt="調帳金額通知" /></h2>
	<p><span class="txtcolor02"><g:text value="${report.saleYm}" format="L####-##" /></span>月份產品調帳總金額如下.</p>
	<p class="pop_txt7" style="overflow-x:hidden;">${report.adjReason}</p>
	<div class="pop_btn">
		<a href="JavaScript:self.close()"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="確定" /></a>
</div>

