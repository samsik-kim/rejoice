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
			setOrderSearchDateAdminPoC('7day', searchAnnounce.startDate, searchAnnounce.endDate);
		}

		// 전체 체크박스 이벤트
		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=dpAnoc.aAnocSeq]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

		// 디폴트 라디오 버튼 선택
		if ( '${anocCd}' == '' )	{
			$( "input[name=anocCd]" ).filter( 'input[value=]' ).attr( "checked", "checked" );
		}

	} );

	var searchAnnounceList = function() {
		if(showValidate('ckeckForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			$( "#searchAnnounce" ).attr("action", "<c:url value="/contents/announceList.omp"/>");
			$( "#searchAnnounce" ).submit();
		}
	};

	var goView	= function()	{
		$( "#selectedAnocSeq" ).val( "" );
		$( "#searchAnnounce" ).attr("action", "<c:url value="/contents/announceView.omp"/>");
		$( "#searchAnnounce" ).submit();
	};

	function notiDeleteAnnounceList() {
		//alert("<gm:string value='jsp.contents.announce.announceList.msg.none_select_anoc'/>");
	};

	function deleteAnnounceList() {
		
		if(showValidate('searchAnnounce', 'dialog', 'Check Input Value.')) {

			var selectedAnocSeq = makeAnocSeqStr();
			$( "#selectedAnocSeq" ).val( selectedAnocSeq );

			if(confirm("<gm:string value='jsp.contents.announce.announceList.msg.confirm_del'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$( "#searchAnnounce" ).attr("action", "<c:url value="/contents/deleteAnnounce.omp"/>");
				$( "#searchAnnounce" ).submit();
			}

		}

	};

	var makeAnocSeqStr = function() {
		var ids = "";
		$("input:checkbox[name=checkAnocSeq]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

	var announceView = function( anocSeq ) {
		$( "#anocSeq" ).val( anocSeq );
		$( "#searchAnnounce" ).attr("action", "<c:url value="/contents/announceView.omp"/>");
		$( "#searchAnnounce" ).submit();
	};

	function deleteAnnounce(anocSeq) {

		$( "#selectedAnocSeq" ).val( anocSeq );

		if(confirm("<gm:string value='jsp.contents.announce.announceList.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#searchAnnounce" ).attr("action", "<c:url value="/contents/deleteAnnounce.omp"/>");
			$( "#searchAnnounce" ).submit();
		}
	};

	var funcCheckForm = {
		checkdatevalue : function() {
			if( $("form[name=searchAnnounce] input[name=dpAnoc\\.startDate]").val() > $("form[name=searchAnnounce] input[name=dpAnoc\\.endDate]").val() ) {
				return	false;
			}
			return true;
		}
	};

</script>
	
		<div id="location">
			Home &gt; 컨텐츠관리 &gt; 서비스점검관리 &gt; <strong>서비스점검관리</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">서비스 점검관리</h1>
		<p>서비스 점검 시 출력되는 안내문구 관리를 할 수 있다.</p>
		<s:form id="ckeckForm" name="ckeckForm" theme="simple">
			<input type="hidden" id="checkDateValue" name="checkDateValue" 
				v:checkdatevalue m:checkdatevalue="<gm:string value="jsp.contents.announce.announceList.msg.check_date"/>" />
		</s:form>
		<s:form action="announceList" id="searchAnnounce" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="selectedAnocSeq" name="selectedAnocSeq" />
			<s:hidden id="anocSeq" name="dpAnoc.anocSeq" />
			<s:hidden name="dpAnoc.page.no" value="1"/>
		<table class="tabletype01">
			<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>작성기간</th>
				<td class="align_td">
					<!--
					<s:radio list="result.radioMap" name="dpAnoc.srchAnocCd" cssClass="ml05" /><br/>
					-->
					<input type="text" id="startDate" name="dpAnoc.startDate" value="<g:text value='${dpAnoc.startDate}' format='L####-##-##'/>" class="txt" style="width:80px;" readonly />
					&nbsp; ~ &nbsp;
					<input type='text' id="endDate" name="dpAnoc.endDate" value="<g:text value='${dpAnoc.endDate}' format='L####-##-##'/>" class="txt" style="width:80px;" readonly />
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('today', searchAnnounce.startDate, searchAnnounce.endDate);"><span>오늘</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('7day', searchAnnounce.startDate, searchAnnounce.endDate);"><strong>최근1주</strong></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('1month', searchAnnounce.startDate, searchAnnounce.endDate);"><span>최근1개월</span></a>
				</td>
				<td class="text_c" >
					<a class="btn" href="javascript:searchAnnounceList();"><strong>검색</strong></a>
				</td>
			</tr>
		</table>

		<table class="tabletype02 mt25" id="announceTable">
			<colgroup>
				<col style="width:3%;" >
				<col style="width:40px;" >
				<col >
				<col >
				<col >
				<col style="width:80px;" >
				<col style="width:70px;" >
			</colgroup>
			<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>번호</th>
				<th>제목</th>
				<th>공지기간</th>
				<th>작성일</th>
				<th>작성자</th>
				<th>삭제</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='7'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="dpAnocList.size == 0">
			<tr><td colspan="7" class="text_c"><gm:string value='jsp.contents.announce.announceList.msg.none_result'/></td></tr>
				</s:if>
				<s:else>
				<s:iterator value="dpAnocList" status="status">
			<tr>
				<td><input type="checkbox" name="checkAnocSeq" value="${anocSeq}" 
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.contents.announce.announceList.msg.none_select_anoc"/>" /></td>
				<td><s:property value="#status.index+1"/></td>
				<td class="text_l"><a href="javascript:announceView('${anocSeq}');"><strong>${anocTitl}</strong></a></td>
				<td>
					<g:text value="${anocStrtDttm}" format="L####-##-## ##:##"/>
					&nbsp; ~ &nbsp;
					<g:text value="${anocEndDttm}" format="L####-##-## ##:##"/>
				</td>
				<td><g:text value="${regDttm}" format="L####-##-##"/></td>
				<td><s:property value="regNm"/></td>
				<td><a class="btn_s" href="javascript:deleteAnnounce('${anocSeq}');"><span>삭제</span></a></td>
			</tr>
				</s:iterator>
				</s:else>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<s:if test="srchFlag != 'TRUE'">
		<p class="fl mt05">
		<a class="btn_s" href="javascript:notiDeleteAnnounceList();"><span>선택삭제</span></a>
		</p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:goView()"><span>등록</span></a></p>
		</s:if>
		<s:else>
			<s:if test="dpAnocList.size == 0">
		<p class="fl mt05">
		<a class="btn_s" href="javascript:notiDeleteAnnounceList();"><span>선택삭제</span></a>
		</p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:goView()"><span>등록</span></a></p>
			</s:if>
			<s:else>
		<p class="fl mt05">
		<a class="btn_s" href="javascript:deleteAnnounceList();"><span>선택삭제</span></a>
		</p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:goView()"><span>등록</span></a></p>
			</s:else>
		</s:else>
		<!-- paging -->
			<g:pageIndex item="${dpAnocList}"/>
		<!-- //paging -->

</body>
</html>