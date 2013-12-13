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
	
		// 전체 체크박스 이벤트
		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=checkPhoneModelCd]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );
	
		$("#searchType").change( function() {
		    $("#searchValue").val("");
		});

	} );

	var notiDeviceList = function() {
		//alert("<gm:string value='jsp.device.servicedevice.serviceDeviceList.msg.none_select' />");
	};

	var goView	= function()	{
		$( "#phoneModelCd" ).val( "" );
		$( "#searchDeviceForm" ).attr("action", "<c:url value="/device/serviceDeviceView.omp"/>");
		$( "#searchDeviceForm" ).submit();
	};

	function goPage(no) {
		$("form[name=searchDeviceForm] input[name=phoneInfo\\.page\\.no]").val(no);
		searchDeviceList();
	}

	var searchDeviceList = function() {
		$( "#searchDeviceForm" ).attr("action", "<c:url value="/device/serviceDeviceList.omp"/>");
		$( "#searchDeviceForm" ).submit();
	};

	var clearSrchCond = function( mode ) {
		$("#searchLcdSize").val("");
		$("#searchVmVer").val("");
		$("#searchSvcCd").val("");
		$("#searchType").val("byMgmtPhoneModelNm");
		$("#searchValue").val("");
	};

	var deviceView = function( phoneModelCd ) {
		$( "#phoneModelCd" ).val( phoneModelCd );
		$( "#searchDeviceForm" ).attr("action", "<c:url value="/device/serviceDeviceView.omp"/>");
		$( "#searchDeviceForm" ).submit();
	};

	var updatePhoneInfoDelYn = function() {
		
		if(showValidate('searchDeviceForm', 'dialog', 'check input value.')) {
			
			var selectedPhoneModelCd =  makePhoneModelCdStr();
			// alert("선택된 단말:" +selectedPhoneModelCd);
			$("#selectedPhoneModelCd").val(selectedPhoneModelCd);

			if(confirm("<gm:string value='jsp.device.servicedevice.serviceDeviceList.msg.confirm_del'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$( "#updateDeviceForm" ).submit();
			}

		}

	};

	var makePhoneModelCdStr = function() {
		var ids = "";
		$("input:checkbox[name=checkPhoneModelCd]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};


</script>

		<div id="location">
			Home &gt; 단말관리 &gt; 서비스단말관리 &gt; <strong>서비스단말리스트</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">서비스단말리스트</h1>
		<p>단말기 관리를 합니다.</p>

		<s:form action="updatePhoneInfoDelYn" id="updateDeviceForm" theme="simple">
			<s:hidden id="selectedPhoneModelCd" name="selectedPhoneModelCd" />
		</s:form>

		<s:form action="serviceDeviceList" id="searchDeviceForm" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="page.no" name="phoneInfo.page.no" value="%{phoneInfo.page.no}"/>
			<s:hidden id="phoneModelCd" name="phoneInfo.phoneModelCd" />
		<table class="tabletype01">
			<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>검색조건</th>
				<td class="align_td">					
					<label for="#">LCD크기</label>
						<select id="searchLcdSize" name="phoneInfo.searchLcdSize" style="width:85px;" >
                        	<option value="">전체</option>
		    				<gc:options group="PD0021" codeType="full" value="${phoneInfo.searchLcdSize}" />
		    			</select>

					<label for="#">지원OS</label>
						<select id="searchVmVer" name="phoneInfo.searchVmVer" style="width:85px;">
                        	<option value="">전체</option>
		    				<gc:options group="PD0091" codeType="full" filter="support" value="${phoneInfo.searchVmVer}" />
						</select>

					<label for="#">서비스 구분</label>
						<select id="searchSvcCd" name="phoneInfo.searchSvcCd" style="width:85px;">
                        	<option value="">전체</option>
		    				<gc:options group="US0052" codeType="full" value="${phoneInfo.searchSvcCd}" />
						</select>
				</td>
				<td rowspan="2" class="text_c" >
					<a class="btn" href="javascript:searchDeviceList();"><strong>검색</strong></a>
					<a class="btn" href="javascript:clearSrchCond();"><span>검색초기화</span></a>
				</td>
			</tr>
			<tr>
				<th>검색어</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="searchType" name="phoneInfo.searchType" style="width:104px;  vertical-align:middle;" />
					<input id="searchValue" type="text" name="phoneInfo.searchValue" class="txt" style="width:200px;  vertical-align:middle;" value="${phoneInfo.searchValue}" />
				</td>
			</tr>
		</table>

		<table class="tabletype02 mt25" id="serviceDeviceTable">
			<colgroup>
				<col style="width:3%;" >
				<col >
				<col >
				<col >
				<col >
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
				<th>이미지</th>
				<th>모델명</th>
				<th>단말명칭</th>
				<th>GDCD</th>
				<th>제조사</th>
				<th>LCD크기</th>
				<th>지원 OS</th>
				<th>서비스 구분</th>
				<th>사용 여부</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='11'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="phoneInfoList.size == 0">
			<tr><td colspan='11'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.device.servicedevice.serviceDeviceList.msg.none_result'/></span></td></tr>
				</s:if>
				<s:else>
				<c:set var="resultNum" value="${srchCnt - (phoneInfo.page.no-1)*10 }"/>
				<s:iterator value="phoneInfoList" status="status">
			<tr>
				<td><input type="checkbox" name="checkPhoneModelCd" value="${phoneModelCd}"
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceList.msg.none_select"/>" /></td>
				<td>${resultNum}</td>
				<td>
				<c:if test="${empty listImgPos}"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/temp02.jpg" alt="" /></c:if>
				<c:if test="${not empty listImgPos}">
					<img src="${CONF['omp.common.url.http-share.phone']}${listImgPos}" width="40" height="40" 
						onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" />
				</c:if>
				</td>
				<td><a href="javascript:deviceView('${phoneModelCd}');">${mgmtPhoneModelNm}</a></td>
				<td><a href="javascript:deviceView('${phoneModelCd}');">${modelNm}</a></td>
				<td>${gdcd}</td>
				<td>${makeCompNm}</td>
				<td>${lcdSizeNm}</td>
				<td>${vmVerNm}</td>
				<td>${svcCdNm}</td>
				<td><c:if test="${delYn eq 'N' }">사용</c:if><c:if test="${delYn eq 'Y' }">미사용</c:if></td>
			</tr>
				<c:set var="resultNum" value="${resultNum-1 }"/>
				</s:iterator>
				</s:else>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<s:if test="srchFlag != 'TRUE'">
		<p class="fl mt05"><a class="btn_s" href="javascript:notiDeviceList();"><span>선택삭제</span></a></p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:goView();"><span>등록</span></a></p>
		</s:if>
		<s:else>
			<s:if test="phoneInfoList.size == 0">
		<p class="fl mt05"><a class="btn_s" href="javascript:notiDeviceList();"><span>선택삭제</span></a></p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:goView();"><span>등록</span></a></p>
			</s:if>
			<s:else>
		<p class="fl mt05"><a class="btn_s" href="javascript:updatePhoneInfoDelYn();"><span>선택삭제</span></a></p>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:goView();"><span>등록</span></a></p>
			</s:else>
		</s:else>
		<!-- paging -->
			<g:pageIndex item="${phoneInfoList}"/>
		<!-- //paging -->

</body>
</html>
