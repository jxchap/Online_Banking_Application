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
		<table border="1">
			<tr>
				<th>Account ID</th>
				<th>Account Type</th>
				<th>Branch</th>
				<th>Balance</th>
				<th>Date Opened</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${customerAccounts }" var="account">
				<tr>
					<td>${account.accountId }</td>
					<td>${account.accountType }</td>
					<td>${account.accountBranch.branchName }</td>
					<td>${account.accountBalance }</td>
					<td>${account.accountDateOpened }</td>
					<td><c:choose>
							<c:when test="${account.accountType.equals(accountTypes[0])}">
								<ul>
									<li><a
										href="renderAccountDeposit?accountId=${account.accountId }">Deposit</a>
									</li>
									<li><a href="renderAccountTransfer?accountId=${account.accountId }">Transfer</a>
									</li>
									<li><a
										href="renderAccountWithDrawal?accountId=${account.accountId }">Withdraw</a>
									</li>
								</ul>




							</c:when>
							<c:when test="${account.accountType.equals(accountTypes[1])}">
								<ul>
									<li><a
										href="renderAccountDeposit?accountId=${account.accountId }">Deposit</a>
									</li>
									<li><a href="renderAccountTransfer?accountId=${account.accountId }">Transfer</a>
									</li>
									<li><a
										href="renderAccountWithDrawal?accountId=${account.accountId }">Withdraw</a>
									</li>
								</ul>
							</c:when>

							<c:when test="${account.accountType.equals(accountTypes[2])}">
								<ul>
									<li><a
										href="renderLoanPayment?accountId=${account.accountId }">Pay
											into Loan</a></li>

								</ul>

							</c:when>
						</c:choose></td>
				</tr>
			</c:forEach>

		</table>
	</div>


	<c:if test="${accountForDeposit != null}">
		<frm:form action="saveDeposit" method="post" modelAttribute = "bankTransaction">
			<div>
				<table>
					<tr>
						<td>Account #</td>
						<td>Amount to Deposit</td>
					</tr>
					<tr>
						<td>${accountForDeposit.accountId }<frm:input type="hidden"
							name='accountId' value="${accountForDeposit.accountId }" path="bankTransactionToAccount"/></td>

						<td><frm:input type="number" min="0" name='depositAmount' path="transactionAmount"/></td>
						<td><input type="submit" value="Submit" /></td>
						<td><frm:errors path="transactionAmount"  cssStyle="color:red;"/></td>
						<td><frm:errors path="bankTransactionToAccount"  cssStyle="color:red;"/></td>

					</tr>
				</table>
			</div>
		</frm:form>
	</c:if>

	<c:if test="${accountForWithDrawal != null}">
		<frm:form action="saveWithdrawal" method="post" modelAttribute = "bankTransaction">
			<div>
				<table>
					<tr>
						<td>Account #</td>
						<td>Amount to Withdraw</td>
					</tr>
					<tr>
						<td>${accountForWithDrawal.accountId }<frm:input type="hidden"
							name='accountId' value="${accountForWithDrawal.accountId }" path="bankTransactionFromAccount"/></td>
							
						<td><frm:input type="number" min="0" name='withdrawalAmount' path = "transactionAmount"/></td>
						<td><input type="submit" value="Submit" /></td>
						<td><frm:errors path="transactionAmount"  cssStyle="color:red;"/></td>
						<td><frm:errors path="bankTransactionFromAccount"  cssStyle="color:red;"/></td>
					</tr>
				</table>
			</div>
		</frm:form>
	</c:if>

	<c:if test="${accountForLoanPayment != null}">
		<frm:form action="saveLoanPayment" method="post" modelAttribute = "bankTransaction">
			<div>
				<table>
					<tr>
						<td>Account #</td>
						<td>Amount to Pay Into Loan</td>
					</tr>
					<tr>
						<td>${accountForLoanPayment.accountId }<frm:input type="hidden"
							name='accountId' value="${accountForLoanPayment.accountId }" path="bankTransactionToAccount"/></td>
							
						<td><frm:input type="number" min="0" name='loanPaymentAmount' path="transactionAmount"/></td>
						<td><input type="submit" value="Submit" /></td>
						<td><frm:errors path="transactionAmount"  cssStyle="color:red;"/></td>
						<td><frm:errors path="bankTransactionFromAccount"  cssStyle="color:red;"/></td>
					</tr>
				</table>
			</div>
		</frm:form>
	</c:if>


	<c:if test="${accountForTransfer != null}">
		<frm:form action="saveTransfer" method="post" modelAttribute = "bankTransaction">
			<div>
				<table>
					<tr>
						<td>From Account #</td>
						<td>To Account #</td>
						<td>Amount to Transfer</td>
					</tr>
					<tr>
						<td>${accountForTransfer.accountId }<frm:input type="hidden"
							name='fromAccountId' value="${accountForTransfer.accountId }" path="bankTransactionFromAccount"/></td>
						<td><frm:input type="number" min="0"
							name='toAccountId'  path="bankTransactionToAccount" required="true"/></td>
							
						<td><frm:input type="number" min="0" name='transferAmount' path="transactionAmount"/></td>
						<td><input type="submit" value="Submit" /></td>
						<td><frm:errors path="transactionAmount"  cssStyle="color:red;"/></td>
						<td><frm:errors path="bankTransactionFromAccount"  cssStyle="color:red;"/></td>
						<td><frm:errors path="bankTransactionToAccount"  cssStyle="color:red;"/></td>
					</tr>
				</table>
			</div>
		</frm:form>
	</c:if>



</body>
</html>
