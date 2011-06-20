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
<jsp:include page="produto">
    <jsp:param name="action" value="0"/>
</jsp:include>
</table>

<!-- se for admin -->
<a href="/xCommerce/produto/insert.jsp"><%= msg.getString("PRODUTO_INSERT") %></a><br>

<%@include file="/footer.jsp"%>
