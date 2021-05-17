<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role Form</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand text-white"><h5>Online Banking Supreme</h5></a>
		<div>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/home">Home</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/branchForm">Branches</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/userForm">Users</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/customerForm">Customer
						Profile</a></li>
				<sec:authorize access="hasAuthority('User')">
					<li class="nav-item"><a class="nav-link"
						href="/withdrawTransferDeposit">Transfer/Deposit</a></li>
				</sec:authorize>
				<sec:authorize access="hasAuthority('Admin')">
					<li class="nav-item"><a class="nav-link" href="/roleForm">Roles</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/accountForm">Accounts</a>
					</li>
				</sec:authorize>
				<li class="nav-item"><a class="nav-link" href="/login">Login</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/logout">Logout</a>
				</li>
			</ul>
		</div>
	</nav>
	<div align="center">
		<frm:form action="saveRole" method="post" modelAttribute="role">
			<table>
				<tr>
					<th colspan='2'>Role Details Form</th>
				</tr>
				<tr>
					<td>Role Id:</td>
					<td><frm:input type='text' name='roleId' path='roleId'
							value="${role.roleId }" readonly="true" /></td>
				</tr>
				<tr>
					<td>Role Name:</td>
					<td><frm:input type='text' name='name' path='name' /></td>
					<td><frm:errors path="name"  cssStyle="color:red;"/></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit" /></td>
				</tr>
			</table>

		</frm:form>

		<div>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<sec:authorize access="hasAuthority('Admin')">
						<th colspan="2">Actions</th>
					</sec:authorize>
				</tr>

				<c:forEach items="${roles }" var="role">
					<tr>
						<td>${role.roleId }</td>
						<td>${role.name }</td>
						<sec:authorize access="hasAuthority('Admin')">
							<td><a href="/deleteRole?roleId=${role.roleId }">Delete</a></td>
							<td><a href="/updateRole?roleId=${role.roleId }">Update</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>
