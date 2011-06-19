package org.xcommerce.servlets;

import org.xcommerce.beans.*;

import org.xcommerce.db.DBManager;

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
							} catch (Exception ex) { ex.printStackTrace(); response.sendRedirect("/xCommerce/message.jsp?msg=501"); }
							response.sendRedirect("/xCommerce/message.jsp?msg=502");
							break;

					case REMOVE:
							e = new Estoque();
							e.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							e.setPreco(Float.parseFloat(request.getParameter("preco")));
							e.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.remove();
							} catch (Exception ex) { ex.printStackTrace(); }
							response.sendRedirect("/xCommerce/message.jsp?msg=502");
							break;
					case UPDATE:
							e = new Estoque();
							e.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							e.setPreco(Float.parseFloat(request.getParameter("preco")));
							e.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
							e.setFornecedor(request.getParameter("fornecedor"));
							
							try {
								e.update();
							} catch (Exception ex) { ex.printStackTrace(); }
							response.sendRedirect("/xCommerce/message.jsp?msg=503");
							break;
							
					case LIST:
							try {
								Session session = DBManager.getSession();

		   					session.beginTransaction();
								List l = Estoque.findAll();
								session.getTransaction().commit();
								Locale currentLocale = request.getLocale();
		    				ResourceBundle msg = ResourceBundle.getBundle("org.xcommerce.bundles.message", currentLocale);
								
								Iterator it = l.iterator();
								out.println("<table>");
								out.println("<tr class=\"labelRow\"><th>COD</th><th>"+msg.getString("ESTOQUE_PRECO")+"</th> <th>"+msg.getString("ESTOQUE_QUANTIDADE")+"</th><th>"+msg.getString("ESTOQUE_FORNECEDOR")+"</th></tr>");
								
								while (it.hasNext()) {
									e = (Estoque) it.next();
									out.println("<tr class=\"labelRow\"><td class=\"idCell\">"+e.getCodigo()+"</td>");
									out.println("<td>"+e.getPreco()+"</td>");
									out.println("<td>"+e.getQuantidade()+"</td>");
									out.println("<td>"+e.getFornecedor()+"</td></tr>");
								}
								out.println("</table>");
								
								

							} catch (Exception ex) { ex.printStackTrace(); }
								
					}

  }

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
       doGet(request, response);
  }

}



