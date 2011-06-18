<%@include file="/header.jsp"%>
<h1><%=msg.getString("UPLOAD_IMAGE")%> <%=msg.getString("IMAGE")%></h1>
<form action="imagemservlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="op" value="0"/>
    <input type="hidden" name="pcode" value="<%=request.getParameter("pcode")%>"/>
    <input type="file" name="name"/>
    <input type="submit" value="Upload"/>
</form>
<%@include file="/footer.jsp"%>