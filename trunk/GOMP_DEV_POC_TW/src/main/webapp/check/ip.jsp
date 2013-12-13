<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	try {
		out.println("java.net.InetAddress.getLocalHost().getHostAddress() : " + java.net.InetAddress.getLocalHost().getHostAddress());
		out.println("<br/>");
		out.println("request.getLocalAddr() : " + request.getLocalAddr());
	} catch(Exception e) {}
%>
