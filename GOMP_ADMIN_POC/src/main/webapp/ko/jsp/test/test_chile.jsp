<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<script type="text/javascript">
    var binCnt = 0;
    var libCnt = 0;
    
    window.onload = function() {
        binCnt = parent.form1.binCnt.value;
        libCnt = parent.form1.libCnt.value;
        
        var doc      = parent.form1;
        var lib_nm   = "";
        var lib_val  = "";
        var bin_nm   = "";
        var bin_val  = "";
        var chk_nm   = "";
        var chk_val  = "";
        var strHTML  = "";
        var strCheck = "";
            
        for (var i = 0; i < libCnt; i++) {
            lib_nm   = "listLibrary["+ i +"].scid";
            lib_val  = doc[lib_nm].value; 
            strHTML += "<INPUT type=text name=\"" + lib_nm + "\" value=\"" + lib_val + "\" /><BR>";

            for (var j =0; j < binCnt; j ++) {
                bin_nm   = "listBinary["+ j +"].scid";
                bin_val  = doc[bin_nm].value;
                
                chk_nm   = "M[0]." + lib_val + bin_val;
                chk_val  = doc[chk_nm].value;
                
                strCheck = (chk_val == "Y") ? "checked" : "";
                strHTML += "<INPUT type=\"checkbox\" name=\"M[0]." + lib_val + bin_val + "\" "+ strCheck +" onclick=\"checkClick(this);\" />　　";
            }
            strHTML += "<BR>";                                 
        }
        
        
        libs.innerHTML = strHTML;
        var form = document.form1;
    }   

    function checkClick(obj) {
    	var doc    = parent.form1;
        var obj_nm = obj.name;
        var oval   = doc[obj_nm].value;

        if (oval == "Y") {
            doc[obj_nm].value = "N";
        } else {
        	doc[obj_nm].value = "Y";
        } 
    }

    function f_libAdd() {
        libCnt        = parseInt(libCnt) + 1;
    	var doc       = parent.form1;
        var lib_nm     = "listLibrary[" + libCnt + "].scid";
        var lib_val    = "library" + libCnt;
        var strHTML    = "";
        var strHTMLLIB = "";
        var strHTMLMAP = "";
         
        strHTML   += "<INPUT type=text name=\"" + lib_nm + "\" value=\"" + lib_val + "\" /><BR>";
        strHTMLLIB = "<INPUT type=text name=\"" + lib_nm + "\" value=\"" + lib_val + "\" />";
        
        for (var j =0; j < binCnt; j ++) {
            bin_nm   = "listBinary["+ j +"].scid";
            bin_val  = doc[bin_nm].value;
            strHTML    += "<INPUT type=\"checkbox\" name=\"M[0]." + lib_val + bin_val + "\"  onclick=\"checkClick(this);\" />　　";
            strHTMLMAP += "<INPUT type=\"text\"     name=\"M[0]." + lib_val + bin_val + "\"  value=\"N\" />";
        }
        
        libs.innerHTML += strHTML + "<BR>";

        parent.document.getElementById("librarys")  .innerHTML += strHTMLLIB;
        parent.document.getElementById("libmapping").innerHTML += strHTMLMAP + "<BR>";
    }
    
</script>
</head>

<body>
<INPUT type="button" value="눌러" onclick="f_libAdd();" />
<form name="form1">
<DIV id="libs">
</DIV>
</form>
</table>
</body>
</html>