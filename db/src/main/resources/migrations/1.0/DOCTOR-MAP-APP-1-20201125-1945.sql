create table geoaddress
(
    id     serial primary key not null,
    region varchar(100)       not null,
    city   varchar(100)       not null,
    street varchar(100)       not null,
    house  varchar(100)       not null,
    lat    NUMERIC(10, 7)     not null,
    lon    NUMERIC(10, 7)     not null,
    unique (region, city, street, house, lat, lon)
);

create table sick
(
    id              serial primary key not null,
    address_id      integer            not null,
    surname         varchar(50)        not null,
    name_patronymic varchar(150)       not null,

    constraint sick_address_address_id
        foreign key (address_id)
            references geoAddress (id)
);

create table illness_sick
(
    sick_id    integer not null,
    illness_id integer not null,
    primary key (sick_id, illness_id),

    constraint illness_sick_sick_sick_id
        foreign key (sick_id)
            references sick (id) on delete cascade,

    constraint illness_sick_illness_illness_id
        foreign key (illness_id)
            references illness (id) on delete cascade
);
