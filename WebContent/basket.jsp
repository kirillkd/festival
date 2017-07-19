<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="productsBean" scope="request" class="beans.ProductBean"></jsp:useBean>

<%@ page import ="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Basket</title>
</head>
<body>

	<%@include file="header.html" %>
	
	<form action="/festival/UpdateBalanceServlet">
		<div class="container">
			<table class="table table-hover">
    			<thead>
      				<tr>
      					<th>Product Name</th>
        				<th>Item Price</th>
        				<th>Quantity</th>
        				<th>Total Product Price</th>
      				</tr>
    			</thead>
    			<tbody>
    				<%
    				ArrayList<Integer> quantities = (ArrayList<Integer>) request.getAttribute("quantities");
     		 		for(int i = 0; i < quantities.size(); i++){%>
						<tr>
    						<% if (quantities.get(i) > 0) {%>
    							<td><%= productsBean.getProductsList().get(i).getName() %></td>
    							<td><%= productsBean.getProductsList().get(i).getPrice() %></td>
    							<td><%= quantities.get(i) %></td>
    							<td><%= productsBean.getProductsList().get(i).getPrice() * quantities.get(i) %></td>
    						<%} %>
  						</tr>  
 					<% } %>  
      
    			</tbody>
    		</table>
    		
    		<div class="container">
  				<h3>Total Price</h3>
  				<div class="well"><%= productsBean.getSelectedProductsPrice() %></div>
			</div>
			
			<input type="hidden" name="wbID" value="${wristbandId}" />
			<input type="hidden" name="amountID" value="${amountID}" />
			
			<button type = "submit" class = "btn btn-success">Proceed</button> 
		
			<a href="/festival/shops"><button type = "button" class = "btn btn-info">Cancel</button></a>
		</div>		
	</form>
	
</body>
</html>