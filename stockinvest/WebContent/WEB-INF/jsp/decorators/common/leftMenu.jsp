<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
/*
 * Slide Menu
 */
$(document).ready(function() {
	$('#leftmenu ul').hide();
	$('#leftmenu li a').click(function() {
		var checkElement = $(this).next();
		if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
			$('#leftmenu ul:visible').slideUp('slow');
			return false;
		}
		if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
			checkElement.slideDown('slow');
			return false;
		}
	});
});
</script>

<ul id="leftmenu">
	<li><a href="#">계정관리</a></li>
	<li><a href="/code/codeList.do">코드관리</a></li>
	<li>
		<a href="#">게시판관리</a>
		<ul>
			<li><a href="#">종합정보</a></li>
			<li><a href="#">문재인</a></li>
			<li><a href="#">턴어라운드</a></li>
		</ul>
	</li>
</ul>
