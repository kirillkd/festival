<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList, java.util.Optional, java.sql.Date, utils.Pair, utils.DateConverter" %>
<%@ page import="beans.FestivalEventBean, beans.TicketTypeBean, beans.TicketPriceBean, beans.VisitorBean, beans.TicketBean" %>


<jsp:useBean id="festivalEventListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="ticketTypeListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="sexListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>
<jsp:useBean id="paymentMethodListBean" scope="request" class="beans.GenericListBean"></jsp:useBean>

<jsp:useBean id="festivalEventBean" scope="request" class="beans.FestivalEventBean"></jsp:useBean>
<jsp:useBean id="ticketTypeBean" scope="request" class="beans.TicketTypeBean"></jsp:useBean>
<jsp:useBean id="ticketPriceBean" scope="request" class="beans.TicketPriceBean"></jsp:useBean>
<jsp:useBean id="visitorBean" scope="request" class="beans.VisitorBean"></jsp:useBean>
<jsp:useBean id="ticketBean" scope="request" class="beans.TicketBean"></jsp:useBean>


<!DOCTYPE html>

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
				<div class="alert alert-danger alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<%= request.getAttribute("error") %>
				</div>
			<% } %>
			
			<% if (request.getAttribute("action").equals("purchase")) { %>
			
				<form action="/festival/purchase-ticket" method="post">
					<div class="form-group">					
						<label for="inputFestival_Event">Festival Event</label>
						<select class="form-control" id="inputFestival_Event" name="inputFestival_Event">
							<% for(int i=0; i<festivalEventListBean.getItems().size(); i++) { %>
								<option value="<%= ((FestivalEventBean) festivalEventListBean.getItems().get(i)).getFestival_event_id() %>"><%= ((FestivalEventBean) festivalEventListBean.getItems().get(i)).getName() %></option>
							<% } %>
						</select>
					</div>
					
					<div class="form-group">	
						<label for="inputTicket_Type">Ticket Type</label>
						<select class="form-control" id="inputTicket_Type" name="inputTicket_Type">
							<% for(int i=0; i<ticketTypeListBean.getItems().size(); i++) { %>
								<option value="<%= ((TicketTypeBean) ((Pair) ticketTypeListBean.getItems().get(i)).getLeft()).getTicket_type_id() %>"><%= ((TicketTypeBean) ((Pair) ticketTypeListBean.getItems().get(i)).getLeft()).getType() %> (<%= String.format("%.2f", ((TicketPriceBean) ((Pair) ticketTypeListBean.getItems().get(i)).getRight()).getPrice()) %> €) </option>
							<% } %>
						</select>
					</div>
					
					<div class="form-group">	
						<label for="inputFirst_Name">First Name</label>
						<input type="text" required class="form-control" id="inputFirst_Name" name="inputFirst_Name" placeholder="First Name" value="<%= Optional.ofNullable(visitorBean.getFirst_name()).orElse("") %>">
					</div>
					
					<div class="form-group">	
						<label for="inputLast_Name">Last Name</label>
						<input type="text" required class="form-control" id="inputLast_Name" name="inputLast_Name" placeholder="Last Name" value="<%= Optional.ofNullable(visitorBean.getLast_name()).orElse("") %>">
					</div>
					
					<div class="form-group">	
						<label for="inputEmail">E-Mail</label>
						<input type="text" required class="form-control" id="inputEmail" name="inputEmail" placeholder="E-Mail" value="<%= Optional.ofNullable(visitorBean.getEmail()).orElse("") %>">
					</div>
					
					<div class="form-group">	
						<label for="inputPhone">Phone</label>
						<input type="text" class="form-control" id="inputPhone" name="inputPhone" placeholder="Phone" value="<%= Optional.ofNullable(visitorBean.getPhone()).orElse("") %>">					
					</div>
					
					<div class="form-group">	
						<label for="inputBirthdate">Birthdate</label>
						<%
							String birthdateString = "";
							
							if (visitorBean.getBirthdate() != null) {
								birthdateString = DateConverter.SQLDateToUserInputString(visitorBean.getBirthdate());
							}
						%>
						
						<input type="date" required class="form-control" id="inputBirthdate" name="inputBirthdate" placeholder="01-01-1970" value="<%= birthdateString %>">
					</div>
					
					<div class="form-group">	
						<label for="inputSex">Sex</label>
						<select class="form-control" id="inputSex" name="inputSex">
							<% for(int i=0; i<sexListBean.getItems().size(); i++) { %>
								<option><%= sexListBean.getItems().get(i) %></option>
							<% } %>
						</select>		
					</div>				
									
					<div class="form-group">	
						<label for="inputAddress">Address</label>
						<input type="text" required class="form-control" id="inputAddress" name="inputAddress" placeholder="Number Street, County, Federal State Postcode">					
					</div>
					
					<div class="form-group">	
						<label for="inputCountry">Country</label>
						<input type="text" required class="form-control" id="inputCountry" name="inputCountry" placeholder="Country">
					</div>
					
					<div class="form-group" style="padding-bottom:20px">	
						<label for="inputPayment_Method">Payment Method</label>					
						<select class="form-control" id="inputPayment_Method" name="inputPayment_Method">
							<% for(int i=0; i<paymentMethodListBean.getItems().size(); i++) { %>
								<option><%= paymentMethodListBean.getItems().get(i) %></option>
							<% } %>
						</select>		
					</div>
					
					<button type="submit" class="btn btn-primary btn-lg">Submit</button>
					
				</form>
			
			<% } else if (request.getAttribute("action").equals("confirm")) { %>
			
				<p> 
					Please have a look again at the data entered by you and check your data for correctness. <br>
					Submitting the form means that you buy a personalized ticket which you can not return anymore.
				</p>
							
				<form action="/festival/purchase-ticket-confirm" method="post">
					<table class="table">
						<tbody>
							<tr>
								<th>Festival Event</th>
								<td><%= festivalEventBean.getName() %></td>
								<input type="hidden" name="inputFestival_Event" value="<%= festivalEventBean.getFestival_event_id() %>">
							</tr>
							<tr>
								<th>Ticket Type</th>
								<td><%= ticketTypeBean.getType() %></td>
								<input type="hidden" name="inputTicket_Type" value="<%= ticketTypeBean.getTicket_type_id() %>">
							</tr>
							<tr>
								<th>Ticket Price</th>
								<td><%= String.format("%.2f", ticketPriceBean.getPrice()) %> €</td>
							<tr>
								<th>First Name</th>
								<td><%= visitorBean.getFirst_name() %></td>
								<input type="hidden" name="inputFirst_Name" value="<%= visitorBean.getFirst_name() %>">
							</tr>
							<tr>
								<th>Last Name</th>
								<td><%= visitorBean.getLast_name() %></td>
								<input type="hidden" name="inputLast_Name" value="<%= visitorBean.getLast_name() %>">
							</tr>
							<tr>
								<th>E-Mail</th>
								<td><%= visitorBean.getEmail() %></td>
								<input type="hidden" name="inputEmail" value="<%= visitorBean.getEmail() %>">
							</tr>
							<tr>
								<th>Phone</th>
								<td><%= visitorBean.getPhone() %></td>
								<input type="hidden" name="inputPhone" value="<%= visitorBean.getPhone() %>">
							</tr>
							<tr>
								<th>Birthdate</th>
								<td><%= DateConverter.SQLDateToGermanDate(visitorBean.getBirthdate()) %></td>
								<input type="hidden" name="inputBirthdate" value="<%= visitorBean.getBirthdate() %>">
							</tr>
							<tr>
								<th>Sex</th>
								<td><%= visitorBean.getSex() %></td>
								<input type="hidden" name="inputSex" value="<%= visitorBean.getSex() %>">
							</tr>
							<tr>
								<th>Address</th>
								<td><%= visitorBean.getAddress() %></td>
								<input type="hidden" name="inputAddress" value="<%= visitorBean.getAddress() %>">
							</tr>
							<tr>
								<th>Country</th>
								<td><%= visitorBean.getCountry() %></td>
								<input type="hidden" name="inputCountry" value="<%= visitorBean.getCountry() %>">
							</tr>
							<tr>
								<th>Payment Method</th>
								<td><%= ticketBean.getPayment_method() %></td>
								<input type="hidden" name="inputPayment_Method" value="<%= ticketBean.getPayment_method() %>">
							</tr>
						</tbody>
					</table>
					
					<button type="submit" class="btn btn-primary btn-lg">Buy the ticket!</button>
					
				</form>	
						
			<% } %>
			
		</div>
	</body>
</html>