<!--
function goMoveList(cpage , rowsPerPage) {

	document.pageNaviForm.currentPage.value = cpage;
	if(rowsPerPage != null) {
		document.pageNaviForm.rowsPerPage.value = parseInt(rowsPerPage);
	}
	
	if(pagingProperty.urlPath != null){
		document.pageNaviForm.action = pagingProperty.urlPath;
	} else if(pagingProperty.searchForm != null) {
		document.pageNaviForm.action = pagingProperty.searchForm.action;
	}else {
		document.pageNaviForm.action = pagingProperty.searchForm.action;
	}
	
	if(pagingProperty.modalName!=null) {
		document.pageNaviForm.target = pagingProperty.modalName;
	}
	document.pageNaviForm.submit();
}

function goMoveListPopUp(cpage , rowsPerPage) {
    
    document.pageNaviForm.currentPage.value = cpage;
    if(rowsPerPage != null) {
        document.pageNaviForm.rowsPerPage.value = parseInt(rowsPerPage);
    }
    
    if(pagingProperty.urlPath != null){
        document.pageNaviForm.action = pagingProperty.urlPath;
    } else if(pagingProperty.searchForm != null) {
        document.pageNaviForm.action = pagingProperty.searchForm.action;
    }else {
        document.pageNaviForm.action = pagingProperty.searchForm.action;
    }
    
    document.pageNaviForm.target = window.name;
    
    document.pageNaviForm.submit();
}
//-->
