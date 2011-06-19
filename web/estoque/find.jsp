<%@include file="/header.jsp"%>
<% String pcode = request.getParameter("pcode"); %>
<h1><%=msg.getString("ESTOQUE")%></h1>
<jsp:include page="EstoqueServlet">
    <jsp:param name="function" value="4"/>
    <jsp:param name="codigo" value="<%= pcode %>"/>
</jsp:include>

<form action="EstoqueServlet" method="post">
    <input type="hidden" name="function" value="2"/>
    <input type="hidden" name="codigo" value="<%= pcode %>"/>
    <label for="preco"><%=msg.getString("ESTOQUE_PRECO")%></label><input type="text" name="preco"/><br/>
    <label for="quantidade"><%=msg.getString("ESTOQUE_QUANTIDADE")%></label><input type="text" name="quantidade"/><br/>
    <label for="fornecedor"><%=msg.getString("ESTOQUE_FORNECEDOR")%></label><input type="text" name="fornecedor"/><br/>
    <input type="submit" value="Modificar"/><br/>
</form>

<form action="EstoqueServlet" method="post">
    <input type="hidden" name="function" value="1"/>
    <input type="hidden" name="codigo" value="<%= pcode %>"/>
    <input type="submit" value="Remover"/><br/>
</form>


<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>
