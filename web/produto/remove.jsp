<%@include file="/header.jsp"%>
<%String codigo = request.getParameter("codigo"); %>"
<jsp:include page="produto">
    <jsp:param name="action" value="3"/>
    <jsp:param name="codigo" value="<%= codigo%>"/>
</jsp:include>
<%@include file="/footer.jsp"%>
