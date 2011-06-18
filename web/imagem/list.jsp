<%@include file="/header.jsp"%>
<% String pcode = request.getParameter("pcode"); %>
<h1><%=msg.getString("LIST_IMAGES")%> <%=msg.getString("IMAGES")%></h1>
<jsp:include page="imagemservlet">
    <jsp:param name="op" value="2"/>
    <jsp:param name="pcode" value="<%=pcode%>"/>
</jsp:include>
<%@include file="/footer.jsp"%>