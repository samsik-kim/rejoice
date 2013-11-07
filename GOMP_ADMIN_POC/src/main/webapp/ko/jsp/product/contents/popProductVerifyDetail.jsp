<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	body.popup table {
		margin-bottom:15px
	}
</style>
<script type="text/javascript" language="javascript">
//<![CDATA[
$(document).ready(function(){
	$("#closeBtn").click(function(event){
		event.preventDefault();
		self.close();
	});
});
//]]>
</script>
</head>
<body>
<div id="popup_area_810">
		<h1>검증결과 상세내역</h1>
		<table class="pop_tabletype01">
			<colgroup>
				<col style="width:20%;">
				<col style="width:30%">
				<col style="width:20%;">
				<col style="width:30%">
			</colgroup>
			<tr>
				<th>검증요청일</th>
				<td><g:text format="L####-##-##" >${contentsVerify.insDttm }</g:text></td>
				<th>검증완료일</th>
				<td><g:text format="L####-##-##" >${contentsVerify.ctEndDt }</g:text></td>
			</tr>
			<!-- 
			<tr>
				<th>검증결과</th>
				<td><gc:html code="${contentsVerify.agrmntStat }"/></td>
			</tr>
			 -->
		</table>
		<c:forEach items="${subContentsList }" var="verify">
		<!-- <div>${verify.scid }</div> -->
		<table class="pop_tabletype01">
			<colgroup><col style="width:20%;"><col style="width:80%"></colgroup>
			<tbody>
			<tr>
				<th>프로비저닝</th>
				<td>
					<ul>
						<li>minSdkVersion : ${verify.vmVerMin }</li>
						<li>targetSdkVersion : ${verify.vmVerMax }</li>
						<li>maxSdkVersion : ${verify.vmVerTarget }</li>
						<li>versionCode : ${verify.versionCode }</li>
						<li>versionName : ${verify.versionName }</li>
						<li>package : ${verify.pkgNm }</li>
						<li>LCD Size : 
							<c:forEach items="${verify.lcdSizeList }" var="lcd" varStatus="k">
							<c:if test="${k.index gt 0}">, </c:if>${lcd }
							</c:forEach>
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<th>실행파일</th>
				<td>					  	
					<a href="<c:url value="/fileSupport/fileDown.omp">
							<c:param name="bnsType" value="common.path.share.product"/>
							<c:param name="filePath" value="${verify.runFilePos }"/>
							<c:param name="fileName" value="${verify.runFileNm }"/>
							</c:url>">${verify.runFileNm }</a>
				</td>
			</tr>
			<tr>
			<th>App. 검증 결과서</th>
				<td>
					<c:if test="${not empty verify.appCtResultFile  }">
					<a href="<c:url value="/fileSupport/fileDown.omp">
							<c:param name="bnsType" value="common.path.share.product"/>
							<c:param name="filePath" value="${verify.appCtResultFile }"/>
							<c:param name="fileName" value="${verify.appCtResultFileNm }"/>
							</c:url>">${verify.appCtResultFileNm }</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>기타첨부파일</th>
				<td>
					<c:forEach items="${verify.addFileList }" var="addFile" varStatus="k">
					<c:if test="${not empty addFile.ADDFILE  }">
					<a href="<c:url value="/fileSupport/fileDown.omp">
							<c:param name="bnsType" value="common.path.share.product"/>
							<c:param name="filePath" value="${addFile.ADDFILE }"/>
							<c:param name="fileName" value="${addFile.ADDFILENM }"/>
							</c:url>">${addFile.ADDFILENM }</a><br/>
					</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>검증결과 상세내역</th>
				<td>					  	
					<g:html value="${verify.appCtCmt }" />
				</td>
			</tr>
			</tbody>
		</table>
		</c:forEach>
		<div class="pop_btn" >
			<a class="btn" href="#" id="closeBtn"><strong>닫기</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>