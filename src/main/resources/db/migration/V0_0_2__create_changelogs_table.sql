create table changelogs (
    id SERIAL primary key,
    event_type char(6) not null,
    table_name varchar(100),
    data JSONB not null,
    status varchar(10) default 'WAITING',
    created_at timestamptz not null default now(),
    modified_at timestamptz not null default now()
);