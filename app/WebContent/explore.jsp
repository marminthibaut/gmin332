<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String json = (String) request.getAttribute("json");
%>
<jsp:include page="includes/header.jsp" />

<script type="text/javascript">
//init data
    var json = "<%= json %>";
    //end
</script>
<h3><%=request.getAttribute("uri_ontology")%></h3>


<div id="center-container">
    <div id="infovis"></div>
</div>

<div id="right-container">

<h4>Change Tree Orientation</h4>
<table>
    <tr>
        <td>
            <label for="r-left">left </label>
        </td>
        <td>
            <input type="radio" id="r-left" name="orientation" checked="checked" value="left" />
        </td>
    </tr>
    <tr>
         <td>
            <label for="r-top">top </label>
         </td>
         <td>
            <input type="radio" id="r-top" name="orientation" value="top" />
         </td>
    <tr>
         <td>
            <label for="r-bottom">bottom </label>
          </td>
          <td>
            <input type="radio" id="r-bottom" name="orientation" value="bottom" />
          </td>
    </tr>
    <tr>
          <td>
            <label for="r-right">right </label>
          </td> 
          <td> 
           <input type="radio" id="r-right" name="orientation" value="right" />
          </td>
    </tr>
</table>

</div>

<div id="log"></div>


<script type="text/javascript">
	init();
</script>
<jsp:include page="includes/footer.jsp" />
