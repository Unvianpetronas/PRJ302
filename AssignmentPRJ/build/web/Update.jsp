<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            width: 50%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px gray;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: gray;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            width: 100%;
            border-radius: 5px;
        }
        button:hover {
            background-color: darkgray;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update User Information</h2>
        <form action="UpdateController" method="POST">
            <input type="hidden" name="Userid" value="${param.Userid}" />
            
            <label>Name:</label>
            <input type="text" name="name" value="${param.name}" required />
            
            <label>Phone:</label>
            <input type="text" name="Phone" value="${param.Phone}" required />
            
            <label>Street:</label>
            <input type="text" name="street" value="${param.street}" required />
            
            <label>City:</label>
            <input type="text" name="city" value="${param.city}" required />
            
            <label>State:</label>
            <input type="text" name="state" value="${param.state}" required />
            
            <label>Postal Code:</label>
            <input type="text" name="postalcode" value="${param.postalcode}" required />
            
            <label>Country:</label>
            <input type="text" name="country" value="${param.country}" required />
            
            <input type="hidden" name="addressID" value="${param.addressID}" />
          
            <button type="submit" value="Update" name="action"  >Update</button>
        </form>
            <h1 style="color: activecaption">${requestScope.MESS}</h1>
            <a href="admin.jsp" style="display: inline-block; padding: 5px 10px; background-color: gray; color: black; text-decoration: none; border-radius: 5px;">Go back!</a>
    </div>
</body>
</html>