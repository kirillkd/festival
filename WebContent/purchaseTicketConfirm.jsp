<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="beans.FestivalEventBean, beans.VisitorBean, beans.TicketBean, beans.TicketPriceBean" %>


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
							<td><%= visitorBean.getBirthdate() %></td>
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
		</div>
	</body>
</html>