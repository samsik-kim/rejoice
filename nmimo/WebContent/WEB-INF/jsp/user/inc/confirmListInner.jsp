<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area // 사용자관리 -->
<table id="userListTable" name="userListTable" summary="리스트" class="ListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:100px">사용자ID</th>
			<th style="width:100px">사용자명</th>
			<th style="width:240px">부서명</th>
			<th style="width:200px">전화번호</th>
			<th style="width:200px">휴대폰번호</th>
			<th style="width:100px">승인여부</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.dataList}" varStatus="listIndex" var="data">
		<tr>
			<td><a href="#" onclick="javascript:goDetailPop('${data.userId}');">${data.userId}</a></td>
			<td><a href="#" onclick="javascript:goDetailPop('${data.userId}');">${data.userNm}</a></td>
			<td>${data.userRlvnDeptNm}</td>
			<td>${data.genlTelNo}</td>
			<td>${data.mphonNo}</td>
			<td>
				<c:if test="${data.userSttusVal == 'Y'}" >
					<span class="red">승인요청</span>
				</c:if>
				<c:if test="${data.userSttusVal == 'U'}" >
					<span class="red">승인완료</span>
				</c:if>
			</td>
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