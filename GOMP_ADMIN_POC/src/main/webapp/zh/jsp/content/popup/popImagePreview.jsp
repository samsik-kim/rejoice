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
 
	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_02.gif' />" alt="預覽-上傳之圖片將變換為以下形式" /></h2>
	<div class="viewbox" style="overflow-x:hidden;">
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000103}" alt="預覽" width="207" height="311" /></p>
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000104}" alt="預覽" width="207" height="311" /></p>
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000105}" alt="預覽" width="207" height="311" /></p>
		<p class="imgbox fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000106}" alt="預覽" width="207" height="311" /></p>
	</div>
 
</div>
