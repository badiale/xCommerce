<%@include file="/header.jsp"%>
<h1><%=msg.getString("IMAGES")%></h1>
<p><a href="upload.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("UPLOAD_IMAGE")%>
</a></p>
<p><a href="delete.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("DELETE_IMAGE")%>
</a></p>
<p><a href="list.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("LIST_IMAGES")%>
</a></p>
<%@include file="/footer.jsp"%>