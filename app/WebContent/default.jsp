
<%
	Exception e = (Exception) request.getAttribute("Exception");
%>

<jsp:include page="includes/header.jsp" />

<%
	if (e != null) {
%>
<p class="error"><%=e.getMessage()%></p>
<%
	}
%>

<form action="explore" method="post">
	Ontology : http://<input type="text" name="uri_ontology" /> <input
		type="submit" value="Describe" />
</form>
<jsp:include page="includes/footer.jsp" />