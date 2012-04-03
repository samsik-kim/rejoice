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
		headers: { 0:{sorter: false} }		
	});
	
	$("#delBtn").click(function(){
		var delArr = document.getElementsByName("delch");
		var delValues = "";
		for(var i=0; i<delArr.length; i++){
			delValues += delArr[i].value + ",";
		}
		if(confirm("삭제 하시겠습니까?")){
			$("#delVal").val(delValues);
			$("#frm").attr('action','/board/delete.do') ;
			$("#frm").submit();
		}
	});
	
	$("#excelBtn").click(function(){
		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		location.href = "/member/excel.do?stDt="+$("#stDt").val()+"&enDt="+$("#enDt").val();
	});
	
	
	$("#regBtn").click(function(){
		$("#searchFrm").attr('action','/board/insertBoardForm.do') ;
		$("#searchFrm").submit();
	});	
});

//검색
function searchList(){
		if($("#stDt").val() == "시작일") $("#stDt").val('');
		if($("#enDt").val() == "종료일") $("#enDt").val('');
		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		$("#searchFrm").attr('action','/board/boardList.do') ;
		$("#searchFrm").submit();
}

//목록
function goList(currentPage){
	location.href="/board/list.do?currentPage="+currentPage;
}

//상세
function fn_detail(seq){
	$("#seq").val(seq);
	$("#frm").attr('action','/board/boardDetail.do') ;
	$("#frm").submit();
}

//-->
</script>
<div class="pmbox mar_b22">
<form name="searchFrm" id="searchFrm" method="post">
	<div class="fltl mar_trl20">
		시작일:<input type="text" name="stDt" id="stDt" class="w70" value="${info.stDt }"/>&nbsp;~
		종료일:<input type="text" name="enDt" id="enDt" class="w70" value="${info.enDt }"/>&nbsp;&nbsp;
		<select id="searchKey" name="searchKey" class="w128" title="검색조건선택">
			<option value="codeNum" <c:if test="${info.searchKey eq 'codeNum' }">selected</c:if>>종목코드</option>
			<option value="codeName" <c:if test="${info.searchKey eq 'codeName' }">selected</c:if>>종목명</option>
			<option value="subject" <c:if test="${info.searchKey eq 'subject' }">selected</c:if>>제목</option>
			<option value="content" <c:if test="${info.searchKey eq 'content' }">selected</c:if>>내용</option>
		</select>&nbsp;
	</div>
	<input type="text" id="searchValue" name="searchValue" class="w150" value="${info.searchValue }" v:required='trim' m:required="검색어를 입력하십시오." />
	<a href="#"><img id="searchBtn" src="/resource/images/common/btn_board_search.gif" alt="검색" onclick="javascript:searchList();"/></a>
</form>
</div>
<!-- Excel Download -->
<div class="fltr mar_trl20">
	<a href="#"><img id="excelBtn" src="/resource/images/common/btn_excel.gif" alt="excel" /></a>
</div>
<div></div>
<div class="tstyleC">
<table width="600"   id="options" class="tablesorter">
	<caption>고객 리스트</caption>
	<colgroup>
		<col width="50" />
		<col width="80" />
		<col width="" />
		<col width="140" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">선택</th>
			<th scope="col">작성일</th>
			<th scope="col">제목</th>
			<th scope="col">종목</th>
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
			<td><input type="checkbox" name="delch" value="${list.seqNo}"></td>
			<td><div>${list.crtDate}</div></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.subject}</a></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeName}(${list.codeNum})</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<table align="right">
	<tr>
		<td align="right">
			<a href="#"><img id="regBtn" src="/resource/images/board/btn_regist.jpg" alt="등록" /></a>
			<a href="#"><img id="delBtn" src="/resource/images/board/btn_delete.jpg" alt="삭제하기" /></a>	
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