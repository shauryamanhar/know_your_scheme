<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

if(request.getParameter("errors") != null && !request.getParameter("errors").equals("")){
	out.println("<h1>Problems on data scrapping</h1>");
}else{
	out.println("<h1>Data Saved to the Database</h1>");
}

%>

</body>
</html>