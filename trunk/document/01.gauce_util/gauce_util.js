var _GAUCE_COMMA	= "__@GC__";  //tr parameter로 셋팅하기전 파라미터의 ","를 대체할 문자열
var _GAUCE_SGQT		= "__GSQ__";
var _GUACE_DBQT		= "__GDQ__";

var cgc_check_col		= 'CHECK_FLAG'; //grid row를 선택하기 위한 checkbox 컬럼id
var cgc_status_col		= 'STATUS_TXT'; //grid row의 상태를 표시해주기 위한 컬럼id
var cgc_status_ignore_col	= new Array('CHECK_FLAG','STATUS_TXT');	//grid row의 수정 event를 무시할 컬럼id

if(!String.prototype.replaceAll) {
	
}