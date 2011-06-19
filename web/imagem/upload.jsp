<%@include file="/header.jsp"%>
<h1><%=msg.getString("UPLOAD_IMAGE")%> <%=msg.getString("IMAGE")%></h1>
<p><%=msg.getString("SUPPORTED_IMAGE_TYPES")%></p>
<br/>
<form action="imagemservlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="op" value="0"/>
    <input type="hidden" name="pcode" value="<%=request.getParameter("pcode")%>"/>
    <input type="file" name="imagefile"/><br/>
    <input type="submit" value="<%=msg.getString("UPLOAD_IMAGE")%>"/><br/>
</form>
<br/>
<a href="javascript:history.go(-1)"/><%=msg.getString("BACK")%></a>
<%@include file="/footer.jsp"%>