<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_12.gif' />" alt="업데이트 이력 관리" />
	<a href="#" class="help zindex2"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif' />" alt="" class="pad_b2" />
		<div class="helpbox">
		<div class="helpboxin">
			<p>판매하는 상품에 대한 업데이트 이력을 관리할 수있습니다.</p>
			<p>등록 또는 삭제한 업데이트 이력은 검증절차 이후에 반영되며, 판매 중인 상태에서는 반영되지 않습니다.</p>
		</div>
		</div>
	</a>
</h4>
<div class="tstyleC">
	<table summary="적용일자,등록일자,내용,수정/삭제" class="w792 bbnone">
		<caption>적용일자,등록일자,내용,수정/삭제</caption>
		<colgroup>
			<col width="18%" />
			<col width="18%" />
			<col />
			<col width="18%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit15.gif' />" alt="적용일자" /></th>
				<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit16.gif' />" alt="등록일자" /></th>
				<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit17.gif' />" alt="내용" /></th>
				<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit18.gif' />" alt="수정/삭제" /></th>
			</tr>
		</thead>
	</table>
</div>
<div class="tstyleC h191" style="overflow-x:hidden;">
	<table summary="업데이트 이력관리" class="w792 wbreak">
		<caption>업데이트 이력관리</caption>
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
						<td class="tit01 wbtd">${contentUpdate.updateText}</td>
						<td class="brnone">
							<c:if test="${fn:trim(content.verifyPrgrYn) eq 'PD005399'  or fn:trim(content.verifyPrgrYn) eq 'PD005303'}">
								<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="수정"/></a> 
								<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="삭제"/></a>
							</c:if>
							<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="수정"/></a> 
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="삭제"/></a>
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
								<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="수정"/></a> 
								<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="삭제"/></a>
							</c:if>
							<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif' />" alt="수정"/></a> 
								<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' />" alt="삭제"/></a>
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