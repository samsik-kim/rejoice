<?
/******************************************************************
*  �����ͺ��̽� ��������    																		  *
*  2005.08.25																							  *
******************************************************************/

/* �����ͺ��̽� �������� - ���� */
	$HOST = "localhost";
	$ID = "stock";
	$PWD = "stock-web";
	$DB = "stock";


//-----------------�⺻���� ---------------------------//
	$dir="/home/stock/public_html/";  //������丮
	$domain="http://stockinvest.ne.kr";
	$shop_name="���������ڹ�";
	$admin_email="jys3202@hanmail.net";


//----------------- ��񿬰� --------------------------//

	if($CONNECT_STATUS != "Active"){
	  	$CONNECT = @mysql_connect($HOST, $ID, $PWD) or die("mysql_connect Error!!! mysql ���� �Ǵ� �н����带 Ȯ���ϼ���.");
		@mysql_select_db($DB, $CONNECT) or die("mysql_select_db Error!!! mysql DB���� Ȯ���ϼ���.");

		$CONNECT_STATUS = "Active";
	}

if ($SESSION_STATUS != "Active"){
	
	session_start();

	$SESSION_STATUS = "Active";
}
/* �����ͺ��̽� �������� - �� */
?>