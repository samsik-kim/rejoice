<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr height="30"> 
     <td colspan="2"><font color="#AE0000"><B>[ 코드관리 ]</B></font></td>
  </tr>
  <tr> 
     <td colspan=2><table width="680" border="0" cellspacing="1" cellpadding="0" bgcolor="#00025B">
          <tr height="30" class="subject" align="center">  
            <td width="100">종목명</td>
            <td width="100">종목코드</td>
            <td width="100">지분보유</td>
            <td width="100">전화번호</td>
            <td width="100">정보연락처</td>
            <td width="100">등록일</td>
  </tr>
	<c:if test="${pageInfo.totalCount<=0}">
		<tr class="no-list"><td colspan="7">								
			조회 내용이 없습니다.
		</td></tr>
	</c:if>
	<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i">
	<tr>
		<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeName}</a></td>
		<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeNum}</a></td>
		<td>${list.holdShare}</td>
		<td>${list.tel}</td>
		<td>${list.info_tel}</td>
		<td>${list.crtDate}</td>
	</tr>
	</c:forEach>  
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