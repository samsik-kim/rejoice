<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<script type="text/javascript"> 
var fooFns;
	$(document).ready( function() {
		
		fooFns = {
			checkverifycmtcd : function() {
				if ($('input[name=content\\.verifyCommentCd]:checked').length > 0) {
					return true;
                } else { 
                	return false;
                }
			} ,
			checkverifyetccmt : function() {
				
				if ($('#verifyCommentCd6').is(':checked')) {
	              	  if($('#verifyEtcCmt').val() != '' && $('#verifyEtcCmt').val() != null) {

	              		  if(text8Limit($('#verifyEtcCmt'), "300")) {
	              			  $("input[name=content\\.verifyEtcCmt]").val($('#verifyEtcCmt').val());
	              			  return true;
	              		  } else {
	              			  return false;
	              		  }
	              	  } else {
	              		  return false;
	              	  }
                } else {
                	$("input[name=content\\.verifyEtcCmt]").val("");
                	return true;
                }
			}
		};
	});

	function text8Limit(c, args) {
		// 글짜가 차지할 byte를 기준으로 길이 제한 검사 UTF8기준
		var val;
		var	limit;
		var	length;
		
		val		= $(c).val();
		limit	= parseInt(args); 
		length	= $.getByteLength(val, 3);

		return length <= limit;
	}
	
	function validationReqVerify() {
		var result = showValidate('verifyReqFrm', 'default', '<gm:string value="jsp.content.reVerifyReqComnt.msg.title04" />', fooFns);
		if (result)verifyReq('${content.cid}', '${tabGbn}');
		else return;
	}
</script>
<div class="layerwrap">
 
	<div id="pop_area01" class="positop">
 	<form id="verifyReqFrm" name="verifyReqFrm" method="post">
 	<input type="hidden" name="content.cid" value="${content.cid}" />
 	<input type="hidden" name="tabGbn" value="${tabGbn}" />
 	<input type="hidden" name="content.verifyEtcCmt" />
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_04.gif' />" alt="상품검증요청" /></h2>
		<p class="pop_txt4 txt_bold"><span class="txtcolor06"><g:html value="${content.prodNm}"/></span>을(를) 검증요청하시겠습니까?</p>
		<div class="appbox mar_b15">
			<ul>
				<li>등록하신 상품에 대한 검증 내용을 하단에 입력해주세요.</li>
				<li>입력하신 내용은 검증팀에서 검증 시 참고하며, 검증 시 효율적인 검증 절차를 지원합니다.</li>
				<li>검증진행 중에는 상품 정보 수정이 불가능하며 상품정보에 대한 운영자 검증이 완료되기 전까지는 '검증요청취소'가 가능합니다.</li>
			</ul>
		</div>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_txt_05.gif' />" alt="검증사유" /></h3>
		<div class="pop_box2 mar_b15">
			<ul>
				<li>
					<input type="checkbox" id="verifyCommentCd1" name="content.verifyCommentCd" class="check"  checked="checked" value="${CONST.VERIFY_COMMENT_CD_DEVICE_ADD}" v:checkverifycmtcd m:checkverifycmtcd="<gm:tagAttb value='jsp.content.reVerifyReqComnt.msg.01'/>" />
					<label for="check01">&nbsp;<gc:text code="${CONST.VERIFY_COMMENT_CD_DEVICE_ADD}"/></label>
				</li>
				<li>
					<input type="checkbox" id="verifyCommentCd2" name="content.verifyCommentCd"  value="${CONST.VERIFY_COMMENT_CD_DEVICE_DEL}" v:checkverifycmtcd m:checkverifycmtcd="<gm:tagAttb value='jsp.content.reVerifyReqComnt.msg.01'/>"/>
					<label for="check02">&nbsp;<gc:text code="${CONST.VERIFY_COMMENT_CD_DEVICE_DEL}"/></label>
				</li>
				<li>
					<input type="checkbox" id="verifyCommentCd3" name="content.verifyCommentCd"  value="${CONST.VERIFY_COMMENT_CD_FUNC_CHANGE}" v:checkverifycmtcd m:checkverifycmtcd="<gm:tagAttb value='jsp.content.reVerifyReqComnt.msg.01'/>"/>
					<label for="check03">&nbsp;<gc:text code="${CONST.VERIFY_COMMENT_CD_FUNC_CHANGE}"/></label>
				</li>
				<li>
					<input type="checkbox" id="verifyCommentCd4" name="content.verifyCommentCd"  value="${CONST.VERIFY_COMMENT_CD_IMG_CHANGE}" v:checkverifycmtcd m:checkverifycmtcd="<gm:tagAttb value='jsp.content.reVerifyReqComnt.msg.01'/>"/>
					<label for="check04">&nbsp;<gc:text code="${CONST.VERIFY_COMMENT_CD_IMG_CHANGE}"/></label>
				</li>
				<li>
					<input type="checkbox" id="verifyCommentCd5" name="content.verifyCommentCd"  value="${CONST.VERIFY_COMMENT_CD_MANIFEST_CHANGE}" v:checkverifycmtcd m:checkverifycmtcd="<gm:tagAttb value='jsp.content.reVerifyReqComnt.msg.01'/>"/>
					<label for="check05">&nbsp;<gc:text code="${CONST.VERIFY_COMMENT_CD_MANIFEST_CHANGE}"/></label>
				</li>
				<li class="pad_b5">
					<input type="checkbox" id="verifyCommentCd6" name="content.verifyCommentCd" value="${CONST.VERIFY_COMMENT_CD_MANIFEST_ETC}" v:checkverifycmtcd m:checkverifycmtcd="<gm:tagAttb value='jsp.content.reVerifyReqComnt.msg.01'/>"/>
					<label for="check06">&nbsp;<gc:text code="${CONST.VERIFY_COMMENT_CD_MANIFEST_ETC}"/></label>
				</li>
			</ul>
		</div>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_txt_06.gif' />" alt="기타" /></h3>
			<p><textarea cols="1" rows="1" class="w346" id="verifyEtcCmt" v:checkverifyetccmt m:checkverifyetccmt="<gm:tagAttb value='jsp.content.reVerifyReqComnt.msg.02'/>" ></textarea></p>
		<div class="pop_btn">
			<a href="javascript:validationReqVerify();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="확인" id="verifyReqBtn"/></a>
			<a href="javascript:closePopupLayer('popVerifyReq');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif' />" alt="취소" /></a>
		</div>
		<p class="pop_close"><a href="javascript:closePopupLayer('popVerifyReq');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif' />" alt="닫기" /></a></p>
 
 	</form>
	</div>
 
</div>
 
