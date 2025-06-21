<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
            margin: 40px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            max-width: 900px;
            margin: auto;
        }
        h1 {
            color: #333;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            border: none;
        }
        .btn-view { background: #3498db; }
        .btn-cart { background: #2ecc71; }
        .btn-add { background: #f39c12; }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        td {
            background-color: #f9f9f9;
        }
        input[type="number"] {
            width: 60px;
            padding: 5px;
            text-align: center;
        }
        .message {
            color: brown;
            font-weight: bold;
            margin-top: 10px;
        }
        .link {
            display: block;
            margin-top: 15px;
            font-size: 18px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        .link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome to Our Store</h1>

    <form action="MainController">
        <input type="submit" class="btn btn-view" value="View" name="action">
    </form>

    <c:if test="${not empty requestScope.LIST_PRODUCT}">
        <table>
            <thead>
                <tr>
                    <th>NO</th>
                    <th>ProductID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${requestScope.LIST_PRODUCT}" varStatus="count">
                    <tr>
                        <td>${count.index + 1}</td>
                        <td>${product.productID}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>
                            <form action="MainController" method="POST">
                                <input type="number" name="quantity" min="1" value="">
                                <input type="hidden" name="productID" value="${product.productID}">
                                <input type="submit" class="btn btn-add" value="AddCart" name="action">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <h2 class="message">${requestScope.ERROR}</h2>
    <h2 class="message">${requestScope.SUCCESS}</h2>

    <a href="shoppingbag.jsp" class="link">Shopping Bag</a>
     <a href="user.jsp" class="link">Return to user</a>
</div>

</body>
</html>
