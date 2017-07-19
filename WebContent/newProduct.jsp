<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList, java.util.Optional, beans.VendorBean, beans.ShopBean" %>


<jsp:useBean id="productBean" scope="request" class="beans.ProductBean"></jsp:useBean>
<jsp:useBean id="vendorBean" scope="request" class="beans.VendorBean"></jsp:useBean>
<jsp:useBean id="vendorListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="shopListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="categoryListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>


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
			
				<div class="alert alert-danger alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<%= request.getAttribute("error") %>
				</div>
				
			<% } %> 
			<% if ((LinkedList) vendorListBean.getItems() != null) { %>
			
				<b>Please select a vendor:</b><br>
				<% for (int i=0; i<vendorListBean.getItems().size(); i++) { %>
					<a href="/festival/new-product?vendor_id=<%= ((VendorBean) vendorListBean.getItems().get(i)).getVendor_id() %>">
					<%= ((VendorBean) vendorListBean.getItems().get(i)).getVendor_id() %>: <%= ((VendorBean) vendorListBean.getItems().get(i)).getName() %>
					</a><br>
				<% } %>
				
			<% } else { %>
			
				<form action="/festival/new-product" method="post">
					<input type="hidden" name="inputVendorId" value="<%= vendorBean.getVendor_id() %>">
					<div class="form-group">
						<label for="inputProductName">Product Name</label>
						<input type="text" required class="form-control" id="inputProductName" name="inputProductName" placeholder="Product Name" value="<%= Optional.ofNullable(productBean.getName()).orElse("") %>">
					</div>
					<div class="form-group">
						<label for="inputProductPrice">Product Price</label>
						<input type="number" required step="0.01" min="0" class="form-control" id="inputProductPrice" name="inputProductPrice" value="<%= Optional.ofNullable(productBean.getPrice()) %>">
					</div>
					<div class="form-group">
						<label for="inputProductType">Product Type</label>
						<input type="text" required class="form-control" id="inputProductType" name="inputProductType" placeholder="Product Type" value="<%= Optional.ofNullable(productBean.getType()).orElse("") %>">
					</div>
					<div class="form-group">
						<label for="inputProductCategory">Product Category</label>
						<select class="form-control" id="inputProductCategory" name="inputProductCategory">
							<% for (int i=0; i<categoryListBean.getItems().size(); i++) { %>
								<option><%= categoryListBean.getItems().get(i) %></option>
							<% } %>
						</select>
					</div>
					<div class="form-group">
						<label for="inputSoldIn">Shops to be sold in</label>
						<select required class="form-control" id="inputSoldIn" name="inputSoldIn" multiple="multiple">
							<% for (int i=0; i<shopListBean.getItems().size(); i++) { %>
								<option value="<%= ((ShopBean) shopListBean.getItems().get(i)).getShop_id() %>"><%= ((ShopBean) shopListBean.getItems().get(i)).getName() %></option>
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