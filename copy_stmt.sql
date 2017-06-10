COPY visitor (visitor_id, email, last_name, first_name, address, country, birthdate, phone, sex)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/visitor.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY journalist (journalist_id, news_agency, visitor_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/journalist.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY ticket_type (ticket_type_id, type, arrival_day, departure_day)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/ticket_type.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY ticket_price (ticket_price_id, price, valid_from, valid_to, ticket_type_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/ticket_price.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY sponsor (sponsor_id, provider_id, money)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/sponsor.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY vendor (vendor_id, sponsor_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/vendor.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY donor (donor_id, sponsor_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/donor.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY area (area_type, capacity)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/area.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY advertisement (advertisement_id, type, quantity, donor_id, area_type)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/advertisement.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY stage (stage_id, name, capacity, type, number_seats)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/stage.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY band (band_id, headliner, timeslot_date, timeslot_start, timeslot_end, press_information, is_cancelled, provider_id, stage_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/band.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY song (song_id, name, lyricist, length)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/song.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY visitor_account (username, password, filter_date, alarm, visitor_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/visitor_account.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY timetable ( timetable_id, band_id, username, rating)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/timetable.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY product (product_id, name, price, type, category, provider_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/product.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY shop (shop_id, name, category, vendor_id, area_type)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/shop.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY sold_in (shop_id, product_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/sold_in.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY wristband (wristband_id, visitor_id, disabled, balance)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/wristband.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY location (location_id, postcode, city, country)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/location.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY festival_event (festival_event_id, start_date, end_date, name, location_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/festival_event.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
     
 
COPY ticket (ticket_id, booking_date, payment_method, ticket_type_id, visitor_id, festival_event_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/ticket.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY department (department_id, name)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/department.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY employee (employee_id, first_name, last_name, birthdate, department_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/employee.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY note (note_id, message, time, employee_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/note.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY employee_note (employee_id, note_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/employee_note.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY instruction (instruction_id, text, time, band_id, employee_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/instruction.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY shift (shift_id, type, place, start_time, end_time)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/shift.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY employee_shift (employee_id, shift_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/employee_shift.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY newsletter (newsletter_id, title, publish_time)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/newsletter.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY post (post_id, heading, content, publish_time, tags, newsletter_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/post.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY sale (sale_id, time, quantity, wristband_id, shop_id, product_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/sale.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY application (application_id, type, description, date, status, festival_event_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/application.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
  
 
COPY provider (provider_id, name, application_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/provider.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
COPY area_access (wristband_id, area_type, time)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/area_access.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
 
 
COPY plays (song_id, band_id)
FROM '/home/sj/Schreibtisch/Datenbanksysteme/project_tables/plays.csv'
WITH DELIMITER ';'
NULL AS 'null' csv;
