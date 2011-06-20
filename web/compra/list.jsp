<%@include file="/header.jsp"%>
<% String ccode = request.getParameter("ccode"); %>
<h1><%=msg.getString("LIST_COMPRA")%></h1>
<br/>
<jsp:include page="CompraServlet">
    <jsp:param name="function" value="3"/>
	<jsp:param name="ccode" value="<%=ccode%>"/>
</jsp:include>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>
