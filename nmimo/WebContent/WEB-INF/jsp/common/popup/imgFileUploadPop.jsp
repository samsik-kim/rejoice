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
		
		var contents = "<img src='/messageBody/"+text.fileRealName+"' border='0'>";
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
		if( extension == 'gif' || extension == 'jpg' ){        
			return;			        
		}else{
			alert("jpg / gif 파일로만 업로드가 가능합니다.");          
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
		<h2>이미지 업로드</h2><br>
	</div>
	<hr />
	
	<!-- pContents -->
	<div class="pContents">
		<form name="frm" id="frm" method="post" enctype="multipart/form-data">
	    <!-- Upload Area -->
	    <div class="pUpload">
	        <ul>
	        <li><strong>이미지 파일은 형식에 맞는 JPG , GIF 파일로만 업로드가 가능합니다.</strong><br></li>
	        <li class="red">용량은 307KB 이하로 제한됩니다.</li>
	        </ul>
	    </div>
	    <div class="pUpload">
	        <ul>
		        <li>IMG 파일 <input class="btn_sml" type="file" name="fileNm" id="fileNm" value="찾아보기" v:required m:required="이미지 파일을 선택해 주세요."></li>
	        </ul>
	        <ul>
		        <li>URL 링크 <input class="btn_sml" type="text" name="urlInfo" id="urlInfo" > - http:// 포함해서 입력 (필요시) 
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

