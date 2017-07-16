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
			
			<% if (request.getAttribute("error") != null) { %>
				<%= request.getAttribute("error") %>
			<% } %>
		
			<nav class="navbar navbar-default">

			<div class="container-fluid">

				<ul class="nav navbar-nav">
					<li><a>Please enter username and password: </a>
					<li>
				</ul>

				<form class="navbar-form" action="/festival/checkUsername"
					method="post">
					<input type="text" required class="form-control"
						placeholder="username" id="username" name="username">
						<input type="password" required class="form-control"
						placeholder="password" id="password_id"
						name="password">
						<button type="submit" class="btn btn-primary navbar-btn">Submit</button>
						
				</form>
				
				<form class="navbar-form" action="/festival/createTimetable" method="post">			
					<input type="hidden" name="username" value="<%= request.getAttribute("username") %>" >
					
					<% if (((ArrayList) request.getAttribute("bands")).size() > 0) { %>
						
						<input type="hidden" name="bands" value="<%= bands.size()%>" >
						
						<% for(int i = 0; i < bands.size() ; i++){%>					
							<p> <%= ((BandBean) bands.get(i)).getName() %> </p>
							
														
							<fieldset>
							<input type="hidden" name="<%= i %>.band" value="<%= ((BandBean) bands.get(i)).getName() %>">
							
							<input type="radio" name="<%= i %>.preferences" id="1" value="1">
							<label for="1"> 1</label><br>
							<input type="radio" name="<%= i %>.preferences" id="2" value="2">
							<label for="2"> 2</label><br>
							<input type="radio" name="<%= i %>.preferences" id="3" value="3">
							<label for="3"> 3</label><br>
							<input type="radio" name="<%= i %>.preferences" id="4" value="4">
							<label for="4"> 4</label><br>
							<input type="radio" name="<%= i %>.preferences" id="5" value="5">
							<label for="5"> 5</label><br>
							</fieldset>
							
						 <% } %>
						  
						  	<button type="submit" class="btn btn-primary navbar-btn">Submit</button>
						 
					<% } %>
				 </form>	
					
										
		
				</div>
			</nav>
		</div>			
	</body>
</html>
