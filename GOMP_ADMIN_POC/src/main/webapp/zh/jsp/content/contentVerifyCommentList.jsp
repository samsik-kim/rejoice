<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>產品現狀 &gt;產品管理 &gt; 產品上架/管理</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="-1"/> 
<meta http-equiv="Pragma" content="no-cache"/> 
<meta http-equiv="Cache-Control" content="no-cache"/>

<link href="<c:url value='/${ThreadSession.serviceLocale.language}/css/content.css' />" rel="stylesheet" type="text/css" />
<link type="text/css" href="<c:url value="/js/jquery/ui/css/smoothness/jquery-ui-1.8.10.custom.css"/>" rel="stylesheet" />
<!-- script src="http://jquery-ui.googlecode.com/svn/tags/latest/external/jquery.bgiframe-2.1.2.js" type="text/javascript"></script -->

<script type="text/javascript" src="<c:url value="/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery-ui-1.8.10.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/jsTree/jquery.jstree.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.cookie.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.MultiFile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.selectbox-1.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/content.js"/>"></script>

<script type="text/javascript" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_txt01.gif' />/js/common.js"></script>
<script type="text/javascript" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_txt01.gif' />/js/dev.common.js"></script>
<script type="text/javascript" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_txt01.gif' />/js/xtractor_cookie.js"></script>

<script type="text/javascript" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_txt01.gif' />/js/content.management.js"></script>

</head>
 
<body>
<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_13.gif' />" alt="審核紀錄管理" />
	<a href="#" class="help zindex2"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif' />" alt="" class="pad_b2" />
		<div class="helpbox">
		<div class="helpboxin">
			<p>產品審核時上傳之審核內容可於此確認. </p>
			<p>所上傳內容將被審核管理者參考, 以便提供更加有效之審核流程.</p>
		</div>
		</div>
	</a>
</h4>
<div class="tstyleC">
<form >
	<table summary="請審日期,請審內容" class="w792 bbnone">
		<caption>請審日期,請審內容</caption>
		<colgroup>
			<col width="18%" />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit19.gif' />" alt="請審日期" /></th>
				<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit20.gif' />" alt="請審內容" /></th>		
			</tr>
		</thead>
	</table>
</div>
<div class="tstyleC h122 mar_b22" style="overflow-x:hidden;">
	<table summary="審核紀錄管理" class="w792 wbreak">
	<caption>審核紀錄管理</caption>
		<colgroup>
			<col width="18%" />
			<col />
		</colgroup>
		<tbody>
			<c:choose>
				<c:when test="${not empty resultVerifyCmntList}">
					<c:forEach items="${resultVerifyCmntList}" var="verifyCmnt" varStatus="listIndex">	
						<c:if test="${not empty verifyCmnt.verifyInsDttm}">
						<tr>
							<td>${verifyCmnt.verifyInsDttm}</td>
							<td class="tit02 brnone wbtd">
								<g:html value="${verifyCmnt.verifyCommentNm}"/>
							</td>
						</tr>
						</c:if>
						<c:if test="${empty verifyCmnt.verifyInsDttm}">
							<tr><td colspan="2"><gm:string value='jsp.content.contentVerifyCommentList.text.list02'/></td></tr>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="2"><g:html value='jsp.content.contentVerifyCommentList.text.list02'/></td></tr>
				</c:otherwise>
			</c:choose>	
		</tbody>
	</table>
</form>	
</div>
</body>
</html>
