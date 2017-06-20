CREATE INDEX ON timetable_entry(username);
CREATE INDEX ON timetable_entry(band_id);
CREATE INDEX ON area(area_type);
CREATE INDEX ON product(provider_id);
CREATE INDEX ON vendor(sponsor_id);
 
CREATE INDEX ON journalist (visitor_id);
CREATE INDEX ON ticket (visitor_id);
CREATE INDEX ON ticket (ticket_type_id);
CREATE INDEX ON ticket_price (ticket_type_id);
 
CREATE INDEX ON provider(application_id);
CREATE INDEX ON band (provider_id);
CREATE INDEX ON band (stage_id);
CREATE INDEX ON sponsor (provider_id);
CREATE INDEX ON instruction (band_id);
CREATE INDEX ON instruction (employee_id);
CREATE INDEX ON visitor_account (visitor_id);
CREATE INDEX ON plays (song_id);
CREATE INDEX ON plays (band_id);
 
CREATE INDEX ON donor (sponsor_id);
CREATE INDEX ON advertisement (area_type);
CREATE INDEX ON advertisement (donor_id);
CREATE INDEX ON shop (vendor_id);
CREATE INDEX ON shop (area_type);
CREATE INDEX ON sold_in (product_id);
CREATE INDEX ON wristband (visitor_id);
 
CREATE INDEX ON sale (wristband_id); 
CREATE INDEX ON sale (shop_id);
CREATE INDEX ON sale (product_id);
CREATE INDEX ON post (newsletter_id);
CREATE INDEX ON area_access (wristband_id);
CREATE INDEX ON festival_event (location_id);

