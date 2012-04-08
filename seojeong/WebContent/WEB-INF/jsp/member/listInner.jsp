<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="tstyleC">
<table width="600"   id="options" class="tablesorter">
	<caption>고객 리스트</caption>
	<colgroup>
		<col width="50" />
		<col width="50" />
		<col width="150"/>
		<col width="150" />
		<col width="100" />
		<col width="100" />
		<col width="100" />
		<col width="" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">선택</th>
			<th scope="col">순번</th>
			<th scope="col">고객명</th>
			<th scope="col">휴대폰</th>
			<th scope="col">당첨횟수</th>
			<th scope="col">방문횟수</th>
			<th scope="col">방문일</th>
			<th scope="col">등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${pageInfo.totalCount<=0}">
			<tr class="no-list"><td colspan="8">
				조회 내용이 없습니다.
			</td></tr>
		</c:if>
		<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i">
		<tr>
			<td><input type="checkbox" name="delch" value="${list.seq}"></td>
			<td><div>${list.rnum}</div></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seq}');">${list.memberNm}</a></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seq}');">${list.mdn}</a></td>
			<td>${list.winCnt}</td>
			<td>${list.vstCnt}</td>
			<td>${list.vstDt}</td>
			<td>${list.regDt}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<table align="right">
	<tr>
		<td align="right">
			<a href="#"><img id="delBtn" src="/resource/images/common/btn_del.gif" alt="삭제하기" /></a>	
		</td>
	</tr>
</table>
<div class="pagination"><!-- pagination -->
		<dnc:PageNavigation pages="${pageInfo}" link="goList" pageIndexName="currentPage" styleClass="paging" 
			firstImg="${pageContext.request.contextPath }/resource/images/common/btn_ppre.gif"  
			prevImg="${pageContext.request.contextPath }/resource/images/common/btn_pre.gif" 
			nextImg="${pageContext.request.contextPath }/resource/images/common/btn_next.gif" 
			lastImg="${pageContext.request.contextPath }/resource/images/common/btn_nnext.gif"
			image="true" linkScript="true" 
		/>					
</div><!-- // pagination -->