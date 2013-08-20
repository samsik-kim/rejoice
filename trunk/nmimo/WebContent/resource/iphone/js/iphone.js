
/**
 * Iframe ON
 * @param thisForm
 */
function iframeOn(thisForm){
	
	var editorFrame = document.getElementById('textWindow');

	editorFrame.contentWindow.document.body.onclick = function () {
		textWindow.focus();
		chkBytes(thisForm);
	}

	editorFrame.contentWindow.document.body.onkeyup = function () {
		chkBytes(thisForm);
	}

	editorFrame.contentWindow.document.body.onfocus = function () {
		chkBytes(thisForm);
	}
}


/**
 * 아이프레임 Bytes 체크
 * @param thisForm
 */
function chkBytes(thisForm) {
	var url;
	var str, tmpstr;
	
	if (thisForm.msgDivVal != undefined) {

		var chkJobMode = thisForm.msgDivVal.value;
		var chkMode = thisForm.rMode.value; // insert, modify 구분값

		thisForm.msgSbst.value = textWindow.document.body.innerText;
		tmpstr = textWindow.document.body.innerText;
		str = GetBytes(tmpstr);
		

		if (chkMode == 'I') { // 입력페이지
			
//			if (chkJobMode == 'S' && str > '90') { // SMS기준 90byte 가 넘으면 경고창 
//				alert('작성중인 SMS메시지는\n\n90byte로 제한 됩니다.');
//			}



		} else { // 수정페이지

			
//TODO 입력완료 후 작업			
//			if (chkJobMode == 'S') { 
//
//				if (str > MAXSMSMSG_LEN) {
//					alert(MAXSMSMSG_LEN
//							+ "Byte를 초과했습니다.\n\nSMS로 저장된 메세지는 MMS로 변경될수 없습니다.\n\nMMS로 변경을 원하시는 경우 해당 메시지를 삭제후\n\nMMS로 재등록해주세요.");
//					thisForm.msgSbst.value = CutBytesString(tmpstr, MAXSMSMSG_LEN- GetBytes(url));
//					var iFrmBody;
//					var bodyStyle;
//					var bodyHtml;
//					var html;
//					var contents = thisForm.msgSbst.value;
//
//					bodyStyle = "<style>"
//							+ "	a {text-decoration: none; color:black}"
//							+ "	a:hover {text-decoration: underline; color:navy}"
//							+ "	P {margin-top:2px;margin-bottom:2px;}"
//							+ "	body {background-color:;font-size:9pt;letter-spacing:1px;font-family:굴림;line-height:120%;background-repeat: no-repeat; background-attachment:fixed;overflow-x:no;scrollbar-face-color: #d1d1d1;scrollbar-shadow-color: #cccccc;scrollbar-highlight-color: #f1f1f1;scrollbar-3dlight-color: #ffffff;scrollbar-darkshadow-color: #ffffff;scrollbar-track-color: #f1f1f1;scrollbar-arrow-color: #333333;}"
//							+ "</style>";
//
//					html = "<html><head>"
//							+ bodyStyle
//							+ "<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'></head>"
//							+ "<body style='word-wrap:break-word;word-break:break-all;background-color:#ffffff;' topmargin='0px' rightmargin='0px' bottommargin='0px' leftmargin='0px'>"
//							+ contents + "</body></html>";
//
//					textWindow.document.open();
//					textWindow.document.write(html);
//					textWindow.document.close();
//
//					// 메시지내용을 잘라낸후 다시 bytes 체크후 입력
//					tmpstr = thisForm.msgSbst.value;
//
//					thisForm.callbackurl.value = (thisForm.callbackurl.value)
//							.replace(' ', '');
//					url = thisForm.callbackurl.value;
//					tmpstr = tmpstr + url;
//
//					document.getElementById('msg_length').innerText = GetBytes(tmpstr);
//					// 메시지내용을 잘라낸후 다시 bytes 체크후 입력
//				}
//
//			} else { // mms로 저장된 - 무시.. 그냥 mms로 저장
//
//			}
		}

		document.getElementById('msg_length').innerText = str;
	}
}


/**
 * 구분자 등록
 * @param thisForm
 */
