<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>審核結果之詳細內容</title>
</head>

<body>
<div id="pop_area02">
 
	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_06.gif' />" alt="審核結果之詳細內容 可瀏覽審核結果之詳情" /></h2>
	<div class="ssbox" style="overflow-x:hidden;">
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_14.gif' />" alt="重發認證信" /></h4>
		<div class="tstyleC mar_b22 w482">
			<table summary="重發認證信">
				<caption>重發認證信</caption>
				<colgroup>
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
				</colgroup>	
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit39.gif' />" alt="請審日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit40.gif' />" alt="審核完畢(預期)日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit41.gif' />" alt="審核狀態" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit42.gif' />" alt="審核結果" /></th>
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
                        		<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_cancel02.gif" alt="verify cancel" /></a>
                        	</c:if>
                        </td>
                        <td class="bbnone brnone">
                        	<c:if test="${CONST.AGREEMENT_STATUS_AGREE == contentVerifyDetail.agrmntStat }">
	                        	<gc:text code="${contentVerifyDetail.agrmntStat }" />
	                        </c:if>
	                        <c:if test="${CONST.AGREEMENT_STATUS_REJECT == contentVerifyDetail.agrmntStat }">
	                        	<span class="txtcolor02"> <gc:text code="${contentVerifyDetail.agrmntStat }" /> </span>
	                        </c:if>
	                    </td>
					</tr>
				</tbody>
			</table>
		</div>
		<c:forEach items="${subContentsVerifyList }" var="subContent">
		<h4 class="h44">${subContent.runFileNm }</h4>
		<div class="tstyleC mar_b22 w482">
			<table summary="Binary 檔資訊">
				<caption>Binary 檔資訊</caption>
				<colgroup>
					<col width="25%" />
					<col />
				</colgroup>	
				<tbody>
					<tr>
						<th class="tit05 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit31.gif' />" alt="Binary 檔資訊" /></th>
						<td class="btnone brnone lh160 tit04">
							minSdkVersion : ${subContent.vmVerMin } <br />  
							targetSdkVersion : ${subContent.vmVerTarget } <br />
							maxSdkVersion : ${subContent.vmVerMax } <br />
							versionCode : ${subContent.versionCode } <br />
							versionName : ${subContent.versionName } <br />
							Package : ${subContent.pkgNm } <br />
							LCDsize : <c:forEach items="${subContent.itemCdList }" var="lcdSize"><gc:text code="${lcdSize.itemCd }" />&nbsp; </c:forEach>
						</td>
					</tr>
					<tr>
						<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit32.gif' />" alt="App. 審核結果報告" /></th>
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
						<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit33.gif' />" alt="其他附加檔案" /></th>
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
						<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit34.gif' />" alt="審核結果詳細內容" /></th>
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
