<%@include file="/header.jsp"%>
<% String pcode = request.getParameter("pcode"); %>
<h1><%=msg.getString("ESTOQUE")%></h1>
<jsp:include page="EstoqueServlet">
    <jsp:param name="function" value="4"/>
    <jsp:param name="codigo" value="<%=pcode%>"/>
</jsp:include>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>
