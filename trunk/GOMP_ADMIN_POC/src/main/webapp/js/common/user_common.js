
/******** DEFINE PUBLIC*************/
	var _debug = false;//// debug 모드 
	///
	var OMPCOOKIE;///////// 전역 변수 선언 
	var CONTEXT_PATH ="/userpoc";/// 해당 페이지에서 재정의 해서 사용
/******** DEFINE CONSTANT *************/
/************ cross browser flag *******************/
	var _isFF=false; var _isIE=false; var _isOpera=false; var _isKHTML=false; var _isMacOS=false;
	
	if(navigator.userAgent.indexOf('Macintosh')!= -1)_isMacOS=true;
	if((navigator.userAgent.indexOf('Safari')!= -1)||(navigator.userAgent.indexOf('Konqueror')!= -1))_isKHTML=true;
	else if(navigator.userAgent.indexOf('Opera')!= -1){
		_isOpera=true;
		_OperaRv=parseFloat(navigator.userAgent.substr(navigator.userAgent.indexOf('Opera')+6,3));
	}else if(navigator.appName.indexOf("Microsoft")!=-1)_isIE=true;
	else{
		_isFF=true;
		var _FFrv=parseFloat(navigator.userAgent.split("rv:")[1])
	}
	
	var appVer = navigator.appVersion;
/************ cross browser flag *******************/
/**************RegEx *****************************************/
	/// 핸드폰
	var REGEX_HP_HYPHEN = /[01](0|1|6|7|8|9)[-](\d{4}|\d{3})[-]\d{4}$/g
	
	////달력[calendar]
	var REGEX_CALENDAR_HYPHEN = /^(19|20)\d\d([-])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])$/
	var REGEX_CALENDAR_ALL = /^(19|20)\d\d([- /.])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])$/

