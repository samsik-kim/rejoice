///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * JS 페이지 공통 환경 변수.
 * gnb.jsp에서 아래 형식으로 사용해서
 * 컨텍스트패스와 이미지 서버 url을 지정해서 담습니다.
 * 필요한 변수들 정의 하시면 됩니다.
 * 
	$(document).ready(function(){
		setContextPath("${pageContext.request.contextPath }");
	});
 */
var env = {
	contextPath : ""
};

function setContextPath(paramContextPath){
	env.contextPath = paramContextPath;
}

/* selectbox */
function selectBox(containerID) {

	var container = document.getElementById(containerID);
	var selectBoxTitle = container.getElementsByTagName("span").item(0);
	var selectBoxList = container.getElementsByTagName("ul").item(0);
	var selectBoxArrow = container.getElementsByTagName("div").item(2);

	var selectBoxList2 = container.getElementsByTagName("ul");
	
	selectBoxArrow.style.cursor ="pointer";
	selectBoxList.style.display = "none";
	
	selectBoxTitle.onclick = function() {
		if(selectBoxList.style.display == "block" ) selectBoxList.style.display = "none";
		else selectBoxList.style.display = "block";
	}
	selectBoxArrow.onclick = function() {
		if(selectBoxList.style.display == "block" ) selectBoxList.style.display = "none";
		else selectBoxList.style.display = "block";
	}
	selectBoxList.onclick = function() {
		selectBoxList.style.display = "none";
	}
	selectBoxTitle.onblur = function() {
		selectBoxList.style.display = "block";
	}
	selectBoxList.onblur = function() {
		selectBoxList.style.display = "none";
	}
	
	selectBoxList.onmouseout = function(){
		selectBoxList.style.display = "none";
	}
	selectBoxList.onmouseover = function(){
		selectBoxList.style.display = "block";
	}
	
}

function selectBoxClick(id, name, val){
	document.getElementById(id+"Span").innerText = name;
	document.all[id].value = val;
}

 

// img over/out
function imageOver(imgs) {
	imgs.src = imgs.src.replace("off.gif", "on.gif");
}
function imageOut(imgs) {
	imgs.src = imgs.src.replace("on.gif", "off.gif");
}

// png 파일 투명 처리 
function setPng24(obj) {
    obj.width=obj.height=1;
    obj.className=obj.className.replace(/\bpng24\b/i,'');
    obj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');"
    obj.src='';
    return '';
}


/**
 * ## jQuery Ajax default 설정. ##
 * - jQuery를 이용한 모든 Ajax call 의 beforeSend에 blockUI를 걸어서 메세지를 띄어줍니다.
 * - Ajax call의 모든결과(stop/error/success)에 blockUI를 해제시켜 줍니다.
 * 
 * ## jQuery POST Ajax call ## 
 * - JSon 형식의 ajax call에는 postJson을..
 * - HTML 형식의 ajax call에는 postRequest를 사용하시면 됩니다.
 * 
 * ## beforeSend에 blockUI를 걸고싶지 않은 경우 ## 
 * 	$.ajax({
		type: "GET",
		url: "URL",
		data: [],
		beforeSend: function(xhr) {},
		success: function(data) {
			// todo
		}
	});
 *	형식으로 beforeSend를 재정의해서 사용하시면 됩니다.
 *
 * ## TIPS ##
 * - 일반 submit 호출 전에 
 * 		$.blockUI({ message: '<h4>잠시만 기다려 주세요.</h4>' });
 *   위의 구문을 사용하시면.. 이중호출 방지 및 처리중 메세지 띄우는 효과가 있답니다~~ ㅋㅋ
 *
 *	사용에 문의 있으시면 언제든 물어보셔도 됩니다~
 */
//$().ajaxStop($.unblockUI); 
//$().ajaxError($.unblockUI); 
//$().ajaxSuccess($.unblockUI); 

