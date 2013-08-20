<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area // KBN관리>전송정보 -->
<table  id="msgSendListTable" name="msgSendListTable" summary="리스트" class="ListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:200px">코드명</th>
			<th style="width:440px">작업명</th>
			<th style="width:100px">전송기간</th>
			<th style="width:100px">등록자</th>
			<th style="width:100px">전송상태</th>
		</tr>
	</thead>
	<tbody>
		<tr>
		  	<td>555</td>
			<td class="l">555</td>
			<td>555</td>
			<td>555</td>
			<td>555</td>
		</tr>
	</tbody>
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
