<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<script type="text/javascript">

	$(document).ready( function()	{
	
		$("#divRegist").css("display", "none");
	
	} );

	var cancelRegistForm = function() {
		
		$("#divRegist").css("display", "none");
		$("#divReqRegist").css("display", "");
		
		clearRegistForm();
		$( "#upMenuId" ).val("");

	};
	
	var viewRegistForm = function( locateUpMenuId ) {
		
		if( locateUpMenuId == "" ) {
			alert("<gm:string value="jsp.adminmgr.menu.menuList.msg.alert"/>");
			return;
		}
		
		$("#divRegist").css("display", "");
		$("#divReqRegist").css("display", "none");
		
		$( "#upMenuId" ).val( locateUpMenuId );

		$( "#mode" ).val( "INS" );

		var tmpMenuDepth = "";
		if( locateUpMenuId == "ROOT" ) {
			tmpMenuDepth = "2";
		} else {
			if( locateUpMenuId.length == 4 ) {
				tmpMenuDepth = "3";
			} else {
				tmpMenuDepth = "4";
			}
		}
		//alert(locateUpMenuId + "\n" + tmpMenuDepth);

		$( "#menuDepth" ).val( tmpMenuDepth );
		var subMenuYn = "Y";
		if( tmpMenuDepth == 4 ) {
			subMenuYn = "N";
		}
		$( "#subMenuYn" ).val( subMenuYn );

		$( "#pageUrl" ).val( "${pageContext.request.contextPath}" );
		$( "#upMenuId" ).attr( "disabled", true );

	};
	
	var searchMenuList = function() {

		if ( $("form[name=searchMenuform] input[name='adMenu.searchValue']").val() == "" ) {
			$( "#upMenuId" ).val( "" );
			$( "#locateMenuId" ).val( "" );
			$( "#locateMenuNm" ).val( "" );
			$( "#menuDepth" ).val( 0 );
		};

		$( "#searchMenuForm" ).submit();
	};

	var viewMenuList = function( selectedMenuId, menuDepth, menuNm ) {
		
		$( "#selectedMenuId" ).val( selectedMenuId );
		$( "#selectedMenuNm" ).val( menuNm );
		$( "#selectedMenuDepth" ).val( 0 ); // 메뉴 위치(locate) 값 때문에

		if(menuDepth == "4") {
			alert( "<gm:string value="jsp.adminmgr.menu.menuList.msg.none_result_lower"/>" );
			return;
		}
		
		$( "#searchMenuForm" ).submit();
	};

	var viewUpMenuList = function( selectedMenuId, menuDepth ) {
		
		if(menuDepth == "3") {
			$( "#selectedMenuId" ).val( "ROOT" );
		} else {
			$( "#selectedMenuId" ).val( selectedMenuId.substring(0, (selectedMenuId.length - 3)) );
		}
		$( "#selectedMenuDepth" ).val( menuDepth );
		
		if(menuDepth == "2") {
			alert( "<gm:string value="jsp.adminmgr.menu.menuList.msg.none_result_upper"/>" );
			return;
		}
		
		$( "#searchMenuForm" ).submit();
	};

	var processAdMenu = function() {
		
		if(!showValidate('processAdMenu', 'dialog', 'Check Input Value.')) {
			return;
		}
		
		if(confirm("<gm:string value='jsp.adminmgr.menu.menuList.msg.confirm_ins'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			if( $("#menuId").attr("disabled") == true) {
				$("#menuId").removeAttr("disabled");
				//alert( $("#menuId").val() );
			}
			if( $("#upMenuId").attr("disabled") == true) {
				$("#upMenuId").removeAttr("disabled");
				//alert( $("#menuId").val() );
			}
			$( "#searchValue" ).val( $("input[name='adMenu.searchValue']").val() );
			$( "#processAdMenu" ).submit();
		}
		
	};

	var updateMenuUseYn = function( menuId, useYn ) {

		$( "#updateMenuId" ).val( menuId );
		$( "#updateUseYn" ).val( useYn );

		if(confirm("<gm:string value='jsp.adminmgr.menu.menuList.msg.confirm_mod'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#searchMenuForm" ).attr("action", "<c:url value="/adminMgr/updateAdMenuUseYn.omp"/>");
			$( "#searchMenuForm" ).submit();
		}
		
	};

	var processForm = function(menuId, upMenuId, menuNm, pageUrl, menuPrior, menuDepth, subMenuYn, useYn) {

		$("#divRegist").css("display", "");
		$("#divReqRegist").css("display", "none");

		$( "#menuId" ).val( menuId );
		$( "#upMenuId" ).val( upMenuId );
		$( "#menuNm" ).val( menuNm );
		$( "#pageUrl" ).val( pageUrl );
		$( "#menuPrior" ).val( menuPrior );
		$( "#menuDepth" ).val( menuDepth );
		$( "#subMenuYn" ).val( subMenuYn );
		$( "#useYn" ).val( useYn );

		$( "#mode" ).val( "UPD" );

		$( "#menuId" ).attr( "disabled", true );
		$( "#upMenuId" ).attr( "disabled", true );

	};
	
	var clearRegistForm = function( locateUpMenuId ) {

		if( locateUpMenuId == "" ) {
			cancelRegistForm();
			return;
		}

		$( "#menuId" ).val("");
		//$( "#upMenuId" ).val("");
		$( "#menuNm" ).val("");
		$( "#pageUrl" ).val( "" );
		$( "#menuPrior" ).val( "" );
		$( "#menuDepth" ).val( "" );
		$( "#subMenuYn" ).val( "" );
		$( "#useYn" ).val( "Y" );

		$( "#menuId" ).attr( "disabled", false );
		//$( "#upMenuId" ).attr( "disabled", false );

	};
	
