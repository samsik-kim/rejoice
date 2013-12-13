<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.on{
    background: none repeat scroll 0 0 #EEEEEE;
    font-weight: bold;
    text-decoration: none;
}
</style>
<script type="text/javascript" src="<c:url value="/inc/_pophead.js"/>"></script>
<script type="text/javascript">

function trim(value) {
	return value.replace(/^\s+|\s+$/g,"");
}

var ctgrIndex=0;
function ctgrClick(){
	$("#testCategory li").each(function(){
		$(this).click(function(){
			if($(this).hasClass("on")){
				$(this).removeClass("on");
				$("#name").val("");
				$("#explanation").val("");
				//$("#useY").attr("checked","");
				//$("#useN").attr("checked","");
				return;
			}
			$(this).addClass("on");
			var mId = this.id;
			$("#name").val(trim($(this).text()));
			$("#explanation").val($("#explain"+mId).val());
			if($("#useYn"+mId).val()=='Y'){
				$("#useY").attr("checked",true);
			}else{
				$("#useN").attr("checked",true);
			}	
			$("#testCategory li").each(function(){
				if(mId != this.id){
					$(this).removeClass("on");
				}
			});
		});
	});
}
	
$(function() {
	$("#name").change(function() {
		var checkInsert=0;
		var modText = document.getElementById('name').value;
		$("#testCategory li").each(function(){
			if($(this).hasClass("on")){
				$(this).text(modText);
				checkInsert=1;
			}
		});
		if(checkInsert!=1){
			$("#testCategory").append("<li name='ctgrDepth' id='ctgrDepth"+ctgrIndex+"' value='' onClick='javascript:ctgrClick();'>" + modText + "</li>");
			ctgrIndex++;
		}
	});
});

function saveCtgr(){
	if(showValidate('ctgrForm', 'default','')){
	$("#testCategory li").each(function(){
		if($(this).hasClass("on")){
			document.getElementById('ctCtgrSeq').value=$(this).attr("value");
		}
	});
	var tempNm = document.getElementById('name').value;
	var tempExpl = document.getElementById('explanation').value;
	
	if(document.getElementById('useY').checked==true){
		document.getElementById('usYN').value='Y';
	}else{
		document.getElementById('usYN').value='N';
	}
	document.getElementById('titleNm').value=tempNm;
	document.getElementById('expl').value=tempExpl;
	var options={ 
			 success:       showResponse,
	         url:       env.contextPath+"/verify/saveCategory.omp", 
	         type:      "post"  
		}; 
	$('#ctgrForm').ajaxSubmit(options);
	}
}

function closeCtgr(){
	opener.parent.selectVerifyList();
	window.close();
}

function showResponse(){
	alert("<gm:string value='jsp.certifymgr.popupCategory.msg.save'/>");
	location.href = env.contextPath+"/verify/popCategory.omp";
}
</script>
<title>新增類別時產生之跳出式視窗</title>
</head>
<body class="popup">
<s:form id="ctgrForm" name="ctgrForm" theme="simple" method="post" enctype="multipart/form-data">
	<s:hidden id="expl" name="expl"/>
	<s:hidden id="titleNm" name="titleNm"/>
	<s:hidden id="usYN" name="usYN"/>
	<s:hidden id="ctCtgrSeq" name="ctCtgrSeq"/>
	<div id="popup_area_810">
		<h1>Category List</h1>
		<div class="box04" style="overflow:hidden;">
			<p class="fl mr20"><strong>類別</strong></p>
			<ul id="testCategory" class="scroll ul_case02">
			<c:choose>
					<c:when test="${ctgrCnt > 0}">
						<c:forEach items="${categoryNameList}" var="item" varStatus="ctgrCnt">
							<li name="ctgrDepth" id="${item.ctCtgSeq}" value="${item.ctCtgSeq}" onClick='javascript:ctgrClick();' style="cursor:pointer;">${item.titleNm}
							<input type="hidden" id="explain${item.ctCtgSeq}" name="explain" value="${item.explain}">
							<input type="hidden" id="useYn${item.ctCtgSeq}" name="useYn" value="${item.useYn}">
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li name="ctgrDepth" id="dfault" class="on" value="1"> <gm:string value="jsp.certifymgr.popupCategory.msg.list" /></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<h1 class="mt25">類別內容</h1>
		<table class="pop_tabletype01">
			<colgroup>
				<col width="20%;">
				<col width="80%;">
			</colgroup>
			<tr>
				<th>名稱</th>
				<td><input type="text" id="name" style="width:80%;" maxlength="20" v:required m:required="<gm:tagAttb value='jsp.certifymgr.popupCategory.msg.name01'/>" v:text8_limit="60" m:text8_limit="<gm:tagAttb value="jsp.certifymgr.popupCategory.msg.name02"/>"/></td>
			</tr>
			<tr>
				<th>說明</th>
				<td><input type="text" id="explanation" style="width:80%;" maxlength="50" v:required m:required="<gm:tagAttb value='jsp.certifymgr.popupCategory.msg.explain01'/>" v:text8_limit="150" m:text8_limit="<gm:tagAttb value="jsp.certifymgr.popupCategory.msg.explain02"/>" /></td>
			</tr>
			<tr>
				<th>使用與否</th>
				<td><input type="radio" id="useY" name="use" value="Y" checked/> 使用 <input type="radio" id="useN" name="use" value="N" v:mustcheck="1" m:mustcheck="<gm:tagAttb value='jsp.certifymgr.popupCategory.msg.use'/>"/> 不使用</td>
			</tr>
		</table>
		<div class="pop_btn" >
			<a class="btn" href="javascript:saveCtgr();"><strong>新增／變更</strong></a>
			<a class="btn" href="javascript:closeCtgr();"><strong>關閉</strong></a>
		</div>
	</div>
	</s:form>
</body>