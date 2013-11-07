/**
 * isEmail(obj) : 이메일 형식 체크
 *
 */
function isEmail(val) {

	var filter=/^(\w+(?:\.\w+)*)@((?:\w+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
	var valFront = ""; // Email Front Value

	if (val.match("@")) {
		valFront = val.substring(0, val.indexOf("@"));
	}

	var bChar = (valFront.match("-") || valFront.match("_")) // add '_'. '-'

	if(filter.test(val) || bChar) {
		return false;
	}
	else
		return true;
}


/**
 * isChecked(obj)
 *
 * <p>checked 속성을 가진 폼객체의 선택여부를 체크합니다.</p>
 * <p>&nbsp;</p>
 * @param obj checked 속성을 가진 폼객체
 * @return boolean
 */
function isChecked(obj) {

    var isChecked = false;

    if (obj == null) {
        return false;
    }

    if (obj.length == null && !obj.checked) {
        isChecked = false;
    } else if (obj.length == null && obj.checked) {
        isChecked = true;
    } else if (obj.length != null) {
        if (!isChecked) {
            for (i = 0; i < obj.length; i++) {
                if (obj[i].checked) {
                    isChecked = true;
                    break;
                }
            }
        }
    }

    return isChecked;
}




/***********************************************************************
  1. 설명 : 날짜 입력시 "-" 를 붙여준다.
************************************************************************/
function nextPosition(obj,nextObj){
  var cnt   =   0;
  for(var i = 0; i < obj.value.length; i ++)
  {
    if(obj.value.substring(i, i + 1) == '-')
    {
    }else{
        cnt = cnt +1;
    }
  }
  if (cnt == 8){
    nextObj.focus();
  }
}

function OnFocusColor(fld)
{
    fld.style.backgroundColor="#E4F6E8";
}

function LostFocusColor(fld)
{
    fld.style.backgroundColor="";
}
var i;

/***********************************************************************
  1. 설명 : Text에 영문자 소문자를 대문자로 바꿔준다.
************************************************************************/
function js_UpperCase() {
  if(!(event.keyCode < 97 || event.keyCode > 122)) {
    event.keyCode -= 32;
    event.returnValue=true;
  }
}

/***********************************************************************
  1. 설명 : Text에 영문자 대문자를 소문자로 바꿔준다.
************************************************************************/
function js_LowerCase() {
  if(!(event.keyCode < 65 || event.keyCode > 90)) {
    event.keyCode += 32;
    event.returnValue=true;
  }
}

/************************************************************************************

 js_DateType() : 년월일이 같이 있는 Input box 상에서 년월일을 체크하는 함수
                    물론 인자값중에서 type으로 년월일의 구분자를 선택할 수 있다.
                    js_js_DateType(this, "/") | 20000101-->2000/01/01

 사용예      : <input ... onblur = "js_js_DateType(this, "/")">

*************************************************************************************/

function js_DateType(obj, type)
{
   var sDate=obj.value.replace(/(\,|\.|\-|\/|[ ])/g,"");
//   var sFormat="YYYYMMDD";
   var aDaysInMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
   if (sDate.length == 0)
        return;
   if (sDate.length != 8 )
   {
        alert("날짜를 잘못 입력하였습니다.\n날짜형식은 YYYYMMDD입니다.");
        obj.focus();
        return ;
   }
   else
   {
        var iYear=eval(sDate.substr(0,4));
        var iMonth=eval(sDate.substr(4,2));
        var iDay=eval(sDate.substr(6,2));
        var iDaysInMonth=(iMonth!=2)?aDaysInMonth[iMonth-1]:((iYear%4==0 && iYear%100!=0 || iYear % 400==0)?29:28);
        if( (iDay!=null && iMonth!=null && iYear!=null  && iMonth<13 && iMonth>0 && iDay>0 && iDay<=iDaysInMonth) == false )
        {
            alert("다음의 날짜는 없으니 다시 확인하십시요");
            obj.focus();
            return;
        }
        else
        {
            iMonth = (iMonth >=10)? iMonth:"0"+iMonth;
            iDay = (iDay>=10 )? iDay:"0"+iDay;
            obj.value=""+iYear+type+iMonth+type+iDay;
            return true;
        }
   }
}

/************************************************************************************
 js_js_DateType2()      : 년도만 입력하게하는 함수

 사용예                 : <input ... onKeypress = "js_js_DateType2(this)">

*************************************************************************************/

function js_DateType2(obj)
{
    if( obj.value.length == 0 ){ return false; }
    if(eval(obj.value.length != 4 ))
    {
        alert("년도 형식은 YYYY입니다.");
        obj.value = "";
        obj.focus();
        return false;
    }
    if (isNaN(Number(obj.value))) {
        alert("년도는 숫자만 입력하셔야 합니다.");
        obj.value = "";
        obj.focus();
    }
    return true;
}

/************************************************************************************

 js_js_DateType3()      : 년월만 입력하게하는 함수

 사용예                 : <input ... onKeypress = "js_js_DateType3(this)">

*************************************************************************************/

function js_DateType3(obj, type)
{
   var sDate=obj.value.replace(/(\,|\.|\-|\/|[ ])/g,"");
//   var sFormat="YYYYMMDD";
   if (sDate.length == 0)
        return;
   if (sDate.length != 6 )
   {
        alert("잘못 입력하였습니다.\n입력형식은 YYYYMM입니다.");
        obj.value="";
        obj.focus();
        return ;
   }
   else
   {
        var iYear=eval(sDate.substr(0,4));
        var iMonth=eval(sDate.substr(4,2));
        if( (iMonth!=null && iYear!=null  && iMonth<13 && iMonth>0 ) == false )
        {
            alert("잘못 입력하셨습니다. 다시 확인하십시요");
            obj.value="";
            obj.focus();
            return;
        }
        else
        {
            iMonth = (iMonth >=10)? iMonth:"0"+iMonth;
            obj.value=""+iYear+type+iMonth;
            return true;
        }
   }
}


/************************************************************************************


 js_OnlyNumber()        : 숫자만을 입력하기 위한 함수

 사용예                 : <input ... onKeypress = "js_OnlyNumber(this)">

*************************************************************************************/

function js_OnlyNumber(obj)
{
    sFilter="[0-9]";
    if(sFilter)
    {
      var sKey=String.fromCharCode(event.keyCode);
      var re=new RegExp(sFilter);
      // Enter는 키검사를 하지 않는다.
      if(event.keyCode !=9 && event.keyCode!=37 && event.keyCode!=39 && event.keyCode!=8 
      	 && event.keyCode!= 46 && event.keyCode!= 96 && event.keyCode != 97 && event.keyCode != 98
      	 && event.keyCode!= 99 && event.keyCode!= 100 && event.keyCode != 101 && event.keyCode != 102
      	 && event.keyCode != 103 && event.keyCode != 104  && event.keyCode != 105  
      	 && sKey!="\r" && !re.test(sKey)) event.returnValue=false;
	  // Enter 키가 먹지 않게 한다.
      if (event.keyCode == 13){event.returnValue =false;}
    }
}

/************************************************************************************

 js_CheckTel(this)      : 숫자와 ')' ,'-'를 입력하기 위한 함수

 사용예                 : <input ... onBlur = "js_CheckTel(this)">

*************************************************************************************/

function js_CheckTel(obj)
{
    var num = "0123456789-.";
    var resident_num = obj.value;
    for(var i = 0; i < resident_num.length; i ++)
        {
            if(num.indexOf(resident_num.substring(i, i + 1)) < 0)
            {
                alert("숫자 또는 - 를 입력하세요.");
                obj.value = "";
                obj.focus();
                return 0;
            }
        }
        return 1;
}

/************************************************************************************

 js_OnlyNumber4()       : 숫자와'-'를 입력하기 위한 함수

 사용예                 : <input ... onKeypress = "js_OnlyNumber4(this)">

*************************************************************************************/

function js_OnlyNumber4(obj)
{
    sFilter="[0-9]";
    if(sFilter)
    {
      var sKey=String.fromCharCode(event.keyCode);
      var re=new RegExp(sFilter);
      // "Enter"와 "."는 키검사를 하지 않는다.
      if(sKey!="\r" && sKey!="\-" && !re.test(sKey)) {event.returnValue=false;}

      // Enter 키가 먹지 않게 한다.
      if (event.keyCode == 13){event.returnValue =false;}
    }
}

/************************************************************************************

 js_PostCheck()     : 숫자와'-'를 입력하기 위한 함수

 사용예                 : <input ... onKeypress = "js_js_OnlyNumber4(this)">

*************************************************************************************/

function js_PostCheck(obj)
{
   var result = obj.value;
   if(result.length == 0){return false;}
   result = trimChar(result,"-");
   if (!isNum(result)) {
      alert("우편번호는 숫자만 입력하셔야 합니다.");
      obj.value = "";
      obj.focus();
   }
   else if (result.length !== 6 ){
      alert("우편번호는 6자리로 입력하셔야 합니다.('-'제외)");
      obj.value = "";
      obj.focus();
   }
   else {
        obj.value = result;
   }
}

/************************************************************************************

 js_js_DateType() : 년월일이 같이 있는 Input box 상에서 년월일을 체크하는 함수
                    물론 인자값중에서 type으로 년월일의 구분자를 선택할 수 있다.
                    js_js_DateType(this, "/") | 20000101-->2000/01/01

 사용예      : <input ... onblur = "js_js_DateType(this, "/")">

*************************************************************************************/

function js_DateType(obj, type)
{
   var sDate=obj.value.replace(/(\,|\.|\-|\/|[ ])/g,"");
//   var sFormat="YYYYMMDD";
   var aDaysInMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
   if (sDate.length == 0)
        return;
   if (sDate.length != 8 )
   {
        alert("날짜를 잘못 입력하였습니다.\n날짜형식은 YYYYMMDD입니다.");
        obj.value ="";
        obj.focus();
        return ;
   }
   else
   {
        var iYear=eval(sDate.substr(0,4));
        var iMonth=eval(sDate.substr(4,2));
        var iDay=eval(sDate.substr(6,2));
        var iDaysInMonth=(iMonth!=2)?aDaysInMonth[iMonth-1]:((iYear%4==0 && iYear%100!=0 || iYear % 400==0)?29:28);
        if( (iDay!=null && iMonth!=null && iYear!=null  && iYear > 1900 && iYear < 2050 && iMonth<13 && iMonth>0 && iDay>0 && iDay<=iDaysInMonth) == false )
        {
            alert("다음의 날짜는 없으니 다시 확인하십시요");
            obj.value ="";
            obj.focus();
            return;
        }
        else
        {
            iMonth = (iMonth >=10)? iMonth:"0"+iMonth;
            iDay = (iDay>=10 )? iDay:"0"+iDay;
            obj.value=""+iYear+type+iMonth+type+iDay;
            return true;
        }
   }
}



/*************************************************************************
  1. 설명 : 중앙에 새로운 윈도우 생성.
    2. 입력항목(Parameter)
            - url : 링크정보
            - name : 새로운 윈도우 name
            - width : 윈도우넓이
            - height : 윈도우 높이
            - scrollbars : 없음
************************************************************************/
function js_CenterNewWin2(url, name, width, height) {
  var wi = screen.width - width;
  var hi = screen.height - height;

  if( wi < 0 ) wi = 0;
  if( hi < 0 ) hi = 0;

  var info = 'left=' + (wi/2) + ',top=' + (hi/2) + ',width='  + width + ',height=' + height + ',resizable=no, scrollbars=no,menubars=no,status=no';
  var newwin = window.open(url, name, info);
  newwin.focus();
  return newwin;
}

/************************************************************************************

 Checking()     : CheckBox를 Checking 하는 함수.

*************************************************************************************/

function Checking(CheckData,FormName) {
    var checkVal = 0;
    var count ;
    var instance    ="";

    instance= "document."+FormName+"."+ CheckData;
	var evalInst = eval(instance)+"";

	if(evalInst == "undefined"){
            alert('대상이 없습니다');
	}else{
		count   =  eval(instance).length;
		for( i = 0 ; i < count ; i++ ){
			if(eval(instance)[i].checked == true){ checkVal++; }
		}
		if( checkVal == 0 ){
			if(eval(instance).checked == true){
				checkVal = 1;
			}else{
				alert('체크박스를 하나 이상 선택하세요 !!');
			}
		}
	}
    return checkVal;
}



/***************************************************************
  1. 설명 : textarea의 글자수를 체크
    2. 입력항목(Parameter)
            - txtarea : Textarea Name.
            - charlength : 입력가능한 글자수
*************************************************************************/
function js_Chkbyte(txtarea, charlength) {
  var ch = txtarea.value;
  var temp;
  var cnt=0;

  for( k = 0; k < ch.length; k++ )  {
    temp = ch.charAt(k);
    if( escape(temp).length > 4 )
      cnt += 2;
    else
      cnt++;
  }

    if ( cnt <= charlength ) {
        return true;
    } else {
        alert('현재 '+cnt+'byte 입력하셨습니다! \n\n최대 '+ charlength + 'byte까지(한글 2byte) 입력할 수 있습니다!');
        txtarea.focus();
      return false;
    }
}

/************************************************************************************
 js_CheckDigits()       : 3자리마다 컴머(,)를 넣어주는 함수

 사용예                 : <input ... onkeyup = "js_CheckDigits(this)">

*************************************************************************************/

function js_CheckDigits(obj)
{
        var s=obj.value;
        for(j=0; j<s.length; j++)
            s= obj.value.replace(/,/g,"");
    var t="";
    var i;
    var j=0;
    var tLen =s.length;

    if (s.length <= 3 )
    {
        obj.value=s;
        return;
    }

    for(i=0;i<tLen;i++)
    {
       if (i!=0 && ( i % 3 == tLen % 3) )     t += ",";
       if(i < s.length ) t += s.charAt(i);
    }

    obj.value = t;
    return;
}

/************************************************************************************

js_BeforeYear(chkdate)      : 현재 년도 이전의 년도 인지 체크
 사용예                             : <input type="text" name="year" onChange="js_BeforeYear(this);">
                                          ==>> (format: "2003-01-11")
*************************************************************************************/
function js_BeforeYear(chkdate){
    if( chkdate.length != 0 ){
        var currDate = new Date();

        var iYear=currDate.getYear();
        var iMonth=currDate.getMonth()+1;
        iMonth = (iMonth >=10)? iMonth:"0"+iMonth;
        var iDay=currDate.getDate();

        var nowDate=""+iYear+iMonth+iDay;

        if(Number(nowDate) < Number(chkdate.value)){
            alert('현재 이전 날짜만 입력가능합니다.\n\n다시 확인하시고 입력해 주세요.');
            chkdate.focus();
            return false;
        }else{
            return true;
        }
    }
}

/************************************************************************************

js_AfterYear(chkdate)       : 현재 년도 이후의 년도 인지 체크
 사용예                             : <input type="text" name="year" onChange="js_AfterYear(this);">
                                          ==>> (format: "2003-01-11")
*************************************************************************************/
function js_AfterYear(chkdate){
    if( chkdate.length != 0 ){
        var currDate = new Date();

        var iYear=currDate.getYear();
        var iMonth=currDate.getMonth()+1;
        iMonth = (iMonth >=10)? iMonth:"0"+iMonth;
        var iDay=currDate.getDate();

        var nowDate=""+iYear+iMonth+iDay;

        if(Number(nowDate) > Number(chkdate.value)){
            alert('현재 이후 날짜만 입력가능합니다.\n\n다시 확인하시고 입력해 주세요.');
            chkdate.focus();
            return false;
        }else{
            return true;
        }
    }
}

/*************************************************************************
  1. 설명 : 주민번호 체크.
    2. 입력항목(Parameter)
            - preNoRes  : 주민번호 앞 6자리 객체명 => form.preNoRes
            - postNoRes : 주민번호 뒤 7자리 객체명 => form.postNoRes
************************************************************************/
function js_CheckNoRes(preNoRes, postNoRes){

    var str_serial1 = preNoRes;
    var str_serial2 = postNoRes;

    var digit=0
    for (var i=0;i<str_serial1.length;i++){
      var str_dig=str_serial1.substring(i,i+1);
      if (str_dig<'0' || str_dig>'9'){
          digit=digit+1
      }
    }

    if ((str_serial1 == '') || ( digit != 0 )){
      alert('잘못된 주민등록번호입니다.\n\n다시 확인하시고 입력해 주세요.');
      //preNoRes.focus();
      return false;
    }

    var digit1=0
    for (var i=0;i<str_serial2.length;i++){
      var str_dig1=str_serial2.substring(i,i+1);
      if (str_dig1<'0' || str_dig1>'9'){
          digit1=digit1+1
      }
    }

    if ((str_serial2 == '') || ( digit1 != 0 )){
      alert('잘못된 주민등록번호입니다.\n\n다시 확인하시고 입력해 주세요.');
      //postNoRes.focus();
      return false;
    }

    if (str_serial1.substring(2,3) > 1){
      alert('잘못된 주민등록번호입니다.\n\n다시 확인하시고 입력해 주세요.');
      //preNoRes.focus();
      return false;
    }

    if (str_serial1.substring(4,5) > 3){
      alert('잘못된 주민등록번호입니다.\n\n다시 확인하시고 입력해 주세요.');
      //preNoRes.focus();
      return false;
    }

    if ((str_serial2.substring(0,1) > 4) || (str_serial2.substring(0,1) == 0)){
      alert('잘못된 주민등록번호입니다.\n\n다시 확인하시고 입력해 주세요.');
      //postNoRes.focus();
      return false;
    }

// 2004.3.8
// 주충련
// 주민번호를 체크하는 로직 제거
    return true;

    var a1=str_serial1.substring(0,1)
    var a2=str_serial1.substring(1,2)
    var a3=str_serial1.substring(2,3)
    var a4=str_serial1.substring(3,4)
    var a5=str_serial1.substring(4,5)
    var a6=str_serial1.substring(5,6)

    var check_digit=a1*2+a2*3+a3*4+a4*5+a5*6+a6*7

    var b1=str_serial2.substring(0,1)
    var b2=str_serial2.substring(1,2)
    var b3=str_serial2.substring(2,3)
    var b4=str_serial2.substring(3,4)
    var b5=str_serial2.substring(4,5)
    var b6=str_serial2.substring(5,6)
    var b7=str_serial2.substring(6,7)

    var check_digit=check_digit+b1*8+b2*9+b3*2+b4*3+b5*4+b6*5

    check_digit = check_digit%11
    check_digit = 11 - check_digit
    check_digit = check_digit%10

    if (check_digit != b7){
      alert('잘못된 주민등록번호입니다.\n\n다시 확인하시고 입력해 주세요.');
      return false;
    }

  return true;
}

/************************************************************************************
  js_isBusinessNo(regst_no)     : 사업자 등록번호 체크로직
  사용예                            :<input type="text" name="regst_no" size="20"
                                      value="<%=StringUtil.toNNull(VendInfoTt.xcovendemdl.regst_no,"")%>"
                                     mandatory ="0" mandatoryName="사업자등록번호"
                                     onblur="javaScript:js_isBusinessNo(this)">

 *************************************************************************************/
 function js_isBusinessNo(regst_no) {
    Regst_no =regst_no;

    if(Regst_no.length ==10){

      strCk1 = Regst_no.substring(0,3);
      strCk2 = Regst_no.substring(3,5);
      strCk3 = Regst_no.substring(5,10);

        arrCkValue = new Array(10);
        if ( (Number(strCk1)) && (Number(strCk2)) && (Number(strCk3)) ) {
                arrCkValue[0] = ( parseFloat(strCk1.substring(0 ,1))  * 1 ) % 10;
                arrCkValue[1] = ( parseFloat(strCk1.substring(1 ,2))  * 3 ) % 10;
                arrCkValue[2] = ( parseFloat(strCk1.substring(2 ,3))  * 7 ) % 10;
                arrCkValue[3] = ( parseFloat(strCk2.substring(0 ,1))  * 1 ) % 10;
                arrCkValue[4] = ( parseFloat(strCk2.substring(1 ,2))  * 3 ) % 10;
                arrCkValue[5] = ( parseFloat(strCk3.substring(0 ,1))  * 7 ) % 10;
                arrCkValue[6] = ( parseFloat(strCk3.substring(1 ,2))  * 1 ) % 10;
                arrCkValue[7] = ( parseFloat(strCk3.substring(2 ,3))  * 3 ) % 10;
                intCkTemp     = parseFloat(strCk3.substring(3 ,4))  * 5  + "0";
                arrCkValue[8] = parseFloat(intCkTemp.substring(0,1)) + parseFloat(intCkTemp.substring(1,2));
                arrCkValue[9] = parseFloat(strCk3.substring(4,5));
                intCkLastid = ( 10 - ( ( arrCkValue[0]+arrCkValue[1]+arrCkValue[2]+arrCkValue[3]+arrCkValue[4]+arrCkValue[5]+arrCkValue[6]+arrCkValue[7]+arrCkValue[8] ) % 10 ) ) % 10;
                if (arrCkValue[9] != intCkLastid) {
                    alert ("잘못된 사업자등록번호입니다. 다시 확인해 주십시오");
                    return false;
                } else {
                    return true;
                }
            }
    }
}

/*************************************************************************
  1. 설명 : 주민번호,사업자번호 체크.
    2. 입력항목(Parameter)
            - obj  : 자리수에 따라 주민번호,사업자 번호를 체크한다.
************************************************************************/

function Check_Digit(obj)
{
    var result  = obj.value;
    var preNoRes;
    var postNoRes;
    result = trimChar(result, '-');

    if( result.length != 0 ){
        if( result.length == 13 ){

            preNoRes=result.substring(0,6)
            postNoRes=result.substring(6,13)

            if( ! js_CheckNoRes(preNoRes, postNoRes)){
                obj.focus();
                return false;
            }else{
                obj.value=preNoRes+"-"+postNoRes;
            }

        }else if(result.length == 10){

            if( ! js_isBusinessNo(result) ){
                obj.focus();
                return false;
            }else{
                strCk1 = result.substring(0,3);
                strCk2 = result.substring(3,5);
                strCk3 = result.substring(5,10);
                obj.value=strCk1+"-"+strCk2+"-"+strCk3;
            }

        }else{
            alert('잘못된 형식의 번호를 입력하였습니다.\n주민번호 13자리\n사업자번호 10자리');
            return false;
        }
    }
}

/*************************************************************************
  1. 설명 : 주민번호,사업자번호 체크. 상위 사업번호구분에 따라 체크
    2. 입력항목(Parameter)
            - obj  : 자리수에 따라 주민번호,사업자 번호를 체크한다.
            - chkval : 사업번호구분
            - fun_gu : 사업자번호 등록 구분
************************************************************************/

function Check_Digit2(obj,chkfun,fun_gu)
{
    var result  = obj.value;
    var chk_fun = chkfun.value;
    var preNoRes;
    var postNoRes;
    var strCheckFun =   "";
    result = trimChar(result, '-');

    if( result.length != 0 ){
        if( result.length == 13 ){

            preNoRes=result.substring(0,6)
            postNoRes=result.substring(6,13)

            if( ! js_CheckNoRes(preNoRes, postNoRes)){
                alert('잘못된 형식의 번호를 입력하였습니다.');
                obj.value="";
                obj.focus();
                return false;
            }else{
                obj.value=preNoRes+"-"+postNoRes;
                if (fun_gu =="2"){
                    strCheckFun =   "174";
                }else{
                    strCheckFun =   "5AB";
                }
            }
        }else if(result.length == 10){
                if( ! js_isBusinessNo(result) ){
                    alert('잘못된 형식의 번호를 입력하였습니다.');
                    obj.value="";
                    obj.focus();
                    return false;
                }else{
                    strCk1 = result.substring(0,3);
                    strCk2 = result.substring(3,5);
                    strCk3 = result.substring(5,10);
                    obj.value=strCk1+"-"+strCk2+"-"+strCk3;
                    if (fun_gu =="2"){
                        strCheckFun ="58";
                    }else{
                        strCheckFun ="AHP";
                    }
                }
        }else{
            alert('잘못된 형식의 번호를 입력하였습니다.');
            obj.value="";
            obj.focus();
            return false;
        }
    }

    if (strCheckFun != ""){
        onComboChange(chkfun,strCheckFun);
    } else {
        strCheckFun = "";
        onComboChange(chkfun,strCheckFun);
    }

}

/*************************************************************************
  1. 설명 : 주민번호,사업자번호 체크. 상위 사업번호구분에 따라 체크
    2. 입력항목(Parameter)
            - obj  : 자리수에 따라 주민번호,사업자 번호를 체크한다.
            - chkval : 사업번호구분
            - fun_gu : 사업자번호 등록 구분
    3.세관용
************************************************************************/

function Check_Digit4(obj,chkfun,fun_gu)
{
    var result  = obj.value;
    var chk_fun = chkfun.value;
    var preNoRes;
    var postNoRes;
    var strCheckFun =   "";
    result = trimChar(result, '-');

    if( result.length != 0 ){
        if( result.length == 13 ){

            preNoRes=result.substring(0,6)
            postNoRes=result.substring(6,13)

            if( ! js_CheckNoRes(preNoRes, postNoRes)){
                alert('잘못된 형식의 번호를 입력하였습니다.');
                obj.value="";
                obj.focus();
                return false;
            }else{
                obj.value=preNoRes+"-"+postNoRes;
                if (fun_gu =="2"){
                    strCheckFun =   "174";
                }else{
                    strCheckFun =   "5AB";
                }
            }
        }else if(result.length == 10){
                if( ! js_isBusinessNo(result) ){
                    alert('잘못된 형식의 번호를 입력하였습니다.');
                    obj.value="";
                    obj.focus();
                    return false;
                }else{
                    strCk1 = result.substring(0,3);
                    strCk2 = result.substring(3,5);
                    strCk3 = result.substring(5,10);
                    obj.value=strCk1+"-"+strCk2+"-"+strCk3;
                    if (fun_gu =="2"){
                        strCheckFun ="58";
                    }else{
                        strCheckFun ="AHP";
                    }
                }
        }else{
            alert('잘못된 형식의 번호를 입력하였습니다.');
            obj.value="";
            obj.focus();
            return false;
        }
    }
    if (strCheckFun != ""){
        chkfun.value = strCheckFun
    } else {
        strCheckFun = "";
        chkfun.value = "";
    }

}

/*************************************************************************
  1. 설명 : 사업자번호 체크.
    2. 입력항목(Parameter)
            - obj  : 사업자만을 번호를 체크한다.
************************************************************************/

function Check_Digit3(obj)
{
    var result  = obj.value;
    var preNoRes;
    var postNoRes;
    result = trimChar(result, '-');
    if( result.length == 0 ){return false;};
    if( result.length == 10 ){
        if( ! js_isBusinessNo(result) ){
            obj.value='';
            obj.focus();
            return false;
        }else{
            strCk1 = result.substring(0,3);
            strCk2 = result.substring(3,5);
            strCk3 = result.substring(5,10);
            obj.value=strCk1+"-"+strCk2+"-"+strCk3;
        }
    }else{
        alert('잘못된 형식의 번호를 입력하였습니다.\n사업자번호 10자리');
        obj.value='';
        obj.focus();
        return false;
    }
}






function getDateTime(fld)
{
    var runTime = new Date();
    timePortion = new Array;
    timePortion[0] = runTime.getFullYear();
    timePortion[1] = runTime.getMonth()+1;
    if (timePortion[1]<10){
        timePortion[1] = "0"+timePortion[1];
    }
    timePortion[2] = runTime.getDate();
    if (timePortion[2]<10){
        timePortion[2] = "0"+timePortion[2];
    }
    timePortion[3] = runTime.getHours();
    if (timePortion[3]<10){
        timePortion[3] = "0"+timePortion[3];
    }
    timePortion[4] = runTime.getMinutes();
    if (timePortion[4]<10){
        timePortion[4] = "0"+timePortion[4];
    }
    timePortion[5] = runTime.getSeconds();
    if (timePortion[5]<10){
        timePortion[5] = "0"+timePortion[5];
    }
    fld.value = timePortion[0] + "-" +  timePortion[1] +"-" + timePortion[2]+" "+ timePortion[3]+":"+ timePortion[4]+":"+ timePortion[5];
}
function getRtnDateTime()
{
    var runTime = new Date();
    timePortion = new Array;
    timePortion[0] = runTime.getFullYear();
    timePortion[1] = runTime.getMonth()+1;
    if (timePortion[1]<10){
        timePortion[1] = "0"+timePortion[1];
    }
    timePortion[2] = runTime.getDate();
    if (timePortion[2]<10){
        timePortion[2] = "0"+timePortion[2];
    }
    timePortion[3] = runTime.getHours();
    if (timePortion[3]<10){
        timePortion[3] = "0"+timePortion[3];
    }
    timePortion[4] = runTime.getMinutes();
    if (timePortion[4]<10){
        timePortion[4] = "0"+timePortion[4];
    }
    timePortion[5] = runTime.getSeconds();
    if (timePortion[5]<10){
        timePortion[5] = "0"+timePortion[5];
    }
    return timePortion[0] + "-" +  timePortion[1] +"-" + timePortion[2]+" "+ timePortion[3]+":"+ timePortion[4]+":"+ timePortion[5];
}



//숫자만 입력가능하게 
function checkNumber(obj, value) {

    var regExp = /[^0-9]+/g;
    
    if(regExp.test(value)) {
        
//        alert("'숫자' 만 입력 가능합니다.");
        
        var returnStr = "";
        
        for(i = 0; i < value.length; i++) {
            if(value.charAt(i) >= '0' && value.charAt(i) <= '9') {
                returnStr += value.charAt(i);
            }
        }
        
        obj.value = returnStr;
        obj.focus();
    } 
}
// 주어진 문자열이 수치data인지 검사
function isNum(src)
{
  var dst = trimChar(src, ",");
  dst = trim(dst, ' ');

  return !isNaN(Number(dst));
}

/******************************************************************************
    Description     : 문자의 길이를 계산하여줌. 한글은 2자 영문,숫자는 1자
    Parameter       :
    return value    :

*******************************************************************************/
function textFieldCheckLen(fld)
{
    var temp;
    var num;
    var len;
    num = 0;
    len = fld.value.length;

    for(k=0;k<len;k++) {
        temp = fld.value.charAt(k);

        //내장함수 escape를 통해 그 글자의 길이가 4보다 크면 한글이므로 2를 더한다.
        if(escape(temp).length > 4)
        num += 2;
        else
        num++;

    }
    return num;
}

/******************************************************************************
    Description     : 해당 길이 이상의 문자는 textfield에 들어갈수 없다
    Parameter       :
    return value    :

*******************************************************************************/
function textFieldChk(fld,LimitCnt)
{
    var intCnt = 0 ;
    var textValue = "";
    var isSpecheck = false;


    intCnt = textFieldCheckLen(fld) ;
    //document.frmlc.oNum.value = intCnt;

    isSpecheck = specialchr(fld.value);
    if (isSpecheck){
        alert("_~!@#$^{}[]?|\` 문자는 입력할수 없습니다.");
        fld.value='';
        fld.focus();
        return false;
    }

    if (intCnt > LimitCnt)
    {
        for (t=0;t< LimitCnt; t++)
        {
            if (escape((fld.value).substr(t,1)).length > 4)
            {
                LimitCnt = LimitCnt -2;
            }
        }
        alert("현재 입력하신 필드는 " + LimitCnt + " 이상 입력불가 합니다.\n입력한 글자수는 :"+intCnt+"\n제한된 글자수 이상의 글자는 자동으로 삭제합니다.\n[**한글한글자는 3글자로 적용**]");
        fld.value = (fld.value).substr(0,LimitCnt);
        fld.focus();
        return false;
    }
    return true;
}

/*
특수문자가 들어오면 막아주는 메소드
특수문자가 들어오면 리턴값을 true로 들어오게 된다....
*/
function specialchr(str) {
    var isChecked   =   false;
    var specialchr = "_~!@#$^{}[]?|\\`";

    for(i = 0; i < str.length; i++) {

        if(-1 == specialchr.indexOf(str.charAt(i) )) {
            isChecked = false;
        }else{
            return true;
        }
    }
    return isChecked;

}



/******************************************************************************
    Description     : 한글 입력을 막는 메소드
    Parameter       :
    return value    :

*******************************************************************************/
function codeChk(fld,LimitCnt)
{
    len = fld.value.length
    if (len > LimitCnt)
    {
        alert("코드부분에는 " + LimitCnt + "이상의 글자를 입력할수 없습니다");
        fld.value = "";
        fld.focus();
        return;
    }
    for (t=0;t< len; t++)
    {
        if (escape((fld.value).substr(t,1)).length > 4)
        {
            alert("코드부분에 한글을 입력하실수없습니다.");
            fld.value = "";
            fld.focus();
            return;
        }
    }
    fld.value = fld.value.toUpperCase()
}
/******************************************************************************
    Description     : 한글 입력을 막는 메소드
    Parameter       :
    return value    :

*******************************************************************************/
function engChk(fld)
{
    len = fld.value.length
    for (t=0;t< len; t++)
    {
        if (escape((fld.value).substr(t,1)).length > 4)
        {
            alert("한글을 입력하실 수 없습니다.");
            fld.value = "";
            fld.focus();
            return;
        }
    }
    fld.value = fld.value.toUpperCase()
}

function idCheck(fld,LimitCnt, name )
{
    len = fld.value.length
    if (len > LimitCnt)
    {
        alert( name + " 부분에는 " + LimitCnt + "이상의 글자를 입력할수 없습니다");
        fld.value = "";
        fld.focus();
        return;
    }
    for (t=0;t< len; t++)
    {
        if (escape((fld.value).substr(t,1)).length > 4)
        {
            alert(name + " 부분에 한글을 입력하실수없습니다.");
            fld.value = "";
            fld.focus();
            return;
        }
    }
}


function formatChk(fld,LimitCnt,name)
{
    len = fld.value.length
    if (len > LimitCnt)
    {
        alert(name + "는 " + LimitCnt + "자만 입력 가능합니다.");
        fld.value = "";
//      fld.focus();
        return;
    }
    for (t=0;t< len; t++)
    {
        if (escape((fld.value).substr(t,1)).length > 4)
        {
            alert( name + "부분에는 한글을 입력하실수없습니다.");
            fld.value = "";
//          fld.focus();
            return;
        }
    }
}

function formatChk2(fld,LimitCnt,name)
{
    len = fld.value.length
    if (len != LimitCnt)
    {
        alert(name + "는 " + LimitCnt + "자만 입력 가능합니다.");
        fld.value = "";
//      fld.focus();
        return;
    }
    for (t=0;t< len; t++)
    {
        if (escape((fld.value).substr(t,1)).length > 4)
        {
            alert( name + "부분에는 한글을 입력하실수없습니다.");
            fld.value = "";
//          fld.focus();
            return;
        }
    }
}



/************************************************************************************

js_BeforeYear(chkdate)      : 이전의 날짜 입력불가
 사용예                             : <input type="text" name="year" onChange="js_BeforeYear(this);">
                                          ==>> (format: "2003-01-11")
*************************************************************************************/
function js_BeforeDate(chkdate,comdate,mes){
    if( chkdate.value.length != 0 && comdate.value.length != 0){

        if(chkdate.value < comdate.value){
            alert(mes+' 항목 이후 날짜만 입력가능합니다.\n\n다시 확인하시고 입력해 주세요.');
            chkdate.value="";
            chkdate.focus();
            return false;
        }else{
            return true;
        }
    }
}

/************************************************************************************

js_AfterYear(chkdate)       : 이후 일자 입력 불가
 사용예                             : <input type="text" name="year" onChange="js_AfterYear(this);">
                                          ==>> (format: "2003-01-11")
*************************************************************************************/
function js_AfterDate(chkdate,comdate,mes){
    if( chkdate.value.length != 0 && comdate.value.length != 0 ){

        if(chkdate.value > comdate.value){
            alert(mes+' 항목 이전 날짜만 입력가능합니다.\n\n다시 확인하시고 입력해 주세요.');
            chkdate.value="";
            chkdate.focus();
            return false;
        }else{
            return true;
        }
    }
}

/************************************************************************************

js_AfterYear(chkdate)       : 오늘 이전 일자 입력 불가
 사용예                             : <input type="text" name="year" onChange="js_AfterYear(this);">
                                          ==>> (format: "2003-01-11")
*************************************************************************************/
function js_toDate(chkdate){
    var js_toDate   =   new Date();
    var temp,allTemp;
    var year,month,day;
    var dateDiv;
    dateDiv = 0;
    var isDayDiffer;
    allTemp = "";


    for (i=0;i< chkdate.value.length ;i++ ){
        temp = chkdate.value.charAt(i);
        if (temp=='-'){
            dateDiv = dateDiv +1;
            if (dateDiv ==1){
                year = allTemp;
                allTemp = "";
            }else if (dateDiv ==2){
                month = allTemp ;
                allTemp = "";
            }else if (dateDiv ==3){
                day = allTemp;
                allTemp = "";
            }else{
                allTemp = "";
            }
        }else{
            allTemp = allTemp +temp;
        }
    }
    day = allTemp;
    var isDay   =   new Date(year,month-1,day);
    isDayDiffer = isDay - js_toDate;
    isDayDiffer = Math.floor(isDayDiffer/(24*3600*1000));
    if( isDayDiffer < -1 ){
        alert('오늘 이전일자는 입력하실수 없습니다.\n\n다시 확인하시고 입력해 주세요.');
        chkdate.value="";
        chkdate.focus();
        return false;
    }
}

/************************************************************************************

js_AfterYear(chkdate)       : 유효기간 1달후로 계산 단 연도가 바뀔경우 12월31이로 입력
 사용예                             : <input type="text" name="year" onChange="js_AfterYear(this);">
                                          ==>> (format: "2003-01-11")
*************************************************************************************/
function js_ExpDate(chkdate,js_ExpDate){



    var isDay   =   new Date(year,month-1,day);
    isDayDiffer = isDay - js_toDate;
    isDayDiffer = Math.floor(isDayDiffer/(24*3600*1000));
    if( isDayDiffer < 0 ){
        alert('오늘 이전일자는 입력하실수 없습니다.\n\n다시 확인하시고 입력해 주세요.');
        chkdate.value="";
        chkdate.focus();
        return false;
    }
}


/************************************************************************************

js_OnlyChar()       : 문자입력만 유효하게 하는 함수
 사용예                             : <input type="text" name="year" onKeypress()="js_ExpDate();">

*************************************************************************************/
function js_OnlyChar(){
    sFilter="[0-9]";
    if(sFilter)
    {
      var sKey=String.fromCharCode(event.keyCode);
      var re=new RegExp(sFilter);
      // Enter는 키검사를 하지 않는다.
      if(re.test(sKey)) event.returnValue=false;
    }
}

/************************************************************************************

js_EmailCheck()     : 이메일주소 체크
 사용예                             : <input type="text" name="year" onBlur()="js_EmailCheck(this);">

*************************************************************************************/
function js_EmailCheck( obj){
    var len = obj.value.length;
    var charater;
    var temp = false;
    var temp1= false;
    if( len == 0 ){ return false; }
    for (t=0;t< len; t++)
    {
        character = escape((obj.value).substr(t,1));
        if (character == "\@")  {temp = true;}
        if (character == "\.")  {temp1 = true;}
    }
    if( !(temp && temp1) ){
        alert("이메일 형식이 아닙니다.");
        obj.focus();
        return false;
    }
    return textFieldChk(obj,35);
}


/******************************************************************************
    Description     : 수식계산에 필요한 함수 
    Parameter       :
    return value    :
*******************************************************************************/

//음수값 체크
function chkSign(obj){
  if(Number(obj.value) < 0){
     obj.value = "";
     obj.focus();
     return false;
  }
  return true;
}

// null값을 space로 세팅
function makeSpace(value){
       if((value == "") ||(value == null) ){
           return "";
       } else {
           return parseInt(value);
       }
}

// space 또는 null값을 0로 세팅
function makeZero(value){
       if((value == "") ||(value == null) ){
           return 0;
       } else {
           return parseInt(value);
       }
}

//용    도 : 텍스트 필드의 분리자를 제거하는 함수
//매개변수 : 텍스트 필드
//사 용 예 : onFocus="removeDel(obj.value)"
  function removeDel( value ) 
  {
     var rxSplit = new RegExp('([\(\),/:-])');
     var sign = '';

    if ( value.charAt(0) == '-') sign = '-';
    do{
        value = value.replace(rxSplit,'');
    }while( rxSplit.test( value ) )

    value = sign + value ;
    //obj.select();
    return;
  }

//수식에 콤마 제거
function removeComma(origin){
  var tempStr = "";
  for(i = 0;i<origin.length;i++){
    if (origin.charAt(i) == ','){
    }else{
      tempStr = tempStr + origin.charAt(i);
    }
  }
  if (trim(tempStr) ==""){
    tempStr = 0;
  }
  return tempStr;
}


/******************************************************************************
 20060522 : 확장자를 체크해서 허용여부 리턴 
*******************************************************************************/
function js_checkFileType(filename){
  var al=new Array('bmp','gif','jpg','jpeg','png','doc','hwp','txt','ppt','xls','pdf','gul','zip','alz'); //허용할 확장자
//  	alert('filename :'+filename);
  //var tmp=filename.split(".")[1].toLowerCase(); //파일명을 . 로 나누어 소문자화
    var stmp = filename.split(".");
    var tmp ='';
    if (stmp.length > 1) {
       tmp = stmp[stmp.length-1].toLowerCase();  
    } else {
      return false;
    }

 //확장자비교 
  for (i=1; i<=al.length; i++)   {
    if (tmp==al[i]) {
       return true;
    }
  }
  return false;
}




function fnKeydown(obj) {
   if(event.keyCode!=8 && getBytes(obj.value) > obj.maxbyte) {
	 obj.value = obj.value.substring(0,obj.value.length - 1);   	  
   	 event.returnValue=false;
   }

}


//문자열의 좌우 공백제거
function trim ( str )
{
	var temp= rtrim(ltrim(str))
	return  temp;
}

//문자열에서 왼쪽공백제거
function ltrim ( str )
{
	//left trim
	var temp=str.replace(/^\s*/,"");
	//alert('left temp :'+temp.length);
	return  temp;
}

//문자열의 좌우 공백제거
function rtrim ( str )
{
	//right trim
	var temp=str.replace(/\s*$/,"");
	//alert('rigth temp :'+temp.length);
	return  temp;
}





//체크박스 전체선택
function gridCheckAll(chkObj) {

	var eleObj;
	var frm = chkObj.form;

	for (var i = 0; i < frm.elements.length; i++) {
		eleObj = frm.elements[i];
		eleObj.checked = chkObj.checked;
	}
}

/****************************************
 *	이미지 업로드 시 이미지의 확장자를 체크한다.
 *	jpg(jpeg), gif, png
 *****************************************/
 function checkImgFormat(file){
	var lastIndex = file.value.lastIndexOf(".");
	var fileFormat = trim(file.value).substring(lastIndex+1, file.value.len).toLowerCase();
	if(fileFormat =="") return;
	if(!(fileFormat == "jpg" || fileFormat == "gif" || fileFormat == "png" || fileFormat == "jpeg"))
	{
		 alert("jpg, gif, png 파일만 지원 됩니다.");
		 file.outerHTML = file.outerHTML;
		 return;
	}
}

/*****************************************************
 *	파일 업로드 시 확장자를 체크, 실행가능한 파일의 업로드를 막는다.
 *	asp, jsp, html, php, exe, com, sh
 *******************************************************/
 function checkFileFormat(file){
	var lastIndex = file.value.lastIndexOf(".");
	var fileFormat = trim(file.value).substring(lastIndex+1, file.value.len).toLowerCase();
	if(fileFormat == "") return;
	if((fileFormat == "asp" || fileFormat == "jsp" || fileFormat == "html" || fileFormat == "php"
		|| fileFormat == "exe" || fileFormat == "com" || fileFormat == "sh" || fileFormat == "htm" ))
	{
		 alert("업로드가 제한된 파일 입니다.");
		 file.outerHTML = file.outerHTML;
		 return;
	}
}

/*****************************************************
 *	자바 스크립트에서 replaceAll을 가능하게 해주는 function.(3개중 아무거나 사용.)
 *	사용예-> replace( something.value , "\n", "<br>")
 *******************************************************/

function replaceAll( originChar, searchChar, replaceChar ){
	return originChar.replace(eval("/"+searchChar+"/g"),replaceChar);
}




/*****************************************************
 * 
 *  숫자의 콤마(comma) 처리.  	
 *	 
 *******************************************************/
function commify(n) {
    var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
    n += '';                          // 숫자를 문자열로 변환

    while (reg.test(n))
        n = n.replace(reg, '$1' + ',' + '$2');

    return n;
}

// ********************************** luffy start
function getCookie (sCookieName) {
    var sName = sCookieName + "=", ichSt, ichEnd;
    var sCookie = document.cookie;
    
    if ( sCookie.length && ( -1 != (ichSt = sCookie.indexOf(sName)) ) ) {
        if (-1 == ( ichEnd = sCookie.indexOf(";",ichSt+sName.length) ) ) {
            ichEnd = sCookie.length;
        }
        
        return unescape(sCookie.substring(ichSt+sName.length,ichEnd));
    }
    
    return null;
}

function deleteCookie (sName) {
    document.cookie = sName + "=" + getCookie(sName) + "; expires=" + (new Date()).toGMTString() + ";";
}



/*****************************************
* 숫자의 소수점 표시 / 음수표현
******************************************/
function js_DecimalNumber(obj)
{
    var sFilter = "([-+]|[0-9]|[.])";
    var vFilter = "^[-+]?[0-9]*[.]?[0-9]*$";
    var sKey=String.fromCharCode(event.keyCode);
    
    if(sFilter)
    {
      var re=new RegExp(sFilter);
      // Enter는 키검사를 하지 않는다.
      if(sKey != "\r" && !re.test(sKey)) {event.returnValue=false;}

      // Enter 키가 먹지 않게 한다.
      if (event.keyCode == 13){
      	event.returnValue =false;
      // BackSpace, Delete키 검사하지 않는다.	
      }else if (event.keyCode == 22 || event.keyCode == 83) {
      	event.returnValue = true;
      }
    }
    
    var reg = new RegExp(vFilter);
    if( !reg.test(obj.value+sKey)) {
    	event.returnValue=false;
    }
}

/*-----------------------------------------------------------
* 숫자만 입력되도록 한다.	
-----------------------------------------------------------*/
function onlyNum() {
	if ((event.keyCode<48)||(event.keyCode>57)) {
		event.returnValue=false;
	}
}



function checkForNumber() {
	  var key = event.keyCode;
	  if(!(key==8||key==9||key==13||key==46||key==144||
	      (key>=48&&key<=57)||key==110||key==190)) {
	      event.returnValue = false;
	  }
	}
    
