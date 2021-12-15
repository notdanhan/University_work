<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Students Viewer</title>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<style>
			table {
				border: 1px solid #000;
			}
			table tr td {
				border: 1px solid #000;
				padding: 5px;
				margin:auto;
			}
		</style>
	</head>
	<body>
		<h1>Students Table</h1>
		<table>
			<thead>
				<tr>
					<td>ID</td>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Email</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${Students}">
					<tr>
						<td>${student.id}</td>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.emailAddress}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a href="index.jsp">Home</a>
	</body>
</html>