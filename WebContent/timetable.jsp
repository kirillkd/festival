<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, beans.BandBean" %>
<jsp:useBean id="bands" scope="request" class="java.util.ArrayList"></jsp:useBean>

<!DOCTYPE html>

<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>	
	</head>
		
	<body>
		<title>
			Music Festival Management System
		</title>
		
		<div class="container">
	<h1>Music Festival Management System</h1>
	</br>

	<nav class="navbar navbar-default">

		<div class="container-fluid">

			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">Start page</a>
			</div>

		</div>
	</nav>

	<nav class="navbar navbar-default">

		<div class="container-fluid">

			<div class="navbar-header">
				<a class="navbar-brand"> Username: <input type="text" required
					placeholder="Please insert username" id="username" name="username">
				</a>
			</div>


			<div class="nav navbar-nav navbar-right navbar-collapse">
				<form action="/festival/checkUsername" method="post">
				<button type="submit"
						class="btn btn-danger navbar-btn pull-right" data-toggle="modal"
						data-target="#checkUsername">okay</button>
				</form>
				
				<% if (request.getAttribute("bands") == null) { %>
					<p> <%= ((BandBean) bands.get(0)).getName() %> </p>
				<% } %>
			</div>

		</div>
	</nav>

</div>

			
	</body>
</html>