/**************RegEx *****************************************/	
//////////string util /////////////////////////////
	/**
	 * 문자열 trim - 공백 tab 문자열 모두 치환
	 */
	String.prototype._omp_trim = function(){
	 return this.replace(/&nbsp;/g," ").replace(/(^[ \t]*)|([ \t]*$)/g,"");
	}
	
	function removeComma(str) {
	    return str.replace(/,/gi,"");
	}	
	/**
	 * - Setting Comma
	 */
	function insertComma(str) {
		
	    str = str + "";// 
	    if(str.indexOf(",") != -1){
	    	str.replace(/,/gi,"");
	    }
	    
	    var tmpStr = str+"";
	    var underComma = "";

	    if (str.indexOf(".") >=0) {
	        tmpStr = str.substring(0,tmpStr.indexOf("."));
	        underComma = "."+str.substring(str.indexOf(".")+1, str.length);
	    }
	
	    var len = tmpStr.length;
	    var resultValue = "";
	    var sign = "";
	    if (str.substring(0,1) == "-") {
	       sign = "-";
	       len = len -1;
	       tmpStr = tmpStr.substring(1);
	    }
	    for (var i=0 ; i<len ; i++) {
	        if (i > 0  && (i % 3) == 0 )
	            resultValue = "," + resultValue;
	
	        resultValue = tmpStr.charAt(len - 1 - i) + resultValue;
	    }
	   return sign + resultValue + underComma;
	}

	/**
	 * @desc : 문자열 타입 확인
	*/
	var _VAL_NULL 		= "900";	//NULL
	var _VAL_NUM 		= "901";	//NUMBER
	var _VAL_UPPERCASE 	= "902";	//영문 대문자
	var _VAL_LOWERCASE 	= "903";	//영문 소문자
	var _VAL_KOR 		= "904";	//한글
	var _VAL_ETC 		= "999";	//기타[특문 ... 등등]
	String.prototype.checkValue = function() {
		var str = this;
	
		
		var Msg = "";
		var returnVal = new Array();
		
		if(str==''||str==null||str=="undefined") {
			Msg = "없음";
			returnVal[returnVal.length] = _VAL_NULL;
		}else{
			var start = 0;
			var end = 0;
			for(i=0; i<str.length; i++) {
			
				start = returnVal.length;
				
				if(str.charCodeAt(i) >= 48 && str.charCodeAt(i) <= 57) {
					Msg = "숫자";
					returnVal[returnVal.length] = _VAL_NUM;
				}	
				if(str.charCodeAt(i) >=65 && str.charCodeAt(i) <= 90) {
					Msg = "대문자";
					returnVal[returnVal.length] = _VAL_UPPERCASE;
				}
				
				if(str.charCodeAt(i) >= 97 && str.charCodeAt(i) <= 122) {
					Msg = "소문자";
					returnVal[returnVal.length] = _VAL_LOWERCASE;
				}
				if((str.charCodeAt(i) >= 0x3130 && str.charCodeAt(i) <= 0x318F) || (str.charCodeAt(i) >= 0xAC00 && str.charCodeAt(i) <= 0xD7A3)) {
					Msg = "한글";
					returnVal[returnVal.length] = _VAL_KOR;
				}
				
				end = returnVal.length;
				if(start == end){
					Msg = "기타";
					returnVal[returnVal.length] = _VAL_ETC;
				}
	
			}
			
		}
		
		return returnVal;
	
	}///// END String.prototype.checkValue	
	
	/**
	 * - 영문 / 유니코드 혼용, 1, 2, 4, 8[특문]만 단일 코드 임
	 * 1: 영문 , 2: 숫자 , 3:영문+숫자, 4:한글유니, 5:영문+한글유니, 6:한글유니+숫자, 7: 영문+숫자+한글유니, 8:특문
	 * 9: 영문+특문, 10:숫자+특문, 11:영문+숫자+특문, 12:한글유니+특문, 13:영문+한글+특문, 14:숫자+한글+특문
	 * 15: 영문+숫자+한글유니+특문
	 */
	function isMix(str){
		if(str == null || str == "undefined"){
			return false;
		}
		
		var ret = 0;
		var type = str.checkValue().toString();
		if(false)alert("객체 타입 : "+typeof(type));
		
		/// 대문자 또는 소문자
		if(type.indexOf(_VAL_UPPERCASE) != -1 || type.indexOf(_VAL_LOWERCASE) != -1){
			ret = 1;
		}
		// 숫자
		if(type.indexOf(_VAL_NUM) != -1){
			ret += 2;
		}
		// 한글 유니코드
		if(type.indexOf(_VAL_KOR) != -1){
			ret += 4;
		}
		// 한글 유니코드를 제외한 나머지 코드
		if(type.indexOf(_VAL_ETC) != -1){
			ret += 8;
		}
		
		return ret;
	}//// isMix

	/**
	 * @desc : (unicode)을 2byte로 계산하여 길이를 반환	
	*/
	String.prototype.bytes = function() {
        var str = this;
        var len = 0;
        for (var i=0; i<str.length; i++) len += ( escape(str.charCodeAt(i)).length > 4 ) ? 2 : 1;
        return len;
	}
	
	/**
	 * - 글자수 자르기<br>
	 * - page encode utf-8 일 경우 unicode 도 1byte 처리 됨
	 * @param str : 원본 스트링 소스
	 * @param int liimt[1byte 기준] : 제한 글자 수 
	 */
	function str_cut(str, limit) {
		if(str == null || str == "undefined"){
			return "";
		}
		
		limit = limit||0;
		if(typeof(limit)== "string"){
			limit = parseInt(limit, 10);
		}
			
		var len = str.bytes();
		
		var tmp = "";
		if(limit > len){
			tmp = str;
			return tmp;
		}else{
			tmp = str.substring(0, limit);
			return tmp + "...";
		}
	}	

	/**
	 * - 글자수 자르기<br>
	 * - page encode utf-8 일 경우 unicode 도 1byte 처리 됨
	 * @param str : 원본 스트링 소스
	 * @param int liimt[1byte 기준] : 제한 글자 수 
	 */
	function str_cut_mixcheck(str, limit) {
		if(str == null || str == "undefined"){
			return "";
		}
		var limitHalf = limit||0;
		
		var mixResult = isMix(str);
		
		if(false)alert("[JS:common]mixResult="+mixResult);
		
		if( mixResult == 1 || mixResult == 2 || mixResult == 3 
				|| mixResult == 8 || mixResult == 9 || mixResult == 10
				|| mixResult == 11
		){
			limitHalf = limit;
		}else {
			limitHalf = limit / 2;
		}
		
		////limit = limit||0;
		if(typeof(limitHalf)== "string"){
			limit = parseInt(limitHalf, 10);
		}
		
		var len = str.bytes();
		
		var tmp = "";
		if(limit > len){
			tmp = str;
			return tmp;
		}else{
			tmp = str.substring(0, limitHalf);
			return tmp + "...";
		}
	}	
	
	/**
	 * - 글자수 자르기<br>
	 * - page encode utf-8 일 경우 unicode 도 1byte 처리 됨
	 * @param str : 원본 스트링 소스
	 * @param int liimt[1byte 기준] : 제한 글자 수 
	 */
	function str_cut_without_dot(str, limit) {
		if(str == null || str == "undefined"){
			return "";
		}
		
		limit = limit||0;
		if(typeof(limit)== "string"){
			limit = parseInt(limit, 10);
		}
			
		var len = str.bytes();
		
		var tmp = "";
		if(limit > len){
			tmp = str;
			return tmp;
		}else{
			tmp = str.substring(0, limit);
			return tmp;
		}
	}	
////////string util /////////////////////////////	
	
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
	/***
	selectBoxTitle.onmouseover = function(){
		selectBoxList.style.display = "block";
	}
	
	selectBoxTitle.onmouseout = function(){
		alert("title OUT");
	}

	container.onmouseout = function(){
		selectBoxList.style.display = "none";
	}
	container.onmouseover = function(){
		selectBoxList.style.display = "block";
	}
	***/
}

var selectBoxClick = function(id, name, val){
	if( document.all ){
		document.getElementById(id+"Span").innerText = name;
		document.all[id].value = val;
	}else{
		spanObj = document.getElementById(id+"Span");
		spanObj.textContent = name;
		document.getElementById(id).value = val;
	}
}

function selectBoxInit(id){
	if( document.all ){
		var text = document.getElementById(id+"Span").innerText;
		if( text == null ||  text == "" ){
			document.getElementById(id+"Span").innerText = document.getElementById(id+"Name").innerText;
			document.all[id].value = document.getElementById(id+"HidnVal").innerText;
		}
	}else{
		var text = document.getElementById(id+"Span").textContent;
		if( text == null ||  text == "" ){
			document.getElementById(id+"Span").textContent = document.getElementById(id+"Name").textContent;
			document.getElementById(id).value = document.getElementById(id+"HidnVal").textContent;
		}
	}
}

