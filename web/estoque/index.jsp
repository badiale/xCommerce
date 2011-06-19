<%@include file="/header.jsp"%>
<h1><%=msg.getString("ESTOQUE")%></h1>
<p><a href="list.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("LIST_ESTOQUE")%>
</a></p>
<p><a href="find.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("BUSCA_ESTOQUE")%>
</a></p>
<p><a href="insert.jsp?pcode=<%=request.getParameter("pcode")%>">
    <%=msg.getString("INSERE_ESTOQUE")%>
</a></p>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>