function stringAdd(thisForm)
{

	var getHtml;
	var sHtml;
	var bodyStyle;
	var st=0;

	getHtml	= textWindow.document.body.innerHTML;
	bodyStyle = "<style>" +
	"	a {text-decoration: none; color:black}" +
	"	a:hover {text-decoration: underline; color:navy}" +
	"	P {margin-top:2px;margin-bottom:2px;}" +
	"	body {background-color:;font-size:9pt;letter-spacing:1px;font-family:굴림;line-height:120%;background-repeat: no-repeat; background-attachment:fixed;overflow-x:no;scrollbar-face-color: #d1d1d1;scrollbar-shadow-color: #cccccc;scrollbar-highlight-color: #f1f1f1;scrollbar-3dlight-color: #ffffff;scrollbar-darkshadow-color: #ffffff;scrollbar-track-color: #f1f1f1;scrollbar-arrow-color: #333333;}" +
	"</style>";
	
	if(getHtml.indexOf("$1")<0){
		st = 1;
	}
		
	if(getHtml.indexOf("$1")>=0){
		st = 2;
	}
		
	if(getHtml.indexOf("$2")>=0){
		st = 3;
	}
		
	if(getHtml.indexOf("$3")>=0){
		st = 4;
	}

	if(getHtml.indexOf("$4")>=0){
		st = 5;
	}
	if(getHtml.indexOf("$5")>=0){
		st = 6;
	}

	if(getHtml.indexOf("$6")>=0){
		st = 7;
	}
	
	if(getHtml.indexOf("$7")>=0){
		st = 8;
	}
	
	if(getHtml.indexOf("$8")>=0){
		st = 9;
	}
	
	if(getHtml.indexOf("$9")>=0){
		st = 10;
	}
	
	if(getHtml.indexOf("$10")>=0){
		st = 11;
	}

	if(st<=10){

		sHtml			= "<html><head>" + bodyStyle + "<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'></head>";
		sHtml			= sHtml + "<body style='word-wrap:break-word;word-break:break-all;background-color:#ffffff;' topmargin='0px' rightmargin='0px' bottommargin='0px' leftmargin='0px'>";
		sHtml			= sHtml + getHtml + "$"+st;
		sHtml			= sHtml + "</body></html>";
		
		textWindow.document.open();
		textWindow.document.write(sHtml);
		textWindow.document.close();
		textWindow.document.designMode = 'on'; 
		textWindow.focus();
		chkBytes(thisForm);
		
		return;

	}else{
		alert("더이상 구분자를 추가하실수 없습니다.");
		textWindow.focus();
		
		return;
	}
}


/**
 * 폰트 컬러
 * @param xexeCommand
 * @param value
 */
function changeCommand(thisForm,xexeCommand, value){

	if(thisForm.msgDivVal != undefined){
		
		var chkJobMode = thisForm.msgDivVal.value;

		if (chkJobMode == 'S' || chkJobMode == 'T'){													// sms 모드에선 폰트컬르 변경X
			alert('SMS모드에서는 폰트컬르를 변경할수 없습니다.');
			textWindow.focus();
			return;
		}

		textWindow.document.execCommand(xexeCommand,"",value);
		textWindow.focus();
	}
}



/**
 * 특수문자 추가
 * @param imoti
 */

function addMsg(imoti){

	var thisForm = document.frm;
	
	if(thisForm.msgDivVal.value == "S"){
		alert("[사용불가] MMS전용 이모티콘 입니다.");
		return;
	}
	
	var OldHtml = textWindow.document.body.innerHTML;
	var bodyStyle;
	var Html;

	bodyStyle = "<style>" +
				"	a {text-decoration: none; color:black}" +
				"	a:hover {text-decoration: underline; color:navy}" +
				"	P {margin-top:2px;margin-bottom:2px;}" +
				"	body {background-color:;font-size:9pt;letter-spacing:1px;font-family:굴림;line-height:120%;background-repeat: no-repeat; background-attachment:fixed;overflow-x:no;scrollbar-face-color: #d1d1d1;scrollbar-shadow-color: #cccccc;scrollbar-highlight-color: #f1f1f1;scrollbar-3dlight-color: #ffffff;scrollbar-darkshadow-color: #ffffff;scrollbar-track-color: #f1f1f1;scrollbar-arrow-color: #333333;}" +
				"</style>";

	Html =	"<html><head>" + bodyStyle + "<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'></head>" +
			"<body style='word-wrap:break-word;word-break:break-all;background-color:#ffffff;' topmargin='0px' rightmargin='0px' bottommargin='0px' leftmargin='0px'>" +
			OldHtml + imoti +
			"</body></html>";

	textWindow.document.open();
	textWindow.document.write(Html);
	textWindow.document.close();

	textWindow.document.designMode = 'on'; 
	textWindow.focus();
	thisForm.msgSbst.value = textWindow.document.body.innerHTML;
	chkBytes(thisForm);
}


