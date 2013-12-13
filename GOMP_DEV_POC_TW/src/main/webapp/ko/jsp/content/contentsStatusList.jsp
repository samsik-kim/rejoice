<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">
<!--
	 $(document).ready(function() {
	
 	 	for(var i=0;i < $('input[name=newRegistContentFlag]').length;i++) {
 	 		if ($('input[name=newRegistContentFlag]:nth('+i+')').val() == 'Y') {
 	 	    	$('img[name=newContsIcon]:nth('+i+')').attr('style', 'display:');
 	 	    }
 	 	}
 	
 	 	$('#searchBtn').click(function() {
 	 		$("input[name=content\\.searchType]").val($('#searchType').val());
 	 		$("input[name=content\\.searchValue]").val($('#searchValue').val());
 	 		$("input[name=content\\.saleSearchType]").val($('#saleStat').val());
 	 		$("form[name=contentStatusListFrm] input[name=content\\.page\\.no]").val(1);
 	 		
			$('#contentStatusListFrm').submit();
		}).css("cursor", "pointer");
 	 	
 	 	// 검색 enter Key
 	 	$("#searchValue").keydown(function(event) {
 			if(event.keyCode == 13) {
 				$("#searchBtn").click();
 			}
 		});
 	 });

	function gotoContentsList() {
 		$("input[name=content\\.searchType]").val($('#searchType').val());
 		$("input[name=content\\.searchValue]").val($('#searchValue').val());
 		$("input[name=content\\.saleSearchType]").val($('#saleStat').val());
 		
		$("#contentStatusListFrm").attr("target", "_self");
		$("#contentStatusListFrm").attr("action", "contentsStatusList.omp");
		$("#contentStatusListFrm").submit();
	}

	function goPage(no) {
		$("form[name=contentStatusListFrm] input[name=content\\.page\\.no]").val(no);
		gotoContentsList();
	}

	/*
	 * 상품 판매상태 변경
	 */
	 function chageContentState(eventRequest, objCid) {
	 	var options;
	 	
	 	if (eventRequest == 'SALE_STOP') {					// 판매 중지
	 		if (confirm("<gm:string value='jsp.content.contentsStatus.btn.saleStop' />")) {
	 			
	 			if(showStatusCheck(objCid)) {
	 			
	 				options = {
	 						dataType: "json",
	 						type	: "post",
	 						success : function (responseText) {
	 							if(responseText.resultCode == 'FAIL') {
	 								alert("<gm:string value='jsp.content.common.msg.result.fail' />");
	 								
	 							}else {
	 								alert("<gm:string value='jsp.content.common.msg.result.success' />");
	 								location.replace(env.contextPath + "/content/contentsStatusList.omp");
	 							}
	 						}
	 				};  
	 			
	 				$('#contentStatusListFrm').attr("action", "ajaxChangeSaleStatus.omp");
	 			} else {
		 			location.reload();
		 			return;
		 		}
	 		} else {
	 			return;
	 		}
	 	} else if (eventRequest == 'CONTS_REQ') {		// 해지 요청
	 		
	 		if(showStatusCheck(objCid)) {
	 			options = {
	 					dataType:   "json",
	 					type	: "post",
	 					success : function (responseText) {
	 						if(responseText.resultCode == 'FAIL') {
	 							alert("<gm:string value='jsp.content.common.msg.result.fail' />");
	 							
	 						}else {
	 							alert("<gm:string value='jsp.content.common.msg.result.success' />");
	 							location.replace(env.contextPath + "/content/contentsStatusList.omp");
	 						}
	 					}
	 			};  
	 			
	 			$('#contentStatusListFrm').attr("action", "ajaxChangeSaleStatus.omp");
	 		} else {
	 			location.reload();
	 			return;
	 		}
	 	
	 	} else if (eventRequest == 'SALE_START') {		// 판매 개시
	 		if (confirm("<gm:string value='jsp.content.contentsStatus.btn.saleStart' />")) {
	 			if(showStatusCheck(objCid)) {
	 				options = {
	 						dataType:   "json",
	 						type	: "post",
	 						success : function (responseText) {
	 							if(responseText.resultCode == 'FAIL') {
	 								alert("<gm:string value='jsp.content.common.msg.result.fail' />");
	 							}else {
	 								alert("<gm:string value='jsp.content.common.msg.result.success' />");
	 								location.replace(env.contextPath + "/content/contentsStatusList.omp");
	 							}
	 						}
	 				};  
	 			
	 				$('#contentStatusListFrm').attr("action", "ajaxChangeSaleStatus.omp");
	 			} else {
	 				location.reload();
	 				return;
	 			}
	 		} else {
	 			return;
	 		}
	 	} else {										// 미승인 상품 삭제
	 		if (confirm("<gm:string value='jsp.content.contentsStatus.btn.contDel' />")) {
	 			if(showStatusCheck(objCid)) {
	 				options = {
	 						dataType:   "json",
	 						type	: "post",
	 						success : function (responseText, status) {
	 							if(responseText.resultCode == 'FAIL') {
	 								alert("<gm:string value='jsp.content.common.msg.result.fail' />");
	 								
	 							}else {
	 								alert("<gm:string value='jsp.content.common.msg.result.success' />");
	 								location.replace(env.contextPath + "/content/contentsStatusList.omp");
	 							}
	 						},
	 						error   : function(xhr, textStatus, errorThrown){}
	 				};  
	 			
	 				$('#contentStatusListFrm').attr("action", "ajaxContentDelete.omp");
	 			} else {
	 				location.reload();
	 				return;
	 			}
	 		} else {
	 			return;
	 		}
	 		
	 	}
	 	
	 	$('#contentStatusListFrm').attr("target", "_self");	
	 	$('#contentStatusListFrm').ajaxSubmit(options);
	 	
	 }
	
	/*
	 * Ajax 통신시 Session 및 검증 진행 상태를 Check 한다.
	 */
	function showStatusCheck(objCid)  { 

		$("#cid").val(objCid);
		var param = $("#contentStatusListFrm").serialize();
		var chFlag = true;
		
		$.ajax({
	        type: "POST",
	        async : false,
	        url: env.contextPath + "/content/ajaxStatusCheck.omp",
	        data : param,
	        dataType:  "json",
	        success: function(data){
			    try{
					
					if(data.resultCode == 'NS') {
						alert("<gm:string value='jsp.content.common.msg.status.ns' />");
						location.replace(env.contextPath + "/login/loginMain.omp");
						chFlag = false;
					} else if(data.resultCode == 'NC') {
						alert("<gm:string value='jsp.content.common.msg.status.nc' />");
						location.replace(env.contextPath + "/content/contentsStatusList.omp");
						chFlag = false;
					} else if(data.resultCode == 'VIEW') {
						alert("<gm:string value='jsp.content.common.msg.status.view' />");
						chFlag = false;
	
					} else if(data.resultCode == 'FAIL') {
						alert("<gm:string value='jsp.content.common.msg.result.fail' />");
						chFlag = false;
					}
				
				}catch(e){
					chFlag = false;
				}
	        },
	        error: function(xhr, textStatus, errorThrown){chFlag = false;}
	    }); 
		
		return chFlag;
	} 
