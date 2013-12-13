<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<title>검색어 등록</title>
<script type="text/javascript">

function insertSearch(){
	if(showValidate('searchForm', 'default', '')){
		var options={ 
					 success:       responseIns,
			         url:       env.contextPath+"/search/insertSearch.omp", 
			         type:      "post"  
				   }; 
		$('#searchForm').ajaxSubmit(options);
	}
}

function responseIns(){
	alert("<gm:string value='jsp.search.search_insert.msg.savere'/>");
	closePop();
}

function closePop(){
	window.opener.$("#searchListForm").attr("target", "_self");
	window.opener.document.searchListForm.action="searchList.omp";
	window.opener.document.searchListForm.submit();
	window.close();
}
</script>
</head>
<body>
<s:form id="searchForm" name="searchForm" theme="simple" method="post">
<input type="hidden" id="pageNo" name="searchSub.page.no" value="${searchSub.page.no}"/>
<div id="popup_area_440">
		<h1>검색어 등록</h1>
		<p class="mt20"><label><strong>검색어</strong></label>
		<input type="text" id="insVal" name="insVal" class="txt" style="width:70%;" v:required="trim" m:required="<gm:tagAttb value='jsp.search.search_insert.msg.seareq'/>" class="w520" v:text8_limit="200" m:text8_limit="<gm:tagAttb value='jsp.search.search_insert.msg.200'/>" value="<g:tagAttb value="${insVal}"/>" />
		<input type="hidden" id="insIdx" name="insIdx" value="${insIdx}" /></p>
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:insertSearch();"><strong>등록</strong></a>
			<a class="btn_s" href="javascript:closePop();"><strong>닫기</strong></a>
		</div>
	</div>
</s:form>	
</body>