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
 
	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_02.gif'/>" alt="미리보기-등록하신 이미지는 아래와같이 변환되어 사용됩니다." /></h2>
	<div class="viewbox" style="overflow-x:hidden;">
		<div class="imgbox1 fltl">
			<div class="pmview wbreak">
				<p class="fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000109}" alt="관련사진" width="114px" height="114"/></p>
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
					<dd class="dd01">NT$ <g:text value="${content.prodPrc}" format='R#,###,###,###'/><span><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_view.gif'/>" alt="결제" /></span></dd>
				</dl>
			</div>
			<div class="pmview01 wbreak">
				<p class="txt01"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_previewti_01.gif'/>" alt="이용등급" /><gc:text code="${content.gameDelibGrd}"/></p>
			</div>
			<div class="pmview02 wbreak">
				<p class="txt02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_previewti_02.gif'/>" alt="태그" />
				<c:if test="${not empty contentTagMap.tagNm1}" ><span>${contentTagMap.tagNm1}</span></c:if>
				<c:if test="${not empty contentTagMap.tagNm2}" ><span style="color:#994c19;text-decoration:none;">,</span> <span>${contentTagMap.tagNm2}</span> </c:if>
				<c:if test="${not empty contentTagMap.tagNm3}" ><span style="color:#994c19;text-decoration:none;">,</span> <span>${contentTagMap.tagNm3}</span> </c:if>
				<c:if test="${not empty contentTagMap.tagNm4}" ><span style="color:#994c19;text-decoration:none;">,</span> <span>${contentTagMap.tagNm4}</span> </c:if>
				<c:if test="${not empty contentTagMap.tagNm5}" ><span style="color:#994c19;text-decoration:none;">,</span> <span>${contentTagMap.tagNm5}</span> </c:if>
				</p>
			</div>	
			
			<!-- Tab_menu S -->
			<ul class="pad_b10 overh cb">
				<li class="fltl"><a href="javascript:clickTab('PreviewDesc');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_01_on.gif'/>" alt="상품설명/사용후기" /></a></li>
				<li class="fltl"><a href="javascript:clickTab('PreviewImg');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretab_02.gif'/>" alt="미리보기" /></a></li>
			</ul>
			
			<!-- //Tab_menu E -->
			<div class="imgbox3 wbreak" id="PreviewDescDiv" style="display:;">
				<h3 class="fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_pretxt_01.gif'/>" alt="상품설명" /></h3>
				<p class="txtr"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/prebtn_01.gif'/>" alt="동영상보기" /></p>
				<p class="pad_t7 mar_b22">
					<c:if test="${not empty content.descImg.filePos}" >
						<img src="${CONF['omp.common.url.http-share.product']}${content.descImg.filePos}" width="440px" />
					</c:if>
				</p>
				<p class="mar_b22">${content.prodDescDtl}</p>
				<h3 class="fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_pretxt_02.gif'/>" alt="사용후기" /></h3>
				<p class="txtr"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/prebtn_02.gif'/>" alt="사용후기등록" /></p>
				<dl>
					<dd class="txtc pad_t20"><gm:string value='jsp.content.popItemPreview.text.01' /></dd>
				</dl>
			</div>
			
			<div class="pad_b10 overh cb" id="PreviewImgDiv" style="display:none;">
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000103}" alt="관련사진" width="207" height="345"/></p>
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000104}" alt="관련사진" width="207" height="345"/></p>
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000105}" alt="관련사진" width="207" height="345"/></p>
				<p class="imgbox2 fltl"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000106}" alt="관련사진" width="207" height="345"/></p>
			</div>
			
			<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretxt_01.gif'/>" alt="전체화면으로보기" /></p>
			<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pretxt_02.gif'/>" alt="" /></p>
		</div>
	</div>
 
</div>
