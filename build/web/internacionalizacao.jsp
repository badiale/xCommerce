<%@page import="java.util.*" %>
<%
	Locale currentLocale = request.getLocale();
	ResourceBundle msg = ResourceBundle.getBundle("org.xcommerce.bundles.msgexemplo", currentLocale);
%>
<html>
	<head>
		<title><%= msg.getString("TITLE") %></title>
	</head>

	<body>
		<%= msg.getString("HELLO_WORLD") %>
	</body>
</html>
