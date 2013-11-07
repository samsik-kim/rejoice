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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>카테고리 등록</title>

<script language='javascript'>

	function goPage(no) {
		$("form[name=searchForm] input[name=sub\\.page\\.no]").val(no);
		funcSearch();
	}
	
	function funcSearch(){

		document.searchForm.action="popAdminProdList.omp";
		document.searchForm.target="popup";
		document.searchForm.submit();
	}

	function funcInsertAdminProd() {

		if(document.getElementById("newDpCatNm").value == null || document.getElementById("newDpCatNm").value == "") {
			alert("<gm:string value='카테고리 명을 입력해주세요.'/>");
			return;
		}

		if(document.getElementById("bodyImageUpload").value == null || document.getElementById("bodyImageUpload").value == "") {
			alert("<gm:string value='Body 이미지를 선택해주세요.'/>");
			return;
		}

		if(!document.getElementById("bodyImageUpload").value.toLowerCase().match(/\.(jpg|gif)$/i) && document.getElementById("bodyImageUpload").value != "") {
			alert("<gm:string value='Body 이미지 파일 형식이 올바르지 않습니다.
확인 후 다시 등록해주세요.'/>");
			return;
		}

		document.myForm.action = './popInsertCategory.omp';
		document.myForm.submit();
	}
	
	function funcProdDetail(prodId){
		width = 440;
		height = 350;
		var url = env.contextPath +"/contents/popAdminProdDetail.omp?prodId=" + prodId;
		
		openCenteredWindow(url, width, height, "no", "prodDetail");
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
	
	function insertAdminProd() {
		if ( $( "input:checkbox[name=checkProdId]:checked" ).length == 0 )	{
			alert( "<gm:string value='jsp.contents.adminrec.popAdminProdList.msg.insert_check'/>" );
			return;
		}

		var selectedProdId =  makeProdIdStr();
		
		if(!confirm("<gm:string value='jsp.contents.adminrec.popAdminProdList.msg.insert_confirm'/>")){
			return;
		}
		var options={ 
				 success:       responseIns,
		         url:       env.contextPath+"/contents/insertAdminRecommendProd.omp?selectedProdId="+selectedProdId, 
		         type:      "post"  
			   }; 
		$('#searchForm').ajaxSubmit(options);
		//document.searchForm.action = 'insertAdminRecommendProd.omp?selectedProdId='+selectedProdId;
		//document.searchForm.submit();
		
/*
		var form = searchForm;
		form.action="insertAdminRecommendProd.omp?selectedProdId=" + selectedProdId;

		$.blockUI({ message: '<h4>잠시만 기다려 주세요.</h4>' });
		$( "#selectedProdId" ).val( selectedProdId );
		$( "#searchForm" ).submit();
*/
		//form.target="_self";
		//form.submit();
	};
	
	function responseIns(){
		alert("<gm:string value='jsp.contents.adminrec.popAdminProdList.msg.insert_success'/>");
		window.opener.$("#searchForm").attr("target", "_self");
		window.opener.document.searchForm.action="selectAdminRecommendList.omp";
		window.opener.document.searchForm.submit();
		window.close();
	}
</script>
	
</head>

<body class="popup">
	<div id="popup_area_810">
		<h1>상품조회</h1>
        <s:form id="searchForm" name="searchForm" action="popAdminProdList" theme="simple" >
		<input type="hidden" id="dpUpCatNo" name="sub.dpUpCatNo" value="${sub.dpUpCatNo}"/>
		<input type="hidden" id="scrGbn" name="sub.scrGbn" value="${sub.scrGbn}"/>
		<input type="hidden" id="dpCatNo" name="sub.dpCatNo" value="${sub.dpCatNo}"/>
		<input type="hidden" id="no" name="sub.page.no" value="1" />
        <table class="pop_tabletype01">
		<colgroup><col style="width:20%;"><col><col style="width:20%;"></colgroup>
        	<tr>
            	<th>상품검색</th>
                <td>
                	<label for="#">상품명&nbsp;</label>
                    <input id="dpProdNm" name="sub.dpProdNm" value="${sub.dpProdNm}" type="text" class="txt" style="width:80px; margin-right:30px;" /><!-- 2011-03-18 -->
                    <label for="#">상품AID&nbsp;</label>
                    <input id="aid" name="sub.aid" value="${sub.aid}" type="text" class="txt" style="width:80px;" />
                </td>
                <td class="text_c"><a class="btn" href="javascript:funcSearch();"><strong>검색</strong></a></td>
            </tr>
        </table>
        </s:form>
        <br />
		<p class="mb10"><a class="btn_s" href="javascript:insertAdminProd()"><strong>선택상품등록</strong></a></p>
        
        <table class="pop_tabletype02">
			<colgroup><col style="width:5%;"><col style="width:7%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:10%;"><col style="width:13%;"><col style="width:15%;"></colgroup>
        	<tr>
            	<th><input type="checkbox" id="checkAll" /></th>
                <th>번호</th>
                <th>카테고리</th>
                <th>이미지</th>
                <th>상품명</th>
                <th>가격</th>
                <th>개발자</th>
                <th>다운로드</th>
                <th>상품AID</th>
                <th>게시일</th>
            </tr>
			<c:choose>
			<c:when test="${totalCount eq 0 }">
			<tr>
				<td colspan="10" class="text_c">"검색결과가 없습니다."</td>
			</tr>
			</c:when>
			<c:otherwise>
			<c:forEach items="${list}" var="adminProdList" varStatus="status">
            <tr>
            	<td><input type="checkbox" name="checkProdId" value="${adminProdList.prodId}"/></td>
                <td>${adminProdList.rnum}</td>
                <td>${adminProdList.catNm}</td>
                <td><img src="${CONF['omp.common.url.http-share.product']}${adminProdList.filePos}" width="72" height="72" alt="" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" /></td>
                <td><a href="javascript:funcProdDetail('${adminProdList.prodId}');"><g:html value="${adminProdList.prodNm}" format="L#..."/></a></td>
                <td>${adminProdList.prodAmt}</td>
                <td>${adminProdList.mbrNm}</td>
                <td>${adminProdList.dwldCnt}</td>
                <td>${adminProdList.aid}</td>
                <td>${adminProdList.regDt2}</td>
            </tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
         </table>
        
        <p class="mt10"><a class="btn_s" href="javascript:insertAdminProd()"><strong>선택상품등록</strong></a></p>
		<!-- paging -->
		<g:pageIndex item="${list}"/>
		<!-- //paging -->
		
	</div>
    <!-- //contents -->
</body>
</html>
