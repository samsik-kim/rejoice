function email_chk()
{
    var t = document.join.join_email.value
	var ValidFlag = false
	var atCount = 0
	var SpecialFlag
	var atLoop
	var atChr
	var BadFlag
	var tAry1
	var UserName
	var DomainName

	if ( t.length > 0 && t.indexOf("@") > 0 && t.indexOf(".") > 0 ) {
		atCount = 0
		SpecialFlag = false

		for( atLoop=1; atLoop<=t.length; atLoop++ ) {
			atChr = t.substring( atLoop, atLoop+1 )
			if ( atChr == "@" ) atCount = atCount + 1

			if ( (atChr >= 32) && (atChr <= 44) ) SpecialFlag = true 
			if ( (atChr == 47) || (atChr == 96) || (atChr >= 123) ) SpecialFlag = true 
			if ( (atChr >= 58) && (atChr <= 63) ) SpecialFlag = true 
			if ( (atChr >= 91) && (atChr <= 94) ) SpecialFlag = true 
		}

		if ( ( atCount == 1 ) && (SpecialFlag == false ) ) {
			BadFlag = false
			tAry1 = t.split("@")
			UserName = tAry1[0]
			DomainName = tAry1[1]
			if ( (UserName.length <= 0 ) || (DomainName.length <= 0 ) ) BadFlag = true
			if ( DomainName.substring( 1, 2 ) == "." ) BadFlag = true
			if ( DomainName.substring( DomainName.length-1, DomainName.length) == "." ) BadFlag = true
			ValidFlag = true
		}
	}
	if ( BadFlag == true ) ValidFlag = false
	return ValidFlag

}

function LengthRange(ch,slen,mlen){
	if (slen > ch.length || ch.length > mlen){
		alert(frm.elements[i].pval+" "+sml+"�� �̻� "+lrg+"�� ���Ϸ� �Է��ϼž� �մϴ�.");
		frm.elements[i].focus();
		return;						
	}
}


//����,����üũ
function isAlphaNumStr(form) 
{ 
  
  for(var i=0; i<form.value.length;i++) { 
	
    if ( isAlphaChr( form.value.charAt(i) ) == true ) continue; 
    if ( isNumChr( form.value.charAt(i) ) == true ) continue;
	alert("������ ���ڸ��� ����ϼ���.");
	form.value="";
	form.focus();

  } 

  return true; 
         
} 

//�������θ� ��������� üũ
function isAlphaChr(ch) 
{ 
        if ( isUpperAlphaChr(ch) == true || isLowerAlphaChr(ch) == true ) 
                return true 
        else 
                return false 
} 

//�빮��üũ
function isUpperAlphaChr(ch) 
{ 
        if ( ch >= 'A' && ch <= 'Z' ) 
                return true 
        else 
                return false 
} 

//�ҹ���üũ
function isLowerAlphaChr(ch) 
{ 
        if ( ch >= 'a' && ch <= 'z' ) 
                return true 
        else 
                return false 
} 

//����üũ
function isNumChr(ch) 
{ 
        if ( ch >= '0' && ch <= '9' ) { 
                return true; 
        } else { 
                return false; 
        } 
} 

//����,���� ����Ȯ��
function Use_AlparNum(str){
	result = true;
	srceng=/[A-z]/gi;
	srcint=/[0-9]/gi;

	seng=str.search(srceng);
	sint=str.search(srcint);

	if (seng==-1 || sint==-1){
	  result = false;
	}
	return result;
}

//�ִ�ũ�⸦ ������ �������� �Ѿ��
function tabtonext(obj,nextobj) 
{ 
        if (obj.value.length == obj.maxLength ) { 
                nextobj.focus(); 
        } 

} 

//������ ��â���� ����
function WinOpen(ref,name,wid,hei,scroll){
	use_scrollbar=scroll ? 'yes' : 'no';
	var window_left = (screen.width-640)/2;
    var window_top = (screen.height-480)/2;
	window.open(ref,name,'width=' + wid + ',height=' + hei +',status=no,scrollbars=' + use_scrollbar + ',top=' + window_top + ',left=' + window_left + '');
}

function Chk_Length(Text) { // ���� ���ٰ������� �Ǵ�.
  var i=0,space=0,Latin=0;
  for(i=0;i<Text.length;i++)   {
    Latin = escape(Text.charAt(i)).length; 
    if(Latin == 6) space++; 
    space++;
  }
  return space;
}

function Text_Limit(TWin,Max) {// �ؽ�Ʈ ����� ���� ����
  //var BackCon;
  Chars = Chk_Length(TWin.value);
  //if(Len) Len.value =(Max)-Chars; // Len�� ����� �� ���� �ִ��� �����ִ� input.^^
                                                 // �־ �׸� ��� �׸�����.
  if(Chars == Max || Chars == (Max-1)) site_input.tmp_contents.value=TWin.value; // 2����Ʈ �ϰ�� �ǳ� �� ���� �ֱ� ������..
  else if(Chars > Max)
  {
   alert('�Է��� '+Max+'�� ������ �����մϴ�.'); 
   TWin.value=site_input.tmp_contents.value;
  }
// �� ǥ�ô� ���� ���� ��ǲ�߿��� ������ ���� �ι�° ��ǲ�� ����ݴϴ�.
// ��) title,id,name,pass,content �̷������� �ִٸ� pass��...
// ����ָ� event.keyCode= 9;�� ���ؼ� content�� ��Ŀ���� ���ϴ�.
}

