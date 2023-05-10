create table student
(
    id         text primary key,
    name       text not null,
    age        int,
    gender     int,
    created_at timestamp,
    updated_at timestamp
)