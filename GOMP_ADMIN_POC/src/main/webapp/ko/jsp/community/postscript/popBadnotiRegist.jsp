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
<title>불량사용후기 신고</title>

<script type="text/javascript">

	function doSubmit() {

		if(showValidate('popBadnotiForm', 'dialog', 'Check Input Value.')) {
			
			if(confirm("<gm:string value='jsp.community.postscript.popBadnotiRegist.msg.confirm_rpt'/>")) {
				//$("#dpBadnoti.rptDscr").val( jQuery.trim($("#dpBadnoti.rptDscr").val())) );
				popBadnotiForm.submit();
			}

		}
	}

	function doCancle(){
		self.close();
	}

</script>

</head>
<body class="popup">
	<div id="popup_area_440">
		<s:form action="popInsertBadnoti" id="popBadnotiForm" theme="simple">
			<input type="hidden" name="dpBadnoti.notiNo" id="notiNo" value="${dpBadnoti.notiNo }"/>
			<input type="hidden" name="selectedNotiNo" id="selectedNotiNo" value="${selectedNotiNo }"/>
			<input type="hidden" name="dpBadnoti.mbrNo" id="mbrNo" value="${dpBadnoti.mbrNo }"/>
			<input type="hidden" name="dpBadnoti.pnshClsCd" id="pnshClsCd" value="PD00540"/>

			<input type="hidden" name="dpProdNoti.page.no" id="pageNo" value="${dpProdNoti.page.no}"/>
			<input type="hidden" name="dpProdNoti.searchType" id="searchType" value="${dpProdNoti.searchType}"/>
			<input type="hidden" name="dpProdNoti.searchValue" id="searchValue" value="${dpProdNoti.searchValue}"/>
			<input type="hidden" name="dpProdNoti.srchBadnotiYn" id="searchBadnotiYn" value="${dpProdNoti.srchBadnotiYn}"/>
			<input type="hidden" name="dpProdNoti.srchDelYn" id="searchDelYn" value="${dpProdNoti.srchDelYn}"/>
			<input type="hidden" name="dpProdNoti.startDate" id="startDate" value="${dpProdNoti.startDate}"/>
			<input type="hidden" name="dpProdNoti.endDate" id="endDate" value="${dpProdNoti.endDate}"/>

    	<table class="pop_tabletype01">
        	<colgroup>
            	<col style="width:25%;">
                <col >
                <col style="width:25%;">
                <col >
         	</colgroup>
        	<tbody>
            	<tr>
                	<th>신고사유</th>
                    <td colspan="3">
                    	<select id="badnotiRptCd" name="dpBadnoti.badnotiRptCd" style="width:104px;"
							v:required="trim" m:required="<gm:string value="jsp.community.postscript.popBadnotiRegist.msg.none_select_rptcd"/>" >
                        	<option value="">신고사유선택</option>
		    				<gc:options group="PD0043" codeType="full" />
		    			</select>
                    </td>
                </tr>
                <tr>
                	<th>운영자ID</th>
                    <td>${dpBadnoti.regId}</td>
                    <th>신고일시</th>
                    <td><g:text value="${dpBadnoti.regDttm}" format="L####-##-## ##:##"/></td>
                </tr>
                <tr>
                	<th>의견</th>
                    <td colspan="3">
                    	<textarea cols="33" rows="5" id="rptDscr" name="dpBadnoti.rptDscr"
							v:required="trim" m:required="<gm:string value="jsp.community.postscript.popBadnotiRegist.msg.check_rptdscr"/>"
							v:text8_limit="500" m:text8_limit="<gm:string value="jsp.community.postscript.popBadnotiRegist.msg.check_len_dscr"/>" ></textarea>
					</td>
                </tr>
            </tbody>
        </table>
        </s:form>

		<div class="pop_btn" >
			<a class="btn_s" href="javascript:doSubmit();"><strong>신고</strong></a>
            <a class="btn_s" href="javascript:doCancle();"><strong>취소</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>
