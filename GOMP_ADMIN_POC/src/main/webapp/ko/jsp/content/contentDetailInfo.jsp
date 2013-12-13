<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script type="text/javascript">
<!--
	var smmrText = "<gm:string value='jsp.content.contentDetailInfo.text.smmrText' />";
	var dtlText = "<gm:string value='jsp.content.contentDetailInfo.text.dtlText' />";
	var contMngText = "<gm:string value='jsp.content.contentDetailInfo.text.contMngText' />";
	var prodNmText = "<gm:string value='jsp.content.registContentWrite.text.prodNm01' />";
	
	var formChanged = false;		// 변경 내역 확인 Flag
	var fooFns;
	
	$(document).ready(function() {

	 	<c:if test="${empty content.prodDescSmmr}">
	 		$('#prodDescSmmr').val(smmrText);
	 	</c:if>
	 	
	 	<c:if test="${empty content.prodDescDtl}">
	 		$('#prodDescDtl').text(dtlText);
	 	</c:if>

	 	// 변경 내용 Flag
	 	$(function() {
	 	 	$("#editForm").find('input, textarea').change(function(){
	 	 		changeFlag();
	 	 	});
	 	});

	 	fooFns = {  
			checkprodnm : function() {//상품명
				if (isNull($("#prodNm").val()) || $("#prodNm").val().indexOf(prodNmText) > -1) return false;
                else return true; 
			}, 
	        checkprodnmspecial :   function(c) {//상품명특수문자
	    	   var regExp = /[~!@\#$%^&*\()\=+|\\/:;?"<>']/gi;
	    	   
               if (regExp.test($(c).val())) return false;
               else return true;
     		},
			checkprodexposuredevnm : function() {//판매자명
				if (isNull($("#exposureDevNm").val())) return false;
	            else return true;
	                  
			},
			checkprodprc : function() {//상품가격
				if (isNull($("#prodPrc").val())) return false;
                else return true;
			},
			checkprodctgrcd : function() {//분류
				if (isNull($("#ctgrCd").val())) return false;
                else return true;
			},
			checkprodgamedelibgrd : function() {//이용등급
				if($(':input[name=content.gameDelibGrd]:checked').length > 0) return true;
				else return false;
			},
			checkprodtaginfoseq : function() {//태그
				if (isNull($("#tagInfoSeq0").val())) return false;
                else return true;
			},
			checktagspecial: function(c) {//태그 특수문자
				var regExp = /[~!@\#$%^&*\()\=+|\\/:;?"<>']/gi;
				    	   
                if (regExp.test($(c).val())) return false;
                else return true;
			},
			checkprodproddescsmmr : function() {//요약설명
				if (isNull($("#prodDescSmmr").val()) || $("#prodDescSmmr").val().indexOf(smmrText) > -1) return false;
                else return true;
			},
			checkprodproddescdtl : function() {//상세설명
				if (isNull($("#prodDescDtl").val()) || $("#prodDescDtl").val().indexOf(dtlText) > -1) return false;
                else return true;
			},
			checkprodiconimg : function() {//대표아이콘1
				try{
					
					if ('${content.iconImg1.fileNm}' != '') {	//한번 저장 후
						
						if ($('#divIconImg1Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempIconImg1File').val() != '') return true;
							else return false;
						}
					} else {	// 최초  
						
						if ($('#iconImg1').val() != '') return true;
						else return false;
					}
				}catch(e){
					if($('#tempIconImg1File').val() != '') return true;
					else return false;
				}
			},
			checkprodpreviewimgone : function() {//미리보기1
				try{
					
					if ('${content.previewImg1.fileNm}' != '') {	//한번 저장 후
						
						if ($('#divPreviewImg1Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg1File').val() != '') return true;
							else return false;
						}
					} else {	// 최초  
						
						if ($('#previewImg1').val() != '') return true;
						else return false;
					}
				}catch(e){
					if($('#tempPreviewImg1File').val() != '') return true;
					else return false;
				}
			},
			checkprodpreviewimgscd : function() {//미리보기2
				try{
					if ('${content.previewImg2.fileNm}' != '') {	//한번 저장 후
						
						if ($('#divPreviewImg2Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg2File').val() != '') return true;  
							else return false;
						}
					} else {	// 최초
						
						if ($('#previewImg2').val() != '') return true;
						else return false;
					}
				}catch(e){
					if($('#tempPreviewImg2File').val() != '') return true;  
					else return false;
				}
			},
			checkprodpreviewimgthd : function() {//미리보기3
				try{
					if ('${content.previewImg3.fileNm}' != '') {	//한번 저장 후
					
						if ($('#divPreviewImg3Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg3File').val() != '') return true;
							else return false;
						}
					} else {	// 최초
						
						if ($('#previewImg3').val() != '') return true;
						else return false;
					}
				}catch(e){
					if ($('#tempPreviewImg3File').val() != '') return true;
					else return false;
				}
			},
			checkprodpreviewimgforth : function() {//미리보기4
				try{
					if ('${content.previewImg4.fileNm}' != '') {	//한번 저장 후
						
						if ($('#divPreviewImg4Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg4File').val() != '') return true;  
							else return false;
						}
					} else {	// 최초
						
						if ($('#previewImg4').val() != '') return true;
						else return false;
					}
				}catch(e){
					if ($('#tempPreviewImg4File').val() != '') return true;
					else return false;
				}
			},
			checkpayinfo : function() {
				<c:if test="${content.payMemberInfo == CONST.MEM_TYPE_DEV_NOPAY}" >
				var priceValue = parseInt($("#prodPrc").val());
				
				if(priceValue > 0) {
					 $( "#prodPrc" ).val(0);	
					return false;
				} else return true;
				</c:if>
				<c:if test="${content.payMemberInfo == CONST.MEM_TYPE_DEV_PAY}" >
					return true;
				</c:if>
			}
		};
	 	
	 	$('#modifyBtn').click(function(){
	 		doSubmit('SAVE', '${content.cid}');
	 	}).css("cursor", "pointer");
	 	
	 	$('#modifyCnl').click(function(){
	 		location.reload(); return false;
	 	}).css("cursor", "pointer");
	 	
	 	$('#listBtn').click(function(){
	 		var frm = $('#editForm');
			frm.attr("target", "_self");	
			frm.attr("action","./contentsStatusList.omp");
			frm.submit();
	 		
	 	}).css("cursor", "pointer");
	});
	
	function inputTextClear(type) {
		if(type=='SMMR' && ($("#prodDescSmmr").val() == smmrText)) {
			$("#prodDescSmmr").val('');
			$("#prodDescSmmr").empty();
		} else if (type=='DTL' && ($("#prodDescDtl").val() == dtlText) ) {
			$("#prodDescDtl").val('');
			$("#prodDescDtl").text('');
			$("#prodDescDtl").empty();
		} else if (type=='CONTMNG' && ($("#corpProdNo").val() == contMngText) ) {
			$("#corpProdNo").val('');
			$("#corpProdNo").empty();
		}
	}
	
	 function checkCategory() {
    	if ($('#contentCtgrNm').text() != null && $('#contentCtgrNm').text() != '') return true;
		else return false;
	}
	
	function preDoSubmit() {
		if (!formChanged) return true;
		else return false;
	}

	function changeFlag() {
		formChanged = true; 
	}

	function doSubmit(pageGbn, objCid) {
		
		closePopupLayer('changeFormOk');
		
 		var result = showValidate('editForm', 'default', '<gm:string value="jsp.content.contentDetailInfo.msg.title02"/>', fooFns);
 	
 		if (result) {
			
 			 //상품가격setting
			 try{
				 $( "#prodPrc" ).val(parseInt($( "#prodPrc" ).val().replace(/,/gi,''))); // 불러온 값중에서 컴마를 제거
			}catch(e) {}
	
			var tabUrl;
			if (pageGbn == 'DETAIL') {
				tabUrl = env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid;
			} else if (pageGbn == 'DEVELOP') {
				tabUrl = env.contextPath + "/content/contentDevInfoView.omp?content.cid=" + objCid;
			} 
			
			$('#redirectUrl').val(tabUrl);

			var options = {
					type: "post",
			        dataType:  "json",
					success: function(responseText, status){

						if(responseText.resultMessage.indexOf("SUCCESS") > -1) {
							var url = responseText.redirectUrl;
				
							// 탭이동
							if (url.length > 0 ) {
								$("#editForm").attr("target", "_self");	
								$("#editForm").attr("action", url);
								$("#editForm").submit();
							}
							// 저장버튼
							else {
								alert("<gm:string value='jsp.content.common.msg.result.success'/>");
								url = env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + '${content.cid}';
								$("#editForm").attr("target", "_self");	
								$("#editForm").attr("action", url);
								$("#editForm").submit();
							}
						} else {
							// 저장 실패
							if(responseText.resultMessage.indexOf("LOGIN") > -1) {
								location.replace( env.contextPath + "/login/loginMain.omp");
							} else if(responseText.resultMessage.length <= 0) {
								return;
							} else {
								var resultMessage = responseText.resultMessage.replace(" ", "");
								
								if (resultMessage == 'FILE_EXTENTION_ERROR') alert("<gm:string value='jsp.content.contentDetailInfo.msg.result01' />");
								else if (resultMessage == 'FILE_SIZE_ERROR') alert("<gm:string value='jsp.content.contentDetailInfo.msg.result02' />");
								else if (resultMessage == 'IMAGE_UPDATE_FAIL') alert("<gm:string value='jsp.content.contentDetailInfo.msg.result03' />");
								else if (resultMessage == 'THUMBNAIL_UPDATE_FAIL') alert("<gm:string value='jsp.content.contentDetailInfo.msg.result04' />");
								else if (resultMessage == 'NONE_PAY_MEMBER') alert("<gm:string value='jsp.content.contentDetailInfo.msg.result05' />");
								else if (resultMessage == 'PROD_PRC_LIMIT') alert("<gm:string value='jsp.content.contentDetailInfo.msg.prodPrd'><gm:arg><g:text value="${CONF['omp.dev.product.contents.prodPrc.limit']}" format='R#,###,###,###'/></gm:arg></gm:string>");
								else alert("<gm:string value='jsp.content.common.msg.result.fail' />");
								
							}
							
						}
			
					},
					error: function(xhr, textStatus, errorThrown){}
			};
			
			var frm = $('#editForm');
			frm.attr("target", "_self");	
			frm.attr("action","./ajaxModifyContentDetailInfo.omp");
			frm.ajaxSubmit(options);
			
		} else {
			return;
		}
	}
	
	// File Upload, remove Upload File
	function setUploadFileNameCheck( fileObj, id, extCode )	{
		var frm = fileObj.form;

		//var fname=document.all.myfile.value;
		var imgNmLength = fileObj.value;
		var arrImg=("file:///"+imgNmLength.replace(/ /gi,"%20").replace(/\\/gi,"/")).split("/");
		var imgNm = arrImg[arrImg.length-1];

		//alert(imgNmLength.getByteLength() + " :::: " + imgNmLength);
		if(imgNm.getByteLength() > 100){
			alert('<gm:string value="jsp.content.contentDetailInfo.msg.file01"/>');
			$(fileObj).parent().html($(fileObj).parent().html());
			return;
		}

		if ( !isExt( fileObj.value, extCode ) )	{
			alert('<gm:string value="jsp.content.contentDetailInfo.msg.file02"/>');
			$(fileObj).parent().html($(fileObj).parent().html());
			return;
		}

		var index = fileObj.value.lastIndexOf("\\");

		if ( index > -1 )	{
			$("#"+id).val(fileObj.value.substring(index+1));
		}	else	{
			$("#"+id).val(fileObj.value);
		}
		
	}
	
	//function trim(objStr) {
	//	return objStr.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	//}

	// check contents  layer popup
	function showChangeFormOkLayer(pageGbn, objCid) {
		var layerHtml = "";  
	
		layerHtml += '<div class="layerwrap">';   
		layerHtml += '	<div class="layerbg"></div>'; 
		layerHtml += '	<div id="pop_area01">'; 
		layerHtml += '		<h2><img src="' + env.contextPath + '/${ThreadSession.serviceLocale.language}/images/pop/mp_title_01.gif" alt="변경 내용 저장 확인" /></h2>';
		layerHtml += '		<p class="pop_txt2">변경된 내용이 저장되지 않았습니다. <br />저장을 하지 않고 이동하실 경우 변경된 내용은 삭제됩니다.</p>';	
		layerHtml += '		<div class="pop_btn">';	
		layerHtml += '			<a href="javascript:doSubmit(\''+ pageGbn+'\',\'' +  objCid +'\');"><img src="'+ env.contextPath +'/${ThreadSession.serviceLocale.language}/images/pop/btn_save.gif" alt="저장" /></a>';	
		layerHtml += '			<a href="javascript:moveTab(\''+ pageGbn+'\',\'' +  objCid +'\')"><img src="'+ env.contextPath +'/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif" alt="취소" /></a>';	
		layerHtml += '		</div>';	
		layerHtml += '	<p class="pop_close"><a href="javascript:closePopupLayer(\'changeFormOk\');"><img src="' + env.contextPath + '/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif" alt="닫기" /></a></p>';	
		layerHtml += '	</div>';		
		layerHtml += '</div>';
		
		createPopupLayer("changeFormOk");
		$("#changeFormOk_body").html(layerHtml);
		showPopupLayer("changeFormOk", "wrap", 60);		
	}

	// page move mypagepage
	function gotoMemberPayInfo() {
		location.href = "${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }" + "/mypage/mypageIntro.omp?forwardAction=CALCULATE";
	}

