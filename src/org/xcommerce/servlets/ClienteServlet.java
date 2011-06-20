package org.xcommerce.servlets;

// Servlet Imports
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
// xCommerce Imports
import org.xcommerce.beans.Cliente;
import org.xcommerce.db.DBManager;
// Hibernate Imports
import org.hibernate.Session;
// Other Imports
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Servlet to handle client operations within the e-commerce application.
 * @author Fábio Abrão Luca
 */
public class ClienteServlet extends HttpServlet {
    private final int CONFIRMLOGIN  = 0;
    private final int REGISTER	    = 1;
    private final int LOGIN	    = 2;
    private final int LOGOUT	    = 3;
    private final int LOGINBOX	    = 4;
    private final int UNREGISTER    = 5;
    private final int MODIFY	    = 6;
    
    public void doGet (HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession();

	String targetUrl = null;
	int operation = -1;
	try {
	     operation = Integer.parseInt(request.getParameter("op"));
	} catch (Exception e) {
	    targetUrl = "/xCommerce/message.jsp?msg=0";
	    response.sendRedirect(targetUrl);
	}
	Cliente client = null;
	switch (operation) {
	    case CONFIRMLOGIN:
		try {
		    client = (Cliente) session.getAttribute("cliente");
		    if (client == null || ((client != null) &&
	    		    (Cliente.findValidate(client.getEmail(),client.getSenha())==null))) {
		        targetUrl = "/xCommerce/index.jsp";
		    }
		    if (targetUrl != null) {
			response.sendRedirect(targetUrl);
		    }
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=0";
		    response.sendRedirect(targetUrl);
		}
		break;
	    case REGISTER:
		client = new Cliente();
		try {
		    client.setNome(request.getParameter("nome"));
		    client.setEmail(request.getParameter("email"));
		    client.setSenha(request.getParameter("senha"));
		    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		    Date nascimento = format.parse(request.getParameter("nasc"));  
		    client.setNascimento(nascimento);
		    client.insert();
		    targetUrl = "/xCommerce/message.jsp?msg=0";
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=0";
		    response.sendRedirect(targetUrl);
		}
		break;
	    case UNREGISTER:
		try {
		    client = (Cliente) session.getAttribute("cliente");
		    if ((client != null) &&
	    		    (Cliente.findValidate(client.getEmail(),client.getSenha())!=null)) {
		        client.remove();
			targetUrl = "/xCommerce/index.jsp";
		    }
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=0";
		    response.sendRedirect(targetUrl);
		}
		break;
	    case LOGIN:
		String mail = request.getParameter("email");
		String pass = request.getParameter("senha");
		client = Cliente.findValidate(mail,pass);
		if (client != null) {
		    session.setAttribute("cliente",client);
		    targetUrl = "/xCommerce/cliente";
		} else {
		    targetUrl = "/xCommerce/message.jsp?msg=0";
		}
		break;
	    case LOGOUT:
		session.invalidate();
		targetUrl = "/xCommerce/index.jsp";
		break;
	    case LOGINBOX:
		try {
		    Locale currentLocale = request.getLocale();
		    ResourceBundle msg = ResourceBundle.getBundle("org.xcommerce.bundles.message", currentLocale);
		    client = (Cliente) session.getAttribute("cliente");
		    if (client == null || ((client != null) &&
	    		    (Cliente.findValidate(client.getEmail(),client.getSenha())==null))) {
			out.println("<form action=\"/xCommerce/cliente/clienteservlet\" method=\"get\">");
			out.println("<input type=\"hidden\" name=\"op\" value=\"2\"/>");
			out.println("<table>");
			out.println("<tr><td>"+msg.getString("EMAIL")+":</td>"+
				"<td><input class=\"loginInputText\" type=\"text\""+
				"name=\"email\"></td></tr>");
			out.println("<tr><td>"+msg.getString("PASSWORD")+":</td>"+
				"<td><input class=\"loginInputText\" type=\"password\""+
				"name=\"senha\"></td></tr>");
			out.println("</table>");
			out.println("<a href=\"/xCommerce/cliente/register.jsp\">"+
				msg.getString("REGISTER")+"</a>");
			out.println("<input id=\"submitLogin\" type=\"submit\""+
				"value=\""+msg.getString("LOGIN")+"\">");
			out.println("</form>");
		    } else {
			out.println(msg.getString("GREETING")+" "+client.getNome()+"!<br/>");
			out.println("<form action=\"clienteservlet\" method=\"get\">");
			out.println("<input type=\"hidden\" name=\"op\" value=\"3\"/>");
			out.println("<input id=\"submitLogin\" type=\"submit\""+
				"value=\""+msg.getString("LOGOUT")+"\">");
			out.println("</form>");
		    }
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=0";
		    response.sendRedirect(targetUrl);
		}
		break;
	    case MODIFY:
		try {
		    client = (Cliente) session.getAttribute("cliente");
		    if ((client != null) &&
	    		    (Cliente.findValidate(client.getEmail(),client.getSenha())!=null)) {
			client.setNome(request.getParameter("nome"));
			client.setSenha(request.getParameter("senha"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
			Date nascimento = format.parse(request.getParameter("nasc"));  
			client.setNascimento(nascimento);
			client.update();
			targetUrl = "/xCommerce/cliente";
		    }
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=0";
		    response.sendRedirect(targetUrl);
		}
		break;
	    default:
		targetUrl = "/xCommerce/message.jsp?msg=404";
		response.sendRedirect(targetUrl);
		break;
	}
    }
}