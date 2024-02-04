<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	max-width: 100%;
	padding: 0;
}

.scrollable-cell {
	max-width: 100px; /* Set the maximum width for the cell */
	overflow-x: auto;
	white-space: nowrap; /* Prevent text wrapping */
}

input, select {
	padding: 8px;
}

span {
	float: right;
	margin: 10px;
}

table {
	width: 100%;
	min-width: min-content;
	max-width: 100%;
}

form {
	margin: 10px 0;
}

a {
	text-decoration: none;
	color: #007BFF;
	margin-right: 10px;
}

button {
	background-color: #007BFF;
	color: white;
	border: none;
	padding: 8px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
</head>

<body>
<span th:text="${message}"></span>
	<span><a href="/logout">Log out >>>>></a></span>
	<form action="AddCustomer" method="get">
		<button type="submit" value="add Customer">add Customer</button>
	</form>
	<span><a href="/customer/sync"><button type="submit">Sync</button></a></span>
	<form id="searchForm" action="/customer/search" method="get">
		<label for="criteria">Search Criteria:</label> <select id="criteria"
			name="searchCriteria">
			<option value="firstName"
				th:selected="${session.searchCriteria == 'firstName'}">First
				Name</option>
			<option value="city"
				th:selected="${session.searchCriteria == 'city'}">City</option>
			<option value="email"
				th:selected="${session.searchCriteria == 'email'}">Email</option>
			<option value="phoneNumber"
				th:selected="${session.searchCriteria == 'phoneNumber'}">Phone
				Number</option>
		</select><label for="searchTerm">Search Term:</label> <input type="text"
			id="searchTerm" name="searchTerm" th:value="${session.searchTerm}"
			required> <select id="rec" name="records">
			<option th:selected="${records == 1}" value="1">1</option>
			<option th:selected="${records == 5}" value="5">5</option>
			<option th:selected="${records == 10}" value="10">10</option>
			<option th:selected="${records == 15}" value="15">15</option>
			<option th:selected="${records == 20}" value="20">20</option>
		</select>

		<button type="submit">Search</button>
	</form>

	<table id="customerTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Street</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr th:each="customer : ${matchingCustomers}">
				<td th:text="${customer.id}"></td>
				<td th:text="${customer.firstName}"></td>
				<td th:text="${customer.lastName}"></td>
				<td class="scrollable-cell" th:text="${customer.street}"></td>
				<td class="scrollable-cell" th:text="${customer.address}"></td>
				<td th:text="${customer.city}"></td>
				<td th:text="${customer.state}"></td>
				<td th:text="${customer.email}"></td>
				<td th:text="${customer.phoneNumber}"></td>
				<td><a th:href="@{edit/{id}(id=${customer.id})}"
					class="edit-btn">Edit</a> <a
					th:href="@{DeleteCustomer/{id}(id=${customer.id})}"
					class="delete-btn">Delete</a></td>
			</tr>
		</tbody>
	</table>

	<th:block th:if="${totalPages != null and totalPages > 0}">
		<ul>
			<li th:each="i : ${#numbers.sequence(0, totalPages - 1)}"
				th:classappend="${currentPage == i ? 'active' : ''}"><a
				class="page-link"
				th:if="${session.searchCriteria != null and session.searchTerm != null}"
				th:href="@{/customer/search(searchCriteria=${session.searchCriteria}, searchTerm=${session.searchTerm}, pageNumber=${i},records=${records})}"
				th:text="${i + 1}"></a> <span
				th:unless="${session.searchCriteria != null and session.searchTerm != null}"
				th:text="${i + 1}">Page Number</span></li>
		</ul>
	</th:block>

	<script>
		document.getElementById('rec').addEventListener('change', function() {
			document.getElementById('searchForm').submit();
		});
	</script>





</body>
</html>
