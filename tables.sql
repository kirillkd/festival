create table visitor (
    visitor_id    serial primary key,
    email        varchar(100) not null,
    last_name    varchar(100) not null,
    first_name    varchar(100) not null,
    address    varchar(250) not null,
    country    varchar(100) not null,
    birthdate    date not null,
    phone        varchar(50),
    sex        varchar(1)
);
 
 
create table journalist (
    journalist_id        serial primary key,
    news_agency      varchar(50) not null,
    visitor_id        integer references visitor (visitor_id) not null
);
 
 
create table ticket_type (
    ticket_type_id        serial primary key,
    type            varchar(100) not null,
arrival_day        date not null,
departure_day        date not null
);
 
 
create table ticket_price (
    ticket_price_id        serial primary key,
    price            numeric(6,2),
    valid_from        date not null,
    valid_to        date,
    ticket_type_id        integer references ticket_type (ticket_type_id) not null
);
    
    
create table provider (
    provider_id    serial primary key,
name        varchar(100) not null
);
 
 
 
create table sponsor (
sponsor_id    serial primary key,
    provider_id    integer references provider (provider_id) not null,
money        numeric(9,2) not null
);
 
 
create table vendor (
    vendor_id    serial primary key,
    sponsor_id    integer references sponsor (sponsor_id) not null
);
 
 
create table donor (
    donor_id    serial primary key,
    sponsor_id    integer references sponsor (sponsor_id) not null
);
 
 
create table area (
    area_type    varchar(100) primary key,
    capacity    integer not null
);
 
 
create table advertisement (
    advertisement_id    integer not null,
    type                    varchar(100) not null,
    quantity                integer not null,
    donor_id                integer references donor (donor_id),
    area_type                varchar(100) references area (area_type),
    constraint ad_pk         primary key (advertisement_id, area_type)
);
 
 
create table stage (
    stage_id        serial primary key,
    name            varchar(100) not null,
    capacity        integer not null,
    type            varchar(200) not null,
    number_seats     integer not null default 0
);
 
 
 
 
create table band (
    band_id                   serial primary key,
    headliner        boolean not null default false,
    timeslot_date        date not null,
    timeslot_start        date not null,
    timeslot_end        date not null,
    press_information    text,
    is_cancelled        boolean not null default false,
    provider_id        integer references provider (provider_id) not null,
    stage_id        integer references stage (stage_id)
);
 
 
 
create table song (
    song_id    serial primary key,
    name        varchar(100) not null,
    lyricist        varchar(100) not null,
    length        interval
);
 
 
create table visitor_account (
    username    varchar(100) primary key,
    password    varchar(100) not null,
    filter_date    date,
    alarm        timestamp,
    visitor_id    integer unique not null references visitor (visitor_id)
);
 
 
create table timetable (
    timetable_id    serial primary key,
    band_id    integer references band (band_id),
username     varchar(100) references visitor_account (username),
    rating        integer not null default 1 check (rating > 0 and rating < 6)
);
 
 
create table product (
    product_id    serial primary key,
    name        varchar(100) not null,
    price        numeric(5,2) not null,
    type        varchar(200) not null,
    category    varchar(200) not null,
    provider_id    integer references provider (provider_id) --null value means that the product is provided by the festival itself
);
 
 
create table shop (
    shop_id    serial primary key,
    name        varchar(100) not null,
    category    varchar(100),
    vendor_id    integer references vendor (vendor_id),
    area_type    varchar(100) references area (area_type)
);
 
 
create table sold_in (
    shop_id        integer references shop (shop_id),
    product_id        integer references product (product_id),
    constraint sold_in_pk primary key (shop_id, product_id)
);
 
 
create table wristband (
    wristband_id    serial primary key,
    visitor_id    integer references visitor (visitor_id),
    disabled    boolean not null default true,
    balance    numeric (6,2) not null default 0.0 check (balance >= 0)
);
 
 
create table location (
    location_id    serial primary key,
    postcode    varchar(10) not null,
    city        varchar(100) not null,
    country    varchar(50) not null
);
 
 
create table festival_event (
    festival_event_id     serial primary key,
    start_date            timestamp not null,
    end_date            timestamp not null,
    name                varchar(50) not null,
    location_id            integer references location (location_id) not null
);
 
 
create table ticket (
    ticket_id             serial primary key,
    booking_date              date not null,
    payment_method      varchar(50) not null,
    ticket_type_id             integer references ticket_type (ticket_type_id),
    visitor_id             integer references visitor (visitor_id) not null,
    festival_event_id    integer references festival_event (festival_event_id) not null
);
 
 
create table department (
    department_id                serial primary key,
    name                    varchar(100) not null,
    festival_event_id    integer references festival_event (festival_event_id) not null
);
 
 
create table employee (
    employee_id        serial primary key,
    first_name        varchar(100) not null,
    last_name        varchar(100) not null,
    birthdate        date not null,
    department_id        integer references department (department_id)
);
 
create table note (
    note_id    serial primary key,
    message    text not null,
    time        timestamp,
    employee_id    integer references employee (employee_id)
);
 
 
create table employee_note (
    employee_id            integer references employee (employee_id),
note_id            integer references note (note_id),
    constraint emp_note_pk     primary key (employee_id, note_id)
);
 
 
create table instruction (
    instruction_id    serial primary key,
    text        text not null,
    time        timestamp not null,
    band_id    integer references band (band_id) not null,
    employee_id    integer references employee (employee_id)
);
 
 
create table shift (
    shift_id        serial primary key,
    type        varchar(100) not null,
    place        varchar(100) not null,
    start_time    timestamp not null,
    end_time    timestamp not null
);
 
 
create table employee_shift (
    employee_id                   integer references employee (employee_id),
    shift_id                       integer references shift (shift_id),
    constraint emp_shift_pk primary key (employee_id, shift_id)
);
 
 
create table newsletter (
    newsletter_id    serial primary key,
    title        varchar(100) not null,
    publish_time    timestamp not null
);
 
 
create table newsletter_application (
    visitor_id    integer references visitor (visitor_id),
    newsletter_id     integer references newsletter (newsletter_id),
    constraint visit_news_pk primary key (visitor_id, newsletter_id)
);
 
 
create table post (
    post_id        serial primary key,
    heading    varchar(100) not null,
    content    text not null,
    publish_time    timestamp not null,
    tags        varchar(200),
    newsletter_id     integer references newsletter (newsletter_id)
);
 
 
create table sale (
    sale_id        serial primary key,
    time        timestamp not null,
    quantity    integer not null,
    wristband_id    integer references wristband (wristband_id) not null,
    shop_id    integer references shop (shop_id) not null,
    product_id    integer references product (product_id) not null
);
 
 
create table application (
    application_id    serial primary key,
    type        varchar(30) not null,
    description    text not null,
    date        timestamp not null,
    status        varchar(15),
    provider_id    integer references provider (provider_id) not null,
	festival_event_id	integer references festival_event (festival_event_id) not null
);
 
 
create table area_access (
    wristband_id            integer references wristband (wristband_id) not null,
    area_type            varchar(100) references area (area_type) not null,
    time                timestamp not null,
    constraint wristband_time     primary key (wristband_id, time)
);
create table plays (
    song_id            integer not null references song (song_id),
    band_id            integer not null references band (band_id),
    constraint plays_pk         primary key (song_id, band_id)
);

