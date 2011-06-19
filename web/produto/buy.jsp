<%@include file="/header.jsp"%>

<H1>Nome do produto</H1>

<p align="center">
	<TABLE WIDTH="100%" height="150">
		<TR>
			<!-- repete -->
			<TD>
				<a href="imagem.jpg">
					<img src="imagem.jsp" width="150" height="150">
				</a>
			</TD>
		</TR>
	</TABLE>
	<br><a href="inserir imagem"><%= msg.getString("PRODUTO_INSERT_IMAGE") %></a>
</p>

<!-- caracteristicas -->
<p>
<h2><%= msg.getString("PRODUTO_CARACTERISTICA") %></H2>
<table WIDTH="100%">
	<!-- repete -->
	<tr>
		<th width="150">Caracteristica</th>
		<td>Valor</td>
	</tr>
</table>
</p>

<!-- se for admim -->
<p align="center">
<a href="/xCommerce/produto/edit.jsp?codigo=codigo"><%= msg.getString("PRODUTO_EDIT") %></a> |
<a href="/xCommerce/produto?codigo=codigo&action=3"><%= msg.getString("PRODUTO_REMOVE") %></a> |
<a href="/xCommerce/estoque/find.jsp?pcode=codigo"><%= msg.getString("PRODUTO_ESTOQUE") %></a>
</p>

<%@include file="/footer.jsp"%>