function chksub(frm){
//ȸ������ �� submit üũ �̰� �ѹ��̸� ���.. ����
//�� �̸��� �Ѱ��ְ� �Ʒ� �ش� üũ�� �ʿ��� �׸� input �ڽ��� �־��ָ� ���.
//Null ==pchk, ���� == lun, ���� == ints, ���� == lens, �ּ� slen  �ִ� blen
	
	var retvalue="ready";

	var frm=eval(frm);
	var num=frm.elements.length;
	srceng=/[A-z]/gi;
	srcint=/[0-9]/gi;

	for (i=0;i<num;i++){
		var str = frm.elements[i].value;
		var sml = frm.elements[i].slen-1+1;
		var lrg = frm.elements[i].blen-1+1;

//NULL üũ
		if (frm.elements[i].pchk=="Y" && str==""){
			alert(frm.elements[i].pval+" �ʼ��׸��Դϴ�.");
			frm.elements[i].focus();
			return;
		}

//����,���� ����Ȯ��
		seng=str.search(srceng);
		sint=str.search(srcint);

		if (frm.elements[i].lun=="Y" && (seng==-1 || sint==-1)){
			  alert(frm.elements[i].pval+" ���� ���� ���� �Է��ϼž� �մϴ�.");
			  frm.elements[i].focus();
			  return;
		}
	}

	retvalue="ok"
	return retvalue;
}

function regchk(frm){
		var frm=eval(frm);
		var result = true;
// �ֹε�Ϲ�ȣ �Է� �˻�
// �ֹι�ȣ ���ڸ� regnum1 ���ڸ� regnum2
      	var chk =0
 
		var yy = frm.regnum1.value.substring(0,2)
		var mm = frm.regnum1.value.substring(2,4)
		var dd = frm.regnum1.value.substring(4,6)
		var sex = frm.regnum2.value.substring(0,1)

	 	if ((frm.regnum1.value.length!=6)||(yy <20||mm <1||mm>12||dd<1)){
    		result = false;
	  	}

	  	if ((sex != 1 && sex !=2 )||(frm.regnum2.value.length != 7 )){
    		result = false;
	  	}   

		// �ֹε�Ϲ�ȣ üũ 

	  	for (var i = 0; i <=5 ; i++){ 
			chk = chk + ((i%8+2) * parseInt(frm.regnum1.value.substring(i,i+1)))
	 	}

	  	for (var i = 6; i <=11 ; i++){ 
        	chk = chk + ((i%8+2) * parseInt(frm.regnum2.value.substring(i-6,i-5)))
 		}

	  	chk = 11 - (chk %11)
  		chk = chk % 10

	  	if (chk != frm.regnum2.value.substring(6,7))
  		{
    		result = false;
	  	}

		return result;
}

/*
//<!-- �Ϸ絿�� â�ݱ� ���� (index�� �ҽ�)-->
//<script language="javascript">
function getCookieVal (offset) {
   var endstr = document.cookie.indexOf (";", offset);
   if (endstr == -1)
      endstr = document.cookie.length;
   return unescape(document.cookie.substring(offset, endstr));
}

function getCookie (name) {
   var arg = name + "=";
   var alen = arg.length;
   var clen = document.cookie.length;
   var i = 0;
   while (i < clen) {
      var j = i + alen;
      if (document.cookie.substring(i, j) == arg)
         return getCookieVal (j);
      i = document.cookie.indexOf(" ", i) + 1;
      if (i == 0) 
         break; 
   }
   return null;
}

//���� �����˾�����
function main_pop_up1() {
  if (getCookie('popup1') != 'deny') {
    window.open('/pop/pop01.html','popup1','width=400, height=400, resizable=yes, scrollbars=no,left=50, top=100');
  }
}

//���� �����˾�����2
function main_pop_up2() {
  if (getCookie('popup2') != 'deny') {
    window.open('/pop/pop02.html','popup2','width=429, height=375, resizable=yes, scrollbars=no,left=460, top=100');
  }
}

//main_pop_up1();
//main_pop_up2();
//</script> 
//<!-- �Ϸ絿�� â�ݱ� �� -->

//<!-- �Ϸ絿�� â�ݱ� ���� (�˾�â��ҽ�) -->
//<script language="javascript">
function setCookie (name, value, expires) {
  document.cookie = name + "=" + escape (value) +"; path=/; expires=" + expires.toGMTString();
}

function Setting(form) {
  var expdate = new Date();
  expdate.setDate(expdate.getDate() + 1);
  if (form.neveropen.checked) {
    setCookie('popup1', "deny", expdate);
  }
  window.close();
}

//</script>
//<!-- �Ϸ絿�� â�ݱ� �� -->
//javascript:Setting(document.notice); �Լ�ȣ��� �������� �����ش�.
*/