//-->
</script>
<style>							
	.fileinputs {position: relative; overflow: hidden; height: 24px; width: 600px;}
	.fileinputs * {vertical-align: middle;}
	.fakefile {position: absolute; top:0px; left:0px; height: 30px;  z-index: 1;}
	.inputFile {position: relative; text-align: right; top: -12px; width: 500px; height:40px; filter: alpha(opacity=0); opacity: 0; z-index: 2; direction: ltl; selector-dummy: expression(this.hideFocus=true);}
</style>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 상품등록/관리 &gt; 상품관리 <strong>&gt;</strong> <span>상품현황</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title03.gif'/>" alt="상품현황" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	<form id="editForm" name="editForm"  method="post" enctype="multipart/form-data" >	
		<input type="hidden" id="cid" name="content.cid" 			value="${content.cid }" />
		<input type="hidden" name="content.vmType" 					value="${content.vmType }" />
		<input type="hidden" name="content.searchValue"				value="${content.searchValue}" />
		<input type="hidden" name="content.searchType"				value="${content.searchType}" />
		<input type="hidden" name="content.saleSearchType" 			value="${content.saleSearchType}" />
		<input type="hidden" name="content.page.no"					value="${content.page.no}" />
		
		<input type="hidden" id="redirectUrl" 	name="redirectUrl" 	value=""/>
		<input type="hidden" id="tabGbn" 		name="tabGbn" 		value="${tabGbn}"/>
		
		<input type="hidden" name="content.descImgDelFlag" 			value="${content.descImgDelFlag }" 		id="descImgDelFlag"/>
		<input type="hidden" name="content.previewImg1DelFlag" 		value="${content.previewImg1DelFlag }" 	id="previewImg1DelFlag"/>
		<input type="hidden" name="content.previewImg2DelFlag" 		value="${content.previewImg2DelFlag }" 	id="previewImg2DelFlag"/>
		<input type="hidden" name="content.previewImg3DelFlag" 		value="${content.previewImg3DelFlag }" 	id="previewImg3DelFlag"/>
		<input type="hidden" name="content.previewImg4DelFlag" 		value="${content.previewImg4DelFlag }" 	id="previewImg4DelFlag"/>
		<input type="hidden" name="content.iconImg1DelFlag" 		value="${content.iconImg1DelFlag }" 	id="iconImg1DelFlag"/>
		<input type="hidden" name="content.iconImg2DelFlag" 		value="${content.iconImg2DelFlag }" 	id="iconImg2DelFlag"/>
		
		<input type="hidden" name="content.descImg.cid" 			value="${content.cid }" />
		<input type="hidden" name="content.previewImg1.cid" 		value="${content.cid }" />
		<input type="hidden" name="content.previewImg2.cid" 		value="${content.cid }" />
		<input type="hidden" name="content.previewImg3.cid" 		value="${content.cid }" />
		<input type="hidden" name="content.previewImg4.cid" 		value="${content.cid }" />
		<input type="hidden" name="content.iconImg1.cid" 			value="${content.cid }" />
		<input type="hidden" name="content.descImg.imgGbn" 			value="${CONST.CONTENT_IMAGE_DESC}" />
		<input type="hidden" name="content.previewImg1.imgGbn" 		value="${CONST.CONTENT_IMAGE_PREV1}" />
		<input type="hidden" name="content.previewImg2.imgGbn" 		value="${CONST.CONTENT_IMAGE_PREV2}" />
		<input type="hidden" name="content.previewImg3.imgGbn" 		value="${CONST.CONTENT_IMAGE_PREV3}" />
		<input type="hidden" name="content.previewImg4.imgGbn" 		value="${CONST.CONTENT_IMAGE_PREV4}" />
		<input type="hidden" name="content.iconImg1.imgGbn" 		value="${CONST.CONTENT_IMAGE_ICON1}" />	
		
		<!-- include contentBaseInfo -->
		<jsp:include page="contentBaseInfo.jsp"/>
		<!-- //Tab_menu E -->
		
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_06.gif'/>" alt="기본정보" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="상품기본정보 입력">
				<caption>상품기본정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">
							<span>*</span> <label for="product">상품명</label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>일부 특수문자(<,>)는 사용이 불가하며 최대 30Byte까지 입력 가능합니다.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="prodNm" name="content.prodNm" value="<g:html value='${content.prodNm}'/>" class="w150" v:checkprodnm m:checkprodnm="<gm:tagAttb value='jsp.content.registContentWrite.text.prodNm01'/>" v:checkprodnmspecial  m:checkprodnmspecial="<gm:tagAttb value='jsp.content.registContentWrite.btn.prodNm02'/>"  v:text8_limit="45" m:text8_limit="<gm:tagAttb value='jsp.content.registContentWrite.btn.prodNm01'/>"/></td>
					</tr>
					<tr>
						<th scope="row">
							<span>*</span> <label for="seller">판매자 노출명</label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>Shop client에 표시될 상품의 판매자명을 뜻합니다.</p>
								<p>최대 16 글자, 영문 최대 50 글자까지 입력이 가능합니다.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="exposureDevNm" name="content.exposureDevNm" class="w150" value="<g:html value='${content.exposureDevNm}'/>"  v:checkprodexposuredevnm m:checkprodexposuredevnm="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.exposureDevNm01'/>" v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.exposureDevNm02'/>" /> &nbsp;<span class="txt_90">Default는 판매자명이며, 수정 가능합니다.</span></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="proprice">상품가격</label></th>
						<td>
							<input type="text" id="prodPrc" name="content.prodPrc" class="w150" value="<g:text value="${content.prodPrc}" format='R#,###,###,###'/>" onkeyup="checkPrice(this);" v:checkprodprc v:checkpayinfo m:checkprodprc="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodPrc01'/>" m:checkpayinfo="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.result05'/>" /> &nbsp;
							<span class="txt_90">원 (부가가치세 포함)</span> <br />
							<c:if test="${content.payMemberInfo == CONST.MEM_TYPE_DEV_NOPAY}" >
								<span class="txt_90">유료상품은 정산정보를 등록하신 회원에 한해 검증/판매가 가능 합니다.</span>&nbsp;
								<a href="javascript:gotoMemberPayInfo();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_calculate.gif'/>" alt="정산정보등록" /></a>
							</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="product">프로모션 URL</label></th>		
						<td><input type="text" id="promotionUrl" name="content.promotionUrl"  value="<c:out value='${content.promotionUrl eq null ? "http://" : content.promotionUrl}' />" class="w400" v:text8_limit="500" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.promotionUrl01'/>" /> &nbsp;<span class="txt_90"><br />App 홍보가 가능한 외부 리소스 (예: Youtube 동영상) 가 있는 경우 입력해주세요.</span></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="productnum">상품 관리번호</label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>판매자가 별도의 목적 (예. 3자 정산 등)을 위해 자제척으로 관리하는 식별코드를 입력할 수 있습니다.</p>
									<p>해당 식별코드는 ‘상품별 판매현황’ 메뉴에서 상품별로 함께 제공이 됩니다.</p>
								</div>
								</div>
							</a>
						</th>
						<td class="bgnone"><input type="text" id="corpProdNo" name="content.corpProdNo" value="<g:html value='${content.corpProdNo}'/>"  class="w400" onfocus="javascript:inputTextClear('CONTMNG');" v:regexp="[a-zA-Z0-9]+" m:regexp="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.corpProdNo01'/>" v:text8_limit="100" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.corpProdNo02'/>" /> &nbsp;<span class="txt_90">영문과 숫자를 조합하여 입력해주세요.</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_07.gif'/>" alt="분류정보" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="상품분류정보 입력">
				<caption>상품분류정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> 분류</th>
						<td>
							<input type="hidden" name="content.ctgrDepth"  id="ctgrDepth" value="${content.ctgrDepth}" />
							<input type="hidden" name="content.ctgrCd"  id="ctgrCd" value="${content.ctgrCd}" v:checkprodctgrcd m:checkprodctgrcd="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.ctgrCd'/>"/>
							<c:choose>
								<c:when test="${not empty content.ctgrNm}">
									<a href="javascript:changeFlag();popSelectCtgrList('${content.cid}');">
										<span id="contentCtgrNm">${content.ctgrNm}</span> 
										&nbsp;<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cata02.gif'/>" alt="분류 수정하기" />
									</a>
								</c:when>
								<c:otherwise>
									<span id="contentCtgrNm"></span> &nbsp;<a href="javascript:changeFlag();popSelectCtgrList('${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cata.gif'/>" alt="분류 선택하기" /></a>
								</c:otherwise>
							</c:choose>	
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 이용등급
							<a href="#" class="help">
							<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>미성년자가 이용 불가능한 상품은 “성인 이용가” 로 선택해 주세요.</p>
							</div>
							</div>
							</a>
						</th>
						<td>
							<input type="radio" name="content.gameDelibGrd" value="${CONST.GRADE_ALL}"  <c:out value="${content.gameDelibGrd == CONST.GRADE_ALL ?  'checked=checked' : ''}" />  v:checkprodgamedelibgrd m:checkprodgamedelibgrd="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.gameDelibGrd'/>" /><label for="radio1"> <gm:string value='jsp.content.contentDetailInfo.lbl.gameDelibGrd01' /></label>&nbsp;&nbsp;
							<input type="radio" name="content.gameDelibGrd" value="${CONST.GRADE_ADULT}" <c:out value="${content.gameDelibGrd == CONST.GRADE_ADULT ?  'checked=checked' : ''}" /> v:checkprodgamedelibgrd m:checkprodgamedelibgrd="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.gameDelibGrd'/>"/><label for="radio2"> <gm:string value='jsp.content.contentDetailInfo.lbl.gameDelibGrd02'/></label>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> 태그입력
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>상품 검색 시 다양한 Keyword를 입력해 주세요.</p>
							</div>
							</div>
							</a>
						</th>
						<td class="bgnone lh210">
							<input type="text" id="tagInfoSeq0" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="${contentTagMap.tagNm1}" v:checkprodtaginfoseq m:checkprodtaginfoseq="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm01'/>" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>"  v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							<input type="text" id="tagInfoSeq1" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="${contentTagMap.tagNm2}" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							<input type="text" id="tagInfoSeq2" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="${contentTagMap.tagNm3}" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" /><br />
							<input type="text" id="tagInfoSeq3" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="${contentTagMap.tagNm4}" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							<input type="text" id="tagInfoSeq4" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="${contentTagMap.tagNm5}" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							&nbsp;<span class="txt_90">* 특수문자 사용불가</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_08.gif'/>" alt="상품설명" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="상품설명정보 입력">
				<caption>상품설명정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="abstract">요약 설명</label></th>
						<td><input type="text" id="prodDescSmmr" name="content.prodDescSmmr" value="${content.prodDescSmmr}" class="w410" onfocus="javascript:inputTextClear('SMMR');" v:checkprodproddescsmmr  m:checkprodproddescsmmr="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescSmmr01'/>" v:text8_limit="96" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescSmmr02'/>" /></td>		
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="detailexp">상세 설명</label></th>
						<td>
							<textarea id="prodDescDtl" name="content.prodDescDtl" cols="1" rows="1" class="w410" onfocus="javascript:inputTextClear('DTL');"  v:checkprodproddescdtl  m:checkprodproddescdtl="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescDtl01'/>" v:text8_limit="3900" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescDtl02'/>">${content.prodDescDtl}</textarea>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="expimg">설명이미지</label> 
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>상품에 대한 보다 자세한정보를 이미지로 표현할 수 있습니다.</p>
									</div>
								</div>
							</a>
						</th>
						<td class="bgnone">
							<div class="fileinputs" id="divDescImgSelect"  style="display:${empty content.descImg.filePos?'box':'none'};" >
								<span><input type="file" class="inputFile" id="descImg" name="content.descImg.upload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempDescImgFile','descImg');" style="cursor: pointer;" onkeydown="this.blur();"></span>
								<div class="fakefile">
									<input type="text" id="tempDescImgFile" name="tempDescImgFile" disabled="disabled" readonly value="${empty content.descImg.filePos ? 'Width 600 Pixel (Height 제한없음) JPG파일을 등록해주시기 바랍니다.':'' }" class="w410" /> &nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a>
								</div>
							</div>
							<div id="divDescImgFileInfo" style="display:${empty content.descImg.filePos ?'none':'box'};">
						
								<a href="<c:url value="/fileSupport/bbsFileDown.omp">
									<c:param name="bnsType" value="common.path.http-share.product"/>
									<c:param name="filePath" value="${content.descImg.filePos}"/>
									<c:param name="fileName" value="${content.descImg.fileNm}"/>
									</c:url>" >${content.descImg.fileNm}
								</a> &nbsp;
								 
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divDescImgFileInfo', 'divDescImgSelect', 'descImgPreviewBtn', 'content.descImgDelFlag');">
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" alt="삭제하기" />
								</a>
								<a href="javascript:popPreview('${content.cid }');">
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_view1.gif'/>" alt="미리보기" id="descImgPreviewBtn"/>
								</a>
							</div>
						</td>		
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_09.gif'/>" alt="상품이미지" /></h4>
		<div class="tstyleA">
			<table summary="상품이미지정보 입력">
				<caption>상품이미지정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="icon">대표아이콘</label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>212*212 사이즈의 JPG파일을 등록하면 Shop Client에서 사용되는 이미지 사이즈에 맞게 자동 생성됩니다.</p>
									</div>
								</div>
							</a>
						</th>
						<td>
							<div class="fileinputs"  id="divIconImg1Select"  style="display:${empty content.iconImg1.filePos?'box;':'none;'}">
								<span><input type="file" class="inputFile"  id="iconImg1" name="content.iconImg1.upload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempIconImg1File', 'pict');" style="cursor: pointer;" onkeydown="this.blur();" /></span>
								<div class="fakefile">
									<input type="text" id="tempIconImg1File" name="tempIconImg1File"  readonly  value="${empty content.iconImg1.filePos ? '212*212 크기의 JPG, PNG, GIF, BMP파일을 등록해주시기 바랍니다.':''}" class="w410" v:checkprodiconimg  m:checkprodiconimg ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.iconImg1'/>" /> &nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a>
								</div>
							</div>
							<div id="divIconImg1FileInfo" style="display:${empty content.iconImg1.filePos?'none':'box'};">	
								<p>
									<a href="<c:url value="/fileSupport/fileDown.omp">
										<c:param name="bnsType" value="common.path.http-share.product"/>
										<c:param name="filePath" value="${content.iconImg1.filePos}"/>
										<c:param name="fileName" value="${content.iconImg1.fileNm}"/>
										</c:url>" >${content.iconImg1.fileNm }</a> &nbsp;
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divIconImg1FileInfo', 'divIconImg1Select', 'iconImgBtn', 'content.iconImg1DelFlag');">
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" alt="삭제하기" />
								</a>
								<br />
								</p>
								<p class="vb">
									<span class="imgbox"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000108}" alt="72" width="72" height="72"/></span>
									<span class="imgbox1"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000109}" alt="130"   width="130" height="130"/></span>
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> 미리보기
						<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
						<div class="helpbox">
						<div class="helpboxin">
							<p>Shop Client의 상품 상세의 미리보기 기능에 제공되는 이미지를 등록합니다.</p>
							<p>상품 내용을 잘 표현할 수 있는 이미지를 등록해주세요. (200KB 이하, JPG)</p>
						</div>
						</div>
						</a>
						</th>
						<td class="bgnone lh210">
							<div class="fileinputs"  id="divPreviewImg1Select"  style="display:${empty content.previewImg1.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg1" name="content.previewImg1.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg1File','descImg');" style="cursor: pointer;" onkeydown="this.blur();"  /></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg1File" name="tempPreviewImg1File" class="w410"  readonly  value="${empty content.previewImg1.filePos ? '상품 이미지의 원본 사이즈, 200KB 이하의 JPG 파일을 등록해 주시기 바랍니다.': ''   }" v:checkprodpreviewimgone  m:checkprodpreviewimgone ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg1'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a><br />
								</div>
							</div>
							<div id="divPreviewImg1FileInfo" style="display:${empty content.previewImg1.filePos ? 'none':'box' };">
								<c:if test="${not empty content.previewImg1.filePos }">
									<a href="<c:url value="/fileSupport/fileDown.omp">
										<c:param name="bnsType" value="common.path.http-share.product"/>
										<c:param name="filePath" value="${content.previewImg1.filePos}"/>
										<c:param name="fileName" value="${content.previewImg1.fileNm}"/>
										</c:url>" >${content.previewImg1.fileNm } &nbsp;
									</a>	
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg1FileInfo', 'divPreviewImg1Select', 'previewImgBtn', 'content.previewImg1DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="삭제하기" /></a>
								<br />
								</c:if>
							</div>
						
							<div class="fileinputs"  id="divPreviewImg2Select"  style="display:${empty content.previewImg2.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg2" name="content.previewImg2.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg2File','descImg');" style="cursor: pointer;" onkeydown="this.blur();" /></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg2File" name="tempPreviewImg2File" class="w410"   readonly  value="${empty content.previewImg2.filePos ? '상품 이미지의 원본 사이즈, 200KB 이하의 JPG 파일을 등록해 주시기 바랍니다.':''  }" v:checkprodpreviewimgscd  m:checkprodpreviewimgscd ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg2'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a><br />
								</div>
							</div>
							<div id="divPreviewImg2FileInfo" style="display:${empty content.previewImg2.filePos ? 'none':'box' };">
								<c:if test="${not empty content.previewImg2.filePos }">
									<a href="<c:url value="/fileSupport/fileDown.omp">
										<c:param name="bnsType" value="common.path.http-share.product"/>
										<c:param name="filePath" value="${content.previewImg2.filePos}"/>
										<c:param name="fileName" value="${content.previewImg2.fileNm}"/>
										</c:url>" >${content.previewImg2.fileNm } &nbsp;
									</a>	
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg2FileInfo', 'divPreviewImg2Select', 'previewImgBtn', 'content.previewImg2DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="삭제하기" /></a>
								<br />
								</c:if>
							</div>
							
							<div class="fileinputs"  id="divPreviewImg3Select" style="display:${empty content.previewImg3.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg3" name="content.previewImg3.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg3File','descImg');" style="cursor: pointer;" onkeydown="this.blur();" /></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg3File" name="tempPreviewImg3File" class="w410"  readonly  value="${empty content.previewImg3.filePos ? '상품 이미지의 원본 사이즈, 200KB 이하의 JPG 파일을 등록해 주시기 바랍니다.':''  }" v:checkprodpreviewimgthd  m:checkprodpreviewimgthd ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg3'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a><br />
								</div>
							</div>
							<div id="divPreviewImg3FileInfo" style="display:${empty content.previewImg3.filePos ? 'none':'box' };">
								<c:if test="${not empty content.previewImg3.filePos }">
									<a href="<c:url value="/fileSupport/fileDown.omp">
										<c:param name="bnsType" value="common.path.http-share.product"/>
										<c:param name="filePath" value="${content.previewImg3.filePos}"/>
										<c:param name="fileName" value="${content.previewImg3.fileNm}"/>
										</c:url>" >${content.previewImg3.fileNm } &nbsp;
									</a>	
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg3FileInfo', 'divPreviewImg3Select', 'previewImgBtn', 'content.previewImg3DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="삭제하기" /></a>
								<br />
								</c:if>
							</div>
							
							<div class="fileinputs"  id="divPreviewImg4Select" style="display:${empty content.previewImg4.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg4" name="content.previewImg4.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg4File','descImg');" style="cursor: pointer;" onkeydown="this.blur();"/></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg4File" name="tempPreviewImg4File" class="w410" readonly  value="${empty content.previewImg4.filePos ? '상품 이미지의 원본 사이즈, 200KB 이하의 JPG 파일을 등록해 주시기 바랍니다.':''  }"  v:checkprodpreviewimgforth  m:checkprodpreviewimgforth ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg4'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a>
								</div>
							</div>
							<div id="divPreviewImg4FileInfo" style="display:${empty content.previewImg4.filePos ? 'none':'box' };">
								<c:if test="${not empty content.previewImg4.filePos }">
									<a href="<c:url value="/fileSupport/fileDown.omp">
										<c:param name="bnsType" value="common.path.http-share.product"/>
										<c:param name="filePath" value="${content.previewImg4.filePos}"/>
										<c:param name="fileName" value="${content.previewImg4.fileNm}"/>
										</c:url>" >${content.previewImg4.fileNm } &nbsp;
									</a>	
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg4FileInfo', 'divPreviewImg4Select', 'previewImgBtn', 'content.previewImg4DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="삭제하기" /></a>
								</c:if>
							</div>
							
							<c:if test="${not empty content.previewImg1.filePos && not empty content.previewImg2.filePos && not empty content.previewImg3.filePos && not empty content.previewImg4.filePos}" >
								<a href="javascript:popImagePreview('${content.cid }');" class="fltr mar_mt20"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_transfor.gif'/>" alt="변환된 이미지 확인" /></a>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="guideinfo">
			<ul class="bult02 txt_90">
				<li>상세정보 항목은 자유롭게 수정하실 수 있으며, 실시간으로 수정 반영됩니다.</li> 
				<li>검증 요청 중일 경우 검증이  완료된 이후에 수정 가능합니다.</li>
				<li>더 자세한 안내는 <a href="<c:url value='/community/basicInfoGuide.omp'/>"><span class="txtcolor04">개발지원가이드</span></a>를 통해 확인하실 수 있습니다.</li>
				<li><span class="txtcolor03">*</span> 가 표시된 부분은 필수입력 항목입니다.</li>
			</ul>
		</div>
		<div class="btnarea mar_t30">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_save.gif'/>" alt="저장" id="modifyBtn" />
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="취소" id="modifyCnl"/>
			<p class="btn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif'/>" alt="목록"  id="listBtn"/></p>
		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->

</div>