<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ page import="com.omp.admin.common.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="<c:url value="/${ThreadSession.serviceLocale.language}/css/popup.css"/>" type="text/css">
<style type="text/css">
form{clear:both;}
/*jquery ui dialog header hidden*/
/*.ui-widget-header{display:none} */
/*.ui-dialog .ui-dialog-content {overflow:hidden; padding: 0 0;} */
</style>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.json-1.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/product.js"/>"></script>
<script type="text/javascript">
//<![CDATA[
    var formChanged = false;
	$(function() {
		$("#productForm").find('input, select, textarea').change(function(){
			formChanged = true;	
		});
		
		 $(window).bind('beforeunload', function(e) {
		        if (formChanged) {
		            return '<gm:string value="jsp.product.form.notSave"/>"';
		        }
		 });
		 
		// ====================  Product Info Save ==========================
		$("#productInfoSave").click(function(event){
			event.preventDefault();
			<c:choose>
			<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
			alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
			</c:when>
			<c:otherwise>
			
			if(showValidate('productForm', 'default', '<gm:string value="jsp.product.validate.commMsg"/>')){
				if(confirm('<gm:string value="jsp.product.change.save.confirm"/>')){
					$.post('<c:url value="/product/ajaxUpdateProductInfo.omp"/>',{changeData:$.toJSON($("#productForm").serializeObject())}, 
						function(data){
							alert(data.msg);
							// if success
							if(data.resultCode == 1){
								$(window).unbind('beforeunload');
								$("#contentsProductInfo").click();				
							}
					},"json");
				}
			}
			</c:otherwise>
			</c:choose>
		});
		
		// ==================== change category =============================
		$( "#categoryDiv" ).dialog({
			modal: true,
			autoOpen: false,
			resizable: false,
			width:480
		});

		// change category btn in page
		$("#changeCategoryBtn").click(function(event){
			event.preventDefault();
			<c:choose>
			<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
			alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
			</c:when>
			<c:otherwise>
			$("#categoryForm").reset();
			$("#categoryForm input[id="+$("#ctgrCd2").val()+"]").attr("checked","checked");
			$("#categoryDiv").dialog('open');
			</c:otherwise>
			</c:choose>
		});
		// popup close btn
		$("#categoryModalClose").click(function(event){
			event.preventDefault();
			$("#categoryDiv").dialog('close');
		});
		// change category btn in popup
		$("#doChangeCategoryBtn").click(function(event){
			event.preventDefault();
			if( $("#ctgrCd2").val() != $("#categoryForm input:radio:checked").val()){
				formChanged = true;
			}
			var ctgrCd2 = $("#ctgrCd2").val($("#categoryForm input:radio:checked").val()).val();
			$("#ctgrCd2Label").text($("#categoryForm label[for="+ctgrCd2+"]").text());
			$("#categoryDiv").dialog('close');
		});
		
		<c:choose>
		<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_ING or contents.saleStat eq CONTENT_SALE_STAT_STOP}">
			$("#stopSaleBtn").click(function(event){
				event.preventDefault();
				<c:choose>
				<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
				alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
				</c:when>
				<c:otherwise>
				if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStop"/></gm:arg></gm:string>')){
					$.post('<c:url value="/product/ajaxUpdateStopSaleStat.omp"/>',{cid:"${contents.cid}"}, 
						function(data){
							alert(data.msg);
							// if success
							if(data.resultCode == 1){
								$("#contentsBaseInfo").click();		
							}
					},"json");		
				}
				</c:otherwise>
				</c:choose>
			});
		</c:when>
		<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
			$("#startSaleBtn").click(function(event){
				event.preventDefault();
				<c:choose>
				<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
				alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
				</c:when>
				<c:otherwise>
				if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStart"/></gm:arg></gm:string>')){
					$.post('<c:url value="/product/ajaxUpdateStartSaleStat.omp"/>',{cid:"${contents.cid}"}, 
						function(data){
							alert(data.msg);
							// if success
							if(data.resultCode == 1){
								$("#contentsBaseInfo").click();							
							}
					},"json");
				}
				</c:otherwise>
				</c:choose>
			});
		</c:when>
		</c:choose>
		
		
		<c:if test="${contents.drmYn eq 'N' and contents.agrmntStat eq AGREEMENT_STATUS_AGREE and (contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat  eq CONTENT_SALE_STAT_ING or contents.saleStat  eq CONTENT_SALE_STAT_STOP)}">
			$("#registARMBtn").click(function(event){
				event.preventDefault();
				if(confirm('<gm:string value="jsp.product.arm.regist.conrim"/>')){
					$.post('<c:url value="/product/ajaxRegistArm.omp"/>',{cid:"${contents.cid}"}, 
						function(data){
							alert(data.msg);
							// if success
							if(data.resultCode == 1){
								$("#contentsProductInfo").click();							
							}
					},"json");
				}
				
			});
		</c:if>
	});
