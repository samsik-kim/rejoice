// product contents menu click 
// 2011.03.14 by bcpark
	$(function() {
		// $.blockUI({ message: null, overlayCSS:{backgroundColor:'#FFFFFF'} });
		// ====================  Tab Navigation ==========================
		$("#contentsList").click(function(event){
			event.preventDefault();
			document.searchForm.action = env.contextPath+"/product/listContents.omp";
			document.searchForm.submit();
		});
		
		$("#contentsBaseInfo").click(function(event){
			event.preventDefault();
			document.searchForm.action = env.contextPath+"/product/viewContentsBaseInfo.omp" ;
			document.searchForm.submit();
		});
		$("#contentsProductInfo").click(function(event){
			event.preventDefault();
			document.searchForm.action = env.contextPath+"/product/viewContentsProductInfo.omp" ;
			document.searchForm.submit();
		});
		$("#devConts").click(function(event){
			event.preventDefault();
			document.searchForm.action = env.contextPath+"/product/viewDevConts.omp" ;
			document.searchForm.submit();
		});
		$("#saleDevConts").click(function(event){
			event.preventDefault();
			document.searchForm.action = env.contextPath+"/product/viewSaleDevConts.omp" ;
			document.searchForm.submit();
		});
		$("#signDevConts").click(function(event){
			event.preventDefault();
			document.searchForm.action = env.contextPath+"/product/viewSignDevConts.omp" ;
			document.searchForm.submit();
		});
		$("#saleStatHisList").click(function(event){
			event.preventDefault();
			document.searchForm.action = env.contextPath+"/product/viewSaleStatHisList.omp" ;
			document.searchForm.submit();
		});
		$("#productVerifyDetail").click(function(event){
			event.preventDefault();
			try{$("#no").val('1');}catch(e){}
			document.searchForm.action = env.contextPath+"/product/viewProductVerifyDetail.omp" ;
			document.searchForm.submit();
		});
	});