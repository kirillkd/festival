--How many shops did each vendor run?

SELECT p.name, COUNT(*)
FROM shop sh, vendor v, sponsor sp, provider p
WHERE sh.vendor_id = v.vendor_id
AND v.sponsor_id = sp.sponsor_id
AND sp.provider_id = p.provider_id
GROUP BY p.name;


--Total time of music played?

SELECT SUM(s.length)
FROM plays ps, song s
WHERE ps.song_id = s.song_id;


--How much money did each visitor spend in total?

SELECT v.last_name, v.first_name, SUM(s.quantity * p.price) + tp.price AS total
FROM sale s, wristband w, visitor v, product p, ticket t, ticket_price tp
WHERE s.wristband_id = w.wristband_id
AND w.visitor_id = v.visitor_id
AND s.product_id = p.product_id
AND v.visitor_id = t.visitor_id
AND t.ticket_type_id = tp.ticket_type_id
AND t.booking_date >= tp.valid_from
AND t.booking_date <= coalesce(tp.valid_to, current_date)
GROUP BY v.last_name, v.first_name, tp.price;


--How many songs were played in average per band?

WITH total_songs_per_band AS (
	SELECT band_id, COUNT(*) AS songs
	FROM plays
	GROUP BY band_id
)
SELECT AVG(songs) FROM total_songs_per_band;


--How much money did the bands earn with their merchandise?

SELECT prov.name, SUM(s.quantity * prod.price) AS total
FROM sale s, product prod, provider prov, band b
WHERE s.product_id = prod.product_id
AND prod.provider_id = prov.provider_id
AND prov.provider_id = b.provider_id
GROUP BY prov.name;

