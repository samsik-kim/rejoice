<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function(){
		
		$("#searchWord").keypress(function(event) {
			 if(event.keyCode == 13) {
				 $("form[name=listNotice] input[name=notice\\.page\\.no]").val(1);
					
				$("#listNotice").attr("action", env.contextPath + "/community/listNotice.omp");
				$("#listNotice").submit();
			  	
				return false;
			 }
		});
	});

	$(function(){
		$("#searchBtn").click(function(){
			$("form[name=listNotice] input[name=notice\\.page\\.no]").val(1);
			
			$("#listNotice").attr("action", env.contextPath + "/community/listNotice.omp");
			$("#listNotice").submit();
			
			return false;
		});
	});

	function viewLink(noticeId) {
		$("#noticeId").val(noticeId);
		$("#listNotice").attr("method", "get");
		$("#listNotice").attr("action", env.contextPath + "/community/viewNotice.omp");
		$("#listNotice").submit();
	}

	function goPage(no) {
		$("form[name=listNotice] input[name=notice\\.page\\.no]").val(no);
		$("#listNotice").submit();
	}

</script>

<form id="listNotice" name="listNotice" method="post" action="">
	<input type="hidden" name="notice.page.no" value="${notice.page.no}"/>
	<input type="hidden" id="noticeId" name="notice.noticeId"/>

<!-- Content Area S -->
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원문의 <strong>&gt;</strong> <span>공지사항</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title01.gif' />" alt="공지사항" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<div class="pmbox mar_b22">
			<div class="fltl mar_trl20">
				<select id="searchType" name="notice.searchType" class="w145" title="검색조건선택">
					<option value="title" selected>제목</option>
					<option value="title_dscr" <c:if test="${notice.searchType eq 'title_dscr' }">selected</c:if>>제목+내용</option>
				</select>
			</div>
			<input type="text" id="searchWord" name="notice.searchWord" class="w410"  value="<g:text value='${notice.searchWord}'/>" title="검색어를 입력해주세요" />
			<input type="image" id="searchBtn" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_search1.gif' />" alt="검색" />
		</div>
		<div class="notice">
			<table summary="공지사항 목록">
				<caption>공지사항 목록</caption>
				<colgroup>
					<col width="7%" />
					<col />
					<col width="13%" />
					<col width="11%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit01.gif' />" alt="번호" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit02.gif' />" alt="제목" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit03.gif' />" alt="날짜" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit04.gif' />" alt="조회" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${not empty list}">
						<c:forEach items="${list}" var="item" varStatus="status">
						<tr>
							<td>${notice.page.totalCount - item.rnum + 1}</td>
							<td class="tit01">
								<a href="javascript:viewLink('${item.noticeId}');"><g:text value="${item.title}" /></a>
								<c:if test="${item.downYn eq 'Y' }">
									&nbsp;<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_addfile.gif'/>" alt="첨부파일" />
								</c:if>
							</td>
							<td ><g:text value="${item.insDttm}" format="L####-##-##" /></td>
							<td > ${item.hit} </td>
						</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">검색된 게시물이 없습니다.</td>
						</tr>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<!-- paging -->
		<div align="center">
			<g:pageIndex item="${list}"/>
		</div>
		<!-- //paging -->

	</div><!-- //CONTENT TABLE E-->

</div><!-- //Content Area E -->

</form>