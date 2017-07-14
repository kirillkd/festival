<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <jsp:useBean id="shopsBean" scope="request" class="beans.ShopBean"></jsp:useBean>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Visitor</title>
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
      <th>Shop Id</th>
        <th>Shop Name</th>
        <th>Category</th>
      </tr>
    </thead>
    <tbody>
    <%
      for(int i = 0; i < shopsBean.getShopList().size(); i++){%>
	<tr>
    <td><%= shopsBean.getShopList().get(i).getShop_id() %></td>
    <td><a href="./ProductsServlet?shopid=<%= shopsBean.getShopList().get(i).getShop_id()%>"><%= shopsBean.getShopList().get(i).getName() %></a></td>
    <td><%= shopsBean.getShopList().get(i).getCategory() %></td>
  </tr>  
 <% } %>  
      
    </tbody>
</div>

</body>
</html>