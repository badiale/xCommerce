<%@include file="/header.jsp"%>
<h1><%=msg.getString("PRODUTO_INSERT")%></h1>
<form action="produto" method="post">
    <input type="hidden" name="action" value="1"/>
    <label for="nome"><%=msg.getString("PRODUTO_NOME")%></label><input type="text" name="nome"/><br/>
    <label for="preco"><%=msg.getString("ESTOQUE_PRECO")%></label><input type="text" name="preco"/><br/>
    <label for="descricao"><%=msg.getString("PRODUTO_DESC")%></label><input type="text" name="descricao"/><br/>
    <label for="caracteristica"><%=msg.getString("PRODUTO_CARAC")%></label><input type="text" name="caracteristica"/><br/>
    <label for="valor">Val</label><input type="text" name="valor"/><br/>
    <label for="caracteristica2"><%=msg.getString("PRODUTO_CARAC")%></label><input type="text" name="caracteristica2"/><br/>
    <label for="valor2">Val</label><input type="text" name="valor2"/><br/>
    <label for="caracteristica3"><%=msg.getString("PRODUTO_CARAC")%></label><input type="text" name="caracteristica3"/><br/>
    <label for="valor3">Val</label><input type="text" name="valor3"/><br/>
    <input type="submit" value="Ok"/><br/>
</form>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>
