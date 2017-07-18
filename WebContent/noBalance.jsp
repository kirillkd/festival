<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Not enough balance</title>
</head>
<body>
	<%@include file="header.html" %>
	<div class="container">
		<div class="container">
  			<div class="well">You do not have enough balance on your wristband.
  			 Try selecting other items or top up your wristband.</div>
		</div>
	
		<a href="/festival/shops"><button type = "submit" class = "btn btn-info">Go back</button></a>
	</div>
</body>
</html>