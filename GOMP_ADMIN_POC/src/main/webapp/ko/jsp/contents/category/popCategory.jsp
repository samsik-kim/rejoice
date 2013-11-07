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
<c:if test="${empty param.dpCatNo}">
	<title>카테고리 등록</title>
</c:if>
<c:if test="${not empty param.dpCatNo}">
	<title>카테고리 수정</title>
</c:if>

<script language='javascript'>

	var funcInsertCategory = function() {

		if(showValidate('myForm', 'dialog', 'Check Input Value.')) {
			document.myForm.action = './popInsertCategory.omp';
			document.myForm.submit();
		}
	};

	var funcUpdateCategory = function() {
		
		if(showValidate('myForm', 'dialog', 'Check Input Value.')) {
			document.myForm.action = './popUpdateCategory.omp';
			document.myForm.submit();
		}

	};

	function fileImgExtCheck(obj) {

		var reg = /.(gif|jpg|jpeg|png)(s{0,})$/i;

		var result = obj.value.search(reg);

		if(result == "-1"){
			alert("<gm:string value="jsp.contents.category.popCategory.msg.check_file_ext"/>");
			if($.browser.msie) {
			       $("#bodyImageUpload").replaceWith( $("#bodyImageUpload").clone(true) );
			       $("#modifyBodyImageUpload").replaceWith( $("#modifyBodyImageUpload").clone(true) );
			} else {
			      $("#bodyImageUpload").val("");
			      $("#modifyBodyImageUpload").val("");
			}
			return false;
		}
	};

</script>
	
</head>
<body class="popup">
	<div id="popup_area_440">
		<c:if test="${empty param.dpCatNo}">
		<h1>카테고리 등록</h1>
		</c:if>
		<c:if test="${not empty param.dpCatNo}">
		<h1>카테고리 수정</h1>
		</c:if>
		
		<s:form name="myForm" theme="simple" method="post" enctype="multipart/form-data">
		<s:hidden name="upDpCatNo" value="%{upDpCatNo}"/>
		<s:hidden name="dpCat.dpCatNo" value="%{dpCat.dpCatNo}"/>
        <table class="pop_tabletype01" id="categoryTable">
			<colgroup>
				<col style="width:30%;"><col style="width:70%;">
			</colgroup>
			<tbody>
            	<tr>
                	<th><label for="#">카테고리명</label></th>
                    <td><input id="dpCatNm" name="dpCat.dpCatNm" type="text" class="txt" value="${dpCat.dpCatNm}" 
						v:required m:required="<gm:string value="jsp.contents.category.popCategory.msg.check_dpcatnm"/>" /></td>
                </tr>
                <!--
                <tr>
                	<th><label for="#">Target URL</label></th>
                    <td><s:textfield id="modifyTargetUrl" name="modifyTargetUrl" cssStyle='width:400px;' maxlength='250'/></td>
                </tr>
                -->
			<c:if test="${empty param.dpCatNo}">
                <tr>
                	<th><label for="#">IMG등록</label></th>
                    <td><input id="bodyImageUpload" name="dpCat.bodyImageUpload" type="file" 
                    	onchange="fileImgExtCheck(this);" 
						v:required m:required="<gm:string value="jsp.contents.category.popCategory.msg.check_image"/>" /></td>
                </tr>
			</c:if>
			<c:if test="${not empty param.dpCatNo}">
                <tr>
                	<th><label for="#">IMG등록</label></th>
                    <td>
                    	&nbsp;<img src="${CONF['omp.common.url.http-share.common.scctgr']}${dpCat.bodyFileNm}" width="40" height="40" 
							onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" />
                    	&nbsp;<!-- ${dpCat.bodyFileNm} -->
						<input id="modifyBodyImageUpload" name="dpCat.modifyBodyImageUpload" type="file" 
							onchange="fileImgExtCheck(this);" /></td>
                </tr>
			</c:if>
            </tbody>
          </table>
		<c:if test="${empty param.dpCatNo}">
          <p class="mt10">※ 이미지 등록시</p>
		</c:if>
		<div class="pop_btn" >
			<p>
		<c:if test="${empty param.dpCatNo}">
			<a class="btn_s" href="javascript:funcInsertCategory();"><strong>등록</strong></a>
		</c:if>
		<c:if test="${not empty param.dpCatNo}">
			<a class="btn_s" href="javascript:funcUpdateCategory();"><strong>수정</strong></a>
		</c:if>
			<a class="btn_s" href="javascript:self.close();"><strong>취소</strong></a></p>
		</div>
		</s:form>
	</div>
</body>
</html>
