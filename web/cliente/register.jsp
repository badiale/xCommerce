<%@include file="/header.jsp"%>
<h1><%=msg.getString("REGISTER")%></h1>
<form action="clienteservlet" method="get">
    <input type="hidden" name="op" value="1">
    <table>
	<tr><td><%=msg.getString("NAME")%>:</td><td><input class="loginInputText" type="text" name="nome"></td></tr>
	<tr><td><%=msg.getString("EMAIL")%>:</td><td><input class="loginInputText" type="text" name="email"></td></tr>
	<tr><td><%=msg.getString("PASSWORD")%>:</td><td><input class="loginInputText" type="password" name="senha"></td></tr>
	<tr><td><%=msg.getString("BIRTHDATE")%>:</td><td><input class="loginInputText" type="text" name="nasc"></td></tr>
	<tr><td>&nbsp;</td><td><%=msg.getString("DATEFORMAT")%></td></tr>
    </table>
    <input id="submitLogin" type="submit" value="<%=msg.getString("SUBMIT")%>">
</form>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>