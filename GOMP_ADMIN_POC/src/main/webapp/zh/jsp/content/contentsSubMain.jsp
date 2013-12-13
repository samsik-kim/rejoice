<%@page language="java" contentType="text/html; charset=UTF-8"%>  
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">
<!--
	$(document).ready(function() {

		for(var i=0;i < $('input[name=newRegistContentFlag]').length;i++) {
 	 		if ($('input[name=newRegistContentFlag]:nth('+i+')').val() == 'Y') {
 	 	    	$('img[name=newContsIcon]:nth('+i+')').attr('style', 'display:');
 	 	    }
 	 	}
		
		for(var i=0;i < $('input[name=newSaleWaitContentFlag]').length;i++) {
 	 		if ($('input[name=newSaleWaitContentFlag]:nth('+i+')').val() == 'Y') {
 	 	    	$('img[name=newSaleContsIcon]:nth('+i+')').attr('style', 'display:');
 	 	    }
 	 	}
		
		for(var i=0;i < $('input[name=newRequestStartContentflag]').length;i++) {
 	 		if ($('input[name=newRequestStartContentflag]:nth('+i+')').val() == 'Y') {
 	 	    	$('img[name=newReqContsIcon]:nth('+i+')').attr('style', 'display:');
 	 	    }
 	 	}
		
		for(var i=0;i < $('input[name=newRequestEndContentflag]').length;i++) {
 	 		if ($('input[name=newRequestEndContentflag]:nth('+i+')').val() == 'Y') {
 	 	    	$('img[name=newComContsIcon]:nth('+i+')').attr('style', 'display:');
 	 	    }
 	 	}
	});

	function gotoContentsList(objName) {
		
		var saleStat = "";
		if (objName == "CONTENT_SALE_STAT_NA") {			
			//location.href =  env.contextPath + "/content/contentsStatusList.omp?content.saleStat=${CONST.CONTENT_SALE_STAT_NA}";
			saleStat = '${CONST.CONTENT_SALE_STAT_NA}';
	
			var frm = $('#editForm');
			$('#saleSearchType').val(saleStat);
			frm.attr("target", "_self");	
			frm.attr("action", env.contextPath + "/content/contentsStatusList.omp");
			frm.submit();
			
		} else if (objName == "CONTENT_SALE_STAT_WAIT") {	
			//location.href =  env.contextPath + "/content/contentsStatusList.omp?content.saleStat=${CONST.CONTENT_SALE_STAT_WAIT}";
			saleStat = '${CONST.CONTENT_SALE_STAT_WAIT}';
	
			var frm = $('#editForm');
			$('#saleSearchType').val(saleStat);
			frm.attr("target", "_self");	
			frm.attr("action", env.contextPath + "/content/contentsStatusList.omp");
			frm.submit();
		} else if (objName == "CONTENT_SALE_STAT_ING") {	
			//location.href =  env.contextPath + "/content/contentsStatusList.omp?content.saleStat=${CONST.CONTENT_SALE_STAT_ING}";
			saleStat = '${CONST.CONTENT_SALE_STAT_ING}';
	
			var frm = $('#editForm');
			$('#saleSearchType').val(saleStat);
			frm.attr("target", "_self");	
			frm.attr("action", env.contextPath + "/content/contentsStatusList.omp");
			frm.submit();
		} else if (objName == "VERIFY_COM") {				
			location.href =  env.contextPath + "/content/getContentsVerifyList.omp?ctVerify.searchVerifyPrgrYn=${CONST.CODE_VERIFY_END}&referer=subMain";
		} else if (objName == "VERIFY_ING") 	{		
			location.href =  env.contextPath + "/content/getContentsVerifyList.omp?ctVerify.searchVerifyPrgrYn=${CONST.CODE_VERIFY_ING}&referer=subMain";
		}
		
	}

//-->
</script>


