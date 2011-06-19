package org.xcommerce.servlets;

import org.xcommerce.beans.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Para Hibernate
import org.hibernate.Session;
import java.util.*;

public class CompraServlet extends HttpServlet {

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
					
					Compra c = null;
					switch (function) {
					case INSERT:
							c = new Compra();
							c.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							c.setCliente(Cliente.find(request.getParameter("email")));
							c.setHoraCompra(request.getParameter("horaCompra"));
							try {
								c.insert();
							} catch (Exception ex) { ex.printStackTrace(); }
							//TODO mensagem correta
							response.sendRedirect("message.jsp?msg=501");

					case REMOVE:
							c = new Compra();
							c.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							c.setCliente(Cliente.find(request.getParameter("email")));
							c.setHoraCompra(request.getParameter("horaCompra"));
							
							try {
								c.remove();
							} catch (Exception ex) { ex.printStackTrace(); }
							//TODO mensagem correta
							response.sendRedirect("message.jsp?msg=502");
							
					case UPDATE:
							c = new Compra();
							c.setCodigo(Integer.parseInt(request.getParameter("codigo")));
							c.setCliente(Cliente.find(request.getParameter("email")));
							c.setHoraCompra(request.getParameter("horaCompra"));
							
							try {
								c.update();
							} catch (Exception ex) { ex.printStackTrace(); }
							//TODO mensagem correta
							response.sendRedirect("message.jsp?msg=503");
							
					case LIST:
							try {
								//TODO NA VIEW
								c.findAll();
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



