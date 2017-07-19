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
		
		<%@include file="header.html" %>
		
		<div class="container" style="padding-bottom:50px">
			
			<% if (request.getAttribute("error") != null) { %>			
				<div class="alert alert-danger alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<%= request.getAttribute("error") %>
				</div>
			<% } %>
		
			<div class="container-fluid">
			
				<form class="form-inline" action="/festival/checkUsername" method="post">
					<label>Please enter username and password:</label>
					<input type="text" required class="form-control" placeholder="username" id="username" name="username">
					<input type="password" required class="form-control" placeholder="password" id="password_id" name="password">
					<button type="submit" class="btn btn-primary navbar-btn">Login</button>	
				</form>
			
			</div>
			

	
	
					
	</body>
</html>
