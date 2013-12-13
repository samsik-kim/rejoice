<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String url = request.getContextPath() + "/adminMgr/adminLogin.omp"; 
	response.sendRedirect(url);
%>

<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.datepicker.regional['ko'] = {
			closeText: '닫기',
			prevText: '이전달',
			nextText: '다음달',
			currentText: '오늘',
			monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)','7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
			monthNamesShort: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)','7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
			dayNames: ['일','월','화','수','목','금','토'],
			dayNamesShort: ['일','월','화','수','목','금','토'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			dateFormat: 'yy-mm-dd', 
			firstDay: 0,
			isRTL: false
		};		
		$.datepicker.setDefaults($.extend({showMonthAfterYear: false}, $.datepicker.regional['ko']));
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '${pageContext.request.contextPath }/images/icon_calendar_01.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '${pageContext.request.contextPath }/images/icon_calendar_01.gif', buttonImageOnly: true});
		$("#datepicker1").datepicker($.datepicker.regional['ko']);
		$("#datepicker2").datepicker($.datepicker.regional['ko']);
	});	
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/ui-lightness/jquery-ui-1.7.1.custom.css" />
</head>

<h1>OMP_ADMIN_POC</h1>
<hr/>

<a href="<%= request.getContextPath() %>/category/listCategory.omp">카테고리관리 - 개발자PoC</a><br/>
<a href="<%= request.getContextPath() %>/workday/listWorkday.omp">기술지원  - work-day설정</a><br/>
<a href="<%= request.getContextPath() %>/issue/listIssue.omp">기술지원  - 이슈지원</a><br/>
<br/>

<a href="<%= request.getContextPath() %>/batch/makeMailSmsTarget.omp">배치-유료개발자만료 대상자선정</a><br/>
<a href="<%= request.getContextPath() %>/batch/sendMailSmsTarget.omp">배치-유료개발자만료 Mail/SMS발송</a><br/>
<br/>

<a href="<%= request.getContextPath() %>/contents/listContents.omp">상품관리</a><br/>
<br/>

<a href="<%= request.getContextPath() %>/notice/noticeUpdate.omp?scale=20">고객지원  - 공지사항 - 개발자PoC</a><br/>
<a href="<%= request.getContextPath() %>/forum/forumBoardList.omp">고객지원  - 게시물관리 - 포럼게시물</a><br/>
<a href="<%= request.getContextPath() %>/faq/faqList.omp">고객지원  - FAQ</a><br/>
<br/>

<a href="<%= request.getContextPath() %>/techsupport/knowList.omp">기술지원  - 지식베이스</a><br/>
<a href="<%= request.getContextPath() %>/techsupport/downloadWrite.omp">기술지원  - 다운로드</a><br/>
<br/>
<a href="<%= request.getContextPath() %>/memberDev/memberCondition.omp?scale=10">회원관리  - 유료개발자 - 현황관리</a><br/>
<a href="<%= request.getContextPath() %>/memberDev/memberJoin.omp?scale=10">회원관리  - 유료개발자 - 가입신청관리</a><br/>
<a href="<%= request.getContextPath() %>/memberDev/membership.omp?scale=10">회원관리  - 유료개발자 - 회원정보관리</a><br/>
<a href="<%= request.getContextPath() %>/memberDev/memberLeave.omp?scale=10">회원관리  - 유료개발자 - 탈퇴회원관리</a><br/>
<a href="<%= request.getContextPath() %>/memberDev/payList.omp?scale=10">회원관리  - 유료개발자 - 연회비 납입관리</a><br/>
<br/>


<br/>
<input type="text" id="datepicker1" />~<input type="text" id="datepicker2" />
