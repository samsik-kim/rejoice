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
			setOrderSearchDateAdminPoC('1month', searchNotice.startDate, searchNotice.endDate);
		}

		$( "#btnSubmit" ).click( function()	{
			if(showValidate('ckeckForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
				$("form[name=searchNotice] input[name=notice\\.page\\.no]").val("1");
				$( "#searchNotice" ).submit();
			}
		} );

		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=notice.noticeId]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

		if ( '${notice.openYn}' == '' )	{
			$( "input[name=openYn]" ).filter( 'input[value=]' ).attr( "checked", "checked" );
		}

		$("#searchType").change( function() {
			$("#searchValue").val("");
		});

	} );

	var makeNoticeIds	= function()	{
		var ids = "";
		$("input:checkbox[name=checkNotiNo]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

	var notiNoticeList = function() {
		//alert("<gm:string value='jsp.community.notice.noticeList.msg.none_select_notice' />");
	};

	var selectNotice = function( noticeId ) {
		$("#noticeId").val( noticeId );
		$("#searchNotice").attr("action", "<c:url value="/community/noticeView.omp"/>");
		$("#searchNotice").submit();
	};

	var updateNotices	= function( mode )	{
		
		if(showValidate('searchNotice', 'dialog', 'Check Input Value.')) {

			var selectedNoticeId = makeNoticeIds();
			// alert("SELECTED NOTICD_ID : " + makeNoticeIds());
			$( "#mode" ).val( mode );
			$( "#selectedNoticeId" ).val( selectedNoticeId );
			
			if(confirm("<gm:string value='jsp.community.notice.noticeList.msg.confirm_mod'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$( "#searchNotice" ).attr("action", "<c:url value="/community/updateNoticeList.omp"/>");
				$( "#searchNotice" ).submit();
			}

		}
		
	};

	var clearSrchCond = function( mode ) {
		$(":input:radio[name=notice.openYn]").filter('input:radio[value=""]').attr("checked", "checked");
		setOrderSearchDateAdminPoC('1month', searchNotice.startDate, searchNotice.endDate);
		$("#searchType").val("title");
		$("#searchValue").val("");
	};

	function goPage(no) {
		$("form[name=searchNotice] input[name=notice\\.page\\.no]").val(no);
		$( "#searchNotice" ).submit();
	};

	var funcCheckForm = {
		checkdatevalue : function() {
			if( $("form[name=searchNotice] input[name=notice\\.startDate]").val() > $("form[name=searchNotice] input[name=notice\\.endDate]").val() ) {
				return	false;
			}
			return true;
		}
	};

</script>	

		<div id="location">
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_WEB">
			首頁  &gt; 客戶服務中心  &gt; 公告事項管理  &gt; <strong>WEB公告事項</strong> 
			</s:if>
			<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_SC">
			首頁  &gt; 客戶服務中心  &gt; 公告事項管理  &gt; <strong>SC公告事項</strong> 
			</s:elseif>
			<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_DEV">
			首頁  &gt; 客戶服務中心  &gt; 公告事項管理  &gt; <strong>開發商公告事項</strong> 
			</s:elseif>
		</div><!-- //location -->

		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_WEB">
		<h1 class="fl pr10">WEB公告事項</h1>
		</s:if>
		<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_SC">
		<h1 class="fl pr10">SC公告事項</h1>
		</s:elseif>
		<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_DEV">
		<h1 class="fl pr10">開發商公告事項</h1>
		</s:elseif>
		<p>可登載／管理公告事項。</p>

		<s:form id="ckeckForm" name="ckeckForm" theme="simple">
			<input type="hidden" id="checkDateValue" name="checkDateValue" 
				v:checkdatevalue m:checkdatevalue="<gm:string value="jsp.community.notice.noticeList.msg.check_date"/>" />
		</s:form>
		<s:form action="noticeList" id="searchNotice" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="pageNo" name="notice.page.no" value="%{notice.page.no}"/>
			<s:hidden id="ctgrCd" name="ctgrCd" value="%{ctgrCd}" />
			<s:hidden id="notice.ctgrCd" name="notice.ctgrCd" value="%{ctgrCd}" />
			<s:hidden id="mode" name="mode" />
			<s:hidden id="selectedNoticeId" name="notice.selectedNoticeId" />
			<s:hidden id="noticeId" name="notice.noticeId" value="%{notice.noticeId}" />
			<s:hidden name="gid" id="gid" value="%{notice.gid}" />
		<table class="tabletype01">
			<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>搜尋條件</th>
				<td class="line2_5">					
					<s:radio list="result.radioMap" name="notice.searchOpenYn" cssClass="ml05" /><br/>

					<label for="#">搜尋期間</label>
					<input type="text" id="startDate" name="notice.startDate" value="<g:text value='${notice.startDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly />
					&nbsp; ~ &nbsp;
					<input type='text' id="endDate" name="notice.endDate" value="<g:text value='${notice.endDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly/>
					<br />
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('1month', searchNotice.startDate, searchNotice.endDate);"><strong>最近一個月</strong></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('3month', searchNotice.startDate, searchNotice.endDate);"><span>最近三個月</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('6month', searchNotice.startDate, searchNotice.endDate);"><span>最近六個月</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('12month', searchNotice.startDate, searchNotice.endDate);"><span>最近一年</span></a>
				</td>
				<td rowspan="2" class="text_c" >
					<a class="btn" href="#" id="btnSubmit"><strong>搜尋</strong></a>
					<a class="btn" href="javascript:clearSrchCond();"><span>重新搜尋</span></a>
				</td>
			</tr>
			<tr>
				<th>搜尋關鍵字</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="searchType" name="notice.searchType" style="width:104px;" />
					<input id="searchValue" type="text" name="notice.searchValue" class="txt" style="width:200px;" value="${notice.searchValue}" />
				</td>
			</tr>
		</table>

		<table class="tabletype02 mt25">
			<colgroup>
				<col style="width:3%;" >
				<col style="width:70px;" >
				<col >
				<col >
				<col >
				<col >
			</colgroup>
			<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>序號</th>
				<th>標題</th>
				<th>設定日期</th>
				<th>設定者</th>
				<th>公開與否</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='7'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="noticeList.size == 0">
			<tr><td colspan='6'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.community.notice.noticeList.msg.none_result'/></span></td></tr>
				</s:if>
				<s:else>
				<c:set var="resultNum" value="${srchCnt - (notice.page.no-1)*10 }"/>
				<s:iterator value="noticeList" status="status">
			<tr>
				<td><input type="checkbox" name="checkNotiNo" value="${noticeId }"
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.community.notice.noticeList.msg.none_select_notice"/>" /></td>
				<td>${resultNum}</td>
			<td class="text_l"><a href="javascript:selectNotice('${noticeId}');"><g:html value="${title}" /></a></td>
			<td><g:text value="${insDttm}" format="L####-##-## ##:##:##"/></td>
			<td>${insNm}</td>
			<td><c:if test="${openYn eq 'Y' }">公開</c:if><c:if test="${openYn eq 'N' }">隱藏 </c:if></td>
			</tr>
				<c:set var="resultNum" value="${resultNum-1 }"/>
				</s:iterator>
				</s:else>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<s:if test="srchFlag != 'TRUE'">
		<p class="fl mt05">
			<a class="btn_s" href="javascript:notiNoticeList();"><span>刪除</span></a>
			<a class="btn_s" href="javascript:notiNoticeList();"><span>公開</span></a>
			<a class="btn_s" href="javascript:notiNoticeList();"><span>隱藏</span></a>
		</p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:selectNotice('0')"><span>新增</span></a></p>
		</s:if>
		<s:else>
			<s:if test="noticeList.size == 0">
		<p class="fl mt05">
			<a class="btn_s" href="javascript:notiNoticeList();"><span>刪除</span></a>
			<a class="btn_s" href="javascript:notiNoticeList();"><span>公開</span></a>
			<a class="btn_s" href="javascript:notiNoticeList();"><span>隱藏</span></a>
		</p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:selectNotice('0')"><span>新增</span></a></p>
			</s:if>
			<s:else>
		<p class="fl mt05">
			<a class="btn_s" href="javascript:updateNotices('D');"><span>刪除</span></a>
			<a class="btn_s" href="javascript:updateNotices('Y');"><span>公開</span></a>
			<a class="btn_s" href="javascript:updateNotices('N');"><span>隱藏</span></a>
		</p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:selectNotice('0')"><span>新增</span></a></p>
			</s:else>
		</s:else>
		<!-- paging -->
		<div class="paging_area">
			<g:pageIndex item="${noticeList}"/>
		</div>
		<!-- //paging -->

</body>
</html>
