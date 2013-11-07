<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<th class="tit01 tit03">選擇適用手機</th>
<td class="bgnone">
	可以已上傳之Binary 檔為標準選擇手機.&nbsp;
	<a href="#" id="searchALink" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_dmsearch.gif'/>" alt="搜尋手機" /></a>
	<div class="tstyleC mar_t10">
		<table summary="手機名稱,型號,OS 版本,LCD Size" class="w577 bbnone">
			<caption>手機名稱,型號,OS 版本,LCD Size</caption>
			<colgroup>
				<col width="7%" />
				<col width="18%" />
				<col width="25%" />
				<col width="18%" />
				<col />
			</colgroup>
			<thead>
			<tr>
				<th scope="col" class="tit06 btnone"><input type="checkbox" id="allSelPhoneChkBox" name="allSelPhoneChkBox"/></th>
				<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit21.gif'/>" alt="手機名稱" /></th>
				<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit22.gif'/>" alt="型號" /></th>
				<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit23.gif'/>" alt="OS 版本" /></th>
				<th scope="col" class="tit06 btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit24.gif'/>" alt="LCD Size" /></th>
			</tr>
			</thead>
		</table>
	</div>
	<div class="tstyleC hl185" style="overflow-x:hidden;">
	<table summary="手機名稱" class="w577">
		<caption>手機名稱</caption>
		<colgroup>
			<col width="7%" />
			<col width="18%" />
			<col width="25%" />
			<col width="18%" />
			<col />
		</colgroup>
		<tbody>
		<c:choose>
			<c:when test="${not empty resultMap.sprtPhoneList}">
				<c:forEach items="${resultMap.sprtPhoneList}" var="sprtPhone" varStatus="listIndex">	
				<tr>
					<td>
						<input type="checkbox" name="sprtPhoneModel" value="${sprtPhone.phoneModelCd}" 
							<c:if test="${not empty resultMap.targetPhoneList}">
							<c:forEach items="${resultMap.targetPhoneList}" var="targetPhone" varStatus="listIndex2">
								<c:if test="${sprtPhone.phoneModelCd == targetPhone.phoneModelCd}">checked="checked"</c:if>
							</c:forEach>
							</c:if>
						/>
					</td>
					<td>${sprtPhone.modelNm}</td>
					<td>
						<a href="javascript:gotoPhoneModelInfoList('${phone.mgmtPhoneModelNm}');">
						${sprtPhone.mgmtPhoneModelNm}
						</a>
					</td>
					<td>${sprtPhone.osVersion}</td>
					<td class="brnone">${sprtPhone.lcdSize}</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="5">&nbsp;</td></tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	 </table>
	 </div>
</td>