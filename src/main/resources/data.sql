create table WATCH
(
    ID                integer not null,
    WATCH_ID          varchar not null,
    WATCH_NAME        varchar not null,
    UNIT_PRICE        integer not null,
    DISCOUNT_QUANTITY integer,
    DISCOUNT          integer
);

insert into WATCH(ID, WATCH_ID, WATCH_NAME, UNIT_PRICE, DISCOUNT_QUANTITY, DISCOUNT)
values (1, '001', 'Rolex', 100, 3, 100);

insert into WATCH(ID, WATCH_ID, WATCH_NAME, UNIT_PRICE, DISCOUNT_QUANTITY, DISCOUNT)
values (2, '002', 'Michael Kors', 80, 2, 40);

insert into WATCH(ID, WATCH_ID, WATCH_NAME, UNIT_PRICE)
values (3, '003', 'Swatch', 50);

insert into WATCH(ID, WATCH_ID, WATCH_NAME, UNIT_PRICE)
values (4, '004', 'Casio', 30);

