<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>測試分配</title>
<script type="text/javascript">
	//<![CDATA[
           

	$(function() {
		
		jQuery.fn.selfPage = {
				
			    /**
			     * Save Test Assign
			     *
			     */  
			    saveTestAssign : function (){
			        
			        var param = $("#saveForm").serialize();
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/saveAssignByAjax.omp",
			            data : param,
			            dataType:  "json",
			            success: function(transport){
						    try{
								alert(transport.responseMessage);
								
								if(transport.responseCode == 'SUCC'){
									location.replace(env.contextPath + "/certifymgr/appList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
								}
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){}
			        });                     
			    },
		
			    /**
			     * Save Certify Pass
			     *
			     */  
			    saveCertifyPass : function (){
			        
			        var param = $("#savePassForm").serialize();
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/saveCertifyPassByAjax.omp",
			            data : param,
			            dataType:  "json",
			            success: function(transport){
						    try{
								alert(transport.responseMessage);
								
								if(transport.responseCode == 'SUCC'){
									location.replace(env.contextPath + "/certifymgr/appList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
								}
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){}
			        });                     
			    }			
		};
		
		
		$("#certifyPassBtn").click(function(event){
			event.preventDefault();
			
			if(confirm("<gm:string value='jsp.certifymgr.certifyAppDetailToAssign.msg.confirm_certify_pass'/>")){
				
				$.fn.selfPage.saveCertifyPass();
				
				//if(showValidate("saveForm", 'default', '', extendedValidationFns)){
					//$.fn.selfPage.saveTestAssign();
				//}
			}				
			
		});
		
		$("#testAssignBtn").click(function(event){
			event.preventDefault();
			
			
			
			if(confirm("<gm:string value='jsp.certifymgr.certifyAppDetailToAssign.msg.confirm_tester_assign'/>")){
				
				//$.fn.selfPage.saveTestAssign();
				
				if(showValidate("saveForm", 'dialog', '', extendedValidationFns)){
					$.fn.selfPage.saveTestAssign();
				}
			}			
		});
		
		
		$("#appListBtn").click(function(event){
			event.preventDefault();
			location.replace(env.contextPath + "/certifymgr/appList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
		});			
		
		
		$( "#ctEndExDt" ).datepicker();
		
		
		$("input[name='frmSprtPhoneSeq']").click(function(event){
			
			var obj = $("#targetYn_" + $(this).attr("userIndex"));
			
			if(obj.val() == "Y"){
				obj.val("N");
			}else{
				obj.val("Y");
			}
		});
		
		$("input[name='allCheckBySprtPhone']").click(function(event){
			
			var groupIndexVal = eval($(this).attr("userIndex"));
			var groupChecked = $(this).attr("checked");
			
			$("input[name='frmSprtPhoneSeq']").each(function(i){
				
				if(eval($(this).attr("groupIndex")) == groupIndexVal){
					
					var obj = $("#targetYn_" + $(this).attr("userIndex"));
					
					if(groupChecked){
						$(this).attr("checked", "checked");
						obj.val("Y");
					}else{
						$(this).attr("checked", "");
						obj.val("N");
					}
				}
			});
			
		});
		
		$("#allCheckTestCtg").click(function(event){
			
			var groupChecked = $(this).attr("checked");
			
			$("input[name='saveAssign.ctCtgSeq']").each(function(i){
				if(groupChecked){
					$(this).attr("checked", "checked");
				}else{
					$(this).attr("checked", "");
				}
			});
			
		});
		
		
		$("#idCertifyHistory").click(function(){
			var cid = $(this).attr("cid");
			
			openCenteredWindow(env.contextPath + "/certifymgr/popHistoryList.omp?searchCon.cid="+cid, 810, 550, "yes", "");
		});
		
	});
	
	var extendedValidationFns = {
			
			testCtgCheckd : function(){
				
				var isChecked = false;
				
				$("input[name='saveAssign.ctCtgSeq']").each(function(i){
					var checkedVal = $(this).attr("checked");
					
					if(checkedVal){
						isChecked = checkedVal;
					}
				});
				
				return isChecked;
			},
			sprtPhoneChecked : function(groupCheckObj){
				var isChecked = false;
				
				var groupIndexVal = eval(groupCheckObj.userIndex);
				
				$("input[name='frmSprtPhoneSeq']").each(function(i){
					if(eval($(this).attr("groupIndex")) == groupIndexVal){
						
						var checkedVal = $(this).attr("checked");
						
						if(checkedVal){
							isChecked = checkedVal;
						}
					}
				});	
				
				
				return isChecked;
			}
			
	};	     	
	
	//]]>
</script>
</head>
<body>
			<div id="location">
				首頁 &gt; 產品管理中心 &gt; 審核管理 &gt <strong>測試分配</strong>
			</div><!-- //location -->
 
			<h1 class="fl pr10">測試分配</h1>
			<p>可向測試管理者分配應用軟體審核。</p>
			<h2>基本資訊</h2>		
			<table class="tabletype02">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>名稱</th>
					<td class="text_l">${appInfo.prodNm }</td>
				</tr>
				<tr>
					<th>產品 AID</th>
					<td class="text_l">${appInfo.aid }</td>
				</tr>
				<tr>
					<th>平台</th>
					<td class="text_l"><gc:html code="${appInfo.vmType}"/></td>
				</tr>
				<tr>
					<th>會員等級 / 審核級別</th>
					<td class="text_l"><gc:html code="${appInfo.signOption}"/> / <gc:html code="${appInfo.mbrGrCd}"/></td>
				</tr>
				<tr>
					<th>請審日期 / 審核次數</th>
					<td class="text_l"><g:html value="${appInfo.insDttm}" format="L####-##-##" /> / ${appInfo.verifyReqVer }次審核</td>
				</tr>
				<tr>
					<th>使用指南</th>
					<td class="text_l align_td"><a href="<c:url value="/fileSupport/fileDown.omp">
															<c:param name="bnsType" value="common.path.share.product"/>
															<c:param name="filePath" value="${appInfo.verifyScnrFile }" />
															<c:param name="fileName" value="${appInfo.verifyScnrFileNm}" />
												         </c:url>">${appInfo.verifyScnrFileNm }</a></td><!-- 2011-03-25 -->
				</tr>
				</tbody>
			</table>
 
			<h2 class="fl pr10">審核緣由</h2>
			
			<c:if test="${appInfo.reCertifyReqYn == 'Y'}">			
			<div class="fl mt15 mb05">
				<a class="btn_s" href="#" id="idCertifyHistory" cid="${appInfo.cid }"><strong>查看審核紀錄</strong></a>
			</div>
			</c:if>
			
			<table class="tabletype02">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>請審緣由</th>
					<td class="text_l">
						<c:set var="multiCd" value="${fn:split(appInfo.verifyCommentCd, ';')}" />
						<c:forEach items="${multiCd }" var="verifyCommentCd" varStatus="status">
							<gc:html code="${verifyCommentCd }" /> <c:if test="${fn:length(multiCd) != status.count }"> / </c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text_l">${appInfo.verifyEtcCmtByBr }</td>
				</tr>
				</tbody>
			</table>
 
<form id="saveForm" name="saveForm" method="post"> 
	<input type="hidden" name="saveAssign.cid" value="${appInfo.cid }">
	<input type="hidden" name="saveAssign.verifyReqVer" value="${appInfo.verifyReqVer }">
			<h2>選擇審核手機</h2>
			<c:set var="sprtSequence" value="0" />
			<c:forEach items="${subInfoList}" var="subInfo" varStatus="firtStatus">
			<table class="tabletype02">
				<colgroup>
					<col style="width:10%;">
					<col style="width:10%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				
				<tr>
					<th colspan="2">上傳檔案</th>
					<td class="text_l align_td"><a href="<c:url value="/fileSupport/fileDown.omp">
															<c:param name="bnsType" value="common.path.share.product"/>
															<c:param name="filePath" value="${subInfo.runFilePos }" />
															<c:param name="fileName" value="${subInfo.runFileNm}" />
												         </c:url>">${subInfo.runFileNm}</a> (${subInfo.runFileSizeByKilobyte}Kbyte)</td><!-- 2011-03-25 -->
				</tr>
				<tr>
					<th rowspan="3">OS 版本</th>
					<th>Min</th>
					<td class="text_l">${subInfo.vmVerMin}</td>
				</tr>
				<tr>
					<th>Target</th>
					<td class="text_l">${subInfo.vmVerTarget}</td>
				</tr>
				<tr>
					<th>Max</th>
					<td class="text_l">${subInfo.vmVerMax}</td>
				</tr>
				<tr>
					<th colspan="2">適用手機</th>
					<td class="text_l scroll_wrap">
						<div class="scroll">
							<!-- 2011-03-25 -->
							<table class="tabletype02" style="width:630px;">
								<colgroup>
									<col width="30px;" />
									<col >
									<col >
									<col >
									<col >
								</colgroup>
								<thead>
								<tr>
									<th><input type="checkbox" name="allCheckBySprtPhone" userIndex="${firtStatus.index }" v:sprtPhoneChecked m:sprtPhoneChecked="<gm:tagAttb value="jsp.certifymgr.certifyAppDetailToAssign.msg.phone"/>"/></th>
									<th>手機名稱</th>
									<th>型號</th>
									<th>OS版本</th>
									<th>LCD 解析度</th>
								</tr>
								<c:forEach items="${subInfo.supportPhoneList}" var="info" varStatus="secondStatus">
								<c:set var="sprtSequence" value="${sprtSequence+1 }" />
								<tr>
									<td>
									<c:choose>
										<c:when test="${info.targetYn == 'Y'}">
											<input type="checkbox" groupIndex="${firtStatus.index }" userIndex="${sprtSequence }" 
												name="frmSprtPhoneSeq" value="${info.sprtPhoneSeq}" checked />
										</c:when>
										<c:otherwise>
											<input type="checkbox" groupIndex="${firtStatus.index }" userIndex="${sprtSequence }" 
												name="frmSprtPhoneSeq" value="${info.sprtPhoneSeq}" />
										</c:otherwise>
									</c:choose>
									
									</td>
									<td>${info.modelNm }</td>
									<td>${info.mgmtPhoneModelNm }</td>
									<td><gc:html code="${info.vmVer }"/></td>
									<td><gc:html code="${info.lcdSize }"/></td>
									<input type="hidden" name="saveAssign.sprtPhoneSeq" value="${info.sprtPhoneSeq }">
									<input type="hidden" id="targetYn_${sprtSequence }" name="saveAssign.targetYn" value="${info.targetYn}"/>
									<input type="hidden" name="saveAssign.scid" value="${subInfo.scid }">
								</tr>
								</c:forEach>

								</tbody>
							</table>
							<!-- //2011-03-25 -->
						</div>
					</td>
				</tr>
				</tbody>
			</table>
			<br>
			</c:forEach>
 
			<h2>測試分配</h2>
			<table class="tabletype02">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>選擇測試管理者</th>
					<td class="text_l">
						<select name="saveAssign.testerId" style="width:104px;" v:required m:required="<gm:tagAttb value="jsp.certifymgr.certifyAppDetailToAssign.msg.tester"/>">
							<option value=""></option>						
							<c:forEach items="${adMgrList}" var="info">
                        	<option value="${info.mgrId }">${info.mgrNm }</option>
                        	</c:forEach>
                        </select>
					</td>
				</tr>
				<tr>
					<th>完畢預期</th>
					<td class="text_l">
						<input id="ctEndExDt" name="saveAssign.ctEndExDt" type="text" class="txt" style="width:80px;" value="" readonly v:required m:required="<gm:tagAttb value="jsp.certifymgr.certifyAppDetailToAssign.msg.endExDt"/>"/> 
					</td>
				</tr>
				<tr>
					<th>測試備註</th>
					<td class="text_l"><input id="testMemo" name="saveAssign.testMemo" type="text" class="txt" style="width:80%;" 
						v:text8_limit="300" m:text8_limit="<gm:tagAttb value="jsp.certifymgr.certifyAppDetailToAssign.msg.testMemo"/>"/></td>
				</tr>
				</tbody>
			</table>
 
			<h2>測試分配之類別</h2>
			<table class="tabletype02">
				<colgroup>
					<col style="width:40px;" >
					<col >
					<col >
				</colgroup>
				<thead>
				<tr>
					<th><input type="checkbox" id="allCheckTestCtg"  v:testCtgCheckd m:testCtgCheckd="<gm:string value='jsp.certifymgr.certifyAppDetailToAssign.msg.testCategory'/>"/></th>
					<th>平台</th>
					<th>類別</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${testCtgList}" var="info">
				<tr>
					<td><input name="saveAssign.ctCtgSeq" type="checkbox" value="${info.ctCtgSeq}"/></td>
					<td><gc:html code="${info.vmType}"/></td>
					<td>${info.titleNm}</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
</form>

<form id="savePassForm" name="savePassForm" method="post"> 
	<input type="hidden" name="saveTestStatus.cid" value="${appInfo.cid }">
	<input type="hidden" name="saveTestStatus.verifyReqVer" value="${appInfo.verifyReqVer }">
</form>					
			
			<p class="btn_wrap text_r mt25">
				<a class="btn" href="#" id="certifyPassBtn"><span>通過審核</span></a> 
				<a class="btn" href="#" id="testAssignBtn"><span>測試分配</span></a> 
				<a class="btn" href="#" id="appListBtn"><span>目錄</span></a>
			</p>
					
</body>
</html>

