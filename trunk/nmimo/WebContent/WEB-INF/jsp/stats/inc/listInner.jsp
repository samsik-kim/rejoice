<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area -->
<table summary="리스트" class="ListTable"  id="statsListTable" name="statsListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:300px">작업명(제목)</th>
			<th style="width:80px">메시지구분</th>
			<th style="width:100px">메시지특성</th>
			<th style="width:100px">발송부서</th>
			<th style="width:80px">발송자</th>
			<th style="width:100px">발송시작일</th>
			<th style="width:100px">발송종료일</th>
			<th style="width:120px">발송내역</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="l"><a href="#" onclick="javascript:Common.centerPopupWindow('/stats/popup/listInfoDetailPop.do','window',{width:920,height:273,scrollBars:'no'});">안내sms테스트</a></td>
			<td>MMS</td>
			<td>공지</td>
			<td>기술팀</td>
			<td>사용자2</td>
			<td>2013.00.00</td>
			<td>2013.00.00</td>
			<td><a href="#" onclick="javascript:Common.centerPopupWindow('/stats/popup/sendInfoDetailPop.do','window',{width:1100,height:750,scrollBars:'no'});" class="btn_sml"><span>발송내역조회</span></a></td>
		</tr>
	</tbody>



<!-- 	<tbody> -->
<%-- 	<c:if test="${pageInfo.totalCount<=0}"> --%>
<!-- 		<tr onMouseOver="this.style.background='#f4e9db'" onMouseOut="this.style.background=''"> -->
<!-- 			<td colspan="8">해당 조회 자료가 없습니다.</td> -->
<!-- 		</tr> -->
<%-- 	</c:if> --%>
<%-- 	<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i"> --%>
<!-- 		<tr> -->
<%-- 			<td><a href="#" id="listInfoDetailPop"><c:out value="${list.jobNm}" />&nbsp;</a></td>			 --%>
<%-- 			<td><c:out value="${list.service}" />&nbsp;</td> --%>
<%-- 			<td><c:out value="${list.jobKind1}" />&nbsp;</td> --%>
<%-- 			<td><c:out value="${list.department}" />&nbsp;</td> --%>
<%-- 			<td><c:out value="${list.userNm}" />&nbsp;</td> --%>
<%-- 			<td><c:out value="${list.insertDt}" />&nbsp;</td> --%>
<%-- 			<td><c:out value="${list.insertDt}" />&nbsp;</td> --%>
<!-- 			<td><a href="#"><img src="/resource/images/btn_sendinq.gif" alt="발송내용조회" /></a></td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- 	</tbody> -->
</table>

<div class="paging">
	<mimo:PageNavigation pages="${pageInfo}" link="goList" pageIndexName="pageIndex" 
		firstImg="/resource/images/btn_p_first.gif"
		prevImg="/resource/images/btn_p_prev.gif" 
		nextImg="/resource/images/btn_p_next.gif" 
		lastImg="/resource/images/btn_p_end.gif"
		image="true" 
		linkScript="true"
	/>
</div>