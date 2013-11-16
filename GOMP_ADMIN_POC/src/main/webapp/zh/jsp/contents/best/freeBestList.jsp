<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/commonTag.tld" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<script language='javascript'>

	var listSize = 0;

	function fncOnlyNumber(obj) {
	    obj.value = Number(String(obj.value).replace(/\..*|[^\d]/g,""));
	}
	
	function goPage(no) {
		$("form[name=searchForm] input[name=sub\\.page\\.no]").val(no);
		funcSearch();
	}
	
	function funcSearch(){

		document.searchForm.action="selectFreeBestList.omp";
		document.searchForm.target="_self";
		document.searchForm.submit();
	}
	
	function funcTodaySearch(){
		document.searchForm.timeType.value = "Y";
		document.searchForm.dpSelectDate.value = "";
		//funcSearch();
		
		document.searchForm.action="insertFreeBestDefault.omp";
		document.searchForm.target="_self";
		document.searchForm.submit();
	}

	function searchInitBtn(){
		document.searchForm.dpUpCatNo.value = "";
		document.searchForm.dpCatNo.value = "";
		document.searchForm.timeType.value = "";
		document.searchForm.dpSelectDate.value = "";
		funcSearch();
	}
	
	function funcUpdateAdminRecommend() {

		var chkLen = document.getElementsByName("expoPrior").length;

		var errCnt = 0;
		var priorList = "";

		if(chkLen > 0){
			if(showValidate('searchForm', 'default', '')){
			if(!confirm("<gm:string value='jsp.contents.best.freeBest.msg.update_confirm'/>")){
				return;
			}						

			//for(var i=0; i < chkLen; i++) {
				
				//if((Number(${totalCount}) + 2) <= eval(searchForm.expoPrior[i].value)) {
				//	alert("<gm:string value='순서는 전체 수보다 2이상 큰 수를 넣을 수 없습니다.'/>");
				//	errCnt++;
				//	break;
				//}
				
				//if(searchForm.expoPrior[i].value == '0') {
				//	alert("순서는 '0'을 넣을수 없습니다.");
				//	errCnt++;
				//	break;
				//}

				//if(isNaN(searchForm.expoPrior[i].value)) {
				//	alert("숫자만 입력할 수 있습니다.");
				//	errCnt++;
				//	break;
				//}

				/*
				for(var i=0; i < chkLen; i++) {
					if(priorList == "") {
						priorList = myForm.dpCatNo[i].value + "=" + myForm.dpCatPrior[i].value;
					} else {
						priorList = priorList + "," + myForm.dpCatNo[i].value + "=" + myForm.dpCatPrior[i].value;
					}
				}
				alert(priorList);
				*/
			//}

			if(errCnt > 0) {
				return;
			}

			document.searchForm.action = './updateFreeBestList.omp';
			document.searchForm.submit();
			}
		} else {
			alert("<gm:string value='jsp.contents.best.freeBest.msg.update_check'/>");
			return;
		}

	}
	
	var makeProdIdStr = function() {
		var ids = "";
		$("input:checkbox[name=checkProdId]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};
	
	function funcDeleteAdminRecommend() {
		if ( $( "input:checkbox[name=checkProdId]:checked" ).length == 0 )	{
			alert( "<gm:string value='jsp.contents.best.freeBest.msg.delete_check'/>" );
			return;
		}

		var selectedProdId =  makeProdIdStr();
		
		if(!confirm("<gm:string value='jsp.contents.best.freeBest.msg.delete_confirm'/>")){
			return;
		}	
		
		document.searchForm.action = './deleteFreeBestList.omp?selectedProdId='+selectedProdId;
		document.searchForm.submit();
	};
/*
	function funcDeleteCategory(idx,dpCatNo, catProdCnt) {
		if(catProdCnt > 0) {
			alert("카테고리내 컨텐츠를 모두 삭제 후\n 다시 시도해 주시기 바랍니다.");
			return;
		}
		if(confirm("선택한 카테고리를 삭제 하시겠습니까?")) {
			document.myForm.deleteDpCatNo.value	= dpCatNo;
			document.myForm.action = './deleteCategory.omp';
			document.myForm.submit();
		}
	}
*/
	function funcIncCategoryPrior(idx) {

		var num = document.getElementsByName("expoPrior")[idx].value;

		//if((Number(${totalCount}) + 2) <= (Number(num) + 1)) {
		//	alert("<gm:string value='순서는 전체 수보다 2이상 큰 수를 넣을 수 없습니다.'/>");
		//	document.getElementsByName("expoPrior")[idx].value = num;
		//	return;
		//}

		document.getElementsByName("expoPrior")[idx].value = Number(document.getElementsByName("expoPrior")[idx].value) + 1;
	}

	function funcDecCategoryPrior(idx) {
		
		var num = document.getElementsByName("expoPrior")[idx].value;
		
		if((Number(num) - 1) < 1) {
			alert("<gm:string value='jsp.contents.best.freeBest.msg.category_prior'/>");
			document.getElementsByName("expoPrior")[idx].value = num;
			return;
		}

		document.getElementsByName("expoPrior")[idx].value = Number(document.getElementsByName("expoPrior")[idx].value) - 1;
	}
	
	function funcProdDetail(prodId){
		width = 440;
		height = 350;
		var url = env.contextPath +"/contents/popAdminProdDetail.omp?prodId=" + prodId;
		
		openCenteredWindow(url, width, height, "no", "prodDetail");
	}
	
	$(document).ready( function()	{
		
		$( "#dpSelectDate" ).datepicker();

		// 전체 체크박스 이벤트
		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name='checkProdId']" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

	} );
</script>
			<div id="location">
				首頁 &gt; 內容管理中心 &gt; 人氣免費產品 &gt; <strong>人氣免費產品</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">人氣免費產品</h1>
			<p>可管理免費BEST產品之公開與否和排列順序</p>
			<s:form id="searchForm" name="searchForm" action="" theme="simple" >
			<input type="hidden" id="dpUpCatNo" name="sub.dpUpCatNo" value="${sub.dpUpCatNo}"/>
			<input type="hidden" id="timeType" name="sub.timeType" value="${sub.timeType}"/>
			<input type="hidden" id="no" name="sub.page.no" value="1" />
			<table class="tabletype01">
				<colgroup><col ><col ><col style="width:125px"></colgroup>
				<tr>
					<th>搜尋類別</th>
					<td class="align_td">
						<select id="dpCatNo" name="sub.dpCatNo" style="width:104px;">
                        	<c:forEach items="${dpCategoryList}" var="dpCategoryList">
                        		<option value="${dpCategoryList.dpCatNo }" <c:if test='${dpCategoryList.dpCatNo eq sub.dpCatNo}'>selected</c:if>>${dpCategoryList.dpCatNm }</option>
                        	</c:forEach>
                        </select>
					</td>
					<td rowspan="3" class="text_c" >
						<a class="btn" href="javascript:funcSearch();"><strong>搜尋</strong></a>
						<a class="btn" href="javascript:searchInitBtn();"><span>重新搜尋</span></a>
					</td>
				</tr>
				<tr>
					<th>搜尋過去資料</th>
					<td class="align_td">
						<input id="dpSelectDate" name="sub.dpSelectDate" value="${sub.dpSelectDate}" type="text" class="txt" style="width:80px;" readonly/>
						<span>* 請點選日期並查詢過去記錄.</span>
					</td>
				</tr>
				<tr>
					<th>讀取最新資料</th>
					<td>					
						<a class="btn_s" href="javascript:funcTodaySearch();"><span>讀取</span></a><br />
						<span>* 請點選「讀取最新資料」後自訂牌類順序, 若不操作會依照下載次數自動排序.</span>
					</td>
				</tr>
			</table>
			
			<p class="fr mt25">產品搜尋結果 : 共 ${totalCount} 筆</p>
			<table class="tabletype02">
				<colgroup>
					<col style="width:40px;" >
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
					<th>序號</th>
					<th>類別</th>
					<th>圖示</th>
					<th>產品名稱</th>
					<th>等級</th>
					<th>開發商</th>
					<th>下載</th>
					<th>公開設定</th>
					<th>編輯排序</th>
					<th>產品AID</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${totalCount eq 0 }">
				<tr>
					<tr><td colspan="12" class="text_c">無資料。</td></tr>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${list}" var="adminRecommendList" varStatus="status">
				<input type="hidden" id="scrGbn" name="upProdId" value="${adminRecommendList.prodId}"/>
				<tr>
					<td><input type="checkbox" name="checkProdId" value="${adminRecommendList.prodId}"/></td>
					<td>${adminRecommendList.rnum}</td>
					<td>${adminRecommendList.catNm}</td>
					<td><img src="${CONF['omp.common.url.http-share.product']}${adminRecommendList.filePos}" width="72" height="72" alt="" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" /></td>
					<td class="text_l"><a href="javascript:funcProdDetail('${adminRecommendList.prodId}');"><g:html value="${adminRecommendList.prodNm}" format="L#######..."/></a></td>
					<td>${adminRecommendList.prodGrdCd}</td>
					<td>${adminRecommendList.mbrId}</td>
					<td>${adminRecommendList.dwldCnt}</td>
					<td class="align_td">
						<select id="expoYn" name="expoYn">
							<option value="Y" <c:if test="${adminRecommendList.expoYn eq 'Y' }"> selected </c:if>>公開</option>
							<option value="N" <c:if test="${adminRecommendList.expoYn eq 'N' }"> selected </c:if>>隱藏</option>
						</select>
					</td>
					<td class="align_td">
						<input id="expoPrior" name="expoPrior" type="text" class="txt" style="width:20px;"  value="${adminRecommendList.expoPrior}" onkeyup="javascript:fncOnlyNumber(this);" v:regexp="[1-9][0-9]*" m:regexp="<gm:tagAttb value='jsp.contents.adminrec.msg.validation01'/>" v:required m:required="<gm:tagAttb value='jsp.contents.adminrec.msg.validation02'/>"/> 
						<span class="in_num">
							<a class="num_up" href="javascript:funcIncCategoryPrior(${status.index})">+1</a>
							<a class="num_down" href="javascript:funcDecCategoryPrior(${status.index})">-1</a>
						</span>
					</td>
					<td>${adminRecommendList.aid}</td>
				</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			</s:form>
			<p class="fl mt05">
			<c:if test="${sub.timeType eq 'Y' }">
				<a class="btn_s" href="javascript:funcDeleteAdminRecommend();"><span>批量刪除</span></a>
			</c:if>
			</p>
			<p class="btn_wrap text_r mt05">
			<c:if test="${sub.timeType eq 'Y' }">
				<a class="btn" href="javascript:funcUpdateAdminRecommend();"><span>批量變更</span></a>
			</c:if>
			</p>
			<!-- paging -->
			 <g:pageIndex item="${list}"/>
			<!-- //paging -->

</body>
</html>