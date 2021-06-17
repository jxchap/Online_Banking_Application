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
<title>Customer Form</title>
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
	
		<div align="center">
		<frm:form action="saveCustomer" method="post" modelAttribute="customer">
			<table>
				<tr>
					<th colspan='2'>Customer Details Form</th>
				</tr>
				<tr>
					<td>Customer Id:</td>
					<td><frm:input type='text' name='customerId' path='customerId'
							value="${customer.customerId }" readonly="true" /></td>
				</tr>
				<tr>
					<td>Customer Name:</td>
					<td><frm:input type='text' name='customerName' path='customerName' /></td>
					<td><frm:errors path="customerName"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>
					<c:forEach items = "${customerGenders}" var = "gender">
					<frm:radiobutton name="${gender}"  path="gender" label="${gender}" value="${gender}"/>&nbsp;
					</c:forEach>
					</td>
					<td><frm:errors path="gender"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Date of Birth:</td>
					<td><frm:input type='date' name='date' path='dob' /></td>
					<td><frm:errors path="dob"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<td><frm:input type='text' name='customerMobile' path='customerMobile' /></td>
					<td><frm:errors path="customerMobile"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><frm:input type='text' name='customerEmail' path='customerEmail' /></td>
					<td><frm:errors path="customerEmail"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Address Line 1:</td>
					<td><frm:input type='text' name='addressline1' path='customerAddress.addressline1' /></td>
					<td><frm:errors path="customerAddress.addressline1"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Address Line 2:</td>
					<td><frm:input type='text' name='addressline2' path='customerAddress.addressline2' /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><frm:input type='text' name='city' path='customerAddress.city' /></td>
					<td><frm:errors path="customerAddress.city"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><frm:input type='text' name='state' path='customerAddress.state' /></td>
					<td><frm:errors path="customerAddress.state"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Zip:</td>
					<td><frm:input type='text' name='zip' path='customerAddress.zip' /></td>
					<td><frm:errors path="customerAddress.zip"  cssStyle="color:red;"/></td>
				</tr>
				<tr>
					<td>Customer Id Type:</td>
					<td>
					<c:forEach items = "${customerIdTypes}" var = "idType">
					<frm:radiobutton name="${idType}"  path="customerIDType" label="${idType}" value="${idType}"/>&nbsp;
					</c:forEach>
					</td>
					<td><frm:errors path="customerIDType"  cssStyle="color:red;"/></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit" /></td>
				</tr>
			</table>

		</frm:form>

		
	</div>
	
			<div align="center">
			<table border="1">
				<tr>
					<th>Customer Id</th>
					<th>Customer Name</th>
					<th>Gender</th>
					<th>Date of Birth</th>
					<th>Mobile</th>
					<th>Email</th>
					<th>Address Line 1</th>
					<th>Address Line 2</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
					<th>Customer Type</th>
					
						<th colspan="2">Actions</th>
					
				</tr>

				
					<tr>
						<td>${existingCustomer.customerId }</td>
						<td>${existingCustomer.customerName }</td>
						<td>${existingCustomer.gender }</td>
						<td>${existingCustomer.dob }</td>
						<td>${existingCustomer.customerMobile }</td>
						<td>${existingCustomer.customerEmail }</td>
						<td>${existingCustomer.customerAddress.addressline1 }</td>
						<td>${existingCustomer.customerAddress.addressline2 }</td>
						<td>${existingCustomer.customerAddress.city }</td>
						<td>${existingCustomer.customerAddress.state }</td>
						<td>${existingCustomer.customerAddress.zip }</td>
						<td>${existingCustomer.customerIDType }</td>
							<td><a href="${pageContext.request.contextPath}/updateCustomer?customerId=${existingCustomer.customerId }">Update</a></td>
					</tr>
				

			</table>
		</div>
	
</body>
</html>