/* detail page, point */
function selectBoxPoint(containerID) {

	var container = document.getElementById(containerID);
	var selectBoxTitle = container.getElementsByTagName("div").item(1);
	var selectBoxList = container.getElementsByTagName("ul").item(0);
	var selectBoxArrow = container.getElementsByTagName("div").item(2);

	selectBoxArrow.style.cursor ="pointer";
	selectBoxList.style.display = "none";

	selectBoxTitle.onclick = function() {
		selectBoxList.style.display = "block";
	}
	selectBoxArrow.onclick = function() {
		selectBoxList.style.display = "block";
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
}
/** 평가 하기 선택 ***/
function mov_point(imgPath, point) {
	document.getElementById("evalPoint").value = point;
	document.getElementById('imgPoint').src = imgPath+".gif";
	document.getElementById('imgPoint').alt = point+"";
}
/* */

/**************** prototype combo selectbox *******************/
function comboBox(containerID) {
	var self = this;
	this.disabled = false;
	this.container = document.getElementById(containerID);
	this.selectBoxTitle = this.container.getElementsByTagName("span").item(0);
	this.selectBoxList = this.container.getElementsByTagName("ul").item(0);
	this.selectBoxArrow = this.container.getElementsByTagName("div").item(2);

	this.selectBoxArrow.style.cursor ="pointer";
	this.selectBoxList.style.display = "none";
	
	//// disabled 처리 
	(this.container.disabled)?this.disabled=true:this.disabled=false;
	
	if(this.disabled){
		///// disabled action 
	}else{
		///// active action 
		var listStyle = "none";
		this.selectBoxTitle.onclick = function(){
			listStyle = self.selectBoxList.style.display;
			
			if(listStyle == "block")listStyle = "none";
			else listStyle = "block";
			
			self.selectBoxList.style.display = listStyle;
		}
		this.selectBoxArrow.onclick = function() {
			listStyle = self.selectBoxList.style.display;
			
			if(listStyle == "block")listStyle = "none";
			else listStyle = "block";
			
			self.selectBoxList.style.display = listStyle;
		}
		this.selectBoxList.onclick = function(){
			listStyle = "none";
			
			self.selectBoxList.style.display = listStyle;
		}
	}
	
}//////comboBox

/*** setting select value ***/
/**
 * - common.js comboBox(containId)를 상속한다.
 * @param setVal : 설정함 값
 * @param txtId : 값을 설정할 객체 아이디 (optional) 
 * @use : var combo = new comboBox(containId);  combo.setSelectedValue(oId, setVal);
 */
comboBox.prototype.setSelectText = function(text, txtId){
	txtId = (txtId||this.selectBoxTitle.id);
	var objTextElem = this.selectBoxTitle;
	
	if(txtId == null || txtId == "undefined"){
		alert("데이터를 찾을 수 없습니다.");
	}
	if(typeof(objTextElem) == "object" && objTextElem.tagName=="SPAN"){
		objTextElem.innerHTML = text;
	}
}////setSelectText

comboBox.prototype.setSelectValue = function(val){
	this.value = val;
}////setSelectValue
comboBox.prototype.getSelectValue = function(){
	return this.value;
}////getSelectValue
comboBox.prototype.open = function(){
	this.selectBoxList.style.display="block";
}////open
comboBox.prototype.close = function(){
	if(this.isOpen()){
		this.selectBoxList.style.display="none";
	}
}////close
comboBox.prototype.isOpen = function(){
	if(this.selectBoxList.style.display=="block"){
		return true;
	}else{
		return false;
	}
}////isOpen


/**************** prototype combo selectbox *******************/

/******************* Event permit *******************************/
//Number Only- ie / FF
function numOnly(event){
	
	var e = window.event||event;
	var key = e.which||e.keyCode;
	if(_isIE){
		if(key < 48 || key > 57){			
			e.returnValue=false;
			return false;
		}else{
		 	return true;
		 }
	}
	if(_isFF){
		/// 0 : tab, 8:backspace
		if(key==0 || key==8|| key==9){
			return true;
		}
		if(key < 48 || key > 57){			
			e.preventDefault();
			return false;
		}else{
		 	return true;
		}
	}
}/////////////// end numOnly(){}
/******************* Event permit *******************************/


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

/* Layer */
function showPop(popName) { 
	var pop = document.getElementById(popName);
	pop.style.display="block";
}

function closePop(popName,closeName){
	showPop(popName);
	var pop = document.getElementById(popName);
	var close = document.getElementById(closeName);
	pop.style.display="none";
}

/******* window popup resize *******/
/**
 * @param obj : body아래 최상위 wrap object
 * @param width : 증감 너비
 * @param height : 증감 높이
 * @use : e.g>popupResizer.call(this, document.getElementById("popBody"), 5, 45);
**/
function popupResizer(obj, width, height){
	var wrapper = obj;
	if(wrapper == null || wrapper == "undefined"){
		return;
	}else{
		var pH = wrapper.offsetHeight + height;
		
		var pW = wrapper.offsetWidth + width;
		
		var w = screen.width - pW;
	  	var h = screen.height - pH;
	  	
	  	window.moveTo(w/2, h/2);
	  	
	  	setTimeout(function(){
	  		window.resizeTo(pW, pH);	
	  	}, 100);
	}
}//////// END resizer


/**** window Popup resize2 ***********/
/**
 * by kimyb;
 * ex) window.onload = winresize;
 */
function getnavigatorType(){
	if( navigator.appName == "Netscape"){
		return "Netscape " + navigator.appVersion.charAt(0);
	}else if (  navigator.appName == "Microsoft Internat Explorer" ){
		if( navigator.appVersion.charAt(0) == "4" ){
			if( navigator.appVersion.indexOf("MSIE 5") != -1 ){
				return "ie 5";
			}else if( navigator.appVersion.indexOf("MSIE 6") != -1 ){
				return "ie 6";
			}else if( navigator.appVersion.indexOf("MSIE 7") != -1 ){
				return "ie 7";
			}else{
				return "ie 4";
			}
		}else if ( navigator.appVersion.charAt(0) == "5"){
			return "ie 5";
		}
	}else{
		return "";
	}
}


/**
 * 주어진 Time 과 y년 m월 d일 차이나는 Time을 리턴

 * ex) var time = form.time.value; //'20000101'
 *     alert(shiftTime(time,0,0,-100));
 *     => 2000/01/01 00:00 으로부터 100일 전 Time
 */
function shiftDateUserPoC(time,y,m,d, dele) { //moveTime(time,y,m,d)
    var date = toDateObjectUserPoC(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return toDateStringUserPoC(date, dele);
}

/**
 * 현재 시각을 Time 형식으로 리턴

 */
function getCurrentTimeUserPoC() {
    return toTimeStringUserPoC(new Date());
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toDateObjectUserPoC(time) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);

    return new Date(year,month,day);
}

/**
 * 현재 시각을 Date 형식으로 리턴

 */
function getCurrentDateUserPoC(dele) {
    return toFormatStringUserPoC(toTimeStringUserPoC(new Date()), "-");
}

/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeStringUserPoC(date) { //formatTime(date)
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
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toDateStringUserPoC(date, dele) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }

    return ("" + year + dele + month + dele + day)
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toFormatStringUserPoC(time, dele) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2); // 1월=0,12월=11
    var day   = time.substr(6,2);

    return ("" + year + dele + month + dele + day)
}


