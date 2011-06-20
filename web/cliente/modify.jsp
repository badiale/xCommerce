<%@include file="/header.jsp"%>
<jsp:include page="/cliente/clienteservlet">
    <jsp:param name="op" value="0"/>
</jsp:include>
<%@page import="org.xcommerce.beans.Cliente"%>
<% Cliente client = (Cliente) session.getAttribute("cliente"); %>
<h1><%=msg.getString("UPDATE_ACCOUNT")%></h1>
<form action="clienteservlet" method="get">
    <input type="hidden" name="op" value="6"/>
    <table>
	<tr><td><%=msg.getString("NAME")%>:</td><td><input type="text" name="nome" value="<%=client.getNome()%>"/></td></tr>
	<tr><td><%=msg.getString("PASSWORD")%>:</td><td><input type="password" name="senha"/></td></tr>
	<tr><td><%=msg.getString("BIRTHDATE")%>:</td><td><input type="text" name="nasc" value="<%=client.getNascimento().toString()%>"/></td></tr>
    </table>
    <input type="submit" value="<%=msg.getString("SUBMIT")%>">
</form>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>