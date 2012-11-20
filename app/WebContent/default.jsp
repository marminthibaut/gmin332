
<%
	Exception e = (Exception) request.getAttribute("Exception");
%>

<jsp:include page="includes/header.jsp" />

<div class="container">
	<div class="page-header">
		<h1>
			Ontology viewer <small>GMIN332 - University Montpellier 2</small>
		</h1>
	</div>

	<div class="row">
		<div class="span3">yop</div>
		<div class="span9">
			<%
				if (e != null) {
			%>
			<p class="text-warning"><%=e.getMessage()%></p>
			<%
				}
			%>

			<form action="explore" method="post">
				<fieldset>
					<legend>Choose an ontology</legend>
					<label>Ontology URI</label>
					<div class="input-append">
						<span class="add-on">http://</span> <input
							class="input-xxlarge" type="text" name="uri_ontology"
							placeholder="www.geonames.org/ontology/ontology_v
2.0_Lite.rdf" />
						<button type="submit" class="btn">Describe</button>

					</div>

				</fieldset>
			</form>

		</div>
	</div>
</div>
<jsp:include page="includes/footer.jsp" />