/*
front 주문/배송조회 - 검색 기간(1주일, 15일, 1달, 3달) 구하기 - NO.2 (김성엽 victor@uzen.net 추가)
*/

function setOrderSearchDateUserPoC(term, start, end) {

	// 오늘...
	if(term == "today") {
		start.value = shiftDateUserPoC(getCurrentTimeUserPoC(), 0, 0, 0, "-");
		end.value = getCurrentDateUserPoC("-");
	}
	// 1주일 이면...
	if(term == "7day") {
		start.value = shiftDateUserPoC(getCurrentTimeUserPoC(), 0, 0, -7, "-");
		end.value = getCurrentDateUserPoC("-");
	}
	// 15일 이면...
	if(term == "15day") {
		start.value = shiftDateUserPoC(getCurrentTimeUserPoC(), 0, 0, -15, "-");
		end.value = getCurrentDateUserPoC("-");
	}
	// 1개월 이면...
	if(term == "1month") {
	    start.value = shiftDateUserPoC(getCurrentTimeUserPoC(), 0, -1, 0, "-");
		end.value = getCurrentDateUserPoC("-");
	}
	// 3개월 이면...
	if(term == "3month") {
	    start.value = shiftDateUserPoC(getCurrentTimeUserPoC(), 0, -3, 0, "-");
		end.value = getCurrentDateUserPoC("-");
	}
	// 6개월 이면...
	if(term == "6month") {
	    start.value = shiftDateUserPoC(getCurrentTimeUserPoC(), 0, -6, 0, "-");
		end.value = getCurrentDateUserPoC("-");
	}
	// 12개월 이면...
	if(term == "12month") {
	    start.value = shiftDateUserPoC(getCurrentTimeUserPoC(), 0, -12, 0, "-");
		end.value = getCurrentDateUserPoC("-");
	}
	/*******************************************************/
}

/* layer swap */
function tabSwap(sw) {
	for (i = 1; i < 7; i++) {
		if (sw == i) {  
			document.getElementById('tab01_0'+i).style.display='';
		} else {
			if(document.getElementById('tab01_0'+i) != null)
				document.getElementById('tab01_0'+i).style.display='none';
		}
	}
}
/* layer swap */

/* 090506 RHJ 추가 */
function select(num) {
	var obj = document.getElementById("select" + num);
	if (obj.style.display == "none"){
		obj.style.display = "block";
	} else {
		obj.style.display = "none";
	}
}

