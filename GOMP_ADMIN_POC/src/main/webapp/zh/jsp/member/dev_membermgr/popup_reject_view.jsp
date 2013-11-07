<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 

<title>查看拒絕轉換緣由</title>

	<div id="popup_area_440">
		<h1>查看拒絕轉換緣由</h1>
		<p class="mb05"><strong class="c_06f"><g:html value="${devMember.mbrid}"/></strong>會員轉換申請被拒絕之緣由如下.</p>
		<p class="box01"><g:html value="${devMember.rejreason}"/></p>
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:self.close();"><strong>確定</strong></a>
		</div>
	</div>