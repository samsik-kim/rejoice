<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
<script type="text/javascript">
	
	function init(){
		$("#platCtgr option:first").attr("selected", "true");	
		//$("#category").find('option').remove();
		$("#category option:first").attr("selected", "true");		
		$("#keyword").val("");	
	}
	
	function goPage(no) {
		$("form[name=verifyListForm] input[name=verify\\.page\\.no]").val(no);
		selectVerifyList();
	}
	
	
	function selectVerifyList(){
		var temp = document.getElementById("tempCtgr").value;
		var temp2 = document.getElementById("category").value;
		temp = temp2;
		$("#verifyListForm").attr("target", "_self");
		$("#verifyListForm").attr("action", "verifyCase.omp");
		$("#verifyListForm").submit();
	}
	
	function popupCategory() {
		width = 810;
		height = 460;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		window.open('','popup', 'left=' + x + ',top=' + y + ',width=' + width + ',height=' + height + ',scrollbars=no,status=yes,menubar=no');
		document.verifyListForm.target = 'popup';
		document.verifyListForm.action = 'popCategory.omp';
		document.verifyListForm.submit();
	}
	
	function popupCase() {
		width = 810;
		height = 370;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		window.open('','popup', 'left=' + x + ',top=' + y + ',width=' + width + ',height=' + height + ',scrollbars=no,status=yes,menubar=no');
		document.verifyListForm.target = 'popup';
		document.verifyListForm.action = 'popCase.omp';
		document.verifyListForm.submit();
	}
</script>
</head>
<body>
<s:form id="verifyListForm" name="verifyListForm" theme="simple" method="post">
	<s:hidden id="tempCtgr" name="tempCtgr"/>
	<s:hidden id="fileName" name="fileName"/>
	<s:hidden id="filePathName" name="filePathName"/>
	<s:hidden id="secondDepthCategory" name="secondDepthCategory"/>
	<input type="hidden" name="verify.page.no" value="1"/>
	<div id="location">
	首頁 &gt; 產品管理中心 &gt; 審核管理 &gt <strong>測試項目管理</strong> 
	</div>
	<!-- //location -->

	<h1 class="fl pr10">測試項目管理</h1>
	<p>可對測試項目進行管理。</p>

	<table class="tabletype01">
		<colgroup>
			<col style="width:100px;">
			<col >
			<col style="width:125px">
		</colgroup>
		<tr>
			<th>搜尋條件</th>
			<td class="line2_5">
				<label for="#">平台</label>
				
				<select id="platCtgr" name="verify.platCtgr" style="width: 104px;" >
						<option value="">全部</option>
						<gc:options group="PD0056" codeType="full" excludeFilter="dev" value="${verify.platCtgr}"/>
				</select>
				<label for="#">類別</label>
				
				<s:if test="ctgrCnt == null">
					<select id="category" name="verify.ctCtgrSeq" style="width: 104px;">
						<option value="">全部 </option>
					</select>
				</s:if>
				<s:else>
					<s:select id="category" name="verify.ctCtgSeq" theme="simple" list="categoryNameList" listKey="ctCtgSeq" listValue="titleNm" value="%{verify.ctCtgSeq}" headerKey="" headerValue="全部  " style="width:104px;"/>
				</s:else>
				<br />
				<label for="#">審核項目名稱</label>
				<input id="keyword" name="verify.keyword" type="text" class="txt" onfocus="this.value=''" style="width:80%;" value="${verify.keyword}" />
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" href="javascript:selectVerifyList();"><strong>搜尋</strong></a>
				<a class="btn" href="javascript:init()"><span>重新搜尋</span></a>
			</td>
		</tr>
	</table>
	<table class="tabletype02 mt25">
		<colgroup>
			<col style="width:40px;" >
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
		</colgroup>
		<thead>
			<tr>
				<th>序號</th>
				<th>平台</th>
				<th>類別</th>
				<th>使用與否</th>
				<th>審核項目名稱</th>
				<th>說明</th>
				<th>程序</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${searchCnt > 0}">
					<c:forEach items="${verifyList}" var="item" varStatus="cnt">
						<tr>
							<td style="text-align:center;"><g:html value="${item.totalCount - item.rnum + 1}" /></td>
							<td style="text-align:center;"><g:html value="${item.vmType }" /></td>
							<td style="text-align:center;"><g:html value="${item.ctCtgNm }" /></td>
							<td style="text-align:center;"><g:html value="${item.useYn}" /></td>
							<td style="text-align:center;"><g:html value="${item.titleNm}" /></td>
							<td style="text-align:center;"><g:html value="${item.explain}" /></td>
							<td style="text-align:center;"><a class="btn_s" href="<c:url value="/fileSupport/fileDown.omp"><c:param name="bnsType" value="admin.path.share.testcase"/><c:param name="filePath" value="${item.stepFile }"/><c:param name="fileName" value="${item.stepFileNm }"/></c:url>"><strong>文件</strong></a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="8" class="text_c"><gm:string value="jsp.certifymgr.common.msg.list_result" /></td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<p class="btn_wrap text_r mt25">
		<a class="btn" href="javascript:popupCategory()">
			<span>新增/變更</span></a> 
		<a class="btn" href="javascript:popupCase()">
			<span>新增審核項目</span></a>
	</p>
	<!-- paging -->
	<div align="center">
		<g:pageIndex item="${verifyList}"/>
	</div>
	<!-- //paging -->
</s:form>	
</body>