<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.nmimo.common.util.StringUtils"%>

<script type="text/javascript">

	$(function(){
		$("#goSubmit").click(function(){
			
			if(showValidate('frm', 'alert', '필수항목을 입력해 주세요.') == false){
				return;
			}
			
			if(fileExtCheck($("#fileNm").val()) == false){
				return;
			}

			submitSave(	"frm", "/common/popup/ajaxMultiFileUploadResult.do", result);
		});
	});
	
	function result(text, status){
		
		var contents = "<A href='/messageBody/"+text.fileRealName+"'><font color='blue'>동영상보기</font></A>";
// 		var bodyStyle = '<style>' +
// 		'	a {text-decoration: none; color:black}' +
// 		'	a:hover {text-decoration: underline; color:navy}' +
// 		'	P {margin-top:2px;margin-bottom:2px;}' +
// 		'	body {background-color:;font-size:9pt;letter-spacing:1px;font-family:굴림;line-height:120%;background-repeat: no-repeat; '+
// 		'	background-attachment:fixed;overflow-x:no;scrollbar-face-color: #d1d1d1;scrollbar-shadow-color: #cccccc;scrollbar-highlight-color: #f1f1f1;'+
// 		'	scrollbar-3dlight-color: #ffffff;scrollbar-darkshadow-color: #ffffff;scrollbar-track-color: #f1f1f1;scrollbar-arrow-color: #333333;}</style>';

// 		var innerHtml = '<html><head>'+ bodyStyle +'<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"></head>' +
// 		'<body style="word-wrap:break-word;word-break:break-all;background-color:#ffffff;" topmargin="0px" rightmargin="0px" bottommargin="0px" leftmargin="0px">' +
// 		contents +
// 		'</body></html>';
		
		if(text.resultCode == "S"){
			opener.document.frm.arrMultiFileName.value+=text.fileRealName+"&";
			
			txtWin = opener.textWindow;
			strBody = txtWin.document.body.innerHTML;

			strBody = strBody.replace("</P>", "<BR>");
			strBody = strBody.replace("</p>", "<BR>");
			strBody = strBody.replace("<P>", "");
			strBody = strBody.replace("<p>", "");
			strBody = strBody + contents; 

			txtWin.document.body.innerHTML = "";
			txtWin.document.selection.clear();
			txtWin.focus();

			txtWinCreate = txtWin.document.selection.createRange();
			txtWinCreate.pasteHTML(strBody);
		}
		
		alert(text.resultMsg);
		self.close();
	}
	
	//업로드 파일 확장자 체크
	function fileExtCheck(v){     

		var filePath = v;      
		var extension = (filePath.substr(filePath.length-3)).toLowerCase();             
		if( extension == 'mp4' ){        
			return;			        
		}else{
			alert("mp4 파일로만 업로드가 가능합니다.");          
			return false;
		}
	}
	
</script>

<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
</style>
</head>

<div id="popup" style="width:460px;">

<!-- pTop -->
<div class="pTop">
	<h2>동영상 업로드</h2><br>
</div>
<hr />

<!-- pContents -->
<div class="pContents">
	<form name="frm" id="frm" method="post" enctype="multipart/form-data">
    <!-- Upload Area -->
    <div class="pUpload">
        <ul>
        <li><strong>이미지 파일은 형식에 맞는 MP4 파일로만 업로드 가능합니다.</strong><br></li>
        <li class="red">용량은 819KB 이하로 제한됩니다. <a href="#">[동영상 첨부 주의사항 필독]</a></li>
        </ul>
    </div>
    <div class="pUpload">
        <ul>
	        <li>전송 파일 <input class="btn_sml" type="file" name="fileNm" id="fileNm" value="찾아보기" v:required m:required="동영상 파일을 선택해 주세요."></li>
        </ul>
        <ul>
	        <li>
	        	동영상 파일 사이즈는 동영상이 첨부된 후 작성된 최종 메시지의
				사이즈가 1MB를 넘을 수없습니다. 동영상 최대 첨부 가능사이즈는
				메시지 작성 내용과 동영상 파일에 따라 달라질 수 있습니다.
	        </li>
        </ul>
    </div>
</form>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" id="goSubmit" class="btn_red"><strong>확 인</strong></a> 
	<a href="#" onclick="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>