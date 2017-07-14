<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <jsp:useBean id="productsBean" scope="request" class="beans.ProductBean"></jsp:useBean>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products</title>
</head>
<body>
<%@include file="header.html" %>
<div class="container">
<div class="form-group">
  <label for="usr">Visitor Id:</label>
  <input type="text" class="form-control" id="usr">
</div>
  <table class="table table-hover">
    <thead>
      <tr>
      <th>Product Name</th>
        <th>Price</th>
        <th>Type</th>
		<th>Category</th>
		<th>Quantity</th>
      </tr>
    </thead>
    <tbody>
    <%
      for(int i = 0; i < productsBean.getProductsList().size(); i++){%>
	<tr>
    <td><%= productsBean.getProductsList().get(i).getName() %></td>
    <td><%= productsBean.getProductsList().get(i).getPrice() %></td>
    <td><%= productsBean.getProductsList().get(i).getType() %></td>
    <td><%= productsBean.getProductsList().get(i).getProduct_Category() %></td>
    <td>
     <select id="quantity" name="quantity" style="width: 50px;">
    <option value="0" selected>0</option>
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
  </select>
    </td>   
  </tr>  
 <% } %>  
      
    </tbody>
    </table>
    <a href="./ConfirmPurchaseServlet">
        <button type="button" class="btn btn-info">Proceed</button>
    </a>
   
   
</div>
</body>
</html>