<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand text-white"><h5>Online Banking Supreme</h5></a>
		<div>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/branchForm">Branches</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/userForm">Users</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/customerForm">Customer
						Profile</a></li>
				<sec:authorize access="hasAuthority('User')">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/withdrawTransferDeposit">Transfer/Deposit</a></li>
				</sec:authorize>
				<sec:authorize access="hasAuthority('Admin')">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/roleForm">Roles</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/accountForm">Accounts</a>
					</li>
				</sec:authorize>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
				</li>
			</ul>
		</div>
	</nav>


</body>
</html>