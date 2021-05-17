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
		<frm:form action="saveUser" method="post" modelAttribute="user">
			<table>
				<tr>
					<th colspan='2'>User Details Form</th>
				</tr>
				<tr>
					<td>User Id:</td>
					<td><frm:input type='text' name='userId' path='userId'
							value="${user.userId }" readonly="true" /></td>
				</tr>
				<tr>
					<td>User Name:</td>
					<td><frm:input type='text' name='username' path='username' /></td>
					<td><frm:errors path="username"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><frm:input type='password' name='password' path='password' /></td>
					<td><frm:errors path="password"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><frm:input type='text' name='email' path='email' /></td>
					<td><frm:errors path="email"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<td><frm:input type='text' name='mobile' path='mobile' /></td>
					<td><frm:errors path="mobile"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Roles:</td>
					<td><c:forEach items="${roles}" var="role"> 
							<frm:checkbox path="roles" value="${role.roleId}" checked="true"/>${role.name}
							
            		</c:forEach></td>
            		<td><frm:errors path="roles"  cssStyle="color:red;"/></td>
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
					<th>Email</th>
					<th>Mobile</th>
					<th>Roles</th>
						<th colspan="2">Actions</th>
				</tr>

				<c:forEach items="${users }" var="user">
					<tr>
						<td>${user.userId }</td>
						<td>${user.username }</td>
						<td>${user.email }</td>
						<td>${user.mobile }</td>
						<td><c:forEach items="${user.roles }" var="userRole">${userRole.name} </c:forEach>
						</td>
						<sec:authorize access="hasAuthority('Admin')">
							<td><a href="/deleteUser?username=${user.username }">Delete</a></td>
						</sec:authorize>
							<td><a href="/updateUser?username=${user.username }">Update</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>
