<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.omp.admin.adminmgr.auth.model.AdSession" %>
<%@ page import="com.omp.admin.adminmgr.auth.model.AdMenuMgrauth" %>
<%@ page import="com.omp.admin.common.Constants" %>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	Logger log	= Logger.getLogger( "com.omp.admin.adminmgr.auth.adminLeftMenu" );
	AdSession adminSes	= (AdSession) request.getSession().getAttribute( Constants.ADMIN_AUTH_SESSION_KEY );

	//application 의 값을 사용.
	String topMenuCode	= (String) session.getAttribute( "topMenuCode" );
	String leftMenuCode	= (String) session.getAttribute( "leftMenuCode" );

	if ( topMenuCode != null || leftMenuCode != null )	{
		HashMap<String, List<AdMenuMgrauth>> map	= adminSes.getMenuListMap();
		List<AdMenuMgrauth> depth2List	= (List<AdMenuMgrauth>) map.get( "ROOT" );

		for( int k = 0; k < depth2List.size(); k++ )	{
			AdMenuMgrauth authMgr2	= (AdMenuMgrauth) depth2List.get( k );

		    if ( authMgr2.getMenuId().equals( topMenuCode ) )	{
		        // String menuName2	= authMgr2.getMenuNm();

		        List<AdMenuMgrauth> depth3list	= (List<AdMenuMgrauth>) map.get( topMenuCode );

				for( int i = 0; i < depth3list.size(); i++ )	{
					AdMenuMgrauth authMgr3	= (AdMenuMgrauth) depth3list.get( i );
	
					String menuCode3	= authMgr3.getMenuId();
					String menuName3	= authMgr3.getMenuNm();
					String url3	= authMgr3.getPageUrl();
			        out.println( "<h2><span>" + menuName3 + "</span></h2>" );

					List<AdMenuMgrauth> depth4list	= (List<AdMenuMgrauth>) map.get( menuCode3 );

					out.println( "<ul class=\"s_menu\">" );	  

						for( int j = 0; j < depth4list.size(); j++ )	{			    
							AdMenuMgrauth authMgr4	= (AdMenuMgrauth) depth4list.get( j );
							String menuCode4	= authMgr4.getMenuId();
							String menuName4	= authMgr4.getMenuNm();

							String auth4url	= authMgr4.getPageUrl();
							StringBuffer sb	= new StringBuffer();
/*
							log.info( "[menuCode4] : [" + menuCode4 + "]" );
							log.info( "[menuName4] : [" + menuName4 + "]" );
							log.info( "[auth4url] : [" + auth4url + "]" );
*/
							if ( auth4url.indexOf( "?" ) > 0 )	{
								sb.append( auth4url ).append( "&topCode=" ).append( topMenuCode );
							}	else	{
								sb.append( auth4url ).append( "?topCode=" ).append( topMenuCode );
							}
							sb.append( "&leftCode=" ).append( authMgr4.getMenuId() );

							String url4	= sb.toString();
//							log.info( "[url4] : [" + url4 + "]" );

					//out.println( "<li>&#45;" + menuName3 + "</li>" );

							if ( leftMenuCode.equals( authMgr4.getMenuId() ) )	{
								out.println( "<li><b><a href='" + url4 + "'> " + menuName4 + "</a></b></li>" );
							}	else	{
								out.println( "<li><a href='" + url4 + "'> " + menuName4 + "</a></li>" );
							}

//							log.info( "[authMgr4.getMenuId()] : [" + authMgr4.getMenuId() + "]" );
						}	// end for

					out.println( "</ul>" );
				}	// end for
			}	// end if
		}	// end for
	}	// end if
%>
