<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/commonTag.tld" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language='javascript'>
	$(document).ready(function(){
		if(opener.document.bannerListForm.key.value!=''&&opener.document.bannerListForm.productType.value=='nm'){
			$("#dpProdNm").attr("value",opener.document.bannerListForm.key.value);
			opener.document.bannerListForm.key.value ="";
			funcSearch();
		}
		if(opener.document.bannerListForm.key.value!=''&&opener.document.bannerListForm.productType.value=='aid'){
			$("#aid").attr("value",opener.document.bannerListForm.key.value);
			opener.document.bannerListForm.key.value ="";
			funcSearch();
		}	
	});
	function goPage(no) {
		$("form[name=searchForm] input[name=sub\\.page\\.no]").val(no);
		funcSearch();
	}
	
	function funcSearch(){
		document.searchForm.action="popProdList.omp";
		//document.searchForm.target="self";
		document.searchForm.submit();
	}
	
	function funcProdDetail(prodNm,aid,prodId){
		window.opener.parent.selectProduct(prodNm,aid,prodId);
		window.close();
	}
	
	$(document).ready( function()	{

		// 전체 체크박스 이벤트
		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name='checkProdId']" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

	} );
	
	var makeProdIdStr = function() {
		var ids = "";
		$("input:checkbox[name=checkProdId]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};
	
	function closePop(){
		window.close();
	}
</script>
	
</head>

<body class="popup">
	<div id="popup_area_810">
		<h1>搜尋產品</h1>
        <s:form id="searchForm" name="searchForm" action="popAdminProdList" theme="simple" >
		<input type="hidden" id="dpUpCatNo" name="sub.dpUpCatNo" value="${sub.dpUpCatNo}"/>
		<input type="hidden" id="scrGbn" name="sub.scrGbn" value="${sub.scrGbn}"/>
		<input type="hidden" id="dpCatNo" name="sub.dpCatNo" value="${sub.dpCatNo}"/>
		<input type="hidden" id="no" name="sub.page.no" value="1" />  
		<!-- <div style="overflow:hidden;overflow-x:scroll;width:770px;clear:both;">-->
		<div id="popup_area_810">
         <table class="pop_tabletype01">
         <colgroup><col style="width:20%;"><col><col style="width:20%;"></colgroup>
        	<tr>
            	<th>搜尋產品</th>
                <td>
                	<label for="#">產品名稱&nbsp;</label>
                    <input id="dpProdNm" name="sub.dpProdNm" value="${sub.dpProdNm}" type="text" class="txt" style="width:80px;" /><!-- 2011-03-18 -->
                    <label for="#">產品AID&nbsp;</label>
                    <input id="aid" name="sub.aid" value="${sub.aid}" type="text" class="txt" style="width:80px;" />
                </td>
                <td class="text_c"><a class="btn" href="javascript:funcSearch();"><strong>搜尋</strong></a></td>
            </tr>
        </table>
        </s:form>
        <br />
        
        <div style="overflow:hidden;overflow-x:scroll;width:770px;clear:both;">
		<table class="pop_tabletype02" style="width:1247px;">
			<colgroup><col style="width:5%;"><col style="width:7%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:13%;"><col style="width:15%;"></colgroup>
        	<tr>
                <th>序號</th>
                <th>類別</th>
                <th>圖示</th>
                <th>產品名稱</th>
                <th>產品價格</th>
                <th>開發商</th>
                <th>下載</th>
                <th>產品AID</th>
                <th>上架日期</th>
            </tr>
			<c:choose>
			<c:when test="${totalCount eq 0 }">
			<tr>
				<td colspan="10" class="text_c">無資料。</td>
			</tr>
			</c:when>
			<c:otherwise>
			<c:forEach items="${list}" var="adminProdList" varStatus="status">
            <tr>
                <td><g:html value="${adminProdList.rnum}"/></td>
                <td><g:html value="${adminProdList.catNm}"/></td>
                <td><img src="${CONF['omp.common.url.http-share.product']}${adminProdList.filePos}" width="72" height="72" alt="" /></td>
                <td><a href="javascript:funcProdDetail('${adminProdList.prodNm}','${adminProdList.aid}','${adminProdList.prodId}');"><g:html value="${adminProdList.prodNm}"/></a></td>
                <td>NT$ <g:text value="${adminProdList.prodAmt}" format="R###,###" /></td>
                <td><g:html value="${adminProdList.mbrNm}"/></td>
                <td><g:html value="${adminProdList.dwldCnt}"/></td>
                <td><g:html value="${adminProdList.aid}"/></td>
                <td><g:text value="${adminProdList.regDt}" format="L####-##-##" /></td>
            </tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
         </table>
         </div>
		<!-- paging -->
		<g:pageIndex item="${list}"/>
		<!-- //paging -->
		 <p class="mt20">※  若點選產品名稱,  將自動標示於劃面上。
		<div class="pop_btn">
			<a class="btn" href="javascript:closePop();"><strong>關閉</strong></a> </p>
		</div>		
	</div>
    <!-- //contents -->
</body>