<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Bag</title>
        <style>
            table { width: 80%; margin: auto; border-collapse: collapse; }
            th, td { padding: 10px; border-bottom: 1px solid #ddd; text-align: center; }
            th { background: #f5f5f5; }
            .btn { padding: 8px 12px; text-decoration: none; color: white; border: none; cursor: pointer; }
            .btn-remove { background: #ff4444; }
            .btn-update { background: #ffbb33; }
            .btn-checkout { background: #2196F3; }
            .btn-shop { background: #4CAF50; }
            .center { text-align: center; margin-top: 20px; }
            input[type='number'] { width: 50px; text-align: center; }
        </style>
    </head>
    <body>
        <h2 class="center">Your Shopping Bag</h2>

        <c:if test="${not empty sessionScope.CART.items}">
            <table>
                <tr>
                    <th>Product</th><th>Price</th><th>Qty</th><th>Total</th><th>Actions</th>
                </tr>
                <c:set var="Total" value="0" />
                <c:forEach var="item" items="${sessionScope.CART.items.values()}">
                    <tr>
                        <td>${item.name}</td>
                        <td>$${item.price}</td>
                        <td>
                            <form action="MainController" method="POST" style="display: inline;">
                                <input type="hidden" name="productID" value="${item.productID}">
                                <input type="text" name="quantity" value="${item.quantity}" required>
                                <input type="submit" value="UpdateCart" name="action" class="btn btn-update">
                            </form>
                        </td>
                        <td>$${item.price * item.quantity}</td>
                        <td>
                            <form action="MainController" method="POST">
                                <input type="hidden" name="productID" value="${item.productID}">
                                <input type="submit" value="Remove" name="action" class="btn btn-remove">
                            </form>
                        </td>
                    </tr>
                    <c:set var="Total" value="${Total + (item.price * item.quantity)}" />
                </c:forEach>
                <tr>
                    <td colspan="3" style="text-align: right; font-weight: bold;">Grand Total:</td>
                    <td>$${Total}</td>
                    <td></td>
                </tr>
            </table>

            <div class="center">
                <a href="shopping.jsp" class="btn btn-shop">Continue Shopping</a><br><br>
                <form action="MainController" method="POST" style="text-align: center;">
                    <input type="hidden" name="total" value="${Total}"><br>
                    <input type="submit" value="Checkout" name="action" class="btn btn-checkout">
                </form>

            </div>
        </c:if>

        <c:if test="${empty sessionScope.CART.items}">
            <div class="center">
                <h3>Your shopping bag is empty</h3>
                <a href="shopping.jsp" class="btn btn-shop">Start Shopping</a>
            </div>
        </c:if>
    </body>
</html>
