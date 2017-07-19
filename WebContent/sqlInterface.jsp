<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList" %>


<jsp:useBean id="sqlResult" scope="request" class="beans.SQLResultBean"></jsp:useBean>


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
		
		<div class="container">
			
			<form action="/festival/sql-interface" method="post">
				<div class="form-group">
					<label for="inputQuery">Enter a SQL Query for the festival database:</label>
					<textarea class="form-control" rows="10" name="inputQuery" id="inputQuery" placeholder="Enter SQL Query ..."><% if (request.getAttribute("inputQuery") != null) { %><%= request.getAttribute("inputQuery") %><% } %></textarea>
				</div>
				
				<button type="submit" class="btn btn-primary btn-md">Execute</button>
				
			</form>
			
			<% if (request.getAttribute("error") != null) { %>
				</br>
			<%= request.getAttribute("error") %>

			<% } else if (sqlResult.getColumnNames() != null) { %>
        		<br>
				<table class="table">
					<tr>
					<% for (int i=0; i < sqlResult.getColumnNames().size(); i++) { %>
						<th><%= sqlResult.getColumnNames().get(i) %></th>
					<% } %>
					</tr>
					
					<% for (int i=0; i < sqlResult.getColumnValues().size(); i++) { %>
					<tr>
						<% for (int j=0; j < sqlResult.getColumnValues().get(i).size(); j++) { %>
							<td><%= sqlResult.getColumnValues().get(i).get(j) %></td>
						<% } %>
					</tr>
					<% } %>
					
				</table>

        	<% } %>
			
		</div>
			
	</body>
</html>