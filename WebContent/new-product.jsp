<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList" %>


<jsp:useBean id="shopListBean" scope="request" class="bean.ShopListBean"></jsp:useBean>


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
		
			<% if (request.getAttribute("error") != null) { %>
				</br>
			<%= request.getAttribute("error") %>

			<% } else { %>
			
			<form>
				<div class="form-group">
					<label for="inputProductName">Product Name</label>
					<input type="text" class="form-control" id="inputProductName" placeholder="Product Name">
				</div>
				<div class="form-group">
					<label for="inputProductPrice">Product Price</label>
					<input type="number" step="0.01" min="0" class="form-control" id="inputProductPrice">
				</div>
				<div class="form-group">
					<label for="inputProductType">Product Type</label>
					<input type="text" class="form-control" id="inputProductType" placeholder="Product Type">
				</div>
				<div class="form-group">
					<label for="inputProductCategory">Product Category</label>
					<input type="text" class="form-control" id="inputProductCategory" placeholder="Product Category">
				</div>
				<div class="form-group">
					<label for="inputSoldIn">Shops to be sold in</label>
					<select class="form-control" id="inputSoldIn" multiple="multiple"">
						<% for (int i=0; i<shopListBean.getShops().size(); i++) { %>
							<option><%= shopListBean.getShops().get(i).getName() %></option>
						<% } %>
					</select>
				</div>
				</br>
				<button type="submit" class="btn btn-primary btn-md">Submit</button>
			</form>
			
			<% } %>
			
		</div>
	</body>
</html>