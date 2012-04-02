<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
<!--
$(document).ready(function(){
	$("#stDt").datepicker();
	$("#enDt").datepicker();
	$("#options").tablesorter({
		sortList: [[0,0]], 
	});
	
});

//검색
function searchList(){
		$("#searchFrm").attr('action','/code/codeList.do') ;
		$("#searchFrm").submit();
}

//목록
function goList(currentPage){
	location.href="/code/codeList.do?currentPage="+currentPage;
}

//상세
function fn_detail(seqNo){
	$("#seqNo").val(seqNo);
	$("#detailFrm").attr('action','/code/codeDetail.do') ;
	$("#detailFrm").submit();
}
//-->
</script>
<div class="pmbox mar_b22">
<form name="searchFrm" id="searchFrm" method="post">
	<div class="fltl mar_trl20">
		시작일:<input type="text" name="stDt" id="stDt" class="w70" value="${info.stDt }"/>&nbsp;~
		종료일:<input type="text" name="enDt" id="enDt" class="w70" value="${info.enDt }"/>&nbsp;&nbsp;
		<select id="searchKey" name="searchKey" class="w128" title="검색조건선택">
			<option value="codeName" <c:if test="${info.searchKey eq 'codeName' }">selected</c:if>>종목명</option>
			<option value="codeNum" <c:if test="${info.searchKey eq 'codeNum' }">selected</c:if>>종목코드</option>
		</select>
	</div>
	<input type="text" id="searchValue" name="searchValue" class="w150" value="${info.searchValue }" v:required='trim' m:required="검색어를 입력하십시오." />
	<a href="#"><img id="searchBtn" src="/resource/images/common/btn_board_search.gif" alt="검색" onclick="javascript:searchList();"/></a>
</form>
</div>
<form name="detailFrm" id="detailFrm" method="post">
	<input type="hidden" name="seq" id="seq" />
	<input type="hidden" name="currentPage" value="${pageInfo.currentPage}" />
</form>
<div class="tstyleC">
<table width="550"   id="options" class="tablesorter">
	<caption>고객 리스트</caption>
	<colgroup>
		<col width="150"/>
		<col width="200" />
		<col width="100" />
		<col width="100" />
		<col width="100" />
		<col width="" />
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
			<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeName}</a></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeNum}</a></td>
			<td>${list.holdShare}</td>
			<td>${list.tel}</td>
			<td>${list.infoTel}</td>
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