/// select box 숨기기
// @param list : comboBox 객체 명 .. 리스트
// @param delim : comboBox 생성 parent id 일부 .. select_box1/ select_box1_text.. 에서 select_box 만 사용
// @use : document.body.onclick = function(){comboHide("combo,combo2,combo3","SELECT_BOX");}; Or document.body.onclick = function(){comboHide("combo","SELECT_BOX");calendarHide("combo2","SELECT_BOX");};
function comboHide(list, delim, event){
	

	var lstCo = list||"combo";
	lstCo = lstCo.replace(/\s/gi,"");
	
	var co_delim = delim||"SELECT_BOX";
	var targetArr = lstCo.split(","); 
	var e = window.event||event;
	var srcElem = (_isIE?e.srcElement:e.target);
	var envId = srcElem.id||"";
	var envbox = srcElem;

	if(envId==""){
		
		var envParent = envbox.parentNode;
		if(envbox.tagName.toUpperCase() == "IMG"){
			
			/// 해당 객체외 예외적으로 적용할 객체
			if(envParent.className == "select_right" || envParent.className == "planeYear"){
				//////  박스 style class 명 , id,name를 갖고 있지 않음...
				return true;
			}else{
				
				for(var i = 0; i < targetArr.length; i++){
					eval("if("+targetArr[i]+".isOpen() ){"+targetArr[i]+".close();}");
				}
			}
		}else{
			/// 해당 객체외 예외적으로 적용할 객체
			if(envbox.className == "select_right" || envbox.className == "planeYear"){
				//////  박스 style class 명 , id,name를 갖고 있지 않음...
				return true;
			}else{
				
				for(var k = 0; k < targetArr.length; k++){
					eval("if("+targetArr[k]+".isOpen() ){"+targetArr[k]+".close();}");
				}
			}
		}
	}else{
		
		if(false)alert("envId.indexOf(select_box)=="+envId.indexOf("select_box")+",  envId ==="+envId);//debug
		
		if(envId.toUpperCase().indexOf(co_delim) == -1){ 
			if(targetArr != ""){
				if(targetArr.length > 1){
					for(var j = 0; j < targetArr.length; j++){
						eval("if("+targetArr[j]+".isOpen() ){"+targetArr[j]+".close();}");
					}
				}else{
					eval("if("+targetArr.toString()+".isOpen() ){"+targetArr.toString()+".close();}");
				}
			}
		}
	}
	
	return true;
}//// end comboHide()


/*** move main page ******/
/**
 * @param ctxPath : contextPath 
 * @param mainPath : main page path 
 */
function goMainPage(ctxPath, mainPath){
	////var path = (mainPath!=null)?mainPath:"/index.jsp";
	var path = (mainPath||"/index.jsp");
	location.href= ctxPath + path;
}
/*** move main page ******/
/**
 * - 페이지 이동
 * @param ctxPath : contextPath 
 * @param movePath : move page path 
 */
function movePage(ctxPath, movePath){
	var path = (movePath||"/index.jsp");
	location.href= ctxPath + path;
}


///////////////////// Array User Define ////////////////////

/**
 * @desc : 배열 포함 원소 인지. 확인.. 
 * @return : true / false 
*/
Array.prototype.containValue = function(arg_value){
	  var array = this;
	  for(var i=0; i<array.length; i++){
		    if(array[i] == arg_value){
		      	return true;
		    }
	  }
	  return false;
}

/**
 * @desc : 배열 포함 원소 index 찾기
 * @return : 배열내부 인텍스 / -1
*/
Array.prototype.findIndex = function(arg_value){
	  var array = this;
	  var i = 0;
	  for( i=0; i<array.length; i++){
		    if(array[i] == arg_value){
		      	break;
		    	//return i;
		    }
		    ///찾는 값이 없을 경우 
		    if(i+1 == array.length){
		    	return -1;
		    }
	  }
	  return i;
}

/**
 * @desc : 배열  원소 제거
*/
Array.prototype.removeAt = function(idx){
	var array = this;
  	if(typeof(idx)=="string")idx = parseInt(idx,10);
  	
	for(var i=idx;i<array.length;i++){
 		array[i] = array[i+1];
 	}
 	array.length--;
}
///////////////////// Array User Define ////////////////////

