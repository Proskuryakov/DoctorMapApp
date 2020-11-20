create table user_role
(
    id   integer primary key not null,
    name varchar(30)         not null
);

create table users
(
    id       SERIAL primary key,
    email    varchar(100) not null unique,
    password char(512)    not null,
    fio      varchar(250) not null,
    role_id  integer      not null,

    constraint users_user_role_role_id_fk
        foreign key (role_id)
            references user_role (id)
);

insert into user_role
values (1, 'ADMIN'),
       (2, 'DOCTOR');
