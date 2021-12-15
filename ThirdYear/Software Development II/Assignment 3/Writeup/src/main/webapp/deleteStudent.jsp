<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Student</title>
</head>
<body>
	<form action="deleteStudentServlet">
	<h1>Delete</h1>
		<label for="id">Delete student where ID =</label>
		<input type="number" id="id" name="id">
		<button type="submit">Delete</button>
	</form>
	<a href="index.jsp">Home</a>
</body>
</html>