<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="tstyleC">
<table width="600"   id="options" class="tablesorter">
	<caption>종목 코드 리스트</caption>
	<colgroup>
		<col width="100"/>
		<col width="80" />
		<col width="" />
		<col width="100" />
		<col width="100" />
		<col width="80" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">종목명</th>
			<th scope="col">종목코드</th>
			<th scope="col">지분보유</th>
			<th scope="col">전화번호</th>
			<th scope="col">정보연락처</th>
			<th scope="col">등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${pageInfo.totalCount<=0}">
			<tr class="no-list"><td colspan="6">								
				조회 내용이 없습니다.
			</td></tr>
		</c:if>
		<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i">
		<tr>
			<td style="text-align: left;"><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeName}</a></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeNum}</a></td>
			<td style="text-align: left;">${list.holdShare}</td>
			<td style="text-align: left;">${list.tel}</td>
			<td style="text-align: left;">${list.infoTel}</td>
			<td>${list.crtDate}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<div class="pagination"><!-- pagination -->
		<dnc:PageNavigation pages="${pageInfo}" link="goList" pageIndexName="currentPage" styleClass="paging" 
			firstImg="${pageContext.request.contextPath }/resource/images/common/btn_ppre.gif"  
			prevImg="${pageContext.request.contextPath }/resource/images/common/btn_pre.gif" 
			nextImg="${pageContext.request.contextPath }/resource/images/common/btn_next.gif" 
			lastImg="${pageContext.request.contextPath }/resource/images/common/btn_nnext.gif"
			image="true" linkScript="true" 
		/>					
</div><!-- // pagination -->