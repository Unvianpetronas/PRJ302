<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Confirmation</title>
    <style>
        .center { text-align: center; margin-top: 50px; }
        .btn { padding: 10px 20px; text-decoration: none; color: white; border: none; cursor: pointer; background: #4CAF50; }
    </style>
</head>
<body>
    <div class="center">
        <h2>Thank you for your order!</h2>
        <p>${requestScope.SUCCESS}</p>
        <a href="shopping.jsp" class="btn">Continue Shopping</a>
    </div>
</body>
</html>
