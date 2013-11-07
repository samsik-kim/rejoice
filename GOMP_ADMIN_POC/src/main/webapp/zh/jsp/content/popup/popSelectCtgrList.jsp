<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script language="javascript">
	
	function choiceContentCtgr() {
		
		if ($(':radio[name=dpCatNo]').is(':checked')) {
			var dpCatNo = $(':radio[name=dpCatNo]:checked').val();
			var checkedIndex = $(':radio[name=dpCatNo]').index($(':radio[name=dpCatNo]:checked'));
			var dpCatDepth = $(':hidden[name=ctgrDepth]:nth('+checkedIndex+')').val();
			var dpCatNm = $('span[name=content\\.dpCatNm]:nth('+checkedIndex+')').text();
		
			//$('#contentCtgrNm').html('<input type="hidden" name="content.ctgrCd" value="'+dpCatNo+'"/>'
			//						+'<input type="hidden" name="content.ctgrDepth"  value="'+dpCatDepth+'" />'
			//							+dpCatNm+'&nbsp;&nbsp;');
			
			$('#contentCtgrNm').text(dpCatNm);
			$(':hidden[name=content\\.ctgrDepth]').val(dpCatDepth);
			$(':hidden[name=content\\.ctgrCd]').val(dpCatNo);
		
			closePopupLayer('popSelectCtgrList');
		} else {
			alert("<gm:string value='jsp.content.contentCtgrList.msg.01'/>");
		}
	}
</script>

<div class="layerwrap">

	<div id="pop_area01">
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_03.gif' />" alt="選擇產品類別" /></h2>
		<p class="pop_txt3">請選擇產品類別 (只限1項)</p>
		<div class="catabox" style="overflow-x:hidden;">
			<ul>
				<c:choose>
				<c:when test="${not empty contentCategoryList}">	
					<c:forEach items="${contentCategoryList }" var="category" varStatus="listIndex">	
						<li>
							<input type="hidden" name="ctgrDepth" value="${category.dpCatDepth}" />
							<input type="radio" name="dpCatNo" value="${category.dpCatNo}"  <c:out value="${content.ctgrCd == category.dpCatNo ?  'checked=checked' : ''}" /> />
							&nbsp;<span name="content.dpCatNm">${category.dpCatNm}</span>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise><li><gm:string value='jsp.content.contentCtgrList.text.01'/></li></c:otherwise>
				</c:choose> 
			</ul>
		</div>
		
		<div class="pop_btn">
			<a href="javascript:choiceContentCtgr();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="確定" /></a>
			<a href="javascript:closePopupLayer('popSelectCtgrList');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif' />" alt="取消" /></a>
		</div>
		<p class="pop_close"><a href="javascript:closePopupLayer('popSelectCtgrList');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif' />" alt="close" /></a></p>
 
 