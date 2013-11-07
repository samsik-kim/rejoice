<%@page import="com.omp.commons.payment.phonebill.PhoneBillPayment"%>
<%@page import="com.omp.commons.payment.PaymentProcessor"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.rmi.*"%>
<%@page import="java.security.*"%>
<%@page import="com.omp.commons.payment.creditcard.CreditCardPayment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%


	PhoneBillPayment	pbp;

	pbp = new PhoneBillPayment();
	pbp.setOrderId("order4");
	pbp.setRequestAmount(new BigDecimal("1"));
	pbp.setCustomerId("user1");
	pbp.setPhoneNo("0989782790");
	pbp.setPin("K120114747");
	pbp.setCarrier("FET");
	pbp.setConnectedIp("150.24.52.89");
	pbp.setUserEmail("patracyu@hanmail.net");

	pbp	= PaymentProcessor.requestPhoneBillSendAuthCode(pbp);
	
%>

<%=pbp%>