<?
/******************************************************************
*  데이터베이스 연결정보    																		  *
*  2005.08.25																							  *
******************************************************************/

/* 데이터베이스 연결정보 - 시작 */
	$HOST = "localhost";
	$ID = "stock";
	$PWD = "stock-web";
	$DB = "stock";


//-----------------기본설정 ---------------------------//
	$dir="/home/stock/public_html/";  //절대디렉토리
	$domain="http://stockinvest.ne.kr";
	$shop_name="씨투투자자문";
	$admin_email="jys3202@hanmail.net";


//----------------- 디비연결 --------------------------//

	if($CONNECT_STATUS != "Active"){
	  	$CONNECT = @mysql_connect($HOST, $ID, $PWD) or die("mysql_connect Error!!! mysql 계정 또는 패스워드를 확인하세요.");
		@mysql_select_db($DB, $CONNECT) or die("mysql_select_db Error!!! mysql DB명을 확인하세요.");

		$CONNECT_STATUS = "Active";
	}

if ($SESSION_STATUS != "Active"){
	
	session_start();

	$SESSION_STATUS = "Active";
}
/* 데이터베이스 연결정보 - 끝 */
?>