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
<title>Branch Form</title>
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
		<sec:authorize access="hasAuthority('Admin')">
			<frm:form action="saveBranch" method="post" modelAttribute="branch">
				<table>
					<tr>
						<th colspan='2'>Branch Details Form</th>
					</tr>
					<tr>
						<td>Branch Id:</td>
						<td><frm:input type='text' name='branchId' path='branchId'
								value="${branch.branchId }" readonly="true" /></td>
						<td><frm:errors path="branchId"  cssStyle="color:red;"/></td>
					</tr>
					<tr>
						<td>Branch Name:</td>
						<td><frm:input type='text' name='branchName'
								path='branchName' /></td>
						<td><frm:errors path="branchName"  cssStyle="color:red;"/></td>
					</tr>


					<tr>
						<td>Address Line 1:</td>
						<td><frm:input type='text' name='addressline1'
								path='branchAddress.addressline1' /></td>
						<td><frm:errors path="branchAddress.addressline1"  cssStyle="color:red;"/></td>
					</tr>

					<tr>
						<td>Address Line 2:</td>
						<td><frm:input type='text' name='addressline2'
								path='branchAddress.addressline2' /></td>
						<td><frm:errors path="branchAddress.addressline2"  cssStyle="color:red;"/></td>
					</tr>

					<tr>
						<td>City:</td>
						<td><frm:input type='text' name='city'
								path='branchAddress.city' /></td>
								<td><frm:errors path="branchAddress.city"  cssStyle="color:red;"/></td>
					</tr>

					<tr>
						<td>State:</td>
						<td><frm:input type='text' name='state'
								path='branchAddress.state' /></td>
								<td><frm:errors path="branchAddress.state"  cssStyle="color:red;"/></td>
					</tr>

					<tr>
						<td>Zip Code:</td>
						<td><frm:input type='text' name='zip'
								path='branchAddress.zip' /></td>
									<td><frm:errors path="branchAddress.zip"  cssStyle="color:red;"/></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Submit" /></td>
					</tr>
				</table>

			</frm:form>
		</sec:authorize>
		<div>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Address 1</th>
					<th>Address 2</th>
					<th>City</th>
					<th>State</th>
					<th>Zip Code</th>
					<sec:authorize access="hasAuthority('Admin')">
						<th colspan="2">Actions</th>
					</sec:authorize>
				</tr>

				<c:forEach items="${branches }" var="branch">
					<tr>
						<td>${branch.branchId }</td>
						<td>${branch.branchName }</td>
						<td>${branch.branchAddress.addressline1 }</td>
						<td>${branch.branchAddress.addressline2 }</td>
						<td>${branch.branchAddress.city }</td>
						<td>${branch.branchAddress.state }</td>
						<td>${branch.branchAddress.zip }</td>
						<sec:authorize access="hasAuthority('Admin')">
							<td><a href="/deleteBranch?branchId=${branch.branchId }">Delete</a></td>
							<td><a href="/updateBranch?branchId=${branch.branchId }">Update</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>
