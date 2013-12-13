<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>검증결과 상세내역</title>
</head>

<body>
<div id="pop_area02">
 
	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_06.gif' />" alt="검증결과 상세내역" /></h2>
	<div class="ssbox" style="overflow-x:hidden;">
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_14.gif' />" alt="검증정보" /></h4>
		<div class="tstyleC mar_b22 w482">
			<table summary="검증정보">
				<caption>검증정보</caption>
				<colgroup>
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
				</colgroup>	
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit39.gif' />" alt="검증요청일" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit40.gif' />" alt="검증완료(예정)일" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit41.gif' />" alt="검증상태" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit42.gif' />" alt="검증결과" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="bbnone"><g:text value="${contentVerifyDetail.ctInsDttm }" format="L####-##-##" /></td>
                        <td class="bbnone"><g:text value="${contentVerifyDetail.ctEndDt }" format="L####-##-##" /></td>
                        <td class="bbnone">
                        	<span class="txtcolor15"><gc:text code="${contentVerifyDetail.verifyPrgrYn }" /></span>
                        	<c:if test="${contentVerifyDetail.verifyPrgrYn eq 'PD005301'}">
                        		<a href="${pageContext.request.contextPath }/content/contentVerifyCancel.omp?cid=${contentVerifyDetail.cid }">
                        		<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_cancel02.gif" alt="검증취소" /></a>
                        	</c:if>
                        </td>
                        <td class="bbnone brnone"><gc:text code="${contentVerifyDetail.agrmntStat }" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<c:forEach items="${subContentsVerifyList }" var="subContent">
		<h4 class="h44">${subContent.runFileNm }</h4>
		<div class="tstyleC mar_b22 w482">
			<table summary="바이너리 파일정보">
				<caption>바이너리 파일정보</caption>
				<colgroup>
					<col width="25%" />
					<col />
				</colgroup>	
				<tbody>
					<tr>
						<th class="tit05 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit31.gif' />" alt="바이너리 파일정보" /></th>
						<td class="btnone brnone lh160 tit04">
							min OS SDK Version : ${subContent.vmVerMin }<br />
							target OS SDK Version : ${subContent.vmVerTarget }<br />
							max OS SDK Version : ${subContent.vmVerMax }<br />
							Version Code : ${subContent.versionCode }<br />
							Version Name : ${subContent.versionName }<br />
							Package Name : ${subContent.pkgNm }<br/>
							LCD Size : <c:forEach items="${subContent.itemCdList }" var="lcdSize"><gc:text code="${lcdSize.itemCd }" />&nbsp; </c:forEach>
						</td>
					</tr>
					<tr>
						<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit32.gif' />" alt="App. 검증 결과서" /></th>
						<td class="brnone tit04">
							<span class="uline">	 
								<a href="<c:url value="/fileSupport/fileDown.omp">
									<c:param name="bnsType" value="common.path.share.product"/>
									<c:param name="filePath" value="${subContent.appCtResultFile }"/>
									<c:param name="fileName" value="${subContent.appCtResultFileNm }"/>
									</c:url>"> ${subContent.appCtResultFileNm } </a>
							</span>
						</td>
					</tr>
					<tr>
						<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit33.gif' />" alt="기타첨부파일" /></th>
						<td class="brnone tit04">
							<span class="uline">
								<c:forEach items="${addFile }" var="aFile">
									<c:forEach items="${aFile }" var="file">
										<c:if test="${file.scid == subContent.scid }">
											<a href="<c:url value="/fileSupport/fileDown.omp">
											<c:param name="bnsType" value="common.path.share.product"/>
											<c:param name="filePath" value="${file.addFile }"/>
											<c:param name="fileName" value="${file.addFileNm }"/>
											</c:url>"> ${file.addFileNm } </a> <br/>
										</c:if>
									</c:forEach>  
								</c:forEach>
							</span>
						</td>
					</tr>
					<tr>
						<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit34.gif' />" alt="검증결과 상세내역" /></th>
						<td class="brnone tit04"> 
							<a href="#"> ${subContent.appCtCmt } </a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:forEach>
	</div>

</div>

</body>
</html>