/////////////////// COOKIE START//////////////////
	
	function getOMPCookie(exp, expType){
		if(OMPCOOKIE==null || OMPCOOKIE=="undefined" || OMPCOOKIE=="" ){
			if(typeof(OMPCOOKIE) != "object"){
				OMPCOOKIE = new OMPJHCookies(exp, expType);
			}
		}
		
		return OMPCOOKIE;
	}

	/**
	* - 쿠키 .....
	* @use : var cookie = new OMPJHCookies(10); 
	* @param expire(required) : 유효기간지정 
	**/
	function OMPJHCookies(expire, expType){
		this._debug = false;
		this.date = new Date();
		this.exptype = (expType)?expType:"D";/// D: 일단위, H:시간단위 , M:분단위
		this.validTime = expire;//// 쿠키 유효기간
		this.name = "";
		this.value = "";
		
	
	}

	/**
	* @desc : 쿠키 등록
	* @param cookieName, cookieVal: 입력 받는 값
	* @param date : 선언을 위한 
	**/
	OMPJHCookies.prototype.setCookie = function(cookieName, cookieVal, date){
		this.name = cookieName||"";
		this.value = cookieVal||"";
		date = this.date;
		
		if(this.exptype == "D"){
			date.setDate(date.getDate()+ this.validTime);
		}else if(this.exptype == "H"){
			date.setHours(date.getHours()+ this.validTime)
		}else if(this.exptype == "M"){
			date.setMinutes(date.getMinutes()+ this.validTime);
		}
		
		document.cookie= this.name +'='+escape(this.value)+'; path=/userpoc/;expires='+ date.toGMTString() + ";";
		
		if(this._debug)alert("[debug]setCookie : " + this.getAllCookieValues()); 
	}

	/**
	* @desc : 단일 쿠키값 검색
	**/
	OMPJHCookies.prototype.getCookie = function (cookieName){
		var value = "";
		var allCookies = document.cookie.split('; ');
		var cookieArray = new Array();
		
		for (i=0;i<allCookies.length;i++){
			cookieArray=allCookies[i].split('=');
		
			if (cookieName==cookieArray[0]){
			  value = unescape(cookieArray[1]);
			}
		}
		return value;
	}

	/**
	* @desc : 쿠키명으로 쿠키값 삭제
	**/
	OMPJHCookies.prototype.deleteCookie = function (cookieName){
		document.cookie= cookieName +'= ; path=/userpoc/;expires=; ';
	}

	/**
	* @desc : 모든 쿠키값 삭제
	**/
	OMPJHCookies.prototype.clearAllCookie = function (flag){
		var allCookies = document.cookie.split('; ');
		var cookieArray = new Array();
		var size = allCookies.length;
		
		for (i=0;i<size;i++){
			cookieArray=allCookies[i].split('=');
			document.cookie= cookieArray[0] +'= ; path=/userpoc/;expires=; ';
			if(i+1 == size){
				return true;
			}
		}
		if(true)alert(document.cookie);
	}

	/**
	* @desc : 쿠키명으로 참조하는 배멸 반환 
	* @use : arr['쿠키명'] 참조 
	**/
	OMPJHCookies.prototype.getAllNameCookies = function (){
		var value = new Array();
		var allCookies = document.cookie.split('; ');
		var cookieArray = new Array();
		
		for (i=0;i<allCookies.length;i++){
			cookieArray=allCookies[i].split('=');
			value[cookieArray[0]] = unescape(cookieArray[1]);
		}
		////alert(value['last0']);
		return value;
	}

	/**
	* @desc : 모든 쿠키를 참조하는 배열 반환
	* @param filter : 쿠키 ID filtering
	**/
	OMPJHCookies.prototype.getAllCookieValues = function (filter){
		var ret = new Array();
		var allCookies = document.cookie.split('; ');
		var cookieArray = new Array();
		filter = (filter||"");
		
		for (i=0; i < allCookies.length; i++){
			cookieArray=allCookies[i].split('=');
		
			if(cookieArray[1] == null || cookieArray[1] == "undefined" ||cookieArray[1] == ""){
				//////////// 제외
			}else{
				if(cookieArray[0].indexOf(filter) != -1){
					ret[ret.length] = unescape(cookieArray[1]||"");
				}
			}
			
		}
		
		return ret;
	}
	
	/**
	 * @desc : 모든 쿠키를 참조하는 배열 반환
	 * @param filter : 쿠키 ID filtering
	 **/
	OMPJHCookies.prototype.getAllCookieNames = function (filter){
		var ret = new Array();
		var allCookies = document.cookie.split('; ');
		var cookieArray = new Array();
		filter = (filter||"");
		
		for (i=0; i < allCookies.length; i++){
			cookieArray=allCookies[i].split('=');
			
			if(cookieArray[0] == null || cookieArray[0] == "undefined" ||cookieArray[0] == ""){
				//////////// 제외
			}else{
				if(cookieArray[0].indexOf(filter) != -1){
					ret[ret.length] = (cookieArray[0]||"");
				}
			}
			
		}
		
		return ret;
	}

	/**
	* @desc : 
	* return : 없으면 -1
	**/
	OMPJHCookies.prototype.isCookie = function(cookieName){
		var ret = document.cookie.indexOf(cookieName);
		return ret;
	}
/////////////////// COOKIE END////////////////////


	/**
	 * 현재 스크롤 위치 구하기 
	 */
	var getNowScroll = function(){

		var de = document.documentElement;
		var b = document.body;
		var now = {};

		now.X = document.all ? (!de.scrollLeft ? b.scrollLeft : de.scrollLeft) : (window.pageXOffset ? window.pageXOffset : window.scrollX);
		now.Y = document.all ? (!de.scrollTop ? b.scrollTop : de.scrollTop) : (window.pageYOffset ? window.pageYOffset : window.scrollY);

		return now;
	}


//////////////PC Suite 연동 스크립트 //////////////////////

	// IE 계열과 다른 웹 브라우저 계열을 구분하여 Flash movie 의 이름을 리턴한다.
	// 이 함수는 사용하거나 변경이나 수정할 필요 없이 HTML내에 존재하면 된다
	function thisMovie(movieName){
	
		if (navigator.appName.indexOf("Microsoft") != -1) {
			return window[movieName];
		}else{
			return document[movieName];
		}
	}
	
	//PC Suite에 Command을 전달한다. 전달된 명령의 처리 결과는 
	//function callbackWebPCLauncher(errMsg, value) 함수로 전달된다.
	function requestWebPCLauncher(cmd){
	thisMovie("WebPCLauncher").requestWebPCLauncher(cmd);
	}
	
	//PC Suite 연동 결과
	function callbackWebPCLauncher(errMsg, value){
		switch(errMsg){
			case "TIMEOUT":
				break;
			case "BUSY":
				break;
			case "OK":
				break;
			case "PCSUITE":
				break;
			default:
		}
		alert(errMsg);
	}
	
	
