<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Splash!</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Create Student</h1>
    <form action="createStudent">
    	<label for="firstName">First Name:</label>
    	<input type="text" name="firstName" id="firstName"><br>
    	<label for="lastName">Last Name:</label>
    	<input type="text" name="lastName" id="lastName"><br>
    	<button type="submit">Submit</button><br>
    </form>
   	<br><a href="index.jsp">Return Home</a>
</body>
</html>