create table illness
(
    id    serial primary key  not null,
    name  varchar(300) unique not null,
    color char(7) unique      not null
);