//-->
</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 상품등록/관리 &gt; 상품관리 <strong>&gt;</strong> <span>상품현황</span> </p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title01.gif'/>" alt="상품등록/관리" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	<form id="contentStatusListFrm" name="contentStatusListFrm" method="post">
	<input type="hidden" name="content.vmType" >
	<input type="hidden" name="content.prodNm" >
	<input type="hidden" name="content.searchType" >
	<input type="hidden" name="content.searchValue" >
	<input type="hidden" name="content.saleSearchType" >

	<input type="hidden" name="content.page.no" value="${content.page.no}"/>
		<div class="pmbox mar_b22">
			<div class="pad_bl70">
				<label for="platform"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt04.gif'/>" alt="플랫폼" class="pad_tr5 fltl" /></label>
				<div class="fltl">
				<select id="vmType" name="content.vmType" class="" style="width:113px">
					<gc:options group="${CONST.CODE_PLFM}" codeType="full"/>
				</select>
				</div>
				<label for="condition"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt05.gif'/>" alt="상품상태" class="pad_bl27 fltl" /></label>
				<div class="fltl">
				<select id="saleStat" id="saleStat" class="" style="width:113px">
					<option value=""  <c:out value="${content.saleSearchType==''?'selected=selected':''}" />>전체</option>
					<gc:options group="${CONST.CODE_SALESTAT}" codeType="full" value="${content.saleSearchType}" />
				</select>
				</div>
				<label for="verify"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_txt06.gif'/>" alt="검증진행여부" class="pad_bl27 fltl" /></label>
				<div class="fltl">
				<select id="searchType" class="" style="width:113px">
					<option value=""  <c:out value="${content.searchType==''?'selected=selected':''}" />>전체</option>
					<option value="Y" <c:out value="${content.searchType=='Y'?'selected=selected':''}" />>검증요청</option>
					<option value="N" <c:out value="${content.searchType=='N'?'selected=selected':''}" />>검증요청 외</option>
				</select>
				</div>
				</div>	
			<p class="pad_l70">
				<input type="text" id="searchValue" class="w570"  title="검색어를 입력해주세요" value="${content.searchValue}"  />
				<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_search1.gif'/>"  id="searchBtn" alt="검색" />
			</p>
		</div>
		
		<div class="btstyleA">
			<table summary="상품 리스트">
				<caption>상품 리스트</caption>
				<colgroup>
					<col width="7%" />
					<col />
					<col width="11%" />
					<col width="16%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit06.gif'/>" alt="No" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit01.gif'/>" alt="상품명 / 분류체계" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit02.gif'/>" alt="가격" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit07.gif'/>" alt="상태" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${contentsStatusListCount > 0}">
						<c:forEach items="${contentsStatusList}" var="content" varStatus="listIndex">	
							<tr>
								<td><g:html value="${content.dispRowNum}"/></td>
								<td class="tit01">
									<span class="imgbox fltl"><a href="javascript:gotoContentDetailInfoView('contentStatusListFrm', '${content.cid}');"><img src="${CONF['omp.common.url.http-share.product']}${content.statusImgPos}" width="72" height="72" name="contsImg" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/common/no-img2.gif"/>');" /></a></span>
									<input type="hidden" name="newRegistContentFlag" value="${content.newRegistContentFlag}" />
									<p class="txt01">
									<a href="javascript:gotoContentDetailInfoView('contentStatusListFrm', '${content.cid}');">
										<g:html value="${content.prodNm}"/> 
									</a>
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_new.gif'/>" alt="새로운상품"  name="newContsIcon" style="display:none;"/>
									</p>
									<c:if test="${content.ctgrNm != ''}"><p class="txt02">[${content.ctgrNm}]&nbsp;</p></c:if>
								</td>
								<td>
									<g:text value="${content.prodPrc}" format="R#,###,###,###"/>원
								</td>
								<td>
									<input type="hidden" name="saleStatTxt" value="${content.saleStat}" />
									<gc:text code="${content.saleStat}"/>
									<c:choose>
										<c:when test="${content.saleStat == CONST.CONTENT_SALE_STAT_ING && content.verifyPrgrYn != CONST.CODE_VERIFY_REQ && content.verifyPrgrYn != CONST.CODE_VERIFY_ING}">
											<br /><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell01.gif"/>" alt="판매중지" onclick="javascript:chageContentState('SALE_STOP', '${content.cid}');" style="cursor:pointer;"/>
										</c:when>
										<c:when test="${content.saleStat == CONST.CONTENT_SALE_STAT_RESTRIC && content.verifyPrgrYn != CONST.CODE_VERIFY_REQ && content.verifyPrgrYn != CONST.CODE_VERIFY_ING}">
											<br /><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_cancel.gif"/>" alt="해지요청" onclick="javascript:chageContentState('CONTS_REQ', '${content.cid}');" style="cursor:pointer;"/>
										</c:when>
										<c:when test="${content.saleStat == CONST.CONTENT_SALE_STAT_STOP && content.verifyPrgrYn != CONST.CODE_VERIFY_REQ && content.verifyPrgrYn != CONST.CODE_VERIFY_ING}">
											<br /><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell02.gif"/>" alt="판매개시" onclick="javascript:chageContentState('SALE_START', '${content.cid}');" style="cursor:pointer;"/>
										</c:when>
										<c:when test="${content.saleStat == CONST.CONTENT_SALE_STAT_WAIT && content.verifyPrgrYn != CONST.CODE_VERIFY_REQ && content.verifyPrgrYn != CONST.CODE_VERIFY_ING}">
											<br /><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell02.gif"/>" alt="판매개시" onclick="javascript:chageContentState('SALE_START', '${content.cid}');" style="cursor:pointer;"/>
										</c:when>
									</c:choose>	
									</span>
								</td>
							</tr>	
						</c:forEach> 
					</c:when>	
					<c:otherwise>
						<tr>
							<td colspan="4"><gm:string value='jsp.content.contentsStatus.text.list'/></td>
						</tr>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!-- paging -->
		<div align="center">
			<g:pageIndex item="${contentsStatusList}"/>
		</div>
		<!-- //paging -->
	</form>	
	</div>
	<!-- //CONTENT TABLE E-->

</div>