<%@include file="/header.jsp"%>
<% String pcode = request.getParameter("pcode"); %>
<h1><%=msg.getString("LIST_ESTOQUE")%></h1>
<br/>
<jsp:include page="EstoqueServlet">
    <jsp:param name="function" value="3"/>
</jsp:include>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>
