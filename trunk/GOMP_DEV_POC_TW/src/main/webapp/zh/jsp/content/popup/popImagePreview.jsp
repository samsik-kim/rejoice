<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<script type="text/javascript">
<!--
//-->
</script>

<div id="pop_area03">
 
	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_02.gif' />" alt="미리보기-등록하신 이미지는 아래와같이 변환되어 사용됩니다." /></h2>
	<div class="viewbox" style="overflow-x:hidden;">
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000103}" alt="미리보기" width="207" height="311" /></p>
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000104}" alt="미리보기" width="207" height="311" /></p>
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000105}" alt="미리보기" width="207" height="311" /></p>
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000106}" alt="미리보기" width="207" height="311" /></p>
	</div>
 
</div>
