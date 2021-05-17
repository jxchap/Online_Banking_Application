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
<title>Account Form</title>
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
		<frm:form action="saveAccount" method="post" modelAttribute="account">
			<table>
				<tr>
					<th colspan='2'>New Account Form</th>
				</tr>
				<tr>
					<td>Account Id:</td>
					<td><frm:input type='text' name='accountId' path='accountId'
							value="${account.accountId }" readonly="true" /></td>
							<td><frm:errors path="accountId"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Account Type:</td>
					<td><c:forEach items="${accountTypes}" var="accountType">
							<frm:radiobutton name="${accountType}" path="accountType"
								label="${accountType}" value="${accountType}" />&nbsp;
					</c:forEach></td>
					<td><frm:errors path="accountType"  cssStyle="color:red;"/></td>
				</tr>
				<tr>

					<td>Account Branch:</td>
					<td><frm:select path="accountBranch">

							<c:forEach items="${accountBranches}" var="branch">
								<frm:option value="${branch}">${branch.branchName}</frm:option>
							</c:forEach>



						</frm:select></td>
						<td><frm:errors path="accountBranch"  cssStyle="color:red;"/></td>
				</tr>

				<tr>

					<td>Customer Id:</td>
					<td><frm:select path="accountCustomer">

							<c:forEach items="${customerAccounts}" var="customerAccount">
								<frm:option value="${customerAccount }">${customerAccount.customerName}-${customerAccount.customerMobile}</frm:option>
							</c:forEach>

						</frm:select></td>
						<td><frm:errors path="accountCustomer"  cssStyle="color:red;"/></td>
				</tr>
				
				<tr>
					<td>Account Balance:</td>
					<td><frm:input type='number' name='accountBalance' path='accountBalance' min = "0" required="true"/></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit" /></td>
						<td><frm:errors path="accountBalance"  cssStyle="color:red;"/></td>
				</tr>
			</table>

		</frm:form>
	</div>


</body>
</html>
