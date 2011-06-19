package org.xcommerce.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Para Hibernate
import org.hibernate.Session;
import util.*;

public class Estoque extends HttpServlet {

  private final int INSERT = 0;
  private final int REMOVE = 1;
  private final int UPDATE = 2;
  private final int UPDATE = 3;

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
					int function =
					Integer.parseInt(request.getParameter("function"));

					PrintWriter out = response.getWriter();

					switch (function) {
					case INSERT:
							org.xcommerce.beans.Estoque e = new org.xcommerce.beans.Estoque();
							e.setCodigo(request.getParameter("codigo"));
							e.setPreco(request.getParameter("preco"));
							e.setQuantidade(request.getParameter("quantidade"));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.insert();
							} catch (Exception e) { e.printStackTrace(); }
							response.sendRedirect("message.jsp?msg=501");

					case REMOVE:
							org.xcommerce.beans.Estoque e = new org.xcommerce.beans.Estoque();
							e.setCodigo(request.getParameter("codigo"));
							e.setPreco(request.getParameter("preco"));
							e.setQuantidade(request.getParameter("quantidade"));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.remove();
							} catch (Exception e) { e.printStackTrace(); }
							response.sendRedirect("message.jsp?msg=502");
							
					case UPDATE:
							org.xcommerce.beans.Estoque e = new org.xcommerce.beans.Estoque();
							e.setCodigo(request.getParameter("codigo"));
							e.setPreco(request.getParameter("preco"));
							e.setQuantidade(request.getParameter("quantidade"));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.update();
							} catch (Exception e) { e.printStackTrace(); }
							response.sendRedirect("message.jsp?msg=503");
							
					case LIST:
							try {
								//TODO NA VIEW
								e.findAll();
							} catch (Exception e) { e.printStackTrace(); }
							//response.sendRedirect("message.jsp?msg=504");
					}

  }

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
       doGet(request, response);
  }

}



