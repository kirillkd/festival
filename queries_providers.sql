--How many shops did each vendor run?

select p.name, count(*)
from shop sh, vendor v, sponsor sp, provider p
where sh.vendor_id = v.vendor_id
and v.sponsor_id = sp.sponsor_id
and sp.provider_id = p.provider_id
group by p.name;


--Total time of music played?

select sum(s.length)
from plays ps, song s
where ps.song_id = s.song_id;


--How much money did each visitor spend in total?

select v.last_name, v.first_name, sum(s.quantity * p.price) + tp.price as total
from sale s, wristband w, visitor v, product p, ticket t, ticket_price tp
where s.wristband_id = w.wristband_id
and w.visitor_id = v.visitor_id
and s.product_id = p.product_id
and v.visitor_id = t.visitor_id
and t.ticket_type_id = tp.ticket_type_id
and t.booking_date >= tp.valid_from
and t.booking_date <= coalesce(tp.valid_to, current_date)
group by v.last_name, v.first_name, tp.price;


--How many songs were played in average per band?

with total_songs_per_band as (
	select band_id, count(*) as songs
	from plays
	group by band_id
)
select avg(songs) from total_songs_per_band;


--How much money did the bands earn with their merchandise?

select prov.name, sum(s.quantity * prod.price) as total
from sale s, product prod, provider prov, band b
where s.product_id = prod.product_id
and prod.provider_id = prov.provider_id
and prov.provider_id = b.provider_id
group by prov.name;

