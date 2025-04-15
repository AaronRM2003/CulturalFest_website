<%@ page import="javax.servlet.http.*,javax.servlet.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    if (session == null || session.getAttribute("logged") == null) {
        response.sendRedirect("Login.html"); // Redirect to login if not authenticated
        return;
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome <%= session.getAttribute("name") %></h1>
</body>
</html>