<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<script type="text/javascript">
	
	$(document).ready( function() {
		var listReferer = "${fn:contains(referer, 'getContentsVerifyList')}";
		var cancelReferer = "${fn:contains(referer, 'getContentVerifyDetail')}";
		
		jQuery.fn.detail =  {
				
			verifyStat : function(tabFlag) {
				$.ajax({
		            type: "POST",
		            async : false,
		            url: env.contextPath + "/content/ajaxDitailVerifyState.omp",
		            data : "ctVerify.cid=${contentVerifyDetail.cid }&ctVerify.verifyReqVer=${contentVerifyDetail.verifyReqVer }",
		            dataType:  "html",
		            success: function(transport){
					    try{
					    	$("#detail").html(transport);
						}catch(e){
						}
		            },
		            error: function(xhr, textStatus, errorThrown){
		            }
		        });  
			},
			
			verifyHis : function(tabFlag) {
				$.ajax({
		            type: "POST",
		            async : false,
		            url: env.contextPath + "/content/ajaxDitailVerifyHistory.omp",
		            data : "ctVerify.cid=${contentVerifyDetail.cid }&ctVerify.verifyReqVer=${contentVerifyDetail.verifyReqVer }",
		            dataType:  "html",
		            success: function(transport){
					    try{
					    	$("#detail").html(transport);
						}catch(e){
						}
		            },
		            error: function(xhr, textStatus, errorThrown){
		            }
		        });	
			}
		}
		
		if("true" == listReferer || "true" == cancelReferer) {
			jQuery.fn.detail.verifyStat("verifyState");
		}
		
		$("#verifyStat").click( function() {
			if("verifyState" == $("#verifyTab").val()) {
				event.preventDefault();
			} else {
				$("#verifyTab").val("verifyState");
				
				$("#verifyStat").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/pm_tab03_on.gif");
				$("#verifyHistory").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/pm_tab04.gif");
				
				jQuery.fn.detail.verifyStat("verifyState");	
			}
		});
		
		$("#verifyHistory").click( function() {
			if("verifyHistory" == $("#verifyTab").val()) {
				event.preventDefault();
			} else {
				$("#verifyTab").val("verifyHistory");
				
				$("#verifyStat").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/pm_tab03.gif");
				$("#verifyHistory").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/pm_tab04_on.gif");
				
				jQuery.fn.detail.verifyHis("verifyHistory");	
			}
		});
	});
	
	//Content Detail Page
	function gotoContentView(objCid) {
		location.href = env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid;
	}
</script>
</head>

<body>
<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 상품등록/관리 &gt; 상품관리 <strong>&gt;</strong> <span>검증현황</span></p>

		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title04.gif' />" alt="검증현황" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
          
          	<div class="pminfo mar_b35">
			<p class="fltl mar_r20">
				<span class="imgbox">
					<c:choose>
						<c:when test="${not empty contentVerifyDetail.filePos }">
							<img src="${CONF['omp.common.url.http-share.product']}${contentVerifyDetail.filePos}" 
								onerror="javascript:fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/noimage_130.jpg"/>');" alt="상품이미지"/>
						</c:when>
						<c:otherwise>
							<!-- TODO no_img change -->
							<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/noimage_130.jpg' />" alt="관련사진" />
						</c:otherwise>
					</c:choose>
				</span>
			</p>
			<p class="ti">${contentVerifyDetail.prodNm }</p>

			<p class="tc">${contentVerifyDetail.prodDescSmmr }</p>
			<div class="info_list">
				<ul>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt12.gif' />" alt="플랫폼:" /><strong><gc:text code="${contentVerifyDetail.vmType }" /></strong></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt15.gif' />" alt="Application ID:" />${contentVerifyDetail.aid }</li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt13.gif' />" alt="분류:" />${contentVerifyDetail.ctgrCd }</li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt16.gif' />" alt="대상단말:" />${contentVerifyDetail.sprtPhoneCnt } 종</li>

					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt14.gif' />" alt="등급:" /><gc:text code="${contentVerifyDetail.gameDelibGrd }" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt17.gif' />" alt="등록일:" /><g:text value='${contentVerifyDetail.insDttm }' format='L####-##-##' /></li>
				</ul>
			</div>
		</div>
		<div class="tab mar_b10">
			<input type="hidden" id="verifyTab" value="${ctVerify.verifyDetailTab }">
			<ul> 
				<li><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab03_on.gif' />" id="verifyStat" alt="검증상태" /></a></li>
				<li><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab04.gif' />" id="verifyHistory" alt="검증 히스토리" /></a></li>
			</ul>
		</div>
		
		<div id="detail"></div>
	</div>
	<!-- //CONTENT TABLE E-->
</div>
</body>
</html>		
		