//$.ajaxSetup({
//	global: true,
//    beforeSend: function(xhr) { 
//		$.blockUI({ message: '<h4>잠시만 기다려 주세요.</h4>' });  
//	} ,
//	success: function(data) {
//		$.unblockUI();
//	}
//});	

$.postJSON = function(url, data, callback) {
	$.post(url, data, callback, "json");
};

$.postRequest = function(url, param, callback) {
	$.post(url, param, callback, "html");
};

/**
 * form searilize 후 json object로 반환
 * ex) $("#formName").serializeObject();
 */
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


/**
 * Object value 의 넘버 체크.
 * @param {Object} str
 */
function checkNumber(obj){
	var valueString = obj.value;
	var temp = '';
	for(i = 0; i<valueString.length; i++){
		code = valueString.charCodeAt(i);
		if(code > 47 && code < 58) temp += valueString.charAt(i);
	}
	obj.value = temp;
}

/**
 * 스트링이 한글인지 확인.
 * @param {String} str 문자열
 * @return {Boolean} 한글이면 true
 */
function isKorean(str){		
	var i;
	var ch;
	var retval = true;
	for (i=0;i<str.length;i++) {
		ch = escape(str.charAt(i)); 

		if (strCharByte(ch) != 2) {
			retval = false;
		}
	}
	return retval;
}

/**
 * 스트링 바이트 크기 반환.
 * @param {String} chStr 	문자열
 * @return {Number} 한글이면 2, 아니면 1
 */
function strCharByte(chStr) {
	if (chStr.substring(0, 2) == '%u') {
		if (chStr.substring(2,4) == '00')
			return 1;
		else
			return 2;        //한글
	} else if (chStr.substring(0,1) == '%') {
		if (parseInt(chStr.substring(1,3), 16) > 127)
			return 2;        //한글
		else
			return 1;
	} else {
			return 1;
	}
}

/**
 * 주어진 길이를 초과할 경우 입력값을 붙여 반환. (한글 고려)
 * @param {String} tail 	문자열
 * @return {Number} len
 */
function cutStr(str, limit, tail){
	var tmpStr = str;
	var byte_count = 0;
	var len = str.length;

	for(i=0; i<len; i++){
		byte_count += chr_byte(str.charAt(i));
		if(byte_count == limit-1){
			if(chr_byte(str.charAt(i+1)) == 2){
				tmpStr = str.substring(0,i+1);
			}else {
				tmpStr = str.substring(0,i+2);
			}
			break;
		}else if(byte_count == limit){
			tmpStr = str.substring(0,i+1);
			break;
		}
	}
	return tmpStr+tail;
}
function chr_byte(chr){
	if(escape(chr).length > 4)
		return 2;
	else
		return 1;
}


/**
 * 스트링 널 체크.
 * @param {String} str
 */
function isNull(str){
	if(str == null) return true;
	return !(str.replace(/(^\s*)|(\s*$)/g, ""));
}

/**
 * popup window open 시 사용되며, 기본적으로 toolbar 및 status 등이 없는 간결한 window open시 사용한다.
 * URL : page url, width : window width, height : window height, scrl : scroll 여부(true or false), win_nm : window name
 */
function openCenteredWindow(URL, width, height, scrl, win_nm) {

	var left = parseInt((screen.availWidth/2) - (width/2));
	var top = parseInt((screen.availHeight/2) - (height/2));
	var windowFeatures ="'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars="+scrl+",resizable=no,copyhistory=no";
	windowFeatures  += ",width=" + width + ",height=" + height;
	windowFeatures  += ",left=" + left + ",top=" + top;
	windowFeatures  += ",screenX=" + left + ",screenY=" + top + "'";
	return window.open(URL, win_nm, windowFeatures);

}


/**
 * 팝업 레이어 생성.
 * @param {String} layerId
 */
