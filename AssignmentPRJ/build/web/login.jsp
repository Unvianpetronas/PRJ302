<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 300px;
                text-align: center;
            }
            h1 {
                color: #333;
            }
            input[type="text"], input[type="password"] {
                width: 100%;
                padding: 10px;
                margin: 8px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }
            input[type="submit"], input[type="reset"] {
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 10px;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            input[type="reset"] {
                background-color: #f44336;
            }
            input[type="reset"]:hover {
                background-color: #d32f2f;
            }
            .message {
                color: red;
                margin-top: 10px;
                font-weight: bold;
            }
            .link {
                display: inline-block;
                padding: 8px 15px;
                margin-top: 10px;
                background-color: gray;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .link:hover {
                background-color: darkgray;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Login</h1>
            <form action="MainController" method="POST">
                <input type="text" name="Username" placeholder="Username" required />
                <input type="password" name="Password" placeholder="Password" required />
                <input type="submit" name="action" value="Login" />
                <input type="reset" value="Reset" />
            </form>
            <div class="message">${requestScope.MESSAGE}</div>
            <a href="CreateUser.jsp" class="link">Create New User?</a>
        </div>
    </body>
</html>
