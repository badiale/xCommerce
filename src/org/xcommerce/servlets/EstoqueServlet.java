package org.xcommerce.servlets;

import org.xcommerce.beans.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Para Hibernate
import org.hibernate.Session;
import java.util.*;

public class EstoqueServlet extends HttpServlet {

  private final int INSERT = 0;
  private final int REMOVE = 1;
  private final int UPDATE = 2;
  private final int LIST   = 3;

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
					int function =
					Integer.parseInt(request.getParameter("function"));

					PrintWriter out = response.getWriter();
					
					Estoque e = null;
					switch (function) {
					case INSERT:
							e = new Estoque();
							e.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							e.setPreco(Float.parseFloat(request.getParameter("preco")));
							e.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.insert();
							} catch (Exception ex) { ex.printStackTrace(); }
							response.sendRedirect("message.jsp?msg=501");

					case REMOVE:
							e = new Estoque();
							e.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							e.setPreco(Float.parseFloat(request.getParameter("preco")));
							e.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.remove();
							} catch (Exception ex) { ex.printStackTrace(); }
							response.sendRedirect("message.jsp?msg=502");
							
					case UPDATE:
							e = new Estoque();
							e.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							e.setPreco(Float.parseFloat(request.getParameter("preco")));
							e.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.update();
							} catch (Exception ex) { ex.printStackTrace(); }
							response.sendRedirect("message.jsp?msg=503");
							
					case LIST:
							try {
								//TODO NA VIEW
								e.findAll();
							} catch (Exception ex) { ex.printStackTrace(); }
							//response.sendRedirect("message.jsp?msg=504");
					}

  }

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
       doGet(request, response);
  }

}



