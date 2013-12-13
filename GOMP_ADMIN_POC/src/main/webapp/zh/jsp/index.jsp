<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<head>
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
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '${pageContext.request.contextPath }/images/common/icon_calendar_01.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '${pageContext.request.contextPath }/images/common/icon_calendar_01.gif', buttonImageOnly: true});
		$("#datepicker1").datepicker($.datepicker.regional['ko']);
		$("#datepicker2").datepicker($.datepicker.regional['ko']);
	});	
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/ui-lightness/jquery-ui-1.7.1.custom.css" />
</head>

<body>
<!-- right area -->
<div class="contents_area">
<br/>
<input type="text" id="datepicker1" />~<input type="text" id="datepicker2" />
<br/>

<!-- 
<input type="text" id="zipcode1" name="zipcode1" class="write" style="width:50px;" onclick="javascript: popZipCodeList();" readonly="readonly"/>-<input type="text" id="zipcode2" name="zipcode2" class="write" style="width:50px;" onclick="javascript: popZipCodeList();" readonly="readonly"/> 
<a href="#" onclick="javascript: popZipCodeList(); return false;"><img src="${pageContext.request.contextPath }/images/button/btn_post.gif" alt="우편번호찾기" /></a> <br/>
<input type="text" id="addr1" name="addr1" class="write" style="width:550px;" onclick="javascript: popZipCodeList();" readonly="readonly"/><br/>
<input type="text" id="addr2" name="addr2" class="write" style="width:550px;" />
-->
</div>
</body>