/**
 * 스트링의 바이트 수를 센다(length를 하면 한글도 길이1로 나오는데 바이트 수는 2가 된다)
 * @param str
 * @returns {Number}
 */
function GetBytes( v ){

	var str = new String(v);
	var len = str.length;
	var count = 0;
	
	for (var k=0 ; k<len ; k++){
		temp = str.charAt(k);
		
		if (escape(temp).length > 4) {
			count += 2;
		}
		else if (temp == '\r' && str.charAt(k+1) == '\n') { // \r\n일 경우
			count += 1;
		}
		else if (temp == '\r') { // \r일 경우
			count++;
		}
		else if (temp != '\n') {
			count++;
		} else {
			count++;
		}	
	}
	
	return count;	
}


//발신번호 선택시 Display변경
function setDisplayCallBack(val){	
	var v=val;
	
	if(v=="A"){
		document.getElementById("callBackDiv1").style.display = "block";
		document.getElementById("callBackDiv2").style.display = "none";
		document.getElementById("calbkNoTypeVal2").value = "";
	}else if(v=="B"){
		document.getElementById("callBackDiv1").style.display = "none";
		document.getElementById("callBackDiv2").style.display = "block";
		document.getElementById("calbkNoTypeVal3").value = "";
	}else if(v=="C"){
		document.getElementById("callBackDiv1").style.display = "none";
		document.getElementById("callBackDiv2").style.display = "block";
		document.getElementById("calbkNoTypeVal3").value = "114";
	}else{
		document.getElementById("callBackDiv1").style.display = "none";
		document.getElementById("callBackDiv2").style.display = "none";
	}
}

//발신번호값 선택시 hidden값 세팅
function setDataCalbkNoTypeVal(val){
	var v = val;

	if(v=="A"){
		document.getElementById("feaponCalbkNo").value = document.getElementById("calbkNoTypeVal1").value;	
		document.getElementById("smphCalbkNo").value = document.getElementById("calbkNoTypeVal2").value;
	}else if(v=="B"){
		document.getElementById("feaponCalbkNo").value = document.getElementById("calbkNoTypeVal3").value;
		document.getElementById("smphCalbkNo").value ="";
	}else if(v=="C"){
		document.getElementById("feaponCalbkNo").value = document.getElementById("calbkNoTypeVal3").value;
		document.getElementById("smphCalbkNo").value ="";
	}else{
		document.getElementById("feaponCalbkNo").value ="";
		document.getElementById("smphCalbkNo").value ="";
	}
}

//상세보기 발신번호값 선택시 hidden값 세팅 
function setDataCalbkNoTypeVal2(val){
	var v = val;

	if(v=="A"){
		document.getElementById("calbkNoTypeVal1").value = document.getElementById("feaponCalbkNo").value;	
		document.getElementById("calbkNoTypeVal2").value = document.getElementById("smphCalbkNo").value;
	}else if(v=="B"){
		document.getElementById("calbkNoTypeVal3").value = document.getElementById("feaponCalbkNo").value;
		document.getElementById("smphCalbkNo").value ="";
	}else if(v=="C"){
		document.getElementById("calbkNoTypeVal3").value = document.getElementById("feaponCalbkNo").value;
		document.getElementById("smphCalbkNo").value ="";
	}else{
		document.getElementById("feaponCalbkNo").value ="";
		document.getElementById("smphCalbkNo").value ="";
	}
}



