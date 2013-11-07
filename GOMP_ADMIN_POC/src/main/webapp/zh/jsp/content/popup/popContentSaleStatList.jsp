<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<div id="pop_area04">
	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_01.gif'/>" alt="產品狀態變更之詳細紀錄" /></h2>
	<h4 class="h44"><g:html value='${content.prodNm}'/></h4>
	<div class="btstyleB" >
		<table summary="序號, 變更日期, 變更紀錄" class="w412">
			<caption>序號, 變更日期, 變更紀錄</caption>
			<colgroup>
				<col width="12%" />
				<col width="24%" />
				<col />
			</colgroup>
			<thead>
				<tr>
					<th><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_txt_01.gif'/>" alt="序號" /></th>
					<th><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_txt_02.gif'/>" alt="變更日期" /></th>
					<th class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_txt_03.gif'/>" alt="變更紀錄" /></th>
				</tr>
			</thead>
		</table>
	</div>
	<div class="btstyleB h184" style="overflow-x:hidden;">
		<table summary="序號, 變更日期, 變更紀錄" class="w412 btnone">
			<caption>序號, 變更日期, 變更紀錄</caption>
			<colgroup>
				<col width="12%" />
				<col width="24%" />
				<col />
			</colgroup>
			<tbody>
				<c:choose>
					<c:when test="${not empty SaleStatHistList}">
						<c:forEach items="${SaleStatHistList}" var="saleStatHist" varStatus="listIndex">	
							<tr>
								<td>${listIndex.index + 1}</td>
								<td>${saleStatHist.chngDttm}</td>
								<td class="tit01">
									<c:choose>
										<c:when test="${saleStatHist.saleStat eq CONST.CONTENT_SALE_STAT_NA}"><gm:string value="jsp.content.contentSaleStatList.text.saleStat01"/></c:when>
										<c:when test="${saleStatHist.saleStat eq CONST.CONTENT_SALE_STAT_WAIT}"><gm:string value="jsp.content.contentSaleStatList.text.saleStat02"/></c:when>
										<c:when test="${saleStatHist.agoSaleStat eq CONST.CONTENT_SALE_STAT_ING && saleStatHist.saleStat eq CONST.CONTENT_SALE_STAT_ING}"><gm:string value="jsp.content.contentSaleStatList.text.saleStat03"/></c:when>
							            <c:when test="${saleStatHist.agoSaleStat ne CONST.CONTENT_SALE_STAT_ING && saleStatHist.saleStat eq CONST.CONTENT_SALE_STAT_ING}"><gm:string value="jsp.content.contentSaleStatList.text.saleStat04"/></c:when>
										<c:when test="${saleStatHist.saleStat eq CONST.CONTENT_SALE_STAT_STOP}"><gm:string value="jsp.content.contentSaleStatList.text.saleStat05"/></c:when>
										<c:when test="${saleStatHist.saleStat eq CONST.CONTENT_SALE_STAT_RESTRIC}"><gm:string value="jsp.content.contentSaleStatList.text.saleStat06"/></c:when>
										<c:when test="${saleStatHist.saleStat eq CONST.CONTENT_SALE_STAT_UNREGIST}"><gm:string value="jsp.content.contentSaleStatList.text.saleStat07"/></c:when>	
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>		
			</tbody>
		</table>
	</div>
	
</div>
