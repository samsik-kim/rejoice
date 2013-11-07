<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>取消購買</title>
<div id="popup_area_440">
	<h1>取消購買</h1>
	<table class="pop_tabletype01">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%">
		</colgroup>
		<tbody>
		<tr>
			<th>帳號</th>
			<td><g:html value="${purchase.mbrId}" /></td>
		</tr>
		<tr>
			<th>聯絡電話</th>
			<td><g:html value="${purchase.hpno}" /></td>
		</tr>
		<tr>
			<th>地址</th>
			<td><g:html value='${purchase.addr}' /></td>
		</tr>
		<tr>
			<th>取消日期</th>
			<td><g:html value="${purchase.prchsCnclDtm}" format="L####-##-## ##:##"/></td>
		</tr>
		</tbody>
	</table>
	<div class="pop_btn">
		<a href="javascript:self.close();" class="btn_s"><strong>確定</strong></a>
	</div>
</div><!-- //contents -->