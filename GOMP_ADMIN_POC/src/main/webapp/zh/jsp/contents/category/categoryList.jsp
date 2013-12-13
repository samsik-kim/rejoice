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

<script language='javascript'>

	var listSize = 0;

	var checkCategory = {
		checkprior : function() {
			var errcnt = 0;
			var chkLen = myForm.dpCatPrior.length;
			for(var i=0; i < chkLen; i++) {
				if((chkLen + 2) <= eval(myForm.dpCatPrior[i].value)) {
					errcnt++;
					break;
				}
			}
			if(errcnt > 0) {
				return false;
			}
			return true;
		}
	};

	var funcCategoryUpdateStatus = function(idx, dpCatNo) {

		if(showValidate('myForm', 'dialog', 'Check Input Value.', checkCategory)) {

			var dpCatPrior = document.getElementsByName("dpCatPrior")[idx].value;
			var useYn      = document.getElementsByName("useYn")[idx].value;

			if(confirm("<gm:string value='jsp.contents.category.categoryList.msg.confirm_upd'/>")) {
				document.getElementById('updateDpCatPrior').value	= dpCatPrior;
				document.getElementById('updateUseYn').value		= useYn;
				document.getElementById('updateDpCatNo').value		= dpCatNo;

				document.myForm.action = './updateCategory.omp';
				document.myForm.submit();
			}

		}

	};

	var funcCategoryListUpdateStatus = function() {

		var chkLen = myForm.dpCatPrior.length;

		if(chkLen > 0){

			if(showValidate('myForm', 'dialog', 'Check Input Value.', checkCategory)) {
				if(confirm("<gm:string value='jsp.contents.category.categoryList.msg.confirm_order'/>")){
					document.myForm.action = './updateCategoryPriorList.omp';
					document.myForm.submit();
				}
			}

		} else {
			alert( "<gm:string value="jsp.contents.category.categoryList.msg.none_select"/>" );
			return;
		}

	};

	var funcDeleteCategory = function(idx,dpCatNo, catProdCnt) {
		if(catProdCnt > 0) {
			alert( "<gm:string value="jsp.contents.category.categoryList.msg.alert"/>" );
			return;
		}
		if(confirm("<gm:string value='jsp.contents.category.categoryList.msg.confirm_del'/>")) {
			document.myForm.deleteDpCatNo.value	= dpCatNo;
			document.myForm.action = './deleteCategory.omp';
			document.myForm.target="_self";
			document.myForm.submit();
		}
	};

	var funcIncCategoryPrior = function(idx) {

		var num = document.getElementsByName("dpCatPrior")[idx].value;
		
		if((Number(listSize) + 2) <= (Number(num) + 1)) {
			alert( "<gm:string value="jsp.contents.category.categoryList.msg.check_max_order"/>" );
			document.getElementsByName("dpCatPrior")[idx].value = num;
			return;
		}

		document.getElementsByName("dpCatPrior")[idx].value = Number(document.getElementsByName("dpCatPrior")[idx].value) + 1;
	};

	var funcDecCategoryPrior = function(idx) {
		
		var num = document.getElementsByName("dpCatPrior")[idx].value;
		
		if((Number(num) - 1) < 1) {
			alert( "<gm:string value="jsp.contents.category.categoryList.msg.check_min_order"/>" );
			document.getElementsByName("dpCatPrior")[idx].value = num;
			return;
		}

		document.getElementsByName("dpCatPrior")[idx].value = Number(document.getElementsByName("dpCatPrior")[idx].value) - 1;
	};

	var funcPopCategory = function(idx, dpCatNo) {
		
		var form = myForm;	
		width = 480;
		height = 260;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "No";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
		form.action="popCategory.omp?topCode=M002&leftCode=M002001001&dpCatNo=" + dpCatNo;
		form.target="popup";
		form.submit();
	};

