<%@include file="/header.jsp"%>
<h1><%=msg.getString("DELETE_IMAGE")%> <%=msg.getString("IMAGE")%></h1>
<jsp:include page="imagemservlet">
    <jsp:param name="op" value="3"/>
    <jsp:param name="pcode" value=""/>
</jsp:include>
<jsp:include page="imagemservlet">
    <jsp:param name="op" value="2"/>
    <jsp:param name="pcode" value="1"/>
</jsp:include>
<%@include file="/footer.jsp"%>