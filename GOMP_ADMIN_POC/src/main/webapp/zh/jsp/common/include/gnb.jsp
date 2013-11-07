<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.omp.admin.adminmgr.auth.model.AdSession" %>
<%@ page import="com.omp.admin.adminmgr.auth.model.AdMenuMgrauth" %>
<%@ page import="com.omp.admin.adminmgr.auth.model.AdMgr"%>
<%@ page import="com.omp.admin.common.Constants" %>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
	Logger log = Logger.getLogger("com.omp.admin.adminmgr.auth.adminLeftMenu");
	AdSession adSession = (AdSession)request.getSession().getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
    AdMgr adMgr = (AdMgr)adSession.getAdMgr();
	//String topMenuCode = request.getParameter("topCode");   // 불필요

	//application 의 값을 사용.
	String topMenuCode = (String) session.getAttribute("topMenuCode");

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
		<h1 class="logo">
			<a href="${pageContext.request.contextPath}/adminMgr/loginMain.omp">
			<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/top_logo.gif" alt="" />
			</a></h1>
		<div class="admin_info">
			<!--
			<span>管理者 <strong><%//=adMgr.getMgrNm()%></strong></span>
			-->
			<span><strong><%=adMgr.getMgrNm()%></strong></span>
			<span class="time">最後登入日期 : <%=dateFormat.format(adMgr.getLstAccDts()) %></span>
			<a href="${pageContext.request.contextPath}/adminMgr/adminLogOut.omp" class="btn_s"><strong>退出</strong></a>
		</div>
		<ul class="gnb">
			<%
				if(adSession != null &&  topMenuCode !=null){
					HashMap<String, List<AdMenuMgrauth>> map = adSession.getMenuListMap();
					
					List<AdMenuMgrauth> depth2List = (List<AdMenuMgrauth>) map.get("ROOT");
					if(depth2List != null) {
						for(int k=0;k<depth2List.size();k++){
					    
							AdMenuMgrauth authMgr2 = (AdMenuMgrauth) depth2List.get(k);
						    String menuName = authMgr2.getMenuNm();
						    String menuCode = authMgr2.getMenuId();
						    String url          = "";
						    String leftMenuCode = "";
						    String lastUrl      = "";
						
						    List<AdMenuMgrauth> depth3list = (List<AdMenuMgrauth>) map.get(menuCode);
						    if(depth3list.size()>0){
						    	
						    	AdMenuMgrauth authMgr3 = (AdMenuMgrauth) depth3list.get(0);
						        String menuCode3 = authMgr3.getMenuId();
						        
						        List<AdMenuMgrauth> depth4list = (List<AdMenuMgrauth>) map.get(menuCode3);
						        if(depth4list.size()>0){
						        	AdMenuMgrauth authMgr4 = (AdMenuMgrauth) depth4list.get(0);
						            
						            url = authMgr4.getPageUrl();
						            leftMenuCode = authMgr4.getMenuId();
						        }
						    }
						    
					                          
				            if(url!=null && url.length()>0){
				                if(url.indexOf("?") > 0){
				                    StringBuffer urlBuf = new StringBuffer();
				                    urlBuf.append(url).append("&topCode=").append(menuCode);
				                    urlBuf.append("&leftCode=").append(leftMenuCode);
				                    lastUrl = urlBuf.toString(); 
				                }else{
				                    StringBuffer urlBuf = new StringBuffer();
				                    urlBuf.append(url).append("?topCode=").append(menuCode);
				                    urlBuf.append("&leftCode=").append(leftMenuCode);
				                    lastUrl = urlBuf.toString(); 
				                }
				            }
				                    
						                          
						                          
						    if(topMenuCode.equals(authMgr2.getMenuId())){
						    	out.println( "<li><a href='" + lastUrl +"' style='color: #555555'>" + menuName +"</a></li> ");
						    }else{
						        out.println( "<li><a href='" + lastUrl +"'>" + menuName +"</a></li> ");
						    }
						}
					}
			    }
			%>		
		</ul>