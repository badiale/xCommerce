
<%@ include file="/header.jsp"%>
<div id="message"><p class="<%
    int msgcode = Integer.parseInt(request.getParameter("msg"));

    switch (msgcode) {
	// Gerais do sistema
	case 0: out.println("error_msg\">"+msg.getString("LOGIN_ERROR")); break;
	// Operacoes de imagens
	case 100: out.println("error_msg\">"+msg.getString("MULTIPART_FORM_ERROR")); break;
	case 101: out.println("success_msg\">"+msg.getString("UPLOAD_SUCCESS")); break;
	case 102: out.println("error_msg\">"+msg.getString("UPLOAD_ERROR")); break;
	case 103: out.println("success_msg\">"+msg.getString("DELETE_IMAGE_SUCCESS")); break;
	case 104: out.println("error_msg\">"+msg.getString("DELETE_IMAGE_ERROR")); break;
	case 105: out.println("error_msg\">"+msg.getString("LIST_IMAGE_ERROR")); break;
	case 106: out.println("error_msg\">"+msg.getString("IMAGE_NOT_SUPPORTED")); break;
	// Erro inesperado
	default: out.println("error_msg\">"+msg.getString("UNEXPECTED_ERROR")); break;
    }
    // Template error message: out.println("error_msg\">"+msg.getString("")); break;
    // Template success message: out.println("success_msg\">"+msg.getString("")); break;

%></p></div>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@ include file="/footer.jsp"%>
