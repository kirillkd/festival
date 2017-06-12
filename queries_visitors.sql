--Price a specific visitor paid for his ticket?
---Input: Visitor_ID
SELECT ticket_price.price, visitor.visitor_ID, visitor.first_name, visitor.last_name
FROM visitor, ticket, ticket_price
WHERE visitor.visitor_ID = 2
AND visitor.visitor_ID = ticket.visitor_ID
and ticket.ticket_type_id = ticket_price.ticket_type_id
and ticket.booking_date >= ticket_price.valid_from
and ticket.booking_date <= coalesce(ticket_price.valid_to, current_date);
 
--Which ticket-types have been least sold?
WITH sold_tickets AS (
    SELECT COUNT (ticket.ticket_id) AS number, ticket_type.type AS type
	FROM ticket, ticket_type
	WHERE ticket.ticket_type_id = ticket_type.ticket_type_id
	GROUP BY ticket_type.type
)
SELECT sold_tickets.*
FROM sold_tickets
WHERE sold_tickets.number = 
	(SELECT MIN(sold_tickets.number)
	FROM sold_tickets);

--Timetable of a specific visitor?
---Input: Visitor_ID
SELECT timetable.*, visitor.first_name, visitor.last_name
FROM visitor, visitor_account, timetable
WHERE visitor.visitor_id = 10
AND visitor.visitor_id = visitor_account.visitor_id
AND visitor_account.username = timetable.username;
 
--How many visitors lost their rfid chip?
SELECT COUNT(visitor.*)
FROM visitor, wristband
WHERE wristband.disabled = 'true'
AND wristband.visitor_ID = visitor.visitor_ID;
 
--How many visitors has the festival?
SELECT COUNT(ticket)
FROM ticket;