//////// 이미지 에러시 기본 이미지 //////////////////////////////
	var onImgErr76 = function(obj){
		obj.src = "/userpoc/images/no_image/76x76.gif";
	}
	
	var onImgErr40 = function(obj){
		obj.src = "/userpoc/images/no_image/40x40.gif";
	}
	
	var onImgErr68 = function(obj){
		obj.src = "/userpoc/images/no_image/68x68.gif";
	}

	var onImgErr212 = function(obj){
		obj.src = "/userpoc/images/no_image/190x190.gif";
	}
	
	var onImgErr156 = function(obj){
		obj.src = "/userpoc/images/no_image/156x209.gif";
	}	
	var onImgErrDesc = function(obj, ctxPath){
		obj.src = ctxPath+"/images/game/img_temp.gif";
	}	

	var onImgErrBrd = function(obj){
		parentObj = obj.parentNode;
		parentObj.innerHTML = "<p style='height:8px;'>&nbsp;</p>"+obj.alt;
	}
	/**
	 * 상세 페이지 설명 이미지 
	 */
	var onImgErrDesc = function(obj, ctxPath){
		obj.src = ctxPath+"/images/game/img_temp.gif";
	}	
	var onImgErrPhone = function(obj, ctxPath){
		obj.src = ctxPath+"/images/no_image/phone_general.gif";
	}	
	
	
