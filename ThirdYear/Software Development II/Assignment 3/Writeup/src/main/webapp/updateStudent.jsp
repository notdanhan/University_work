<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Update Student</title>
	</head>
	<body>
		<h1>Update</h1>
		<h2>General Updates</h2>
		<form action="updateStudentServlet">
			<table>
				<thead>
					<tr>
						<td>Update</td>
						<td>Data Field</td>
						<td>New Value</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" id="updatefname" name="updatefname"></td>
						<td>First Name</td>
						<td><input type="text" id="fname" name="fname"></td>
					</tr>
					<tr>
						<td><input type="checkbox" id="updatelname" name="updatelname"></td>
						<td>Last Name</td>
						<td><input type="text" id="lname" name="lname"></td>
					</tr>
					<tr>
						<td><input type="checkbox" id="updateemail" name="updateemail">
						<td>Email</td>
						<td><input type="text" id="email" name="email"></td>
					</tr>	
				</tbody>
			</table>
			<label for="on">Where:</label>
			<select name="on" id="on">
				<option value="id">id</option>
				<option value="firstName">First Name</option>
				<option value="lastName">Last Name</option>
				<option value="emailAddress">Email</option>
			</select>
			<label for="param">=</label>
			<input type="text" id="param" name="param"><br>
			<button type="submit">Update Record(s)</button>
		</form>
		<hr>
		<h2>Batch Email Update</h2>
		<form action="updateStudentServlet">
			<label for="newemaildomain">New Email Domain: <em>fname.lname@</em></label>
			<input type="text" name="newemaildomain" id="newemaildomain">
			<button type="submit">Submit Email Update</button>
		</form>
		<hr>
		<a href="index.jsp">Return Home</a>
	</body>
</html>