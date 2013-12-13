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

		$("form[name=announceForm] input[name='dpAnoc.anocStrtDd']").datepicker();
		$("form[name=announceForm] input[name='dpAnoc.anocEndDd']").datepicker();

		if ( '${dpAnoc.anocCd}' == '' )	{
			$( "input[name='dpAnoc.anocCd']" ).filter( 'input[value=DP005302]' ).attr( "checked", "checked" );
		}

		if( $("#anocStrtDd").val() == "" )	{
			setOrderSearchDateAdminPoC('today', announceForm.anocStrtDd, announceForm.anocEndDd);
		}

	} );

	function funcProcessAnnounce() {
		
		if(showValidate('announceForm', 'dialog', 'Check Input Value.')) {
			
			if(confirm("<gm:string value='jsp.contents.announce.announceView.msg.confirm_ins'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$("#announceForm").submit();
			}

		}
	
	};

	function deleteAnnounce(anocSeq) {
		
		$( "#selectedAnocSeq" ).val( anocSeq );
		
		if(confirm("<gm:string value='jsp.contents.announce.announceView.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#deleteAnnounceForm" ).submit();
		}
	};

	var funcAnnounceList = function()	{
		$( "#announceForm" ).attr("action", "<c:url value="/contents/announceList.omp"/>");
		$( "#announceForm" ).submit();
	};

</script>

		<div id="location">
			首頁  &gt; 內容管理中心  &gt; 系統維修管理  &gt; <strong>系統維修管理 </strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">系統維修管理 </h1>
		<p>可管理系統維修管理時向會員提供之公告.</p>
		<s:form action="deleteAnnounce" id="deleteAnnounceForm" theme="simple">
			<s:hidden id="startDate" name="dpAnoc.startDate" value="%{dpAnoc.startDate}"/>
			<s:hidden id="endDate" name="dpAnoc.endDate" value="%{dpAnoc.endDate}"/>
			<s:hidden id="pageNo" name="dpAnoc.page.no" value="%{dpAnoc.page.no}"/>
			<s:hidden id="selectedAnocSeq" name="selectedAnocSeq" value="%{dpAnoc.anocSeq}" />
		</s:form>
		<s:form action="insertAnnounce" id="announceForm" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="startDate" name="dpAnoc.startDate" value="%{dpAnoc.startDate}"/>
			<s:hidden id="endDate" name="dpAnoc.endDate" value="%{dpAnoc.endDate}"/>
			<s:hidden id="pageNo" name="dpAnoc.page.no" value="%{dpAnoc.page.no}"/>
			<s:hidden id="anocSeq" name="dpAnoc.anocSeq" />
			<s:hidden id="anocStrtDttm" name="dpAnoc.anocStrtDttm" />
			<s:hidden id="anocEndDttm" name="dpAnoc.anocEndDttm" />
			<s:hidden id="anocCd" name="dpAnoc.anocCd" value="DP005301" />
		<table class="tabletype02">
			<colgroup>
				<col style="width:20%;">
				<col style="width:80%;">
			</colgroup>
			<tbody>
			<tr>
				<th>標題</th>
				<td class="text_l">
					<input id="anocTitl" type="text" name="dpAnoc.anocTitl" class="txt" style="width:80%;" 
						v:required m:required="<gm:string value="jsp.contents.announce.announceView.msg.check_title"/>" value="<s:property value='%{dpAnoc.anocTitl}'/>" />
				</td>
			</tr>
			<!--
			<tr>
				<th>公告設定</th>
				<td colspan="3" class="text_l align_td">
					<input type="radio" name="dpAnoc.anocCd" class="ml05" value="DP005302" <c:if test="${dpAnoc.anocCd eq 'DP005302' }"> checked </c:if> />점검 
					<input type="radio" name="dpAnoc.anocCd" class="ml05" value="DP005301" <c:if test="${dpAnoc.anocCd eq 'DP005301' }"> checked </c:if> />알림
				</td>
			</tr>
			-->
			<tr>
				<th>公告期間</th>
				<td class="text_l align_td">
					<input type="text" id="anocStrtDd" name="dpAnoc.anocStrtDd" value="<g:text value='${dpAnoc.anocStrtDd}' format='L####-##-##'/>" class="txt" style="width:80px;" readonly
						v:scompare="le,@{dpAnoc.anocEndDd}" m:scompare="<gm:string value="jsp.contents.announce.announceView.msg.check_date"/>" >
					<select id="anocStrtHh" name="dpAnoc.anocStrtHh" v:required m:required="<gm:string value="jsp.contents.announce.announceView.msg.check_strthh"/>" >
                       	<option value="">點</option>
						<option value="00" <c:if test="${dpAnoc.anocStrtHh eq '00' }">selected</c:if>>00</option>
						<option value="01" <c:if test="${dpAnoc.anocStrtHh eq '01' }">selected</c:if>>01</option>
						<option value="02" <c:if test="${dpAnoc.anocStrtHh eq '02' }">selected</c:if>>02</option>
						<option value="03" <c:if test="${dpAnoc.anocStrtHh eq '03' }">selected</c:if>>03</option>
						<option value="04" <c:if test="${dpAnoc.anocStrtHh eq '04' }">selected</c:if>>04</option>
						<option value="05" <c:if test="${dpAnoc.anocStrtHh eq '05' }">selected</c:if>>05</option>
						<option value="06" <c:if test="${dpAnoc.anocStrtHh eq '06' }">selected</c:if>>06</option>
						<option value="07" <c:if test="${dpAnoc.anocStrtHh eq '07' }">selected</c:if>>07</option>
						<option value="08" <c:if test="${dpAnoc.anocStrtHh eq '08' }">selected</c:if>>08</option>
						<option value="09" <c:if test="${dpAnoc.anocStrtHh eq '09' }">selected</c:if>>09</option>
						<option value="10" <c:if test="${dpAnoc.anocStrtHh eq '10' }">selected</c:if>>10</option>
						<option value="11" <c:if test="${dpAnoc.anocStrtHh eq '11' }">selected</c:if>>11</option>
						<option value="12" <c:if test="${dpAnoc.anocStrtHh eq '12' }">selected</c:if>>12</option>
						<option value="13" <c:if test="${dpAnoc.anocStrtHh eq '13' }">selected</c:if>>13</option>
						<option value="14" <c:if test="${dpAnoc.anocStrtHh eq '14' }">selected</c:if>>14</option>
						<option value="15" <c:if test="${dpAnoc.anocStrtHh eq '15' }">selected</c:if>>15</option>
						<option value="16" <c:if test="${dpAnoc.anocStrtHh eq '16' }">selected</c:if>>16</option>
						<option value="17" <c:if test="${dpAnoc.anocStrtHh eq '17' }">selected</c:if>>17</option>
						<option value="18" <c:if test="${dpAnoc.anocStrtHh eq '18' }">selected</c:if>>18</option>
						<option value="19" <c:if test="${dpAnoc.anocStrtHh eq '19' }">selected</c:if>>19</option>
						<option value="20" <c:if test="${dpAnoc.anocStrtHh eq '20' }">selected</c:if>>20</option>
						<option value="21" <c:if test="${dpAnoc.anocStrtHh eq '21' }">selected</c:if>>21</option>
						<option value="22" <c:if test="${dpAnoc.anocStrtHh eq '22' }">selected</c:if>>22</option>
						<option value="23" <c:if test="${dpAnoc.anocStrtHh eq '23' }">selected</c:if>>23</option>
                       </select>
					<select id="anocStrtMm" name="dpAnoc.anocStrtMm" v:required m:required="<gm:string value="jsp.contents.announce.announceView.msg.check_strtmm"/>" >
                       	<option value="">分</option>
						<option value="00" <c:if test="${dpAnoc.anocStrtMm eq '00' }">selected</c:if>>00</option>
						<option value="05" <c:if test="${dpAnoc.anocStrtMm eq '05' }">selected</c:if>>05</option>
						<option value="10" <c:if test="${dpAnoc.anocStrtMm eq '10' }">selected</c:if>>10</option>
						<option value="15" <c:if test="${dpAnoc.anocStrtMm eq '15' }">selected</c:if>>15</option>
						<option value="20" <c:if test="${dpAnoc.anocStrtMm eq '20' }">selected</c:if>>20</option>
						<option value="25" <c:if test="${dpAnoc.anocStrtMm eq '25' }">selected</c:if>>25</option>
						<option value="30" <c:if test="${dpAnoc.anocStrtMm eq '30' }">selected</c:if>>30</option>
						<option value="35" <c:if test="${dpAnoc.anocStrtMm eq '35' }">selected</c:if>>35</option>
						<option value="40" <c:if test="${dpAnoc.anocStrtMm eq '40' }">selected</c:if>>40</option>
						<option value="45" <c:if test="${dpAnoc.anocStrtMm eq '45' }">selected</c:if>>45</option>
						<option value="50" <c:if test="${dpAnoc.anocStrtMm eq '50' }">selected</c:if>>50</option>
						<option value="55" <c:if test="${dpAnoc.anocStrtMm eq '55' }">selected</c:if>>55</option>
					</select>
					&nbsp; ~ &nbsp; 
					<input type="text" id="anocEndDd" name="dpAnoc.anocEndDd" value="<g:text value='${dpAnoc.anocEndDd}' format='L####-##-##'/>" class="txt" style="width:80px;" readonly/>
					<select id="anocEndHh" name="dpAnoc.anocEndHh" v:required m:required="<gm:string value="jsp.contents.announce.announceView.msg.check_endhh"/>" >
                       	<option value="">點</option>
						<option value="00" <c:if test="${dpAnoc.anocEndHh eq '00' }">selected</c:if>>00</option>
						<option value="01" <c:if test="${dpAnoc.anocEndHh eq '01' }">selected</c:if>>01</option>
						<option value="02" <c:if test="${dpAnoc.anocEndHh eq '02' }">selected</c:if>>02</option>
						<option value="03" <c:if test="${dpAnoc.anocEndHh eq '03' }">selected</c:if>>03</option>
						<option value="04" <c:if test="${dpAnoc.anocEndHh eq '04' }">selected</c:if>>04</option>
						<option value="05" <c:if test="${dpAnoc.anocEndHh eq '05' }">selected</c:if>>05</option>
						<option value="06" <c:if test="${dpAnoc.anocEndHh eq '06' }">selected</c:if>>06</option>
						<option value="07" <c:if test="${dpAnoc.anocEndHh eq '07' }">selected</c:if>>07</option>
						<option value="08" <c:if test="${dpAnoc.anocEndHh eq '08' }">selected</c:if>>08</option>
						<option value="09" <c:if test="${dpAnoc.anocEndHh eq '09' }">selected</c:if>>09</option>
						<option value="10" <c:if test="${dpAnoc.anocEndHh eq '10' }">selected</c:if>>10</option>
						<option value="11" <c:if test="${dpAnoc.anocEndHh eq '11' }">selected</c:if>>11</option>
						<option value="12" <c:if test="${dpAnoc.anocEndHh eq '12' }">selected</c:if>>12</option>
						<option value="13" <c:if test="${dpAnoc.anocEndHh eq '13' }">selected</c:if>>13</option>
						<option value="14" <c:if test="${dpAnoc.anocEndHh eq '14' }">selected</c:if>>14</option>
						<option value="15" <c:if test="${dpAnoc.anocEndHh eq '15' }">selected</c:if>>15</option>
						<option value="16" <c:if test="${dpAnoc.anocEndHh eq '16' }">selected</c:if>>16</option>
						<option value="17" <c:if test="${dpAnoc.anocEndHh eq '17' }">selected</c:if>>17</option>
						<option value="18" <c:if test="${dpAnoc.anocEndHh eq '18' }">selected</c:if>>18</option>
						<option value="19" <c:if test="${dpAnoc.anocEndHh eq '19' }">selected</c:if>>19</option>
						<option value="20" <c:if test="${dpAnoc.anocEndHh eq '20' }">selected</c:if>>20</option>
						<option value="21" <c:if test="${dpAnoc.anocEndHh eq '21' }">selected</c:if>>21</option>
						<option value="22" <c:if test="${dpAnoc.anocEndHh eq '22' }">selected</c:if>>22</option>
						<option value="23" <c:if test="${dpAnoc.anocEndHh eq '23' }">selected</c:if>>23</option>
                       </select>
					<select id="anocEndMm" name="dpAnoc.anocEndMm" v:required m:required="<gm:string value="jsp.contents.announce.announceView.msg.check_endmm"/>" >
                       	<option value="">分</option>
						<option value="00" <c:if test="${dpAnoc.anocEndMm eq '00' }">selected</c:if>>00</option>
						<option value="05" <c:if test="${dpAnoc.anocEndMm eq '05' }">selected</c:if>>05</option>
						<option value="10" <c:if test="${dpAnoc.anocEndMm eq '10' }">selected</c:if>>10</option>
						<option value="15" <c:if test="${dpAnoc.anocEndMm eq '15' }">selected</c:if>>15</option>
						<option value="20" <c:if test="${dpAnoc.anocEndMm eq '20' }">selected</c:if>>20</option>
						<option value="25" <c:if test="${dpAnoc.anocEndMm eq '25' }">selected</c:if>>25</option>
						<option value="30" <c:if test="${dpAnoc.anocEndMm eq '30' }">selected</c:if>>30</option>
						<option value="35" <c:if test="${dpAnoc.anocEndMm eq '35' }">selected</c:if>>35</option>
						<option value="40" <c:if test="${dpAnoc.anocEndMm eq '40' }">selected</c:if>>40</option>
						<option value="45" <c:if test="${dpAnoc.anocEndMm eq '45' }">selected</c:if>>45</option>
						<option value="50" <c:if test="${dpAnoc.anocEndMm eq '50' }">selected</c:if>>50</option>
						<option value="55" <c:if test="${dpAnoc.anocEndMm eq '55' }">selected</c:if>>55</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>內容</th>
				<td class="text_l">
					<textarea class="text_area" id="anocCont" name="dpAnoc.anocCont" 
						v:required m:required="<gm:string value="jsp.contents.announce.announceView.msg.check_dscr"/>"
						v:text8_limit="4000" m:text8_limit="<gm:string value="jsp.contents.announce.announceView.msg.check_len_dscr"/>">${dpAnoc.anocCont}</textarea></td>
			</tr>
			</tbody>
		</table>
		</s:form>
		<s:if test='dpAnoc.anocSeq != 0'>
		<p class="fl mt25"><a class="btn" href="javascript:deleteAnnounce('${dpAnoc.anocSeq}');"><span>刪除</span></a></p>
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:funcProcessAnnounce()"><span>儲存</span></a>
			<a class="btn" href="javascript:funcAnnounceList();"><span>目錄</span></a></p>
		</s:if>
		<s:else>
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:funcProcessAnnounce()"><span>儲存</span></a>
			<a class="btn" href="javascript:funcAnnounceList();"><span>目錄</span></a></p>
		</s:else>

</body>
</html>
