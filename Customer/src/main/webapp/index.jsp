
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Log In</title>
<style>
body {
	font-family: Arial, sans-serif;
	display: grid;
	justify-content: center;
}

.header {
	display: flex;
	justify-content: center;
	font-size: 4rem;
	font-weight: bolder;
	color: #289cc0;
	margin-top: 100px;
	margin-bottom: 50px;
	align-content: center;
}

.form {
	width: auto;
}

.form-grid1 {
	display: flex;
	gap: 20px;
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 10px;
	margin-top: 10px;
	color: black;
}

a {
	text-decoration: none;
	color: #289cc0;
}

input {
	width: 100%;
	padding: 8px 0px 8px 8px;
	border: 1px solid #ffffff;
	border-radius: 5px;
	outline: none;
}

input:invalid {
	border: 1px solid rgb(255, 0, 0);
}

input:valid {
	border: 1px solid rgb(106, 255, 0);
}

button {
	margin-top: 10px;
	padding: 13px 20px;
	background-color: rgb(0, 0, 0);
	color: rgb(255, 255, 255);
	border: none;
	border-radius: 5px;
	cursor: pointer;
	width: fit-content;
}

button[type="submit"]:hover, button[type="button"]:hover {
	background-color: #289cc0;
	color: #ffffff;
}

button:active {
	scale: 0.8;
}

.warning {
	color: red;
	display:flex;
	font-size: 1rem;
}
</style>
</head>
<body>
	<span class="header">Log in </span>
	<div class="login" id="login">
		<form class="form" id="form1" action="login" method="post">
			<div class="form-grid">
				<div>
					<label for="email">Email or Phone</label> <input type="text"
						id="email" name="email" required placeholder="Email"
						pattern="[a-z0-9._%+\-]+@[a-z0-9.\-]+\.[a-z]{2,}$">
				</div>
				<div>
					<label for="password">Password</label> <input type="password"
						id="password" name="password" required placeholder="password"
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
				</div>
			</div>
			<span th:text="${param.error}"></span>
			<div class="form-grid1">
				<button type="submit" value="save" name="register">Log In</button>
			</div>
		</form>
	</div>
</body>
</html>