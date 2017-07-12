<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList, utils.Pair" %>
<%@ page import="beans.FestivalEventBean, beans.TicketTypeBean, beans.TicketPriceBean" %>


<jsp:useBean id="festivalEventListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="ticketTypeListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="sexListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="paymentMethodListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>


<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>	
	</head>
		
	<body>
		<title>
			Purchase Ticket
		</title>
		
		<%@include file="header.html" %>
		
		<div class="container" style="padding-bottom:50px">
		
			<% if (request.getAttribute("error") != null) { %>
				<%= request.getAttribute("error") %>
			<% } else { %>
			<form>
				<div class="form-group">					
					<label for="inputFestival_Event">Festival Event</label>
					<select class="form-control" id="inputFestival_Event"">
						<% for(int i=0; i<festivalEventListBean.getItems().size(); i++) { %>
							<option><%= ((FestivalEventBean) festivalEventListBean.getItems().get(i)).getName() %></option>
						<% } %>
					</select>
				</div>
				
				<div class="form-group">	
					<label for="inputTicket_Type">Ticket Type</label>
					<select class="form-control" id="inputTicket_Type"">
						<% for(int i=0; i<ticketTypeListBean.getItems().size(); i++) { %>
							<option><%= ((TicketTypeBean) ((Pair) ticketTypeListBean.getItems().get(i)).getLeft()).getType() %> (<%= String.format("%.2f", ((TicketPriceBean) ((Pair) ticketTypeListBean.getItems().get(i)).getRight()).getPrice()) %> €) </option>
						<% } %>
					</select>
				</div>
				
				<div class="form-group">	
					<label for="inputFirst_Name">First Name</label>
					<input type="text" class="form-control" id="inputFirst_Name" placeholder="First Name">
				</div>
				
				<div class="form-group">	
					<label for="inputLast_Name">Last Name</label>
					<input type="text" class="form-control" id="inputLast_Name" placeholder="Last Name">
				</div>
				
				<div class="form-group">	
					<label for="inputEmail">E-Mail</label>
					<input type="text" class="form-control" id="inputEmail" placeholder="E-Mail">
				</div>
				
				<div class="form-group">	
					<label for="inputPhone">Phone</label>
					<input type="text" class="form-control" id="inputPhone" placeholder="Phone">					
				</div>
				
				<div class="form-group">	
					<label for="inputBirthdate">Birthdate</label>
					<input type="date" class="form-control" id="inputBirthdate" placeholder="Birthdate">					
				</div>
				
				<div class="form-group">	
					<label for="inputSex">Sex</label>
					<select class="form-control" id="inputSex"">
						<% for(int i=0; i<sexListBean.getItems().size(); i++) { %>
							<option><%= sexListBean.getItems().get(i) %></option>
						<% } %>
					</select>		
				</div>				
								
				<div class="form-group">	
					<label for="inputAddress">Address</label>
					<input type="text" class="form-control" id="inputAddress" placeholder="Number Street, County, Federal State Postcode">					
				</div>
				
				<div class="form-group">	
					<label for="inputCountry">Country</label>
					<input type="text" class="form-control" id="inputCountry" placeholder="Country">					
				</div>
				
				<div class="form-group" style="padding-bottom:20px">	
					<label for="inputPayment_Method">Payment Method</label>					
					<select class="form-control" id="inputPayment_Method"">
						<% for(int i=0; i<paymentMethodListBean.getItems().size(); i++) { %>
							<option><%= paymentMethodListBean.getItems().get(i) %></option>
						<% } %>
					</select>		
				</div>
				
				<button type="submit" class="btn btn-primary btn-lg">Submit</button>
				
			</form>	
			<% } %>
		</div>
	</body>
</html>