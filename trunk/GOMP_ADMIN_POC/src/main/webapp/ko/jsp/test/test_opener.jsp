<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<script type="text/javascript">
    function f_addHTML() {

        alert("f_addHTML");
            
        var frm = document.form1;
        librarys  .innerHTML = librarys  .innerHTML + frm["H[0].lib"].value;
        libmapping.innerHTML = libmapping.innerHTML + frm["H[0].map"].value;
    }
</script>
</head>
<body>
<form name="form1">
Library Count <INPUT type="text" name="libCnt"  value="3"  /><BR>
Binary  Count <INPUT type="text" name="binCnt"  value="4"  /><BR>

<DIV id="binarys">
Binary =>   <INPUT type="text" name="listBinary[0].scid" value="0000000001" />
            <INPUT type="text" name="listBinary[1].scid" value="0000000002" />
            <INPUT type="text" name="listBinary[2].scid" value="0000000003" />
            <INPUT type="text" name="listBinary[3].scid" value="binary1" />
</DIV>

<DIV id="librarys">
Library =>  <INPUT type="text" name="listLibrary[0].scid" value="0000000001" />
            <INPUT type="text" name="listLibrary[1].scid" value="0000000002" />
            <INPUT type="text" name="listLibrary[2].scid" value="0000000003" />
</DIV>

<DIV id="libmapping">
Mapping 정보 <BR>
    <INPUT type="text" name="M[0].00000000010000000001" value="Y" />
    <INPUT type="text" name="M[0].00000000010000000002" value="N" />
    <INPUT type="text" name="M[0].00000000010000000003" value="Y" />
    <INPUT type="text" name="M[0].0000000001binary1"    value="N" /><BR>    
    <INPUT type="text" name="M[0].00000000020000000001" value="N" />
    <INPUT type="text" name="M[0].00000000020000000002" value="Y" />
    <INPUT type="text" name="M[0].00000000020000000003" value="N" />
    <INPUT type="text" name="M[0].0000000002binary1"    value="N" /><BR>
    <INPUT type="text" name="M[0].00000000030000000001" value="Y" />
    <INPUT type="text" name="M[0].00000000030000000002" value="Y" />
    <INPUT type="text" name="M[0].00000000030000000003" value="Y" />
    <INPUT type="text" name="M[0].0000000003binary1"    value="N" /><BR>
</DIV>
<IFRAME src="test_chile.jsp" width="800" height="400">
</IFRAME>
</form>
</body>
</html>