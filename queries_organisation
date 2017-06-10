--What notes have been placed for a particular department?
 
SELECT note.message
FROM note, employee, department
WHERE note.employee_id=employee.employee_id
AND employee.department_id=department.department_id
AND department.department_id=1;


--The amount of money collected from a particular type of ticket between certain dates.
 
SELECT ticket_type.type, SUM(price)
FROM ticket_price, ticket, ticket_type
WHERE ticket_price.ticket_type_id = ticket.ticket_type_id and ticket.ticket_type_id=ticket_type.ticket_type_id
AND booking_date BETWEEN '2017-05-01' AND '2017-05-31'
AND ticket_price.valid from <= booking_date AND booking_date <= coalesce (ticket_price.valid_to, current_date)
GROUP BY ticket_type.type;
 
  
--On which stage a particular song will be played and between what time?

SELECT distinct stage.stage_id, stage.name, band.band_id as BandId, band.timeslot_start as start, band.timeslot_end as end
FROM band, song, plays, stage
WHERE band.band_id=plays.band_id
AND band.stage_id=stage.stage_id
AND plays.song_id=2;


--How  many bands got accepted to a particular music festival?
 
SELECT
(SELECT COUNT(*)
FROM application
WHERE type='band') as BandsApplied,
(SELECT COUNT(*)
FROM application
WHERE type='band'
AND status='accepted') as BandsAccepted;

 
--Which location has had more than one festival organized?
 
SELECT location_id
FROM festival_event
GROUP BY location_id
HAVING COUNT(location_id)>1;