<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!-- Table Area -->
<table summary="리스트" class="ListTable"  id="myWorkStandbyListTable" name="myWorkStandbyListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:100px">서비스구분</th>
			<th style="width:240px">제 목</th>
			<th style="width:200px">등록일시</th>
			<th style="width:100px">발송예정일</th>
			<th style="width:100px">검토협의</th>
			<th style="width:100px">114협의</th>
			<th style="width:100px">발송승인</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.dataList}" varStatus="listIndex" var="data">
		<tr>
			<td>${data.msgDivVal}</td>
			<td class="l"><a href="#" onclick="javascript:doPostDetail('${data.wrkId}','/myWork/standbyListDetail.do');">${data.msgTitleNm}</a></td>
			<td>${data.cretDt}</td>
			<td>${data.rsrvDt}</td>
			<td>${data.trmRqtCascnt}</td>
			<td>${data.trmSttusVal}</td>
			<td>${data.wrkId}</td>
		</tr>
		</c:forEach>
		<c:if test="${pageInfo.totalCount == 0}" >
		<tr>
			<td colspan="7">
				해당게시물이 없습니다.
			</td>
		</tr>
		</c:if>
	</tbody>
</table>

<c:if test="${pageInfo.totalCount > 0}" >
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
</c:if>