//////이미지 에러시 기본 이미지 //////////////////////////////
	
	
	
	function layerPopupLocation(){
		obj = document.getElementById("gnb_pop_body_new");
		if(obj == null || obj == "undefined"){
			return;
		}else{
			var pH = 350;
			var pW = 800;
			
			var w = (screen.availWidth - pW)/2;
		  	var h = (screen.availHeight - pH)/2;
		  	obj.style.left = w+"px";
		  	obj.style.top = h+"px";
		}
	}
	
	
	/**
	 * 사용법은 pop-up JSP 파일에서 body 에 아래와 같이 사용한다.
	 *  <body onload="winResize();"> 
	 */

	 function winResize()
	 {
	  var popBody = document.getElementById("popBody");
	//  var Dwidth = parseInt(document.body.scrollWidth);
	//  var Dheight = parseInt(document.body.scrollHeight);
	  var Dwidth = parseInt(popBody.offsetWidth);
	  var Dheight = parseInt(popBody.offsetHeight);
	  popBody.style.position="absolute";
	  popBody.style.width = "100%";
	  popBody.style.height = "100%";
	  
	  
	  var divEl = document.createElement("div");	 
	
	  divEl.style.position = "absolute";
	  divEl.style.right = "0px";
	  divEl.style.left = "0px";
	  divEl.style.top = "0px";
	  divEl.style.bottom = "0px";
	  divEl.style.width = "100%";
	  divEl.style.height = "100%";
	  
//	  alert("Dwidth == ["+Dwidth+"] \n Dheight == ["+Dheight+"] \n document.body.offsetWidth == ["+document.body.offsetWidth+"] \n divEl.style.width=["+divEl.style.width+"]\n screen.width=["+screen.width+"] \n document.body.clientWidth == ["+document.body.clientWidth+"]");
	  document.body.appendChild(divEl);
	  
	  /** popup 가운데 맞춤 **/
	  var w = (screen.availWidth - Dwidth)/2;
	  var h = (screen.availHeight - Dheight)/2;
	  window.moveTo(w,h);
	  /** popup 가운데 맞춤 **/
	  
	  window.resizeBy(Dwidth-divEl.offsetWidth, Dheight-divEl.offsetHeight);
	  document.body.removeChild(divEl);
	 }
	 
	 function reviewDetailPageLink(svcGrpCd , dpCatNo, prodId,prodGrdCd,t_top) {

			var resultUrl = "";
			
			var gameUrl  = "/userpoc/game/viewProduct02.omp";
			var multiUrl = "/userpoc/multi/detail.omp";

			var para = "?insDpCatNo=" + dpCatNo  + "&insProdId=" + prodId +"&prodGrdCd=" + prodGrdCd+"&t_top=" + t_top ;
			var multiPara = "?prodId=" + prodId+"&prodGrdCd="+prodGrdCd+"&dpCatNo="+dpCatNo+"&reply=Y";

			if(svcGrpCd != null && dpCatNo != null && prodId !=null){
				if(svcGrpCd=="DP000201"){ //game
					resultUrl = gameUrl + para;
				}else if(svcGrpCd=="DP000203"){ //multi
					resultUrl = multiUrl + multiPara;
				}
				location.href=resultUrl;
			}


		}
	 
	 function detailPageLink(svcGrpCd , dpCatNo, prodId,prodGrdCd,t_top) {

			var resultUrl = "";
			
			var gameUrl  = "/userpoc/game/viewProduct.omp";
			var multiUrl = "/userpoc/multi/detail.omp";

			var para = "?insDpCatNo=" + dpCatNo  + "&insProdId=" + prodId +"&prodGrdCd=" + prodGrdCd+"&t_top=" + t_top ;
			var multiPara = "?prodId=" + prodId+"&prodGrdCd="+prodGrdCd+"&dpCatNo="+dpCatNo;

			if(svcGrpCd != null && dpCatNo != null && prodId !=null){
				if(svcGrpCd=="DP000201"){ //game
					resultUrl = gameUrl + para;
				}else if(svcGrpCd=="DP000203"){ //multi
					resultUrl = multiUrl + multiPara;
				}
				location.href=resultUrl;
			}


		}
	 
	/**
	 * =============================================================================
	 * ======================== 통계 관련 스크립트 START ============================= 
	 * =============================================================================
	 */
	 
	//XTVID 쿠키 존재여부를 확인하여 없을 경우 쿠키를 생성한다.
	 function makeXTVIDCookie() {
	     if (! isXTVID()) {
	         setXTVIDCookie();
	 	}
	 }

	 //XTVID가 쿠키에 존재하는 확인한다.
	 function isXTVID() {
	 	var vid = getXTCookie("XTVID");    
	 	if(vid != null && vid != "") {
	 		return true;
	 	} 
	 	return false;
	 }

	 //주어진 이름의 쿠키값을 얻는다.
	 function getXTCookie(name) {
	     var cookies = document.cookie.split("; ");
	     for (var i=0; i < cookies.length; i++)  {
	         var cPos = cookies[i].indexOf( "=" );
	         var cName = cookies[i].substring( 0, cPos );
	         if ( cName == name ) {
	             return unescape( cookies[i].substring( cPos + 1 ) );
	         }
	     }
	     // a cookie with the requested name does not exist
	     return "";
	 }

	 //XTVID 쿠키를 생성한다.
	 function setXTVIDCookie(){
	 	// 3자리 난수 발생
	     var randomid = Math.floor(Math.random()* 1000);       
	 	
	 	// XTVID =  웹서버 식별문자 (A...Z ) 한자리  + yymmdd (쿠키생성일자)  + hhmmss (쿠키생성시각)  
	     //       +  MMM (쿠키 생성시각 1/1000 초) + RRR (난수)
	     var xtvid = "A" + makeXTVIDValue() + randomid;
	 	expireDate = new Date();
	 	expireDate.setYear(expireDate.getYear()+ 10);

	 	//setXTCookie("XTVID", xtvid, 365*10, "/", "sktmall.net");
	 	setXTCookie("XTVID", xtvid, 365*10, "/", getXDomain());
	 }

	 //XTLID 쿠키를 생성한다.
	 function setXTLIDCookie(userNo){
	     //setXTCookie("XTLID", userNo, -1, "/", "sktmall.net");
	     setXTCookie("XTLID", userNo, -1, "/", getXDomain());
	 }

	 //XTLID 쿠키를 삭제한다.
	 function removeXTCookie(name){
	     //setXTCookie(name, "", 0, "/", "sktmall.net");
	     setXTCookie(name, "", 0, "/", getXDomain());
	 }

	 //주어진 조건으로 쿠키를 생성한다.
	 function setXTCookie(name, value, expires, path, domain){
	     var todayDate = new Date();
	     todayDate.setDate(todayDate.getDate() + expires);
	     var expiresInfo = (expires < 0)? '' : todayDate.toGMTString();
	     document.cookie = name + "=" +escape(value) + ";" + "path=" + path + ";domain=" + domain + ";expires="+ expiresInfo;
	 }

	 //쿠키생성을 위한 도메인을 얻는다.
	 function getXDomain() {
	 	var host = document.domain;
	 	var tokens = host.split('.');
	 	var xdomain = tokens[tokens.length-2] + '.' + tokens[tokens.length-1];	
	 	return (tokens[tokens.length-1].length == 2) ? tokens[tokens.length-3] + '.' + xdomain : xdomain;
	 }

	 //XTVID 값을 생성한다.
	 function makeXTVIDValue() {
	     var str = '';
	     nowdate = new Date();
	     digit = nowdate.getYear();
	     if (digit < 2000) { 
	 		digit = digit - 1900;
	     } else {
	 		digit = digit - 2000;
	 	}
	 	str += paddingNo(digit);

	     digit = nowdate.getMonth() + 1;
	 	str += paddingNo(digit);

	     digit = nowdate.getDate();
	 	str += paddingNo(digit);

	     digit = nowdate.getHours();
	 	str += paddingNo(digit);
	     
	 	digit = nowdate.getMinutes();
	 	str += paddingNo(digit);

	     digit = nowdate.getSeconds();
	 	str += paddingNo(digit);

	     digit = nowdate.getMilliseconds();
	 	if ((digit <= 99) && (digit > 9)) { 
	         str += '0' + digit;
	     } else if (digit <= 9) {
	         str += '00' + digit;
	     } else {
	 		str += '' + digit;
	 	}
	     return str;
	 }

	 //10보다 작은 숫자에 '0'을 채워 리턴한다.
	 function paddingNo(val) {
	 	var st = '';
	 	if (val <= 9) {
	 		st += '0' + val;
	 	} else {
	 		st = '' + val;
	 	}
	 	return st;
	 }

	 //XTVID 쿠키생성 호출
	 makeXTVIDCookie();	 
	 
	/**
	 * ==========================================================================
	 * ======================== 통계 관련 스크립트 END ============================= 
	 * ========================================================================== 
	 */