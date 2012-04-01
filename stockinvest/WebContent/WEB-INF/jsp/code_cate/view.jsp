<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
function delchk(){
	if (confirm("삭제된 데이타는 복구가 불가능합니다. \n\n정말 삭제하시겠습니까?")){
		fn_delete('${info.seqNo}');
	}
}

//상세
function fn_delete(seqNo){
	$("#seqNo").val(seqNo);
	$("#detailFrm").attr('action','/stockinvest/codeDelete.do') ;
	$("#detailFrm").submit();
}
//-->
</SCRIPT>
<br><br>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr height="30"> 
     <td><font color="#AE0000"><B>[ 코드관리 ]</B></font></td>
  </tr>
  <tr>
	<td>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="30" bgcolor="white">
	<td class="subject" width="100">종 목 명</td>
	<td class="object" style="padding-left:5px;">${info.codeName}</td>
	<td class="subject" width="100">종목코드</td>
	<td class="object" style="padding-left:5px;">${info.codeNum}</td>
</tr>
 <tr height="30" bgcolor="white">
	<td class="subject">전화번호</td>
	<td class="object" style="padding-left:5px;">
	${info.tel}
	</td>
	<td class="subject">정보연락처</td>
	<td class="object" style="padding-left:5px;">
	${info.infoTel}
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">지분보유</td>
	<td class="object" style="padding-left:5px;">${info.holdShare}</td>
	<td class="subject">주주</td>
	<td class="object" style="padding-left:5px;">${info.juju}</td>
</tr>
</table>

</td>
</tr>
<tr height="30">
	<td align="right">

<a href="">[ 수 정 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:delchk();">[ 삭 제 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="">[ 목 록 ]</a>

	</td>
</tr>
</table>
<form name="detailFrm" id="detailFrm" method="post">
	<input type="hidden" name="seqNo" id="seqNo" />
	<input type="hidden" name="currentPage" value="${pageInfo.currentPage}" />
</form>