<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="org.xcommerce.beans.Cliente" %>
<%
	Locale currentLocale = request.getLocale();
	ResourceBundle msg = ResourceBundle.getBundle("org.xcommerce.bundles.message", currentLocale);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/xCommerce/style.css" />
        <title><%= msg.getString("TITLE") %></title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header"></div>
	    <div id="login">
		<jsp:include page="/cliente/clienteservlet">
		    <jsp:param name="op" value="4"/>
		</jsp:include>
		<!--form action="clienteservlet" method="post">
		    <table>
			<tr><td><#%= msg.getString("EMAIL") %>:</td><td><input class="loginInputText" type="text" name="email"></td></tr>
			<tr><td><#%= msg.getString("PASSWORD") %>:</td><td><input class="loginInputText" type="password" name="password"></td></tr>
		    </table>
		    <input id="submitLogin" type="submit" value="<#%= msg.getString("LOGIN") %>">
		</form-->
	    </div>
            <div id="body-top"></div>
            <div id="content">