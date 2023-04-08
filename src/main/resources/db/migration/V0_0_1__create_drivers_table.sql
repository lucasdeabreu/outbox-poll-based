create table drivers (
    id serial primary key,
    name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(15) not null
);