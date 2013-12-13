<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
	// faq
	function faq(num, faqId, ctgrCd) {
		var obj = document.getElementById("answer" + num);
		var total = document.getElementById("count").value;
		
		if (obj.style.display == "block"){
			for (i=0; i<total ; i++) {
				document.getElementById("answer"+i).style.display = "none";
				$("#question" + i + "td").removeAttr("class");
				$("#answer" + i + "td").removeAttr("class");
			}
			
			obj.style.display = "none";
		} else {
			for (i=0; i<total ; i++) {
				document.getElementById("answer"+i).style.display = "none";
				
				$("#question" + i + "td").removeAttr("class");
				$("#answer" + i + "td").removeAttr("class");
			}
			
			$("#question" + num + "td").attr("class", "question");
			$("#answer" + num + "td").attr("class", "answer");
			
			obj.style.display = "block";

			$.ajax({
				url: env.contextPath + '/community/ajaxFaqHitIncrease.omp',
				type	: "POST",
				data 	: "faq.faqId=" + faqId + "&faq.ctgrCd=" + ctgrCd,
				async	: false,		
				cache	: false
			});
		}
	}
</script>

<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원문의 <strong>&gt;</strong> <span>FAQ</span></p>
		<h3><img src="../${ThreadSession.serviceLocale.language}/images/uc/uc_title02.gif" alt="FAQ" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<div class="pmbox mar_b22">
			<ul class="tab03">
				<c:forEach items="${ctgrList }" var="ctgr">
					<a href="<c:url value='/community/listFaq.omp?faq.ctgrCd=${ctgr.ctgrCd }' />"><li><g:text value="${ctgr.ctgrNm }" /></li></a>
				</c:forEach>
			</ul>
		</div>

		<div class="faqlist">
			<table summary="FAQ">
				<caption>FAQ</caption>
				<colgroup>
					<col />
				</colgroup>
				<tbody>
					<c:forEach items="${list }" var="faq" varStatus="status">
						<tr id="question${status.index }">
							<td id="question${status.index }td">
								<a href="javascript:faq('${status.index }', '${faq.faqId }', '${faq.ctgrCd }');"><g:text value="${status.count }. ${faq.title }" /></a>
								<c:if test="${faq.downYn eq 'Y' }">
									&nbsp;<a href="<c:url value="/fileSupport/bbsFileDown.omp">
															<c:param name="bnsType" value="common.path.http-share.common.faq"/>
															<c:param name="filePath" value="${faq.down_path}"/>
															<c:param name="fileName" value="${faq.down_ofnm}"/>
															</c:url>" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_addfile.gif'/>" alt="첨부파일" /></a>
								</c:if>
								<c:if test="${faq.imgYn eq 'Y' }">
									&nbsp;<a href="<c:url value="/fileSupport/bbsFileDown.omp">
															<c:param name="bnsType" value="common.path.http-share.common.faq"/>
															<c:param name="filePath" value="${faq.img_path}"/>
															<c:param name="fileName" value="${faq.img_ofnm}"/>
															</c:url>" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_addimg.gif'/>" alt="첨부이미지" /></a>
								</c:if>
							</td>
						</tr>
						<tr id="answer${status.index }" style="display: none">
							<td id="answer${status.index }td">${faq.dscr }</td>
						</tr>
					</c:forEach>
					<c:choose>
						<c:when test='${not empty list }'>
							<input type="hidden" id="count" name="count" value="${fn:length(list) }" />
						</c:when>
						<c:otherwise>
							<input type="hidden" id="count" name="count" value="0" />
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->
</div>
