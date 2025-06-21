<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.getRole() != 'Admin'}">
    <c:redirect url="login.jsp" />
</c:if>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        form {
            margin-bottom: 20px;
        }
        input, select {
            padding: 8px;
            margin: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: #fff;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        a {
            text-decoration: none;
            padding: 5px 10px;
            border-radius: 5px;
            color: white;
        }
        .update-btn {
            background-color: #28a745;
        }
        .delete-btn {
            background-color: #dc3545;
        }
        .logout-link {
            display: inline-block;
            margin-top: 20px;
            background-color: #333;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h1>Welcome, ${sessionScope.LOGIN_USER.getName()}!</h1>

    <form action="SearchController" method="GET">
        <input type="text" name="Search" value="${requestScope.SEARCH}" placeholder="Search user...">
        <select name="Fillter">
            <option value="All" ${param.Fillter eq 'All' ? 'selected' : ''}>All</option>
            <option value="Customer" ${param.Fillter eq 'Customer' ? 'selected' : ''}>Customer</option>
            <option value="Manager" ${param.Fillter eq 'Manager' ? 'selected' : ''}>Manager</option>
            <option value="Admin" ${param.Fillter eq 'Admin' ? 'selected' : ''}>Admin</option>
        </select>
        <input type="submit" value="Search">
    </form>

    <c:if test="${requestScope.SEARCHLIST != null}">
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal Code</th>
                    <th>Country</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.SEARCHLIST}" varStatus="count">
                    <tr>
                        <td>${count.index + 1}</td>
                        <td>${user.getUserid()}</td>
                        <td>${user.getName()}</td>
                        <td>${user.getRole()}</td>
                        <td>${user.getCity()}</td>
                        <td>${user.getState()}</td>
                        <td>${user.getPostalcode()}</td>
                        <td>${user.getCountry()}</td>
                        <td>
                            <a href="Update.jsp?Userid=${user.getUserid()}
                               &name=${user.getName()}
                               &Phone=${user.getPhone()}
                               &addressID=${user.getAddressID()}
                               &street=${user.getStreet()}
                               &city=${user.getCity()}
                               &state=${user.getState()}
                               &postalcode=${user.getPostalcode()}
                               &country=${user.getCountry()}"
                               class="update-btn">
                                Update
                            </a>
                        </td>
                        <td>
                            <a href="MainController?action=Delete&userID=${user.getUserid()}&search=${requestScope.SEARCH}" class="delete-btn">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <h2 style="color: brown">${requestScope.MESS}</h2>
    <h2 style="color: brown">${requestScope.DELETE}</h2>

    <a href="MainController?action=Logout" class="logout-link">Logout</a>
</body>
</html>