<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home <strong>&gt;</strong> <span>產品上架/管理</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title00.gif'/>" alt="產品上架/管理" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<form id="editForm" name="editForm" method="post">
		<input type="hidden" id="saleSearchType" name="content.saleSearchType" value="">
		
		<div class="pmbox mar_b22">
			<dl class="dlist01 dlfrt">
				<dt><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt01.gif'/>" alt="販售中產品" /></dt>
				<dd><a href="javascript:gotoContentsList('CONTENT_SALE_STAT_ING');"><span class="txtcolor02"><g:text value="${resultContentsMap.salesContents}" format="R#,###,###,###"/></span></a>件</dd>
			</dl>
			<dl class="dlist01">
				<dt><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt02.gif'/>" alt="新上架產品" /></dt>
				<dd><a href="javascript:gotoContentsList('CONTENT_SALE_STAT_NA');"><span class="txtcolor02"><g:text value="${resultContentsMap.newRegistCount}" format="R#,###,###,###"/></span></a>件</dd>
			</dl>
			<dl class="dlist01">
				<dt><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt03.gif'/>" alt="審核中產品" /></dt>
				<dd><a href="javascript:gotoContentsList('VERIFY_ING');"><span class="txtcolor02"><g:text value="${resultContentsMap.verifyCount}" format="R#,###,###,###"/></span></a>件</dd>
			</dl>
		</div>

		<h4 class="h41 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_01.gif'/>" alt="新上架產品 最近7日內" /></h4>
		<p class="txtr pad_t5"><a href="javascript:gotoContentsList('CONTENT_SALE_STAT_NA');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/more.gif'/>" alt="更多" /></a></p>
		<div class="btstyleA mar_b22">
			<table summary="新上架產品 最近7日內">
				<caption>新上架產品 最近7日內</caption>
				<colgroup>
					<col />
					<col width="11%" />
					<col width="16%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit01.gif'/>" alt="產品名稱 / 類別" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit02.gif'/>" alt="價格" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit03.gif'/>" alt="上傳日期" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${not empty resultContentsMap.newRegistContents }">	
						<c:forEach items="${resultContentsMap.newRegistContents }" var="content" varStatus="listIndex">	
							<tr>
								<td class="tit01">																	
									<span class="imgbox fltl"><a href="javascript:gotoContentView('${content.cid}');"><img src="${CONF['omp.common.url.http-share.product']}${content.statusImgPos}" width="72" height="72" name="contsImg"  onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/no_images.gif"/>');" /></a></span>
									<p class="txt01"><a href="javascript:gotoContentView('${content.cid}');"><g:html value="${content.prodNm}"/></a>
									<input type="hidden" name="newRegistContentFlag" value="${content.newRegistContentFlag}" />
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_new.gif'/>" alt="new"  name="newContsIcon" style="display:none;"/>
									</p>
									<c:if test="${content.ctgrNm != ''}"><p class="txt02">[<g:html value='${content.ctgrNm}' />]</p></c:if>
								</td>
								<td>NT$ <g:text value="${content.prodPrc}" format="R#,###,###,###"/></td>
								<td><g:text value="${content.firstInsDt}" format="L##########"/></td>
							</tr>
						</c:forEach>
					</c:when>	
					<c:otherwise>
						<tr>
							<td colspan="3"><gm:html value="jsp.content.contentsSubMain.text.list"/></td>
						</tr>	
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<h4 class="h41 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_02.gif'/>" alt="待售產品 最近7日內" /></h4>
		<p class="txtr pad_t5"><a href="javascript:gotoContentsList('CONTENT_SALE_STAT_WAIT');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/more.gif'/>" alt="更多" /></a></p>
		<div class="btstyleA mar_b22">
			<table summary="待售產品 最近7日內">
				<caption>待售產品 最近7日內</caption>
				<colgroup>
					<col />
					<col width="11%" />
					<col width="16%" />
					<col width="16%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit01.gif'/>" alt="產品名稱 / 類別" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit02.gif'/>" alt="價格" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit03.gif'/>" alt="上傳日期" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit04.gif'/>" alt="核准日期" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${not empty resultContentsMap.saleWaitContents }">	
						<c:forEach items="${resultContentsMap.saleWaitContents }" var="content" varStatus="listIndex">	
							<tr>
								<td class="tit01">
									<span class="imgbox fltl"><a href="javascript:gotoContentView('${content.cid}');"><img src="${CONF['omp.common.url.http-share.product']}${content.statusImgPos}" width="72" height="72"  name="contsImg" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/no_images.gif"/>');" /></a></span>
									<p class="txt01"><a href="javascript:gotoContentView('${content.cid}');"><g:html value="${content.prodNm}"/></a>
									<input type="hidden" name="newSaleWaitContentFlag" value="${content.newSaleWaitContentFlag}" />
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_new.gif'/>" alt="new"  name="newSaleContsIcon" style="display:none;"/>
									</p>
									<c:if test="${content.ctgrNm != ''}"><p class="txt02">[<g:html value='${content.ctgrNm}' />]</p></c:if>
								</td>
								<td>NT$ <g:text value="${content.prodPrc}" format="R#,###,###,###"/></td>
								<td><g:text value="${content.firstInsDt}" format="L##########"/></td>
								<td><g:text value="${content.firstAgrmntDt}" format="L##########"/></td>
							</tr>
						</c:forEach>
					</c:when>	
					<c:otherwise>
						<tr>
							<td colspan="4"><gm:html value="jsp.content.contentsSubMain.text.list"/></td>
						</tr>	
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<h4 class="h41 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_03.gif'/>" alt="審核中產品" /></h4>
		<p class="txtr pad_t5"><a href="javascript:gotoContentsList('VERIFY_ING');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/more.gif'/>" alt="更多" /></a></p>
		<div class="btstyleA mar_b22">
			<table summary="審核中產品">
				<caption>審核中產品</caption>
				<colgroup>
					<col />
					<col width="16%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit01.gif'/>" alt="產品名稱 / 類別" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit05.gif'/>" alt="請審日期" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${not empty resultContentsMap.verifyContents }">	
						<c:forEach items="${resultContentsMap.verifyContents }" var="content" varStatus="listIndex">	
							<tr>
								<td class="tit01">
									<span class="imgbox fltl"><a href="javascript:gotoContentView('${content.cid}');"><img src="${CONF['omp.common.url.http-share.product']}${content.statusImgPos}" width="72" height="72" name="contsImg" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/no_images.gif"/>');" /></a></span>
									<p class="txt01"><a href="javascript:gotoContentView('${content.cid}');"><g:html value="${content.prodNm}"/></a>
									<input type="hidden" name="newRequestStartContentflag" value="${content.newRequestStartContentflag}" />
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_new.gif'/>" alt="new"  name="newReqContsIcon" style="display:none;"/>
									</p>
									<c:if test="${content.ctgrNm != ''}"><p class="txt02">[<g:html value='${content.ctgrNm}' />]</p></c:if>
								</td>
								<td><g:text value="${content.verifyCtReqInsDttm}" format="L##########"/></td>
							</tr>
						</c:forEach>
					</c:when>	
					<c:otherwise>
						<tr>
							<td colspan="2"><gm:html value="jsp.content.contentsSubMain.text.list"/></td>
						</tr>	
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<h4 class="h41 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_04.gif'/>" alt="審核結果 最近7日內" /></h4>
		<p class="txtr pad_t5"><a href="javascript:gotoContentsList('VERIFY_COM');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/more.gif'/>" alt="更多" /></a></p>
		<div class="btstyleA mar_b22">
			<table summary="審核結果 最近7日內">
				<caption>審核結果 最近7日內</caption>
				<colgroup>
					<col />
					<col width="16%" />
					<col width="16%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit01.gif'/>" alt="產品名稱 / 類別" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit05.gif'/>" alt="請審日期" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit43.gif'/>" alt="審合狀態" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${not empty resultContentsMap.verifyResults }">	
						<c:forEach items="${resultContentsMap.verifyResults }" var="content" varStatus="listIndex">	
							<tr>
								<td class="tit01">
									<span class="imgbox fltl"><a href="javascript:gotoContentView('${content.cid}');"><img src="${CONF['omp.common.url.http-share.product']}${content.statusImgPos}" width="72" height="72"  name="contsImg" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/no_images.gif"/>');" /></span>
									<p class="txt01"><a href="javascript:gotoContentView('${content.cid}');"><g:html value="${content.prodNm}"/></a>
									<input type="hidden" name="newRequestEndContentflag" value="${content.newRequestEndContentflag}" />
									<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/icon_new.gif" alt="new"  name="newComContsIcon" style="display:none;"/>
									</p>
									<c:if test="${content.ctgrNm != ''}"><p class="txt02">[<g:html value='${content.ctgrNm}' />]</p></c:if>
								</td>
								<td><g:text value="${content.verifyCtReqInsDttm}" format="L##########"/></td>
								<td>
									<c:if test="${content.verifyCtAgrmntStat == CONST.AGREEMENT_STATUS_REJECT}">
										<span class="txtcolor03"><gc:text code="${content.verifyCtAgrmntStat}"/></span>
									</c:if>
									<c:if test="${content.verifyCtAgrmntStat == CONST.AGREEMENT_STATUS_AGREE}">
										<gc:text code="${content.verifyCtAgrmntStat}"/>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:when>	 
					<c:otherwise>
						<tr>
							<td colspan="3"><gm:html value="jsp.content.contentsSubMain.text.list"/></td>
						</tr>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>

		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->
	
</div>

