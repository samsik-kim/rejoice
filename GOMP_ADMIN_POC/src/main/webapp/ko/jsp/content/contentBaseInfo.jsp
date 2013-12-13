<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
<!--
	 $(document).ready(function() {

		var cid = '${content.cid}';

 	    if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_ING}') {
 	    	if(${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT} || ${content.verifyPrgrYn == CONST.CODE_VERIFY_END}){
 	    		$('#saleStatBtn').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell01.gif"/>" alt="판매중지" onclick="javascript:chageContentState(\'SALE_STOP\', \'' + cid +'\');" style="cursor:pointer;"/>');
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell04.gif"/>" alt="판매중" />');
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_ING}){
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell04.gif"/>" alt="판매중" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_can.gif"/>" alt="검증중"  />');
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_REQ}) {
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell04.gif"/>" alt="판매중" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell07.gif"/>" alt="검증대기"  />');
 	    	}
 	    	
 	    } else if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_RESTRIC}') {
 	    	if(${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT} || ${content.verifyPrgrYn == CONST.CODE_VERIFY_END}){
 	    		$('#saleStatBtn').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_cancel.gif"/>" alt="해지요청" onclick="javascript:chageContentState(\'CONTS_REQ\', \'' + cid +'\');" style="cursor:pointer;"/>');
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell06.gif"/>" alt="판매금지" />');	
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_ING}){
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell06.gif"/>" alt="판매금지" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_can.gif"/>" alt="검증중"  />');	
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_REQ}) {
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell06.gif"/>" alt="판매금지" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell07.gif"/>" alt="검증대기"  />');
 	    	}
 	    	
 	    } else if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_STOP}') {
 	    	if(${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT} || ${content.verifyPrgrYn == CONST.CODE_VERIFY_END}){
 	    		$('#saleStatBtn').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell02.gif"/>" alt="판매개시" onclick="javascript:chageContentState(\'SALE_START\', \'' + cid +'\');" style="cursor:pointer;"/>');
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell05.gif"/>" alt="판매중지" />');	
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_ING}){
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell05.gif"/>" alt="판매중지" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_can.gif"/>" alt="검증중"  />');	
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_REQ}) {
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell05.gif"/>" alt="판매중지" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell07.gif"/>" alt="검증대기"  />');
 	    	}
 	    	
 	    } else if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_WAIT}') {
 	    	if(${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT} || ${content.verifyPrgrYn == CONST.CODE_VERIFY_END}){
 	    		$('#saleStatBtn').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell02.gif"/>" alt="판매개시" onclick="javascript:chageContentState(\'SALE_START\', \'' + cid +'\');" style="cursor:pointer;"/>');
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell03.gif"/>" alt="판매대기" />');	
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_ING}){
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell03.gif"/>" alt="판매대기" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_can.gif"/>" alt="검증중"  />');	
 	    	} else if (${content.verifyPrgrYn == CONST.CODE_VERIFY_REQ}) {
 	    		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell03.gif"/>" alt="판매대기" /> &nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell07.gif"/>" alt="검증대기"  />');
 	    	}
 	    	
 	    } else if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_NA}' && (${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT} || ${content.verifyPrgrYn == CONST.CODE_VERIFY_END})) {
 	  		$('#saleStatBtn').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_del.gif"/>" alt="삭제하기" onclick="javascript:chageContentState(\'DEL_CONTS\');" style="cursor:pointer;"/>');
 	  		$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_not.gif"/>" alt="미승인" />');	
 	    } else if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_NA}' && ${content.verifyPrgrYn == CONST.CODE_VERIFY_ING}) {
 	    	$('#saleStatBtn').html('');
 	    	$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_can.gif"/>" alt="검증중"  />');	
 	    } else if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_NA}' && ${content.verifyPrgrYn == CONST.CODE_VERIFY_REQ}) {
 	    	$('#saleStatBtn').html('');
 	    	$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_sell07.gif"/>" alt="검증대기"  />');	
 	    } else if ($('input[name=content\\.saleStat]').val() == '${CONST.CONTENT_SALE_STAT_UNREGIST}') {	
 	    	$('#saleStatImg').html('&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/icon_can01.gif"/>" alt="해지요청"  />');
 	    } 	
 
 		$('#saleStatBtn').css("cursor", "pointer");
	 });

	/*
	 * open popup Content Sale Stat Change List
	 */ 
	 function popSaleStat(cid) {
		   var winName = "winContBaseInfo";
		   var url = env.contextPath + "/content/popContentSaleStatList.omp";
	
		   popContentSaleStatList(winName, url, '456', '307', cid);
	 }
	
	 /*
	  * Verify Request Cancel
	  */
	 function popVerifyCancel(cid, tabGbn) {
			
		if(confirm("<gm:string value='jsp.content.contentBaseInfo.btn.verifyCancel'/>")) {

			var options = {   
				dataType : "json",
			    type:      "post",        // 'get' or 'post', override for form's 'method' attribute 
			    success:   
			    	function (resultText, statusText) {
			    		if(resultText.resultCode == "SUCCESS") alert("<gm:string value='jsp.content.common.msg.result.success'/>");
			    		else alert("<gm:string value='jsp.content.common.msg.result.fail' />");
						
						if (tabGbn == "DETAIL") location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + cid);
						else if (tabGbn == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + cid);
				 	},
				error:		function(xhr, textStatus, errorThrown){}
			};
			
		   $('#editForm').attr("target", "_self");	
		   $('#editForm').attr("method", "post");	
		   $('#editForm').attr("action", "ajaxVerifyCancel.omp");
		
		   $('#editForm').ajaxSubmit(options); 

		}else {
			return;
		}
		
	}

	/*
	 * 상품 판매상태 변경
	 */
	 function chageContentState(eventRequest, objCid) {
	 	var options;
	 	
	 	if (eventRequest == 'SALE_STOP') {					// 판매 중지
	 		if (confirm("<gm:string value='jsp.content.contentsStatus.btn.saleStop' />")) {
	 			
	 			if(showStatusCheck()) {
	 			
	 				options = {
	 						dataType: "json",
	 						type	: "post",
	 						success : function (responseText) {
	 							if(responseText.resultCode == 'FAIL') {
	 								alert("<gm:string value='jsp.content.common.msg.result.fail' />");
	 								
	 							} else {
	 								alert("<gm:string value='jsp.content.common.msg.result.success' />");
	 								
	 								if ('${tabGbn}' == 'DETAIL') location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid);
	 								else if ('${tabGbn}' == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + objCid);
	 							}
	 						}
	 				};  
	 			
	 				$('#editForm').attr("action", "ajaxChangeSaleStatus.omp");

		 		} else {
		 			location.reload();
		 			return;
		 		}
	 		} else {
	 			return;
	 		}
	 	} else if (eventRequest == 'CONTS_REQ') {		// 해지 요청
	 		
	 		if(showStatusCheck()) {
	 			options = {
	 					dataType:   "json",
	 					type	: "post",
	 					success : function (responseText) {
	 						if(responseText.resultCode == 'FAIL') {
	 							alert("<gm:string value='jsp.content.common.msg.result.fail' />");
	 							
	 						}else {
	 							alert("<gm:string value='jsp.content.common.msg.result.success' />");
	 							
	 							if ('${tabGbn}' == 'DETAIL') location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid);
 								else if ('${tabGbn}' == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + objCid);
	 						}
	 					}
	 			};  
	 			
	 			$('#editForm').attr("action", "ajaxChangeSaleStatus.omp");
	 		} else {
	 			location.reload();
	 			return;
	 		}
	 	
	 	} else if (eventRequest == 'SALE_START') {		// 판매 개시
	 		if (confirm("<gm:string value='jsp.content.contentsStatus.btn.saleStart' />")) {
	 			if(showStatusCheck()) {
	 		
	 				options = {
	 						dataType:   "json",
	 						type	: "post",
	 						success : function (responseText) {
	 							if(responseText.resultCode == 'FAIL') {
	 								alert("<gm:string value='jsp.content.common.msg.result.fail' />");
	 								
	 								if ('${tabGbn}' == 'DETAIL') location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid);
	 								else if ('${tabGbn}' == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + objCid);
	 							}else {
	 								alert("<gm:string value='jsp.content.common.msg.result.success' />");
	 								if ('${tabGbn}' == 'DETAIL') location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid);
	 								else if ('${tabGbn}' == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + objCid);
	 							}
	 						}
	 				};  
	 			
	 				$('#editForm').attr("action", "ajaxChangeSaleStatus.omp");
	 			} else {
	 				location.reload();
	 				return;
	 			}
	 		} else {
	 			return;
	 		}
	 	} else {										// 미승인 상품 삭제
	 		if (confirm("<gm:string value='jsp.content.contentsStatus.btn.contDel' />")) {
	 			if(showStatusCheck()) {
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
	 			
	 				$('#editForm').attr("action", "ajaxContentDelete.omp");
	 				
	 			} else {
	 				location.replace(env.contextPath + "/content/contentsStatusList.omp");
	 				return;
	 			}
	 		} else {
	 			return;
	 		}
	 		
	 	}
	 	
	 	$('#editForm').attr("target", "_self");	
	 	$('#editForm').ajaxSubmit(options);
	 	
	 }

	/*
	 * Ajax 통신시 Session 및 검증 진행 상태를 Check 한다.
	 */
	function showStatusCheck()  { 
	
		var url = env.contextPath + "/content/ajaxStatusCheck.omp";
		var cid = $("#cid").val();
		var param = $("#editForm").serialize();
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
					} else if(data.resultCode == 'jsp.content.common.msg.status.view') {
						alert("<gm:string value='검증 요청 중에는 상품 정보 변경이 불가능 합니다' />");
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
<c:set var="uri" value="${pageContext.request.requestURI}"/>
<div class="tstyleB mar_b25">
	<table summary="상품현황정보입니다">
		<caption>상품현황정보</caption>
		<colgroup>
			<col width="18%" />
			<col width="32%" />
			<col width="18%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit08.gif'/>" alt="상품명" /></th>
				<td colspan="3"><span class="txt01"><g:html value="${content.prodNm}"/></span> &nbsp;
				<span id="saleStatBtn"></span>
			</td>
			</tr>
			<tr>
				<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit09.gif'/>" alt="상품상태정보" /></th>
				<td>
					<input type="hidden" name="content.saleStat" value="${content.saleStat}"/>
					<span id="saleStatImg"></span>
				</td>
				<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit12.gif'/>" alt="Application ID" /></th>
				<td>${content.aid}</td>
			</tr>
			<tr>
				<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit10.gif'/>" alt="Platform" /></th>
				<td><gc:text code="${content.vmType}"/></td>
				<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit13.gif'/>" alt="등록일" /></th>
				<td><g:text value="${content.firstInsDt}" format="L##########"/></td>
			</tr>
			<tr>
				<th scope="row" class="bbnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit11.gif'/>" alt="판매개시일" /></th>
				<td class="bbnone"><g:text value="${content.saleStartDt}" format="L####-##-####"/></td>
				<th scope="row" class="bbnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit14.gif'/>" alt="상태변경일" /></th>
				<td class="bbnone"><g:text value="${content.updDttm}" format="L####-##-####"/> &nbsp;
				<a href="javascript:popSaleStat('${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_detail.gif'/>" alt="상세내역보기" /></a></td>
				</tr>
			</tbody>
	</table>
</div>
<!-- Tab_menu S -->
<div class="tab mar_b22">
	<c:choose>
		<c:when test="${content.verifyPrgrYn == CONST.CODE_VERIFY_REQ || content.verifyPrgrYn == CONST.CODE_VERIFY_ING}"> 
			<ul>
				<c:choose>
					<c:when test="${fn:contains(pageContext.request.requestURI, 'contentDetailInfo')}"> <!-- 상세정보 -->	
						<c:set var="tabGbn" value="DETAIL"/>
						<li><a href="javascript:moveTab('DETAIL', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab01_on.gif'/>" alt="상세정보" /></a></li>
						<li><a href="javascript:moveTab('DEVELOP', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab02.gif'/>" alt="개발정보" /></a></li>
					</c:when>
					<c:otherwise>	
						<c:set var="tabGbn" value="DEVELOP"/>											<!-- 개발정보 -->	
						<li><a href="javascript:moveTab('DETAIL', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab01.gif'/>" alt="상세정보" /></a></li>
						<li><a href="javascript:moveTab('DEVELOP', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab02_on.gif'/>" alt="개발정보" /></a></li>
					</c:otherwise>
				</c:choose>		
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<c:choose>
					<c:when test="${fn:contains(pageContext.request.requestURI, 'contentDetailInfo')}"> <!-- 상세정보 -->	
						<c:set var="tabGbn" value="DETAIL"/>
						<li><a href="javascript:moveTabCheck('DETAIL', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab01_on.gif'/>" alt="상세정보" /></a></li>
						<li><a href="javascript:moveTabCheck('DEVELOP', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab02.gif'/>" alt="개발정보" /></a></li>
					</c:when>
					<c:otherwise>	
						<c:set var="tabGbn" value="DEVELOP"/>											<!-- 개발정보 -->	
						<li><a href="javascript:moveTab('DETAIL', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab01.gif'/>" alt="상세정보" /></a></li>
						<li><a href="javascript:moveTab('DEVELOP', '${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_tab02_on.gif'/>" alt="개발정보" /></a></li>
					</c:otherwise>
				</c:choose>		
			</ul>
		</c:otherwise>	
	</c:choose>

	<p class="view" >
		<span id="verifyBtnArea">
		<a href="javascript:popVerifyReqComnt('popVerifyReq', '${content.cid}', '${tabGbn}');" id="verifyReqALink">
			<c:if test='${content.verifyAvailable == "Y"}'>
				<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_verify.gif'/>" alt="검증요청" id="verifyReqBtn"/>
			</c:if>
		</a> 
		<a href="javascript:popVerifyCancel('${content.cid}', '${tabGbn}');" id="verifyCancelALink">
			<c:if test='${content.verifyPrgrYn == CONST.CODE_VERIFY_REQ}'>
				<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_verify_can.gif'/>" alt="검증취소" id="verifyCancelBtn"/>
			</c:if>
		</a> 
		</span>	
		<c:if test="${not empty content.previewImg1.filePos && not empty content.previewImg2.filePos && not empty content.previewImg3.filePos && not empty content.previewImg4.filePos}" >	
			<a href="javascript:popPreview('${content.cid }');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_view.gif'/>" alt="미리보기" /></a>
		</c:if>
	</p>
</div>
