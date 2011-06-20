package org.xcommerce.servlets;

import org.xcommerce.beans.*;
import org.xcommerce.config.*;

import org.xcommerce.db.DBManager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Para Hibernate
import org.hibernate.Session;
import java.util.*;

public class ProdutoServlet extends HttpServlet implements Config {
	private ResourceBundle msg;
	private PrintWriter out;

	private final int LIST = 0;
	
	private void listAll(String search) {
		List<Produto> produtos = null;
	
		Session session = DBManager.getSession();
		session.beginTransaction();

		if (search != null && !search.equals("")) produtos = Produto.find(search);
		else produtos = Produto.findAll();
		
		Iterator it = produtos.iterator();

		String html = "";
		Produto p = null;
		while(it.hasNext()) {
			p = (Produto) it.next();
			Imagem img = p.getImagens().iterator().next();

			html += "<tr>";
			html += "<td width=\"10%\">";

			if (img != null)
				html += "<img src=\"" + appFolder + img.getId() + "\" width=\"100\" height=\"100\">";
			else
				html += msg.getString("NO_IMAGE");

			html += "</td>";
			html += "<td width=\"80%\">" +p.getNome() + "</td>";
			html += "<td width=\"10%\">";
			html += "<a href=\"/xCommerce/produto/buy.jsp?codigo="+p.getCodigo()+"\">" 
				+ msg.getString("PRODUTO_BUY") + "</a>";

			// TODO verificar se eh admin
			html += "<!-- se for admin -->";
			html += "<BR><a href=\"/xCommerce/produto/edit.jsp?codigo="+p.getCodigo()+"\""
				+ msg.getString("PRODUTO_EDIT") +"</a>";
			html += "<BR><a href=\"/xCommerce/produto?codigo="+p.getCodigo()+"&action=3\">"
				+msg.getString("PRODUTO_REMOVE")+"</a>";
			html += "</td>";
			html += "</tr>";
		}
		
		session.getTransaction().commit();

		out.println(html);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Locale currentLocale = request.getLocale();
		this.msg = ResourceBundle.getBundle("org.xcommerce.bundles.message", currentLocale);
		this.out = response.getWriter();
		
		int action = LIST;
		try {
			action = Integer.parseInt(request.getParameter("action"));
		} catch (Exception e) {
			System.err.println("ProdutoServlet: " + e.getMessage());
		}

		switch (action) {
			case LIST: {
				String search = request.getParameter("search");
				listAll(search); 
				break;
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