//URL정보 선택시 Display변경
function setDispalyUrlInfo(val){
	var v=val;
	
	if(v=="A"){
		document.getElementById("urlInfoDiv1").style.display = "block";
		document.getElementById("urlInfoDiv2").style.display = "none";
		document.getElementById("urlInfoDiv3").style.display = "none";
	}else if(v=="B"){
		document.getElementById("urlInfoDiv1").style.display = "none";
		document.getElementById("urlInfoDiv2").style.display = "block";
		document.getElementById("urlInfoDiv3").style.display = "none";
	}else if(v=="C"){
		document.getElementById("urlInfoDiv1").style.display = "none";
		document.getElementById("urlInfoDiv2").style.display = "none";
		document.getElementById("urlInfoDiv3").style.display = "block";
	}else{
		document.getElementById("urlInfoDiv1").style.display = "none";
		document.getElementById("urlInfoDiv2").style.display = "none";
		document.getElementById("urlInfoDiv3").style.display = "none";
	}
}


//메세지 작성 단계별 Display 처리
function setHiddenDiv(v1,v2,v3){
	
	var div_step1 = $("#contents_step1").offset();
	var div_step1_width = $("#contents_step1").width();
	var div_step1_height = $("#contents_step1").height();
	var div_step1_left = div_step1.left;
	var div_step1_top = div_step1.top-10;
	
	var div_step2 = $("#contents_step2").offset();
	var div_step2_width = $("#contents_step2").width()+20;
	var div_step2_height = $("#contents_step2").height()+20;
	var div_step2_left = div_step2.left-20;
	var div_step2_top = div_step2.top-10;

	var div_step3 = $("#contents_step3").offset();
	var div_step3_width = $("#contents_step3").width();
	var div_step3_height = $("#contents_step3").height();
	var div_step3_left = div_step3.left;
	var div_step3_top = div_step3.top;

	$("#hidden_div_step1").css("left",div_step1_left+"px");
	$("#hidden_div_step1").css("top",div_step1_top+"px");
	$("#hidden_div_step1").css("width",div_step1_width+"px");
	$("#hidden_div_step1").css("height",div_step1_height+"px");
	
	$("#hidden_div_step2").css("left",div_step2_left+"px");
	$("#hidden_div_step2").css("top",div_step2_top+"px");
	$("#hidden_div_step2").css("width",div_step2_width+"px");
	$("#hidden_div_step2").css("height",div_step2_height+"px");
 	
	$("#hidden_div_step3").css("left",div_step3_left+"px");
	$("#hidden_div_step3").css("top",div_step3_top+"px");
	$("#hidden_div_step3").css("width",div_step3_width+"px");
	$("#hidden_div_step3").css("height",div_step3_height+"px");
	
	if(v1 > 0){
		document.getElementById("hidden_div_step1").style.display="block";
	}else{
		document.getElementById("hidden_div_step1").style.display="none";
	}
	
	if(v2 > 0){
		document.getElementById("hidden_div_step2").style.display="block";
	}else{
		document.getElementById("hidden_div_step2").style.display="none";
	}

	if(v3 > 0){
		document.getElementById("hidden_div_step3").style.display="block";
	}else{
		document.getElementById("hidden_div_step3").style.display="none";
	}

}

//URL정보값 선택시 hidden값 세팅
function setDataUrlInfo(val){
	var v=val;
	
	if(v=="A"){
		document.getElementById("feaponCalbkUrlVal").value = document.getElementById("calbkUrlTypeVal1").value;
		document.getElementById("smphCalbkUrlVal").value = document.getElementById("calbkUrlTypeVal2").value;
		document.getElementById("calbkUrlMemoSbst").value ="";
	}else if(v=="B"){
		document.getElementById("feaponCalbkUrlVal").value = document.getElementById("calbkUrlTypeVal3").value;
		document.getElementById("smphCalbkUrlVal").value = document.getElementById("calbkUrlTypeVal4").value;
		document.getElementById("calbkUrlMemoSbst").value ="";
	}else if(v=="C"){
		document.getElementById("feaponCalbkUrlVal").value = document.getElementById("calbkUrlTypeVal5").value;
		document.getElementById("smphCalbkUrlVal").value = document.getElementById("calbkUrlTypeVal6").value;
		document.getElementById("calbkUrlMemoSbst").value = document.getElementById("calbkUrlTypeVal7").value;
	}else{
		document.getElementById("feaponCalbkUrlVal").value =""; 
		document.getElementById("smphCalbkUrlVal").value ="";
		document.getElementById("calbkUrlMemoSbst").value ="";
	}
}

