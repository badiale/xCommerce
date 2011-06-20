<%@include file="/header.jsp"%>
<h1><%=msg.getString("INSERE_ESTOQUE")%></h1>
<form action="EstoqueServlet" method="post">
    <input type="hidden" name="function" value="0"/>
    <label for="COD">COD</label><input type="text" name="codigo"/>
    <label for="preco"><%=msg.getString("ESTOQUE_PRECO")%></label><input type="text" name="preco"/><br/>
    <label for="quantidade"><%=msg.getString("ESTOQUE_QUANTIDADE")%></label><input type="text" name="quantidade"/><br/>
    <label for="fornecedor"><%=msg.getString("ESTOQUE_FORNECEDOR")%></label><input type="text" name="fornecedor"/><br/>
    <input type="submit" value="Ok"/><br/>
</form>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>
