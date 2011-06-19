package org.xcommerce.servlets;

// Servlet Imports
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
// xCommerce Imports
import org.xcommerce.beans.Imagem;
import org.xcommerce.beans.Produto;
import org.xcommerce.db.DBManager;
// Hibernate Imports
import org.hibernate.Session;
// Other Imports
import java.util.*;
import java.text.SimpleDateFormat;
// Apache Commons for file uploading
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 * Servlet to handle product images' operations within the e-commerce application.
 * @author Fábio Abrão Luca
 */
public class ImagemServlet extends HttpServlet {
    private final int UPLOAD = 0;
    private final int DELETE = 1;
    private final int LIST   = 2;
    private final int SELECT = 3;
    
    public void doGet (HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	
	String targetUrl = null;
	
	// Authentication
	HttpSession httpsession = request.getSession();
	/*Cliente client = (Cliente) httpsession.getAttibute("cliente");
	if (client == null) {
	    targetUrl = "/xCommerce/message.jsp?msg=0";
	    response.sendRedirect(targetUrl);
	}
	*/
	
	int operation = -1;
	int pcode = -1;
	FileItem fitem = null;
	
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	if (isMultipart) {
	    FileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);

	    List items = null;
	    try {
		items = upload.parseRequest(request);
	    } catch (FileUploadException e) {
		targetUrl = "/xCommerce/message.jsp?msg=100";
		response.sendRedirect(targetUrl);
	    }

	    Iterator itr = items.iterator();

	    while (itr.hasNext()) {
		FileItem item = (FileItem) itr.next();

		if (item.isFormField()) { // form field
		    if (item.getFieldName().equals("op")) {
			try {
			    operation = Integer.parseInt(item.getString());
			} catch (Exception e) {
			    targetUrl = "/xCommerce/message.jsp?msg=100";
			    response.sendRedirect(targetUrl);
			}
		    } else if (item.getFieldName().equals("pcode")) {
			try {
			    pcode = Integer.parseInt(item.getString());
			} catch (Exception e) {
			    targetUrl = "/xCommerce/message.jsp?msg=100";
			    response.sendRedirect(targetUrl);
			}
		    }
		} else { // is a file
		    fitem = item;
		}
	    }
	} else {
	    try {
		operation = Integer.parseInt(request.getParameter("op"));
		pcode = Integer.parseInt(request.getParameter("pcode"));
	    } catch (Exception e) {
		targetUrl = "/xCommerce/message.jsp?msg=100";
		response.sendRedirect(targetUrl);
	    }
	}
	
	
	switch (operation) {
	    case UPLOAD:
		try {
		    Imagem image = new Imagem();
		    image.setProduto(Produto.find(new Integer(pcode)));
		    image.setDataCriacao(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		    image.insert();
		    File imageFile = new File(Imagem.imagesFolder+"/"+image.getId());
		    if (fitem.getContentType().equals("image/jpeg") ||
			    fitem.getContentType().equals("image/png") ||
			    fitem.getContentType().equals("image/bmp")) {
			fitem.write(imageFile);
			targetUrl="/xCommerce/message.jsp?msg=101";
		    } else {
			targetUrl="/xCommerce/message.jsp?msg=106";
		    }
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=102";
		    response.sendRedirect(targetUrl);
		}
		break;
	    case DELETE:
		try {
		    String fid_comfirm = request.getParameter("fid");
		    if (fid_comfirm == null) {
			targetUrl = "/xCommerce/message.jsp?msg=104";
		    } else {
			Long fileid = new Long(fid_comfirm);
			Session session = DBManager.getSession();
			session.beginTransaction();
			Imagem image = Imagem.findById(session,fileid);
			session.getTransaction().commit();
			if (image != null) {
			    image.remove();
			    targetUrl="/xCommerce/message.jsp?msg=103";
			} else {
			    targetUrl = "/xCommerce/message.jsp?msg=104";
			}
		    }
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=104";
		    response.sendRedirect(targetUrl);
		}
		break;
	    case LIST:
		try {
		    int code = Integer.parseInt(request.getParameter("pcode"));
		    Session session = DBManager.getSession();
		    session.beginTransaction();
			Produto prod = Produto.find(new Integer(code));
		    Set images = prod.getImagens();
		    session.getTransaction().commit();
		    Iterator itr = images.iterator();
		    Imagem image = null;
		    Locale currentLocale = request.getLocale();
		    ResourceBundle msg = ResourceBundle.getBundle("org.xcommerce.bundles.message", currentLocale);
		    out.println("<table>");
		    out.println("<tr class=\"labelRow\"><th>ID</th><th>"+msg.getString("IMAGE")+"</th></tr>");
		    while (itr.hasNext()) {
			image = (Imagem) itr.next();
			out.println("<tr><td class=\"idCell\">"+image.getId()+"</td><td>"
				+"<img class=\"thumbs\" src=\"/xCommerce"
				+"/images/products/"+image.getId()+"\"></td></tr>");
		    }
		    out.println("</table>");
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=105";
		    response.sendRedirect(targetUrl);
		}
		break;
	    case SELECT:
		try {
		    int code = Integer.parseInt(request.getParameter("pcode"));
		    Session session = DBManager.getSession();
		    session.beginTransaction();
			Produto prod = Produto.find(new Integer(code));
		    session.getTransaction().commit();
		    Set images = prod.getImagens();
		    Iterator itr = images.iterator();
		    Imagem image = null;
		    Locale currentLocale = request.getLocale();
		    ResourceBundle msg = ResourceBundle.getBundle("org.xcommerce.bundles.message", currentLocale);
		    out.println("<form action=\"imagemservlet\" method=\"get\">");
		    out.println("<input type=\"hidden\" name=\"op\" value=\"1\"/>");
		    out.println("<input type=\"hidden\" name=\"pcode\" value=\""+code+"\"/>");
		    out.println("<p>"+msg.getString("CHOOSE_IMAGE")+": </p>");
		    out.println("<select name=\"fid\">");
		    while (itr.hasNext()) {
			image = (Imagem) itr.next();
			out.println("<option value=\""+image.getId()+"\">"
				+image.getId()+"</option>");
		    }
		    out.println("</select>");
		    out.println("<input type=\"submit\" value=\""+msg.getString("DELETE_IMAGE") +"\"/>");
		    out.println("</form>");
		} catch (Exception e) {
		    targetUrl = "/xCommerce/message.jsp?msg=105";
		    response.sendRedirect(targetUrl);
		}
	}
	response.sendRedirect(targetUrl);
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request,response);
    }
}
