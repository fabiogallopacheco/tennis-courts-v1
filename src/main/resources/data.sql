insert into guest(id, name) values(null, 'Roger Federer');
insert into guest(id, name) values(null, 'Rafael Nadal');

insert into tennis_court(id, name) values(null, 'Roland Garros - Court Philippe-Chatrier');

insert
    into
        schedule
        (id, start_date_time, end_date_time, tennis_court_id)
    values
        (null, '2021-12-20T20:00:00.0', '2021-12-20T21:00:00.0', 1);

insert
    into
        reservation
        (id, guest_id, schedule_id, value, reservation_status, refund_value)
    values
        (null, 1, 1, 10, 0, 0);