</script>

		<div id="location">
			首頁  &gt; 內容管理中心  &gt; 類別管理  &gt; <strong>SC類別管理 </strong> 
		</div><!-- //location -->
	
		<h1 class="fl pr10 mb0">SC類別管理 </h1>
		<p>可編輯刊登於SC(手機端)產品之類別.</p>
		<p class="fl mt20 mb05"><a class="btn_s" href="javascript:funcPopCategory('','')"><span>附加類別</span></a></p>
		<p class="fr mt25">類別搜尋總數 : 共 <s:property value="dpCatList.size()"/> 筆</p>
		<s:form id="myForm" name="myForm" theme="simple" method="post">
		<s:hidden name="upDpCatNo" value="%{upDpCatNo}"/>
		<s:hidden name="updateDpCatNo" id="updateDpCatNo"/>
		<s:hidden name="updateDpCatNm" id="updateDpCatNm"/>
		<s:hidden name="updateTargetUrl" id="updateTargetUrl"/>
		<s:hidden name="updateDpCatPrior" id="updateDpCatPrior"/>
		<s:hidden name="updateUseYn" id="updateUseYn"/>
		<s:hidden name="deleteDpCatNo" id="deleteDpCatNo" value=""/>
		<s:hidden name="dpCatPriorList"/>
		<table class="tabletype02">
			<colgroup>
				<col style="width:40px;" >
				<col >
				<col >
				<col >
				<col >
				<col >
			</colgroup>
			<thead>
			<tr>
				<th>序號</th>
				<th>類別</th>
				<th>產品總數</th>
				<th>變更</th>
				<th>使用與否</th>
				<th>編輯排序</th>
			</tr>
			</thead>
			<tbody>
		<s:if test="dpCatList.size == 0">
		<tr><td colspan="6" class="text_c"><gm:string value='jsp.contents.category.categoryList.msg.none_result'/></td></tr>
		</s:if>
		<s:else>
		<s:iterator value="dpCatList" status="status">
			<tr>
				<td><s:property value="#status.index+1"/></td>
				<td><s:hidden name="dpCatNo"/>&nbsp;<s:property value="dpCatNm"/></td>
				<td><s:property value="catProdCnt"/> 個</td>
				<td class="align_td">
					<a class="btn_s" href="javascript:funcPopCategory('${status.index}','${dpCatNo}')"><span>變更</span></a>
					<a class="btn_s" href="javascript:funcDeleteCategory(${status.index},'${dpCatNo}', '${catProdCnt}')"><span>刪除</span></a></td>
				<td class="align_td">
					<select id="useYn" name="useYn">
						<option value="Y" <c:if test="${useYn eq 'Y' }"> selected </c:if>>使用</option>
						<option value="N" <c:if test="${useYn eq 'N' }"> selected </c:if>>未使用</option>
					</select>
				</td>
				<td class="align_td">
					<input id="dpCatPrior" type="text" name="dpCatPrior" class="txt" style="width:80px;" value="<s:property value="dpCatPrior"/>" 
						v:required m:required="<gm:string value="jsp.contents.category.categoryList.msg.check_order"/>"
						v:mustnum m:mustnum="<gm:string value='jsp.contents.category.categoryList.msg.check_num_order'/>" 
						v:ncompare="gt,0" m:ncompare="<gm:string value='jsp.contents.category.categoryList.msg.check_num_positive'/>" 
						v:checkprior m:checkprior="<gm:string value="jsp.contents.category.categoryList.msg.check_max_order"/>" />
					<span class="in_num">
						<a class="num_up" href="javascript:funcIncCategoryPrior(${status.index})">+1</a>
						<a class="num_down" href="javascript:funcDecCategoryPrior(${status.index})">-1</a>
					</span>
					<a class="btn_s" href="javascript:funcCategoryUpdateStatus('${status.index}','${dpCatNo}')"><strong>變更</strong></a>
				</td>
			</tr>
			<script>listSize = <s:property value="#status.index+1"/>;</script>
		</s:iterator>
		</s:else>
			</tbody>
		</table>
		<p class="btn_wrap text_r mt25"><a class="btn" href="javascript:funcCategoryListUpdateStatus()"><span>批量變更</span></a></p>
		</s:form>

</body>
</html>
