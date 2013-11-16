<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_12.gif' />" alt="更新紀錄管理" />
	<a href="#" class="help zindex2"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif' />" alt="" class="pad_b2" />
		<div class="helpbox">
		<div class="helpboxin">
			<p>可對販售產品之更新紀錄進行管理.</p>
		</div>
		</div>
	</a>
</h4>
<div class="tstyleC">
	<table summary="更新紀錄管理" class="w792 bbnone">
		<caption>更新紀錄管理</caption>
		<colgroup>
			<col width="18%" />
			<col width="18%" />
			<col />
			<col width="18%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit15.gif' />" alt="適用日期" /></th>
				<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit16.gif' />" alt="上傳日期" /></th>
				<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit17.gif' />" alt="內容" /></th>
				<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit18.gif' />" alt="修改/刪除" /></th>
			</tr>
		</thead>
	</table>
</div>
<div class="tstyleC h191" style="overflow-x:hidden;">
	<table summary="更新紀錄管理"  class="w792 wbreak">
		<caption>更新紀錄管理</caption>
		<colgroup>
			<col width="18%" />
			<col width="18%" />
                  <col />
                  <col width="18%" />
		</colgroup>
		<tbody>
		<c:choose>
			<c:when test="${not empty resultContentUpdate }">
				<c:forEach items="${resultContentUpdate}" var="contentUpdate">
					<c:if test="${contentUpdate.contsUpdDtNum == 1}">
					<tr>
						<td rowspan="${contentUpdate.contsUpdDtRow}">${contentUpdate.contsUpdDt}</td>
						<td>${contentUpdate.insDttm}</td>
						<td class="tit01 wbtd"><gm:string value='${contentUpdate.updateText}' /></td>
						<td class="brnone">
							<c:if test="${fn:trim(content.verifyPrgrYn) eq 'PD005399'  or fn:trim(content.verifyPrgrYn) eq 'PD005303'}">
								<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="修改"/></a> 
								<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="刪除"/></a>
							</c:if>
							<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="修改"/></a> 
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="刪除"/></a>
							</c:if>
						</td>
					</tr>
					</c:if>
					<c:if test="${contentUpdate.contsUpdDtNum != 1}">
					<tr>
						<td>${contentUpdate.insDttm}</td>
						<td class="tit01 wbtd">${contentUpdate.updateText}</td>
						<td class="brnone">
							<c:if test="${fn:trim(content.verifyPrgrYn) eq 'PD005399'  or fn:trim(content.verifyPrgrYn) eq 'PD005303'}">
								<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="修改"/></a> 
								<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="刪除"/></a>
							</c:if>
							<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="修改"/></a> 
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="刪除"/></a>
							</c:if>
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="4"><gm:string value='jsp.content.contentAndroidDevInfo.text.update.list02' /></td></tr>
				<tr><td colspan="4">&nbsp;</td></tr>
				<tr><td colspan="4">&nbsp;</td></tr>
				<tr><td colspan="4">&nbsp;</td></tr>
				<tr><td colspan="4">&nbsp;</td></tr>
			</c:otherwise>
		</c:choose>	
		</tbody>
	</table>
</div>