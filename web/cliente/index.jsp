<%@include file="/header.jsp"%>
<h1><%=msg.getString("CLIENT")%></h1>
<p><a href="upload.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("UPLOAD_IMAGE")%>
</a></p>
<p><a href="delete.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("DELETE_IMAGE")%>
</a></p>
<p><a href="list.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("LIST_IMAGES")%>
</a></p>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>