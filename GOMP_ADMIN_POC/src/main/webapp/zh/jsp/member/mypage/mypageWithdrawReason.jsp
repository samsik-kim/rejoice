<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">
	
	$(function(){
		$("#ok_btn").click(function(){
			if(showValidate('withdrawForm', 'default',"<gm:string value='jsp.common.msg.title01'/>")){
				<c:choose>
					<c:when test="${profileInfo.mbrCatCd eq CONST.MEM_TYPE_DEV_NOPAY}">
						if($('input[name="withdrawInfo.freeDevEndReasonCd"]:checked').length == 0){
							alert("<gm:string value='jsp.member.mypage.msg.wr01'/>");
							return false;
						}
						
						if($('input[name="withdrawInfo.freeDevEndReasonCd"]:checked').val().indexOf("09") > 0){
							if($("#reason").val().trim() == ""){
								alert("<gm:string value='jsp.member.mypage.msg.wr02'/>");
								
								$("#reason").val("");
								$("#reason").focus();
								
								return false;
							}
						}
					</c:when>
					<c:otherwise>
						if($('input[name="withdrawInfo.payDevEndReasonCd"]:checked').length == 0){
							alert("<gm:string value='jsp.member.mypage.msg.wr01'/>");
							return false;
						}
						
						if($('input[name="withdrawInfo.payDevEndReasonCd"]:checked').val().indexOf("09") > 0){
							if($("#reason").val().trim() == ""){
								alert("<gm:string value='jsp.member.mypage.msg.wr02'/>");
								
								$("#reason").val("");
								$("#reason").focus();
								
								return false;
							}
						}
					</c:otherwise>
				</c:choose>
				
				if(confirm("<gm:string value='jsp.member.mypage.msg.wr03'/>")){
					$("#withdrawForm").attr("target", "_self");
					$("#withdrawForm").attr("action", "mypageWithdrawExcute.omp");
					$("#withdrawForm").submit();
				}
			}
			
			return false;
		});
	});
	
	$(function(){
		<c:choose>
			<c:when test="${profileInfo.mbrCatCd eq CONST.MEM_TYPE_DEV_NOPAY}">
				$('input[name="withdrawInfo.freeDevEndReasonCd"]').click(function(){
					$('input[name="withdrawInfo.freeDevEndReasonCd"]').each(function(){
						if($(this).attr("checked")){
							if($(this).val().indexOf("09") > 0){
								$("#reason").removeAttr("disabled");
								$("#reason").focus();
								
								return false;
							}else{
								$("#reason").val("");
								$("#reason").attr("disabled", "true");
								
								return false;
							}
						}
					});
				});
			</c:when>
			<c:otherwise>
				$('input[name="withdrawInfo.payDevEndReasonCd"]').click(function(){
					$('input[name="withdrawInfo.payDevEndReasonCd"]').each(function(){
						if($(this).attr("checked")){
							if($(this).val().indexOf("09") > 0){
								$("#reason").removeAttr("disabled");
								$("#reason").focus();
								
								return false;
							}else{
								$("#reason").val("");
								$("#reason").attr("disabled", "true");
								
								return false;
							}
						}
					});
				});
			</c:otherwise>
		</c:choose>
	});
</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 會員中心 &gt; <span>會員註銷</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title04.gif'/>" alt="會員註銷" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<form id="withdrawForm" name="withdrawForm" method="post">
	<input type="hidden" name="withdrawInfo.mbrNo" value="<g:tagAttb value="${profileInfo.mbrNo}"/>" />
	<input type="hidden" name="profileInfo.mbrId" value="<g:tagAttb value="${profileInfo.mbrId}"/>" />
	<input type="hidden" name="withdrawInfo.mbrCatcd" value="<g:tagAttb value="${profileInfo.mbrCatCd}"/>" />
	<input type="hidden" name="withdrawInfo.devMbrStatCd" value="<g:tagAttb value="${CONST.MEM_DEV_STATUS_LEAVE_MOTION}"/>" />
	<div id="contents">
 
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle08.gif'/>" alt="請選擇您申請會員註銷之理由!" /></h4>
		<ul class="bult03 mar_b8">
			<li>非常感謝您對我們的支持和配合。<br />請選擇您申請會員註銷理由, 以供反應網站服務。</li>
		</ul>
		<div class="mpbox">
			<h5 class="h53"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_h5_01.gif'/>" alt="我要申請會員註銷理由為" /></h5>
				<ul class="bult06">
					<c:choose>
						<c:when test="${profileInfo.mbrCatCd eq CONST.MEM_TYPE_DEV_NOPAY}">
							<li><gc:radioButtons name="withdrawInfo.freeDevEndReasonCd" group="${CONST.MEM_LEAVE_REASON_NOPAY}" divide="</li><li>"/></li>
						</c:when>
						<c:otherwise>
							<li><gc:radioButtons name="withdrawInfo.payDevEndReasonCd" group="${CONST.MEM_LEAVE_REASON_PAY}" divide="</li><li>"/></li>
						</c:otherwise>
					</c:choose>
				</ul>
				<c:choose>
					<c:when test="${profileInfo.mbrCatCd eq CONST.MEM_TYPE_DEV_NOPAY}">
						<p class="pad_l35 pad_t2">
							<textarea id="reason" cols="1" rows="1" name="withdrawInfo.freeDevEndReasonDscr" class="w706" 
							v:text8_limit="498" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.wr04'/>" disabled></textarea>
						</p>
					</c:when>
					<c:otherwise>
						<p class="pad_l35 pad_t2">
							<textarea id="reason" cols="1" rows="1" name="withdrawInfo.payDevEndReasonDscr" class="w706" 
							v:text8_limit="498" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.wr04'/>" disabled></textarea>
						</p>
					</c:otherwise>
				</c:choose>
		</div>
		<div class="btnarea mar_t30">
			<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" id="ok_btn" alt="確定" />
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="取消" /></a>
		</div>
 
	</div>
	</form>
	<!-- //CONTENT TABLE E-->
</div>