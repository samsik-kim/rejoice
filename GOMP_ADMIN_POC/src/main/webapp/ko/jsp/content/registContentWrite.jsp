<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script language="javascript">
<!--
var options;
	var prodNmText = '<gm:string value="jsp.content.registContentWrite.text.prodNm01" />';
	
	$(document).ready( function() {
		options={ 
				// beforeSubmit:	submitConfirm,
		         success:       showSuccessResponse,  	// showResponse
		         error:         showFailResponse,  		// showResponse

		         // other available options: 
		         url:       env.contextPath + "/content/ajaxRegistConfirm.omp",         // override for form's 'action' attribute 
		         type:      "post",        				// 'get' or 'post', override for form's 'method' attribute 
		         layerId :  "createNewContentLayer",
		     	 parentLayerId : "wrap"    
		}; 

		$("#btnMod").click(function() {
			var result = showValidate('newRegForm', 'default', '<gm:string value="jsp.content.registContentWrite.msg.title01"/>', fooFns);

			if (result) {
				$('#newRegForm').ajaxSubmit(options);
			} else return;
		}).css("cursor", "pointer");
		
		$("#btnCnl").click(function() {
			location.href = env.contextPath + "/content/contentsStatusList.omp";
		}).css("cursor", "pointer");
		
		var fooFns = {
			checkprodnm : function() {
	                  if (isNull($("#prodNm").val()) || $("#prodNm").val().indexOf(prodNmText) > -1) return false;
	                  else return true;
	        },
	        checkprodnmspecial :   function(c) {
	    	   var regExp = /[~!@\#$%^&*\()\=+|\\/:;?"<>']/gi;
	    	   
               if (regExp.test($(c).val())) return false;
               else return true;
     		}
		};
	});

	// post-submit callback 
	function showSuccessResponse(data, statusText)  { 
		createPopupLayer(options.layerId);
		$("#"+options.layerId+"_body").html(data);
		showPopupLayer(options.layerId, options.parentLayerId, 50);			
	}
	
	function showFailResponse()  {
		alert("<gm:string value='jsp.content.common.msg.result.fail'/>");
	}
	
	function submitConfirm(formData, jqForm, options) {	
		//상품명
		if(isNull($("#prodNm").val()) || $("#prodNm").val().indexOf(prodNmText) > -1){
			alert("<gm:string value='jsp.content.registContentWrite.text.prodNm01'/>");
			return false;
		} else {
			return true;
		}
		
	}
	
	function inputTextClear(type) {

		if(type=='1' && $("#prodNm").val().indexOf(prodNmText) > -1) {
			$("#prodNm").val('');
			$("#prodNm").empty();
		}
	}
//-->	
</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 상품등록/관리 &gt; 상품등록 <strong>&gt;</strong> <span>상품등록</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title02.gif'/>" alt="상품등록" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	<form id="newRegForm" name="newRegForm"  method="post">	
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_05.gif'/>" alt="상품 기본 정보 입력" /></h4>
		<div class="tstyleA">
			<table summary="상품 기본 정보 입력">
				<caption>상품 기본 정보 입력</caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">플랫폼 선택</th>
						<td>
							<label for="radio1"> <gc:radioButtons name="content.vmType" group="PD0056" codeType="full" value="${content.vmType}" divide=" &nbsp;" split="&nbsp;"/></label>
						</td>	
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="product">상품명</label> 
						<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
								<div class="helpboxin">
									<p>일부 특수문자(<,>)는 사용이 불가하며 최대 30Byte까지 입력 가능합니다.</p>
								</div>
							</div> 
						</a>
						</th>
						<td class="bgnone">
							<input type="text" id="prodNm" name="content.prodNm" value="<gm:string value='jsp.content.registContentWrite.text.prodNm01'/>"  onfocus="javascript:inputTextClear('1');" class="w620" v:checkprodnm m:checkprodnm="<gm:tagAttb value='jsp.content.registContentWrite.text.prodNm01'/>" v:checkprodnmspecial  m:checkprodnmspecial="<gm:tagAttb value='jsp.content.registContentWrite.btn.prodNm02'/>" v:text8_limit="45" m:text8_limit="<gm:tagAttb value='jsp.content.registContentWrite.btn.prodNm01'/>"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnarea mar_t30">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_register.gif'/>" alt="등록"  id="btnMod" />
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="취소" id="btnCnl"/>
		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->
</div>