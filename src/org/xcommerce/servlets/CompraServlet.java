package org.xcommerce.servlets;

import org.xcommerce.beans.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.xcommerce.db.DBManager;

// Para Hibernate
import org.hibernate.Session;
import java.util.*;

public class CompraServlet extends HttpServlet {

  private final int INSERT = 0;
  private final int REMOVE = 1;
  private final int UPDATE = 2;
  private final int LIST   = 3;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	int function =
	Integer.parseInt(request.getParameter("function"));

	PrintWriter out = response.getWriter();
	
	Compra c = null;
	Produto p = null;

	Locale currentLocale = request.getLocale();
	ResourceBundle msg = ResourceBundle.getBundle("org.xcommerce.bundles.message", currentLocale);

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
			break;

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
			break;							

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
			break;							

	case LIST:
		try {
			
			String html = "";
				
			//Session session = DBManager.getSession();

			//session.beginTransaction();
					
			c = Compra.find(Integer.parseInt(request.getParameter("ccode")));
			//session.getTransaction().commit();			
			Set linhasCompra = c.getLinhasCompra();		
		
			Iterator it = linhasCompra.iterator();
			
			html += "<table>";
			html += "<tr class=\"labelRow\"><th>COD</th><th>"+msg.getString("COMPRA_PRODUTO_NOME")+"</th> <th>"+msg.getString("COMPRA_PRODUTO_PRECO")+"</th><th>"+msg.getString("COMPRA_QUANTIDADE")+"</th></tr>";
			/*while (it.hasNext()) {
				LinhaDeCompra lc = (LinhaDeCompra) it.next();
				html += "<tr class=\"labelRow\"><td class=\"idCell\">"+lc.getCodigo()+"</td>";
				html += "<tr class=\"labelRow\"><td class=\"idCell\">"+lc.getProduto().getNome()+"</td>";
				html += "<tr class=\"labelRow\"><td class=\"idCell\">"+lc.getQuantidade()+"</td>";
				html += "<tr class=\"labelRow\"><td class=\"idCell\">"+lc.getPrecoUnitario()+"</td>";
			}*/
			
			html += "</table><br><br>";
			html += "UUUUUUUUUU>>>>> " + c.getCliente().getEmail();
			//html += msg.getString("COMPRA_TOTAL") + c.total();
		
			out.println(html);

		} catch (Exception ex) { ex.printStackTrace(); }
	}

  }

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
       doGet(request, response);
  }

}



