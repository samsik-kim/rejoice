//XTVID ��Ű ���翩�θ� Ȯ���Ͽ� ���� ��� ��Ű�� ���Ѵ�.
function makeXTVIDCookie() {
    if (! isXTVID()) {
        setXTVIDCookie();
	}
}

//XTVID�� ��Ű�� �����ϴ� Ȯ���Ѵ�.
function isXTVID() {
	var vid = getXTCookie("XTVID");    
	if(vid != null && vid != "") {
		return true;
	} 
	return false;
}

//�־��� �̸��� ��Ű���� ��´�.
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

//XTVID ��Ű�� ���Ѵ�.
function setXTVIDCookie(){
	// 3�ڸ� ���� �߻�
    var randomid = Math.floor(Math.random()* 1000);       
	
	// XTVID =  ������ �ĺ����� (A...Z ) ���ڸ�  + yymmdd (��Ű������)  + hhmmss (��Ű��ð�)  
    //       +  MMM (��Ű ��ð� 1/1000 ��) + RRR (����)
    var xtvid = "A" + makeXTVIDValue() + randomid;
	expireDate = new Date();
	expireDate.setYear(expireDate.getYear()+ 10);

	//setXTCookie("XTVID", xtvid, 365*10, "/", "sktmall.net");
	setXTCookie("XTVID", xtvid, 365*10, "/", getXDomain());
}

//XTLID ��Ű�� ���Ѵ�.
function setXTLIDCookie(userNo){
    //setXTCookie("XTLID", userNo, -1, "/", "sktmall.net");
    setXTCookie("XTLID", userNo, -1, "/", getXDomain());
}

//XTLID ��Ű�� �����Ѵ�.
function removeXTCookie(name){
    //setXTCookie(name, "", 0, "/", "sktmall.net");
    setXTCookie(name, "", 0, "/", getXDomain());
}

//�־��� �������� ��Ű�� ���Ѵ�.
function setXTCookie(name, value, expires, path, domain){
    var todayDate = new Date();
    todayDate.setDate(todayDate.getDate() + expires);
    var expiresInfo = (expires < 0)? '' : todayDate.toGMTString();
    document.cookie = name + "=" +escape(value) + ";" + "path=" + path + ";domain=" + domain + ";expires="+ expiresInfo;
}

//��Ű���� ���� �������� ��´�.
function getXDomain() {
	var host = document.domain;
	var tokens = host.split('.');
	var xdomain = tokens[tokens.length-2] + '.' + tokens[tokens.length-1];	
	return (tokens[tokens.length-1].length == 2) ? tokens[tokens.length-3] + '.' + xdomain : xdomain;
}

//XTVID ���� ���Ѵ�.
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

//10���� ���� ���ڿ� '0'�� ä�� �����Ѵ�.
function paddingNo(val) {
	var st = '';
	if (val <= 9) {
		st += '0' + val;
	} else {
		st = '' + val;
	}
	return st;
}

//XTVID ��Ű�� ȣ��
makeXTVIDCookie();