<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready( function() {
		$("#listBtn").click( function() {
			$("#frm").attr("action", env.contextPath + "/community/listNotice.omp");
			$("#frm").submit();
			
			return false;
		});
	});
</script>

<div id="contents_area"><!-- Title Area S -->
	<div id="ctitle_area">
	<p class="history">Home &gt; 개발지원센터 &gt; 개발지원문의 <strong>&gt;</strong>
	<span>공지사항</span></p>
	<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title01.gif' />" alt="공지사항" /></h3>
	</div>
	<div id="contents">

	<div class="notice">
	<table summary="공지사항 상세">
		<caption>공지사항 상세</caption>
		<colgroup>
			<col />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="tit02"><g:text value="${notice.title}" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="td01">
					<span class="fltl">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/td_tit01.gif' />" alt="날짜" /><g:text value="${notice.insDttm}" format="L####-##-##" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/td_tit02.gif' />" alt="조회" /><g:text value="${notice.hit}" />
					</span>
					<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/td_tit03.gif'/>" alt="첨부파일" />&nbsp; <img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_addfile.gif' />" alt="첨부파일" />
					&nbsp; <a href="<c:url value="/fileSupport/bbsFileDown.omp">
															<c:param name="bnsType" value="common.path.http-share.common.notice"/>
															<c:param name="filePath" value="${notice.down_path}"/>
															<c:param name="fileName" value="${notice.down_ofnm}"/>
															</c:url>" ><span class="uline"><g:text value="${notice.down_ofnm}" /></span></a>
				</td>
			</tr>
			<tr>
				<td class="tit03">
					<g:text value="${notice.dscr}" />
					<c:if test="${notice.imgYn eq 'Y'}">
						<br/><img src="${notice.img_path}" alt="첨부이미지" />
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	<div class="btnarea1 mar_t30">
	<input type="image" id="listBtn" name="listBtn" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif' />" title="목록" />
		<form id="frm" name="frm" method="post">	
			<input type="hidden" name="notice.page.no" value="${notice.page.no}"/>
			<input type="hidden" name="notice.searchType" value="${notice.searchType}" />
			<input type="hidden" name="notice.searchWord" value="${notice.searchWord}" />
		</form>
	</div>
	</div>
</div>