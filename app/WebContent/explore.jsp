<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String testnewparam = (String) request.getAttribute("testnewparam");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	TestnewParam =<%=testnewparam%><br>
	 
	param1 = <%=(String) request.getParameter("param1")%><br>
	
	param2 = <%=(String) request.getParameter("param2")%><br>
</body>
</html>