//]]>
</script>
</head>
<body>
			<div id="location">
				Home &gt; 상품관리 &gt; 상품관리 &gt; <strong>상품정보</strong>
			</div><!-- //location -->

			<h1 class="fl pr10">상품정보</h1>
			<p>상품정보 조회 및 상품상태를 변경 관리합니다.</p>			
			<c:choose>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_ING or contents.saleStat eq CONTENT_SALE_STAT_STOP}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="stopSaleBtn"><strong>판매금지</strong></a></p>
			</c:when>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="startSaleBtn"><strong>판매금지 해지</strong></a></p>
			</c:when>
			</c:choose>
			<div class="tab">
				<ul>
					<li><a href="#" id="contentsBaseInfo">기본정보</a></li>
					<li class="on"><a href="#" id="contentsProductInfo">상품정보</a></li>
					<li><a href="#" id="devConts">개발정보</a></li>
					<c:if test="${CONTENT_SALE_STAT_ING eq contents.saleStat}">
					<li><a href="#" id="saleDevConts">판매중개발정보</a></li>
					</c:if>
					<c:if test="${CONTENT_SALE_STAT_NA ne contents.saleStat}">
					<li><a href="#" id="signDevConts">검증요청개발정보</a></li>
					</c:if>
					<li><a href="#" id="saleStatHisList">상태변경내역</a></li>
					<li><a href="#" id="productVerifyDetail">검증내역</a></li>
				</ul>
			</div>
			<form id="productForm" name="productForm" onsubmit="return false;">
			<input type="hidden" id="cid" name="cid" value="${sub.cid }"/>
			<input type="hidden" id="ctgrCd2" name="ctgrCd2" value="${contents.ctgrCd2 }" v:required m:required="카테고리를 지정해 주세요." />
			<table class="tabletype01">
				<colgroup><col style="width:150px;"><col ></colgroup>
				<tbody>
				<tr>
					<th>상품명</th>
					<td>${contents.prodNm }</td>
				</tr>
				<tr>
					<th>판매자명</th>
					<td>${contents.exposureDevNm }</td>
				</tr>
				<tr>
					<th>상품관리번호</th>
					<td>${contents.corpProdNo }</td>
				</tr>
				
				<tr>
					<th>카테고리</th>
					<td><label id="ctgrCd2Label" style="margin:0;padding:0">${contents.ctgrNm2 }</label> <a class="btn_s" href="#" id="changeCategoryBtn"><span>카테고리변경</span></a></td>
				</tr>
				<tr>
					<th>이용등급</th>
					<td><gc:html code="${contents.gameDelibGrd }"/></td>
				</tr>
				<tr>
					<th>상품가격</th>
					<td><g:text value="${contents.prodPrc}" format="L#,###"/> 원</td>
				</tr>
				<tr>
					<th>Promotion URL</th>
					<td><input id="promotionUrl" name="promotionUrl" type="text" class="txt" style="width:80%;" value="<g:tagAttb value="${contents.promotionUrl }" />" v:text8_limit='500,trim' m:text8_limit='<g:tagAttb value="Promotion URL은 500Byte까지 입력 가능합니다." />'/></td>
				</tr>
				<tr>
					<th>상품요약설명</th>
					<td><input id="prodDescSmmr" name="prodDescSmmr" type="text" class="txt" style="width:80%;" value="<g:tagAttb value="${contents.prodDescSmmr }" />" v:required='trim' m:required="상품요약설명을 입력해 주세요." v:text8_limit='100,trim' m:text8_limit='<g:tagAttb value="상품요약설명은 100Byte까지 입력 가능합니다." />' /></td>
				</tr>
				<tr>
					<th>상품상세설명</th>
					<td>
						<textarea id="prodDescDtl" name="prodDescDtl" class="text_area" v:required='trim' m:required="상품상세설명을 입력해 주세요." v:text8_limit='4000,trim' m:text8_limit='<g:tagAttb value="상품상세설명은 4000Byte까지 입력 가능합니다." />' ><g:tagBody value="${contents.prodDescDtl }" /></textarea>
					</td>
				</tr>
				<tr id="desc">
					<th>상품설명이미지<br /><span style="font-weight:normal;">(권장 사이즈600px / JPG, BMP, PNG 1MB이하)</span></th>
					<td>
						<img src="${CONF['omp.common.url.http-share.product']}${contentsImgMap['DP000107'].filePos}" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/temp_img01.jpg"/>');" />
					</td>
				</tr>
				<tr id="pre">
					<th>상품미리보기<br> 이미지</th>
					<td class="dl_type02">
						<dl>
							<dt>원본 (200KB 이하 / JPG, BMP, PNG 파일)</dt>
							<dd class="first"><span class="txt">1</span>
								<span class="img"><img src="${CONF['omp.common.url.http-share.product']}${contentsImgMap['DP000103'].filePos}" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/temp_img02.jpg"/>');" /></span>
							</dd>
							<dd><span class="txt">2</span>
								<span class="img"><img src="${CONF['omp.common.url.http-share.product']}${contentsImgMap['DP000104'].filePos}" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/temp_img02.jpg"/>');" /></span>
							</dd>
							<dd><span class="txt">3</span>
								<span class="img"><img src="${CONF['omp.common.url.http-share.product']}${contentsImgMap['DP000105'].filePos}" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/temp_img02.jpg"/>');" /></span>
							</dd>
							<dd><span class="txt">4</span>
								<span class="img"><img src="${CONF['omp.common.url.http-share.product']}${contentsImgMap['DP000106'].filePos}" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/temp_img02.jpg"/>');" /></span>
							</dd>
						</dl>
					</td>
				</tr>
				<tr id="icon">
					<th>대표아이콘<br /><span style="font-weight:normal;">(212*212 Size의 JPG, BMP, PNG 파일이며 용량은 200kb이하)</span></th>
					<td class="dl_type01">
						<dl>
							<dt>대표아이콘1(72*72)</dt>
							<dd>
								<img src="${CONF['omp.common.url.http-share.product']}${contentsImgMap['DP000108'].filePos}" width="72" height="72" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/temp_img02.jpg"/>');" />
							</dd>
						</dl>
						<dl class="first">
							<dt>대표아이콘2(130*130)</dt>
							<dd>
								<img src="${CONF['omp.common.url.http-share.product']}${contentsImgMap['DP000109'].filePos}" width="130" height="130" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/temp_img02.jpg"/>');" />
							</dd>
						</dl>
					</td>
				</tr>
				<tr>
					<th>개발자등록<br> 키워드</th>
					<td>
						<ul>
							<c:set var="counter" value="0" />  
							<c:forEach items="${contentsTagList }" var="tagNm" varStatus="k">
							<li>${k.count }. <input id="tagNm[]" name="tagNm[]" type="text" class="txt" style="width:150px;" value="<g:tagAttb value="${tagNm }"/>" v:text8_limit='30,trim' m:text8_limit='<g:tagAttb value="키워드는 30Byte까지 입력 가능합니다." />'/></li>
							<c:set var="counter" value="${counter+1}" />
							</c:forEach>
							<c:if test="${counter < 5 }">
								<c:forEach begin="${ counter+1}"  end="5" step="1" var="k" >
									<li>${k }. <input id="tagNm[]" name="tagNm[]" type="text" class="txt" style="width:150px;" value="" v:text8_limit='30,trim' m:text8_limit='<g:tagAttb value="키워드는 30Byte까지 입력 가능합니다." />'/></li>
								</c:forEach>
							</c:if>
						</ul>
					</td>
				</tr>
				<tr>
					<th>최종변경일</th>
					<td class="align_td"><g:text format="L####-##-##" >${contents.updDttm }</g:text></td>
				</tr>
				<tr>
					<th>ARM 적용여부</th>
					<td class="align_td">
						<c:choose>
							<c:when test="${contents.drmYn eq 'Y' }"><gm:text value="jsp.product.drm.use"/></c:when>
							<c:otherwise>
							<gm:text value="jsp.product.drm.notUse"/>
							</c:otherwise>
						</c:choose>
						<c:if test="${contents.drmYn eq 'N' and contents.verifyPrgrYn ne CODE_VERIFY_REQ and contents.verifyPrgrYn ne CODE_VERIFY_ING and (contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat  eq CONTENT_SALE_STAT_ING or contents.saleStat  eq CONTENT_SALE_STAT_STOP)}"> 
						<a href="#" class="btn_s" id="registARMBtn"><span><gm:text value="jsp.product.drm.regist.btn"/></span></a>
						</c:if>	
					</td>
				</tr>
				</tbody>
			</table>
			</form>
			<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="productInfoSave"><span>저장</span></a><a class="btn" href="#" id="contentsList"><span>목록</span></a></p>
			<s:form id="searchForm" name="searchForm" action="listContents" theme="simple"  method="get">
			<input type="hidden" id="cid" name="sub.cid" value="${sub.cid }"/>
			<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
			<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
			<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
			<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
			<input type="hidden" id="masterNo" name="sub.masterNo" value="${sub.masterNo }" />
			<input type="hidden" id="vmType" name="sub.vmType" value="${sub.vmType }" />
			<input type="hidden" id="saleStat" name="sub.saleStat" value="${sub.saleStat }" />
			<input type="hidden" id="ve12rifyPrgrYn" name="sub.verifyPrgrYn" value="${sub.verifyPrgrYn }" />
			<input type="hidden" id="startDate" name="sub.startDate" value="${sub.startDate }" />
			<input type="hidden" id="endDate" name="sub.endDate" value="${sub.endDate }" />
			<input type="hidden" id="dpCat1" name="sub.dpCat1" value="${sub.dpCat1 }" />
			<input type="hidden" id="dpCat2" name="sub.dpCat2" value="${sub.dpCat2 }" />
			<input type="hidden" id="searchType" name="sub.searchType" value="${sub.searchType }" />
			<input type="hidden" id="searchText" name="sub.searchText" value="${sub.searchText }" />
			</s:form>
			<div class="popup" id="categoryDiv" title='<gm:string value="jsp.product.category.change.title"/>'>
			<div id="popup_area_440" >
				<form id="categoryForm" name="categoryForm" onsubmit="return false;">
				<table class="pop_tabletype02">
					<colgroup><col style="width:100%;"></colgroup>
					<tbody>
					<tr>
						<th>카테고리</th>
					</tr>
					<tr>
						<td>
							<ul class="ul_case01">
								<c:forEach items="${dpCatList2 }" var="dpCat">
								<li><input type="radio" name="ctgrCd2" id="${dpCat.dpCatNo }" value="${dpCat.dpCatNo }" /> <label for="${dpCat.dpCatNo }">${dpCat.dpCatNm }</label></li>
                        		</c:forEach>
							</ul>
						</td>
					</tr>
					</tbody>
				</table>
				</form>
				<div class="pop_btn" >
					<a class="btn_s" href="#" id="doChangeCategoryBtn"><strong>선택</strong></a>
					<a class="btn_s" href="#" id="categoryModalClose"><strong>취소</strong></a>
				</div>
			</div>
			</div>
</body>
</html>