//상세보기 URL정보값 선택시 hidden값 세팅
function setDataUrlInfo2(val){
	var v=val;
	
	if(v=="A"){
		document.getElementById("calbkUrlTypeVal1").value = document.getElementById("feaponCalbkUrlVal").value;
		document.getElementById("calbkUrlTypeVal2").value = document.getElementById("smphCalbkUrlVal").value;
		document.getElementById("calbkUrlMemoSbst").value ="";
	}else if(v=="B"){
		document.getElementById("calbkUrlTypeVal3").value = document.getElementById("feaponCalbkUrlVal").value;
		document.getElementById("calbkUrlTypeVal4").value = document.getElementById("smphCalbkUrlVal").value;
		document.getElementById("calbkUrlMemoSbst").value ="";
	}else if(v=="C"){
		document.getElementById("calbkUrlTypeVal5").value = document.getElementById("feaponCalbkUrlVal").value;
		document.getElementById("calbkUrlTypeVal6").value = document.getElementById("smphCalbkUrlVal").value;
		document.getElementById("calbkUrlTypeVal7").value = document.getElementById("calbkUrlMemoSbst").value;
	}else{
		document.getElementById("feaponCalbkUrlVal").value =""; 
		document.getElementById("smphCalbkUrlVal").value ="";
		document.getElementById("calbkUrlMemoSbst").value ="";
	}
}


//메세지 구분에 따른 Disabled
function goMsgChk(type,val){
	
	var v=val;
	var table = document.getElementById('iphoneTable');
	var links = table.getElementsByTagName('A');
	
	document.getElementById("msgDivVal").value=v;
	
	for (var i = 0; i < links.length; i++) {
	    links[i].disabled = type;
	}
}

// 설명 div보이기, 감추기
function viewDiv(str){
	document.getElementById(''+ str +'').style.display = '';
}

function hideDiv(str){
	document.getElementById(''+ str +'').style.display = 'none';
}
// 설명 div보이기, 감추기



//메세지특성 선택시 Iframe값 입력
function addHead(thisForm, str){

	var addhead;
	switch (str){
		case 'A' :															
			addhead = '[olleh알림]';	// 일회성 - 공지
		break;
	
		case 'B' :															
			addhead = '[olleh안내]';	// 일회성 - 홍보
		break;
		
		case 'C' :															
			addhead = '[olleh안내]';	// 일회성 - 광고
		break;

		case 'D' :															
			addhead = '[olleh안내]';	// 일회성 - 제휴광고
		break;
	
		case 'E' :															
			addhead = '[olleh]';	// 일회성 - 업무
		break;
	
		default:
			addhead = '';			// 선택안함
	}
	
	var OldHtml = textWindow.document.body.innerHTML;
		OldHtml = OldHtml.replace(/\[olleh\]|\[olleh알림\]|\[olleh안내\]/g, "")
	
	var bodyStyle;
	var Html;
	
	bodyStyle = "<style>" +
			"	a {text-decoration: none; color:black}" +
			"	a:hover {text-decoration: underline; color:navy}" +
			"	P {margin-top:2px;margin-bottom:2px;}" +
			"	body {background-color:;font-size:9pt;letter-spacing:1px;font-family:굴림;line-height:120%;background-repeat: no-repeat; background-attachment:fixed;overflow-x:no;scrollbar-face-color: #d1d1d1;scrollbar-shadow-color: #cccccc;scrollbar-highlight-color: #f1f1f1;scrollbar-3dlight-color: #ffffff;scrollbar-darkshadow-color: #ffffff;scrollbar-track-color: #f1f1f1;scrollbar-arrow-color: #333333;}" +
			"</style>";
	
	Html =	"<html><head>" + bodyStyle + "<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'></head>" +
			"<body style='word-wrap:break-word;word-break:break-all;background-color:#ffffff;' topmargin='0px' rightmargin='0px' bottommargin='0px' leftmargin='0px'>" +
			addhead +' '+ OldHtml
			"</body></html>";
	
	textWindow.document.open();
	textWindow.document.write(Html);
	textWindow.document.close();
	textWindow.document.designMode = 'on'; 
	textWindow.focus();
	thisForm.msgSbst.value = textWindow.document.body.innerHTML;	
	
	chkBytes(thisForm);
}



