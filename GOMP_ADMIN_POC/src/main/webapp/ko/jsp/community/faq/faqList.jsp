<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld" %>
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

		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		if( $("#startDate").val() == "" )	{
			setOrderSearchDateAdminPoC('1month', faqForm.startDate, faqForm.endDate);
		}

		// 전체 체크박스 이벤트
		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=faq.faqId]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

		// 디폴트 라디오 버튼 선택
		if ( '${faq.openYn}' == '' )	{
			$( "input[name=openYn]" ).filter( 'input[value=]' ).attr( "checked", "checked" );
		}

		$("#searchType").change( function() {
		    $("#searchValue").val("");
		});

	} );

	// 선택된 ID로 string 만들기
	var makeFaqIds = function() {
		var ids = "";
		$("input:checkbox[name=checkFaqId]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

	var notiFaqList = function() {
		//alert("<gm:string value='jsp.community.faq.faqList.msg.none_select_faq' />");
	};

	var selectFaq = function( faqId ) {
		$("#faqId").val( faqId );
		$("#faqForm").submit();
	};

	// 여러개의 공지 상태변경
	var updateFaqList = function( mode )	{
		
		if(showValidate('faqForm', 'dialog', 'Check Input Value.')) {
			
			var selectedFaqId = makeFaqIds();
			//alert("선택된 글:" + selectedFaqId);
			
			$( "#mode" ).val( mode );
			$( "#selectedFaqId" ).val( selectedFaqId );

			if(confirm("<gm:string value='jsp.community.faq.faqList.msg.confirm_proc'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$( "#faqForm" ).attr("action", "<c:url value="/community/updateFaqList.omp"/>");
				$( "#faqForm" ).submit();
			}

		}

	};

	var searchFaqList = function() {
		
		if(showValidate('ckeckForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			$("form[name=faqForm] input[name=faq\\.page\\.no]").val("1");
			$( "#faqForm" ).attr("action", "<c:url value="/community/faqList.omp"/>");
			$( "#faqForm" ).submit();
		}

	};

	var makeSortFaqIds = function() {
		var ids = "";
		$("#checkFaqId").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

	var clearSrchCond = function( mode ) {
		$(":input:radio[name=faq.openYn]").filter('input:radio[value=""]').attr("checked", "checked");
		setOrderSearchDateAdminPoC('1month', faqForm.startDate, faqForm.endDate);
		$("#searchType").val("category");
		$("#searchValue").val("");
	};

	function funcFaqListUpdateSort() {
		if(confirm("<gm:string value='jsp.community.faq.faqList.msg.confirm_mod'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#faqForm" ).attr("action", "<c:url value="/community/updateFaqSortList.omp"/>");
			$( "#faqForm" ).submit();
		}
	};

	function goPage(no) {
		$("form[name=faqForm] input[name=faq\\.page\\.no]").val(no);
		$( "#faqForm" ).attr("action", "<c:url value="/community/faqList.omp"/>");
		$( "#faqForm" ).submit();
	};

	var funcCheckForm = {
		checkvalue : function() {
			if( $("form[name=faqForm] input[name=faq\\.searchValue]").val() == "" ) {
				return false;
			}
			return true;
		},
		checkdatevalue : function() {
			if( $("form[name=faqForm] input[name=faq\\.startDate]").val() > $("form[name=faqForm] input[name=faq\\.endDate]").val() ) {
				return	false;
			}
			return true;
		}
	};

</script>	

		<div id="location">
			Home &gt; 고객지원 &gt; FAQ관리 &gt; <strong>
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_SC">
			SC FAQ
			</s:if>
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_DEV">
			개발자 FAQ
			</s:if>
			</strong> 
		</div><!-- //location -->

		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_SC">
		<h1 class="fl pr10">SC FAQ</h1>
		<p>SC 관련 FAQ 관리를 할 수 있습니다.</p>
		</s:if>
		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_DEV">
		<h1 class="fl pr10">개발자 FAQ</h1>
		<p>개발자 관련 FAQ 관리를 할 수 있습니다.</p>
		</s:if>

		<s:form id="ckeckForm" name="ckeckForm" theme="simple">
			<!--
			<input type="hidden" id="checkSearchValue" name="checkSearchValue" 
				v:checkvalue m:checkvalue="<gm:string value="jsp.community.faq.faqList.msg.check_search"/>" />
			-->
			<input type="hidden" id="checkDateValue" name="checkDateValue" 
				v:checkdatevalue m:checkdatevalue="<gm:string value="jsp.community.faq.faqList.msg.check_date"/>" />
		</s:form>
		<s:form action="faqView" id="faqForm" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden name="faq.page.no" value="%{faq.page.no}"/>
			<s:hidden id="ctgrCd" name="ctgrCd" value="%{ctgrCd }"/>
			<s:hidden id="faqId" name="faq.faqId" />
			<s:hidden id="selectedFaqId" name="selectedFaqId" value="%{selectedFaqId }"/>
			<s:hidden id="mode" name="mode" />
		<table class="tabletype01">
			<colgroup><col><col ><col style="width:100px"></colgroup>
			<tr>
				<th>노출여부</th>
				<td class="align_td">				
					<s:radio list="result.radioMap" name="faq.searchOpenYn" cssClass="ml05" />
				</td>
				<td rowspan="3" class="text_c" >
					<a class="btn" href="javascript:searchFaqList();"><strong>검색</strong></a>
					<a class="btn" href="javascript:clearSrchCond();"><span>검색초기화</span></a>
				</td>
			</tr>
			<tr>
				<th>FAQ검색</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="searchType" name="faq.searchType" style="width:104px;" />
					<input id="searchValue" type="text" name="faq.searchValue" class="txt" value="${faq.searchValue}" style="width:200px;" />
				</td>
			</tr>
			<tr>
				<th>검색기간</th>
				<td class="align_td">
					<label for="#">검색기간</label>
					<input type="text" id="startDate" name="faq.startDate" value="<g:text value='${faq.startDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly/>
					&nbsp; ~ &nbsp;
					<input type='text' id="endDate" name="faq.endDate" value="<g:text value='${faq.endDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly/>
					&nbsp;
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('1month', faqForm.startDate, faqForm.endDate);"><strong>최근1개월</strong></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('3month', faqForm.startDate, faqForm.endDate);"><span>최근3개월</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('6month', faqForm.startDate, faqForm.endDate);"><span>최근6개월</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('12month', faqForm.startDate, faqForm.endDate);"><span>최근1년</span></a>
				</td>
			</tr>
		</table>

		<table class="tabletype02 mt25">
			<colgroup>
				<col style="width:3%;" >
				<col >
				<col >
				<col >
				<col >
				<col >
				<col >
			</colgroup>
			<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>번호</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>조회수</th>
				<th>노출</th>
				<th>순서변경</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='7'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="faqList.size == 0">
			<tr><td colspan='7'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.community.faq.categoryList.msg.none_result'/></span></td></tr>
				</s:if>
				<s:else>
				<s:iterator value="faqList" status="status">
			<tr>
				<td><input type="checkbox" id="checkFaqId" name="checkFaqId" value="${faqId}"
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.community.faq.faqList.msg.none_select_faq"/>" /></td>
				<td><s:property value="#status.index+1"/></td>
				<td>${ctgrNm}</td>
				<td class="text_l">
					<a href="javascript:selectFaq('${faqId}');">${title}</a>
				</td>
				<td>${hit}</td>
				<td><c:if test="${openYn eq 'Y' }">노출</c:if><c:if test="${openYn eq 'N' }">숨김</c:if></td>
				<td><input id="sort" type="text" name="faq.sort" class="txt" style="width:40px;" value="${sort}"/>
				<input type="hidden" name="faq.sortFaqId" value="${faqId}" /></td>
			</tr>
				</s:iterator>
				</s:else>
			</s:else>
			</tbody>
		</table>
		
		<s:if test="srchFlag != 'TRUE'">
		<p class="fl mt05">
			<a class="btn_s" href="javascript:notiFaqList();"><span>삭제</span></a>
			<a class="btn_s" href="javascript:notiFaqList();"><span>노출</span></a>
			<a class="btn_s" href="javascript:notiFaqList();"><span>숨김</span></a>
		</p>
		<p class="btn_wrap text_r mt05">
			<a class="btn" href="javascript:notiFaqList();"><span>순서적용</span></a>
			<a class="btn" href="javascript:selectFaq('0')"><span>등록</span></a>
		</p>
		</s:if>
		<s:else>
			<s:if test="faqList.size == 0">
		<p class="fl mt05">
			<a class="btn_s" href="javascript:notiFaqList();"><span>삭제</span></a>
			<a class="btn_s" href="javascript:notiFaqList();"><span>노출</span></a>
			<a class="btn_s" href="javascript:notiFaqList();"><span>숨김</span></a>
		</p>
		<p class="btn_wrap text_r mt05">
			<a class="btn" href="javascript:notiFaqList();"><span>순서적용</span></a>
			<a class="btn" href="javascript:selectFaq('0')"><span>등록</span></a>
		</p>
			</s:if>
			<s:else>
		<p class="fl mt05">
			<a class="btn_s" href="javascript:updateFaqList('D');"><span>삭제</span></a>
			<a class="btn_s" href="javascript:updateFaqList('Y');"><span>노출</span></a>
			<a class="btn_s" href="javascript:updateFaqList('N');"><span>숨김</span></a>
		</p>
		<p class="btn_wrap text_r mt05">
			<a class="btn" href="javascript:funcFaqListUpdateSort();"><span>순서적용</span></a>
			<a class="btn" href="javascript:selectFaq('0')"><span>등록</span></a>
		</p>
			</s:else>
		</s:else>
		<!-- paging -->
			<g:pageIndex item="${faqList}"/>
		<!-- //paging -->
		</s:form>

</body>
</html>

