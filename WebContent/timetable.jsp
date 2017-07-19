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
					<button type="submit" class="btn btn-primary navbar-btn">Submit</button>	
				</form>
			
			</div>
			
			
			<% if (((ArrayList) request.getAttribute("bands")).size() > 0) { %>
				<div class="container">
					
					<form action="/festival/createTimetable" method="post">			
						
						<input type="hidden" name="username" value="<%= request.getAttribute("username") %>" >
	
							
						<h3>Please rate your bands (5 best - 1 worst):</h3>
						<input type="hidden" name="bands" value="<%= bands.size()%>" >
							
						<% for(int i = 0; i < bands.size() ; i++) { %>					
						
							<div class="form-group">
								<label><%= ((BandBean) bands.get(i)).getName() %></label>		
								
								<fieldset>
									<input type="hidden" name="<%= i %>.band" value="<%= ((BandBean) bands.get(i)).getName() %>">
									
									<label class="radio-inline" for="1"><input type="radio" name="<%= i %>.preferences" id="1" value="1">1</label>
									<label class="radio-inline" for="2"><input type="radio" name="<%= i %>.preferences" id="2" value="2">2</label>
									<label class="radio-inline" for="3"><input type="radio" name="<%= i %>.preferences" id="3" value="3" checked>3</label>
									<label class="radio-inline" for="4"><input type="radio" name="<%= i %>.preferences" id="4" value="4">4</label>
									<label class="radio-inline" for="5"><input type="radio" name="<%= i %>.preferences" id="5" value="5">5</label>
								</fieldset>
							</div>
						
						<% } %>
							  
						<button type="submit" class="btn btn-primary navbar-btn">Submit</button>
						
					</form>
				</div>
			<% } %>

			
			<% if (((ArrayList) request.getAttribute("times")) != null) { %>
				<div class="container">
					
					<input type="hidden" name="username" value="<%= request.getAttribute("username") %>" >
										
					<h2>Your timetable</h2>
					
					<table class="table table-bordered">					
						<tr>
							<th> Band name </th>
							<th> Date </th>
							<th> Start time </th>
							<th> End time </th>
						</tr>
						
						<% for(int i = 0; i < (((ArrayList) request.getAttribute("times")).size()) ; i++) { %>					
							
							<div class="form-group">
								
									<tr>
										<td><%= ((BandBean) ((ArrayList) request.getAttribute("times")).get(i)).getName() %></td>
										<td><%= ((BandBean) ((ArrayList) request.getAttribute("times")).get(i)).getTimeslot_date() %></td>
										<td><%= ((BandBean) ((ArrayList) request.getAttribute("times")).get(i)).getTimeslot_start() %></td>
										<td><%= ((BandBean) ((ArrayList) request.getAttribute("times")).get(i)).getTimeslot_end() %></td>
									</tr>
								
							</div>
						<% } %>
						
					</table>
					
					<h3>Enjoy your festival!</h3>
					
				</div>
				
			<% } %>
			
		</div>				
	</body>
</html>
