<%@include file="/header.jsp"%>

<H1><%= msg.getString("PRODUTO_TITLE") %></H1>

<!-- se for admin -->
<a href="/xCommerce/produto/insert.jsp"><%= msg.getString("PRODUTO_INSERT") %></a><br>

<table width="100%">
	<tr>
		<td colspan=3>
			<form method="post" action="/xCommerce/produto/index.jsp">
				<INPUT type="text" name="search">
				<input type="submit" VALUE="<%= msg.getString("PRODUTO_SEARCH") %>">
			</form>
		</td>
	</tr>
	<tr>
		<td width="10%"><img src="imagem.jpg" width="300" height="300"></td>
		<td width="80%">Nome do produto</td>
		<td width="10%">
			<a href="/xCommerce/produto/buy.jsp?codigo=codigo"><%= msg.getString("PRODUTO_BUY") %></a>
			<!-- se for admim -->
			<BR><a href="/xCommerce/produto/edit.jsp?codigo=codigo"><%= msg.getString("PRODUTO_EDIT") %></a>
			<BR><a href="/xCommerce/produto?codigo=codigo&action=3"><%= msg.getString("PRODUTO_REMOVE") %></a>
		</td>
	</tr>
</table>

<!-- se for admin -->
<a href="/xCommerce/produto/insert.jsp"><%= msg.getString("PRODUTO_INSERT") %></a><br>

<%@include file="/footer.jsp"%>
