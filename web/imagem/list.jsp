<%@include file="/header.jsp"%>
<% String pcode = request.getParameter("pcode"); %>
<h1><%=msg.getString("LIST_IMAGES")%> <%=msg.getString("IMAGES")%></h1>
<br/>
<jsp:include page="imagemservlet">
    <jsp:param name="op" value="2"/>
    <jsp:param name="pcode" value="<%=pcode%>"/>
</jsp:include>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>