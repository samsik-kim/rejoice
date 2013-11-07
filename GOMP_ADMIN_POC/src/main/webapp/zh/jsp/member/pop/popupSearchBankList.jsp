<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function(){ 
	//
	$('#searchBankList').hide();
	
	//Init Focus
	$('#bankNm').focus();
	
	//
	$("#bankNm").keypress(function(event) {
		if(event.keyCode == 13) {
			$("#codesearch").click();
			return false;
		}
	});
	
	// Search Bank List
	$('#codesearch').click(function(){
		if(showValidate('searchBank', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			var data = $("#searchBank").serialize();
			$.ajax({
				url: env.contextPath + '/mypage/ajaxpopupSearchCodeList.omp',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: true,		
				cache	: false,
				success: function(json){
					if(json.result == "SUCCESS"){
						var str = "";
						for(i=0; i<json.jsonList.length; i++){
							str += "<tr><td><a href=\"javascript:selectBankCd('"+ json.jsonList[i].dtlFullCd + "', '" + json.jsonList[i].cdNm + "');\" title='"+json.jsonList[i].cdNm+"'>"+json.jsonList[i].dtlFullCd+"</a></td>";
							str += "<td><a href=\"javascript:selectBankCd('"+ json.jsonList[i].dtlFullCd + "', '" + json.jsonList[i].cdNm + "');\" title='"+json.jsonList[i].cdNm+"'>"+json.jsonList[i].addField1+"</a></td>";
							str += "<td class='tit01'><a href=\"javascript:selectBankCd('"+ json.jsonList[i].dtlFullCd + "', '" + json.jsonList[i].cdNm + "');\" title='"+json.jsonList[i].cdNm+"'>"+json.jsonList[i].cdNm+"</a></td></tr>";
						}
						$('#searchBankList').show();
					}else if(json.result == "FAIL"){
						$('#searchBankList').show();
						str = "<tr><td colspan='3' class='tit02'>無資料</td></tr>";
					}
					$('#bankListSuccess').html(str);
				}
			});
		}
	});
});
 
function selectBankCd(bankCd, bankNm){
	$('#bankinfo', opener.document).val(bankCd);
	$('#bankinfo1', opener.document).val(bankNm);
	self.close();
}


</script>
<div>
<div id="pop_area04">
	<form name="searchBank" id="searchBank" method="post">
	<h2 class="h21"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_title_04.gif'/>" alt="查詢銀行代號" /></h2>
	<p class="pop_txt5">請輸入您欲找的銀行名稱或簡稱!  <br />若是臺灣銀行, 可輸入「臺灣」或「臺銀」.</p>
	<p class="pop_txt6"><input type="text" name="bank.cdNm" id="bankNm" class="w280"  
						v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>"
						v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.bk02'/>" /> &nbsp;
	<a shape="hover" id="codesearch"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_search.gif'/>" alt="查詢" style="cursor: pointer;"/></a>
	</form>
	
	<div class="btstyleB h184" style="overflow-x:hidden;" id="searchBankList">
		<table summary="查詢銀行代號" class="w412">
			<caption>查詢銀行代號</caption>
			<colgroup>
				<col width="21%" />
				<col />
			</colgroup>
			<thead>
				<tr>
					<th><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_th01.gif'/>" alt="銀行代號" /></th>
					<th><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_th03.gif'/>" alt="銀行簡稱" /></th>
					<th class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_th02.gif'/>" alt="銀行名稱" /></th>
				</tr>
			</thead>
			<tbody id="bankListSuccess"></tbody>
		</table>
	</div>
	
</div>
