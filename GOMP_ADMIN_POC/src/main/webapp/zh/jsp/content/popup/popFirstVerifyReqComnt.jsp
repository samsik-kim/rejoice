<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<div class="layerwrap">
 
	<div id="pop_area01">
 	<form id="verifyReqFrm" name="verifyReqFrm" method="post">
 	<input type="hidden" name="content.cid" value="${content.cid}" />
 	<input type="hidden" name="tabGbn" value="${tabGbn}" />
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_04.gif' />" alt="產品請審" /></h2>
		<p class="pop_txt4 txt_bold">是否要進行<span class="txtcolor06"><g:html value="${content.prodNm}"/></span>請審?</p>
		<div class="appbox">
			<ul>
				<li>審核過程中不得修改產品資訊, 於管理者核准前可’ <br />取消請審’.</li>
			</ul>
		</div>
		<div class="pop_btn">
			<a href="javascript:verifyReq('${content.cid}', '${tabGbn}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="是" id="verifyReqBtn" /></a>
			<a href="javascript:closePopupLayer('popVerifyReq');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif' />" alt="否" /></a>
		</div>
		<p class="pop_close"><a href="javascript:closePopupLayer('popVerifyReq');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif' />" alt="close" /></a></p>
 	</form>
	</div>
</div>
