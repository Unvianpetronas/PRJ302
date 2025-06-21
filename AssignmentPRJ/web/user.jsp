<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    if (session.getAttribute("LOGIN_USER") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 50px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            max-width: 400px;
            margin: auto;
        }
        h2 {
            color: #333;
        }
        p {
            font-size: 16px;
            margin: 10px 0;
            color: #555;
        }
        .btn {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
        }
        .btn-logout { background: #e74c3c; }
        .btn-shop { background: #2ecc71; }
    </style>
</head>
<body>

<div class="container">
    <h2>Welcome, ${sessionScope.LOGIN_USER.name}!</h2>

    <c:choose>
        <c:when test="${sessionScope.LOGIN_USER.role == 'Customer' || sessionScope.LOGIN_USER.role == 'Manager'}">
            <p><strong>User ID:</strong> ${sessionScope.LOGIN_USER.name}</p>
            <p><strong>Phone:</strong> ${sessionScope.LOGIN_USER.phone}</p>
            <p><strong>Role:</strong> ${sessionScope.LOGIN_USER.role}</p>
            <p><strong>Address:</strong> ${sessionScope.ADDRESS_USER.street}, ${sessionScope.ADDRESS_USER.city}, ${sessionScope.ADDRESS_USER.postalcode}</p>
        </c:when>
        <c:when test="${sessionScope.LOGIN_USER.role == 'Admin'}">
            <p><strong>Admin Name:</strong> ${sessionScope.LOGIN_USER.name}</p>
        </c:when>
    </c:choose>

    <h3>${requestScope.MESSAGE}</h3>

    <a href="LogoutController" class="btn btn-logout">Log Out</a>
    <a href="shopping.jsp" class="btn btn-shop">Go Shopping!</a>
</div>

</body>
</html>
