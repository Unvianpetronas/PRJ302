<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create User</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                padding: 20px;
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 350px;
                margin: auto;
            }
            h1 {
                color: #333;
            }
            input[type="text"], input[type="password"] {
                width: 100%;
                padding: 8px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .message {
                color: brown;
                margin-top: 10px;
                font-weight: bold;
            }
            .back-link {
                display: inline-block;
                padding: 8px 15px;
                margin-top: 10px;
                background-color: gray;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .back-link:hover {
                background-color: darkgray;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Create Account</h1>
            <form action="MainController" method="POST">
                <input type="text" name="username" placeholder="Username" required />
                <input type="text" name="name" placeholder="Full Name" required />
                <input type="hidden" name="role" value="Customer" />
                <input type="text" name="phone" placeholder="Phone Number" required />
                <input type="text" name="street" placeholder="Street" required />
                <input type="text" name="city" placeholder="City" required />
                <input type="text" name="state" placeholder="State"  />
                <input type="text" name="postalcode" placeholder="Postal Code" required />
                <input type="text" name="country" placeholder="Country" required />
                <input type="password" name="password" placeholder="Password" required />
                <input type="password" name="confrimpassword" placeholder="Confirm Password" required />
                <input type="submit" value="Create" name="action" />
            </form>
            <div class="message">${requestScope.MESS}</div>
            <a href="login.jsp" class="back-link">Go back</a>
        </div>
    </body>
</html>