function createPopupLayer(layerId){
	var layerHtml = "";

	layerHtml += "<div id=\"" + layerId + "\" class=\"popup_area\" style=\"position:absolute; display:none;\">";
	layerHtml += "<div class=\"popup_top\"><img src=\"" + env.contextPath + "/images/popup/box_top.gif\" alt=\"\" width=\"450\" /></div>";
	layerHtml += "<div class=\"popup_content_bg\">";
	layerHtml += "<div id=\"" + layerId + "_body\" class=\"pop_up\"></div>";
	layerHtml += "</div>";
	layerHtml += "<div class=\"popup_bottom\">";
	layerHtml += "<img src=\"" + env.contextPath + "/images/popup/box_bottom.gif\" alt=\"close\" usemap=\"#Map\" width=\"450\" />";
	layerHtml += "<map name=\"Map\" id=\"Map\"><area shape=\"rect\" coords=\"356,1,406,17\" href=\"#\" onclick=\"javascript:closePopupLayer('" + layerId + "'); return false;\"/></map>";
	layerHtml += "</div>";
	layerHtml += "</div>";
	
	$("#layerArea").append(layerHtml);
}

/**
 * 레이어 팝업.
 * @param {String} targetLayerId  타겟 레이어 id
 * @param {String} parentLayerId  타겟 레이어의 parent 레이어 Id
 */
function showPopupLayer(targetLayerId, parentLayerId){	
    var targetLayer = $("#"+targetLayerId);
	var layerTop = (screen.height - targetLayer.height()) / 2 - 150;    // 아래로 치우친 경향이 있어서 150 줄임
	var layerLeft = (screen.width - targetLayer.width()) / 2;
	
	var zindex = 10;
	if(parentLayerId){
		var parentLayer = $("#"+parentLayerId);
		if(parentLayer.css("z-index") != "auto"){
			zindex = parseInt(parentLayer.css("z-index")) + 10;
		}
	}

	targetLayer.css("top", layerTop);
	targetLayer.css("left", layerLeft);
	targetLayer.css("z-index", zindex);
	targetLayer.fadeIn("slow");	
	
	setDimm(targetLayerId, true);
}

/**
 * 팝업 레이어 닫기
 * @param {String} targetLayerId  타겟 레이어 id
 */
function closePopupLayer(targetLayerId){
	$("#"+targetLayerId).fadeOut("slow");	
	$("#"+targetLayerId).remove();	
	setDimm(targetLayerId, false);
}

/**
 * 타겟 레이어의 딤드처리 설정/해제.
 * @param {String} targetLayerId	타겟 레이어 id
 * @param {Boolean} falg 			true/false
 */
function setDimm(targetLayerId, flag){
	if(flag){
		var htmlStr = "<div id=\"temp_"+targetLayerId+"\" class=\"dimmed\"></div>"
		$("#bodyFrame").append(htmlStr);

		if(document.height > document.documentElement.clientHeight){
			$("#temp_"+targetLayerId).css("height", document.height + "px");
		}
		$("#temp_"+targetLayerId).css("z-index", parseInt($("#"+targetLayerId).css("z-index"))-1);
	}else{
		$("#temp_"+targetLayerId).remove();
	}
}


function popLayerAjaxCall(props){	
	$.ajax({
		type: props.type,
		url: props.url,
		data: props.param,
		beforeSend: function(xhr) {},
		success: function(data) {
			createPopupLayer(props.layerId);
			$("#"+props.layerId+"_body").html(data);
			showPopupLayer(props.layerId, props.parentLayerId);
		}
	});
}

function popMemberSearch(searchValue){
	var params = {
		searchValue : isNull(searchValue) ? '' : searchValue,
		searchType : 'member.nameId'
	};
	var props = {
		type : "POST",
		url : env.contextPath + "/memberDev/popMemberSearch.omp",
		param : params,
		layerId : "popMemberSearch",
		parentLayerId : "container"
	};
	popLayerAjaxCall(props);

}


function popup_center(url,w, h, s, r) 
{

width=screen.width;
height=screen.height;

x=(width/2)-(w/2);
y=(height/2)-(h/2);

opt = "left=" + x + ", top=" + y + ", width=" + w + ", height=" + h;
opt = opt + ", toolbar=no,location=no,directories=no,status=no,menubar=no";
opt = opt + ",scrollbars=" + s;
opt = opt + ",resizable=" + r;
window.open(url, "popup", opt);

}


