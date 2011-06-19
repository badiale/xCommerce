package org.xcommerce.servlets;

import org.xcommerce.beans.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Para Hibernate
import org.hibernate.Session;
import java.util.*;

public class LinhaDeCompraServlet extends HttpServlet {

  private final int INSERT = 0;
  private final int REMOVE = 1;
  private final int UPDATE = 2;
  private final int LIST   = 3;

  public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
      
	int function =
	Integer.parseInt(request.getParameter("function"));

	PrintWriter out = response.getWriter();
	
	LinhaDeCompra lc = null;
	Produto p = null;
	Compra c = null;
	switch (function) {
	case INSERT:
			p = new Produto();
			c = new Compra();
			lc = new LinhaDeCompra();
			lc.setCodigo(Integer.parseInt(request.getParameter("codigo")));

			p = Produto.find(Integer.parseInt(request.getParameter("codigoProduto")));
			lc.setProduto(p);

			c = Compra.find(Integer.parseInt(request.getParameter("codigoCompra")));			
			lc.setCompra(c);

			lc.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
			lc.setPrecoUnitario(Float.parseFloat(request.getParameter("precoUnitario")));

			try {
				lc.insert();
			} catch (Exception ex) { ex.printStackTrace(); }
			//TODO mensagem correta
			response.sendRedirect("message.jsp?msg=501");
			break;

	case REMOVE:
			p = new Produto();
			c = new Compra();
			lc = new LinhaDeCompra();
			lc.setCodigo(Integer.parseInt(request.getParameter("codigo")));

			p = Produto.find(Integer.parseInt(request.getParameter("codigoProduto")));
			lc.setProduto(p);

			c = Compra.find(Integer.parseInt(request.getParameter("codigoCompra")));			
			lc.setCompra(c);

			lc.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
			lc.setPrecoUnitario(Float.parseFloat(request.getParameter("precoUnitario")));
			
			try {
				lc.remove();
			} catch (Exception ex) { ex.printStackTrace(); }
			//TODO mensagem correta
			response.sendRedirect("message.jsp?msg=502");
			break;			

	case UPDATE:
			p = new Produto();
			c = new Compra();
			lc = new LinhaDeCompra();
			lc.setCodigo(Integer.parseInt(request.getParameter("codigo")));

			p = Produto.find(Integer.parseInt(request.getParameter("codigoProduto")));
			lc.setProduto(p);

			c = Compra.find(Integer.parseInt(request.getParameter("codigoCompra")));			
			lc.setCompra(c);

			lc.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
			lc.setPrecoUnitario(Float.parseFloat(request.getParameter("precoUnitario")));
			
			try {
				lc.update();
			} catch (Exception ex) { ex.printStackTrace(); }
			//TODO mensagem correta
			response.sendRedirect("message.jsp?msg=503");
			break;			

	case LIST:
			try {
				//TODO NA VIEW
				lc.findAll();
			} catch (Exception ex) { ex.printStackTrace(); }
			//TODO mensagem correta
			//response.sendRedirect("message.jsp?msg=504");
	}

  }

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
       doGet(request, response);
  }

}



