<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<form name="frm" method="post" id="frm">
	<input type="hidden" name="transferInfo.compNm" value="<g:tagAttb value="${transferInfo.compNm }"/>">
	<input type="hidden" name="profileInfo.mbrCatCd" value="<g:tagAttb value="${profileInfo.mbrCatCd }"/>">
	<input type="hidden" name="transferInfo.bizCatCd" value="<g:tagAttb value="${transferInfo.bizCatCd }"/>">
</form>
<script>
	$("#frm").attr('action', '${pageContext.request.contextPath }/mypage/mypageMemberTransFinsh.omp');
	$("#frm").submit();
</script>