/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toDateObjectAdminPoC(time) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);

    return new Date(year,month,day);
}
/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toDateStringAdminPoC(date, dele) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }

    return ("" + year + dele + month + dele + day)
}
/**
 * 주어진 Time 과 y년 m월 d일 차이나는 Time을 리턴

 * ex) var time = form.time.value; //'20000101'
 *     alert(shiftTime(time,0,0,-100));
 *     => 2000/01/01 00:00 으로부터 100일 전 Time
 */
function shiftDateAdminPoC(time,y,m,d, dele) { //moveTime(time,y,m,d)
    var date = toDateObjectAdminPoC(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return toDateStringAdminPoC(date, dele);
}
/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toFormatStringAdminPoC(time, dele) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2); // 1월=0,12월=11
    var day   = time.substr(6,2);

    return ("" + year + dele + month + dele + day)
}
/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeStringAdminPoC(date) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();
    var hour  = date.getHours();
    var min   = date.getMinutes();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
    if (("" + hour).length  == 1) { hour  = "0" + hour;  }
    if (("" + min).length   == 1) { min   = "0" + min;   }

    return ("" + year + month + day + hour + min)
}
/**
 * 현재 시각을 Time 형식으로 리턴

 */
function getCurrentTimeAdminPoC() {
    return toTimeStringAdminPoC(new Date());
}
/**
 * 현재 시각을 Date 형식으로 리턴

 */
function getCurrentDateAdminPoC(dele) {
    return toFormatStringAdminPoC(toTimeStringAdminPoC(new Date()), "-");
}

/**
 * 연회비 만기일 검색기간 구하기
 * @param term 
 * @param start
 * @param end
 * @return
 */