</script>

		<div id="location">
			Home &gt; 운영자관리 &gt; 메뉴관리 &gt; <strong>메뉴관리</strong> 
		</div>

		<h1 class="fl pr10">메뉴관리</h1>
		<p>메뉴를 조회/등록/삭제를 할 수 있습니다.</p>

		<div id="divRegist">
		<s:form action="processAdMenu" id="processAdMenu" theme="simple">
			<s:hidden id="menuDepth" name="adMenu.menuDepth" />
			<s:hidden id="subMenuYn" name="adMenu.subMenuYn" />
			<s:hidden id="searchValue" name="adMenu.searchValue" />
			<s:hidden id="mode" name="mode" />
		<table class="tabletype01">
			<colgroup>
				<col style="width:20%;"><col  style="width:30%;">
				<col style="width:20%;"><col  style="width:30%;">
			</colgroup>
			<tr>
				<th>메뉴아이디</th>
				<td class="text_l">
					<input id="menuId" type="text" name="adMenu.menuId" class="txt" style="width:95%;" maxlength="10" 
						v:required m:required="<gm:string value="jsp.adminmgr.menu.menuList.msg.insert_menuid"/>" />
				</td>
				<th>상위메뉴아이디</th>
				<td class="text_l">
					<input id="upMenuId" type="text" name="adMenu.upMenuId" class="txt" style="width:95%;" maxlength="7" />
				</td>
			</tr>
			<tr>
				<th>메뉴명</th>
				<td class="text_l">
					<input id="menuNm" type="text" name="adMenu.menuNm" class="txt" style="width:95%;" 
						v:required m:required="<gm:string value="jsp.adminmgr.menu.menuList.msg.insert_menunm"/>" />
				</td>
				<th>페이지URL</th>
				<td class="text_l">
					<input id="pageUrl" type="text" name="adMenu.pageUrl" class="txt" style="width:95%;" 
						v:required m:required="<gm:string value="jsp.adminmgr.menu.menuList.msg.insert_pageurl"/>" />
				</td>
			</tr>
			<tr>
				<th>메뉴순서</th>
				<td class="text_l">
					<input id="menuPrior" type="text" name="adMenu.menuPrior" class="txt" style="width:95%;" maxlength="4" 
						v:required m:required="<gm:string value="jsp.adminmgr.menu.menuList.msg.insert_prior"/>"
						v:mustnum m:mustnum="<gm:string value='jsp.adminmgr.menu.menuList.msg.check_num_prior'/>" />
				</td>
				<th>사용여부</th>
				<td>
					<select id="useYn" name="adMenu.useYn" style="width:95%;">
						<option value="Y" >사용</option>
						<option value="N" >미사용</option>
					</select>
				</td>
			</tr>
		</table>
		</s:form>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:cancelRegistForm();"><span>취소</span></a>
		<a class="btn" href="javascript:clearRegistForm('${locateMenuId}');"><span>초기화</span></a>
		<a class="btn" href="javascript:processAdMenu();"><span>저장</span></a></p>
		</div>

		<div id="divReqRegist">
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:viewRegistForm('${locateMenuId}');"><span>메뉴등록</span></a></p>
		</div>

		<s:form action="menuList" id="searchMenuForm" theme="simple">
		<s:hidden id="selectedMenuId" name="adMenu.upMenuId" />
		<s:hidden id="selectedMenuDepth" name="adMenu.menuDepth" />
		<s:hidden id="selectedMenuNm" name="adMenu.menuNm" />
		<s:hidden id="updateMenuId" name="adMenu.menuId" />
		<s:hidden id="updateUseYn" name="adMenu.useYn" />
		<s:hidden id="locateMenuId" name="locateMenuId" value="%{locateMenuId}" />
		<s:hidden id="locateMenuNm" name="locateMenuNm" value="%{locateMenuNm}" />

		<p class="text_r mt10 mb10" style="background:#ededed; padding:10px; border:1px solid #cdcdcd;">
			<label for="#">메뉴명</label>
			<input id="searchValue" type="text" name="adMenu.searchValue" class="txt" style="width:180px;  vertical-align:middle;" value="${adMenu.searchValue}" />
			<a class="btn_s" href="javascript:searchMenuList();"><span>검색</span></a>
		</p>

		<p class="fr mt10"><strong class="point2">${locateMenuNm}</strong></p>
		<table class="tabletype02 ">
			<colgroup>
				<col >
				<col >
				<col >
				<col >
				<col >
				<col >
				<col style="width:100px;" >
			</colgroup>
			<thead>
			<tr>
				<th>번호</th>
				<th>메뉴아이디</th>
				<th>상위메뉴아이디</th>
				<th>메뉴명</th>
				<th>페이지URL</th>
				<th>사용여부</th>
				<th>관리</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="adMenuList.size == 0">
			<tr><td colspan='7'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.adminmgr.menu.menuList.msg.none_result'/></span></td></tr>
			</s:if>
			<s:else>
			<s:iterator value="adMenuList" status="status">
			<tr>
				<td><s:property value="#status.index+1"/></td>
				<c:choose>
					<c:when test="${locateMenuNm eq 'UNKNOWN' }">
				<td>${menuId}</td>
				<td>${upMenuId}</td>
					</c:when>
					<c:otherwise>
				<td><a href="javascript:viewMenuList('${menuId}', '${menuDepth}', '${menuNm}');">${menuId}</a></td>
				<td><a href="javascript:viewUpMenuList('${upMenuId}', '${menuDepth}', '${menuNm}');">${upMenuId}</a></td>
					</c:otherwise>
				</c:choose>
				<td>${menuNm}</td>
				<td class="text_l">${pageUrl}</td>
				<td><c:if test="${useYn eq 'Y' }">사용</c:if><c:if test="${useYn eq 'N' }">미사용</c:if></td>
				<td><a class="btn_s" href="javascript:processForm('${menuId}','${upMenuId}','${menuNm}','${pageUrl}','${menuPrior}','${menuDepth}','${subMenuYn}','${useYn}');"><span>수정</span></a> 
				<c:if test="${useYn eq 'Y' }">
					<a class="btn_s" href="javascript:updateMenuUseYn('${menuId}', 'N');"><span>미사용</span></a></c:if>
				<c:if test="${useYn eq 'N' }">
					<a class="btn_s" href="javascript:updateMenuUseYn('${menuId}', 'Y');"><span>사용</span></a></c:if>
				</td>
			</tr>
			</s:iterator>
			</s:else>
			</tbody>
		</table>
		</s:form>

</body>
</html>
