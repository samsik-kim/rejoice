<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<script type="text/javascript">
<!--

function clickTab(objTab) {
	
	if(objTab == "PreviewDesc") {
		$("#PreviewDesc").attr("src", "<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_01_on.gif'/>");
		$("#PreviewImg").attr("src", "<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_02.gif'/>");
		$("#PreviewDescDiv").attr("style", "display:;");
		$("#PreviewImgDiv").attr("style", "display:none;");
	} else if(objTab == "PreviewImg") {
		$("#PreviewDesc").attr("src", "<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_01.gif'/>");
		$("#PreviewImg").attr("src", "<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_02_on.gif'/>");
		$("#PreviewDescDiv").attr("style", "display:none;");
		$("#PreviewImgDiv").attr("style", "display:;");
	}
}

//-->
</script>
<div id="pop_area03">
 
	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_02.gif'/>" alt="預覽-上傳之圖片將變換為以下形式" /></h2>
	<div class="viewbox" style="overflow-x:hidden;">
		<div class="imgbox1 fltl">
			<div class="pmview wbreak">
				<p class="fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000109}" alt="photo" width="114px" height="114"/></p>
				<dl>
					<dt><g:html value='${content.prodNm}'/></dt>
					<dd>
						<c:if test="${not empty content.exposureDevNm}">
							<g:html value='${content.exposureDevNm}'/>
						</c:if>
						<c:if test="${empty content.exposureDevNm}">
							<g:html value='${content.devUserId}'/>
						</c:if>
					</dd>
					<dd class="dd01">NT$ <g:text value="${content.prodPrc}" format='R#,###,###,###'/><span><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_view.gif'/>" alt="付款" /></span></dd>
				</dl>
			</div>
			
			<div class="pmview01 wbreak">
				<p class="txt01"><gc:text code="${content.gameDelibGrd}"/></p>
			</div>
			
			<div class="pmview02 wbreak">
				<p class="txt02">&nbsp;
					<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_previewti_02.gif' />" alt="Tag" />
					<c:if test="${not empty contentTagMap.tagNm1}" ><span><g:html value='${contentTagMap.tagNm1}' /></span></c:if>
					<c:if test="${not empty contentTagMap.tagNm2}" ><span style="color:#994c19;text-decoration:none;">,</span> <span><g:html value='${contentTagMap.tagNm2} '/></span> </c:if>
					<c:if test="${not empty contentTagMap.tagNm3}" ><span style="color:#994c19;text-decoration:none;">,</span> <span><g:html value='${contentTagMap.tagNm3}' /></span> </c:if>
					<c:if test="${not empty contentTagMap.tagNm4}" ><span style="color:#994c19;text-decoration:none;">,</span> <span><g:html value='${contentTagMap.tagNm4}'/></span> </c:if>
					<c:if test="${not empty contentTagMap.tagNm5}" ><span style="color:#994c19;text-decoration:none;">,</span> <span><g:html value='${contentTagMap.tagNm5}' /></span> </c:if>				
				</p>
			</div>
			
			<!-- Tab_menu S -->
			<ul class="pad_b10 overh cb">
				<li class="fltl"><a href="javascript:clickTab('PreviewDesc');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_01_on.gif'/>" alt="" id="PreviewDesc" /></a></li>
				<li class="fltl"><a href="javascript:clickTab('PreviewImg');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_02.gif'/>" alt="" id="PreviewImg" /></a></li>
			</ul>	
			<!-- //Tab_menu E -->
		
			<div class="imgbox3" id="PreviewDescDiv" style="display:;" class="imgbox3 wbreak">
				<h3 class="fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_pretxt_01.gif'/>" alt="" /></h3>
				<p class="txtr"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/prebtn_01.gif'/>" alt="" /></p>
				<p class="pad_t7 mar_b22">
					<c:if test="${not empty content.descImg.filePos}" >
						<img src="${CONF['omp.common.url.http-share.product']}${content.descImg.filePos}" width="440px" />
					</c:if>
				</p>
				<p class="mar_b22"><g:html value='${content.prodDescDtl}' /></p>
				<h3 class="fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_pretxt_02.gif'/>" alt="" /></h3>
				<p class="txtr"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/prebtn_02.gif'/>" alt="" /></p>
				<dl>
					<dd class="txtc pad_t20"><gm:string value='jsp.content.popItemPreview.text.01' /></dd>
				</dl>
			</div>
			
			<div class="pad_b10 overh" id="PreviewImgDiv" style="display:none;">
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000103}" alt="預覽" width="207" height="345"/></p>
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000104}" alt="預覽" width="207" height="345"/></p>
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000105}" alt="預覽" width="207" height="345"/></p>
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000106}" alt="預覽" width="207" height="345"/></p>
			</div>
			
			<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretxt_01.gif'/>" alt="" /></p>
			<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretxt_02.gif'/>" alt="" /></p>
		</div>
	</div>
</div>