function setPayDate(term, start, end) {
	if (term < 0) {
		$("#"+start).val(shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, term, "-"));
		$("#"+end).val(getCurrentDateAdminPoC("-"));
	} else if (term > 0) {
		$("#"+start).val(getCurrentDateAdminPoC("-"));
		$("#"+end).val(shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, term, "-"));
	} else {
		$("#"+start).val(getCurrentDateAdminPoC("-"));
		$("#"+end).val(getCurrentDateAdminPoC("-"));
	}
}
/*
front 주문/배송조회 - 검색 기간(1주일, 15일, 1달, 3달) 구하기 
*/
function setOrderSearchDateAdminPoC(term, start, end) {
	
	// 오늘...
	if(term == "today") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, 0, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 1주일 이면...
	if(term == "7day") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -7, "-");
		end.value = getCurrentDateAdminPoC("-");
		
		
	}
	// 14일 이면...
	if(term == "14day") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -14, "-");
		end.value = getCurrentDateAdminPoC("-");
		
	}
	// 21일 이면...
	if(term == "21day") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -21, "-");
		//end.value = getCurrentDateAdminPoC("-");
		end.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -14, "-");
	}
	// 28일 이면...
	if(term == "28day") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -28, "-");
		//end.value = getCurrentDateAdminPoC("-");
		end.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -21, "-");
	}
	// 30일 이면...
	if(term == "30day") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -30, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 35일 이면...
	if(term == "35day") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -35, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 60일 이면...
	if(term == "60day") {
		start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, 0, -60, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 1개월 이면...
	if(term == "1month") {
	    start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, -1, 0, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 2개월 이면...
	if(term == "2month") {
	    start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, -2, 0, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 3개월 이면...
	if(term == "3month") {
	    start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, -3, 0, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 6개월 이면...
	if(term == "6month") {
	    start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, -6, 0, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// 12개월 이면...
	if(term == "12month") {
	    start.value = shiftDateAdminPoC(getCurrentTimeAdminPoC(), 0, -12, 0, "-");
		end.value = getCurrentDateAdminPoC("-");
	}
	// ALL 이면...
	if(term == "ALL") {
	    start.value = "";
		end.value = "";
	}
	/*******************************************************/
}

/**
 * 필수입력 필드 체크.
 * @param filed
 * @param filedName
 * @return
 */
function requiredFiled(filed, filedName) {
	if(filed.value == ''){
		alert(filedName + ' 입력해 주세요.');
		filed.focus();
		return false;
	}
	return true;
}

/**
 * 필수입력 필드 체크.
 * @param filedId
 * @param filedName
 * @return
 */
function requiredFiledById(filedId, filedName) {
	return requiredFiled(document.getElementById(filedId), filedName);
}

/**
 * 숫자만으로 이루어졌는지 체크.
 * @param obj
 * @return
 */
function isNumeric(obj) {
    if(isNaN(obj)) {  
        return false;
    } else {
        return true;
    }
}


function showResultMsg(resultMsg) {
	if(resultMsg != "") {
		alert(resultMsg);
	}
}



//*************************    2009.11.12 월 추가 작업 *************************//

/**
 * 함수명: 하이픈('-')제거함수
 *    
 */
function cmm_del_hyphen(obj) {
	var str =  String(obj.value);

 if (str.length < 1) {
     return "";
 } else {
     var st = "";
     var sp = "-";
     for (var i = 0; i < str.length; i++) {
         if (sp.indexOf(str.substring(i, i + 1)) == -1) {
             st += str.substring(i, i + 1);
         }
     }
     return st;
 }
}

/**
 * 날짜 유효성 체크
 * ex) 
 *        
 *    
 */
function isValidDate(obj){
	var str = cmm_del_hyphen(obj);
	if(str.length != 8 ){
       return false;
	}
      
	if( str.length == 8 )
	{ 
		var yy = Number(str.substring(0,4));
		var mm = Number(str.substring(4,6));
		var dd = Number(str.substring(6,8));
		
		//월 검증
		if( !(mm > 0 && mm < 13) ){
			return false;
		}
		//윤년 검증
		var boundDay = "";

		if(mm != 2){
			var mon=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
			boundDay = mon[mm-1];
		}
		else{
			if (yy%4 == 0 && yy%100 != 0 || yy%400 == 0){
				boundDay = 29;
			}
			else{
				boundDay = 28;
			}
		}
		//일 검증
		if( (dd <= 0) || (dd > boundDay) ){
			return false;
		}	
	}
	return true ;
}


function cmm_is_valid_date_between(s_date, e_date)
{
	var t_s_date = parseInt(cmm_del_hyphen(s_date), 10);
	var t_e_date = parseInt(cmm_del_hyphen(e_date), 10);

	if (isValidDate(s_date) == false)
	{
		alert("날짜 형식에 맞지 않습니다.");
		s_date.focus();
		s_date.select();
		return false;
	}
	if (isValidDate(e_date) == false)
	{		
		alert("날짜 형식에 맞지 않습니다.");
		e_date.focus();
		e_date.select();
		return false;
	}
	
	if (t_s_date > t_e_date)
	{
		alert("선택하신 기간이 잘못 되었습니다.");
		s_date.focus();
		s_date.select();
		return false;
	}
	return true;
}

/**
 * 특수문자 제거
 * @param str
 * @author nefer
 * @return
 */
function removeSpecialChars(obj) {
	var specialChars = /[~!#$^&*=+|:;?"<,.>']/;
	obj.value = obj.value.split(specialChars).join("");
}

function checkRegualaUrl(obj, append, attach){
	var patterns = /(http(s)?:\/\/)?\S+(\.[^(\n|\t|\s|\/))]+)+/gi;
	var data = (append | "") + obj.value + (attach | "");  
	var match = data.split(patterns);
	return (match != null && match.length > 0) ? false : true; 
	
}

/**
 * image fix broken
 * @param img source image
 * @param noImg src
 */
function fixBroken(img, noImg){     
	   img.src = noImg;
	   img.onerror = "";     
	   return true; 
}