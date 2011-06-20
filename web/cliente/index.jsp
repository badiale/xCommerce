<%@include file="/header.jsp"%>
<jsp:include page="/cliente/clienteservlet">
    <jsp:param name="op" value="0"/>
</jsp:include>
<%@page import="org.xcommerce.beans.Cliente"%>
<% Cliente client = (Cliente) session.getAttribute("cliente"); %>
<a href="clienteservlet?op=5&email=<%=client.getEmail()%>&senha=<%=client.getSenha()%>"/>
    <%=msg.getString("DELETE_ACCOUNT")%>
</a>
<br/>
<a href="modify.jsp"/><%=msg.getString("UPDATE_ACCOUNT")%></a>
<br/>
<h1><%=msg.getString("PERSONAL_DATA")%></h1>
    <table>
	<tr><td><%=msg.getString("NAME")%>:</td><td><%=client.getNome()%></td></tr>
	<tr><td><%=msg.getString("EMAIL")%>:</td><td><%=client.getEmail()%></td></tr>
	<tr><td><%=msg.getString("BIRTHDATE")%>:</td><td><%=client.getNascimento().toString()%></td></tr>
    </table>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>