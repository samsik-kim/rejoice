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
	
	var formChanged = false;		// changed Flag
	var fooFns;
	
	$(document).ready(function() {

	 	<c:if test="${empty content.prodDescSmmr}">
	 		$('#prodDescSmmr').val(smmrText);
	 	</c:if>
	 	
	 	<c:if test="${empty content.prodDescDtl}">
	 		$('#prodDescDtl').text(dtlText);
	 	</c:if>

	 	// changed Flag
	 	$(function() {
	 	 	$("#editForm").find('input, textarea').change(function(){
	 	 		changeFlag();
	 	 	});
	 	});

	 	fooFns = {  
			checkprodnm : function() {		
				if (isNull($("#prodNm").val()) || $("#prodNm").val().indexOf(prodNmText) > -1) return false;
                else return true; 
			}, 
	        checkprodnmspecial :   function(c) {
	    	   var regExp = /[~!@\#$%^&*\()\=+|\\/:;?"<>']/gi;
	    	   
               if (regExp.test($(c).val())) return false;
               else return true;
     		},
			checkprodexposuredevnm : function() {
				if (isNull($("#exposureDevNm").val())) return false;
	            else return true;
	                  
			},
			checkprodprc : function() {
				if (isNull($("#prodPrc").val())) return false;
                else return true;
			},
			checkprodctgrcd : function() {
				if (isNull($("#ctgrCd").val())) return false;
                else return true;
			},
			checkprodgamedelibgrd : function() {
				if($(':input[name=content.gameDelibGrd]:checked').length > 0) return true;
				else return false;
			},
			checkprodtaginfoseq : function() {
				if (isNull($("#tagInfoSeq0").val())) return false;
                else return true;
			},
			checktagspecial: function(c) {
				var regExp = /[~!@\#$%^&*\()\=+|\\/:;?"<>']/gi;
				    	   
                if (regExp.test($(c).val())) return false;
                else return true;
			},
			checkprodproddescsmmr : function() {
				if (isNull($("#prodDescSmmr").val()) || $("#prodDescSmmr").val().indexOf(smmrText) > -1) return false;
                else return true;
			},
			checkprodproddescdtl : function() {
				if (isNull($("#prodDescDtl").val()) || $("#prodDescDtl").val().indexOf(dtlText) > -1) return false;
                else return true;
			},
			checkprodiconimg : function() {
				try{
					
					if ('${content.iconImg1.fileNm}' != '') {	
						
						if ($('#divIconImg1Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempIconImg1File').val() != '') return true;
							else return false;
						}
					} else {	
						
						if ($('#iconImg1').val() != '') return true;
						else return false;
					}
				}catch(e){
					if($('#tempIconImg1File').val() != '') return true;
					else return false;
				}
			},
			checkprodpreviewimgone : function() {
				try{
					
					if ('${content.previewImg1.fileNm}' != '') {	
						
						if ($('#divPreviewImg1Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg1File').val() != '') return true;
							else return false;
						}
					} else {	 
						
						if ($('#previewImg1').val() != '') return true;
						else return false;
					}
				}catch(e){
					if($('#tempPreviewImg1File').val() != '') return true;
					else return false;
				}
			},
			checkprodpreviewimgscd : function() {
				try{
					if ('${content.previewImg2.fileNm}' != '') {	
						
						if ($('#divPreviewImg2Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg2File').val() != '') return true;  
							else return false;
						}
					} else {	
						
						if ($('#previewImg2').val() != '') return true;
						else return false;
					}
				}catch(e){
					if($('#tempPreviewImg2File').val() != '') return true;  
					else return false;
				}
			},
			checkprodpreviewimgthd : function() {
				try{
					if ('${content.previewImg3.fileNm}' != '') {	
					
						if ($('#divPreviewImg3Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg3File').val() != '') return true;
							else return false;
						}
					} else {	
						
						if ($('#previewImg3').val() != '') return true;
						else return false;
					}
				}catch(e){
					if ($('#tempPreviewImg3File').val() != '') return true;
					else return false;
				}
			},
			checkprodpreviewimgforth : function() {
				try{
					if ('${content.previewImg4.fileNm}' != '') {	
						
						if ($('#divPreviewImg4Select').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempPreviewImg4File').val() != '') return true;  
							else return false;
						}
					} else {	
						
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

			 try{
				 $( "#prodPrc" ).val(parseInt($( "#prodPrc" ).val().replace(/,/gi,''))); 
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
			        async : false,
					success: function(responseText, status){

						if(responseText.resultMessage.indexOf("SUCCESS") > -1) {
							var url = responseText.redirectUrl;
			
							if (url.length > 0 ) {
								$("#editForm").attr("target", "_self");	
								$("#editForm").attr("action", url);
								$("#editForm").submit();
							}
			
							else {
								alert("<gm:string value='jsp.content.common.msg.result.success'/>");
								url = env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + '${content.cid}';
								$("#editForm").attr("target", "_self");	
								$("#editForm").attr("action", url);
								$("#editForm").submit();
							}
						} else {
				
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
		layerHtml += '		<h2><img src="' + env.contextPath + '/${ThreadSession.serviceLocale.language}/images/pop/pm_title_07.gif" alt="確認儲存變更內容" /></h2>';
		layerHtml += '		<p class="pop_txt2">未儲存變更內容. <br />若未經儲存離開本頁, 變更內容將自動被刪除.</p>';	
		layerHtml += '		<div class="pop_btn">';	
		layerHtml += '			<a href="javascript:doSubmit(\''+ pageGbn+'\',\'' +  objCid +'\');"><img src="'+ env.contextPath +'/${ThreadSession.serviceLocale.language}/images/pop/btn_save.gif" alt="儲存" /></a>';	
		layerHtml += '			<a href="javascript:moveTab(\''+ pageGbn+'\',\'' +  objCid +'\')"><img src="'+ env.contextPath +'/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif" alt="取消" /></a>';	
		layerHtml += '		</div>';	
		layerHtml += '	<p class="pop_close"><a href="javascript:closePopupLayer(\'changeFormOk\');"><img src="' + env.contextPath + '/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif" alt="close" /></a></p>';	
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
	.fileinputs {position: relative; overflow: hidden; height: 26px; width: 615px;}
	.fileinputs * {vertical-align: middle;}
	.fakefile {position: absolute; top:0px; left:0px; height: 30px;  z-index: 1;}
	.inputFile {position: relative; text-align: right; top: -12px; width: 615px; height:40px; filter: alpha(opacity=0); opacity: 0; z-index: 2; direction: ltl; selector-dummy: expression(this.hideFocus=true);}
</style>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 產品上架/管理 &gt; 產品管理 <strong>&gt;</strong> <span>產品現狀</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title03.gif'/>" alt="產品現狀" /></h3>
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
		
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_06.gif'/>" alt="base information" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="基本資訊">
				<caption>基本資訊</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">
							<span>*</span> <label for="product">產品名稱 </label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>無法使用部分特殊符號(&lt;,&gt;),且不得超過45Byte.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="prodNm" name="content.prodNm" value="<g:tagAttb value='${content.prodNm}'/>" class="w150" v:checkprodnm m:checkprodnm="<gm:tagAttb value='jsp.content.registContentWrite.text.prodNm01'/>" v:checkprodnmspecial  m:checkprodnmspecial="<gm:tagAttb value='jsp.content.registContentWrite.btn.prodNm02'/>"  v:text8_limit="45" m:text8_limit="<gm:tagAttb value='jsp.content.registContentWrite.btn.prodNm01'/>"/></td>
					</tr>
					<tr>
						<th scope="row">
							<span>*</span> <label for="seller">銷售人名稱 </label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>顯示於手機之產品販賣者名稱</p>
								<p>最多可輸入16字與50個英文字母.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="exposureDevNm" name="content.exposureDevNm" class="w150" value="<g:tagAttb value='${content.exposureDevNm}'/>"  v:checkprodexposuredevnm m:checkprodexposuredevnm="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.exposureDevNm01'/>" v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.exposureDevNm02'/>" /> &nbsp;<span class="txt_90">銷售人名稱為預設, 可修改</span></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="proprice">產品價格 </label></th>
						<td>NT$ 
							<input type="text" id="prodPrc" name="content.prodPrc" class="w150" value="<g:text value="${content.prodPrc}" format='R#,###,###,###'/>" onkeyup="javascript:checkPrice(this);" v:checkprodprc v:checkpayinfo m:checkprodprc="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodPrc01'/>" m:checkpayinfo="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.result05'/>" /> &nbsp;
							<span class="txt_90">(含附加稅)</span> <br />
							<c:if test="${content.payMemberInfo == CONST.MEM_TYPE_DEV_NOPAY}" >
								<span class="txt_90">付費產品的審核及販售僅限於填寫銀行資料的會員使用.</span>&nbsp;
								<a href="javascript:gotoMemberPayInfo();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_calculate.gif'/>" alt="填寫銀行資料" /></a>
							</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="product">宣傳網址</label></th>		
						<td><input type="text" id="promotionUrl" name="content.promotionUrl"  value="<c:out value='${content.promotionUrl eq null ? "http://" : content.promotionUrl}' />" class="w400" v:text8_limit="500" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.promotionUrl01'/>" /> &nbsp;<span class="txt_90"><br />若具備App外部宣傳途徑(例如:Youtuve影像), 請將網絡地址輸入於此.</span></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="productnum">產品管理號</label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>銷售人可按照其他需要(例如: 3者結算等)填入自行管理識別碼</p>
									<p>該碼按產品類別顯示於”產品販賣現狀”選單中</p>
								</div>
								</div>
							</a>
						</th>
						<td class="bgnone"><input type="text" id="corpProdNo" name="content.corpProdNo" value="<g:tagAttb value='${content.corpProdNo}'/>"  class="w400" onfocus="javascript:inputTextClear('CONTMNG');" v:regexp="[a-zA-Z0-9]+" m:regexp="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.corpProdNo01'/>" v:text8_limit="100" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.corpProdNo02'/>" /> &nbsp;<span class="txt_90">只可填寫數字,英文混合碼</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_07.gif'/>" alt="類別資訊" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="類別資訊">
				<caption>類別資訊</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> 類別</th>
						<td>
							<input type="hidden" name="content.ctgrDepth"  id="ctgrDepth" value="${content.ctgrDepth}" />
							<input type="hidden" name="content.ctgrCd"  id="ctgrCd" value="${content.ctgrCd}" v:checkprodctgrcd m:checkprodctgrcd="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.ctgrCd'/>"/>
							<c:choose>
								<c:when test="${not empty content.ctgrNm}">
									<a href="javascript:changeFlag();popSelectCtgrList('${content.cid}');">
										<span id="contentCtgrNm">${content.ctgrNm}</span> 
										&nbsp;<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cata02.gif'/>" alt="選擇類別" />
									</a>
								</c:when>
								<c:otherwise>
									<span id="contentCtgrNm"></span> &nbsp;<a href="javascript:changeFlag();popSelectCtgrList('${content.cid}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cata.gif'/>" alt="選擇類別" /></a>
								</c:otherwise>
							</c:choose>	
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 使用等級
							<a href="#" class="help">
							<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>若為未成年人不得使用的產品, 請點選”限制級”</p>
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
						<th scope="row" class="tit01"><span>*</span> 填寫關鍵字連結
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="help" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>請輸入搜尋產品時可使用的相關關鍵字</p>
							</div>
							</div>
							</a>
						</th>
						<td class="bgnone lh210">
							<input type="text" id="tagInfoSeq0" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="<g:tagAttb value='${contentTagMap.tagNm1}' />" v:checkprodtaginfoseq m:checkprodtaginfoseq="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm01'/>" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>"  v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							<input type="text" id="tagInfoSeq1" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="<g:tagAttb value='${contentTagMap.tagNm2} '/>" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							<input type="text" id="tagInfoSeq2" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="<g:tagAttb value='${contentTagMap.tagNm3}' />" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							<input type="text" id="tagInfoSeq3" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="<g:tagAttb value='${contentTagMap.tagNm4}' />" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" /><br />
							<input type="text" id="tagInfoSeq4" name="content.tagNm" class="w128" title="<gm:string value='jsp.content.contentDetailInfo.msg.tagNm01'/>" value="<g:tagAttb value='${contentTagMap.tagNm5}' />" v:checktagspecial  m:checktagspecial="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm02'/>" v:text8_limit="30" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.tagNm03'/>" />
							&nbsp;<span class="txt_90">* 不得使用特殊符號</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_08.gif'/>" alt="產品說明" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="產品說明">
				<caption>產品說明</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="abstract">簡介說明</label></th>
						<td><input type="text" id="prodDescSmmr" name="content.prodDescSmmr" value="<g:tagAttb value="${content.prodDescSmmr}"/>" class="w520" onfocus="javascript:inputTextClear('SMMR');" v:checkprodproddescsmmr  m:checkprodproddescsmmr="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescSmmr01'/>" v:text8_limit="96" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescSmmr02'/>" /></td>		
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="detailexp">詳細說明</label></th>
						<td>
							<textarea id="prodDescDtl" name="content.prodDescDtl" cols="1" rows="1" class="w520" onfocus="javascript:inputTextClear('DTL');"  v:checkprodproddescdtl  m:checkprodproddescdtl="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescDtl01'/>" v:text8_limit="3900" m:text8_limit="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.prodDescDtl02'/>"><gm:html value='${content.prodDescDtl}' /></textarea>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="expimg">說明圖片</label> 
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>可利用圖片說明產品詳細資訊. (1M以內, JPG, BMP,PNG)</p>
									</div>
								</div>
							</a>
						</th>
						<td class="bgnone">
							<div class="fileinputs" id="divDescImgSelect"  style="display:${empty content.descImg.filePos?'box':'none'};" >
								<span><input type="file" class="inputFile" id="descImg" name="content.descImg.upload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempDescImgFile','descImg');" style="cursor: pointer;" onkeydown="this.blur();"></span>
								<div class="fakefile">
									<input type="text" id="tempDescImgFile" name="tempDescImgFile" disabled="disabled" readonly value="${empty content.descImg.filePos ? '請上傳寬度 為６00 Pixel（高度不限）之JPG, BMP, PNG 檔':'' }" class="w520" /> &nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="上傳檔案" /></a>
								</div>
							</div>
							<div id="divDescImgFileInfo" style="display:${empty content.descImg.filePos ?'none':'box'};">
						
								<a href="<c:url value="/fileSupport/fileDown.omp">
									<c:param name="bnsType" value="common.path.http-share.product"/>
									<c:param name="filePath" value="${content.descImg.filePos}"/>
									<c:param name="fileName" value="${content.descImg.fileNm}"/>
									</c:url>" >${content.descImg.fileNm}
								</a> &nbsp;
								 
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divDescImgFileInfo', 'divDescImgSelect', 'descImgPreviewBtn', 'content.descImgDelFlag');">
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" alt="刪除" />
								</a>
								<a href="javascript:popPreview('${content.cid }');">
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_view1.gif'/>" alt="預覽" id="descImgPreviewBtn"/>
								</a>
							</div>
						</td>		
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_09.gif'/>" alt="產品圖片" /></h4>
		<div class="tstyleA">
			<table summary="產品圖片">
				<caption>產品圖片</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="icon">圖示</label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>若上傳尺寸為212*212的JPG檔案, 將自動轉換符合手機端頁面尺寸之圖示 (200KB以內, JPG, BMP,PNG)</p>
									</div>
								</div>
							</a>
						</th>
						<td>
							<div class="fileinputs"  id="divIconImg1Select"  style="display:${empty content.iconImg1.filePos?'box;':'none;'}">
								<span><input type="file" class="inputFile"  id="iconImg1" name="content.iconImg1.upload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempIconImg1File', 'pict');" style="cursor: pointer;" onkeydown="this.blur();" /></span>
								<div class="fakefile">
									<input type="text" id="tempIconImg1File" name="tempIconImg1File"  readonly  value="${empty content.iconImg1.filePos ? '請上傳尺寸為212*212 的 JPG, BMP, PNG 格式檔案.':''}" class="w520" v:checkprodiconimg  m:checkprodiconimg ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.iconImg1'/>" /> &nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="上傳檔案" /></a>
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
									<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" alt="刪除" />
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
						<th scope="row" class="tit01"><span>*</span> 預覽
						<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
						<div class="helpbox">
						<div class="helpboxin">
							<p>&middot; 請上傳符合手機螢幕解析度之手機端預覽圖片</p>
							<p>&middot; 請上傳可充分說明產品之圖片 (200KB以內, JPG, BMP,PNG)</p>
						</div>
						</div>
						</a>
						</th>
						<td class="bgnone lh210">
							<div class="fileinputs"  id="divPreviewImg1Select"  style="display:${empty content.previewImg1.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg1" name="content.previewImg1.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg1File','descImg');" style="cursor: pointer;" onkeydown="this.blur();"  /></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg1File" name="tempPreviewImg1File" class="w520"  readonly  value="${empty content.previewImg1.filePos ? '請上傳200kb以下的 JPG, BMP, PNG 格式產品圖片': ''   }" v:checkprodpreviewimgone  m:checkprodpreviewimgone ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg1'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="上傳檔案" /></a><br />
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
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg1FileInfo', 'divPreviewImg1Select', 'previewImgBtn', 'content.previewImg1DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="刪除" /></a>
								<br />
								</c:if>
							</div>
						
							<div class="fileinputs"  id="divPreviewImg2Select"  style="display:${empty content.previewImg2.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg2" name="content.previewImg2.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg2File','descImg');" style="cursor: pointer;" onkeydown="this.blur();" /></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg2File" name="tempPreviewImg2File" class="w520"   readonly  value="${empty content.previewImg2.filePos ? '請上傳200kb以下的 JPG, BMP, PNG 格式產品圖片':''  }" v:checkprodpreviewimgscd  m:checkprodpreviewimgscd ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg2'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="上傳檔案" /></a><br />
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
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg2FileInfo', 'divPreviewImg2Select', 'previewImgBtn', 'content.previewImg2DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="刪除" /></a>
								<br />
								</c:if>
							</div>
							
							<div class="fileinputs"  id="divPreviewImg3Select" style="display:${empty content.previewImg3.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg3" name="content.previewImg3.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg3File','descImg');" style="cursor: pointer;" onkeydown="this.blur();" /></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg3File" name="tempPreviewImg3File" class="w520"  readonly  value="${empty content.previewImg3.filePos ? '請上傳200kb以下的 JPG, BMP, PNG 格式產品圖片':''  }" v:checkprodpreviewimgthd  m:checkprodpreviewimgthd ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg3'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="上傳檔案" /></a><br />
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
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg3FileInfo', 'divPreviewImg3Select', 'previewImgBtn', 'content.previewImg3DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="刪除" /></a>
								<br />
								</c:if>
							</div>
							
							<div class="fileinputs"  id="divPreviewImg4Select" style="display:${empty content.previewImg4.filePos?'box;':'none;'}" >
								<span><input type="file" class="inputFile"  id="previewImg4" name="content.previewImg4.upload" maxlength="1" onchange="javascript:setUploadFileNameCheck(this, 'tempPreviewImg4File','descImg');" style="cursor: pointer;" onkeydown="this.blur();"/></span>
								<div class="fakefile">
									<input type="text" id="tempPreviewImg4File" name="tempPreviewImg4File" class="w520" readonly  value="${empty content.previewImg4.filePos ? '請上傳200kb以下的 JPG, BMP, PNG 格式產品圖片':''  }"  v:checkprodpreviewimgforth  m:checkprodpreviewimgforth ="<gm:tagAttb value='jsp.content.contentDetailInfo.msg.previewImg4'/>"/>&nbsp;
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="上傳檔案" /></a>
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
								<a href="javascript:changeFlag();removeFile(document.editForm, 'divPreviewImg4FileInfo', 'divPreviewImg4Select', 'previewImgBtn', 'content.previewImg4DelFlag');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" class="mb05" alt="刪除" /></a>
								</c:if>
							</div>
							
							<c:if test="${not empty content.previewImg1.filePos && not empty content.previewImg2.filePos && not empty content.previewImg3.filePos && not empty content.previewImg4.filePos}" >
								<a href="javascript:popImagePreview('${content.cid }');" class="fltr mar_mt20"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_transfor.gif'/>" alt="預覽變換檔案" /></a>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="guideinfo">
			<ul class="bult02 txt_90">
				<li>可隨意修改詳細資訊項目, 且可以立即反應修改結果</li> 
				<li>若於請審期間, 審核通過後才可修改資訊</li>
				<li>詳情可於 <a href="<c:url value='/community/basicInfoGuide.omp'/>"><span class="txtcolor04">開發支援指南</span></a>中確定</li>
				<li><span class="txtcolor03">*</span> 為必填欄位</li>
			</ul>
		</div>
		<div class="btnarea mar_t30">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_save.gif'/>" alt="儲存" id="modifyBtn" />
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="取消" id="modifyCnl"/>
			<p class="btn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif'/>" alt="目錄"  id="listBtn"/></p>
		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->

</div>